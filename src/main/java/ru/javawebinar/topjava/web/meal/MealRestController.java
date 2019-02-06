package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Controller
public class MealRestController {
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public void save(Meal meal) {
        service.save(SecurityUtil.authUserId(), meal);
    }

    public void delete(int id) {
        service.delete(SecurityUtil.authUserId(), id);
    }

    public Meal get(int id) {
        return service.get(SecurityUtil.authUserId(), id);
    }

    public List<MealWithExceed> getAll() {
        return service.getAll(SecurityUtil.authUserId());
    }
}