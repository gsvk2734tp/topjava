package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    Meal save(Integer userId, Meal meal) throws NotFoundException;

    void delete(Integer userId, int id) throws NotFoundException;

    Meal get(Integer userId, int id) throws NotFoundException;

    List<MealWithExceed> getAll(Integer userId);

    List<MealWithExceed> getFilter(Integer userId,
                                   LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime);

}