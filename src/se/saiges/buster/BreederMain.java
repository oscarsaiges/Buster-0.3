package se.saiges.buster;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import se.saiges.buster.breeder.Breeder;
import se.saiges.buster.controllers.AddBreederController;
import se.saiges.buster.controllers.BreederController;
import se.saiges.buster.modul.DataBaseBreeder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BreederMain {

    public static Breeder chooseBreeder(){

        Breeder selectedBreeder;

        while(true) {
            Dialog<ButtonType> chooseBreederDialog = new Dialog<>();
            chooseBreederDialog.setTitle("Välj Uppfödare");
            chooseBreederDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            chooseBreederDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

            // Adding custom buttons.
            ButtonType addBreederButton = new ButtonType("Lägg till...");
            ButtonType deleteBreederButton = new ButtonType("Ta bort");
            ButtonType updateBreederButton = new ButtonType("Uppdatera");
            chooseBreederDialog.getDialogPane().getButtonTypes().add(addBreederButton);
            chooseBreederDialog.getDialogPane().getButtonTypes().add(deleteBreederButton);
            chooseBreederDialog.getDialogPane().getButtonTypes().add(updateBreederButton);

            FXMLLoader chooseBreederLoader = new FXMLLoader();
            chooseBreederLoader.setLocation(BreederMain.class.getResource("views/choose_breeder_view.fxml"));
            try {
                chooseBreederDialog.getDialogPane().setContent(chooseBreederLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }

            BreederController breederController = chooseBreederLoader.getController();
            Optional<ButtonType> resultChooseBreeder = chooseBreederDialog.showAndWait();

            // Chooses the selected breeder and continues with the program.
            if(resultChooseBreeder.isPresent() && (resultChooseBreeder.get() == ButtonType.OK)){
                selectedBreeder = breederController.getBreederListView().getSelectionModel().getSelectedItem();
                return selectedBreeder;

            // Removes the selected breeder from the breeder list.
            }else if(resultChooseBreeder.isPresent() && resultChooseBreeder.get() == deleteBreederButton){
                Breeder breeder = breederController.getBreederListView().getSelectionModel().getSelectedItem();
                if(breeder != null) {
                    DataBaseBreeder.getInstance().removeBreeder(breeder);
                    breederController.updateBreederList();
                }

            // Closes program.
            }else if(resultChooseBreeder.isPresent() && (resultChooseBreeder.get() == ButtonType.CLOSE)){
                Platform.exit();
                System.exit(0);
                break;

            // Updating selected breeder object.
            }else if(resultChooseBreeder.isPresent() && resultChooseBreeder.get() == updateBreederButton){
                FXMLLoader updateBreederLoader = new FXMLLoader();
                updateBreederLoader.setLocation(BreederMain.class.getResource("views/add_breeder_view.fxml"));
                Dialog<ButtonType> updateBreederDialog = new Dialog<>();
                updateBreederDialog.setTitle("Uppdatera uppfödare");
                updateBreederDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                updateBreederDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

                try {
                    // Initialize update breeder controller and populating it with the selected breeder object.
                    updateBreederDialog.getDialogPane().setContent(updateBreederLoader.load());
                } catch (IOException e){
                    e.printStackTrace();
                }

                // Creating update breeder controller.
                AddBreederController updateBreederController = updateBreederLoader.getController();
                // Add existing breeder information to dialog.
                Breeder breederToUpdate = breederController.getBreederListView().getSelectionModel().getSelectedItem();
                updateBreederController.populateDialog(breederToUpdate);

                Optional<ButtonType> resultUpdateBreeder = updateBreederDialog.showAndWait();
                if(resultUpdateBreeder.isPresent() && resultUpdateBreeder.get() == ButtonType.OK ){
                    DataBaseBreeder.getInstance().updateBreeder(updateBreederController.updateBreeder(breederToUpdate));
                    breederController.updateBreederList();
                }

            } else if(resultChooseBreeder.isPresent() && resultChooseBreeder.get() == addBreederButton){
                // Creates and adds a new breeder object.
                FXMLLoader newBreederLoader = new FXMLLoader();
                newBreederLoader.setLocation(BreederMain.class.getResource("views/add_breeder_view.fxml"));
                Dialog<ButtonType> newBreederDialog = new Dialog<>();
                newBreederDialog.setTitle("Skapa Ny Uppfödare");
                newBreederDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                newBreederDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

                try {
                    newBreederDialog.getDialogPane().setContent(newBreederLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Optional<ButtonType> resultNewBreeder = newBreederDialog.showAndWait();
                if(resultNewBreeder.isPresent() && resultNewBreeder.get() == ButtonType.OK){
                    AddBreederController addBreederController = newBreederLoader.getController();
                    // creates new breeder
                    Breeder breeder = addBreederController.processResult();

                    // checks if breeder exists.
                    List<Breeder> breederList = DataBaseBreeder.getInstance().getBreederList();
                    if(breederList != null && !breederList.contains(breeder)){
                        DataBaseBreeder.getInstance().createBreeder(breeder);
                        breederController.updateBreederList();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Uppfödare finns redan.");
                        alert.setContentText("Uppfödarnamnet finns redan!");
                        alert.showAndWait();

                    }
                }
            }
        }
        return null;
    }

}
