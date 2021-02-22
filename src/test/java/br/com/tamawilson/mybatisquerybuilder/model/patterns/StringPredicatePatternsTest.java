package br.com.tamawilson.mybatisquerybuilder.model.patterns;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringPredicatePatternsTest {

    public static final String EQUAL = "unaccent({0}}) = unaccent({1})";
    public static final String CONTAINS = "unaccent({0}) ilike unaccent('%' || {1} || '%')";
    public static final String STARTS_WITH = "unaccent({0}) ilike unaccent({1} || '%')";
    public static final String ENDS_WITH = "unaccent({0}) ilike unaccent('%' || {1})";

    @Test
    void shouldReturnCorrectBasicPredicates() {
        Assertions.assertThat(StringPredicatePatterns.EQUAL).isEqualTo(EQUAL);
        Assertions.assertThat(StringPredicatePatterns.CONTAINS).isEqualTo(CONTAINS);
        Assertions.assertThat(StringPredicatePatterns.STARTS_WITH).isEqualTo(STARTS_WITH);
        Assertions.assertThat(StringPredicatePatterns.ENDS_WITH).isEqualTo(ENDS_WITH);
    }
}
