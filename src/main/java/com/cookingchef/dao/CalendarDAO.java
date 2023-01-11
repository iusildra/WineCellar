package com.cookingchef.dao;

import com.cookingchef.model.Calendar;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CalendarDAO {
    /**
     * Creates a calendar entry in the database
     *
     * @param calendar The Calendar object that you want to create in the
     *                   database.
     * @return An optional integer.
     */
    void createCalendarEntryInDb(Calendar calendar) throws SQLException;

    /**
     * Returns a list of Calendar objects that the user in the parameter has.
     *
     * @param userID The ID of the specified user.
     * @return A list of calendars for the given user.
     */
    List<Optional<Calendar>> getAllCalendarByUserId(int userID) throws SQLException;

    /**
     * Returns a list of Calendar objects for a given date.
     *
     * @param date The date of the meal.
     * @return A list of calendars for the given user.
     */
    List<Optional<Calendar>> getAllCalendarByDate(Date date) throws SQLException;

    /**
     * Returns a list of Calendar objects that the user in the parameter
     * has planed for the date given.
     *
     * @param userID The ID of the specified user.
     * @param date The date of the meal.
     * @return A list of calendars for the given user.
     */
    List<Optional<Calendar>> getAllCalendarByUserIdAndDate(int userID, Date date) throws SQLException;

    /**
     * Delete all occurrences of the calendar entry given.
     *
     * @param calendar The calendar entry to delete.
     */
    void removeCalendarFromDb(Calendar calendar) throws SQLException;
}
