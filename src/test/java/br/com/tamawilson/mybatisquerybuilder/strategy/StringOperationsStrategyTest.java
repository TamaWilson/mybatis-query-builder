package br.com.tamawilson.mybatisquerybuilder.strategy;

import br.com.tamawilson.mybatisquerybuilder.data.Foo;
import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.Operators;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.strategy.enums.PredicateStrategyType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class StringOperationsStrategyTest {

    private static StringOperationsStrategy strategy;
    private static SearchCriteria searchCriteria;
    private static MappedField mappedField;

    @BeforeAll
    static void setUp() throws NoSuchFieldException {
        strategy = new StringOperationsStrategy();
        searchCriteria = new SearchCriteria("id", Operators.EQUAL, "TESTE", null);

        mappedField = new MappedField();

        Field field = Foo.class.getDeclaredField("id");

        mappedField.setField(field);
        mappedField.setColumnName("aaa00_id");
        mappedField.setTypeAs(field.getType());
    }
    
    @Test
     void shouldReturnStrategyType(){
        Assertions.assertThat(strategy.getStrategyType()).isEqualTo(PredicateStrategyType.STRING_OPERATIONS);
    }

     @Test
     void shouldReturnAPredicate_givenASearchCriteriaAndMappedField() {

        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull();
     }

    @Test
    void shouldReturnAEQUALSPredicate_givenASearchCriteriaAndMappedField() {
        searchCriteria.setOperation(Operators.EQUAL);
        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull().contains(" = ");
    }

    @Test
    void shouldReturnANOT_EQUALSSPredicate_givenASearchCriteriaAndMappedField() {
        searchCriteria.setOperation(Operators.NOT_EQUALS);
        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull().contains(" != ");
    }

    @Test
    void shouldReturnAGREATER_THANPredicate_givenASearchCriteriaAndMappedField() {
        searchCriteria.setOperation(Operators.GREATER_THAN);
        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull().contains(" > ");
    }

    @Test
    void shouldReturnALOWER_THANPredicate_givenASearchCriteriaAndMappedField() {
        searchCriteria.setOperation(Operators.LOWER_THAN);
        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull().contains(" < ");
    }

    @Test
    void shouldReturnAINPredicate_givenASearchCriteriaAndMappedField() {
        searchCriteria.setOperation(Operators.IN);
        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull().contains(" in ");
    }

    @Test
    void shouldReturnANOT_INPredicate_givenASearchCriteriaAndMappedField() {
        searchCriteria.setOperation(Operators.NOT_IN);
        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull().contains(" not in ");
    }

    @Test
    void shouldReturnACONTAINSPredicate_givenASearchCriteriaAndMappedField() {
        searchCriteria.setOperation(Operators.CONTAINS);
        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull().contains(" ilike");
    }

    @Test
    void shouldReturnASTART_WITHPredicate_givenASearchCriteriaAndMappedField() {
        searchCriteria.setOperation(Operators.START_WITH);
        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull().contains(" ilike").contains("'TESTE' || '%'");
    }

    @Test
    void shouldReturnAEND_WITHPredicate_givenASearchCriteriaAndMappedField() {
        searchCriteria.setOperation(Operators.END_WITH);
        String predicate = strategy.getPredicate(searchCriteria, mappedField);
        Assertions.assertThat(predicate).isNotNull().contains(" ilike").contains("'%' || 'TESTE'");
    }
}
