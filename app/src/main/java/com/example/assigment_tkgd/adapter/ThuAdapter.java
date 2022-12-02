package com.example.assigment_tkgd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assigment_tkgd.fragments.Khoanfragment;
import com.example.assigment_tkgd.fragments.ThuChiFragment;

public class ThuAdapter extends FragmentStateAdapter {
    public ThuAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
         if (position == 0)
             return new ThuChiFragment();
         return new Khoanfragment();

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
