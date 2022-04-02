package com.example.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaCategorias extends AppCompatActivity {
    ListView lvCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);

        Intent intent = getIntent();

        lvCategorias=(ListView)findViewById(R.id.lvCategorias);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, Global.cat);

        lvCategorias.setAdapter(adapter);



        lvCategorias.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
            String catSel = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(ListaCategorias.this, ListaProductos.class);

            intent.putExtra("catSel", catSel);
            startActivity(intent);
        }
    });
}

    public void btnVerCompras(android.view.View view){
        Intent intent = new Intent(ListaCategorias.this, ReporteVenta.class);
        startActivity(intent);
    }

}