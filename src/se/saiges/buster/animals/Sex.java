package se.saiges.buster.animals;

public enum Sex {
    FEMALE ("FEMALE"),
    MALE ("MALE");

    private String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return sex;
    }
}
