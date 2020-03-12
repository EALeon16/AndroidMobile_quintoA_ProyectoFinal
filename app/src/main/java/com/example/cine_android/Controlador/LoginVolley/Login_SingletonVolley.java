package com.example.cine_android.Controlador.LoginVolley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Login_SingletonVolley {

    private RequestQueue queue;
    private Context context;
    private static Login_SingletonVolley miInstancia;

    public Login_SingletonVolley(Context context) {
        this.context = context;
        ////get RequestQueUqe
        queue = getRequestQue();

    }


    public RequestQueue getRequestQue(){

        if(queue == null)
            queue = Volley.newRequestQueue(context.getApplicationContext());
            return queue;


    }

    public static synchronized Login_SingletonVolley getInstance(Context context){
        if(miInstancia == null){
            miInstancia = new Login_SingletonVolley(context);
        }
        return miInstancia;
    }


    public <T> void addRquestque(Request request){
        queue.add(request);


    }
}
