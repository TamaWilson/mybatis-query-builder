package br.com.tamawilson.mybatisquerybuilder.model.patterns;

public class StringPredicatePatterns {

    public static final String EQUAL = "unaccent(%s) = unaccent('%s')";
    public static final String CONTAINS = "unaccent(%s) ilike unaccent('%%%s%%')";
    public static final String STARTS_WITH = "unaccent(%s) ilike unaccent('%s%%')";
    public static final String ENDS_WITH = "unaccent(%s) ilike unaccent('%%%s')";

    private StringPredicatePatterns() {
    }
}
