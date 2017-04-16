package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;


/**
 * Created by Admin on 16.04.2017.
 */
public class MealServlet extends HttpServlet {
    public static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(MealsUtil.getMeals(), LocalTime.MIN,LocalTime.MAX,2000);
        req.setAttribute("mealWithExceeds" , mealWithExceeds);
        LOG.debug("forward to meals.jsp");
        req.getRequestDispatcher("meals.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(MealsUtil.getMeals(), LocalTime.MIN,LocalTime.MAX,2000);
        req.setAttribute("mealWithExceeds" , mealWithExceeds);
        LOG.debug("forward to meals.jsp");
        req.getRequestDispatcher("meals.jsp").forward(req,resp);
    }
}
