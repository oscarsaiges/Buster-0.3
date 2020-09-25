package se.saiges.buster.animals;

public enum AnimalType {
    BUNNY ("BUNNY");

    private String animalType;

    AnimalType(String animalType){
        this.animalType = animalType;
    }


    @Override
    public String toString() {
        return animalType;
    }
}
