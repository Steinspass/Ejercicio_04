package com.example.ndpsh.ejercicio_04.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.ndpsh.ejercicio_04.Models.City;
import com.example.ndpsh.ejercicio_04.R;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class AddEditCityActivity extends AppCompatActivity {

    private int cityId;
    private boolean isCreation;

    private City city;
    private Realm realm;

    private EditText editTextCityName;
    private EditText editTextCityDesc;
    private EditText editTextCityLink;
    private ImageView cityImage;
    private Button btnPreview;
    private FloatingActionButton fab;
    private RatingBar ratingBarCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_city);

        realm = Realm.getDefaultInstance();
        bindUIReferences();

        // comprobar si va a ser una accion para editar o para creaccion
        if (getIntent().getExtras() != null) {
            cityId = getIntent().getExtras().getInt("id");
            isCreation = false;
        }else{
            isCreation = true;
        }
        setActivityTitle();

        if (!isCreation){
            city = getCityByID(cityId);
            binDataToFields();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditNewCity();
            }
        });

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = editTextCityLink.getText().toString();
                if (link.length() > 0)
                    loadImageLinkForPreview(editTextCityLink.getText().toString());
            }
        });
    }

    private void bindUIReferences(){
       editTextCityName = (EditText) findViewById(R.id.editTextCityName);
       editTextCityDesc = (EditText) findViewById(R.id.editTextCityDescription);
       editTextCityLink = (EditText) findViewById(R.id.editTextCityImage);
       cityImage = (ImageView) findViewById(R.id.imageViewPreview);
       btnPreview = (Button) findViewById(R.id.buttonPreview);
       fab = (FloatingActionButton) findViewById(R.id.FABEditCity);
       ratingBarCity = (RatingBar) findViewById(R.id.ratingBarCity);
    }

    private void setActivityTitle(){
        String title = "Edit City";
        if (isCreation) title = "Create New City";
        setTitle(title);
    }

    private City getCityByID(int cityId) {
        return realm.where(City.class).equalTo("id", cityId).findFirst();
    }

    private void binDataToFields() {
        editTextCityName.setText(city.getName());
        editTextCityDesc.setText(city.getSpecs());
        editTextCityLink.setText(city.getImage());
        loadImageLinkForPreview(city.getImage());
        ratingBarCity.setRating(city.getStars());
    }

    private void loadImageLinkForPreview(String link) {
        Picasso.get().load(link).fit().into(this.cityImage);
    }
    private boolean isValidDataForNewCity() {
        if (editTextCityName.getText().toString().length() > 0 &&
                editTextCityDesc.getText().toString().length() > 0&&
                editTextCityLink.getText().toString().length() > 0) {
            return true;
        }else{
            return false;
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(AddEditCityActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void addEditNewCity() {
        if (isValidDataForNewCity()) {
            String Name = editTextCityName.getText().toString();
            String specs = editTextCityDesc.getText().toString();
            String link = editTextCityLink.getText().toString();
            float stars = ratingBarCity.getRating();

            City city = new City(Name,specs,link,stars);
            // En caso de que sea una edicion en vez de creacion pasamos el ID
            if (!isCreation) city.setId(cityId);

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(city);
            realm.commitTransaction();

            goToMainActivity();
        }else{
            Toast.makeText(this, "The data is not valid, please check the fields again", Toast.LENGTH_SHORT).show();
        }
    }
}
