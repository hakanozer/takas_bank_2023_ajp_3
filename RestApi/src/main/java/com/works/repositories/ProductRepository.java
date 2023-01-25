package com.works.repositories;

import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;
import com.works.projections.IProJoinCat;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByTitleEqualsIgnoreCase(String title);

    boolean existsByPidEquals(Long pid);

    // select * from product where UPPER(title) = UPPER(?)
    // Optional<Product> findByTitleEquals(String title);

    @Query(value = "select p.PID, p.TITLE, p.PRICE, c.CID, c.NAME from PRODUCT as p inner join PRODUCT_CATEGORIES PC on p.PID = PC.PRODUCTS_PID inner join CATEGORY C on C.CID = PC.CATEGORIES_CID where c.cid = ?1", nativeQuery = true)
    List<IProJoinCat> allProJoin( Long cid );

    @Query(value = "select pid.PID, pid.TITLE, pid.PRICE, c.CID, c.NAME from PRODUCT as pid inner join PRODUCT_CATEGORIES PC on pid.PID = PC.PRODUCTS_PID inner join CATEGORY C on C.CID = PC.CATEGORIES_CID where c.cid = ?1", nativeQuery = true)
    Page<IProJoinCat> allPageProJoin(Long cid, Pageable pageable);

}