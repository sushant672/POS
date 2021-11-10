package com.increff.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.increff.pos.dao.ProductDao;
import com.increff.pos.pojo.ProductPojo;
import com.increff.pos.util.StringUtil;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;


    @Transactional(rollbackOn = ApiException.class)
    public void add(ProductPojo productPojo) throws ApiException{
        normalize(productPojo);
        check(productPojo);
        ProductPojo productPojo1= productDao.getIdFromBarcode(productPojo.getBarcode());
        if(productPojo1!=null) {
            throw new ApiException("Product with given barcode already exists: " + productPojo.getBarcode());
        }
        //TODO no need to convert mrp to float
        productPojo.setMrp(Math.round(productPojo.getMrp()*100.0)/100.0);
        productDao.insert(productPojo);
    }

    @Transactional(rollbackOn = ApiException.class)
    public void addList(List<ProductPojo> productPojoList) throws ApiException {
        for (ProductPojo productPojo:productPojoList){
            normalize(productPojo);
            check(productPojo);
        }
        for (ProductPojo productPojo:productPojoList){
            add(productPojo);
        }
    }

    @Transactional(rollbackOn = ApiException.class)
    public ProductPojo get(int id) throws ApiException {
        return getCheck(id);
    }

    @Transactional
    public ProductPojo getFromBarcode(String barcode) throws ApiException {
        return checkBarcode(barcode);
    }

    @Transactional
    public List<ProductPojo> getAll() {
        return productDao.selectAll();
    }

    @Transactional(rollbackOn  = ApiException.class)
    public void update(int id, ProductPojo productPojo) throws ApiException {
        check(productPojo);
        normalize(productPojo);
        ProductPojo productPojo1 = getCheck(id);
        productPojo1.setBarcode(productPojo.getBarcode());
        productPojo1.setName(productPojo.getName());
        productPojo1.setMrp(Math.round(productPojo.getMrp()*100.0)/100.0);
        productDao.update(id, productPojo1);
    }


    public void check(ProductPojo productPojo) throws ApiException {
        if(StringUtil.isEmpty(productPojo.getBarcode())) {
            throw new ApiException("Barcode cannot be empty");
        }
        if(StringUtil.isEmpty(productPojo.getName())) {
            throw new ApiException("Name cannot be empty");
        }
        if(productPojo.getMrp()<=0)
            throw new ApiException("Mrp cannot be negative or zero");

    }


    @Transactional(rollbackOn = ApiException.class)
    public ProductPojo checkBarcode(String barcode) throws ApiException {
        if(barcode==null)
            throw new ApiException("Barcode cannot be empty");
        ProductPojo productPojo= productDao.getIdFromBarcode(barcode);
        if(productPojo==null){
            throw new ApiException("Product with given barcode does not exist: "+ barcode);
        }
        return productPojo;
    }


    @Transactional
    public ProductPojo getCheck(int id) throws ApiException {
        ProductPojo productPojo = productDao.select(id);
        if (productPojo == null) {
            throw new ApiException("Product with given ID does not exist, id: " + id);
        }
        return productPojo;
    }


    @Transactional
    public Map<String, ProductPojo> getAllProductPojosByBarcode() {
        List<ProductPojo> productPojoList = getAll();
        Map<String, ProductPojo> barcodeProduct = new HashMap<>();
        for (ProductPojo productPojo : productPojoList) {
            barcodeProduct.put(productPojo.getBarcode(), productPojo);
        }
        return barcodeProduct;
    }


    @Transactional
    protected static void normalize(ProductPojo productPojo) {
        productPojo.setName(StringUtil.toLowerCase(productPojo.getName()));
        productPojo.setBarcode(StringUtil.toLowerCase(productPojo.getBarcode()));
    }

}
