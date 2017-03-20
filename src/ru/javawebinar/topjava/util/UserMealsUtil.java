package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> umwe =
                getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        for (UserMealWithExceed anUmwe : umwe) {
            System.out.println(anUmwe);
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        LinkedList<UserMealWithExceed> filterList = new LinkedList<>();
        long[] dates = mealList.stream().mapToLong(userMeal -> userMeal.getDateTime().toLocalDate().toEpochDay()).distinct().toArray();
        boolean exceed;

        for (UserMeal um : mealList) {
            if (TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime)) {
                exceed = mealList.stream().
                        filter(userMeal -> userMeal.getDateTime().toLocalDate().toEpochDay() == um.getDateTime().toLocalDate().toEpochDay()).
                        mapToInt(UserMeal::getCalories).sum() >= caloriesPerDay;
                filterList.add(new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), exceed));
            }
        }
        return filterList;
    }
}
