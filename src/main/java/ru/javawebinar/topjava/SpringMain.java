package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));

            MealRestController controller = appCtx.getBean(MealRestController.class);

            //GetAll and Print
            controller.getAll().forEach(System.out::println);

            //Save new Meal
            controller.save(new Meal(LocalDateTime.now(), "testMeal", 1111));
            System.out.println("\n");
            controller.getAll().forEach(System.out::println);
            System.out.println("\n");

            //Get Meal
            Meal meal = controller.get(1);
            System.out.println("GET meal by id=1: " + meal);
//            System.out.println(controller.get(1000)); NotFoundException

            //Update meal
            meal.setId(2);
            Meal mealUpdate = new Meal(LocalDateTime.now(), "updateMeal", 222222);
            controller.save(mealUpdate);

            System.out.println("\n");
            controller.getAll().forEach(System.out::println);
            System.out.println("\n");

            meal.setId(7);
//            controller.save(meal); NotFoundException


            //Delete Meal
            controller.delete(1);
//            controller.delete(7); NotFoundException

        }
    }
}
