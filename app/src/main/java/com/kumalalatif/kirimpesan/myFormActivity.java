package com.kumalalatif.kirimpesan;

import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.kumalalatif.kirimpesan.myAdapter.myImageSpinnerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class myFormActivity extends AppCompatActivity{

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText form_nama, form_pesan;
    Spinner spinner;

    String[] image_name = {"Latifah", "Diah", "Mala"};
    int[] image_picture = {R.drawable.mala,R.drawable.mala,R.drawable.mala};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getSupportActionBar().setTitle("Tambah Pesan");

        form_nama = (EditText) findViewById(R.id.namaDetail);
        form_pesan = (EditText) findViewById(R.id.pesanDetail);
        spinner = (Spinner) findViewById(R.id.imageDetail);
        preferences = getSharedPreferences(MainActivity.mainPrers,0);
        editor = preferences.edit();

        myImageSpinnerAdapter spinnerAdapter = new myImageSpinnerAdapter(this,image_picture,image_name);
        spinner.setAdapter(spinnerAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void send(View view) {
        int image = (int) spinner.getSelectedItem();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Nama Pengirim",form_nama.getText().toString());
            jsonObject.put("Isi Pesan",form_pesan.getText().toString());
            jsonObject.put("Waktu Kirim",new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            jsonObject.put("Foto",image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(preferences.contains("Pesan")) {
            String dataMessage = preferences.getString("Pesan","");

            try {
                JSONArray jsonArray = new JSONArray(dataMessage);
                jsonArray.put(jsonObject);
                editor.putString("Pesan", jsonArray.toString());
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            editor.putString("Pesan", jsonArray.toString());
            editor.apply();
        }

        finish();
    }
}
