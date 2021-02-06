package br.com.tamawilson.mybatisquerybuilder.utils;


import br.com.tamawilson.mybatisquerybuilder.model.MappedField;
import br.com.tamawilson.mybatisquerybuilder.model.annotation.MyBatisColumn;
import br.com.tamawilson.mybatisquerybuilder.model.exception.MyBatisColumnException;
import br.com.tamawilson.mybatisquerybuilder.model.type.MyBatisNull;

import java.lang.reflect.Field;

public class MyBatisColumnUtil {

    private MyBatisColumnUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Classe utilitária");
    }

    public static MappedField extractColumn(String fieldPath, Class<?> currentClass) {
        try {
            String[] path = fieldPath.split("\\.", 2);

            Field field = currentClass.getDeclaredField(path[0]);
            if (field.isAnnotationPresent(MyBatisColumn.class)) {
                MyBatisColumn property = field.getAnnotation(MyBatisColumn.class);
                if (!property.name().isEmpty() && path.length == 1) {

                    Class<?> type = field.getType();

                    if (!property.rootAs().getTypeName().equals(MyBatisNull.class.getTypeName())) {
                        type = property.rootAs();
                    }

                    return MappedField.builder().field(field)
                            .columnName(property.name()).typeAs(type).build();
                }
                if (!property.nestedClass().getTypeName().equals(MyBatisNull.class.getTypeName())) {
                    return extractColumn(path[1], property.nestedClass());
                }
            }
            throw new MyBatisColumnException(String.format("Não é possível pesquisar por: %s", path[0]));
        } catch (NoSuchFieldException e) {
            throw new MyBatisColumnException(String.format("O campo %s não existe na entidade %s",
                    e.getMessage(), currentClass.getSimpleName()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MyBatisColumnException(String.format("Informe o campo da entidade %s", currentClass.getSimpleName()));
        }
    }
}
