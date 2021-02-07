package br.com.tamawilson.mybatisquerybuilder.model.dto;

import br.com.tamawilson.mybatisquerybuilder.model.Operators;
import lombok.Data;


@Data
public class SearchCriteria {

    private String key;
    private Operators operation;
    private Object value;
    private String agregator = "and";

    public SearchCriteria(String key, Operators operation, Object value, String agregator) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        if (agregator != null) {
            this.agregator = agregator;
        }
    }
}
