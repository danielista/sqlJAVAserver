package sk.kosickaakademia.martinek.json;


import org.json.simple.*;
import sk.kosickaakademia.martinek.databasa;
import sk.kosickaakademia.martinek.entity.Monument;


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
}
