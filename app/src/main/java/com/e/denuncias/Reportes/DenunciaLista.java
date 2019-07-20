package com.e.denuncias.Reportes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.e.denuncias.R;

import java.util.ArrayList;
import java.util.List;

public class DenunciaLista extends ArrayAdapter<Denuncia> {

    private Activity context;
    private List<Denuncia> denuncias=new ArrayList<>();

    public DenunciaLista( Activity context,List<Denuncia> denuncias) {
        super(context, R.layout.activity_layout_reportes_list,denuncias);
        this.context=context;
        this.denuncias=denuncias;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_layout_reportes_list, null, true);

        TextView titulo = (TextView) listViewItem.findViewById(R.id.titulo);

        Denuncia denuncia = denuncias.get(position);

        titulo.setText(denuncia.getTitulo());

        return listViewItem;
    }


}
