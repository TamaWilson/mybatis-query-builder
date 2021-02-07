package br.com.tamawilson.mybatisquerybuilder.strategy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PredicateStrategyType {

    BASIC_OPERATIONS("basic_operations"),
    NUMERIC_OPERATIONS("numeric_operations"),
    STRING_OPERATIONS("string_operations");



    @Getter
    private final String typeName;
}
