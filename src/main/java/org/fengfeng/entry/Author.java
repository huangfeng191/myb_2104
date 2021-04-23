package org.fengfeng.entry;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("author")
public class Author {
    private String username;
    private int id;
    private String password;
    private String email;

}
