package com.example.backgroundservices.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.backgroundservices.fragment.AsyncTaskFragment;
import com.example.backgroundservices.fragment.ServiceFragment;
import com.example.backgroundservices.fragment.ThreadFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new ThreadFragment();
        }
        else if (position == 1)
        {
            fragment = new AsyncTaskFragment();
        }
        else if (position == 2)
        {
            fragment = new ServiceFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = null;
        if (position == 0)
        {
            title = "Thread";
        }
        else if (position == 1)
        {
            title = "Async Task";
        }
        else if (position == 2)
        {
            title = "Service";
        }
        return title;
    }
}
