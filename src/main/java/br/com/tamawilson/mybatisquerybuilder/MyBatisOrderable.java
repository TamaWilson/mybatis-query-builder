package br.com.tamawilson.mybatisquerybuilder;

import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.utils.MyBatisColumnUtil;
import org.springframework.data.domain.Sort;

public class MyBatisOrderable {

    private final Class<?> clazz;
    private final Sort.Order order;
    private final StringBuilder orderCriteria;

    public MyBatisOrderable(Class<?> clazz, Sort.Order order) {
        this.order = order;
        this.clazz = clazz;
        this.orderCriteria = new StringBuilder();
    }

    public void generateOrderCriteria() {
        MappedField mappedField = MyBatisColumnUtil.extractColumn(order.getProperty(), clazz);
        String orderString = String.format("%s %s", mappedField.getColumnName(), order.getDirection().name());
        orderCriteria.append(orderString);
    }

    public String getOrderCriteria() {
        return orderCriteria.toString();
    }
}
