package com.example.matheus.app_maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

/**
 * Created by Matheus on 12/05/2016.
 */
public class EnviaDadosActivity extends Activity {


    String descricao,tipo;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envia_dados);

        EditText e1 = (EditText) findViewById(R.id.editText);
        Button btn = (Button) findViewById(R.id.submit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.tipo_ocorrencia, android.R.layout.simple_spinner_item);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setAdapter(adapter);

        descricao = e1.getText().toString();
        tipo = spin.getSelectedItem().toString();
    }


    public void conluirOcorrencia (View v){
        GPSTracker gps = new GPSTracker(EnviaDadosActivity.this);
        if(gps.canGetLocation()) {
            Intent intent = getIntent();
            intent.putExtra("lat",gps.getLatitude());
            intent.putExtra("long",gps.getLongitude());
            intent.putExtra("desc",descricao);
            intent.putExtra("tipo",tipo);
            setResult(2, intent);
            finish();
        }else{
            gps.showSettingsAlert();
        }
    }

}
