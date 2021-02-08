package sk.kosickaakademia.martinek.entity;

public class Monument {

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMonumentName() {
        return monumentName;
    }

    public void setMonumentName(String monumentName) {
        this.monumentName = monumentName;
    }

    public int getMonumentID() {
        return monumentID;
    }

    public void setMonumentID(int monumentID) {
        this.monumentID = monumentID;
    }

    public Monument(String countryName, String cityName, String monumentName, int monumentID) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.monumentName = monumentName;
        this.monumentID = monumentID;
    }

    /*
             Trieda Monument sa sklada z :
          - nazov krajiny (nie kod)
          - nazov mesta
          - nazov pamiatky
          - id pamiatky
             */
    //  public boolean insertNewMonument( String code3, String city, String name )
    private String countryName;
    private String cityName;
    private String monumentName;
    private int monumentID;
}
