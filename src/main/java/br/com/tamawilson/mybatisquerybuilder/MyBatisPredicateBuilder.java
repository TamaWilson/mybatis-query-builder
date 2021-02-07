package br.com.tamawilson.mybatisquerybuilder;


import br.com.tamawilson.mybatisquerybuilder.model.Operators;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteriaWrapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MyBatisPredicateBuilder {

    private List<SearchCriteria> params;
    private  Class<?> clazz;

   private final MyBatisPredicate predicate;

    public MyBatisPredicateBuilder(MyBatisPredicate predicate) {
        this.predicate = predicate;
        params = new ArrayList<>();
    }

    public MyBatisPredicateBuilder setClass(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public MyBatisPredicateBuilder withCriteria(
            String key, Operators operation, Object value, String aggregator) {

        params = new ArrayList<>(Collections.singletonList(new SearchCriteria(key, operation, value, aggregator)));

        return this;
    }

    public MyBatisPredicateBuilder withCriteria(
            SearchCriteriaWrapper searchCriteriaWrapper) {
        if (searchCriteriaWrapper != null) {

            params = searchCriteriaWrapper.getSearchCriterias();
        }

        return this;
    }

    public String build() {
        if (params.isEmpty()) {
            return null;
        }

        return params.stream().map(param -> {

            predicate.setClass(clazz).setCriteria(param).generatePredicate();

            int currentIndex = params.indexOf(param);

            if (params.size() > 1 && currentIndex + 1 != params.size()) {
                predicate.addAgregator();
            }

            return predicate.getPredicate();
        }).filter(Objects::nonNull).collect(Collectors.joining(" "));
    }
}
