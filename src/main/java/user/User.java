package user;


import validator.classes.Validation;
import validator.fields.FieldValidator;
import validator.fields.maxlength.MaxLength;
import validator.fields.minlength.MinLength;
import validator.fields.notblank.NotBlank;
import validator.fields.regex.Regex;

@Validation
public class User {

    @NotBlank
    private String username;

    @MinLength(8)
    @MaxLength(20)
    private String password;

    @Regex(".*")
    private String email;

    private User() {}

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private User user;
        private static final FieldValidator validator = new FieldValidator();

        private Builder () {
            user = new User();
        }

        public Builder name(String name) {
            user.username = name;
            return this;
        }

        public Builder password(String pass) {
            user.password = pass;
            return this;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public User build() {
            return user;
        }

        public User validateAndBuild() throws Exception {
            validator.validate(user);
            return user;
        }

    }

}
