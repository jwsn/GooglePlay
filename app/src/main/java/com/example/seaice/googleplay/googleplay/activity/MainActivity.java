package com.example.seaice.googleplay.googleplay.activity;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.seaice.googleplay.R;

import com.example.seaice.googleplay.googleplay.adapter.ViewPagerAdapter;
import com.example.seaice.googleplay.googleplay.fragment.BaseFragment;
import com.example.seaice.googleplay.googleplay.fragment.FragmentFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    //private ViewPagerAdapter pagerAdapter;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer)
    DrawerLayout drawerLayout;
    @InjectView(R.id.pager)
    ViewPager pager;
    @InjectView(R.id.pagertab)
    PagerTabStrip mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initView();
        initData();
    }

    @Override
    protected void initView() {
        setToolBar();
        setDrawerLayout();
        setViewPager();
    }

    //设置滑动页面
    private void setViewPager() {
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mIndicator.setTabIndicatorColorResource(R.color.colorPrimary);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                BaseFragment baseFragment = FragmentFactory.createFragment(position);
                baseFragment.show();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void initData() {
    }

    //设置action bar
    private void setToolBar() {
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("GooglePlay");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitleTextAppearance(this, R.style.ToolBarTitleTheme);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_am);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.ab_search) {
                    Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT);
                }
                return false;
            }
        });
    }
    //设置侧边连
    private void setDrawerLayout() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
    }
}
