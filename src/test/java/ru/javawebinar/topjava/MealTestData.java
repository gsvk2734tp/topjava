package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal MEAL1 = new Meal(START_SEQ + 2, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(START_SEQ + 3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 500);
    public static final Meal MEAL3 = new Meal(START_SEQ + 4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(START_SEQ + 5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL5 = new Meal(START_SEQ + 6, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL6 = new Meal(START_SEQ + 7, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    public static final Meal MEAL_ADMIN1 = new Meal(START_SEQ + 8, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal MEAL_ADMIN2 = new Meal(START_SEQ + 9, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final List<Meal> USER_MEALS = Arrays.asList(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6);
    public static final List<Meal> ADMIN_MEALS = Arrays.asList(MEAL_ADMIN1, MEAL_ADMIN2);

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
