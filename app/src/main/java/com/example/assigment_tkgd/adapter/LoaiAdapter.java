package com.example.assigment_tkgd.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_tkgd.R;
import com.example.assigment_tkgd.dao.LoaiDAO;
import com.example.assigment_tkgd.models.LoaiThu;

import java.util.ArrayList;

public class LoaiAdapter extends RecyclerView.Adapter<LoaiAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiThu> list;
    private LoaiDAO loaiDAO;
    private int trangThai;

    public LoaiAdapter(Context context, ArrayList<LoaiThu> list, LoaiDAO loaiDAO, int trangThai) {
        this.context = context;
        this.list = list;
        this.loaiDAO = loaiDAO;
        this.trangThai = trangThai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtID.setText(String.valueOf(list.get(position).getIdLoai()));
        holder.txtTenLoai.setText(list.get(position).getTenLoai());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDilogUpdate(list.get(holder.getAdapterPosition()));
            }
        });

        holder.ivDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = loaiDAO.delete(list.get(holder.getAdapterPosition()).getIdLoai());
                if (check){
                    Toast.makeText(context,"Delete thành công",Toast.LENGTH_LONG).show();
                    getDS();
                }else {
                    Toast.makeText(context,"Delete thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showDilogUpdate(LoaiThu l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_loai,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtenLoai = view.findViewById(R.id.edtTenLoai);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);

        txtTieuDe.setText("EDIT LOAI");
        edtenLoai.setText(l.getTenLoai());
        btnThem.setText("Cập Nhật");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = edtenLoai.getText().toString();
                LoaiThu loai = new LoaiThu(l.getIdLoai(),data,trangThai);
                boolean check = loaiDAO.update(loai);
                if (check){
                    Toast.makeText(context,"Edit thành công",Toast.LENGTH_LONG).show();
                    getDS();
                }else {
                    Toast.makeText(context,"Edit thất bại",Toast.LENGTH_LONG).show();
                }
                alertDialog.dismiss();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void getDS(){
        list.clear();
        list = loaiDAO.LayDSLoai(trangThai);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtID, txtTenLoai;
        ImageView ivIcon, ivEdit, ivDele;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDele = itemView.findViewById(R.id.ivDele);
        }
    }
}
