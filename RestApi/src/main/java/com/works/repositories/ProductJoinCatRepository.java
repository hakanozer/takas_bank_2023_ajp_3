package com.works.repositories;

import com.works.entities.ProductJoinCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductJoinCatRepository extends JpaRepository<ProductJoinCat, Long> {


    @Query(value = "select p.PID, p.TITLE, p.PRICE, c.CID, c.NAME from PRODUCT as p inner join PRODUCT_CATEGORIES PC on p.PID = PC.PRODUCTS_PID inner join CATEGORY C on C.CID = PC.CATEGORIES_CID where c.cid = ?1", nativeQuery = true)
    List<ProductJoinCat> allProCat( long cid );

}