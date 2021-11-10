package com.increff.pos.controller;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.BrandService;
import com.increff.pos.util.DataConversionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.increff.pos.pojo.BrandPojo;

import java.util.ArrayList;
import java.util.List;

//Controls the brand page of the application
@Api
@RestController
public class BrandController{

    @Autowired
    private BrandService brandService;

    //Add a brand
    @ApiOperation(value = "Add a brand")
    @RequestMapping(path = "/api/brand", method = RequestMethod.POST)
    public void add(@RequestBody BrandForm brandForm) throws ApiException {
        BrandPojo brandPojo = DataConversionUtil.convert(brandForm);
        brandService.add(brandPojo);
    }

    @ApiOperation(value = "Add brand list")
    @RequestMapping(path = "/api/brand/list", method = RequestMethod.POST)
    public void add(@RequestBody List<BrandForm> brandForm) throws ApiException {
        List<BrandPojo> brandPojoList=new ArrayList<>();
        for(BrandForm brandForm1:brandForm) {
            BrandPojo brandPojo = DataConversionUtil.convert(brandForm1);
            brandPojoList.add(brandPojo);
        }
        brandService.addList(brandPojoList);
    }

    //Retrieve a brand using id
    @ApiOperation(value = "Get a brand by Id")
    @RequestMapping(path = "/api/brand/{id}", method = RequestMethod.GET)
    public BrandData get(@PathVariable int id) throws ApiException {
        BrandPojo brandPojo = brandService.get(id);
        return DataConversionUtil.convert(brandPojo);
    }

    //Retrieve complete list of all brands
    @ApiOperation(value = "Get list of all brands")
    @RequestMapping(path = "/api/brand", method = RequestMethod.GET)
    public List<BrandData> getAll(){
        List<BrandPojo> brandPojoList = brandService.getAll();
        return DataConversionUtil.convert(brandPojoList);
    }

    //Updates a brand
    @ApiOperation(value = "Update a brand")
    @RequestMapping(path = "/api/brand/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id, @RequestBody BrandForm brandForm) throws ApiException {
        BrandPojo brandPojo = DataConversionUtil.convert(brandForm);
        brandService.update(id, brandPojo);
    }
}
