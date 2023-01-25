package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProductJoinCat {

    @Id
    private Long pid;
    private String title;
    private Integer price;
    private Long cid;
    private String name;

}
