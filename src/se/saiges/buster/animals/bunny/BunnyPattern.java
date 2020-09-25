package se.saiges.buster.animals.bunny;

public enum BunnyPattern {
    DALMATINER ("Dalmatiner"),
    MANTELTECKNAD ("Manteltecknad"),
    ENGELSKSCHECKTECKNING ("Engelskscheckteckning"),
    RHINSKSCHECKTECKNING ("Rhinscheckteckning"),
    TYSKSCHECKTECKNING ("Tyskscheckteckning"),
    GEPARD ("Gepard"),
    TREFÄRGAD ("Trefärgad"),
    HOLLÄNDARTECKNING ("Holländarteckning"),
    HOTOT ("Hotot"),
    JAPANTECKNING ("Japanteckning"),
    RYSSTECKNAD ("Rysstecknad"),
    VITBLÅÖGD ("Vit Blåögd"),
    VITRÖDÖGD ("Vir Rödögd"),
    BROKAD ("Brokad"),
    VITÖRAD ("Virörad"),
    WIENERTECKNAD ("Wienertecknad");

    private String bunnyPattern;

    BunnyPattern(String bunnyPattern) {
        this.bunnyPattern = bunnyPattern;
    }

    @Override
    public String toString() {
        return bunnyPattern;
    }
}
