package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDao {

    void addMeal(LocalDateTime localDateTime, String description, int calories);

    void deleteMeal(int id);

    void updateMeal(int id, LocalDateTime localDateTime, String description, int calories);

    Meal getMealById(int id);

    List<Meal> getMeals();
}
