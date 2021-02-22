package br.com.tamawilson.mybatisquerybuilder.model.patterns;

public class BasicPredicatePatterns {

    public static final String EQUAL = "? = ?";
    public static final String NOT_EQUALS = "? != ?";
    public static final String GREATER_THAN = "? > ?";
    public static final String LOWER_THAN = "? < ?";
    public static final String IN = "? in ?";
    public static final String NOT_IN = "? not in ?";

    private BasicPredicatePatterns() {
    }
}

