package validator;


import validator.exception.NotSupportedValidationType;
import validator.exception.ValidationException;

import java.lang.reflect.Field;

public interface Validator {
    void isValid(Object entity, Field field) throws ValidationException, NotSupportedValidationType;
}
