package com.cookingchef.factory;

import com.cookingchef.dao.PostgresUserDAO;
import com.cookingchef.dao.UserDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.facade.UserFacade;

import java.sql.Connection;
import java.util.Optional;

public class PostgresFactory implements AbstractFactory {

    private static volatile PostgresFactory instance;

    private PostgresFactory() {}

    public static synchronized PostgresFactory getPostgresFactory() {
        if (instance == null) {
            instance = new PostgresFactory();
        }
        return instance;
    }
    @Override
    public UserDAO getUserDAO() {

        PostgresUserDAO postgresUserDAO = PostgresUserDAO.getPostgresUserDAO();
        return postgresUserDAO;
    }

    @Override
    public Optional<Connection> getPostgresFactory(String dbName, String user, String password, int port) {
        return ConnectionManager.getConnection(dbName, user, password, port);
    }


}

