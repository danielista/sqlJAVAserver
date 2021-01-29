package sk.kosickaakademia.martinek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class databasa {


    public void showCities(String countryCode1, String countryCode2){
tajnosti tj = new tajnosti();
        try {
            String query = "SELECT Name, CountryCode " +
                    "FROM city " +
                    "WHERE CountryCode LIKE ? OR CountryCode LIKE ?";  //? znamemná že tam dačo chýýýba sú očíslované tie otázniky
            //  Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(tj.getUrl(), tj.getUsername(), tj.getPassword());
            if(conn!=null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("SAXES");
                PreparedStatement ps = conn.prepareStatement(query);

                ps.setString(1,countryCode1);  // ošetrenie.. overuje či to je string..
                ps.setString(2,countryCode2);  // ošetrenie.. overuje či to je string..


                ResultSet rs = ps.executeQuery();  // tu ho už spúšťa
                while (rs.next()){
                    String city = rs.getString("Name");
                    String code = rs.getString("CountryCode");
                    System.out.println(city + " " + code);
                }
                conn.close();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
