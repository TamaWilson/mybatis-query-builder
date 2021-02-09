package br.com.tamawilson.mybatisquerybuilder.data;

import br.com.tamawilson.mybatisquerybuilder.model.annotation.MyBatisColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bar {

    @MyBatisColumn(name = "AAA01_id")
    private Long id;

    @MyBatisColumn(name = "AAA01_comments")
    private String comments;

    private Integer serial;
}