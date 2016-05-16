package pt.iscte.dam.callphpwebserviceexample.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by Miguel on 17/05/16.
 */
public class JsonParser {
    private static JsonParser mInstance = null;

    private static Gson gson;

    private JsonParser(){
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
    }

    public static JsonParser getInstance(){
        if(mInstance == null)
        {
            mInstance = new JsonParser();
        }
        return mInstance;
    }

    public <T> String objectToString(T object) {
        return gson.toJson(object);
    }

    public <T> T stringToObject(String jsonString, Class<T> classType) {
        return gson.fromJson(jsonString, classType);
    }

    public <T> T stringToObjectType(String jsonString, Type classType) {
        return gson.fromJson(jsonString, classType);
    }

}