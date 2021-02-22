package br.com.tamawilson.mybatisquerybuilder.converter;

import br.com.tamawilson.mybatisquerybuilder.conveter.SearchCriteriaConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.util.Objects;


class SearchCriteriaConverterTest {


    private final SearchCriteriaConverter searchCriteriaConverter = new SearchCriteriaConverter();


    @Test
    void shouldLoadConverter(){

        ApplicationContextRunner context = new ApplicationContextRunner()
                .withUserConfiguration(SearchCriteriaConverter.class);

        context.run(it -> Assertions.assertThat(it).hasSingleBean(SearchCriteriaConverter.class));
    }


    @Test
    void shouldConvertString_WhenAValidPatternIsPassed(){
        String validPattern1 = "search:*1";
        String validPattern2 = "search::(1,2)";
        String validPattern3 = "search:*hello world";

        Assertions.assertThat(Objects.requireNonNull(searchCriteriaConverter.convert(validPattern1)).getSearchCriterias()).isNotEmpty();
        Assertions.assertThat(Objects.requireNonNull(searchCriteriaConverter.convert(validPattern2)).getSearchCriterias()).isNotEmpty();
        Assertions.assertThat(Objects.requireNonNull(searchCriteriaConverter.convert(validPattern3)).getSearchCriterias()).isNotEmpty();

    }


    @Test
    void shouldReturnAEmptySearchCriteriaWrapper_WhenAInvalidPatternIsPassed(){
        String validPattern1 = "searchxx1";
        String validPattern2 = "search**hello world";

        Assertions.assertThat(Objects.requireNonNull(searchCriteriaConverter.convert(validPattern1)).getSearchCriterias()).isEmpty();
        Assertions.assertThat(Objects.requireNonNull(searchCriteriaConverter.convert(validPattern2)).getSearchCriterias()).isEmpty();
    }

    @Test
    void shouldReturnANull_WhenTheStringPassedIsNull(){
        Assertions.assertThat(searchCriteriaConverter.convert(null)).isNull();
    }
}