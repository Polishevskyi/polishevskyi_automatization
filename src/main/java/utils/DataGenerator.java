package utils;

import com.github.javafaker.Faker;

public class DataGenerator {
    private static final Faker faker = new Faker();

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getPassword() {
        return faker.regexify("[a-zA-Z0-9]{10,16}");
    }
}
