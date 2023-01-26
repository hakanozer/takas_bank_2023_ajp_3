package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    private String url;
    private String userName;
    private String roles;
    private String userAgent;
    private String details;
    private Date date;

}
