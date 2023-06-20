package Interfaces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Interface que la implementan las clases que necesiten convertir algun tipo de dato
 * en un JSONArray.
 */
public interface I_toJSONArray
{
     JSONArray toJSONArray()throws JSONException;
}
