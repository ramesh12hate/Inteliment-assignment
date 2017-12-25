package com.example.rramesh.assignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rramesh.assignment.databinding.ActivityScenarioTwoBinding;
import com.example.rramesh.interfaces.Api;
import com.example.rramesh.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * By Ramesh Barahate
 * Description : For Api call and data fetching using Retrofit
 */

public class ScenarioTwoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    // variable declaration
    private List<String> mArrayCity = new ArrayList<>();
    private List<City> mCityList;
    private ProgressDialog mProgressDialog;
    private ActivityScenarioTwoBinding mActivityScenarioTwoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityScenarioTwoBinding = DataBindingUtil.setContentView(this, R.layout.activity_scenario_two);
        mActivityScenarioTwoBinding.city.setOnItemSelectedListener(this);
        isNetworkAvailable();
    }


    /**
     * getCities
     * Description : Fetch data from URL using Retrofit GET request
     */
    private void getCities(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
        mProgressDialog.setCancelable(false);
        mActivityScenarioTwoBinding.btnOpenMap.setOnClickListener(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<City>> call = api.getCities();

        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                //In this point we got our hero list
                //thats damn easy right ;)
                mCityList = response.body();
                for(int i = 0; i < mCityList.size(); i++){
                    mArrayCity.add(mCityList.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, mArrayCity);
                mActivityScenarioTwoBinding.city.setAdapter(adapter);
                mProgressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });
    }

    /**
     * isNetworkAvailable
     * Description : Check Network connection available or not
     * and redirect accordingly
     */
    public void isNetworkAvailable() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            new AlertDialog.Builder(this).setMessage(getString(R.string.network_msg))
                    .setTitle(getString(R.string.network_title))
                    .setCancelable(false)
                    .setNeutralButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton){
                                    finish();
                                }
                            })
                    .show();
        }else
            getCities();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        for(int i = 0; i < mCityList.size(); i++){
            if(mActivityScenarioTwoBinding.city.getSelectedItem().toString().equalsIgnoreCase(mCityList.get(i).getName())){
                String str = getString(R.string.from_central) + "\n";
                if(mCityList.get(i).getFromCentral().car != null){
                    str += getString(R.string.by_car) + " " + mCityList.get(i).getFromCentral().car +"\n";
                }

                if(mCityList.get(i).getFromCentral().train != null){
                    str += getString(R.string.by_train) + " " + mCityList.get(i).getFromCentral().train;
                }
                mActivityScenarioTwoBinding.tvMode.setText(str);

                ((TextView) view).setTextColor(Color.BLACK);
                break;
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_open_map:
                // To Open Google Map
                for(int i = 0; i < mCityList.size(); i++) {
                    if (mActivityScenarioTwoBinding.city.getSelectedItem().toString().equalsIgnoreCase(mCityList.get(i).getName())) {
                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", mCityList.get(i).getLocation().latitude, mCityList.get(i).getLocation().longitude);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(intent);
                        break;
                    }
                }
                break;
        }
    }
}
