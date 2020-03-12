package com.example.cine_android.Controlador;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cine_android.Controlador.LoginVolley.ServicioWebvolleyLogin;
import com.example.cine_android.modelo.Cartelera;
import com.example.cine_android.modelo.Comprobante;
import com.example.cine_android.modelo.Persona;
import com.example.cine_android.vista.actividades.ActividadComprobante;
import com.example.cine_android.vista.actividades.ActividadPerfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicioWebvolleyCartelera {

    Context context;
    String get = "/cine/getCartelera";
    String getById = "/usuarios/getPersona";
    String host = "http://10.20.4.149:8080/cine";
    String com = "/getComprobante";


    String consulta = "";
    boolean estado;
    final public List<Persona> lista;
    public List<Comprobante> listaP;


    public ServicioWebvolleyCartelera(Context context) {
        this.context = context;
        lista = new ArrayList<>();
        listaP = new ArrayList<>();
    }

    public List<Comprobante> getbyidC(final String codigo) {
        String url = host.concat(com) + codigo;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // devuelve un json
                try {
                    Log.e("entro al catch", "entro al catch");
                    //Toast.makeText(context,  "estado: " + estado, Toast.LENGTH_SHORT).show();
                    JSONArray jsonUsuario = response.getJSONArray("data");
                    Log.e("jsonUsuario", "jsonUsuario" + jsonUsuario);
                    for (int i = 0; i < jsonUsuario.length(); i++) {
                        JSONObject jsonObject = jsonUsuario.getJSONObject(i);
                        //Toast.makeText(context, jsonObject.toString(), Toast.LENGTH_LONG  ).show();
                        Comprobante usuario = new Comprobante();
                        usuario.setCantN(jsonObject.getInt("cantidad_boletoN"));
                        usuario.setCantE(jsonObject.getInt("cantidad_boletoE"));
                        usuario.setCantTotal(jsonObject.getInt("total_boleto"));
                        usuario.setPelicula(jsonObject.getString("nombre_pelicula"));
                        usuario.setPrecio(jsonObject.getDouble("precio_total"));
                        usuario.setFecha(jsonObject.getString("fecha_pelicua"));
                        usuario.setHora(jsonObject.getString("hora_pelicula"));
                        usuario.setSala(jsonObject.getString("nombre_sala"));

                        listaP.add(usuario);
                        Log.e("Lista", listaP.size()+"");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("ERRORT", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRORR", error.getMessage());

            }
        }
        );
        Cartelera_SingletonVolley.getInstance(context).addRquestque(jsonObjectRequest);
        Log.e("Lista", listaP.size()+"");
        return listaP;
    }




    public List<Persona> getInfoC(final String codigo) {
        String url = "http://10.20.4.149:8080/usuarios/getPersona" + codigo;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // devuelve un json
                try {
                    Log.e("entro al catch", "entro al catch");
                    //Toast.makeText(context,  "estado: " + estado, Toast.LENGTH_SHORT).show();
                    JSONArray jsonUsuario = response.getJSONArray("data");
                    Log.e("jsonUsuario", "jsonUsuario" + jsonUsuario);
                    for (int i = 0; i < jsonUsuario.length(); i++) {
                        JSONObject jsonObject = jsonUsuario.getJSONObject(i);
                        //Toast.makeText(context, jsonObject.toString(), Toast.LENGTH_LONG  ).show();
                        Persona usuario = new Persona();
                        usuario.setNombre(jsonObject.getString("first_name"));
                        usuario.setApellido(jsonObject.getString("last_name"));
                        usuario.setEmail(jsonObject.getString("email"));
                        usuario.setEdad(jsonObject.getInt("edad"));
                        usuario.setCedula(jsonObject.getInt("cedula"));
                        usuario.setDireccion(jsonObject.getString("direccion"));

                        lista.add(usuario);
                        Log.e("Lista", listaP.size()+"");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("ERRORT", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRORR", error.getMessage());

            }
        }
        );
        Cartelera_SingletonVolley.getInstance(context).addRquestque(jsonObjectRequest);
        Log.e("Lista", lista.size()+"");
        return lista;
    }





}

