package com.stellar.burgers.model;

import java.util.Random;

public class DataGenerator {
    private static final Random random = new Random();

    public static String generateEmail() {
        return "test_user_" + random.nextInt(1000000) + "@yandex.ru";
    }

    public static String generatePassword() {
        return "password" + random.nextInt(10000);
    }

    public static String generateName() {
        String[] names = {"Alex", "Maria", "Ivan", "Elena", "Dmitry"};
        return names[random.nextInt(names.length)] + random.nextInt(100);
    }

    public static User generateUser() {
        return new User(generateEmail(), generatePassword(), generateName());
    }

    // поле отсутствует (null)

    public static User generateUserWithoutEmail() {
        return new User(null, generatePassword(), generateName());
    }

    public static User generateUserWithoutPassword() {
        return new User(generateEmail(), null, generateName());
    }

    public static User generateUserWithoutName() {
        return new User(generateEmail(), generatePassword(), null);
    }

    // пустое поле

    public static User generateUserWithEmptyEmail() {
        return new User("", generatePassword(), generateName());
    }

    public static User generateUserWithEmptyPassword() {
        return new User(generateEmail(), "", generateName());
    }

    public static User generateUserWithEmptyName() {
        return new User(generateEmail(), generatePassword(), "");
    }

    // метод для параметризации

    public static User generateUserWithInvalidField(String fieldType, String invalidValue) {
        String email = generateEmail();
        String password = generatePassword();
        String name = generateName();

        switch (fieldType) {
            case "email":
                return new User(invalidValue, password, name);
            case "password":
                return new User(email, invalidValue, name);
            case "name":
                return new User(email, password, invalidValue);
            default:
                return new User(email, password, name);
        }
    }
}