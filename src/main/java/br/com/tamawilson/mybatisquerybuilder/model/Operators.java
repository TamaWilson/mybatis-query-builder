package br.com.tamawilson.mybatisquerybuilder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum  Operators {

    EQUAL(":"),
    NOT_EQUALS("!:"),
    GREATER_THAN(">"),
    LOWER_THAN("<"),
    IN("::"),
    NOT_IN("!::"),
    CONTAINS(":*"),
    START_WITH(":*"),
    END_WITH(":*");

    @Getter
    private final String operator;

    private static final Map<String, Operators> translateMap = new HashMap<>();

    static {
        for (Operators o : Operators.values()) {
            translateMap.put(o.getOperator(), o);
        }
    }

    public static Operators get(String operator) {
        return translateMap.get(operator);
    }

}
