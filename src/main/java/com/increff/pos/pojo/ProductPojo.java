package com.increff.pos.pojo;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"barcode"}, name="uniqueBarcode")})
public class ProductPojo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String barcode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double mrp;

    private Integer brandCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public Integer getBrandCategory() {
        return brandCategory;
    }

    public void setBrandCategory(Integer brandCategory) {
        this.brandCategory = brandCategory;
    }

}
