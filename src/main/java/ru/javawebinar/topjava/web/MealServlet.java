package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String LIST_MEALS = "/meals.jsp";
    private static final String UPDATE_OR_ADD = "/mealData.jsp";
    private MealDao mealDao = new MealInMemory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            log.debug("delete meal {}", mealId);
            mealDao.deleteMeal(mealId);
            request.setAttribute("meals", MealsUtil.getWithExceeded(mealDao.getMeals(), 2000));
            forward = LIST_MEALS;
        } else if ("update".equals(action)) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            log.debug("forward to update form {}", mealId);
            Meal meal = mealDao.getMealById(mealId);
            request.setAttribute("meal", meal);
            forward = UPDATE_OR_ADD;
        } else if ("add".equals(action)) {
            log.debug("forward to add form");
            forward = UPDATE_OR_ADD;
        } else  {
            log.debug("create meals with exceeded");
            request.setAttribute("meals", MealsUtil.getWithExceeded(mealDao.getMeals(), 2000));
            log.debug("forward to meals");
            forward = LIST_MEALS;
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mealId = request.getParameter("mealId");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date") + " " + request.getParameter("time"), formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        if (mealId == null || mealId.isEmpty()) {
            mealDao.addMeal(dateTime, description, calories);
        } else {
            int id = Integer.parseInt(mealId);
            mealDao.updateMeal(id, dateTime, description, calories);
        }

        log.debug("create meals with exceeded");
        request.setAttribute("meals", MealsUtil.getWithExceeded(mealDao.getMeals(), 2000));
        log.debug("forward to meals");
        request.getRequestDispatcher(LIST_MEALS).forward(request, response);
    }
}
