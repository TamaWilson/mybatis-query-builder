package br.com.tamawilson.mybatisquerybuilder.model;

import lombok.*;

import java.lang.reflect.Field;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MappedField {
    private Field field;
    private String columnName;
    private Class<?> typeAs;
}
