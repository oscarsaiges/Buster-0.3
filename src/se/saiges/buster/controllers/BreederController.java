package se.saiges.buster.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import se.saiges.buster.breeder.Breeder;
import se.saiges.buster.modul.DataBaseBreeder;

public class BreederController {


    @FXML
    private GridPane chooseBreederWindow;
    @FXML
    private ListView<Breeder> breederListView;
    @FXML
    private Label labelBreeder;
    @FXML
    private Label labelBreederName;
    @FXML
    private Label labelBreederEmail;
    @FXML
    private Label labelBreederAddress;
    @FXML
    private Label labelBreederZipCode;
    @FXML
    private Label labelBreederPhoneNumber;


    public void initialize(){
        //gets breeder list from Database
        updateBreederList();

        breederListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        breederListView.getSelectionModel().selectFirst();
        showSelectedBreeder();

        breederListView.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            if(!breederListView.getSelectionModel().isEmpty()){
                showSelectedBreeder();
            }
        });
    }

    public ListView<Breeder> getBreederListView(){
        return this.breederListView;
    }

    public void updateBreederList(){
        breederListView.getItems().clear();
        breederListView.getItems().addAll(DataBaseBreeder.getInstance().getBreederList());
        breederListView.refresh();
    }

    // Private methods
    private void showSelectedBreeder() {
        Breeder selectedBreeder = breederListView.getSelectionModel().getSelectedItem();;
        if(selectedBreeder != null) {
            this.labelBreeder.setText(selectedBreeder.getBreederName());
            this.labelBreederName.setText(selectedBreeder.getOwnerName());
            this.labelBreederEmail.setText(selectedBreeder.getOwnerEmail());
            this.labelBreederAddress.setText(selectedBreeder.getOwnerAddress());
            this.labelBreederZipCode.setText(selectedBreeder.getOwnerZipCode());
            this.labelBreederPhoneNumber.setText(selectedBreeder.getOwnerPhoneNumber());
        }
    }
}
