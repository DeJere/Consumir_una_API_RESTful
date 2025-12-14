package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Adapters.AdaptadorLugar;
import com.example.myapplication.models.LugarTuristico;
import com.example.myapplication.WebService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class actividad_lista_lugar extends AppCompatActivity
        implements WebService.Asynchtask {

    ListView lstOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad_lista_lugar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lstOpciones = findViewById(R.id.lstLugares);

        Map<String, String> datos = new HashMap<>();

        WebService ws = new WebService(
                "https://turismo.quevedoenlinea.gob.ec/lugar_turistico/json_getlistadoGridLT",
                datos,
                this,
                this
        );

        ws.execute("GET");
    }


    @Override
    public void processFinish(String result) {

        try {
            JSONObject json = new JSONObject(result);
            JSONArray jsonArray = json.getJSONArray("data");

            ArrayList<LugarTuristico> lista = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                String nombre = obj.getString("nombre_lugar");
                String descripcion = obj.getString("descripcion");

                lista.add(new LugarTuristico(nombre, descripcion));
            }

            AdaptadorLugar adaptador =
                    new AdaptadorLugar(this, lista.toArray(new LugarTuristico[0]));

            lstOpciones.setAdapter(adaptador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

