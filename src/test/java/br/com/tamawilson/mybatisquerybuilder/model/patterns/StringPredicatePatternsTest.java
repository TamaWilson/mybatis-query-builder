package br.com.tamawilson.mybatisquerybuilder.model.patterns;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringPredicatePatternsTest {

    static final String EQUAL = "unaccent(%s) = unaccent('%s')";
    static final String CONTAINS = "unaccent(%s) ilike unaccent('%%%s%%')";
    static final String STARTS_WITH = "unaccent(%s) ilike unaccent('%s%%')";
    static final String ENDS_WITH = "unaccent(%s) ilike unaccent('%%%s')";

    @Test
    void shouldReturnCorrectBasicPredicates() {
        Assertions.assertThat(StringPredicatePatterns.EQUAL).isEqualTo(EQUAL);
        Assertions.assertThat(StringPredicatePatterns.CONTAINS).isEqualTo(CONTAINS);
        Assertions.assertThat(StringPredicatePatterns.STARTS_WITH).isEqualTo(STARTS_WITH);
        Assertions.assertThat(StringPredicatePatterns.ENDS_WITH).isEqualTo(ENDS_WITH);
    }
}
