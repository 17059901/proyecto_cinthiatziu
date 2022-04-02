package com.example.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DetalleProducto extends AppCompatActivity {

    ImageView imgProducto;
    TextView tvProducto,tvTotalDetalle;
    Button btnMenos, btnMas;
    float precio=0;
    int cantidad=0;
    String catSel;
    String prodSel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        Intent intent = getIntent();
        prodSel = intent.getStringExtra("prodSel");
        catSel = intent.getStringExtra("catSel");
        String position = intent.getStringExtra("position");
        imgProducto = findViewById(R.id.imgProducto);
        tvProducto = findViewById(R.id.tvProducto);
        btnMenos = findViewById(R.id.btnMenos);
        btnMas = findViewById(R.id.btnMas);
        tvTotalDetalle = findViewById(R.id.tvTotalDetalle);
        if (prodSel.contains("Cocacola")) {
            imgProducto.setImageResource(R.mipmap.cocacola);
        }
        if (prodSel.contains("Fanta")) {
            imgProducto.setImageResource(R.mipmap.fanta);
        }
        if (prodSel.contains("Sprite")) {
            imgProducto.setImageResource(R.mipmap.sprite);
        }
        if (prodSel.contains("Emperador chocolate")) {
            imgProducto.setImageResource(R.mipmap.emperador_chocolate);
        }
        if (prodSel.contains("Emperador limon")) {
            imgProducto.setImageResource(R.mipmap.emperador_limon);
        }
        if (prodSel.contains("Emperador nuez")) {
            imgProducto.setImageResource(R.mipmap.emperador_nuez);
        }
        if (prodSel.contains("Doritos")) {
            imgProducto.setImageResource(R.mipmap.doritos);
        }
        if (prodSel.contains("Rufles")) {
            imgProducto.setImageResource(R.mipmap.rufles);
        }
        if (prodSel.contains("Chetos")) {
            imgProducto.setImageResource(R.mipmap.chetos);
        }

        float[] cat = {};;
        if(catSel.equals("Bebidas")){
            cat=Global.precioBebidas;
        }
        if(catSel.equals("Galletas")){
            cat=Global.precioGalletas;
        }
        if(catSel.equals("Sabritas")){
            cat=Global.precioSabritas;
        }
        precio=cat[Integer.parseInt(position)];
        tvProducto.setText(prodSel+" $"+precio);
    }
    public void botonMas(View view)
    {
        cantidad++;
        float subTotal=precio*cantidad;
        tvTotalDetalle.setText(cantidad+" x $"+precio+": $"+String.valueOf(subTotal));
    }
    public void botonMenos(View view)
    {
        cantidad--;
        if(cantidad<=0){
            cantidad=0;
            tvTotalDetalle.setText("Sub total: ");
            Toast.makeText(this, "Debe de haber por lo menos un producto", Toast.LENGTH_SHORT).show();
        }else{
            float subTotal=precio*cantidad;
            tvTotalDetalle.setText(cantidad+" x $"+precio+": $"+String.valueOf(subTotal));
        }
    }
    public void botonCancelar(View view)
    {

        Intent intent = new Intent(this, ListaProductos.class);
        startActivity(intent);
    }
    public void botonProcesar(View view)
    {
        if (cantidad==0){
            Toast.makeText(this, "Debe de haber por lo menos un producto", Toast.LENGTH_SHORT).show();
        }
        else {
            String url=Global.url+"insertarDetalle.php?idUser="+Global.idUser+"&categoria="+catSel+"&producto="+prodSel+"&cantidad="+cantidad+"&precioUnitario="+precio+"&total="+precio*cantidad;
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                if(response.equals("0")){
                    Toast.makeText(this, "Datos no guardado", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ReporteVenta.class);
                    startActivity(intent);
                }
            }, error -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            });
            queue.add(stringRequest);
        }
    }
}