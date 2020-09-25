package se.saiges.buster.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import se.saiges.buster.MainController;
import se.saiges.buster.animals.Animal;
import se.saiges.buster.animals.Sex;
import se.saiges.buster.animals.bunny.Bunny;
import se.saiges.buster.modul.DataBaseBunny;

import java.util.List;
import java.util.stream.Collectors;

public class AddParentController extends MainController {
    @FXML
    ComboBox<Animal> motherComboBox;
    @FXML
    ComboBox<Animal> fatherComboBox;

    public void initialize() { }

    public void setParents(Bunny child){

        // Look up and add females to mother comboBox.
        List<Bunny> motherList = DataBaseBunny.getInstance().getParentsList(child.getId(), Sex.FEMALE);

        if( motherList != null && !motherList.isEmpty()){
         motherList.stream()
                    .filter(animal -> animal.getSex() == Sex.FEMALE)
                    .filter(animal -> animal.getId() != child.getId())
                    .collect(Collectors.toList());

            motherComboBox.getItems().addAll(motherList);
        }

        // Look up and add males to father comboBox.
        List<Bunny> fatherList = DataBaseBunny.getInstance().getParentsList(child.getId(), Sex.MALE);

        if(fatherList != null && !fatherList.isEmpty()){
            fatherList.stream()
                    .filter(animal -> animal.getSex() == Sex.MALE)
                    .filter(animal -> !animal.equals(child))
                    .collect(Collectors.toList());

            fatherComboBox.getItems().addAll(fatherList);
        }

        // Checks if child has parents and selects them if they are present.
        if(child.getMotherId() > 0) {
            Bunny motherBunny = DataBaseBunny.getInstance().getBunnyById(child.getMotherId());
            motherComboBox.getSelectionModel().select(motherBunny);
        }

        if(child.getFatherId() > 0) {
            Bunny fatherBunny = DataBaseBunny.getInstance().getBunnyById(child.getFatherId());
            fatherComboBox.getSelectionModel().select(fatherBunny);
        }
    }

    public void processParents(Animal child){
        if(motherComboBox.getValue() != null) {
            child.setMotherId(motherComboBox.getValue().getId());
        }
        if(fatherComboBox.getValue() != null) {
            child.setFatherId(fatherComboBox.getValue().getId());
        }
    }
}
