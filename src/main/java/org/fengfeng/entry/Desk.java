package org.fengfeng.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("Desk")
public class Desk implements Serializable {

    private int id;
    private String tp;
    private String name;
    private int feet;
}
