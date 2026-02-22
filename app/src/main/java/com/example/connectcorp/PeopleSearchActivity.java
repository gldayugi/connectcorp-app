package com.example.connectcorp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleSearchActivity extends AppCompatActivity {

    EditText jobTitle, location;
    Button searchBtn;

    String API_KEY = "https://api.apollo.io/api/v1/mixed_people/api_search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_search);

        jobTitle = findViewById(R.id.jobTitle);
        location = findViewById(R.id.location);
        searchBtn = findViewById(R.id.searchPeopleBtn);

        searchBtn.setOnClickListener(v -> searchPeople());
    }

    private void searchPeople() {

        ApolloApiService service =
                RetrofitClient.getClient().create(ApolloApiService.class);

        JSONObject json = new JSONObject();
        try {
            json.put("q_keywords", jobTitle.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json"));

        Call<ResponseBody> call =
                service.searchPeople(API_KEY, body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Toast.makeText(PeopleSearchActivity.this,
                        "Results Retrieved",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call,
                                  Throwable t) {
                Toast.makeText(PeopleSearchActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}