package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> save(SecurityUtil.authUserId(), m));
        save(SecurityUtil.ADMIN_ID, new Meal(LocalDateTime.of(2019, Month.MAY, 31, 20, 0), "Ужин-Админ", 2010));
        save(SecurityUtil.ADMIN_ID, new Meal(LocalDateTime.of(2019, Month.MAY, 30, 14, 0), "Обед-Админ", 2000));
    }

    @Override
    public Meal save(Integer userId, Meal meal) {

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (!repository.containsKey(userId)) repository.put(userId, new HashMap<>());

            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }

        // treat case: update, but absent in storage
        if (checkNotFound(userId, meal.getId())) {
            return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(Integer userId, int id) {
        if (checkNotFound(userId, id)) return repository.get(userId).remove(id) != null;

        return false;

    }

    @Override
    public Meal get(Integer userId, int id) {
        if (checkNotFound(userId, id)) return repository.get(userId).get(id);

        return null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        if (repository.containsKey(userId)) return repository.get(userId).values().stream()
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());

        return Collections.emptyList();
    }

    public boolean checkNotFound(Integer userId, int id) {
        return repository.containsKey(userId) && repository.get(userId).containsKey(id);
    }
}

