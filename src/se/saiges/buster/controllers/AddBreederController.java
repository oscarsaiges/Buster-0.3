package se.saiges.buster.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import se.saiges.buster.breeder.Breeder;

public class AddBreederController {

    @FXML
    private TextField breeder;
    @FXML
    private TextField ownerName;
    @FXML
    private TextField ownerEmail;
    @FXML
    private TextField ownerAddress;
    @FXML
    private TextField ownerZipCode;
    @FXML
    private TextField ownerPhoneNumber;

    public Breeder processResult(){
        return new Breeder( ownerName.getText().trim(),
                            ownerEmail.getText().trim(),
                            ownerAddress.getText().trim(),
                            ownerZipCode.getText().trim(),
                            ownerPhoneNumber.getText().trim(),
                            breeder.getText().trim());
    }

    public void populateDialog(Breeder breeder){
        this.breeder.setText(breeder.getBreederName());
        this.ownerName.setText(breeder.getOwnerName());
        this.ownerEmail.setText(breeder.getOwnerEmail());
        this.ownerAddress.setText(breeder.getOwnerAddress());
        this.ownerZipCode.setText(breeder.getOwnerZipCode());
        this.ownerPhoneNumber.setText(breeder.getOwnerPhoneNumber());
    }

    public Breeder updateBreeder(Breeder breeder){
        breeder.setBreederName(this.breeder.getText().trim());
        breeder.setOwnerName(this.ownerName.getText().trim());
        breeder.setOwnerEmail(this.ownerEmail.getText().trim());
        breeder.setOwnerAddress(this.ownerAddress.getText().trim());
        breeder.setOwnerZipCode(this.ownerZipCode.getText().trim());
        breeder.setOwnerPhoneNumber(this.ownerPhoneNumber.getText().trim());
        return breeder;
    }

}
