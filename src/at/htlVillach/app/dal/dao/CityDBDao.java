package at.htlVillach.app.dal.dao;

import at.htlVillach.app.bll.City;
import at.htlVillach.app.dal.DatabaseManager;

import java.util.List;

public class CityDBDao implements  Dao<City>{
    @Override
    public List<City> getAll() {
        return DatabaseManager.getInstance().getAllCities();
    }
}
