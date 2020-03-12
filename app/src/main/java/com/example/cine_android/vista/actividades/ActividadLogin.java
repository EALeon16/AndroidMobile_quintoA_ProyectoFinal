package com.example.cine_android.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cine_android.Controlador.Cartelera_SingletonVolley;
import com.example.cine_android.Controlador.LoginVolley.Login_SingletonVolley;
import com.example.cine_android.MainActivity;
import com.example.cine_android.R;

import java.util.HashMap;
import java.util.Map;

import static com.activeandroid.Cache.getContext;

public class ActividadLogin extends AppCompatActivity implements View.OnClickListener{

    EditText usuario, clave;
    Button ingresar;
    StringRequest stringRequest;
    ProgressDialog progreso;
    String host = "http://192.168.0.109:8089";
    private String login = "/usuarios/ingresar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_login);
        cargarComponentes();

    }

    public void cargarComponentes(){
        usuario = findViewById(R.id.txtUsuario);
        clave = findViewById(R.id.txtClave);
        ingresar = findViewById(R.id.btnIngresar);
        ingresar.setOnClickListener(this);
        Bundle datos = this.getIntent().getExtras();
        String verN = datos.getString("usuario");
        Toast.makeText(this, "--" + verN, Toast.LENGTH_SHORT).show();



    }

    public void Login(){

        String path = host.concat(login);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("response","response" + response);

                if(response.trim().equalsIgnoreCase("success")){

                    Toast.makeText(getApplicationContext(), "Login Succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(ActividadLogin.this, MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Clave incorrecta", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Usuario incorrecta", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", usuario.getText().toString().trim());
                params.put("password", clave.getText().toString().trim());
                return  params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Cartelera_SingletonVolley.getInstance(getContext()).addRquestque(stringRequest);

    }

    @Override
    public void onClick(View v) {
        Login();
    }
}
