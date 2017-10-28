package ru.staff;

public class Sector {
    private char link;
    private String value;

    public Sector(char link, String value) {
        this.link = link;
        this.value = value;
    }

    public void UpdateWithNext(Sector sector){
        this.value = this.value+String.valueOf(this.link)+sector.value;
        this.link = sector.link;
    }

    @Override
    public String toString(){
        return value;
    }

    public char getLink() {
        return link;
    }

    public String getValue() {
        return value;
    }
}
