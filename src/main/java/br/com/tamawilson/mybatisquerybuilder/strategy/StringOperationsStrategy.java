package br.com.tamawilson.mybatisquerybuilder.strategy;

import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.strategy.enums.PredicateStrategyType;
import br.com.tamawilson.mybatisquerybuilder.strategy.interfaces.PredicateStrategy;
import br.com.tamawilson.mybatisquerybuilder.strategy.maps.StringOperations;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

@Component
public class StringOperationsStrategy implements PredicateStrategy {
    @Override
    public PredicateStrategyType getStrategyType() {
        return PredicateStrategyType.STRING_OPERATIONS;
    }

    @Override
    public String getPredicate(SearchCriteria criteria, MappedField mappedField) {
        return getCondition(criteria, mappedField).toString();
    }

    @Override
    public Condition getCondition(SearchCriteria criteria, MappedField mappedField) {
        Condition condition = DSL.noCondition();
        String value = (String) criteria.getValue();

        Field<String> field = DSL.field(mappedField.getColumnName(), String.class);

        StringOperations stringOperations = new StringOperations();
        String pattern = stringOperations.getOperations().get(criteria.getOperation());
        return condition.and(pattern, field, value);
    }

}
