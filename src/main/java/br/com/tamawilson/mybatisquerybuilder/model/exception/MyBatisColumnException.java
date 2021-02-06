package br.com.tamawilson.mybatisquerybuilder.model.exception;

public class MyBatisColumnException extends RuntimeException {

    public MyBatisColumnException(String field, String operador, String type) {
        super(MyBatisColumnException.generateMessage(field, operador, type));
    }

    public MyBatisColumnException(String message) {
        super(message);
    }

    private static String generateMessage(String field, String operador, String type) {
        return String.format("Não é possível utilizar o operardor %s no campo %s (%s)", operador, field, type);
    }
}
