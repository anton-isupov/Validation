package user;


import validator.classes.Validation;
import validator.fields.FieldValidator;
import validator.fields.maxlength.MaxLength;
import validator.fields.minlength.MinLength;
import validator.fields.notblank.NotBlank;
import validator.fields.regex.Regex;

import java.util.Objects;

public class User {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private User(Builder builder) {
        this.id = Objects.requireNonNull(builder.id);
        this.username = Objects.requireNonNull(builder.username);
        this.password = Objects.requireNonNull(builder.password);
        this.email = Objects.requireNonNull(builder.email);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Validation
    public static class Builder {

        private Integer id;

        @NotBlank
        private String username;

        @MinLength(8)
        @MaxLength(20)
        private String password;

        @Regex(".*")
        private String email;

        private static final FieldValidator validator = new FieldValidator();

        private Builder () {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.username = name;
            return this;
        }

        public Builder password(String pass) {
            this.password = pass;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }

        public User validateAndBuild() throws Exception {
            validator.validate(this);
            return new User(this);
        }

    }

}
