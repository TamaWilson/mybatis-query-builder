package br.com.tamawilson.mybatisquerybuilder.predicates;

import br.com.tamawilson.mybatisquerybuilder.MyBatisPredicateBuilder;
import br.com.tamawilson.mybatisquerybuilder.data.Foo;
import br.com.tamawilson.mybatisquerybuilder.model.Operators;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteriaWrapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MyBatisPredicateBuilderTest {

    @Test
    void sholdSetParams_whenPassAStringValueAnOPeratorAVelueAndAnAregator_thenReturnTheInstace (){

        MyBatisPredicateBuilder myBatisPredicate = new MyBatisPredicateBuilder(Class.class);
        MyBatisPredicateBuilder returned = myBatisPredicate.withCriteria("key", Operators.EQUAL, "value", "or");

        Assertions.assertThat(returned).isEqualTo(myBatisPredicate);
    }

    @Test
    void sholdSetParams_whenPasASearchCriteriaWrapper_thenReturnTheInstace (){

        MyBatisPredicateBuilder myBatisPredicate = new MyBatisPredicateBuilder(Class.class);
        SearchCriteriaWrapper searchCriteriaWrapper = new SearchCriteriaWrapper();

        List<SearchCriteria> searchCriteriaList = IntStream.of(1,5)
                .mapToObj(x-> new SearchCriteria("key", Operators.EQUAL, "value", "or"))
                .collect(Collectors.toList());

        searchCriteriaWrapper.setSearchCriterias(searchCriteriaList);

        MyBatisPredicateBuilder returned = myBatisPredicate.withCriteria(searchCriteriaWrapper);

        Assertions.assertThat(returned).isEqualTo(myBatisPredicate);
    }

    @Test
    void shouldReturnAStringOfPredicates_whenCallBuildMethodAfterSetParams(){
        MyBatisPredicateBuilder myBatisPredicate = new MyBatisPredicateBuilder(Foo.class);
        SearchCriteriaWrapper searchCriteriaWrapper = new SearchCriteriaWrapper();

        List<SearchCriteria> searchCriteriaList = IntStream.of(1,5)
                .mapToObj(x-> new SearchCriteria("id", Operators.EQUAL, x, "or"))
                .collect(Collectors.toList());

        searchCriteriaWrapper.setSearchCriterias(searchCriteriaList);
        String returned = myBatisPredicate.withCriteria(searchCriteriaWrapper).build();

        Assertions.assertThat(returned).isNotEmpty();
    }

    @Test
    void shouldReturnNull_whenCallBuildMethodWithEmpyListOfParams(){
        MyBatisPredicateBuilder myBatisPredicate = new MyBatisPredicateBuilder(Foo.class);

        SearchCriteriaWrapper searchCriteriaWrapper = new SearchCriteriaWrapper();

        String returned = myBatisPredicate.withCriteria(searchCriteriaWrapper).build();

        Assertions.assertThat(returned).isNull();
    }


}
