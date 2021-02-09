package br.com.tamawilson.mybatisquerybuilder.utils;

import br.com.tamawilson.mybatisquerybuilder.data.Foo;
import br.com.tamawilson.mybatisquerybuilder.model.exception.MyBatisColumnException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class MyBatisColumnUtilTest {

    @Test
    void theClassShouldInComplianceWithUtilityClassPattern() throws NoSuchMethodException {

        Class<?> clazz = MyBatisColumnUtil.class;

        Assertions.assertThat(MyBatisColumnUtil.class.getDeclaredConstructors().length).isOne();
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
    void shouldReturnAException_whenTheFieldDoesNotExistOnEntity() {

        Assertions.assertThatThrownBy(()-> MyBatisColumnUtil.extractColumn("asd", Foo.class))
                .isInstanceOf(MyBatisColumnException.class).hasMessageContaining("não existe na entidade");
    }

    @Test
    void shouldReturnAException_whenTheFielIsNotMappedOnEntity() {

        Assertions.assertThatThrownBy(()-> MyBatisColumnUtil.extractColumn("createdAt", Foo.class))
                .isInstanceOf(MyBatisColumnException.class).hasMessageContaining("Não é possível utilizar o campo");
    }

    @Test
    void shouldReturnsAMappedField_whenPassingAValidFieldPathForAClass() {

        Assertions.assertThat(MyBatisColumnUtil.extractColumn("bar.id", Foo.class)).isNotNull();
    }


    @Test
    void shouldReturnAException_whenTheNestedFieldOnPathDoesNotExistOnNestedClass() {

        Assertions.assertThatThrownBy(()-> MyBatisColumnUtil.extractColumn("bar.imagination", Foo.class))
                .isInstanceOf(MyBatisColumnException.class).hasMessageContaining("não existe na entidade");
    }

    @Test
    void shouldThrowsAExceptions_whenTheNestedFieldOnPathIsNotMappedOnNestedClass() {

        Assertions.assertThatThrownBy(()-> MyBatisColumnUtil.extractColumn("bar.serial", Foo.class))
                .isInstanceOf(MyBatisColumnException.class).hasMessageContaining("Não é possível utilizar o campo");
    }

    @Test
    void shouldReturnAMappedFiel_whenAcessANestedClassAsRoot() {

        Assertions.assertThat(MyBatisColumnUtil.extractColumn("bar", Foo.class)).isNotNull();
    }

   @Test
    void shouldThrowsAException_whenTheNestedClassIsNotMappedToBeAcessedAsRoot() {

        Assertions.assertThatThrownBy(()-> MyBatisColumnUtil.extractColumn("fon", Foo.class))
                .isInstanceOf(MyBatisColumnException.class).hasMessageContaining("Informe o campo da entidade");
    }
}
