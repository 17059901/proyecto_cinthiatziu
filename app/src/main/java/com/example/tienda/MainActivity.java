package com.example.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    EditText txtEmail = null;
    EditText txtPass = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPass);
        //txtEmail.setText("Hola Mundo");
    }

    public void login(View view){
        String email = txtEmail.getText().toString();
        String url=Global.url+"usuarioExiste.php?email="+txtEmail.getText()+"&pass="+txtPass.getText();
        if(email.equals("")  || !validateEmail(email)){

            Toast.makeText(this, "El email es incorrecto", Toast.LENGTH_SHORT).show();
        }
        else{
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                if(response.equals("0")){
                    Toast.makeText(this, "Usuario NO existe", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Usuario existe", Toast.LENGTH_SHORT).show();
                    Global.idUser = response;
                    Intent intent = new Intent(this, ListaCategorias.class);
                    startActivity(intent);
                }
            }, error -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            });
            queue.add(stringRequest);
        }
    }
    public boolean validateEmail(String email){
        return email.contains("@");
    }
}