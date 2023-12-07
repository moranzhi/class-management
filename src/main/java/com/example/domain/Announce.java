package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announce {
    private Integer  id;

    private String  title;

    private String  content;

    private String  senddate;

    private String  expiredate;

    private String  publisher;

    private String  ispub;

    private String  remark;
}
