package br.com.tamawilson.mybatisquerybuilder;


import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.model.exception.MyBatisColumnException;

import java.lang.reflect.Field;

import static br.com.tamawilson.mybatisquerybuilder.utils.MyBatisColumnUtil.extractColumn;
import static br.com.tamawilson.mybatisquerybuilder.utils.NumberReflectionsUtil.isReflectedAsNumber;


public class MyBatisPredicate {

    private final StringBuilder predicate = new StringBuilder();
    private SearchCriteria criteria;
    private Class<?> clazz;
    private Field field;

    public MyBatisPredicate setCriteria(final SearchCriteria criteria) {
        this.criteria = criteria;
        return this;
    }

    public MyBatisPredicate setClass(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getPredicate() {
        return predicate.toString();
    }

    public MyBatisPredicate generatePredicate() {

        MappedField mappedField = extractColumn(criteria.getKey(), clazz);
        field = mappedField.getField();

        if (isReflectedAsNumber(mappedField.getTypeAs())) {
            appendNumericPredicate(mappedField.getColumnName());
        }

        if (mappedField.getTypeAs().isAssignableFrom(String.class)) {
            appendStringPredicate(mappedField.getColumnName());
        }

        return this;
    }

    private void appendNumericPredicate(String currentColumn) {
        switch (criteria.getOperation()) {
            case ":":
                String equals = String.format("%s = %s", currentColumn, criteria.getValue());
                predicate.append(equals);
                break;
            case ">":
                String greaterThan = String.format("%s > %s", currentColumn, criteria.getValue());
                predicate.append(greaterThan);
                break;
            case "<":
                String lowerThan = String.format("%s < %s", currentColumn, criteria.getValue());
                predicate.append(lowerThan);
                break;
            case "!:":
                String different = String.format("%s != %s", currentColumn, criteria.getValue());
                predicate.append(different);
                break;
            case "::":
                String in = String.format("%s in %s", currentColumn, criteria.getValue());
                predicate.append(in);
                break;
            case "!::":
                String notIn = String.format("%s not in %s", currentColumn, criteria.getValue());
                predicate.append(notIn);
                break;
            default:
                throw new MyBatisColumnException(criteria.getKey(), criteria.getOperation(), field.getType().toString());
        }
    }

    private void appendStringPredicate(String currentColumn) {
        switch (criteria.getOperation()) {
            case ":":
                String equals = String.format("unaccent(%s) = unaccent('%s')", currentColumn, criteria.getValue());
                predicate.append(equals);
                break;
            case ":*":
                String contains = String.format("unaccent(%s) ilike unaccent('%%%s%%')", currentColumn, criteria.getValue());
                predicate.append(contains);
                break;
            case ">*":
                String startWith = String.format("unaccent(%s) ilike unaccent('%s%%')", currentColumn, criteria.getValue());
                predicate.append(startWith);
                break;
            case "*<":
                String endsWith = String.format("unaccent(%s) ilike unaccent('%%%s')", currentColumn, criteria.getValue());
                predicate.append(endsWith);
                break;
            default:
                throw new MyBatisColumnException(criteria.getKey(), criteria.getOperation(), field.getType().toString());
        }
    }

    public void addAgregator() {
        predicate.append(" ").append(criteria.getAgregator());
    }


}



