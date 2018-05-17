package com.udacity.sandwichclub.utils;

import android.content.Context;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Parses a JSON string and places the parsed data in a Sandwich object.
     * @param context Context
     * @param json JSON string
     * @return Returns a Sandwich object containing the JSON data
     * @throws JSONException Exception for JSON error
     */
    public static Sandwich parseSandwichJson(Context context, String json) throws JSONException {
        Sandwich sandwich = new Sandwich();
        // Create JSON objects from the json string.
        JSONObject sandwichJsonObject = new JSONObject(json);
        JSONObject sandwichNameJsonObject =
                sandwichJsonObject.getJSONObject(
                        context.getString(R.string.sandwich_json_name_key));

        // parse the main name
        sandwich.setMainName(
                sandwichNameJsonObject.optString(
                        context.getString(R.string.sandwich_json_name_mainName_key)));

        // Create a list of strings from a JSON array for the list of a.k.a names.
        JSONArray alsoKnownAsJsonArray = sandwichNameJsonObject.getJSONArray(
                context.getString(R.string.sandwich_json_name_aka_key));
        List<String> alsoKnownAsList = new ArrayList<String>();
        if (alsoKnownAsJsonArray != null) {
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownAsList.add(alsoKnownAsJsonArray.optString(i));
            }
        }
        sandwich.setAlsoKnownAs(alsoKnownAsList);

        // parse the place of origin
        sandwich.setPlaceOfOrigin(
                sandwichJsonObject.optString(
                        context.getString(R.string.sandwich_json_placeOfOrigin_key)));
        // parse the description
        sandwich.setDescription(
                sandwichJsonObject.optString(
                        context.getString(R.string.sandwich_json_desciption_key)));
        // parse the image URL
        sandwich.setImage(
                sandwichJsonObject.optString(
                        context.getString(R.string.sandwich_json_image_key)));

        // Create a list of strings from a JSON array for the list of ingredients.
        JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray(
                context.getString(R.string.sandwich_json_ingredients_key));
        List<String> ingredientsList = new ArrayList<String>();
        if (ingredientsJsonArray != null) {
            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                ingredientsList.add(ingredientsJsonArray.optString(i));
            }
        }
        sandwich.setIngredients(ingredientsList);

        return sandwich;
    }
}
