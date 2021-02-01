package sk.kosickaakademia.martinek.entity;

public class City {
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    private String name;
    private String code;

    public City(String name, String code) {
        this.name = name;
        this.code = code;
    }




}
