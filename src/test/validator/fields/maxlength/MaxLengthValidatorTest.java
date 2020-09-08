package validator.fields.maxlength;

import org.junit.jupiter.api.Test;
import validator.exception.ValidationException;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaxLengthValidatorTest {

    MaxLengthValidator validator = new MaxLengthValidator();

    @Test
    void isValid() throws NoSuchFieldException {
        Mock mock = new Mock();
        Field errorField = mock.getClass().getDeclaredField("errorField");
        Field testField = mock.getClass().getDeclaredField("testField");

        assertThrows(ValidationException.class, () -> validator.isValid(mock, errorField));
        assertDoesNotThrow(() -> validator.isValid(mock, testField));

    }

    private static class Mock {
        @MaxLength(5)
        private final String errorField = "error-error";

        @MaxLength(5)
        private final String testField = "pass";
    }
}

