package com.cookingchef.facade;

import com.cookingchef.dao.UnitDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Unit;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class UnitFacade {
    private static AtomicReference<UnitFacade> instance = new AtomicReference<>();
    private final UnitDAO unitDAO;

    protected UnitFacade() {
        var factory = new PostgresFactory();
        this.unitDAO = factory.getUnitDAO();
    }

    public List<Optional<Unit>> getAllUnits() throws SQLException {
        return unitDAO.getAllUnits();
    }

    public Optional<Unit> getUnitById(int id) throws SQLException {
        return unitDAO.getUnitById(id);
    }

    public Optional<Unit> getUnitByName(String name) throws SQLException {
        return unitDAO.getUnitByName(name);
    }
}
