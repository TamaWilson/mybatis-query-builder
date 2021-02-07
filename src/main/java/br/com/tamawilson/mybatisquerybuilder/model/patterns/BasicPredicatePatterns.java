package br.com.tamawilson.mybatisquerybuilder.model.patterns;

public class BasicPredicatePatterns {

    private BasicPredicatePatterns() {
    }

    public static final String EQUAL = "%s = %s";
    public static final String NOT_EQUALS = "%s != %s";
    public static final String GREATER_THAN = "%s > %s";
    public static final String LOWER_THAN = "%s < %s";
    public static final String IN = "%s in %s";
    public static final String NOT_IN = "%s not in %s";
}
