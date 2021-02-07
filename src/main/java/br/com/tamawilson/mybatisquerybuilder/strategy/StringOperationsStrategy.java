package br.com.tamawilson.mybatisquerybuilder.strategy;

import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.strategy.enums.PredicateStrategyType;
import br.com.tamawilson.mybatisquerybuilder.strategy.interfaces.PredicateStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.maps.StringOperations;
import org.springframework.stereotype.Component;

@Component
public class StringOperationsStrategy implements PredicateStrategy {
    @Override
    public PredicateStrategyType getStrategyType() {
        return PredicateStrategyType.STRING_OPERATIONS;
    }

    @Override
    public String getPredicate(SearchCriteria criteria, MappedField mappedField) {
        StringOperations stringOperations = new StringOperations();
        String pattern = stringOperations.getOperations().get(criteria.getOperation());
        return String.format(pattern, mappedField.getColumnName(), criteria.getValue());
    }
}
