package org.fengfeng.entry;

import lombok.Data;

import java.io.Serializable;

@Data
public class People implements Serializable {
    private String name;
    private String age;
    private String hobby;
}
