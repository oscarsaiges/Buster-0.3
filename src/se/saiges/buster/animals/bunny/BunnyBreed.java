package se.saiges.buster.animals.bunny;

public enum BunnyBreed {
    HERMELIN("HERMELIN");

    private String bunnyBreed;

    BunnyBreed(String bunnyBreed){
        this.bunnyBreed = bunnyBreed;
    }

    @Override
    public String toString() {
        return bunnyBreed;
    }
}
