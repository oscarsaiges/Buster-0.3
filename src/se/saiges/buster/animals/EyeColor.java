package se.saiges.buster.animals;

public enum EyeColor {
    BLUE ("BLUE"),
    BROWN ("BROWN"),
    RED ("RED"),
    GREY ("GREY"),
    MIXED ("MIXED");

    private String eyeColor;

    EyeColor(String eyeColor){
        this.eyeColor = eyeColor;
    }

    @Override
    public String toString() {
        return eyeColor;
    }
}
