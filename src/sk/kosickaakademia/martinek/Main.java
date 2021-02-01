package sk.kosickaakademia.martinek;


import sk.kosickaakademia.martinek.entity.City;
import sk.kosickaakademia.martinek.output.Output;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        databasa mojaPrvaDatabasa = new databasa();
       // mojaPrvaDatabasa.showCities("Argentina");
       // mojaPrvaDatabasa.cityJson("Slovakia");

        Output out = new Output();

        String country = "Italy";
        List<City> list = mojaPrvaDatabasa.getCities(country);
        out.printCities(list);
    }
}
