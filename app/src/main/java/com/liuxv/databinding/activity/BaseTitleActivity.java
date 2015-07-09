package com.liuxv.databinding.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.liuxv.databinding.R;
import com.liuxv.databinding.fragment.BaseFragment;

/**
 *
 * @author liuxu@gmail.com (Liu Xu)
 */
public abstract class BaseTitleActivity extends AppCompatActivity {

  protected BaseFragment mFragment;

  private DrawerLayout drawerlayout;
  private NavigationView navigationView;


  protected abstract String getTitleText();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.explore_activity);
    initToolbar();
    initViews();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        drawerlayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  protected void replaceFragment(Fragment newFragment) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container, newFragment);
    transaction.commitAllowingStateLoss();
  }


  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (mFragment != null) {
      if (mFragment.onKeyDown(keyCode, event)) {
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
  }


  private void initToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.explore_menu);
    setTitle(getTitleText());
  }

  private void initViews() {
    drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);

    navigationView = (NavigationView) findViewById(R.id.navigation);
    navigationView
            .setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerlayout.closeDrawer(GravityCompat.START);
                return true;
              }
            });
  }

}
