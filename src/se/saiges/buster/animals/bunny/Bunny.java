package se.saiges.buster.animals.bunny;

import se.saiges.buster.Displayable;
import se.saiges.buster.animals.Animal;
import se.saiges.buster.animals.EyeColor;

public class Bunny extends Animal implements Displayable {

    private BunnyBreed bunnyBreed;
    private BunnyColor bunnyColor;
    private BunnyFurType bunnyFurType;
    private BunnyPattern bunnyPattern;
    private EyeColor eyeColor;

    //Constructors
    public Bunny() {
    }

    // Getters
    public BunnyBreed getBunnyBreed() { return bunnyBreed; }
    public BunnyColor getBunnyColor() { return bunnyColor; }
    public BunnyFurType getBunnyFurType() { return bunnyFurType; }
    public BunnyPattern getBunnyPattern() { return bunnyPattern; }
    public EyeColor getEyeColor() { return eyeColor; }

    // Setters
    public void setBunnyBreed(BunnyBreed bunnyBreed) { this.bunnyBreed = bunnyBreed; }
    public void setBunnyColor(BunnyColor bunnyColor) { this.bunnyColor = bunnyColor; }
    public void setBunnyFurType(BunnyFurType bunnyFurType) { this.bunnyFurType = bunnyFurType; }
    public void setBunnyPattern(BunnyPattern bunnyPattern) { this.bunnyPattern = bunnyPattern; }
    public void setEyeColor(EyeColor eyeColor) { this.eyeColor = eyeColor; }

    @Override
    public String toString() {
        return getName();
    }
}
