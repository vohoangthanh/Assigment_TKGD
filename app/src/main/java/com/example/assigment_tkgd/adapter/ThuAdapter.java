package com.example.assigment_tkgd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assigment_tkgd.fragments.KhoanThufragment;
import com.example.assigment_tkgd.fragments.LoaiThufragment;

public class ThuAdapter extends FragmentStateAdapter {
    public ThuAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
         if (position == 0)
             return new LoaiThufragment();
         return new KhoanThufragment();

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
