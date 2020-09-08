package validator.fields.minlength;

import validator.Validator;
import validator.exception.NotSupportedValidationType;
import validator.exception.ValidationException;

import java.lang.reflect.Field;

public class MinLengthValidator implements Validator {

    @Override
    public void isValid(Object entity, Field field) throws ValidationException, NotSupportedValidationType {
        if (field.isAnnotationPresent(MinLength.class)) {
            MinLength minLength = field.getAnnotation(MinLength.class);

            if (field.getType() != String.class)
                throw new NotSupportedValidationType("Not supported field type " + field.getType());

            field.setAccessible(true);
            int expected = minLength.value();
            try {
                String o = (String) field.get(entity);
                if (o.length() < expected)
                    throw new ValidationException("Not valid field value length, expected more than: " + expected);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
