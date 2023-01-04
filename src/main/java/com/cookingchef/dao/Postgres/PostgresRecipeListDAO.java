package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.RecipeListDAO;

import java.util.concurrent.atomic.AtomicReference;

public class PostgresRecipeListDAO implements RecipeListDAO {
    private static AtomicReference<PostgresRecipeListDAO> instance = new AtomicReference<>();

    private PostgresRecipeListDAO() {
    }

    /**
     * If the instance is null, create a new instance and return it.
     * Otherwise, return the existing instance.
     *
     * @return A PostgresPartnerDAO object.
     */
    public static PostgresRecipeListDAO getPostgresPartnerDAO() {
        instance.compareAndSet(null, new PostgresRecipeListDAO());
        return instance.get();
    }


}
