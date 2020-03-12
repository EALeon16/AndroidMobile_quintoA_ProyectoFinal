package com.example.cine_android.vista.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cine_android.Controlador.ServicioWebvolleyCartelera;
import com.example.cine_android.MainActivity;
import com.example.cine_android.R;
import com.example.cine_android.modelo.Adapter.Comprobante_Adapter;
import com.example.cine_android.modelo.Comprobante;

import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ActividadComprobante extends AppCompatActivity {
    TextView ver;
    RecyclerView recyclerView;
    List<Comprobante> listaC;
    Comprobante_Adapter adapter;
    ServicioWebvolleyCartelera sw = new ServicioWebvolleyCartelera(this);
    String TAG = "GenerateQrCode";
    ImageView imageView;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_comprobante);
        recyclerView = findViewById(R.id.recyclerComprobante);
        imageView = findViewById(R.id.qe);

        Bundle datos = this.getIntent().getExtras();
        String verN = datos.getString("usuario");
        ver = findViewById(R.id.txtUser);
        ver.setText(verN);
        cargarLista(sw.getbyidC("/"+ verN));



    }

    public void cargarLista(List<Comprobante> lista){
        listaC = new ArrayList<Comprobante>();
        listaC = lista;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Comprobante_Adapter(listaC);
        adapter.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comprobante comprobante = listaC.get(recyclerView.getChildAdapterPosition(v));
                WindowManager manager = (WindowManager)getSystemService(WINDOW_SERVICE);
                Display display= manager.getDefaultDisplay();
                Point poitn = new Point();
                int width = poitn.x;
                int height = poitn.y;
                int small = width<height ? width:height;
                small = small*3/4;
                qrgEncoder = new QRGEncoder("Boletos Normales: " + comprobante.getCantN()+" \n" + "Boletos Especiales: " + comprobante.getCantE()+" \n"  + "Fecha de proyeccion: "
                        + comprobante.getFecha()+" \n" + "Hora de proyeccion: " + comprobante.getHora()+" \n" + "Sala: " + comprobante.getSala() +" \n" + "Pelicula"  + comprobante.getPelicula(),
                        null, QRGContents.Type.TEXT, small);
                try{
                    bitmap = qrgEncoder.encodeAsBitmap();
                    imageView.setImageBitmap(bitmap);

                }catch (Exception e){

                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu );
        return true;
    }


    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.idIniciarSeccion){
            intent = new Intent(ActividadComprobante.this, ActividadPerfil.class);
            Bundle datos = this.getIntent().getExtras();
            String verN = datos.getString("usuario");
            datos.putString("apellido", verN);
            intent.putExtras(datos);
            startActivity(intent);
        }else if (id == R.id.idRegistrarse){
            Intent intent1 = new Intent(ActividadComprobante.this, ActividadLogin.class);

            startActivity(intent1);
        }
        return true;

    }
}
