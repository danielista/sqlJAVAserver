package sk.kosickaakademia.martinek.json;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import sk.kosickaakademia.martinek.databasa;
import sk.kosickaakademia.martinek.entity.Monument;


import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class Server {
    databasa db = new databasa();



    public String getMonument(){
        String resultJSON="";
        List<Monument> list = db.getMonuments();
        if(list.isEmpty())return "{}"; //prázdny objekt

        JSONObject jObject = new JSONObject();
        JSONArray jArray = new JSONArray();
        for (Monument m : list){
            JSONObject newItem = new JSONObject();
            newItem.put("id", m.getMonumentID());
            newItem.put("country", m.getCountryName());
            newItem.put("city", m.getCityName());
            newItem.put("monument", m.getMonumentName());

            jArray.add(newItem);
        }
        jObject.put("monument",jArray);
        System.out.println(jObject.toString());

        return resultJSON;
    }

    public JSONObject jsonINPUT(){
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("resource/jsonINPUTText.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) obj;

        String country = (String) jo.get("country");
        String city = (String) jo.get("city");
        String monument = (String) jo.get("monument");

        System.out.println(country);
        System.out.println(city);
        System.out.println(monument);

        return jo;
    }

    public String insertJSONMONUMENT(){
        String country = (String) jsonINPUT().get("country");
        String city = (String) jsonINPUT().get("city");
        String monument = (String) jsonINPUT().get("monument");

        // pokračuj danko  --> takže už to len zapíšem do monument.. však mam taku metodu hotovu OMG facepalm
        databasa db = new databasa();
        db.insertNewMonument(country,city,monument);

        return country+" "+city+" "+monument;
    }


}
