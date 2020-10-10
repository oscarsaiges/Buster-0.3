package se.saiges.buster;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import se.saiges.buster.animals.Sex;
import se.saiges.buster.animals.bunny.Bunny;
import se.saiges.buster.animals.litters.Litter;
import se.saiges.buster.breeder.Breeder;
import se.saiges.buster.controllers.AddParentController;
import se.saiges.buster.modul.DataBaseBreeder;
import se.saiges.buster.modul.DataBaseBunny;
import se.saiges.buster.modul.DataBaseLitter;
import se.saiges.buster.modul.DataBaseSetup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainController {

    @FXML
    private Label animalNameLabel;
    @FXML
    private Label animalBreederLabel;
    @FXML
    private Label animalDateLabel;
    @FXML
    private Label animalSexLabel;
    @FXML
    private Label animalBreedLabel;
    @FXML
    private Label animalColorLabel;
    @FXML
    private Label animalPatternLabel;
    @FXML
    private Label animalEyeColorLabel;

    @FXML
    private Label motherNameLabel;
    @FXML
    private Label motherBreederLabel;
    @FXML
    private Label motherDateLabel;
    @FXML
    private Label motherBreedLabel;
    @FXML
    private Label motherColorLabel;
    @FXML
    private Label motherPatternLabel;
    @FXML
    private Label motherEyeColorLabel;


    @FXML
    private Label fatherNameLabel;
    @FXML
    private Label fatherBreederLabel;
    @FXML
    private Label fatherDateLabel;
    @FXML
    private Label fatherBreedLabel;
    @FXML
    private Label fatherColorLabel;
    @FXML
    private Label fatherPatternLabel;
    @FXML
    private Label fatherEyeColorLabel;

    @FXML
    private Label mGrandMotherNameLabel;
    @FXML
    private Label mGrandMotherBreederLabel;
    @FXML
    private Label mGrandMotherDateLabel;
    @FXML
    private Label mGrandMotherBreedLabel;
    @FXML
    private Label mGrandMotherColorLabel;
    @FXML
    private Label mGrandMotherPatternLabel;
    @FXML
    private Label mGrandMotherEyeColorLabel;

    @FXML
    private Label mGrandFatherNameLabel;
    @FXML
    private Label mGrandFatherBreederLabel;
    @FXML
    private Label mGrandFatherDateLabel;
    @FXML
    private Label mGrandFatherBreedLabel;
    @FXML
    private Label mGrandFatherColorLabel;
    @FXML
    private Label mGrandFatherPatternLabel;
    @FXML
    private Label mGrandFatherEyeColorLabel;

    @FXML
    private Label fGrandMotherNameLabel;
    @FXML
    private Label fGrandMotherBreederLabel;
    @FXML
    private Label fGrandMotherDateLabel;
    @FXML
    private Label fGrandMotherBreedLabel;
    @FXML
    private Label fGrandMotherColorLabel;
    @FXML
    private Label fGrandMotherPatternLabel;
    @FXML
    private Label fGrandMotherEyeColorLabel;

    @FXML
    private Label fGrandFatherNameLabel;
    @FXML
    private Label fGrandFatherBreederLabel;
    @FXML
    private Label fGrandFatherDateLabel;
    @FXML
    private Label fGrandFatherBreedLabel;
    @FXML
    private Label fGrandFatherColorLabel;
    @FXML
    private Label fGrandFatherPatternLabel;
    @FXML
    private Label fGrandFatherEyeColorLabel;

    @FXML
    private CheckMenuItem checkIsOwned;
    @FXML
    private CheckMenuItem showOwned;
    @FXML
    private CheckMenuItem showAll;
    @FXML
    private CheckMenuItem showNotOwned;
//    @FXML
//    private ListView<Animal> animalListView;
    @FXML
    private TreeView<Displayable> animalTreeView;

    private static Breeder selectedBreeder;
    private  AnimalMain animalMain;
    private LitterMain litterMain;

    // The Initializer
    public void initialize() {
        // Open connection to database
        DataBaseSetup.getInstance().open();

        animalMain = new AnimalMain();
        litterMain = new LitterMain();

        // Chose breeder and set it as selected breeder.
        selectedBreeder = BreederMain.chooseBreeder();
        if(selectedBreeder == null) exitProgram();

        // Gets the animal list for the selected breeder and populate local animal list.
        showAllAnimals();
        //Sets Check Menu Item "showAll" to true.
        showAll.setSelected(true);

        animalTreeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        animalTreeView.getSelectionModel().selectFirst();

        // Event listener
        // Show details of the selected animal in main animal viewer.
        animalTreeView.getSelectionModel().selectedItemProperty().addListener((observableValue, bunnyTreeItem, t1) -> {
            if(!animalTreeView.getSelectionModel().isEmpty() && t1.getValue().getClass().equals(Bunny.class)){
                Bunny bunny = (Bunny) t1.getValue();
                if(bunny.getBreederId() != 0) {
                    showSelectedAnimal();
                }
            }
//            if(t1.getClass().equals(Litter.class)){
//                System.out.println("Litter");
//            }
        });

    }

    public static Breeder getSelectedBreeder(){
        return selectedBreeder;
    }

    // Action Handlers

    // Bunny
    public void createAnimal(){
        Bunny bunny = animalMain.createAnimal(false);

        if(bunny != null){
            DataBaseBunny.getInstance().createBunny(bunny, selectedBreeder);
        }

        updateAnimalList();
    }

    public void updateSelectedAnimal(){
        Bunny bunny = animalMain.updateAnimal((Bunny) animalTreeView.getSelectionModel().getSelectedItem().getValue());

        if(bunny != null){
            DataBaseBunny.getInstance().updateBunny(bunny, selectedBreeder);
        }

        updateAnimalList();
    }

    public void removeSelectedAnimal(){
        Bunny bunny = (Bunny) animalTreeView.getSelectionModel().getSelectedItem().getValue();

        if(bunny != null) {
            DataBaseBunny.getInstance().removeBunnyById(bunny.getId());
        }

        updateAnimalList();
    }

    public void addParents(){
        Bunny child = (Bunny) animalTreeView.getSelectionModel().getSelectedItem().getValue();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/add_parent_view.fxml"));
        Dialog<ButtonType> dialog = dialogFactory("Add parents", fxmlLoader);

        AddParentController addParentController = fxmlLoader.getController();
        addParentController.setParents(child);
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            addParentController.processParents(child);
            DataBaseBunny.getInstance().updateBunny(child, selectedBreeder);
            updateAnimalList(DataBaseBunny.getInstance().getBunnyListByBreederId(selectedBreeder.getId()));
        }
    }

    public void checkIsOwned(){
        Bunny bunny = (Bunny) animalTreeView.getSelectionModel().getSelectedItem().getValue();
        bunny.setOwned(checkIsOwned.isSelected() ? 1 : 0);
        DataBaseBunny.getInstance().updateBunnyIsOwned(bunny.isOwned(), bunny.getId());
    }

    //Litter
    public void createLitter(){
        Litter litter = litterMain.createLitter();

        if(litter != null) {
            // A list of all bunny's created in the new litter object.
            List<Bunny> litterList = new ArrayList<>(litter.getLitterList());

            // Writes the new litter to the database
            DataBaseLitter.getInstance().createLitter(litter);

            // Reads the new litter from the database, so that the new litter id can be written to the bunny's in the litter when they are created.
            Litter litterFinal = DataBaseLitter.getInstance().getLastEnteredLitter();

            // Litter id are added to the bunny's and then they are written to the database.
            litterList.forEach(bunny -> {
                bunny.setLitterId(litterFinal.getLitterId());
                DataBaseBunny.getInstance().createBunny(bunny, selectedBreeder);
            });
        }

        updateAnimalList();
    }

    // Menu
    public void exitProgram(){
        DataBaseSetup.getInstance().close();
        Platform.exit();
        System.exit(0);
    }

    public void showOwnedAnimals (){
        showAll.setSelected(false);
        showNotOwned.setSelected(false);

        List<Bunny> showList = DataBaseBunny.getInstance().getBunnyListByBreederId(selectedBreeder.getId())
                .stream()
                .filter(bunny -> bunny.isOwned() == 1 )
                .collect(Collectors.toList());

        updateAnimalList(showList);
    }

    public void showAllAnimals(){
        showOwned.setSelected(false);
        showNotOwned.setSelected(false);
        updateAnimalList();
    }

    public void showNotOwnedAnimals(){
        showAll.setSelected(false);
        showOwned.setSelected(false);

        List<Bunny> showList = DataBaseBunny.getInstance().getBunnyListByBreederId(selectedBreeder.getId())
                .stream()
                .filter(bunny -> bunny.isOwned() == 0 )
                .collect(Collectors.toList());

        updateAnimalList(showList);
     }

     // Private methods

    private void updateAnimalList(){
        updateAnimalList(DataBaseBunny.getInstance().getBunnyListByBreederId(selectedBreeder.getId()));
    }

    private void updateAnimalList(List<Bunny> bunnyList) {
        Bunny root = new Bunny();
        root.setName("Root");
        Bunny female = new Bunny();
        female.setName("Females");
        Bunny male = new Bunny();
        male.setName("Males");

        TreeItem<Displayable> rootItem = new TreeItem<>(root);
        TreeItem<Displayable> females = new TreeItem<>(female);
        TreeItem<Displayable> males = new TreeItem<>(male);


        List<Litter> litterList = new ArrayList<>(DataBaseLitter.getInstance().getLitters());

        bunnyList.forEach(bunny -> {
            if(bunny.getSex() == Sex.FEMALE){
                TreeItem<Displayable> selectedBunny = new TreeItem<>(bunny);

                litterList.forEach(litter -> {
                    if(litter.getMotherId() == bunny.getId()){
                        selectedBunny.getChildren().add(new TreeItem<>(litter));
                    }
                });

                females.getChildren().add(selectedBunny);

            }
            if(bunny.getSex() == Sex.MALE){
                TreeItem<Displayable> selectedBunny = new TreeItem<>(bunny);

                litterList.forEach(litter -> {
                    if(litter.getFatherId() == bunny.getId()){
                        System.out.println(litter.getLitterId());
                        selectedBunny.getChildren().add(new TreeItem<>(litter));
                        System.out.println(selectedBunny.getChildren());
                    }
                });
                males.getChildren().add((selectedBunny));
            }
        });

        rootItem.getChildren().add(females);
        rootItem.getChildren().add(males);
        animalTreeView.setRoot(rootItem);
        animalTreeView.setShowRoot(false);
    }

    private Dialog<ButtonType> dialogFactory(String title, FXMLLoader fxmlLoader){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
        }
        return dialog;
    }

    private void showSelectedAnimal(){
        // change to a Switch later on?
        if(!animalTreeView.getSelectionModel().isEmpty() && animalTreeView.getSelectionModel().getSelectedItem().getValue().getClass().equals(Bunny.class)){
            Bunny bunny = (Bunny) animalTreeView.getSelectionModel().getSelectedItem().getValue();

            // Sets the check menu item, with in the menu, to true or false
            checkIsOwned.setSelected(bunny.isOwned() == 1);

            animalNameLabel.setText(bunny.getName());
            animalBreederLabel.setText(DataBaseBreeder.getInstance().getBreeder(bunny.getBreederId()).getOwnerName());
            animalDateLabel.setText(bunny.getDateOfBirth().toString());
            animalSexLabel.setText(bunny.getSex().toString());
            animalBreedLabel.setText(bunny.getAnimalType().toString());
            animalColorLabel.setText(bunny.getBunnyColor().toString());
            animalPatternLabel.setText(bunny.getBunnyPattern().toString());
            animalEyeColorLabel.setText(bunny.getEyeColor().toString());

            if(bunny.getMotherId() > 0 ) {
                Bunny mother = DataBaseBunny.getInstance().getBunnyById(bunny.getMotherId());
                motherNameLabel.setText(mother.getName());
                motherBreederLabel.setText(DataBaseBreeder.getInstance().getBreeder(mother.getBreederId()).getOwnerName());
                motherDateLabel.setText(mother.getDateOfBirth().toString());
                motherBreedLabel.setText(mother.getAnimalType().toString());
                motherColorLabel.setText(mother.getBunnyColor().toString());
                motherPatternLabel.setText(mother.getBunnyPattern().toString());
                motherEyeColorLabel.setText(mother.getEyeColor().toString());

                if(mother.getMotherId() > 0 ) {
                    Bunny mm = DataBaseBunny.getInstance().getBunnyById(mother.getMotherId());
                    mGrandMotherNameLabel.setText(mm.getName());
                    mGrandMotherBreederLabel.setText(DataBaseBreeder.getInstance().getBreeder(mm.getBreederId()).getOwnerName());
                    mGrandMotherDateLabel.setText(mm.getDateOfBirth().toString());
                    mGrandMotherBreedLabel.setText(mm.getAnimalType().toString());
                    mGrandMotherColorLabel.setText(mm.getBunnyColor().toString());
                    mGrandMotherPatternLabel.setText(mm.getBunnyPattern().toString());
                    mGrandMotherEyeColorLabel.setText(mm.getEyeColor().toString());
                }else{
                    mGrandMotherNameLabel.setText("");
                    mGrandMotherBreederLabel.setText("");
                    mGrandMotherDateLabel.setText("");
                    mGrandMotherBreedLabel.setText("");
                    mGrandMotherColorLabel.setText("");
                    mGrandMotherPatternLabel.setText("");
                    mGrandMotherEyeColorLabel.setText("");
                }

                if(mother.getFatherId() > 0 ) {
                    Bunny mf = DataBaseBunny.getInstance().getBunnyById(mother.getFatherId());
                    mGrandFatherNameLabel.setText(mf.getName());
                    mGrandFatherBreederLabel.setText(DataBaseBreeder.getInstance().getBreeder(mf.getBreederId()).getOwnerName());
                    mGrandFatherDateLabel.setText(mf.getDateOfBirth().toString());
                    mGrandFatherBreedLabel.setText(mf.getAnimalType().toString());
                    mGrandFatherColorLabel.setText(mf.getBunnyColor().toString());
                    mGrandFatherPatternLabel.setText(mf.getBunnyPattern().toString());
                    mGrandFatherEyeColorLabel.setText(mf.getEyeColor().toString());
                }else{
                    mGrandFatherNameLabel.setText("");
                    mGrandFatherBreederLabel.setText("");
                    mGrandFatherDateLabel.setText("");
                    mGrandFatherBreedLabel.setText("");
                    mGrandFatherColorLabel.setText("");
                    mGrandFatherPatternLabel.setText("");
                    mGrandFatherEyeColorLabel.setText("");
                }
            }else{
                motherNameLabel.setText("");
                motherBreederLabel.setText("");
                motherDateLabel.setText("");
                motherBreedLabel.setText("");
                motherColorLabel.setText("");
                motherPatternLabel.setText("");
                motherEyeColorLabel.setText("");

                mGrandMotherNameLabel.setText("");
                mGrandMotherBreederLabel.setText("");
                mGrandMotherDateLabel.setText("");
                mGrandMotherBreedLabel.setText("");
                mGrandMotherColorLabel.setText("");
                mGrandMotherPatternLabel.setText("");
                mGrandMotherEyeColorLabel.setText("");

                mGrandFatherNameLabel.setText("");
                mGrandFatherBreederLabel.setText("");
                mGrandFatherDateLabel.setText("");
                mGrandFatherBreedLabel.setText("");
                mGrandFatherColorLabel.setText("");
                mGrandFatherPatternLabel.setText("");
                mGrandFatherEyeColorLabel.setText("");
            }

            if(bunny.getFatherId() > 0){
                Bunny father = DataBaseBunny.getInstance().getBunnyById(bunny.getFatherId());
                fatherNameLabel.setText(father.getName());
                fatherBreederLabel.setText(DataBaseBreeder.getInstance().getBreeder(father.getBreederId()).getOwnerName());
                fatherDateLabel.setText(father.getDateOfBirth().toString());
                fatherBreedLabel.setText(father.getAnimalType().toString());
                fatherColorLabel.setText(father.getBunnyColor().toString());
                fatherPatternLabel.setText(father.getBunnyPattern().toString());
                fatherEyeColorLabel.setText(father.getEyeColor().toString());

                if(father.getMotherId() > 0) {
                    Bunny fm = DataBaseBunny.getInstance().getBunnyById(father.getMotherId());
                    fGrandMotherNameLabel.setText(fm.getName());
                    fGrandMotherBreederLabel.setText(DataBaseBreeder.getInstance().getBreeder(fm.getBreederId()).getOwnerName());
                    fGrandMotherDateLabel.setText(fm.getDateOfBirth().toString());
                    fGrandMotherBreedLabel.setText(fm.getAnimalType().toString());
                    fGrandMotherColorLabel.setText(fm.getBunnyColor().toString());
                    fGrandMotherPatternLabel.setText(fm.getBunnyPattern().toString());
                    fGrandMotherEyeColorLabel.setText(fm.getEyeColor().toString());
                }else{
                    fGrandMotherNameLabel.setText("");
                    fGrandMotherBreederLabel.setText("");
                    fGrandMotherDateLabel.setText("");
                    fGrandMotherBreedLabel.setText("");
                    fGrandMotherColorLabel.setText("");
                    fGrandMotherPatternLabel.setText("");
                    fGrandMotherEyeColorLabel.setText("");
                }

                if(father.getFatherId() > 0 ) {
                    Bunny ff = DataBaseBunny.getInstance().getBunnyById(father.getFatherId());
                    fGrandFatherNameLabel.setText(ff.getName());
                    fGrandFatherBreederLabel.setText(DataBaseBreeder.getInstance().getBreeder(ff.getBreederId()).getOwnerName());
                    fGrandFatherDateLabel.setText(ff.getDateOfBirth().toString());
                    fGrandFatherBreedLabel.setText(ff.getAnimalType().toString());
                    fGrandFatherColorLabel.setText(ff.getBunnyColor().toString());
                    fGrandFatherPatternLabel.setText(ff.getBunnyPattern().toString());
                    fGrandFatherEyeColorLabel.setText(ff.getEyeColor().toString());
                }else{
                    fGrandFatherNameLabel.setText("");
                    fGrandFatherBreederLabel.setText("");
                    fGrandFatherDateLabel.setText("");
                    fGrandFatherBreedLabel.setText("");
                    fGrandFatherColorLabel.setText("");
                    fGrandFatherPatternLabel.setText("");
                    fGrandFatherEyeColorLabel.setText("");
                }
            }else{
                fatherNameLabel.setText("");
                fatherBreederLabel.setText("");
                fatherDateLabel.setText("");
                fatherBreedLabel.setText("");
                fatherColorLabel.setText("");
                fatherPatternLabel.setText("");
                fatherEyeColorLabel.setText("");

                fGrandMotherNameLabel.setText("");
                fGrandMotherBreederLabel.setText("");
                fGrandMotherDateLabel.setText("");
                fGrandMotherBreedLabel.setText("");
                fGrandMotherColorLabel.setText("");
                fGrandMotherPatternLabel.setText("");
                fGrandMotherEyeColorLabel.setText("");

                fGrandFatherNameLabel.setText("");
                fGrandFatherBreederLabel.setText("");
                fGrandFatherDateLabel.setText("");
                fGrandFatherBreedLabel.setText("");
                fGrandFatherColorLabel.setText("");
                fGrandFatherPatternLabel.setText("");
                fGrandFatherEyeColorLabel.setText("");
            }

        }
    }
}