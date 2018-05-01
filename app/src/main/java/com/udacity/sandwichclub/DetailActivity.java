package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;

        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            closeOnError();
            return;
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method uses the sandwich data to populate the views in the UI.
     * @param sandwich Object containing the sandwich data.
     */
    private void populateUI(Sandwich sandwich) {
        // Get a reference to all of the Views in the layout.
        ImageView sandwichImageIV = findViewById(R.id.image_iv);
        TextView originTV = findViewById(R.id.origin_tv);
        TextView alsoKnownAsTV = findViewById(R.id.also_known_as_tv);
        TextView ingredientsTV = findViewById(R.id.ingredients_tv);
        TextView descriptionTV = findViewById(R.id.description_tv);

        // Load the sandwich image.
        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.drawable.ic_error_outline_black_48px)
                .into(sandwichImageIV);

        // Set the origin text.
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            originTV.setText(R.string.string_unknown_origin);
        } else {
            originTV.setText(sandwich.getPlaceOfOrigin());
        }

        // Convert the list of a.k.a. names into a string.
        // Use that string for alsoKnownAsTV's text.
        List<String> akaList = sandwich.getAlsoKnownAs();
        StringBuilder akaNames = new StringBuilder("");
        for (String akaName : akaList) {
            akaNames.append("  * ");
            akaNames.append(akaName);
            akaNames.append("\n");
        }
        alsoKnownAsTV.setText(akaNames.toString());

        // Convert the list of ingredients into a string.
        // Use that string for ingredientsTV's text.
        List<String> ingredientsList = sandwich.getIngredients();
        StringBuilder ingredients = new StringBuilder("");
        for (String ingredient : ingredientsList) {
            ingredients.append("  * ");
            ingredients.append(ingredient);
            ingredients.append("\n");
        }
        ingredientsTV.setText(ingredients.toString());

        // Set the description text.
        descriptionTV.setText(sandwich.getDescription());

        // Set the Detail activity's title to the main sandwich name.
        setTitle(sandwich.getMainName());
    }
}
