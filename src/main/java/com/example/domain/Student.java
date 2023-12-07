package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer  id;

    private String  name;

    private String  sex;

    private String  classid;

    private String  phone;

    private String  address;

    private String  remark;

    private String  birthday;
}
