package sk.kosickaakademia.martinek.entity;

public class City {
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCode3() {
        return code3;
    }

    private String code3;
    private String name;

    private int population;
    private String country;
    private String code;
    private String district;

    public void setCode3(String code3) {
        this.code3 = code3;
    }



    public City(String name, int population, String district, String country) {
        this.name = name;
        this.population = population;
        this.district = district;
        this.country = country;
    }


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
