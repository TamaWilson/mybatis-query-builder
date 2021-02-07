package br.com.tamawilson.mybatisquerybuilder.strategy;

import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.strategy.enums.PredicateStrategyType;
import br.com.tamawilson.mybatisquerybuilder.strategy.interfaces.PredicateStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.maps.NumericOperations;
import org.springframework.stereotype.Component;

@Component
public class NumericOperationsStrategy implements PredicateStrategy {
    @Override
    public PredicateStrategyType getStrategyType() {
        return PredicateStrategyType.BASIC_OPERATIONS;
    }

    @Override
    public String getPredicate(SearchCriteria criteria, MappedField mappedField) {
        NumericOperations numericOperations = new NumericOperations();
        String pattern = numericOperations.getOperations().get(criteria.getOperation());
        return String.format(pattern, mappedField.getColumnName(), criteria.getValue());
    }
}
