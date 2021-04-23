package org.fengfeng.entry;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("blogAndAuthor")
public class BlogAndAuthor {
    private int id;
    private String name;
    private String rating;
    private Author author;
}
