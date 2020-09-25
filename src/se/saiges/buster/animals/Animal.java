package se.saiges.buster.animals;

import java.time.LocalDate;

public abstract class Animal {

    private int id;
    private int motherId;
    private int fatherId;
    private int litterId;

    private String name;
    private int breederId;
    private LocalDate dateOfBirth;
    private Sex sex;
    private AnimalType animalType;

    private int isOwned;

    // Constructor's
    public Animal() {
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getBreederId() { return breederId; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public Sex getSex() { return sex; }
    public AnimalType getAnimalType() { return animalType; }
    public int isOwned() { return isOwned; }
    public int getMotherId() { return motherId; }
    public int getFatherId() { return fatherId; }
    public int getLitterId() { return litterId; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBreederId(int breederId) { this.breederId = breederId; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setSex(Sex sex) { this.sex = sex; }
    public void setAnimalType(AnimalType animalType) { this.animalType = animalType; }
    public void setOwned(int owned) { isOwned = owned; }
    public void setMotherId(int motherId) { this.motherId = motherId; }
    public void setFatherId(int fatherId) { this.fatherId = fatherId; }
    public void setLitterId(int litterId) { this.litterId = litterId; }
}