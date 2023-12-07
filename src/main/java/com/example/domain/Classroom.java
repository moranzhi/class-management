package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    private Integer  id;

    private String  classid;

    private String  address;

    private String  status;

    private String  maxmember;

    private String  remark;
}
