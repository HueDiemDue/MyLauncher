package laucher.com.mylauncher.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Admin on 1/11/2018.
 */

public class PagesFragmentAdapter extends FragmentPagerAdapter {
    private final int NUM_PAGES = 4;
    private ArrayList<Fragment> lstPages = new ArrayList<>();
    private Context context;

    public PagesFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        return lstPages.get(position);
    }

    @Override
    public int getCount() {
        return lstPages.size();
    }

    public void addFragments(Fragment fragment) {
        this.lstPages.add(fragment);
    }


}
