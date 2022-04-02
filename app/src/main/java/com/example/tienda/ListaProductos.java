package com.example.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaProductos extends AppCompatActivity {
    ListView lvProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        lvProductos=(ListView)findViewById(R.id.lvProductos);
        Intent intent = getIntent();
        String catSel = intent.getStringExtra("catSel");

        String[] cat = {};;
        if(catSel.equals("Bebidas")){
            cat=Global.Bebidas;
        }
        if(catSel.equals("Galletas")){
            cat=Global.Galletas;
        }
        if(catSel.equals("Sabritas")){
            cat=Global.Sabritas;
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, cat);

        lvProductos.setAdapter(adapter);

        lvProductos.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String prodSel = (String) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), DetalleProducto.class);
                intent.putExtra("catSel", catSel);
                intent.putExtra("prodSel", prodSel);
                intent.putExtra("position", String.valueOf(position));
                startActivity(intent);
            }
        });
    }

    public void irReporteVenta(android.view.View view) {
        Intent intent = new Intent(this, ReporteVenta.class);
        startActivity(intent);
    }
}