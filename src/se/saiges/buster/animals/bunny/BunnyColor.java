package se.saiges.buster.animals.bunny;

public enum BunnyColor {
    VIT ("Vit"),
    SVART ("Svart"),
    BLÅ ("Blå"),
    ZOBELBLÅ ("Zobel Blå"),
    ZOBELBRUN ("Zobel Brun"),
    ZOBELEGERN ("Zobel Egern"),
    EGERN ("Egern"),
    ISABELLA ("Isabella"),
    HAVANA ("Havanna"),
    RYSSTECKNAD ("Rysstecknad"),
    JAPANTECKNAD ("Japantecknad"),
    GUL ("Gul"),
    BOURGONE ("Bourgone"),
    MADAGASKAR ("Madagaskar"),
    SALANDER ("Salander"),
    CHINCHILLA ("Chinchilla"),
    OTTER ("Otter");

    private String colorName;

    BunnyColor(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return colorName;
    }
}
