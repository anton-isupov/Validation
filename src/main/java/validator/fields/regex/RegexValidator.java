package validator.fields.regex;

import validator.Validator;
import validator.exception.NotSupportedValidationType;
import validator.exception.ValidationException;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class RegexValidator implements Validator {

    @Override
    public void isValid(Object entity, Field field) throws ValidationException, NotSupportedValidationType {
        if (field.isAnnotationPresent(Regex.class)) {
            if (field.getType() != String.class)
                throw new NotSupportedValidationType("Not supported field type " + field.getType());

            field.setAccessible(true);
            Regex regexAnnotation = field.getAnnotation(Regex.class);
            String regex = regexAnnotation.value();
            try {
                String value = (String) field.get(entity);
                Pattern pattern = Pattern.compile(regex);

                if (!pattern.matcher(value).matches())
                    throw new ValidationException("Field " + field + " in "
                            + entity.getClass() + " not matches with regex " + regex);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
