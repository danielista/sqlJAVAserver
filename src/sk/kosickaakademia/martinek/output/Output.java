package sk.kosickaakademia.martinek.output;

import sk.kosickaakademia.martinek.entity.CapitalCity;
import sk.kosickaakademia.martinek.entity.City;
import sk.kosickaakademia.martinek.entity.Country;

import java.util.List;

public class Output {
    public void printCities(List<City> list){
        System.out.println("List of cities: ");
        for(City c : list){
            System.out.println("    - " + c.getName() );
        }
    }


    public void printCC(List<CapitalCity> list){
        System.out.println(" TU TO JE HLAVNE MESTA ZADANEHO KONTINENTU  (metoda cc)" );
        for(CapitalCity cc : list){
            System.out.println(" - Krajina: " + cc.getCountry() +"  ,mesto: "+ cc.getCityname() +"  s populáciou: "+ cc.getPopulation() );
        }
    }


    public void printCountryInfo(Country country){

        if(country == null){
            System.out.println("country doesnt EXIST!!");
        }else{
            System.out.println("Name: " + country.getName() + " (" + country.getCode3Letters()+ ")");
            System.out.println("hlavné mesto: " + country.getCapitalCity());
            System.out.println("KONTINENT: " + country.getContinent());
        }
    }

}
