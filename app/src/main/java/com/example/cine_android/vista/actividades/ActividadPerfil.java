package com.example.cine_android.vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cine_android.Controlador.Cartelera_SingletonVolley;
import com.example.cine_android.Controlador.ServicioWebvolleyCartelera;
import com.example.cine_android.R;
import com.example.cine_android.modelo.Adapter.Persona_Adapter;
import com.example.cine_android.modelo.Persona;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.activeandroid.Cache.getContext;

public class ActividadPerfil extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recycler;
    EditText ingresar, direccion;
    Persona_Adapter adapter;
    List<Persona> listaPersona;
    StringRequest stringRequest;
    Button editar;

    ServicioWebvolleyCartelera sw = new ServicioWebvolleyCartelera(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_perfil);
        cargarComponentes();
        Bundle datos = this.getIntent().getExtras();
        final String verN = datos.getString("usuario");
        Toast.makeText(this, "Usuario" + verN, Toast.LENGTH_SHORT).show();
        cargarLista(sw.getInfoC("/"+ verN));
        editar = findViewById(R.id.btnEditar);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e2 = editar.getText().toString();

                if(e2.length() == 0 ) {
                    Toast.makeText(ActividadPerfil.this, "Debes ingresar los campos" , Toast.LENGTH_SHORT).show();
                }else{

                    updateUsuario("/" + verN);
                }
            }
        });


    }

    public void cargarComponentes(){
        recycler = findViewById(R.id.recyclerPersona);
        ingresar = findViewById(R.id.txtEmail);
        direccion = findViewById(R.id.txtDireccion);


    }
    public void cargarLista(List<Persona> lista){
        listaPersona = new ArrayList<Persona>();
        listaPersona = lista;
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Persona_Adapter(listaPersona);
        adapter.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona usuario = listaPersona.get(recycler.getChildAdapterPosition(v));
                ingresar.setText(usuario.getEmail());
                direccion.setText(usuario.getDireccion());
            }
        });
        recycler.setAdapter(adapter);
    }

    private void updateUsuario(final String codigo) {


        String url="http://10.20.4.149:8080/usuarios/editPersona" + codigo;

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("entro al response", "entro al response");

                if (response.trim().equalsIgnoreCase("success")){
                    Log.e("entro al if", "enytrpo al if");
                    ingresar.setText("");
                    Toast.makeText(ActividadPerfil.this,"Usuario actualizado",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ActividadPerfil.this,"Usuario actualizado ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ","else"+response);
                    ingresar.setText("");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"No se ha podido conectar",Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String email=ingresar.getText().toString();
                String ed = direccion.getText().toString();

                //String imagen=convertirImgString(bitmap);

                Map<String,String> parametros=new HashMap<>();
                parametros.put("email",email);
                parametros.put("direccion",ed);
                //parametros.put("imagen",imagen);

                return parametros;
            }
        };
        //request.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Cartelera_SingletonVolley.getInstance(this).addRquestque(stringRequest);
    }

    @Override
    public void onClick(View v) {

    }
}
