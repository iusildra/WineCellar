package com.cookingchef.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.model.Partner;
import com.cookingchef.model.PartnerDbFields;

public class PostgresPartnerDAO implements PartnerDAO {

  private static AtomicReference<PostgresPartnerDAO> instance = new AtomicReference<>();

  private PostgresPartnerDAO() {
  }

  /**
   * If the instance is null, create a new instance and return it.
   * Otherwise, return the existing instance
   * 
   * @return A PostgresPartnerDAO object.
   */
  public static PostgresPartnerDAO getPostgresPartnerDAO() {
    instance.compareAndSet(null, new PostgresPartnerDAO());
    return instance.get();
  }

  @Override
  public Optional<Integer> registerPartnerInDb(Partner partner) throws SQLException {
    var query = "INSERT INTO partner(name, description, website) VALUES(?, ?, ?) RETURNING id";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setString(1, partner.getName());
      stmt.setString(2, partner.getDescription());
      stmt.setString(3, partner.getWebsite());

      stmt.executeQuery();
      var rs = stmt.getResultSet();
      rs.next();
      return Optional.of(rs.getInt(PartnerDbFields.ID.value));
    }
  }

  @Override
  public void updatePartnerInDb(Partner partner) throws SQLException {
    var query = "UPDATE partner SET name = ?, description = ?, website = ? WHERE id = ?";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setString(1, partner.getName());
      stmt.setString(2, partner.getDescription());
      stmt.setString(3, partner.getWebsite());
      stmt.setInt(4, partner.getId().get());
      stmt.executeUpdate();
    }
  }

  @Override
  public void removePartnerFromDb(Partner partner) throws SQLException {
    var query = "DELETE FROM partner WHERE id = ?";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, partner.getId().get());
      stmt.executeUpdate();
    }

  }

  @Override
  public Optional<Partner> getPartnerById(int id) throws SQLException {
    var query = "SELECT * FROM partner WHERE id = ?";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, id);
      stmt.executeQuery();
      var rs = stmt.getResultSet();
      if (rs.next()) {
        return Optional.of(
            new Partner(
                rs.getInt(PartnerDbFields.ID.value),
                rs.getString(PartnerDbFields.NAME.value),
                rs.getString(PartnerDbFields.DESCRIPTION.value),
                rs.getString(PartnerDbFields.WEBSITE.value)));
      }
    }

    return Optional.empty();
  }

  @Override
  public List<Partner> getPartnersByName(String name) throws SQLException {
    var query = "SELECT * FROM partner WHERE name LIKE ?";
    var conn = ConnectionManager.getConnection();

    try (var stmt = conn.prepareStatement(query)) {
      stmt.setString(1, "%"+ name + "%");
      stmt.executeQuery();
      var rs = stmt.getResultSet();
      var partners = new ArrayList<Partner>();
      while (rs.next()) {
        partners.add(
            new Partner(
                rs.getInt(PartnerDbFields.ID.value),
                rs.getString(PartnerDbFields.NAME.value),
                rs.getString(PartnerDbFields.DESCRIPTION.value),
                rs.getString(PartnerDbFields.WEBSITE.value)));
      }
      return partners;
    }
  }

}
