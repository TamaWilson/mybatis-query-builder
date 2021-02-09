package br.com.tamawilson.mybatisquerybuilder.model.patterns;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicPredicatePatternsTest {

    static final String EQUAL = "%s = %s";
    static final String NOT_EQUALS = "%s != %s";
    static final String GREATER_THAN = "%s > %s";
    static final String LOWER_THAN = "%s < %s";
    static final String IN = "%s in %s";
    static final String NOT_IN = "%s not in %s";

    @Test
    void shouldReturnCorrectBasicPredicates() {
        Assertions.assertThat(BasicPredicatePatterns.EQUAL).isEqualTo(EQUAL);
        Assertions.assertThat(BasicPredicatePatterns.NOT_EQUALS).isEqualTo(NOT_EQUALS);
        Assertions.assertThat(BasicPredicatePatterns.GREATER_THAN).isEqualTo(GREATER_THAN);
        Assertions.assertThat(BasicPredicatePatterns.LOWER_THAN).isEqualTo(LOWER_THAN);
        Assertions.assertThat(BasicPredicatePatterns.IN).isEqualTo(IN);
        Assertions.assertThat(BasicPredicatePatterns.NOT_IN).isEqualTo(NOT_IN);

    }
}
