package br.com.tamawilson.mybatisquerybuilder;

import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;

public class MyBatisOrderableBuilder {

    private final Class<?> clazz;
    private Sort sort;


    public MyBatisOrderableBuilder(Class<?> clazz) {
        this.clazz = clazz;
        sort = Sort.unsorted();
    }

    public MyBatisOrderableBuilder with(Sort sort) {
        this.sort = sort;
        return this;
    }

    public String build() {
        return sort.get().map(order -> {
            MyBatisOrderable orderable = new MyBatisOrderable(clazz, order);
            orderable.generateOrderCriteria();
            return orderable.getOrderCriteria();
        }).collect(Collectors.joining(", "));
    }
}
