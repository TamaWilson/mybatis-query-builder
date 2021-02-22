package br.com.tamawilson.mybatisquerybuilder.model.patterns;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicPredicatePatternsTest {

    public static final String EQUAL = "? = ?";
    public static final String NOT_EQUALS = "? != ?";
    public static final String GREATER_THAN = "? > ?";
    public static final String LOWER_THAN = "? < ?";
    public static final String IN = "? in ?";
    public static final String NOT_IN = "? not in ?";

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
