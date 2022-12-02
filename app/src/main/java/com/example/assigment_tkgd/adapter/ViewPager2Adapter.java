package com.example.assigment_tkgd.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assigment_tkgd.fragments.Khoanfragment;
import com.example.assigment_tkgd.fragments.LoaiFrament;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private int trangThai;

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, int trangThai) {
        super(fragmentActivity);
        this.trangThai = trangThai;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            LoaiFrament loaiFrament = new LoaiFrament();
            Bundle bundle = new Bundle();
            bundle.putInt("trangThai", trangThai);
            loaiFrament.setArguments(bundle);
            return loaiFrament;
        }

        return new Khoanfragment();

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
