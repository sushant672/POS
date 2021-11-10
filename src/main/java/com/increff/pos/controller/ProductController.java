package com.increff.pos.controller;


import com.increff.pos.model.ProductData;
import com.increff.pos.model.ProductForm;

import com.increff.pos.pojo.BrandPojo;
import com.increff.pos.pojo.ProductPojo;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.BrandService;
import com.increff.pos.service.ProductService;
import com.increff.pos.util.DataConversionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Api
@RestController

public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;


    @ApiOperation(value = "Add a product")
    @RequestMapping(path = "/api/product", method = RequestMethod.POST)
    public void add(@RequestBody ProductForm productForm) throws ApiException {
        productForm.setBrand(productForm.getBrand().toLowerCase().trim());
        productForm.setCategory(productForm.getCategory().toLowerCase().trim());

        BrandPojo brandPojo=brandService.getBrandPojo(productForm.getBrand(), productForm.getCategory());
        productService.add(DataConversionUtil.convert(productForm,brandPojo));
    }


    @ApiOperation(value = "Adds products")
    @RequestMapping(path = "/api/product/list", method = RequestMethod.POST)
    public void add(@RequestBody List<ProductForm> productFormList) throws ApiException {
        List<ProductPojo> productPojoList=new ArrayList<>();
        for(ProductForm productForm:productFormList) {
            productForm.setBrand(productForm.getBrand().toLowerCase().trim());
            productForm.setCategory(productForm.getCategory().toLowerCase().trim());
            BrandPojo brandPojo=brandService.getBrandPojo(productForm.getBrand(), productForm.getCategory());
            productPojoList.add(DataConversionUtil.convert(productForm,brandPojo));
        }
        productService.addList(productPojoList);
    }


    @ApiOperation(value = "Get a product by Id")
    @RequestMapping(path = "/api/product/{id}", method = RequestMethod.GET)
    public ProductData get(@PathVariable int id) throws ApiException {
        ProductPojo productPojo = productService.get(id);
        BrandPojo brandPojo= brandService.get(productPojo.getBrandCategory());
        return DataConversionUtil.convert(productPojo,brandPojo);
    }


    @ApiOperation(value = "Get list of all products")
    @RequestMapping(path = "/api/product", method = RequestMethod.GET)
    public List<ProductData> getAll() throws ApiException {
        List<ProductPojo> productPojoList = productService.getAll();
        List<ProductData> productDataList = new ArrayList<>();
        for (ProductPojo productPojo : productPojoList){
            BrandPojo brandPojo= brandService.get(productPojo.getBrandCategory());
            productDataList.add(DataConversionUtil.convert(productPojo,brandPojo));
        }
        return productDataList;
    }


    @ApiOperation(value = "Updates a product")
    @RequestMapping(path = "/api/product/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id, @RequestBody ProductForm productForm) throws ApiException {
        ProductPojo productPojo= productService.get(id);
        productForm.setBrand(brandService.get(productPojo.getBrandCategory()).getBrand());
        productForm.setCategory(brandService.get(productPojo.getBrandCategory()).getCategory());
        BrandPojo brandPojo=brandService.getBrandPojo(productForm.getBrand(), productForm.getCategory());
        productService.update(id, DataConversionUtil.convert(productForm,brandPojo));
    }

}
