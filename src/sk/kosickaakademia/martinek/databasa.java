package sk.kosickaakademia.martinek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class databasa {


    public void showCities(){
tajnosti tj = new tajnosti();
        try {
            String query = "SELECT Name, CountryCode " +
                    "FROM city " +
                    "WHERE CountryCode LIKE '000'";
            //  Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(tj.getUrl(), tj.getUsername(), tj.getPassword());
            if(conn!=null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("SAXES");
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
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
