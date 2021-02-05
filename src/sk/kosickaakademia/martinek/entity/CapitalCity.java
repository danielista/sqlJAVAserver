package sk.kosickaakademia.martinek.entity;

public class CapitalCity {

    //getters
    public int getPopulation() {
        return population;
    }
    public String getCityname() {    return cityname;    }
    public String getCountry() {
        return country;
    }

    //premenné
    private String cityname;
    private int population;
    private String country;

    //konštruktor
    public CapitalCity( String country, String cityname, int population) {
        this.cityname = cityname;
        this.population = population;
        this.country = country;
    }
}
