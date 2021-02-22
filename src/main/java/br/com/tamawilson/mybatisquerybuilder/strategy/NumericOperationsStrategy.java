package br.com.tamawilson.mybatisquerybuilder.strategy;

import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.strategy.enums.PredicateStrategyType;
import br.com.tamawilson.mybatisquerybuilder.strategy.interfaces.PredicateStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.maps.NumericOperations;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

@Component
public class NumericOperationsStrategy implements PredicateStrategy {

    @Override
    public PredicateStrategyType getStrategyType() {
        return PredicateStrategyType.NUMERIC_OPERATIONS;
    }

    @Override
    public String getPredicate(SearchCriteria criteria, MappedField mappedField) {
        return getCondition(criteria, mappedField).toString();
    }

    @Override
    public Condition getCondition(SearchCriteria criteria, MappedField mappedField) {
        Condition condition = DSL.noCondition();
        Number value  = Double.valueOf((String) criteria.getValue());

        Field<Double> field = DSL.field(mappedField.getColumnName(), Double.class);

        NumericOperations numericOperations = new NumericOperations();
        String pattern = numericOperations.getOperations().get(criteria.getOperation());
        return condition.and(pattern, field, value);
    }
}
