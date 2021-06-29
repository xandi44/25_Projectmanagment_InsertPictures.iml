package at.htlVillach.app;

import at.htlVillach.app.bll.City;
import at.htlVillach.app.dal.dao.CityDBDao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        try {

            // St. Moritz geht nicht weil irgendwie jeder Name so ca 100 leerzeichen hat

            Set<City> cities = City.getCities(new CityDBDao());
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@tcif.htl-villach.at:1521/orcl", "d3a27", "d3a27");

            for(City c : cities){
                InputStream res = Main.class.getResourceAsStream("/"+c.getName()+"/");

                BufferedReader reader = new BufferedReader(new InputStreamReader(res));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    PreparedStatement ps = con.prepareStatement("insert into picture (idCity,picture) values(?,?)");
                    ps.setInt(1, c.getId());

                    String path = ("/"+c.getName().replace(" ","")+"/"+line);
                    InputStream picture = Main.class.getResourceAsStream(path);

                    ps.setBinaryStream(2, picture);
                    ps.executeUpdate();

                }
                reader.close();
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
