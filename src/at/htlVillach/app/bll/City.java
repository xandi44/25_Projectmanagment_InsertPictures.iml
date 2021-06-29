package at.htlVillach.app.bll;

import at.htlVillach.app.dal.dao.Dao;

import java.util.HashSet;
import java.util.Set;

public class City {
    int id;
    String name;
    int einwohner;
    int flaeche;
    int seehohe;
    String webseite;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Set<City> getCities(Dao<City> dao){
        return new HashSet<>(dao.getAll());
    }

}
