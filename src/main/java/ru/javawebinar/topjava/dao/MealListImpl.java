package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MealListImpl implements  MealsList{


    private static AtomicInteger atomicInteger = new AtomicInteger(10);


    @Override
    public void add(Meal meal) {
        getList().add(new Meal(meal.getId() ==0 ? atomicInteger.getAndIncrement():meal.getId(),meal.getDateTime(),meal.getDescription(),meal.getCalories()));
    }

    @Override
    public void update(Meal meal) {
        delete(meal.getId());
        add(meal);
    }

    @Override
    public void delete(long id) {
        @SuppressWarnings("OptionalGetWithoutIsPresent") Meal m = getList().stream().filter(meal -> meal.getId() == id).findFirst().get();
        getList().remove(m);
    }

    @Override
    public Meal byId(long id) {
        //noinspection OptionalGetWithoutIsPresent
        return getList().stream().filter(meal -> meal.getId() == id).findFirst().get();
    }

    @Override
    public List<Meal> getList() {
        return MealsUtil.getMeals();
    }

    public static long getId() {
        return atomicInteger.getAndIncrement();
    }

}
