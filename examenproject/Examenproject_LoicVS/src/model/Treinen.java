package model;

public class Treinen {
    private int classNummer;
    private short maxWagons;

    // Constructor
    public Treinen(int locomotiefClass, short maxWagons) {
        this.classNummer = locomotiefClass;
        this.maxWagons = maxWagons;
    }

    // Getter Setter
    public int getClassNummer() {
        return classNummer;
    }

    public void setClassNummer(int classNummer) {
        this.classNummer = classNummer;
    }

    public short getMaxWagons() {
        return maxWagons;
    }

    public void setMaxWagons(short maxWagons) {
        this.maxWagons = maxWagons;
    }
}
