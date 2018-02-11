package com.example.manohar.check;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BranchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<String>> listDataChild;
    public static TextView book_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_branch);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_branch);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Select Semester", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_branch);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_branch);
        //navigationView.setNavigationItemSelectedListener(this);
        expandableList = (ExpandableListView) findViewById(R.id.navigationmenu_branch);

        //Prepare data for expandableList
        prepareListData();

        //Set up expandable List Adapter
        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, expandableList);
        expandableList.setAdapter(mMenuAdapter);
        //on selecting submenu
        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                //Engineering Branches
                if(i==1){
                    Intent intent = new Intent(view.getContext(), BranchActivity.class);
                    switch (i1){
                        case 0:
                            intent.putExtra("Branch", "CIVIL ENGINEERING");
                            startActivity(intent);
                            break;
                        case 1:
                            intent.putExtra("Branch", "COMPUTER SCIENCE");
                            startActivity(intent);
                            break;
                        case 2:
                            intent.putExtra("Branch", "ELECTRICAL AND ELECTRONICS");
                            startActivity(intent);
                            break;
                        case 3:
                            intent.putExtra("Branch", "ELECTRONICS AND COMMUNICATION");
                            startActivity(intent);
                            break;
                        case 4:
                            intent.putExtra("Branch", "INFORMATION TECHNOLOGY");
                            startActivity(intent);
                            break;
                        case 5:
                            intent.putExtra("Branch", "INSTRUMENTATION");
                            startActivity(intent);
                            break;
                        case 6:
                            intent.putExtra("Branch", "MECHANICAL");
                            startActivity(intent);
                            break;
                        default:
                            Toast.makeText(BranchActivity.this, "Wrong Selection!",
                                    Toast.LENGTH_SHORT).show();
                    }

                }
                //Medical branches
                if(i==2){
                    Intent intent = new Intent(view.getContext(), BranchActivity.class);
                    switch (i1){
                        case 0:
                            intent.putExtra("Branch", "B.A.M.S");
                            startActivity(intent);
                            break;
                        case 1:
                            intent.putExtra("Branch", "B.D.S");
                            startActivity(intent);
                            break;
                        case 2:
                            intent.putExtra("Branch", "B.PHARMACY");
                            startActivity(intent);
                            break;
                        case 3:
                            intent.putExtra("Branch", "M.B.B.S");
                            startActivity(intent);
                            break;
                        default:
                            Toast.makeText(BranchActivity.this, "Wrong Selection!",
                                    Toast.LENGTH_SHORT).show();
                    }

                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_branch);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //on selecting menu
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                //Log.d("DEBUG", "heading clicked");
                return false;
            }
        });


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("Branch");
        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.branch_name);
        textView.setText(message);
        //fadeIn animation
        final Animation fadeIn = new AlphaAnimation(0.0f,1.0f);
        fadeIn.setDuration(300);
        //delay to set visible
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView) findViewById(R.id.branch_name);
                textView.startAnimation(fadeIn);
                textView.setVisibility(View.VISIBLE);
                textView.scrollBy(0,30);
            }
        },500);

        book_title = (TextView)findViewById(R.id.book_title);
        fetchData fetchData = new fetchData();
        fetchData.execute("http://xoombook.com/wp-json/wp/v2/posts/?filter[category_name]=android&per_page=100&fields=id,date,link,title");

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding data header
        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName(getString(R.string.home));
        item1.setIconImg(R.drawable.ic_home);
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName(getString(R.string.engineering));
        item2.setIconImg(R.drawable.ic_menu_engineering);
        listDataHeader.add(item2);

        ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setIconName(getString(R.string.medical));
        item3.setIconImg(R.drawable.ic_menu_medical);
        listDataHeader.add(item3);

        ExpandedMenuModel item4 = new ExpandedMenuModel();
        item4.setIconName(getString(R.string.aus));
        item4.setIconImg(R.drawable.ic_menu_library);
        listDataHeader.add(item4);

        ExpandedMenuModel item5 = new ExpandedMenuModel();
        item5.setIconName(getString(R.string.lpw));
        item5.setIconImg(R.drawable.ic_menu_setting);
        listDataHeader.add(item5);

        ExpandedMenuModel item6 = new ExpandedMenuModel();
        item6.setIconName(getString(R.string.cwo));
        item6.setIconImg(R.drawable.ic_menu_group);
        listDataHeader.add(item6);

        List<String> heading1 = new ArrayList<>();

        // Adding engineering branches
        List<String> heading2 = new ArrayList<>();
        heading2.add(getString(R.string.cvl));
        heading2.add(getString(R.string.cse));
        heading2.add(getString(R.string.eee));
        heading2.add(getString(R.string.ece));
        heading2.add(getString(R.string.ite));
        heading2.add(getString(R.string.ie));
        heading2.add(getString(R.string.me));

        // Adding medical branches
        List<String> heading3 = new ArrayList<>();
        heading3.add(getString(R.string.bams));
        heading3.add(getString(R.string.bds));
        heading3.add(getString(R.string.bph));
        heading3.add(getString(R.string.mbbs));

        List<String> heading4 = new ArrayList<>();
        List<String> heading5 = new ArrayList<>();

        List<String> heading6 = new ArrayList<>();
        heading6.add(getString(R.string.rgstr));
        heading6.add(getString(R.string.lgn));
        heading6.add(getString(R.string.prst));
        heading6.add(getString(R.string.grps));
        heading6.add(getString(R.string.mbr));

        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), heading2);
        listDataChild.put(listDataHeader.get(2), heading3);
        listDataChild.put(listDataHeader.get(3), heading4);
        listDataChild.put(listDataHeader.get(4), heading5);
        listDataChild.put(listDataHeader.get(5), heading6);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_branch);
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_branch);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
