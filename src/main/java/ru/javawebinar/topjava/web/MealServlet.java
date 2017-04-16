package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Admin on 16.04.2017.
 */
public class MealServlet extends HttpServlet {
    public static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Sending redirect to meals.jsp");
        resp.sendRedirect("meals.jsp");
    }
}
