package com.example.quyet.podomoro.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by quyetdv on 2/16/2017.
 */

public interface TaskFragmentListener {
    void onChangeFragment(Fragment frag, boolean addToBackStack);
}
