package sk.kosickaakademia.martinek;

import sk.kosickaakademia.martinek.entity.City;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class databasa {


    public List<City> getCities(String KAUNTRI){
tajnosti tj = new tajnosti();
        List<City> list = new ArrayList<>();
        try {
            String query = "SELECT city.Name, city.CountryCode " +
                    "FROM city " +
                    "INNER JOIN country ON country.code = city.CountryCode " +
                    "WHERE country.name LIKE ?";  //? znamemná že tam dačo chýýýba sú očíslované tie otázniky

            Connection conn = DriverManager.getConnection(tj.getUrl(), tj.getUsername(), tj.getPassword());
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(conn!=null){
                System.out.println("SAXES");
                PreparedStatement ps = conn.prepareStatement(query);
                System.out.println(ps);


                ps.setString(1,KAUNTRI);  // ošetrenie.. overuje či to je string..


                ResultSet rs = ps.executeQuery();  // tu ho už spúšťa
                while (rs.next()){
                    String mesta = rs.getString("Name");
                    String code = rs.getString("CountryCode");
                    String country = rs.getString("CountryCode");
                    City city = new City(mesta,code);
                    list.add(city);
                }
                conn.close();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

// JSON PARSING
    public void cityJson(String KAUNTRI){
        tajnosti tj = new tajnosti();
        try {
            String query = "SELECT city.Name, JSON_EXTRACT(Info,'$.Population') AS population " +
                    "FROM city " +
                    "INNER JOIN country ON country.code = city.CountryCode " +
                    "WHERE country.name LIKE ? ORDER BY Population DESC";  //? znamemná že tam dačo chýýýba sú očíslované tie otázniky
            //  Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(tj.getUrl(), tj.getUsername(), tj.getPassword());
            if(conn!=null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("SAXES");
                PreparedStatement ps = conn.prepareStatement(query);
                System.out.println(ps);


                ps.setString(1,KAUNTRI);  // ošetrenie.. overuje či to je string..


                ResultSet rs = ps.executeQuery();  // tu ho už spúšťa
                while (rs.next()){
                    String city = rs.getString("Name");
                    int pop = rs.getInt("Population");
                    System.out.println(city + " (" + pop + ") ");
                }
                conn.close();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
