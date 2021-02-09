package br.com.tamawilson.mybatisquerybuilder.utils;

import br.com.tamawilson.mybatisquerybuilder.data.Foo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.List;

class NumberReflectionsUtilTest {

    @Test
    void theClassShouldInComplianceWithUtilityClassPattern() throws NoSuchMethodException {

        Class<?> clazz = NumberReflectionsUtil.class;

        Assertions.assertThat(NumberReflectionsUtil.class.getDeclaredConstructors().length).isOne();
        final Constructor<?> constructor = clazz.getDeclaredConstructor();

        Assertions.assertThat(constructor.isAccessible()).isFalse();
        Assertions.assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();


        Assertions.assertThatThrownBy(()->{
            constructor.setAccessible(true);
            constructor.newInstance();
        }).hasCauseInstanceOf(IllegalStateException.class);

        for (final Method method : clazz.getDeclaredMethods()) {
            Assertions.assertThat(Modifier.isStatic(method.getModifiers())).isTrue();
        }
    }

    @Test
    void shouldReturnTrue_whenAClassNumberIsEvaluated(){
        Assertions.assertThat(NumberReflectionsUtil.isReflectedAsNumber(Integer.class)).isTrue();
        Assertions.assertThat(NumberReflectionsUtil.isReflectedAsNumber(Float.class)).isTrue();
        Assertions.assertThat(NumberReflectionsUtil.isReflectedAsNumber(Double.class)).isTrue();
        Assertions.assertThat(NumberReflectionsUtil.isReflectedAsNumber(Long.class)).isTrue();
        Assertions.assertThat(NumberReflectionsUtil.isReflectedAsNumber(BigDecimal.class)).isTrue();
        Assertions.assertThat(NumberReflectionsUtil.isReflectedAsNumber(Short.class)).isTrue();
    }

    @Test
    void shouldReturnFalse_whenAClassNotisAssignableFromAsNumberIsEvaluet(){
        Assertions.assertThat(NumberReflectionsUtil.isReflectedAsNumber(String.class)).isFalse();
        Assertions.assertThat(NumberReflectionsUtil.isReflectedAsNumber(List.class)).isFalse();
        Assertions.assertThat(NumberReflectionsUtil.isReflectedAsNumber(Foo.class)).isFalse();
    }
}
