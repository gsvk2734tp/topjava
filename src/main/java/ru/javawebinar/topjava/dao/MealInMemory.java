package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealInMemory implements MealDao {

    private static AtomicInteger id = new AtomicInteger(100000);
    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    static {
        meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void addMeal(LocalDateTime localDateTime, String description, int calories) {
        meals.put(id.get(), new Meal(id.getAndIncrement(), localDateTime, description, calories));
    }

    @Override
    public void deleteMeal(int id) {
        meals.remove(id);
    }

    @Override
    public void updateMeal(int id, LocalDateTime localDateTime, String description, int calories) {
        meals.put(id, new Meal(id, localDateTime, description, calories));
    }

    @Override
    public Meal getMealById(int id) {
        return meals.getOrDefault(id, null);
    }

    @Override
    public List<Meal> getMeals() {
    return new ArrayList<>(meals.values());
    }
}
