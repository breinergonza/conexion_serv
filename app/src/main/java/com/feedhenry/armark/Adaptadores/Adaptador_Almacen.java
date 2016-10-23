package com.feedhenry.armark.Adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feedhenry.armark.R;

/**
 * Created by ASUS on 20/10/2016.
 */
public class Adaptador_Almacen extends RecyclerView.Adapter<Adaptador_Almacen.ViewHolder> {

    private final Context contexto;
    private Cursor items;
    public OnItemClickListener escuchaAlmacen;

    public interface OnItemClickListener {
        public void onClick(ViewHolder holder, String idAlmacen);
    }

    public Adaptador_Almacen(Context contexto,OnItemClickListener escuchaAlmacen) {
        this.contexto = contexto;
        this.escuchaAlmacen = escuchaAlmacen;
    }


    @Override
    public Adaptador_Almacen.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_almacen,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Adaptador_Almacen.ViewHolder holder, int position) {
        items.moveToPosition(position);
        String s;

        // asignacion de ui
        s= items.getString(ConsultaAlmacen.RAZONSOCIAL);
        holder.viewRazonSocial.setText(s);

        s= items.getString(ConsultaAlmacen.DESCRIPCION);
        holder.viewDescripcion.setText(s);

        s= items.getString(ConsultaAlmacen.LOGO);
        Glide.with(contexto).load(s).centerCrop().into(holder.viewLogo);
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.getCount();
        }
        return 0;
    }

    public void swapCursor(Cursor nuevoCursor) {
        if (nuevoCursor != null) {
            items = nuevoCursor;
            notifyDataSetChanged();
        }
    }
    public Cursor getCursor() {
        return items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Tomamos las referencias ui
        private TextView viewRazonSocial,viewDescripcion;
        private ImageView viewLogo;

        public ViewHolder(View itemView) {
            super(itemView);

            viewRazonSocial = (TextView) itemView.findViewById(R.id.txt_razonSocial);
            viewDescripcion = (TextView)itemView.findViewById(R.id.txt_descripcion_almacen);
            viewLogo = (ImageView)itemView.findViewById(R.id.img_almcen);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            escuchaAlmacen.onClick(this,obtenerIdAlmacen(getAdapterPosition()));
        }
    }

    private String obtenerIdAlmacen(int adapterPosition) {
        if (items != null) {
            if (items.moveToPosition(adapterPosition)) {
                return items.getString(ConsultaAlmacen.ID_ALMACEN);
            }
        }

        return null;
    }

    interface ConsultaAlmacen {
        int ID_ALMACEN = 0;
        int IDWEB = 1;
        int RAZONSOCIAL = 2;
        int NIT = 3;
        int DESCRIPCION = 4;
        int DIRECCION = 5;
        int TELEFONO = 6;
        int CORREO= 7;
        int POSICIONGPS = 8;
        int LOGO = 9;
        int MARCADOR = 10;
        int REGISTRO = 11;
        int MODIFICADOR = 12;
        int VISIBLE = 13;
        int ACTIVO = 14;
        int TAGS = 15;
        int X = 16;
        int Y = 17;
    }
}
