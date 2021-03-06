package sk.kosickaakademia.martinek;

import sk.kosickaakademia.martinek.entity.CapitalCity;
import sk.kosickaakademia.martinek.entity.City;
import sk.kosickaakademia.martinek.entity.Country;
import sk.kosickaakademia.martinek.entity.Monument;
import sk.kosickaakademia.martinek.json.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class databasa {
    tajnosti tj = new tajnosti();

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection(tj.getUrl(), tj.getUsername(), tj.getPassword());
        Class.forName("com.mysql.cj.jdbc.Driver");
        return conn;
    }

    public String getCountryCode (String name){
            if(name == null || name.equalsIgnoreCase(""))   return null;
            try {
                Connection con= getConnection();
                String query = "SELECT Code FROM country WHERE Name LIKE ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1,name);  //nahradzujeme jeden a vlastne ten prvy otaznik v query
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    String code = rs.getString("Code");
                    con.close();
                    return code;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            /*
            catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
             */

        return null;
    }


    // DOES EXIST CITY?  :D
    public int existCity(String code3, String cityName){
        if(code3==null || cityName==null || code3.equals("") || cityName.equals(""))
            return -1;


        String query = "SELECT id FROM city WHERE CountryCode LIKE ? AND name LIKE ? ";
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, code3);
                        ps.setString(2, cityName);
                        ResultSet rs = ps.executeQuery();

                       // con.close();

                        if(rs.next()){
                            int id = rs.getInt("id");
                            con.close();
                            return id;
                        }else {
                            con.close();
                            return -1;
                        }



            }catch (Exception e){
                e.printStackTrace();
            }



        return -1; // znamená že mesto neexistuje
    }

    public boolean insertNewMonument(String code3, String city, String name){


        if(name == null || name.equals(""))
            return false;

        int cityId = existCity(code3, city);
        if(cityId == -1)return false;

        String query = "INSERT INTO monument(name,city) VALUES (?, ?) ";

        try {
            Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1,name);
                ps.setInt(2,cityId);
                ps.executeUpdate();
            con.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

   // GET MONUMENTS STATEMENT
    public List<Monument>  getMonuments(){
        String query= "SELECT monument.id AS monumentID, monument.name AS monumentName, city.Name AS mestecko, country.Name AS krajina " +
                "       FROM monument " +
                "        INNER JOIN city ON city.ID = monument.city " +
                "         INNER JOIN country ON country.Code = city.CountryCode ";
        ArrayList<Monument> monuments = new ArrayList<>();

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement( query );

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String krajina = rs.getString("krajina");
                String mestecko = rs.getString("mestecko");
                String monumentName = rs.getString("monumentName");
                int monumentID = rs.getInt("monumentID");

              //  System.out.println(krajina +  " " +mestecko + " " + monumentName + monumentID);
                Monument monument = new Monument(krajina,mestecko,monumentName,monumentID);
                monuments.add(monument);

            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return monuments;
    }


    /// GET CAPITAL CIETIES BASED ON CONTINENT INPUT ;) 5.februara 2021
    List<CapitalCity> getCapitalCities(String continent) {

            String query= "SELECT country.name AS krajina, city.name AS mestecko,         \n" +
                    "         JSON_EXTRACT(Info,'$.Population') AS populacia\n" +
                    "            FROM country\n" +
                    "            INNER JOIN city ON country.Capital = city.ID\n" +
                    "            INNER JOIN countryinfo ON country.code = countryinfo._id\n" +
                    "            WHERE JSON_UNQUOTE(JSON_EXTRACT(doc, '$.geography.Continent')) like ? ";
            ArrayList<CapitalCity> continentCities = new ArrayList<>();

            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement( query );
                ps.setString(1,continent);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    String krajina = rs.getString("krajina");
                    String mestecko = rs.getString("mestecko");
                    int populacia = rs.getInt("populacia");

                   // System.out.println(krajina +  " " +mestecko + " " + populacia);
                    CapitalCity cc = new CapitalCity(krajina,mestecko,populacia);
                    continentCities.add(cc);

                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return continentCities;
    }



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
                    // System.out.println(code3Letters +  " " +capitalCity + " " + continent);

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
                conn.close(); // treba aj zatvárať po sebe connections  .. čo máš doma záclony? :D

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



    public boolean chceckCity (String country, String city){

        String query = "SELECT Name, CountryCode from city " +
                "WHERE CountryCode LIKE ?";
        try {
            Connection connection = getConnection();
                String code = getCountryCode(country);
                if (code == null) return false;
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, code);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    if (city.equalsIgnoreCase(rs.getString("Name")))
                        return true;
                }
                connection.close();
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }


    public void updatePopulation(String country, String city, int population) {
    //if(chceckCity(country,city)){           // skúšam či nieje v tej samotnej metode nejaka chyba :D a teda potom to spraviť cez jeden select aj validaciu

            if(population < 1 || population>1111111111){
                System.out.println("ACHTUNG ACHTUNG ::!! nemožeš zmeniť populaciu niesi Thanos");
            } else {
                String query = "update city " +
                        "inner join country on city.countryCode=country.code " +
                        "set info=? " +
                        "where city.name like ? and country.name like ?";

            /*

                                String query = "UPDATE city " +
                                        "SET Info = '?' " +
                                        "WHERE Name = '?'" ;




                        String query = "UPDATE city " +
                                " SET " +
                                " Info = ? " +
                                " FROM " +
                                " Country " +
                                " INNER JOIN city ON country.code = city.CountryCode " +
                                " WHERE " +
                                " city.Name = ? ";

                                FROM country city
            INNER JOIN city ON country.code = city.CountryCode
                 */

                try {
                    Connection con = getConnection();
                    PreparedStatement ps = con.prepareStatement(query);

                    String json="{\"Population\": " + population +"}";
                    ps.setString(1,json);  // vkladanie JSON-u
                    ps.setString(2,city);

                    ps.executeUpdate(); //toto ale vracia int kolko riadkov zmien urobil
                    System.out.println("result: "+ps.executeUpdate());
                    con.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        //}else System.out.println("Si si istý že mesto Ti patrí do tvojho štátu?");
    }


    public void insertCity(City newCity) {
        String country = newCity.getCountry();
        String code3 = getCountryCode(country);
        if(code3 == null){
            System.out.println("ACHTUNG ACHTUNG dana krajina:"+ country+"neexistuje! :D");
        } else {
            newCity.setCode3(code3);
            String query = " INSERT INTO city (Name, CountryCode, District, Info) " +
                    " VALUES ( ?,?,?,?) ";


            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1,newCity.getName());
                ps.setString(2,newCity.getCode3());
                ps.setString(3,newCity.getDistrict());


                String json="{\"Population\": " + newCity.getPopulation() +"}";
                ps.setString(4,json);  // vkladanie JSON-u

                //ps.execute(); //vrati true alebo false.. či sa podarilo vložiť HOVNO
                ps.executeUpdate(); //toto ale vracia int kolko riadkov zmien urobil
                System.out.println("result: "+ps.executeUpdate());
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}