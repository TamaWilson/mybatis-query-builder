package br.com.tamawilson.mybatisquerybuilder.strategy.factory;

import br.com.tamawilson.mybatisquerybuilder.strategy.BasicOperationsStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.NumericOperationsStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.StringOperationsStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.factory.PredicateStrategyFactory;
import br.com.tamawilson.mybatisquerybuilder.strategy.interfaces.PredicateStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class PredicateStrategyFactoryTest {

    @Test
     void shouldReturnABasicOperationsStrategy_whenAProvidedClassIsNotANumberOrString(){

        PredicateStrategy strategy = PredicateStrategyFactory.getStrategy(Date.class);

        Assertions.assertThat(strategy).isNotInstanceOf(NumericOperationsStrategy.class)
                .isNotInstanceOf(StringOperationsStrategy.class)
                .isInstanceOf(BasicOperationsStrategy.class);
    }


    @Test
    void shouldReturnANumericOperationsStrategy_whenAProvidedClassIsANumber(){

        PredicateStrategy strategy = PredicateStrategyFactory.getStrategy(Long.class);

        Assertions.assertThat(strategy).isNotInstanceOf(StringOperationsStrategy.class)
                .isNotInstanceOf(BasicOperationsStrategy.class)
                .isInstanceOf(NumericOperationsStrategy.class);
    }

    @Test
    void shouldReturnAStringOperationsStrategy_whenAProvidedClassIsAString(){

        PredicateStrategy strategy = PredicateStrategyFactory.getStrategy(String.class);

        Assertions.assertThat(strategy).isNotInstanceOf(BasicOperationsStrategy.class)
                .isNotInstanceOf(NumericOperationsStrategy.class)
                .isInstanceOf(StringOperationsStrategy.class);
    }
}