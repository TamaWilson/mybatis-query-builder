package br.com.tamawilson.mybatisquerybuilder.strategy.maps;

import br.com.tamawilson.mybatisquerybuilder.model.Operators;
import br.com.tamawilson.mybatisquerybuilder.model.patterns.StringPredicatePatterns;

import java.util.Map;

public class StringOperations extends BasicOperationMap {

    public StringOperations() {
        super();
        Map<Operators, String> operations = this.getOperations();

        operations.put(Operators.EQUAL, StringPredicatePatterns.EQUAL);
        operations.put(Operators.CONTAINS, StringPredicatePatterns.CONTAINS);
        operations.put(Operators.START_WITH, StringPredicatePatterns.STARTS_WITH);
        operations.put(Operators.END_WITH, StringPredicatePatterns.ENDS_WITH);
    }
}
