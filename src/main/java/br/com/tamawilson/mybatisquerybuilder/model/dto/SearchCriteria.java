package br.com.tamawilson.mybatisquerybuilder.model.dto;

import lombok.Data;


@Data
public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;
    private String agregator = "and";

    public SearchCriteria(String key, String operation, Object value, String agregator) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        if (agregator != null) {
            this.agregator = agregator;
        }
    }
}
