package com.cookingchef.facade;

import com.cookingchef.dao.CalendarDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Calendar;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CalendarFacade {
    private static AtomicReference<CalendarFacade> instance = new AtomicReference<>();
    private final CalendarDAO calendarDAO;

    protected CalendarFacade() {
        var factory = new PostgresFactory();
        this.calendarDAO = factory.getCalendarDAO();
    }

    public static CalendarFacade getCalendarFacade() {
        instance.compareAndSet(null, new CalendarFacade());
        return instance.get();
    }

    public void addCalendar(Calendar calendar) throws SQLException {
        calendarDAO.createCalendarEntryInDb(calendar);
    }

    public void deleteCalendar(Calendar calendar) throws SQLException {
        calendarDAO.removeCalendarFromDb(calendar);
    }

    public List<Optional<Calendar>> getAllCalendarByUserId(int userID) throws SQLException {
        return calendarDAO.getAllCalendarByUserId(userID);
    }

    public List<Optional<Calendar>> getAllCalendarByDate(Date date) throws SQLException {
        return calendarDAO.getAllCalendarByDate(date);
    }

    public List<Optional<Calendar>> getAllCalendarByUserIdAndDate(int userID, Date date) throws SQLException {
        return calendarDAO.getAllCalendarByUserIdAndDate(userID, date);
    }
}
