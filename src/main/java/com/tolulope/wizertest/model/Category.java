package com.tolulope.wizertest.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
