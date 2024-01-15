package Cucumber_Testing_Practices.kisayollar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class readJsonFile
{
    public String[] readJson() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader=new FileReader("src\\test\\java\\Cucumber_Testing_Practices\\localizations\\Personal.JSON");
        Object obj =jsonParser.parse(reader);
        JSONObject userLoginsJSONobj=(JSONObject) obj;
        JSONArray userloginsArray=(JSONArray) userLoginsJSONobj.get("users");
        String[] arr=new String[userloginsArray.size()];
        for (int i=0;i< userloginsArray.size();i++)
        {
            JSONObject users=(JSONObject)userloginsArray.get(i);
            String userMail=(String) users.get("mail");
            String userPass=(String) users.get("password");
            arr[i]=userMail+","+userPass;
        }
        return arr;
    }



}