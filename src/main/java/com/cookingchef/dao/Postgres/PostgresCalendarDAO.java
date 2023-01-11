package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.CalendarDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Calendar;
import com.cookingchef.model.CalendarDbFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresCalendarDAO implements CalendarDAO {
    private static AtomicReference<PostgresCalendarDAO> instance = new AtomicReference<>();

    private PostgresCalendarDAO() {
    }

    /**
     * If the instance is null, create a new instance and return it.
     * Otherwise, return the existing instance.
     *
     * @return A PostgresCalendarDAO object.
     */
    public static PostgresCalendarDAO getPostgresCalendarDAO() {
        instance.compareAndSet(null, new PostgresCalendarDAO());
        return instance.get();
    }

    /**
     * Creates a calendar entry in the database
     *
     * @param calendar The Calendar object that you want to create in the
     *                 database.
     * @return An optional integer.
     */
    @Override
    public void createCalendarEntryInDb(Calendar calendar) throws SQLException {
        var queryRL = "INSERT INTO calendar(user_id, recipe_id, meal_category_id, date) VALUES(?, ?, ?, ?)";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(queryRL)) {
            stmt.setInt(1, calendar.getUserId());
            stmt.setInt(2, calendar.getRecipeId());
            stmt.setInt(3, calendar.getMealCategoryId());
            stmt.setDate(4, calendar.getDate());

            stmt.executeUpdate();
        }
    }

    /**
     * Returns a list of Calendar objects that the user in the parameter has.
     * Caution! The list may be empty.
     *
     * @param userID The ID of the specified user.
     * @return A list of calendars for the given user.
     */
    @Override
    public List<Optional<Calendar>> getAllCalendarByUserId(int userID) throws SQLException {
        var query = "SELECT * FROM calendar WHERE user_id = ?";
        var conn = ConnectionManager.getConnection();
        ArrayList<Optional<Calendar>> toReturn = new ArrayList<>();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                Optional<Calendar> calendar = Optional.of(new Calendar(
                        rs.getInt(CalendarDbFields.USER_ID.value),
                        rs.getInt(CalendarDbFields.RECIPE_ID.value),
                        rs.getInt(CalendarDbFields.MEAL_CATEGORY_ID.value),
                        rs.getDate(CalendarDbFields.DATE.value)
                ));
                toReturn.add(calendar);
            }
        }
        return toReturn;
    }

    /**
     * Returns a list of Calendar objects for a given date.
     *
     * @param date The date of the meal.
     * @return A list of calendars for the given user.
     */
    @Override
    public List<Optional<Calendar>> getAllCalendarByDate(Date date) throws SQLException {
        var query = "SELECT * FROM calendar WHERE date = ?";
        var conn = ConnectionManager.getConnection();
        ArrayList<Optional<Calendar>> toReturn = new ArrayList<>();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, date);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                Optional<Calendar> calendar = Optional.of(new Calendar(
                        rs.getInt(CalendarDbFields.USER_ID.value),
                        rs.getInt(CalendarDbFields.RECIPE_ID.value),
                        rs.getInt(CalendarDbFields.MEAL_CATEGORY_ID.value),
                        rs.getDate(CalendarDbFields.DATE.value)
                ));
                toReturn.add(calendar);
            }
        }
        return toReturn;
    }

    /**
     * Returns a list of Calendar objects that the user in the parameter
     * has planed for the date given.
     *
     * @param userID The ID of the specified user.
     * @param date   The date of the meal.
     * @return A list of calendars for the given user.
     */
    @Override
    public List<Optional<Calendar>> getAllCalendarByUserIdAndDate(int userID, Date date) throws SQLException {
        var query = "SELECT * FROM calendar WHERE user_id = ? AND date = ?";
        var conn = ConnectionManager.getConnection();
        ArrayList<Optional<Calendar>> toReturn = new ArrayList<>();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setDate(2, date);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                Optional<Calendar> calendar = Optional.of(new Calendar(
                        rs.getInt(CalendarDbFields.USER_ID.value),
                        rs.getInt(CalendarDbFields.RECIPE_ID.value),
                        rs.getInt(CalendarDbFields.MEAL_CATEGORY_ID.value),
                        rs.getDate(CalendarDbFields.DATE.value)
                ));
                toReturn.add(calendar);
            }
        }
        return toReturn;
    }

    /**
     * Delete all occurrences of the calendar entry given.
     *
     * @param calendar The calendar entry to delete.
     */
    @Override
    public void removeCalendarFromDb(Calendar calendar) throws SQLException {
        var query = "DELETE FROM calendar WHERE user_id = ? AND recipe_id = ? AND meal_category_id = ? AND date = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, calendar.getUserId());
            stmt.setInt(2, calendar.getRecipeId());
            stmt.setInt(3, calendar.getMealCategoryId());
            stmt.setDate(4, calendar.getDate());
            stmt.executeUpdate();
        }
    }
}
