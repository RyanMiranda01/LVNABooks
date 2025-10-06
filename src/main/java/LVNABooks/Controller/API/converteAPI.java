package LVNABooks.Controller.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class converteAPI {
    private static ObjectMapper mapper = new ObjectMapper();


    public static  <T> T converteAPi(String json, Class<T> clas) throws JsonProcessingException {

        return mapper.readValue(json, clas);
    }


}
