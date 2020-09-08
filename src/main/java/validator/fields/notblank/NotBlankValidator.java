package validator.fields.notblank;


import validator.Validator;
import validator.exception.NotSupportedValidationType;
import validator.exception.ValidationException;

import java.lang.reflect.Field;

public class NotBlankValidator implements Validator {

    @Override
    public void isValid(Object entity, Field field) throws ValidationException, NotSupportedValidationType {
        if (field.isAnnotationPresent(NotBlank.class)) {
            if (field.getType() != String.class)
                throw new NotSupportedValidationType("Not supported field type " + field.getType());

            field.setAccessible(true);
            try {
                String o = (String) field.get(entity);
                if (o.isBlank())
                    throw new ValidationException("Field " + field.getName() +
                            " in " + entity.getClass() + " can not be blank");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
