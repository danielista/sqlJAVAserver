package sk.kosickaakademia.martinek.output;

import sk.kosickaakademia.martinek.entity.City;

import java.util.List;

public class Output {
    public void printCities(List<City> list){
        for(City c : list){
            System.out.println(c.getName() );
        }
    }
}
