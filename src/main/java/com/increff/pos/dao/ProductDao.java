package com.increff.pos.dao;

import org.springframework.stereotype.Repository;
import com.increff.pos.pojo.ProductPojo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class ProductDao extends AbstractDao{

    @PersistenceContext
    private EntityManager em;

    @Transactional()
    public void insert(ProductPojo productPojo){
        em.persist(productPojo);
    }


    public ProductPojo select(int id){
        return em.find(ProductPojo.class,id);
    }

    public List<ProductPojo> selectAll() {
        String select_all = "select p from ProductPojo p";
        TypedQuery<ProductPojo> query = getQuery(select_all,  ProductPojo.class);
        return query.getResultList();
    }

    public void update(int id,ProductPojo productPojo) {
        ProductPojo productPojo1=em.find(ProductPojo.class, id);
        productPojo1.setId(productPojo.getId());
        productPojo1.setMrp(productPojo.getMrp());
        productPojo1.setName(productPojo.getName());
        productPojo1.setBarcode(productPojo.getBarcode());
        productPojo1.setBrandCategory(productPojo.getBrandCategory());
        em.merge(productPojo1);
    }

    public ProductPojo getIdFromBarcode(String barcode){
        String select_id_barcode = "select p from ProductPojo p where barcode=:barcode";
        TypedQuery<ProductPojo> query = getQuery(select_id_barcode, ProductPojo.class);
        query.setParameter("barcode", barcode);
        List<ProductPojo> res=query.getResultList();
        if(res.size()>0){
            return res.get(0);
        }
        else
            return null;
    }
}
