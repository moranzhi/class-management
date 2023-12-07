package com.example.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    private Integer  id;

    private String  classname;

    private Integer  mambernum;

    private String  teacher;

    private String  opentime;

    private String  classstatus;
}
