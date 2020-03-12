package com.example.cine_android.Controlador.LoginVolley;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cine_android.modelo.Cartelera;
import com.example.cine_android.modelo.Comprobante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicioWebvolleyLogin {

    Context context;
    String host = "http://192.168.0.109:8089/cine";
    String get = "/getCartelera";
    String com = "/getComprobante";


    String consulta = "";
    boolean estado;
    final public List<Cartelera> lista;
    final public  List<Comparable> listaC;




    public ServicioWebvolleyLogin(Context context) {
        this.context = context;
        lista = new ArrayList<>();
        listaC = new ArrayList<>();
    }













}

