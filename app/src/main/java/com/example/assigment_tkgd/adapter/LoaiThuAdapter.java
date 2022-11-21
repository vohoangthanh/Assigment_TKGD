package com.example.assigment_tkgd.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assigment_tkgd.R;
import com.example.assigment_tkgd.models.LoaiThu;

import java.util.ArrayList;

public class LoaiThuAdapter extends BaseAdapter {
    private ArrayList<LoaiThu> list;
    private Context context;

    public LoaiThuAdapter(ArrayList<LoaiThu> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewoffItem{
        TextView txtTen;
        ImageView ivSua,ivXoa,ivIcon;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        ViewoffItem viewoffItem;

        if (view == null ){
            viewoffItem = new ViewoffItem();
            view = inflater.inflate(R.layout.item_loaithu,viewGroup,false);

            viewoffItem.txtTen = view.findViewById(R.id.txtTen);
            viewoffItem.ivSua = view.findViewById(R.id.ivSua);
            viewoffItem.ivXoa = view.findViewById(R.id.ivXoa);
            viewoffItem.ivIcon = view.findViewById(R.id.ivIcon);
            view.setTag(viewoffItem);
        }else {
            viewoffItem = (ViewoffItem) view.getTag();
        }

        viewoffItem.txtTen.setText(list.get(i).getName());
        return view;
    }
}
