package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {


    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(Integer userId, Meal meal) {
        return checkNotFoundWithId(repository.save(userId, meal), userId);
    }

    @Override
    public void delete(Integer userId, int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(userId, id), id);
    }

    @Override
    public Meal get(Integer userId, int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(userId, id), id);
    }

    @Override
    public List<MealWithExceed> getAll(Integer userId) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}