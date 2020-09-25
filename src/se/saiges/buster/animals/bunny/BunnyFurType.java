package se.saiges.buster.animals.bunny;

public enum BunnyFurType {
    NORMALPÄLS ("NormalPäls"),
    REX ("Rex"),
    LEJONHUVAD ("Lejonhuvad"),
    SATIN ("Satin"),
    TEDDY ("Teddy"),
    SATINREX ("SatinRex"),
    FUCHS ("Fuchs"),
    ANGORA ("Angora");

    private String furrType;

    BunnyFurType(String furrType) {
        this.furrType = furrType;
    }

    @Override
    public String toString() {
        return furrType;
    }
}
