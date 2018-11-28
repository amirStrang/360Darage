package com.a360degree.a360darage.MainPages;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.a360degree.a360darage.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigation;
    private int mNavLastItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //lunch Default Fragment page
        LunchFragment(HomeFragment.class);
        //set Default nav item
        mBottomNavigation.setSelectedItemId(R.id.action_home);
        //save last fragment before transfer to add activity
        mNavLastItemId = R.id.action_home;
    }

    @Override
    protected void onResume() {
        //select last fragment before lunch add activity
        mBottomNavigation.setSelectedItemId(mNavLastItemId);
        super.onResume();
    }

    private void init() {

        //init bottom navigation
        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setItemIconTintList(null);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Class fragmentClass = null;
                    int itemId = menuItem.getItemId();
                    switch (itemId) {
                        case R.id.action_home:
                            fragmentClass = HomeFragment.class;
                            //save last fragment before transfer to add activity
                            mNavLastItemId = itemId;
                            break;
                        case R.id.action_search:
                            fragmentClass = SearchFragment.class;
                            //save last fragment before transfer to add activity
                            mNavLastItemId = itemId;
                            break;
                        case R.id.action_add:
                            transfer(AddActivity.class);
                            break;
                        case R.id.action_subscribes:
                            fragmentClass = SubscribesFragment.class;
                            //save last fragment before transfer to add activity
                            mNavLastItemId = itemId;
                            break;
                        case R.id.action_profile:
                            fragmentClass = ProfileFragment.class;
                            //save last fragment before transfer to add activity
                            mNavLastItemId = itemId;
                            break;
                    }

                    LunchFragment(fragmentClass);

                    return true;
                }
            };


    private void LunchFragment(Class fragmentClass) {

        Fragment fragment = null;

        try {
            if (fragmentClass != null) {
                fragment = (Fragment) fragmentClass.newInstance();
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_fragment_container, fragment)
//                    .addToBackStack(null)
                    .commit();
        }
    }

    private void transfer(Class dest) {
        //go to destination activity
        startActivity(new Intent(MainActivity.this, dest));
    }
}
