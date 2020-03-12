package com.example.cine_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
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
import com.example.cine_android.Controlador.ServicioWebvolleyCartelera;
import com.example.cine_android.modelo.Adapter.Cartelera_Adapter;
import com.example.cine_android.modelo.Cartelera;
import com.example.cine_android.vista.actividades.ActividadComprobante;
import com.example.cine_android.vista.actividades.ActividadLogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText usuario, clave;
    Button ingresar;
    StringRequest stringRequest;
    ProgressDialog progreso;
    String host = "http://10.20.4.149:8080";
    private String login = "/usuarios/ingresar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_login);
        cargarComponentes();

        /* webView =  (WebView) findViewById(R.id.webview);

        webView.loadUrl("file:///android_asset/principal.html");
         webView.setWebViewClient(new WebViewClient(){
             public boolean shouldOverrideUrlLoading(WebView view, String url){
                 return  false;
             }

         });*/



    }


    public void cargarComponentes(){
        usuario = findViewById(R.id.txtUsuario);
        clave = findViewById(R.id.txtClave);
        ingresar = findViewById(R.id.btnIngresar);
        ingresar.setOnClickListener(this);
    }

    public void Login(){

        String path = host.concat(login);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("response","response" + response);

                if(response.trim().equalsIgnoreCase("success")){

                    Toast.makeText(getApplicationContext(), "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(MainActivity.this, ActividadComprobante.class);
                    Bundle enviar = new Bundle();
                    String user = usuario.getText().toString();
                    enviar.putString("usuario", user);
                    intent.putExtras(enviar);
                    startActivity(intent);
                    usuario.setText("");
                    clave.setText("");

                }else{
                    Toast.makeText(getApplicationContext(), "Clave incorrecta", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();

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
        Cartelera_SingletonVolley.getInstance(this).addRquestque(stringRequest);

    }

    @Override
    public void onClick(View v) {
        Login();

    }



}