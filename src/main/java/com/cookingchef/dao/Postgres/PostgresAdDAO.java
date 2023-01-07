package com.cookingchef.dao.Postgres;

import com.cookingchef.dao.AdDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Ad;
import com.cookingchef.model.AdDbFields;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PostgresAdDAO implements AdDAO {

    private static AtomicReference<PostgresAdDAO> instance = new AtomicReference<>();

    private PostgresAdDAO() {
    }

    /**
     * If the instance is null, create a new instance and return it.
     * Otherwise, return the existing instance
     *
     * @return A PostgresAdDAO object.
     */
    public static PostgresAdDAO getPostgresAdDAO() {
        instance.compareAndSet(null, new PostgresAdDAO());
        return instance.get();
    }

    @Override
    public Optional<Integer> createAdInDb(Ad ad) throws SQLException {
        var query = "INSERT INTO advert(description_promotion, price, partner_id,ingredient_id) VALUES(?, ?, ?, ?) RETURNING id";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ad.getDescriptionPromotion());
            stmt.setInt(2, ad.getPrice());
            stmt.setInt(3, ad.getPartnerId());
            stmt.setInt(4, ad.getIngredientId());

            stmt.executeQuery();
            var rs = stmt.getResultSet();
            rs.next();
            var newId = rs.getInt(AdDbFields.ID.value);
            ad.setId(newId);
            return Optional.of(newId);
        }
    }

    @Override
    public void updateAdInDb(Ad ad) throws SQLException {
        var query = "UPDATE advert SET description_promotion = ?, price = ?, partner_id = ?, ingredient_id = ? WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ad.getDescriptionPromotion());
            stmt.setInt(2, ad.getPrice());
            stmt.setInt(3, ad.getPartnerId());
            stmt.setInt(4, ad.getIngredientId());
            stmt.setInt(5, ad.getId().get());
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeAdFromDb(Ad ad) throws SQLException {
        var query = "DELETE FROM advert WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ad.getId().get());
            stmt.executeUpdate();
        }

    }

    @Override
    public Optional<Ad> getAdById(int id) throws SQLException {
        var query = "SELECT * FROM advert WHERE id = ?";
        var conn = ConnectionManager.getConnection();

        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            var rs = stmt.getResultSet();
            if (rs.next()) {
                return Optional.of(
                        new Ad(
                                rs.getInt(AdDbFields.ID.value),
                                rs.getString(AdDbFields.DESCRIPTION_PROMOTION.value),
                                rs.getInt(AdDbFields.PRICE.value),
                                rs.getInt(AdDbFields.PARTNER_ID.value),
                                rs.getInt(AdDbFields.INGREDIENT_ID.value)));
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Ad> getAdsByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<Ad> getAds() throws SQLException {
        return AdDAO.super.getAds();
    }
}