package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer  id;

    private String  bookname;

    private String  bookclass;

    private String  price;

    private Integer  num;

    private String  buydate;

    private String  remark;

}
