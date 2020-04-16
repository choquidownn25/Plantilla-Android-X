package com.example.plantilla.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
// import android.support.annotation.NonNull;
// import android.support.design.widget.FloatingActionButton;
// import android.support.design.widget.NavigationView;
// import android.support.design.widget.Snackbar;
// import android.support.design.widget.TabLayout;
// import android.support.v4.app.FragmentPagerAdapter;
// import android.support.v4.view.GravityCompat;
// import android.support.v4.view.ViewPager;
// import android.support.v4.widget.DrawerLayout;
// import android.support.v7.app.ActionBarDrawerToggle;
// import android.support.v7.app.AppCompatActivity;
// import android.support.v7.app.AppCompatDelegate;
// import android.support.v7.widget.RecyclerView;
// import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

// import android.support.v4.app.Fragment;
// import android.support.v4.app.FragmentManager;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.plantilla.Orden.ActivityOrden;
import com.example.plantilla.Perfil.PerfilActivity;
import com.example.plantilla.R;
import com.example.plantilla.account.activity.EditProfileActivity;
import com.example.plantilla.account.activity.ProfileActivity;
import com.example.plantilla.account.activity.model.Userinformation;
import com.example.plantilla.cardio.MainActivityCardio;
import com.example.plantilla.ui.adapter.CardAdapter;
import com.example.plantilla.ui.adapter.SectionsPagerAdapter;
import com.example.plantilla.ui.fragment.PlaceholderFragment;
import com.example.plantilla.ui.models.Movie;
import com.example.plantilla.ui.models.Usuarios;
import com.example.plantilla.ui.tab.fragment.NavigationDrawerFragment;
import com.example.plantilla.ui.utilidades.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements CardAdapter.Listener, NavigationView.OnNavigationItemSelectedListener {

    //<editor-fold desc="Aributos">
    CircleImageView image_profile;
    private NavigationView navigationView;
    private MenuItem mMenuItem;
    static boolean sIsNight = false;
    private static final String ARG_SECTION_NUMBER = "section_number";
    //
    private WebView webView;
    private ProgressBar progressBar;
    //Creamos la vista de la actividad
    /**
     * The pagerAdapter} that will provide
     * fragments for each of the sections. We use a
     *  fragment Pager Adapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     *
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private SectionsPagerAdapter mSectionsPagerAdapters;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ViewPager mViewPagers;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardAdapter mAdapters;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<String> alName;
    private ArrayList<Integer> alImage;


    //Dependencias de instancias globales
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private Context context;
    private Activity contexts;
    // Bitmaps will only be decoded once and stored in this cache
    public static SparseArray<Bitmap> sPhotoCache = new SparseArray<Bitmap>(4);
    private int posicion = 0;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private String drawerTitle;

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private FloatingActionButton fab;
    private ImageButton imageButton;
    //Firebase autenticacion
    private FirebaseAuth mAut;
    //Firebase usuario
    private FirebaseUser firebaseUser;
    public TextView nombre;
    private ImageView imageView;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;
    private String TAG  = "MainActivity";
    private String DatoTelefono;
    private EditText mLoginName;
    //</editor-fold>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_naview);
        Fabric.with(this, new Crashlytics());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        context = getApplicationContext();
        contexts = getParent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Crea el adaptador que devolverá un fragmento para cada uno de los tres
        // Secciones primarias de la actividad.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Configurar el ViewPager con el adaptador de secciones
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        fab = findViewById(R.id.fab);
        //permite que se vean los datos en el menu
        drawerLayout = findViewById(R.id.drawer_layout);
        imageView = (ImageView) findViewById(R.id.nav_header_imageView);
        ImageView menuIcon = (ImageView) findViewById(R.id.nav_header_imageView);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tabLayout.setupWithViewPager(mViewPager);

        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //actionBarDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        user=firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                Userinformation userProfile = dataSnapshot.getValue(Userinformation.class);
                //toolbar.setTitle(userProfile.getUserName());
                DatoTelefono=userProfile.getUserPhoneno();
            }
            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, databaseError.getMessage());
                Fabric.getLogger().e(Fabric.TAG, "Failed to load splah" + databaseError.getMessage());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, ActivityOrden.class);
                startActivity(intent);
            }
        });


    }

    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    /**
     * Carga los elementos dibujables en la vista de imagen dada de manera eficiente. Utiliza el método descrito.
     * <a href="http://developer.android.com/training/displaying-bitmaps/load-bitmap.html"> aquí. </a>
     *
     * @param imageView
     * @param resId Identificador de recurso del drawable para cargar desde la memoria
     */
    private void setImageBitmap(ImageView imageView, int resId) {
        Bitmap bitmap = Utils.decodeSampledBitmapFromResource(getResources(),
                resId, imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
        sPhotoCache.put(resId, bitmap);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMenuItem = menu.findItem(R.id.action_night_mode);
        mMenuItem.setChecked(sIsNight);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_night_mode: {
                sIsNight = !sIsNight;
                if(sIsNight) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                mMenuItem.setChecked(sIsNight);
                return true;
            }


            case R.id.action_settings: {
                sIsNight = !sIsNight;
                if(sIsNight) {
                    Intent about = new Intent(getApplicationContext(), MainActivityCardio.class);
                    startActivity(about);

                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                mMenuItem.setChecked(sIsNight);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(Movie movie) {
        if(movie != null){
            Toast.makeText(this, "You just selected " + movie.name + "!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Toast.makeText(this, "You just selected " +  "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        try{

            int id = menuItem.getItemId();

            if (id == R.id.nav_item_one) {
                // Handle the camera action
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
            if (id == R.id.nav_item_two) {
                // Handle the camera action
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intent);
            } /*else if (id == R.id.nav_item_three) {

        } else if (id == R.id.nav_item_four) {

        } else if (id == R.id.nav_item_five) {

        } else if (id == R.id.nav_item_six) {

        }*/

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }catch (Exception e){
            Log.e(TAG,"Failed to load icon" + e.getMessage());
            Fabric.getLogger().e(Fabric.TAG, "Failed to load icon", e);
            return false;
        }


    }

    public void showText(String topText, String bottomText) {
    }



}
