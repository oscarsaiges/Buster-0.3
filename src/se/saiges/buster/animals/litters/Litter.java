package se.saiges.buster.animals.litters;

import se.saiges.buster.animals.bunny.Bunny;
import se.saiges.buster.modul.DataBaseBunny;

import java.time.LocalDate;
import java.util.ArrayList;

public class Litter {

    private int litterId;
    private int motherId;
    private int fatherId;

    private int born;
    private int alive;
    private int defect;
    private int males;
    private int females;

    private LocalDate dateOfBirth;

    private ArrayList<Bunny> litterList = new ArrayList<>();

    public Litter() {
    }
    // Getters
    public int getLitterId() { return litterId; }
    public int getMotherId() { return motherId; }
    public int getFatherId() { return fatherId; }
    public int getBorn() { return born; }
    public int getAlive() { return alive; }
    public int getDefect() { return defect; }
    public int getMales() { return males; }
    public int getFemales() { return females; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public ArrayList<Bunny> getLitterList() { return litterList; }

    // Setters
    public void setLitterId(int litterId) { this.litterId = litterId; }
    public void setMotherId(int motherId) { this.motherId = motherId; }
    public void setFatherId(int fatherId) { this.fatherId = fatherId; }
    public void setBorn(int born) { this.born = born; }
    public void setAlive(int alive) { this.alive = alive; }
    public void setDefect(int defect) { this.defect = defect; }
    public void setMales(int males) { this.males = males; }
    public void setFemales(int females) { this.females = females; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setLitterList(ArrayList<Bunny> litterList) { this.litterList = litterList; }

    // Methods
    public void addToLitterList(Bunny bunny){
        litterList.add(bunny);
    }

    public void removeFromLitterList(Bunny bunny){
        litterList.remove(bunny);
    }

    @Override
    public String toString() {
        return  DataBaseBunny.getInstance().getBunnyById(motherId).getName() + " " +
                DataBaseBunny.getInstance().getBunnyById(fatherId).getName() + " " +
                dateOfBirth.toString();
    }
}
