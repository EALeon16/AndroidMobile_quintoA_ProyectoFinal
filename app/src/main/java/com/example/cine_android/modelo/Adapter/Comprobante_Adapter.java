package com.example.cine_android.modelo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cine_android.R;
import com.example.cine_android.modelo.Cartelera;
import com.example.cine_android.modelo.Comprobante;

import java.util.List;

public class Comprobante_Adapter extends RecyclerView.Adapter<Comprobante_Adapter.ViewHolderUsuario> implements View.OnClickListener{
    List<Comprobante> lista;
    public View.OnClickListener cliclk;

    public Comprobante_Adapter(List<Comprobante> lista) {
        this.lista = lista;
    }
    @Override
    public ViewHolderUsuario onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comprobante, null);
        view.setOnClickListener(this);
        return new ViewHolderUsuario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUsuario viewHolderUsuario, int pos) {
        viewHolderUsuario.datoN.setText(lista.get(pos).getCantN()+"");
        viewHolderUsuario.datoE.setText(lista.get(pos).getCantE()+"");
        viewHolderUsuario.datoTB.setText(lista.get(pos).getCantTotal()+"");
        viewHolderUsuario.datoP.setText(lista.get(pos).getPelicula());
        viewHolderUsuario.PT.setText(lista.get(pos).getPrecio()+"");
        viewHolderUsuario.fecha.setText(lista.get(pos).getFecha());
        viewHolderUsuario.hors.setText(lista.get(pos).getHora());
        viewHolderUsuario.sala.setText(lista.get(pos).getSala());
        //viewHolderUsuario.datoImagen.setText(lista.get(pos).getImagen()+"");

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



    public class ViewHolderUsuario extends RecyclerView.ViewHolder {
        TextView datoN;
        TextView datoE;
        TextView datoTB;
        TextView datoP;
        TextView PT;
        TextView fecha;
        TextView hors;
        TextView sala;
        public ViewHolderUsuario(View itemView) {
            super(itemView);
            datoN = itemView.findViewById(R.id.lblctnN);
            datoE = itemView.findViewById(R.id.lblcantE);
            datoTB = itemView.findViewById(R.id.lblCanttotal);
            datoP = itemView.findViewById(R.id.lblPelicula);
            PT = itemView.findViewById(R.id.lblprecioT);
            fecha = itemView.findViewById(R.id.lblfecha);
            hors = itemView.findViewById(R.id.lblhora);
            sala = itemView.findViewById(R.id.lblSala);
            //datoImagen = itemView.findViewById(R.id.lblUsuarioImagen);
        }
    }
}