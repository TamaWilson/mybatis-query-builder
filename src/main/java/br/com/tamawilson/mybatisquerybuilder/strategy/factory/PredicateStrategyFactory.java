package br.com.tamawilson.mybatisquerybuilder.strategy.factory;

import br.com.tamawilson.mybatisquerybuilder.strategy.BasicOperationsStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.NumericOperationsStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.StringOperationsStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.enums.PredicateStrategyType;
import br.com.tamawilson.mybatisquerybuilder.strategy.interfaces.PredicateStrategy;
import br.com.tamawilson.mybatisquerybuilder.utils.NumberReflectionsUtil;

import java.util.EnumMap;
import java.util.Map;

public class PredicateStrategyFactory {

    private PredicateStrategyFactory() {
    }

    private static final Map<PredicateStrategyType, PredicateStrategy> strategies = new EnumMap<>(PredicateStrategyType.class);

    static {
        strategies.put(PredicateStrategyType.BASIC_OPERATIONS,
                new BasicOperationsStrategy());
        strategies.put(PredicateStrategyType.NUMERIC_OPERATIONS,
                new NumericOperationsStrategy());
        strategies.put(PredicateStrategyType.STRING_OPERATIONS,
                new StringOperationsStrategy());
    }

    public static PredicateStrategy getStrategy(Class<?> clazz) {
        if(clazz.isAssignableFrom(String.class)){
            return strategies.get(PredicateStrategyType.STRING_OPERATIONS);
        }

        if(NumberReflectionsUtil.isReflectedAsNumber(clazz)){
            return strategies.get(PredicateStrategyType.NUMERIC_OPERATIONS);
        }

        return strategies.get(PredicateStrategyType.BASIC_OPERATIONS);
    }



}
