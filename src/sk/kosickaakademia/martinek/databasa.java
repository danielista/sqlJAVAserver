package sk.kosickaakademia.martinek;

import sk.kosickaakademia.martinek.entity.City;
import sk.kosickaakademia.martinek.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class databasa {
    tajnosti tj = new tajnosti();

    //
    public Country getCountryInfo(String country){
        String queryZLY = "SELECT country.name, country.code, city.name " +
               // " JSON_EXTRACT(doc, '$.geography.Continent') AS kontinent " +
                " JSON_UNQUOTE(JSON_EXTRACT(doc, '$.geography.Continent')) AS continent " +
                " JSON_EXTRACT(doc, '$.geography.SurfaceArea') AS area "+
                " FROM country "+
                " INNER JOIN city ON country.Capital = city.ID "+
                " INNER JOIN countryinfo ON country.code = countryinfo._id "+
                " WHERE country.name LIKE ? ";
        String query = "SELECT country.name, country.code, city.name, " +
                " JSON_UNQUOTE(JSON_EXTRACT(doc, '$.geography.Continent')) AS Continent, " +
                " JSON_EXTRACT(doc, '$.geography.SurfaceArea') AS area" +
                " FROM country " +
                " INNER JOIN city ON country.Capital = city.ID " +
                " INNER JOIN countryinfo ON country.code = countryinfo._id " +
                " WHERE country.name like ?";

        Country countryInfo = null;
try {
    Connection con = getConnection();

    PreparedStatement ps = con.prepareStatement( query );
    ps.setString(1,country);
    ResultSet rs = ps.executeQuery();
    if (rs.next()){
        String code3Letters = rs.getString("country.code");
        String capitalCity = rs.getString("city.name");
        String continent = rs.getString("continent");
        int area = rs.getInt("area");
        System.out.println(code3Letters +  " " +capitalCity + " " + continent);

        countryInfo = new Country(country,code3Letters,capitalCity,area,continent);

    }


}catch (Exception e){
    e.printStackTrace();
}


        return countryInfo;
    }




    public List<City> getCities(String KAUNTRI){
        List<City> list = new ArrayList<>();
        try {
            String query = "SELECT city.Name, city.CountryCode " +
                    "FROM city " +
                    "INNER JOIN country ON country.code = city.CountryCode " +
                    "WHERE country.name LIKE ?";  //? znamemná že tam dačo chýýýba sú očíslované tie otázniky

            Connection conn = getConnection();
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

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection(tj.getUrl(), tj.getUsername(), tj.getPassword());
        Class.forName("com.mysql.cj.jdbc.Driver");
        return conn;
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
