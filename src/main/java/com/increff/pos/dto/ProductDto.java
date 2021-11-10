package com.increff.pos.dto;

import com.increff.pos.model.ProductForm;
import com.increff.pos.pojo.BrandPojo;
import com.increff.pos.pojo.ProductPojo;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductDto {

    /*
    @Autowired
    private BrandService brandService;

    public ProductPojo convert(ProductForm productForm) throws ApiException {

        ProductPojo productPojo = new ProductPojo();

        productPojo.setBarcode(productForm.getBarcode());
        productPojo.setMrp(productPojo.getMrp());
        productPojo.setName(productForm.getName());

        BrandPojo brandPojo = brandService.getBrandPojo(productForm.getBrand(), productForm.getCategory());
        productPojo.setBrandCategory(brandPojo.getId());

        return productPojo;
    }

     */
}
