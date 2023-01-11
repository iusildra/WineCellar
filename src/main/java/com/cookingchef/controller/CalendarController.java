package com.cookingchef.controller;

import com.cookingchef.facade.CalendarFacade;
import com.cookingchef.model.Calendar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    @FXML
    public ListView<String> listView;

    @FXML
    private GridPane calendarGrid;

    private CalendarFacade calendarFacade;
    private RecipeFacade recipeFacade;

    public CalendarController() {
        this.calendarFacade = CalendarFacade.getCalendarFacade();
        this.recipeFacade = RecipeFacade.getRecipeFacade();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] weekDays = {"Lun","Mar","Mer","Jeu","Ven","Sam","Dim"};
        for(int i=0; i<weekDays.length; i++){
            Label label = new Label(weekDays[i]);
            calendarGrid.add(label, i, 0);
        }
        //add more labels for days of the month
        for(int i=1; i<31; i++){
            Label label = new Label(String.valueOf(i));
            calendarGrid.add(label, i%7, i/7+1);
        }

        // Add event handlers to respond to user input, such as mouse clicks on the labels representing the days of the month.
        for (int i = 7; i < calendarGrid.getChildren().size(); i++) {
            Node node = calendarGrid.getChildren().get(i);
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setOnMouseClicked(e -> {
                    // show note for the selected day in some other text area.
                    ArrayList<Optional<Calendar>> calendarList = new ArrayList<>();
                    try {
                        calendarList = (ArrayList<Optional<Calendar>>) this.calendarFacade.getAllCalendarByDate(
                                Date.valueOf(
                                        LocalDate.of(2023, 1, Integer.parseInt(label.getText()))));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if(calendarList.size() > 0){
                        for (Optional<Calendar> calendar : calendarList) {
                            this.listView.getItems().add(this.recipeFacade.getRecipeById(calendar.get().getRecipeId()));
                        }
                    }
                });
            }
        }
    }
}
