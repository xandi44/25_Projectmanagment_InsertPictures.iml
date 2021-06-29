package at.htlVillach.app.dal;



import at.htlVillach.app.bll.City;
import at.htlVillach.app.utie.PropertyManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseManager {
    private Connection connection;
    private String driver;
    private String url;
    private String username;
    private String password;
    private static DatabaseManager instance;

    private DatabaseManager(){
        PropertyManager.getInstance().setFileName("db.properties");
        this.driver = PropertyManager.getInstance().readProperty("driver","oracle.jdbc.OracleDriver");
        this.url = PropertyManager.getInstance().readProperty("url","jdbc:oracle:thin:@tcif.htl-villach.at:1521/orcl");
        this.username = PropertyManager.getInstance().readProperty("username","d3a27");
        this.password = PropertyManager.getInstance().readProperty("password","d3a27");
    }

    private Connection createConnection(){
        Connection con = null;
        //Laden des Treibers
        try {
            Class.forName(this.driver);
            con = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }



    public List<City> getAllCities() {
        ArrayList<City> studentArrayList = new ArrayList<>();

        Statement stmt;
        ResultSet resultSet;

        String query = "SELECT * FROM city";

        try(Connection con = this.createConnection()){
            //Statement wird erzeugt
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(query);
            // resultset wird durchiteriert
            while(resultSet.next()){
                studentArrayList.add(new City(resultSet.getInt(1),resultSet.getString(2)));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return studentArrayList;
    }

}
