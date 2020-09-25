package se.saiges.buster.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import se.saiges.buster.AnimalMain;
import se.saiges.buster.animals.Sex;
import se.saiges.buster.animals.bunny.Bunny;
import se.saiges.buster.animals.litters.Litter;
import se.saiges.buster.modul.DataBaseBunny;

public class AddLitterController {

    @FXML
    private ComboBox<Bunny> motherOfLitter;
    @FXML
    private ComboBox<Bunny> fatherOfLitter;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private TextField born;
    @FXML
    private TextField alive;
    @FXML
    private TextField defect;
    @FXML
    private TextField females;
    @FXML
    private TextField males;
    @FXML
    ListView<Bunny> litterListView;

    private Litter litter = new Litter();

    public void initialize(){
        motherOfLitter.getItems().setAll(DataBaseBunny.getInstance().getParentsList(0, Sex.FEMALE));
        fatherOfLitter.getItems().setAll(DataBaseBunny.getInstance().getParentsList(0, Sex.MALE));
    }

    public Litter createLitter(){

        litter.setMotherId(motherOfLitter.getValue().getId());
        litter.setFatherId(fatherOfLitter.getValue().getId());
        litter.setBorn(Integer.parseInt(born.getText()));
        litter.setAlive(Integer.parseInt(alive.getText()));
        litter.setDefect(Integer.parseInt(defect.getText()));
        litter.setFemales(Integer.parseInt(females.getText()));
        litter.setMales(Integer.parseInt(males.getText()));
        litter.setDateOfBirth(dateOfBirth.getValue());

        return litter;
    }

    @FXML
    private void addAnimal(){
        AnimalMain animalMain = new AnimalMain();
        Bunny bunny = animalMain.createAnimal(true);
        if(bunny != null){
            bunny.setDateOfBirth(dateOfBirth.getValue());
            bunny.setMotherId(motherOfLitter.getValue().getId());
            bunny.setFatherId(fatherOfLitter.getValue().getId());

            litter.addToLitterList(bunny);
            litterListView.getItems().add(bunny);
        }
        litterListView.refresh();
    }

    @FXML
    private void removeAnimal(){
        if(!litterListView.getSelectionModel().isEmpty()){
            Bunny bunny = litterListView.getSelectionModel().getSelectedItem();
            litter.removeFromLitterList(bunny);
            litterListView.getSelectionModel().getSelectedItems().remove(bunny);
            litterListView.refresh();
        }
    }
}
