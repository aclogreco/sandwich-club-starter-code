package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Parses a JSON string and places the parsed data in a Sandwich object.
     * @param json JSON string
     * @return Returns a Sandwich object containing the JSON data
     * @throws JSONException Exception for JSON error
     */
    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich = new Sandwich();
        // Create JSON objects from the json string.
        JSONObject sandwichJsonObject = new JSONObject(json);
        JSONObject sandwichNameJsonObject = sandwichJsonObject.getJSONObject("name");

        // parse the main name
        sandwich.setMainName(sandwichNameJsonObject.getString("mainName"));

        // Create a list of strings from a JSON array for the list of a.k.a names.
        JSONArray alsoKnownAsJsonArray = sandwichNameJsonObject.getJSONArray("alsoKnownAs");
        List<String> alsoKnownAsList = new ArrayList<String>();
        if (alsoKnownAsJsonArray != null) {
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownAsList.add(alsoKnownAsJsonArray.getString(i));
            }
        }
        sandwich.setAlsoKnownAs(alsoKnownAsList);

        // parse the place of origin
        sandwich.setPlaceOfOrigin(sandwichJsonObject.getString("placeOfOrigin"));
        // parse the description
        sandwich.setDescription(sandwichJsonObject.getString("description"));
        // parse the image URL
        sandwich.setImage(sandwichJsonObject.getString("image"));

        // Create a list of strings from a JSON array for the list of ingredients.
        JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray("ingredients");
        List<String> ingredientsList = new ArrayList<String>();
        if (ingredientsJsonArray != null) {
            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                ingredientsList.add(ingredientsJsonArray.getString(i));
            }
        }
        sandwich.setIngredients(ingredientsList);

        return sandwich;
    }
}
