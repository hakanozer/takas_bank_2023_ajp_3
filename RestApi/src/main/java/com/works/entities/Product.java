package com.works.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    //private String pid = UUID.randomUUID().toString();

    @Length(min = 2, max = 100)
    @NotEmpty
    @NotNull
    @Column(unique = true, length = 100)
    private String title;

    @NotEmpty
    @Length(min = 5, max = 200)
    @NotNull
    @Column(length = 200)
    private String detail;


    @Max(50000)
    @Min(1)
    @NotNull
    private Integer price;

    @ManyToMany
    List<Category> categories;

}
