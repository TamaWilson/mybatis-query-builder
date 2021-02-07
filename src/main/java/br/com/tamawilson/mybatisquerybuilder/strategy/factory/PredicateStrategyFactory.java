package br.com.tamawilson.mybatisquerybuilder.strategy.factory;

import br.com.tamawilson.mybatisquerybuilder.strategy.enums.PredicateStrategyType;
import br.com.tamawilson.mybatisquerybuilder.strategy.interfaces.PredicateStrategy;
import br.com.tamawilson.mybatisquerybuilder.utils.NumberReflectionsUtil;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@Component
public class PredicateStrategyFactory {

    private Map<PredicateStrategyType, PredicateStrategy> strategies;

    public PredicateStrategyFactory(Set<PredicateStrategy> strategiesSet) {
        createStrategy(strategiesSet);
    }

    public PredicateStrategy findStrategy(Class<?> clazz) {
        if(clazz.isAssignableFrom(String.class)){
            return strategies.get(PredicateStrategyType.STRING_OPERATIONS);
        }

        if(NumberReflectionsUtil.isReflectedAsNumber(clazz)){
            return strategies.get(PredicateStrategyType.NUMERIC_OPERATIONS);
        }

        return strategies.get(PredicateStrategyType.BASIC_OPERATIONS);
    }

    private void createStrategy(Set<PredicateStrategy> strategySet) {
        strategies = new EnumMap<>(PredicateStrategyType.class);
        strategySet.forEach(
                strategy ->strategies.put(strategy.getStrategyType(), strategy));
    }

}
