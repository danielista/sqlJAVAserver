package sk.kosickaakademia.martinek.entity;

public class CapitalCity {


    public String getCityname() {
        return cityname;
    }

    public int getPopulation() {
        return population;
    }

    public String getCountry() {
        return country;
    }


    private String cityname;
    private int population;
    private String country;

    public CapitalCity( String country, String cityname, int population) {
        this.cityname = cityname;
        this.population = population;
        this.country = country;
    }
}
