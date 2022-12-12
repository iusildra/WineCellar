module com.cookingchef {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.naming;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires net.synedra.validatorfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires java.sql;
	requires org.postgresql.jdbc;

	opens com.cookingchef.View to javafx.fxml;
	exports com.cookingchef.View;
	opens com.cookingchef.Controller to javafx.fxml;
	exports com.cookingchef.Controller;
}