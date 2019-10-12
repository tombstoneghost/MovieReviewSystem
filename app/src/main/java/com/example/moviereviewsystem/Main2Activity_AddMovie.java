package com.example.moviereviewsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main2Activity_AddMovie extends AppCompatActivity {

    String selectedItemText;

    EditText movieTitle, releaseYear, Description;
    RatingBar ratingBar;

    Button saveDetails, cancelButton;

    Spinner spinnerCategory;

    DatabaseReference databaseReference;

    HashMap<String,Object> movieDetails = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__add_movie);


        movieTitle=findViewById(R.id.movieTitle);
        releaseYear=findViewById(R.id.releaseYear);
        Description=findViewById(R.id.movieDescription);

        ratingBar=findViewById(R.id.ratingBar);

        saveDetails=findViewById(R.id.saveDetails);
        cancelButton=findViewById(R.id.cancelButton);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("movie_review_system");

        spinnerCategory = findViewById(R.id.spinnerCategory);


        String[] genres = new String[] {
                "Select a Genre",
                "Action",
                "Adventure",
                "Comedy",
                "Crime",
                "Drama",
                "Horror",
                "Mystery",
                "Political",
                "Religious",
                "Sci-Fi",
                "Thriller" };

        List<String> genreList = new ArrayList<>(Arrays.asList(genres));

//        ArrayAdapter<String>  spinnerArrayAdapter = new ArrayAdapter<String>(this,
//                , genreList);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, genreList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerArrayAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = movieTitle.getText().toString();
                int release = Integer.parseInt(releaseYear.getText().toString());
                float ratings = ratingBar.getRating();
                String description=Description.getText().toString();

                movieDetails.put("Title",title);
                movieDetails.put("ReleaseYear", release);
                movieDetails.put("Rating", ratings);
                movieDetails.put("Description",description);
                movieDetails.put("Category",selectedItemText);

                databaseReference.push().setValue(movieDetails);

                Toast.makeText(getApplicationContext(),"Data Saved Successfully", Toast.LENGTH_LONG).show();

                finish();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
