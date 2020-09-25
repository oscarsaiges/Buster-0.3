package se.saiges.buster;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import se.saiges.buster.animals.bunny.Bunny;
import se.saiges.buster.controllers.AddAnimalController;

import java.io.IOException;
import java.util.Optional;

public class AnimalMain {

    public Bunny createAnimal(Boolean forLitter){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/add_animal_view.fxml"));
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add new animal.");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
        }

        AddAnimalController addAnimalController = fxmlLoader.getController();

        if(forLitter){
            addAnimalController.hideForLitter();
        }

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            return addAnimalController.processResult(MainController.getSelectedBreeder());
        }

        return null;
    }

    public Bunny updateAnimal(Bunny bunny){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/add_animal_view.fxml"));
        Dialog<ButtonType> updateAnimalDialog = new Dialog<>();
        updateAnimalDialog.setTitle("Update animal.");
        updateAnimalDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        updateAnimalDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        try{
            updateAnimalDialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
        }

        AddAnimalController addAnimalController = fxmlLoader.getController();
        addAnimalController.setAnimalData(bunny);

        Optional<ButtonType> result = updateAnimalDialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            return addAnimalController.processResult(bunny);
        }

        return null;
    }

}
