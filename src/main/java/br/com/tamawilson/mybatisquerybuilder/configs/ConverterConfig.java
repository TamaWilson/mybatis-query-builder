package br.com.tamawilson.mybatisquerybuilder.configs;

import br.com.tamawilson.mybatisquerybuilder.conveter.SearchCriteriaConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SearchCriteriaConverter());
    }

}
