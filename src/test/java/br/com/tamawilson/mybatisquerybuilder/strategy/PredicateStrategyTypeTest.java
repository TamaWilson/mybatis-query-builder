package br.com.tamawilson.mybatisquerybuilder.strategy;

import br.com.tamawilson.mybatisquerybuilder.strategy.enums.PredicateStrategyType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PredicateStrategyTypeTest {

    @Test
    void shouldReturnTheCorrectStrategyType(){
        Assertions.assertThat(PredicateStrategyType.BASIC_OPERATIONS.getTypeName()).isEqualTo("basic_operations");
        Assertions.assertThat(PredicateStrategyType.NUMERIC_OPERATIONS.getTypeName()).isEqualTo("numeric_operations");
        Assertions.assertThat(PredicateStrategyType.STRING_OPERATIONS.getTypeName()).isEqualTo("string_operations");
    }
}
