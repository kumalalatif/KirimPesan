package com.kumalalatif.kirimpesan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.kumalalatif.kirimpesan.myAdapter.myMessageAdapter;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    public static String mainPrers = "file.main.Pesan";
    RecyclerView view;
    myMessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (RecyclerView) findViewById(R.id.RV_View);
        view.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences pref = getSharedPreferences(mainPrers,0);
        String dataMessage = pref.getString("Pesan","");
        try {
            JSONArray jsonArray = new JSONArray(dataMessage);
            messageAdapter = new myMessageAdapter(jsonArray);

            view.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("JSON", dataMessage);
    }

    public void addMessage(View view) {
        startActivity(new Intent(this, myFormActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hapus,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.hapus) {
            SharedPreferences sharedPreferences = getSharedPreferences(mainPrers,0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            finish();
            startActivity(getIntent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}