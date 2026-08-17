package com.example.framework.utilities;

import com.github.javafaker.Faker;

/**
 * Utility wrapper around Java Faker to generate realistic test data.
 *
 * The Faker instance is thread‑local to avoid concurrency issues during parallel execution.
 */
public class FakerUtility {

    private static final ThreadLocal<Faker> FAKER = ThreadLocal.withInitial(Faker::new);

    /**
     * Returns the thread‑local {@link Faker} instance.
     *
     * @return Faker for the current thread
     */
    public static Faker faker() {
        return FAKER.get();
    }

    // Example shortcut methods (add more as needed)

    public static String name() {
        return faker().name().fullName();
    }

    public static String email() {
        return faker().internet().emailAddress();
    }

    public static String phone() {
        return faker().phoneNumber().cellPhone();
    }

    public static String address() {
        return faker().address().fullAddress();
    }
}