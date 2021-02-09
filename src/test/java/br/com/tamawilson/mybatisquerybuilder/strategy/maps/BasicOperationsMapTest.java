package br.com.tamawilson.mybatisquerybuilder.strategy.maps;

import br.com.tamawilson.mybatisquerybuilder.model.Operators;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class BasicOperationsMapTest {

    private static NumericOperations numericOperations;
    private static List<Operators> avaiableOperators;

    @BeforeAll
    static void setUp() {
        numericOperations = new NumericOperations();

        avaiableOperators = new ArrayList<>();
        avaiableOperators.add(Operators.EQUAL);
        avaiableOperators.add(Operators.NOT_EQUALS);
        avaiableOperators.add(Operators.GREATER_THAN);
        avaiableOperators.add(Operators.LOWER_THAN);
        avaiableOperators.add(Operators.IN);
        avaiableOperators.add(Operators.NOT_IN);
    }

    @Test
    void shloudHaveAllBasicOperationsMapped() {
        Map<Operators, String> operations = numericOperations.getOperations();
        Assertions.assertThat(operations.keySet()).hasSameElementsAs(avaiableOperators);
    }
}
