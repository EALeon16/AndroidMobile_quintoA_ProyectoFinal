package com.example.cine_android.modelo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cine_android.R;
import com.example.cine_android.modelo.Cartelera;

import java.util.List;

public class Cartelera_Adapter extends RecyclerView.Adapter<Cartelera_Adapter.ViewHolderCartelera>{
    List<Cartelera> lista;
    public Cartelera_Adapter(List<Cartelera> lista) {
        this.lista = lista;
    }
    @Override
    public ViewHolderCartelera onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cartelera_usuario, null);

        return new ViewHolderCartelera(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCartelera viewHolderCartelera, int pos) {
        viewHolderCartelera.datoHora.setText(lista.get(pos).getHora_pelicula());
        viewHolderCartelera.datoFecha.setText(lista.get(pos).getFecha_pelicua());
        viewHolderCartelera.datoId.setText(lista.get(pos).getPelicula_id());
        viewHolderCartelera.datoSala.setText(lista.get(pos).getSala_id());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderCartelera extends RecyclerView.ViewHolder {
        TextView datoHora;
        TextView datoFecha;
        TextView datoId;
        TextView datoSala;

        public ViewHolderCartelera(@NonNull View itemView) {
            super(itemView);
            datoHora = itemView.findViewById(R.id.lblhora);
            datoFecha = itemView.findViewById(R.id.lblfecha);
            datoId = itemView.findViewById(R.id.lblPeliculaId);
            datoSala = itemView.findViewById(R.id.lblSalaId);
        }
    }
}
