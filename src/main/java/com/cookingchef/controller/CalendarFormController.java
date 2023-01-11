package com.cookingchef.controller;

import com.cookingchef.facade.CalendarFacade;
import com.cookingchef.facade.MealCategoryFacade;
import com.cookingchef.model.Calendar;
import com.cookingchef.model.MealCategory;
import com.cookingchef.view.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class CalendarFormController implements Initializable {
    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button closeButton;

    private Optional<Integer> recipeId = Optional.empty();
    private Optional<Integer> userId = Optional.empty();
    private Optional<Integer> mealCategoryId = Optional.empty();

    private CalendarFacade calendarFacade;
    private MealCategoryFacade mealCategoryFacade;

    public CalendarFormController() {
        this.calendarFacade = CalendarFacade.getCalendarFacade();
        this.mealCategoryFacade = MealCategoryFacade.getMealCategoryFacade();
    }

    @FXML
    protected void onClickValidateButton() {
        CalendarFacade calendarFacade = CalendarFacade.getCalendarFacade();
        try {
            this.mealCategoryId = Optional.of((this.mealCategoryFacade.getMealCategoryByName(this.comboBox.getValue())).get().getId());
        } catch (SQLException e) {
            Notifications.create()
                    .title("Error")
                    .text("Connection to database failed\nPlease try again")
                    .showError();
        }

        this.userId = Main.getUser().getId();
        // get the ID of the recipe from where the user clicks.
        this.recipeId = getRecipeId();

        var calendar = new Calendar(
                this.userId.get(),
                this.recipeId.get(),
                this.mealCategoryId.get(),
                Date.valueOf(this.datePicker.getValue()));

        try {
            calendarFacade.addCalendar(calendar);
        } catch (SQLException e) {
            Notifications.create()
                    .title("Fail to add new calendar entry")
                    .text("Please refer to an admin.")
                    .showError();
        }
    }

    public void reset() {
        this.recipeId = Optional.empty();
        this.userId = Optional.empty();
        this.mealCategoryId = Optional.empty();
        this.datePicker.setValue(LocalDate.now());
    }

    public void fillInputs(Calendar calendar) {
        this.recipeId = Optional.of(calendar.getRecipeId());
        this.userId = Optional.of(calendar.getUserId());
    }

    @FXML
    protected void onClose() {
        var stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setValue(LocalDate.now());
        try {
            comboBox.getItems().addAll(((MealCategory) mealCategoryFacade.getAllMealCategories()).getName());
        } catch (SQLException e) {
            Notifications.create()
                    .title("Fail to load meal categories")
                    .text("Please refer to an admin.")
                    .showError();
        }
    }
}
