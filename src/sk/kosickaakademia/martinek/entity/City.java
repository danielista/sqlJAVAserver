package sk.kosickaakademia.martinek.entity;

public class City {
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    private String code;
    public void setCode3(String code3) {
        this.code3 = code3;
    }

// sprav si ešte konštruktor.. s tým čo potrebuješ.. :D
    private String code3;
    private String name;
    private int population;
    private String country;
    private String district;


    public int getPopulation() {
        return population;
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public City(String name, String code) {
        this.name = name;
        this.code = code;
    }




}
