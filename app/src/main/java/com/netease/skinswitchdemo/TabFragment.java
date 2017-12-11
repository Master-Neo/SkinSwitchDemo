package com.netease.skinswitchdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.netease.skinswitchdemo.skin.CustomLayoutInflaterFactory;
import com.netease.skinswitchdemo.skin.SkinManager;

/**
 * @author neo
 * @date 2017/11/21
 * Copyright 2017 NetEase. All rights reserved.
 */

public class TabFragment extends android.support.v4.app.Fragment {

    CustomLayoutInflaterFactory mFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) {
        return getActivity().getLayoutInflater();
    }

    private boolean switchSkin;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        final TabLayout tabLayout = rootView.findViewById(R.id.tl_tab);
        final Button bt = rootView.findViewById(R.id.bt_switch);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinManager.getInstance(getContext()).switch2SkinResource(getContext());
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
