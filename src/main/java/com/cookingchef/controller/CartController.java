package com.cookingchef.controller;

import com.cookingchef.facade.CartFacade;
import com.cookingchef.facade.PartnerFacade;
import com.cookingchef.model.CartEntry;
import com.cookingchef.model.Partner;
import com.cookingchef.view.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CartController implements Initializable {

	@FXML
	private FlowPane flow;

	private List<Partner> partners;

	private ObservableList<CartEntry> cartList = FXCollections.observableArrayList();

	@FXML
	private TableView<CartEntry> table = new TableView<>();

	@FXML
	private TableColumn<CartEntry,String> name = new TableColumn<>("Name");

	@FXML
	TableColumn<CartEntry, Double> qty = new TableColumn<>("Quantity");

	@FXML
	TableColumn<CartEntry, String> unit = new TableColumn<>("Unit");

	@FXML
	TableColumn<CartEntry, CartEntry> delete = new TableColumn<>("Delete");



	private void fetchCart() {
		try {
			CartFacade facade = CartFacade.getCartFacade();
			cartList.setAll(facade.getCartByUser(Main.getUser().getId().get()));
			table.getItems().setAll(cartList);
			table.refresh();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		name.setCellValueFactory(new PropertyValueFactory<CartEntry,String>("name"));
		qty.setCellValueFactory(new PropertyValueFactory<CartEntry,Double>("quantity"));
		unit.setCellValueFactory(new PropertyValueFactory<CartEntry,String>("unit"));
		delete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		delete.setCellFactory(param -> removeButtonFactory(Optional.empty()));

		fetchCart();

		fetchPartners();


		System.out.println(table.getItems());


	}

	private void fetchPartners() {
		PartnerFacade facade = PartnerFacade.getPartnerFacade();
		try {
			partners = facade.getAllPartners();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		for (Partner partner : partners) {
			VBox box = new VBox();
			box.setSpacing(10);
			flow.setMargin(box, new javafx.geometry.Insets(10));

			box.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 5px; -fx-background-radius: 5px;-fx-font-size: 20px; -fx-end-margin: 10px; -fx-start-margin: 10px; -fx-padding: 10px;");
			box.setAlignment(javafx.geometry.Pos.TOP_CENTER);
			Text name = new Text(partner.getName());
			name.setStyle("-fx-font-weight: bold;");
			Text description = new Text(partner.getDescription());
			Hyperlink link = new Hyperlink(partner.getWebsite());

			box.getChildren().addAll(name, description, link);
			flow.getChildren().add(box);
		}
	}


	public void deleteCartEntry(CartEntry cartEntry) throws SQLException {
		CartFacade facade = CartFacade.getCartFacade();
		facade.deleteCart(cartEntry);
		this.cartList.remove(cartEntry);
		this.table.getItems().remove(cartEntry);
		this.table.refresh();
	}

	public TableCell<CartEntry, CartEntry> removeButtonFactory(Optional<Runnable> callback) {
		return new TableCell<CartEntry, CartEntry>() {
			private final Button deleteButton = new Button("Delete");

			@Override
			protected void updateItem(CartEntry cartEntry, boolean empty) {
				super.updateItem(cartEntry, empty);

				if (cartEntry == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(event -> {
					try {
						deleteCartEntry(cartEntry);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
					callback.ifPresent(Runnable::run);
				});
			}
		};
	}



}
