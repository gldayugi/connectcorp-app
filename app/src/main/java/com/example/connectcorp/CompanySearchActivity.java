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

public class CompanySearchActivity extends AppCompatActivity {

    EditText companyName;
    Button searchBtn;

    String API_KEY = "https://api.apollo.io/api/v1/mixed_companies/search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_search);

        companyName = findViewById(R.id.companyName);
        searchBtn = findViewById(R.id.searchCompanyBtn);

        searchBtn.setOnClickListener(v -> searchCompany());
    }

    private void searchCompany() {

        ApolloApiService service =
                RetrofitClient.getClient().create(ApolloApiService.class);

        JSONObject json = new JSONObject();
        try {
            json.put("q_organization_name", companyName.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json"));

        Call<ResponseBody> call =
                service.searchCompanies(API_KEY, body);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(CompanySearchActivity.this,
                            "Company Results Retrieved",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CompanySearchActivity.this,
                            "API Error: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call,
                                  Throwable t) {
                Toast.makeText(CompanySearchActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}