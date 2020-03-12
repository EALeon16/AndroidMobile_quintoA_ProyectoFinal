package com.example.cine_android.modelo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cine_android.R;
import com.example.cine_android.modelo.Cartelera;
import com.example.cine_android.modelo.Persona;

import java.util.List;

public class Persona_Adapter extends RecyclerView.Adapter<Persona_Adapter.ViewHolderCartelera> implements View.OnClickListener{
    List<Persona> lista;
    public View.OnClickListener cliclk;
    public Persona_Adapter(List<Persona> lista) {
        this.lista = lista;
    }
    @Override
    public ViewHolderCartelera onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_persona, null);
        view.setOnClickListener(this);
        return new ViewHolderCartelera(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCartelera viewHolderCartelera, int pos) {
        viewHolderCartelera.datoNombre.setText(lista.get(pos).getNombre());
        viewHolderCartelera.datoApellido.setText(lista.get(pos).getApellido());
        viewHolderCartelera.datoEmail.setText(lista.get(pos).getEmail());
        viewHolderCartelera.datoEdad.setText(lista.get(pos).getEdad()+"");
        viewHolderCartelera.datoDireccion.setText(lista.get(pos).getDireccion());
        viewHolderCartelera.datoCedula.setText(lista.get(pos).getCedula()+"");

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {
        if(cliclk != null){
            cliclk.onClick(v);
        }

    }
    public void setOnClick(View.OnClickListener listener){
        this.cliclk = listener;


    }

    public class ViewHolderCartelera extends RecyclerView.ViewHolder {
        TextView datoNombre;
        TextView datoApellido;
        TextView datoEmail;
        TextView datoEdad;
        TextView datoDireccion;
        TextView datoCedula;


        public ViewHolderCartelera(@NonNull View itemView) {
            super(itemView);
            datoNombre = itemView.findViewById(R.id.lblNombre);
            datoApellido = itemView.findViewById(R.id.lblApellidos);
            datoEmail = itemView.findViewById(R.id.lblemail);
            datoEdad = itemView.findViewById(R.id.lblEdad);
            datoDireccion =itemView.findViewById(R.id.lblDireccion);
            datoCedula =itemView.findViewById(R.id.lblCedula);
        }
    }
}
