package validator.fields;


import validator.Validator;
import validator.classes.Validation;
import validator.exception.NotSupportedValidationType;
import validator.exception.ValidationException;
import validator.fields.maxlength.MaxLengthValidator;
import validator.fields.minlength.MinLengthValidator;
import validator.fields.notblank.NotBlankValidator;
import validator.fields.regex.RegexValidator;

import java.lang.reflect.Field;
import java.util.List;

public class FieldValidator {

    private final List<Validator> validators = List.of(
            new MinLengthValidator(),
            new NotBlankValidator(),
            new MaxLengthValidator(),
            new RegexValidator()
    );

    public void validate(Object entity) throws ValidationException, NotSupportedValidationType {
        Class<?> clazz = entity.getClass();
        if (clazz.isAnnotationPresent(Validation.class)) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                for (Validator validator : validators) {
                    validator.isValid(entity, field);
                }
            }
        }
    }
}

