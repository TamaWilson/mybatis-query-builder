package br.com.tamawilson.mybatisquerybuilder.conveter;

import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteria;
import br.com.tamawilson.mybatisquerybuilder.model.dto.SearchCriteriaWrapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteriaConverter implements Converter<String, SearchCriteriaWrapper> {

    private final String operators = "!::|!:|::|:\\*|>\\*|\\*<|:|<|>";

    @Override
    public SearchCriteriaWrapper convert(@Nullable String search) {
        if (search != null) {
            SearchCriteriaWrapper searchCriteriaWrapper = new SearchCriteriaWrapper();

            Pattern pattern = Pattern.compile("([^,].*?)(" + operators + ")(\\(.*\\)|.*?)(?:(\\|)(and|or))?(?=,|$)");
            Matcher matcher = pattern.matcher(search);
            while (matcher.find()) {
                SearchCriteria sc = new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5));
                searchCriteriaWrapper.add(sc);
            }
            return searchCriteriaWrapper;
        }
        return null;
    }
}
