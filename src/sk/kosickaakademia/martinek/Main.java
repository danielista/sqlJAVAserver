package sk.kosickaakademia.martinek;

import sk.kosickaakademia.martinek.entity.City;
import sk.kosickaakademia.martinek.entity.Country;
import sk.kosickaakademia.martinek.output.Output;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        databasa mojaPrvaDatabasa = new databasa();
       // mojaPrvaDatabasa.showCities("Argentina");
       // mojaPrvaDatabasa.cityJson("Slovakia");

        Output out = new Output();

        String country = "Slovakia";

        List<City> list = mojaPrvaDatabasa.getCities(country);
        out.printCities(list);

        Country country1 = mojaPrvaDatabasa.getCountryInfo(country);
        out.printCountryInfo(country1);
        // System.out.println(country1);
    }
}
