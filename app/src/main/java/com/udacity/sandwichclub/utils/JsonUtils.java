package com.udacity.sandwichclub.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.udacity.sandwichclub.model.Sandwich;

public class JsonUtils {

    /**
     * Parses a JSON string and places the parsed data in a Sandwich object.
     * @param json JSON string
     * @return Returns a Sandwich object containing the JSON data
     * @throws JSONException Exception for JSON error
     */
    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich = new Sandwich();
        JSONObject sandwichJsonObject = new JSONObject(json);
        JSONObject sandwichNameJsonObject = sandwichJsonObject.getJSONObject("name");

        sandwich.setMainName(sandwichNameJsonObject.getString("mainName"));

        JSONArray alsoKnownAsJsonArray = sandwichNameJsonObject.getJSONArray("alsoKnownAs");
        List<String> alsoKnownAsList = new ArrayList<String>();
        if (alsoKnownAsJsonArray != null) {
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownAsList.add(alsoKnownAsJsonArray.getString(i));
            }
        }
        sandwich.setAlsoKnownAs(alsoKnownAsList);

        sandwich.setPlaceOfOrigin(sandwichJsonObject.getString("placeOfOrigin"));
        sandwich.setDescription(sandwichJsonObject.getString("description"));
        sandwich.setImage(sandwichJsonObject.getString("image"));

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
