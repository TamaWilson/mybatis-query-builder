package br.com.tamawilson.mybatisquerybuilder.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchCriteriaWrapper {

    List<SearchCriteria> searchCriterias;

    public SearchCriteriaWrapper() {
        searchCriterias = new ArrayList<>();
    }

    public void add(SearchCriteria searchCriteria) {
        searchCriterias.add(searchCriteria);
    }
}
