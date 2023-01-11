package com.cookingchef.dao;

import com.cookingchef.model.Unit;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UnitDAO {
    /**
     * Returns a list of Unit objects.
     *
     * @return A list of Unit objects.
     */
    List<Optional<Unit>> getAllUnits() throws SQLException;

    /**
     * Returns a Unit object with the given ID.
     *
     * @param id The ID of the Unit object.
     * @return A Unit object.
     */
    Optional<Unit> getUnitById(int id) throws SQLException;

    /**
     * Returns a Unit object with the given name.
     *
     * @param name The name of the Unit object.
     * @return A Unit object.
     */
    Optional<Unit> getUnitByName(String name) throws SQLException;
}
