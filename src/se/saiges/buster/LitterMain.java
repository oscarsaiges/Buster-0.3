package se.saiges.buster;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import se.saiges.buster.animals.litters.Litter;
import se.saiges.buster.controllers.AddLitterController;

import java.io.IOException;
import java.util.Optional;

public class LitterMain {

    public Litter createLitter(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/add_litter_view.fxml"));
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Create new Litter");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
        }

        AddLitterController addLitterController = fxmlLoader.getController();
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            return addLitterController.createLitter();
        }
        return  null;
    }
}
