package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.UnitDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Unit;
import com.cookingchef.model.UnitDbFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresUnitDAO implements UnitDAO {
    private static AtomicReference<PostgresUnitDAO> instance = new AtomicReference<>();

    private PostgresUnitDAO() {
    }

    /**
     * If the instance is null, create a new instance and return it.
     * Otherwise, return the existing instance.
     *
     * @return A PostgresCalendarDAO object.
     */
    public static PostgresUnitDAO getPostgresUnitDAO() {
        instance.compareAndSet(null, new PostgresUnitDAO());
        return instance.get();
    }

    /**
     * Returns a list of Unit objects.
     *
     * @return A list of Unit objects.
     */
    @Override
    public List<Optional<Unit>> getAllUnits() throws SQLException {
        var query = "SELECT * FROM unit";
        var conn = ConnectionManager.getConnection();
        ArrayList<Optional<Unit>> toReturn = new ArrayList<>();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                Optional<Unit> unit = Optional.of(new Unit(
                        rs.getInt(UnitDbFields.ID.value),
                        rs.getString(UnitDbFields.NAME.value)
                ));
                toReturn.add(unit);
            }
        }
        return toReturn;
    }

    /**
     * Returns a Unit object with the given ID.
     *
     * @param id The ID of the Unit object.
     * @return A Unit object.
     */
    @Override
    public Optional<Unit> getUnitById(int id) throws SQLException {
        var query = "SELECT * FROM unit WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(new Unit(
                        rs.getInt(UnitDbFields.ID.value),
                        rs.getString(UnitDbFields.NAME.value)
                ));
            }
        }
        return Optional.empty();
    }

    /**
     * Returns a Unit object with the given name.
     *
     * @param name The name of the Unit object.
     * @return A Unit object.
     */
    @Override
    public Optional<Unit> getUnitByName(String name) throws SQLException {
        var query = "SELECT * FROM unit WHERE name = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(new Unit(
                        rs.getInt(UnitDbFields.ID.value),
                        rs.getString(UnitDbFields.NAME.value)
                ));
            }
        }
        return Optional.empty();
    }
}
