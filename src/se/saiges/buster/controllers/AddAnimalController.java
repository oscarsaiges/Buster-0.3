package se.saiges.buster.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.saiges.buster.animals.AnimalType;
import se.saiges.buster.animals.EyeColor;
import se.saiges.buster.animals.Sex;
import se.saiges.buster.animals.bunny.*;
import se.saiges.buster.animals.litters.Litter;
import se.saiges.buster.breeder.Breeder;
import se.saiges.buster.modul.DataBaseLitter;

public class AddAnimalController {

    @FXML
    private TextField animalName;
    @FXML
    private Label textDateOfBirth;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private ComboBox<BunnyColor> animalColor;
    @FXML
    private ComboBox<Sex> animalSex;
    @FXML
    private ComboBox<BunnyBreed> animalBreed;
    @FXML
    private ComboBox<BunnyPattern> animalPattern;
    @FXML
    private ComboBox<BunnyFurType> animalFurType;
    @FXML
    private ComboBox<EyeColor> animalEyeColor;
    @FXML
    private ComboBox<Litter> litter;
    @FXML
    private Label textLitter;
    @FXML
    private CheckBox animalIsOwned;

    public void initialize(){

        animalColor.getItems().setAll(BunnyColor.values());
        animalSex.getItems().setAll(Sex.values());
        animalBreed.getItems().setAll(BunnyBreed.values());
        animalPattern.getItems().setAll(BunnyPattern.values());
        animalFurType.getItems().setAll(BunnyFurType.values());
        animalEyeColor.getItems().setAll(EyeColor.values());
        litter.getItems().setAll(DataBaseLitter.getInstance().getLitters());
    }

    public Bunny processResult(Breeder breeder){

        Bunny bunny = new Bunny();

        bunny.setName(animalName.getText().trim());
        bunny.setDateOfBirth(dateOfBirth.getValue());
        bunny.setBunnyColor(animalColor.getValue());
        bunny.setSex(animalSex.getValue());
        bunny.setBunnyBreed(animalBreed.getValue());
        bunny.setBunnyPattern(animalPattern.getValue());
        bunny.setBunnyFurType(animalFurType.getValue());
        bunny.setEyeColor(animalEyeColor.getValue());
        bunny.setOwned(animalIsOwned.isSelected() ? 1 : 0);
        bunny.setAnimalType(AnimalType.BUNNY);

        if(litter.getSelectionModel().getSelectedItem() != null){
            bunny.setLitterId(litter.getValue().getLitterId());
        }

        bunny.setBreederId(breeder.getId());

        return bunny;
    }

    public Bunny processResult(Bunny bunny){

        bunny.setName(animalName.getText().trim());
        bunny.setDateOfBirth(dateOfBirth.getValue());
        bunny.setBunnyColor(animalColor.getValue());
        bunny.setSex(animalSex.getValue());
        bunny.setBunnyBreed(animalBreed.getValue());
        bunny.setBunnyPattern(animalPattern.getValue());
        bunny.setBunnyFurType(animalFurType.getValue());
        bunny.setEyeColor(animalEyeColor.getValue());
        bunny.setOwned(animalIsOwned.isSelected() ? 1 : 0);

        if(litter.getSelectionModel().getSelectedItem() != null){
            bunny.setLitterId(litter.getValue().getLitterId());
        }

        return bunny;
    }

    public void setAnimalData(Bunny bunny){

            animalName.setText(bunny.getName());
            dateOfBirth.setValue(bunny.getDateOfBirth());
            animalColor.setValue(bunny.getBunnyColor());
            animalSex.setValue(bunny.getSex());
            animalBreed.setValue(bunny.getBunnyBreed());
            animalPattern.setValue(bunny.getBunnyPattern());
            animalFurType.setValue(bunny.getBunnyFurType());
            animalEyeColor.setValue(bunny.getEyeColor());
            litter.setValue(DataBaseLitter.getInstance().getLitterListById(bunny.getLitterId()));

            if(bunny.isOwned() == 1){
                animalIsOwned.setSelected(true);
            }

    }

    public void hideForLitter(){
        dateOfBirth.setVisible(false);
        textDateOfBirth.setVisible(false);
        litter.setVisible(false);
        textLitter.setVisible(false);
    }
}
