module com.cookingchef {
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires java.naming;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires net.synedra.validatorfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires transitive java.sql;
	requires transitive org.postgresql.jdbc;
	requires spring.security.crypto;
    requires java.desktop;

    opens com.cookingchef.view to javafx.fxml;
	opens com.cookingchef.controller to javafx.fxml;

	exports com.cookingchef.controller;
	exports com.cookingchef.dao;
	exports com.cookingchef.dbutils;
	exports com.cookingchef.factory;
	exports com.cookingchef.facade;
	exports com.cookingchef.model;
	exports com.cookingchef.view;
    exports com.cookingchef.dao.Postgres;
}