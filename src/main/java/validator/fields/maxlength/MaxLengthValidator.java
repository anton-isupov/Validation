package validator.fields.maxlength;

import validator.Validator;
import validator.exception.NotSupportedValidationType;
import validator.exception.ValidationException;

import java.lang.reflect.Field;

public class MaxLengthValidator implements Validator {

    @Override
    public void isValid(Object entity, Field field) throws ValidationException, NotSupportedValidationType {
        if (field.isAnnotationPresent(MaxLength.class)) {
            MaxLength maxLength = field.getAnnotation(MaxLength.class);

            if (field.getType() != String.class)
                throw new NotSupportedValidationType("Not supported field type " + field.getType());

            field.setAccessible(true);
            int expected = maxLength.value();
            try {
                String o = (String) field.get(entity);
                if (o.length() > expected)
                    throw new ValidationException("Not valid field value length, expected less than: " + expected);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
