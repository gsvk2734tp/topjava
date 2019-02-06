package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {

    public static Integer USER_ID = 1;
    public static Integer ADMIN_ID = 2;

    public static int authUserId() {
        return USER_ID;
    }
    public static int authAdminId() {return ADMIN_ID; }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}