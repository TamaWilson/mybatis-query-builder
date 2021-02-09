package br.com.tamawilson.mybatisquerybuilder.strategy.maps;

import br.com.tamawilson.mybatisquerybuilder.model.Operators;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class StringOperationsTest {

    private static StringOperations stringOperations;
    private static List<Operators> avaiableOperators;

    @BeforeAll
    static void setUp() {
        stringOperations = new StringOperations();
        avaiableOperators = new ArrayList<>();
        avaiableOperators.add(Operators.EQUAL);
        avaiableOperators.add(Operators.NOT_EQUALS);
        avaiableOperators.add(Operators.GREATER_THAN);
        avaiableOperators.add(Operators.LOWER_THAN);
        avaiableOperators.add(Operators.IN);
        avaiableOperators.add(Operators.NOT_IN);
        avaiableOperators.add(Operators.CONTAINS);
        avaiableOperators.add(Operators.START_WITH);
        avaiableOperators.add(Operators.END_WITH);
    }

    @Test
    void shloudHaveAllNumericOperationsMapped() {
        Map<Operators, String> operations = stringOperations.getOperations();
        Assertions.assertThat(operations.keySet()).hasSameElementsAs(avaiableOperators);
    }
}
