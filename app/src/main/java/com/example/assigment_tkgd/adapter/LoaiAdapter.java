package com.example.assigment_tkgd.adapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_tkgd.R;


public class LoaiAdapter extends RecyclerView.Adapter<LoaiAdapter.Vie> {






    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        ViewoffItem viewoffItem;

        if (view == null ){
            viewoffItem = new ViewoffItem();
            view = inflater.inflate(R.layout.item_loai,viewGroup,false);

            viewoffItem.txtTen = view.findViewById(R.id.txtTen);
            viewoffItem.ivSua = view.findViewById(R.id.ivSua);
            viewoffItem.ivXoa = view.findViewById(R.id.ivXoa);
            viewoffItem.ivIcon = view.findViewById(R.id.ivIcon);
            view.setTag(viewoffItem);
        }else {
            viewoffItem = (ViewoffItem) view.getTag();
        }

        viewoffItem.txtTen.setText(list.get(i).getTenLoai());
        return view;
    }
}
