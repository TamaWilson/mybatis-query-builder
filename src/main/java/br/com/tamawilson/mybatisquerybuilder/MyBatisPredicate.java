package br.com.tamawilson.mybatisquerybuilder;


import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.strategy.factory.PredicateStrategyFactory;
import br.com.tamawilson.mybatisquerybuilder.strategy.interfaces.PredicateStrategy;

import static br.com.tamawilson.mybatisquerybuilder.utils.MyBatisColumnUtil.extractColumn;


public class MyBatisPredicate {

    private StringBuilder predicate;
    private SearchCriteria criteria;
    private Class<?> clazz;

    public MyBatisPredicate setCriteria(final SearchCriteria criteria) {
        this.criteria = criteria;
        return this;
    }

    public MyBatisPredicate setClass(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getPredicate() {
        return predicate.toString();
    }

    public MyBatisPredicate generatePredicate() {

        MappedField mappedField = extractColumn(criteria.getKey(), clazz);

        PredicateStrategy predicateStrategy = PredicateStrategyFactory.getStrategy(mappedField.getTypeAs());
        String stringCriteria = predicateStrategy.getPredicate(criteria, mappedField);
        predicate = new StringBuilder(stringCriteria);

        return this;
    }

    public void addAgregator() {
        predicate.append(" ").append(criteria.getAgregator());
    }


}



