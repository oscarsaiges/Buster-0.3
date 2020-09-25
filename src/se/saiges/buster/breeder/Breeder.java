package se.saiges.buster.breeder;

import java.io.Serializable;

public class Breeder implements Serializable, Comparable<Breeder> {

    private int id;

    private String ownerName;
    private String ownerEmail;
    private String ownerAddress;
    private String ownerZipCode;
    private String ownerPhoneNumber;
    private String breederName;

//    private List<Animal> breederAnimalList;

    private long serialVersionUID = 1L;

    public Breeder() {
    }

    //Remove when DB is up and running
    public Breeder(String ownerName, String ownerEmail, String ownerAddress, String ownerZipCode, String ownerPhoneNumber, String breederName) {
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.ownerAddress = ownerAddress;
        this.ownerZipCode = ownerZipCode;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.breederName = breederName;
//        this.breederAnimalList = new ArrayList<>();
    }

    // Add add remove animal from the breeder list.
//    public void addAnimalToList(Animal a) {
//        breederAnimalList.add(a);
//    }
//    public  void removeAnimalFromList(Animal a){
//        breederAnimalList.remove(a);
//    }

    // Getters

    public int getId() { return id; }
    public String getOwnerName() { return ownerName; }
    public String getOwnerEmail() { return ownerEmail; }
    public String getOwnerAddress() { return ownerAddress; }
    public String getOwnerZipCode() { return ownerZipCode; }
    public String getOwnerPhoneNumber() { return ownerPhoneNumber; }
    public String getBreederName() { return breederName; }
//    public List<Animal> getBreederAnimalList() { return breederAnimalList; }

//  Setters

    public void setId(int id) { this.id = id; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
    public void setOwnerAddress(String ownerAddress) { this.ownerAddress = ownerAddress; }
    public void setOwnerZipCode(String ownerZipCode) { this.ownerZipCode = ownerZipCode; }
    public void setOwnerPhoneNumber(String ownerPhoneNumber) { this.ownerPhoneNumber = ownerPhoneNumber; }
    public void setBreederName(String breederName) { this.breederName = breederName; }
//    public void setBreederAnimalList(List<Animal> breederAnimalList) { this.breederAnimalList = breederAnimalList; }

    @Override
    public String toString() {
        return breederName;
    }

    @Override
    public int compareTo(Breeder breeder) {
        return this.breederName.compareTo(breeder.getBreederName());
    }
}