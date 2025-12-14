package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] lista_meses = getResources().getStringArray(R.array.lista_meses);
        ArrayAdapter<String> adapter =
                new  ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line,lista_meses);

    AutoCompleteTextView actvLista =
            findViewById(R.id.listaCategorias);
        actvLista.setAdapter(adapter);

        actvLista.setOnItemClickListener((parent, view, position, id) -> {
    String mes = parent.getItemAtPosition(position).toString();
TextView lblmesdirecionado = findViewById(R.id.lblmesdirecionado);
    lblmesdirecionado.setText("Mes Seleccionado: " + mes);


    //nuevo
            String[] lista_dias = getResources().getStringArray(R.array.lista_dias_semana);
            ArrayAdapter<String> adaptador2 =
                    new  ArrayAdapter<String>(this,
                            android.R.layout.simple_dropdown_item_1line,lista_dias);
            ListView lstOpciones = (ListView)findViewById(R.id.lstOpciones);
            View header = getLayoutInflater().inflate(
                    R.layout.lycabecera, null);
lstOpciones.addHeaderView(header);

            lstOpciones.setAdapter(adaptador2);

            lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(MainActivity.this,
                            "Item Seleccionado " + item,
                            Toast.LENGTH_SHORT).show();

                }

            });
            ;
});



    }


}