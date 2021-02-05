package sk.kosickaakademia.martinek;


import sk.kosickaakademia.martinek.output.Output;


public class Main {
    public static void main(String[] args) {
        databasa mojaPrvaDatabasa = new databasa();
       // mojaPrvaDatabasa.showCities("Argentina");
       // mojaPrvaDatabasa.cityJson("Slovakia");

        Output out = new Output();


       //   String country = "Slovakia";

       // List<City> list = mojaPrvaDatabasa.getCities(country);
       // out.printCities(list);


        // GET COUNTRY INFO BASED ON COUNTRY INPUT
        // Country country1 = mojaPrvaDatabasa.getCountryInfo(country);
        // out.printCountryInfo(country1);
        // System.out.println(country1);


          // CAPITAL CITIES CONTINENT
          out.printCC(mojaPrvaDatabasa.getCapitalCities("Europe"));


        //insertiky :D
        //String code = mojaPrvaDatabasa.getCountryCode("Mali");
        //System.out.println(code);


       // City newCity = new City("Bytca",404,"Zilina","Slovakia");
       // mojaPrvaDatabasa.insertCity(newCity);

       // nejde ešte PROPERLY mojaPrvaDatabasa.updatePopulation("Afganistan","Kabul",1786652);
    }
}
