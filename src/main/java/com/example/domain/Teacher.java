package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private Integer  id;

    private String  name;

    private String  sex;

    private String  nick;

    private String  money;

    private String  remark;
}
