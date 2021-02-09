package br.com.tamawilson.mybatisquerybuilder.predicates;

import br.com.tamawilson.mybatisquerybuilder.MyBatisPredicate;
import br.com.tamawilson.mybatisquerybuilder.data.Foo;
import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.Operators;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.strategy.BasicOperationsStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.factory.PredicateStrategyFactory;
import br.com.tamawilson.mybatisquerybuilder.utils.MyBatisColumnUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MyBatisPredicateTest {

    private MyBatisPredicate myBatisPredicate;

    @BeforeEach
    void setUp(){
        myBatisPredicate = new MyBatisPredicate();
    }

    @Test
    void shouldSetCriteria_ThenReturnThePredicateInstance(){

        SearchCriteria searchCriteria = mock(SearchCriteria.class);

        Assertions.assertThat(myBatisPredicate.setCriteria(searchCriteria))
                .isInstanceOf(MyBatisPredicate.class);
    }

    @Test
    void shouldSetClass_ThenReturnThePredicateInstance(){

        Assertions.assertThat(myBatisPredicate.setClass(Foo.class))
                .isInstanceOf(MyBatisPredicate.class);
    }

    @Test
    void shoulGenerateAPredicateStringBuilder_ThenReturnThePredicateInstance(){
        try (MockedStatic<PredicateStrategyFactory> mocked = mockStatic(PredicateStrategyFactory.class);
                MockedStatic<MyBatisColumnUtil> utilMock = mockStatic(MyBatisColumnUtil.class)) {
        String key = "key";
        Operators operator = Operators.EQUAL;
        String value= "value";

        SearchCriteria searchCriteria = new SearchCriteria(key, operator, value, null);
        MappedField mappedField = new MappedField();
        mappedField.setTypeAs(Foo.class);

        utilMock.when(()->MyBatisColumnUtil.extractColumn(anyString(),any(Class.class))).thenReturn(mappedField);

        BasicOperationsStrategy strategy = mock(BasicOperationsStrategy.class);
        when(strategy.getPredicate(any(SearchCriteria.class), any(MappedField.class))).thenReturn("key = value");
        mocked.when(() -> PredicateStrategyFactory.getStrategy(Foo.class)).thenReturn(strategy);

        Assertions.assertThat(myBatisPredicate.setClass(Foo.class).setCriteria(searchCriteria).generatePredicate()).isInstanceOf(MyBatisPredicate.class);

        }
    }

    @Test
    void shoulReturnString_WhenTriesToGetPredicate(){
        try (MockedStatic<PredicateStrategyFactory> mocked = mockStatic(PredicateStrategyFactory.class);
             MockedStatic<MyBatisColumnUtil> utilMock = mockStatic(MyBatisColumnUtil.class)) {
            String key = "key";
            Operators operator = Operators.EQUAL;
            String value= "value";

            SearchCriteria searchCriteria = new SearchCriteria(key, operator, value, null);
            MappedField mappedField = new MappedField();
            mappedField.setTypeAs(Foo.class);

            utilMock.when(()->MyBatisColumnUtil.extractColumn(anyString(),any(Class.class))).thenReturn(mappedField);

            BasicOperationsStrategy strategy = mock(BasicOperationsStrategy.class);
            when(strategy.getPredicate(any(SearchCriteria.class), any(MappedField.class))).thenReturn("key = value");
            mocked.when(() -> PredicateStrategyFactory.getStrategy(Foo.class)).thenReturn(strategy);

            myBatisPredicate.setClass(Foo.class).setCriteria(searchCriteria).generatePredicate();
            String predicate = myBatisPredicate.getPredicate();

            Assertions.assertThat(predicate).isNotNull();
            Assertions.assertThat(predicate).isEqualTo("key = value");

        }
    }

    @Test
    void shouldAddAnAgregator_ThenDoNothing(){
        try (MockedStatic<PredicateStrategyFactory> mocked = mockStatic(PredicateStrategyFactory.class);
             MockedStatic<MyBatisColumnUtil> utilMock = mockStatic(MyBatisColumnUtil.class)) {

            String key = "key";
            Operators operator = Operators.EQUAL;
            String value= "value";

            SearchCriteria searchCriteria = new SearchCriteria(key, operator, value, null);
            MappedField mappedField = new MappedField();
            mappedField.setTypeAs(Foo.class);

            utilMock.when(()->MyBatisColumnUtil.extractColumn(anyString(),any(Class.class))).thenReturn(mappedField);

            BasicOperationsStrategy strategy = mock(BasicOperationsStrategy.class);
            when(strategy.getPredicate(any(SearchCriteria.class), any(MappedField.class))).thenReturn("key = value");
            mocked.when(() -> PredicateStrategyFactory.getStrategy(Foo.class)).thenReturn(strategy);

            myBatisPredicate.setClass(Foo.class).setCriteria(searchCriteria).generatePredicate().addAgregator();

            Assertions.assertThat(myBatisPredicate.getPredicate()).isNotNull();
            Assertions.assertThat(myBatisPredicate.getPredicate()).contains("and");
        }
    }
}
