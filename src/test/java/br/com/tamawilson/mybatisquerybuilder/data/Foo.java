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
public class Foo {

    @MyBatisColumn(name = "aaa00_id")
    private Long id;

    @MyBatisColumn(name = "aaa00_last_status")
    private Integer lastStatus;

    @MyBatisColumn(name = "aaa00_qtd")
    private Integer quantity;

    @MyBatisColumn(name = "aaa00_description")
    private String description;

    @MyBatisColumn(name= "aaa00_id_bar", nestedClass = Bar.class, rootAs = Long.class)
    private Bar bar;
}
