package com.example.projectdam;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class UniversitateAdapter extends BaseAdapter {
    private Context context;
    private List<Universitate> universitati;

    public UniversitateAdapter(Context context, List<Universitate> universitati) {
        this.context = context;
        this.universitati = universitati;
    }

    @Override
    public int getCount() {
        return universitati.size();
    }

    @Override
    public Object getItem(int position) {
        return universitati.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_layout, null);
        }

        Universitate universitate = universitati.get(position);
        TextView numeTextView = view.findViewById(R.id.nume);
        numeTextView.setText(universitate.getNume());

        if (position % 2 == 0) {
            view.setBackgroundColor(Color.parseColor("#00D2FF"));
        } else {
            view.setBackgroundColor(Color.parseColor("#9874a7"));
        }

        return view;
    }
}
