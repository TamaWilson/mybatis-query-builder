package br.com.tamawilson.mybatisquerybuilder.strategy.interfaces;

import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.strategy.enums.PredicateStrategyType;

public interface PredicateStrategy {

    PredicateStrategyType getStrategyType();
    String getPredicate(SearchCriteria criteria, MappedField mappedField);
}
