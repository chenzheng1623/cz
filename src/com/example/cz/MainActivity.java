package com.example.cz;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.fragment.coursefragment;
import com.example.fragment.gradefragment;
import com.example.fragment.settingfragment;
import com.example.fragment.tablefragment;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private List<Fragment> listfragment = new ArrayList<Fragment>();
    private List<String> listtitle = new ArrayList<String>();
    private FragmentStatePagerAdapter adapter;
    private AlertDialog dialog;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private CoordinatorLayout rootlayout;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listtitle.add("课表查询");
        listtitle.add("选课查询");
        listtitle.add("成绩查询");
        listtitle.add("设置");
        initview();
        initdate();
        if (savedInstanceState != null) {
            pager.setCurrentItem(savedInstanceState.getInt("loc"));
        }
    }

    private void initdate() {
        adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return listfragment.size();
            }

            @Override
            public Fragment getItem(int position) {
                return listfragment.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return listtitle.get(position);
            }
        };
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(2);
//        tab.addTab(tab.newTab().setText("选课查询"));
//        tab.addTab(tab.newTab().setText("成绩查询"));
//        tab.addTab(tab.newTab().setText("设置"));
        //设置tab和viewpager协作
        tab.setupWithViewPager(pager);
    }

    private void initview() {
        pager = (ViewPager) findViewById(R.id.viewpager);
        listfragment.add(new tablefragment());
        listfragment.add(new coursefragment());
        listfragment.add(new gradefragment());
        listfragment.add(new settingfragment());
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        rootlayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        tab = (TabLayout) findViewById(R.id.tabLayout);
//        fab = (FloatingActionButton) findViewById(R.id.fabBtn);
//        fab.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(rootlayout, "Hello. I am Snackbar!", Snackbar.LENGTH_SHORT)
//                        .setAction("Undo", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                            }
//                        })
//                        .show();
//            }
//        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialog = new AlertDialog.Builder(MainActivity.this).create();
//            dialog.getWindow().setContentView(R.layout.bacdialog);
            View view = LayoutInflater.from(this).inflate(R.layout.bacdialog, null);
            dialog.setView(view, 0, 0, 0, 0);
//            Button button1 = (Button) dialog.getWindow().findViewById(R.id.tuichu1);
            Button button1 = (Button) view.findViewById(R.id.tuichu1);

            button1.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            Button button2 = (Button)view.findViewById(R.id.tuichu2);
            button2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("loc", pager.getCurrentItem());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
