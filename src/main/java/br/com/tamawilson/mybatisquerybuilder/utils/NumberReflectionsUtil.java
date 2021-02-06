package br.com.tamawilson.mybatisquerybuilder.utils;

import java.util.HashSet;
import java.util.Set;

public class NumberReflectionsUtil {

    private static final Set<Class<?>> NUMBER_REFLECTED_PRIMITIVES;

    static {
        Set<Class<?>> s = new HashSet<>();
        s.add(byte.class);
        s.add(short.class);
        s.add(int.class);
        s.add(long.class);
        s.add(float.class);
        s.add(double.class);
        NUMBER_REFLECTED_PRIMITIVES = s;
    }

    private NumberReflectionsUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Classe utilitária");
    }

    public static boolean isReflectedAsNumber(Class<?> type) {
        return Number.class.isAssignableFrom(type) || NUMBER_REFLECTED_PRIMITIVES.contains(type);
    }
}

