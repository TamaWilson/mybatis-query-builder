package br.com.tamawilson.mybatisquerybuilder.strategy.maps;

import br.com.tamawilson.mybatisquerybuilder.model.Operators;
import br.com.tamawilson.mybatisquerybuilder.model.patterns.BasicPredicatePatterns;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;

@Getter
public class BasicOperationMap {

    private final Map<Operators, String> operations = new EnumMap<>(Operators.class);

    public BasicOperationMap() {
        operations.put(Operators.EQUAL, BasicPredicatePatterns.EQUAL);
        operations.put(Operators.NOT_EQUALS, BasicPredicatePatterns.NOT_EQUALS);
        operations.put(Operators.GREATER_THAN, BasicPredicatePatterns.GREATER_THAN);
        operations.put(Operators.LOWER_THAN, BasicPredicatePatterns.LOWER_THAN);
        operations.put(Operators.IN, BasicPredicatePatterns.IN);
        operations.put(Operators.NOT_IN, BasicPredicatePatterns.NOT_IN);
    }
}
