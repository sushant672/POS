package com.increff.pos.util;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandPojo;
import pos.model.*;
import pos.pojo.*;


import java.util.ArrayList;
import java.util.List;

public class DataConversionUtil {

    //convert brand form into brand pojo
    public static BrandPojo convert(BrandForm brandForm) {
        BrandPojo brandPojo = new BrandPojo();
        brandPojo.setBrand(brandForm.getBrand());
        brandPojo.setCategory(brandForm.getCategory());
        return brandPojo;
    }

    //Converts a brand pojo into brand data
    public static BrandData convert(BrandPojo brandPojo) {
        BrandData brandData = new BrandData();
        brandData.setBrand(brandPojo.getBrand());
        brandData.setCategory(brandPojo.getCategory());
        brandData.setId(brandPojo.getId());
        return brandData;
    }
    //converts List of brand pojo to List of brand data
    public static List<BrandData> convert(List<BrandPojo> brandPojoList) {
        List<BrandData> brandDataList = new ArrayList<>();
        for (BrandPojo brandPojo : brandPojoList) {
            brandDataList.add(convert(brandPojo));
        }
        return brandDataList;
    }














}
