package br.com.tamawilson.mybatisquerybuilder.model.patterns;

public class StringPredicatePatterns {

    public static final String EQUAL = "unaccent({0}}) = unaccent({1})";
    public static final String CONTAINS = "unaccent({0}) ilike unaccent('%' || {1} || '%')";
    public static final String STARTS_WITH = "unaccent({0}) ilike unaccent({1} || '%')";
    public static final String ENDS_WITH = "unaccent({0}) ilike unaccent('%' || {1})";

    private StringPredicatePatterns() {
    }
}
