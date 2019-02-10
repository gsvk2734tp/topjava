package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceImplTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL1.getId(), USER.getId());
        Assert.assertEquals(meal, MEAL1);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ADMIN1.getId(), ADMIN.getId());
        assertMatch(service.getAll(ADMIN.getId()), MEAL_ADMIN2);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL1);
        updated.setDateTime(LocalDateTime.now());
        updated.setDescription("NewDesc");
        updated.setCalories(9999);
        service.update(updated, USER.getId());
        Assert.assertEquals(service.get(MEAL1.getId(), USER.getId()), updated);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "NewMeal", 99);
        Meal created = service.create(newMeal, ADMIN.getId());
        newMeal.setId(created.getId());
        assertMatch(service.getAll(ADMIN.getId()), created, MEAL_ADMIN2, MEAL_ADMIN1);

    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> allBetween = service.getBetweenDateTimes(MEAL1.getDateTime(), MEAL3.getDateTime(), USER.getId());
        assertMatch(allBetween, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER.getId());
        assertMatch(all, USER_MEALS.stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList()));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1, ADMIN.getId());
    }

    @Test(expected = DataAccessException.class)
    public void duplicateCreate() {
        service.create(new Meal(MEAL1.getDateTime(), "Duplicate", 2000), USER.getId());
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
       service.delete(2, ADMIN.getId());
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        Meal update = new Meal(MEAL1);
        update.setId(MEAL1.getId());
        update.setCalories(2);
        service.update(update, ADMIN.getId());
    }
}