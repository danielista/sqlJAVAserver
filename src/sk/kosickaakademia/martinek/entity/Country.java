package sk.kosickaakademia.martinek.entity;

public class Country {

    private String name;
    private String code3Letters;

    //konštruktor na UPDATE overenie či existuje mesto
    public Country(String name, String code3Letters) {
        this.name = name;
        this.code3Letters = code3Letters;
    }

    private String capitalCity;
    private String continent;
    private int area; // rozloha km2

    public Country(String name, String code3Letters, String capitalCity, int area, String continent) {
        this.name = name;
        this.code3Letters = code3Letters;
        this.capitalCity = capitalCity;
        this.area = area;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public String getCode3Letters() {
        return code3Letters;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public int getArea() {
        return area;
    }

    public String getContinent() {
        return continent;
    }




}
