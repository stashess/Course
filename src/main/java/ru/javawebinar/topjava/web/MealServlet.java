package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealListImpl;
import ru.javawebinar.topjava.dao.MealsList;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import sun.rmi.runtime.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;


/**
 * Created by Admin on 16.04.2017.
 */
public class MealServlet extends HttpServlet {
    public static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    MealListImpl getAllUsers = new MealListImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<MealWithExceed> mealWithExceeds = MealsUtil.getMealsWithExceeded();
        req.setAttribute("mealWithExceeds" , mealWithExceeds);
        LOG.debug("forward to meals.jsp");
        if ("delete".equalsIgnoreCase(action)) {
            LOG.debug("delete");
            Long id = Long.parseLong(req.getParameter("id"));
            getAllUsers.delete(id);
            resp.sendRedirect("meals");
        } else if("edit".equalsIgnoreCase(action)){
            LOG.debug("edit");
            Long id = Long.parseLong(req.getParameter("id"));
            req.setAttribute("editmeal",getAllUsers.byId(id));
            req.getRequestDispatcher("/meals.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("/meals.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("doPost");
        req.setCharacterEncoding("UTF-8");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Meal meal;
        long id;
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime").replace('T',' '),dateTimeFormatter);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        if (req.getParameter("id")!=""){
             id = Long.parseLong(req.getParameter("id"));
        } else id =0;

        if (id==0){
            meal  = new Meal(MealListImpl.getId(),localDateTime,description,calories);
           getAllUsers.add(meal);
        } else {
            meal  = new Meal(id,localDateTime,description,calories);
            getAllUsers.update(meal);
        }

        req.setAttribute("meals", getAllUsers.getList());
        resp.sendRedirect("meals");
    }

}
