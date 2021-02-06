package br.com.tamawilson.mybatisquerybuilder;


import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteriaWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyBatisPredicateBuilder {

    private final List<SearchCriteria> params;
    private final Class<?> clazz;

    public MyBatisPredicateBuilder(Class<?> clazz) {
        params = new ArrayList<>();
        this.clazz = clazz;
    }


    public MyBatisPredicateBuilder withCriteria(
            String key, String operation, Object value, String aggregator) {

        params.add(new SearchCriteria(key, operation, value, aggregator));

        return this;
    }

    public MyBatisPredicateBuilder withCriteria(
            SearchCriteriaWrapper searchCriteriaWrapper) {
        if (searchCriteriaWrapper != null) {
            params.addAll(searchCriteriaWrapper.getSearchCriterias());
        }

        return this;
    }

    public String build() {
        if (params.isEmpty()) {
            return null;
        }

        return params.stream().map(param -> {
            MyBatisPredicate predicate = new MyBatisPredicate();

            predicate.setClass(clazz).setCriteria(param).generatePredicate();

            int currentIndex = params.indexOf(param);

            if (params.size() > 1 && currentIndex + 1 != params.size()) {
                predicate.addAgregator();
            }

            return predicate.getPredicate();
        }).filter(Objects::nonNull).collect(Collectors.joining(" "));
    }
}
