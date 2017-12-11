package com.netease.skinswitchdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.netease.skinswitchdemo.base.BaseActivity;
import com.netease.skinswitchdemo.skin.SkinManager;
import com.netease.skinswitchdemo.util.PreferenceUtil;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(PermissionChecker.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_main);

        findViewById(R.id.bt_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSkinResource = PreferenceUtil
                        .getBoolean(MainActivity.this, IConstants.KEY_SKIN_PREFERENCE, false);
                if (isSkinResource) {
                    SkinManager.getInstance(MainActivity.this).restore2DefaultResource(MainActivity.this);

                } else {
                    SkinManager.getInstance(MainActivity.this).switch2SkinResource(MainActivity.this);

                }
            }
        });

        findViewById(R.id.bt_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
                startActivity(i);
            }
        });

//        FragmentManager manager = getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction ft = manager.beginTransaction();
//        TabFragment fragment = new TabFragment();
//        ft.add(R.id.ll_container, fragment);
//        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
