package sk.kosickaakademia.martinek.entity;

public class Monument {

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMonumentName() {
        return monumentName;
    }

    public void setMonumentName(String monumentName) {
        this.monumentName = monumentName;
    }

    public Monument(String code3, String city, String monumentName) {
        this.code3 = code3;
        this.city = city;
        this.monumentName = monumentName;
    }

    //  public boolean insertNewMonument( String code3, String city, String name )
    private String code3;
    private String city;
    private String monumentName;
}
