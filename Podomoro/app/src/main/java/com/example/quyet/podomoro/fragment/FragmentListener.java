package com.example.quyet.podomoro.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by quyetdv on 2/14/2017.
 */

public interface FragmentListener {
    void replaceFragment(Fragment fragment, boolean addToBackStack);
}
