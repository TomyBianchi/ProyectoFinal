package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Interface que la implementan las clases que necesiten convertir algun tipo de dato
 * en un JSONObject.
 */
public interface I_toJSONObject
{
     JSONObject toJSONObject()throws JSONException;
}
