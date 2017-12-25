package com.netease.skinswitchdemo.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

/**
 * @author neo
 * @date 2017/12/25
 * Copyright 2017 NetEase. All rights reserved.
 */

public class BaseFragment extends Fragment {
    @Override
    public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) {
        return getActivity().getLayoutInflater();
    }
}
