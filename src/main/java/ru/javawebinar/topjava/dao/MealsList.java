package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;


public interface MealsList {
    void add(Meal meal);
    void update(Meal meal);
    void delete(long id);
    Meal byId (long id);
    List<Meal> getList();
}
