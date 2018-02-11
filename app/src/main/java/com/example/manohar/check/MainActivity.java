package com.example.manohar.check;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.manohar.check.Fragment.engineer;
import com.example.manohar.check.Fragment.medical;
import com.example.manohar.check.Fragment.request;
import com.example.manohar.check.Fragment.upload;
import com.example.manohar.check.helper.BottomNavigationBehaviour;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Variables Used
    ExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<String>> listDataChild;
    public static TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Action and setting header
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Bottom Navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehaviour());
        loadFragment(new engineer());

        //Side navigation
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        //ViewPager Image slider main Page

        //Prepare data for expandableList
        expandableList = (ExpandableListView) findViewById(R.id.navigationmenu);
        prepareListData();

        //Set up expandable List Adapter
        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, expandableList);
        expandableList.setAdapter(mMenuAdapter);
        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {return false;}
        });
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {return false;}
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    fragment = new engineer();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_gifts:
                    fragment = new medical();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    fragment = new request();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    fragment = new upload();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding data header
        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName(getString(R.string.engineering));
        item1.setIconImg(R.drawable.ic_menu_engineering);
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName(getString(R.string.medical));
        item2.setIconImg(R.drawable.ic_menu_medical);
        listDataHeader.add(item2);

        ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setIconName(getString(R.string.aus));
        item3.setIconImg(R.drawable.ic_menu_library);
        listDataHeader.add(item3);

        ExpandedMenuModel item4 = new ExpandedMenuModel();
        item4.setIconName(getString(R.string.lpw));
        item4.setIconImg(R.drawable.ic_menu_setting);
        listDataHeader.add(item4);

        ExpandedMenuModel item5 = new ExpandedMenuModel();
        item5.setIconName(getString(R.string.cwo));
        item5.setIconImg(R.drawable.ic_menu_group);
        listDataHeader.add(item5);



        // Adding engineering branches
        List<String> heading1 = new ArrayList<>();
        heading1.add(getString(R.string.cvl));
        heading1.add(getString(R.string.cse));
        heading1.add(getString(R.string.eee));
        heading1.add(getString(R.string.ece));
        heading1.add(getString(R.string.ite));
        heading1.add(getString(R.string.ie));
        heading1.add(getString(R.string.me));

        // Adding medical branches
        List<String> heading2 = new ArrayList<>();
        heading2.add(getString(R.string.bams));
        heading2.add(getString(R.string.bds));
        heading2.add(getString(R.string.bph));
        heading2.add(getString(R.string.mbbs));

        List<String> heading3 = new ArrayList<>();
        List<String> heading4 = new ArrayList<>();

        //Adding other options
        List<String> heading5 = new ArrayList<>();
        heading5.add(getString(R.string.rgstr));
        heading5.add(getString(R.string.lgn));
        heading5.add(getString(R.string.prst));
        heading5.add(getString(R.string.grps));
        heading5.add(getString(R.string.mbr));

        //Adding header list
        listDataChild.put(listDataHeader.get(0), heading1);
        listDataChild.put(listDataHeader.get(1), heading2);
        listDataChild.put(listDataHeader.get(2), heading3);
        listDataChild.put(listDataHeader.get(3), heading4);
        listDataChild.put(listDataHeader.get(4), heading5);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_engineer) {
            // Handle the camera action
        } else if (id == R.id.nav_medical) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
