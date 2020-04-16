package com.example.plantilla.sinch.push;

import com.example.plantilla.account.activity.model.Userinformation;
import com.example.plantilla.sinch.config.util.SinchService;
import com.example.plantilla.sinch.config.util.SinchServices;
import com.example.plantilla.ui.activity.MainActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.sinch.android.rtc.calling.Call;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.plantilla.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import android.app.ProgressDialog;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class PlaceCallActivity extends BaseActivity {

    //<editor-fold desc="Atributos">
    private Button mCallButton;
    private EditText mCallName;
    public final int PICK_CONTACT = 2015;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;
    private String TAG  = PlaceCallActivity.class.getSimpleName();
    private  String indicativoPais;
    private  String resultadoIndicativoPais;
    private String indicativoUSA;
    private String resultadoIndicativoUSA;
    private ProgressDialog progressDialog;
    private String DatoTelefono;

    //</editor-fold>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Fabric.with(this, new Crashlytics());


        mCallName = (EditText) findViewById(R.id.callName);
        mCallButton = (Button) findViewById(R.id.callButton);
        mCallButton.setEnabled(false);
        mCallButton.setOnClickListener(buttonClickListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mCallName.setVisibility(View.GONE);
        Button stopButton = (Button) findViewById(R.id.stopButton);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        stopButton.setOnClickListener(buttonClickListener);


        (findViewById(R.id.btncontacto)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(i, PICK_CONTACT);
            }
        });

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
                collapsingToolbarLayout.setTitle(userProfile.getUserName());
                DatoTelefono=userProfile.getUserPhoneno();
            }
            @Override
            public void onCancelled( DatabaseError databaseError) {
                try {
                    Toast.makeText(PlaceCallActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e(TAG, databaseError.getMessage());
                }catch (Exception e){
                    Fabric.getLogger().e(Fabric.TAG, "Failed to load ", e);
                }

            }
        });

    }

    @Override
    protected void onServiceConnected() {
        TextView userName = (TextView) findViewById(R.id.loggedInName);

        userName.setText(getSinchServiceInterface().getUserName());
        mCallButton.setEnabled(true);
    }

    private void stopButtonClicked() {
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
        finish();
    }

    private void callButtonClicked() {
        String userName = mCallName.getText().toString();
        if (userName.isEmpty()) {
            Toast.makeText(this, "Please enter a user to call", Toast.LENGTH_LONG).show();
            return;
        }

        Call call = getSinchServiceInterface().callUserVideo(userName);
        String callId = call.getCallId();

        Intent callScreen = new Intent(this, CallScreenActivity.class);
        callScreen.putExtra(SinchServices.CALL_ID, callId);
        startActivity(callScreen);
    }

    private OnClickListener buttonClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.callButton:
                    callButtonClicked();
                    break;

                case R.id.stopButton:
                    stopButtonClicked();
                    break;

            }
        }
    };

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
            cursor.moveToFirst();
            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            (new normalizePhoneNumberTask()).execute(cursor.getString(column));
        }
    }

    class normalizePhoneNumberTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String normalizedPhoneNumber = "";

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("https://callingapi.sinch.com/v1/calling/query/number/" + params[0].replaceAll("\\s+", ""));

//                String usernamePassword = "application:" + APP_KEY + ":" + APP_SECRET; SinchServices
                String usernamePassword = "application:" + SinchService.getAppKey() + ":" + SinchService.getAppSecret();
                String encoded = Base64.encodeToString(usernamePassword.getBytes(), Base64.NO_WRAP);
                httpGet.addHeader("Authorization", "Basic " + encoded);

                HttpResponse response = httpclient.execute(httpGet);
                ResponseHandler<String> handler = new BasicResponseHandler();
                normalizedPhoneNumber = parseJSONResponse(handler.handleResponse(response));
            } catch (ClientProtocolException e) {
                Log.d("ClientProtocolException", e.getMessage());
                Fabric.getLogger().e(Fabric.TAG, "Failed to ", e);
            } catch (IOException e) {
                Log.d("IOException", e.getMessage());
                Fabric.getLogger().e(Fabric.TAG, "Failed to ", e);
            }

            return normalizedPhoneNumber;
        }

        @Override
        protected void onPostExecute(String normalizedPhoneNumber) {
            Toast.makeText(getApplicationContext(), normalizedPhoneNumber, Toast.LENGTH_LONG).show();
            if(normalizedPhoneNumber=="" || normalizedPhoneNumber.isEmpty()){
                new SweetAlertDialog(PlaceCallActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(PlaceCallActivity.this.getString(R.string.contacto_no_encontrado))
                        .setContentText(PlaceCallActivity.this.getString(R.string.el_contacto))
                        .show();
                Toast.makeText(getApplicationContext(), "No es numero movil" +normalizedPhoneNumber, Toast.LENGTH_LONG).show();
                mCallName.setText(normalizedPhoneNumber);
            }else {
                indicativoPais = normalizedPhoneNumber;
                resultadoIndicativoPais = indicativoPais.substring(0,3);
                indicativoUSA=normalizedPhoneNumber;
                resultadoIndicativoUSA=indicativoUSA.substring(0,2);
                //Mexico
                if(resultadoIndicativoPais.equals("+52") ){
                    String sCadena = normalizedPhoneNumber;
                    String sSubCadena = sCadena.substring(1,14);
                    mCallName.setVisibility(View.VISIBLE);
                    mCallName.setFocusable(false);
                    mCallName.setText(sSubCadena);
                }else if(resultadoIndicativoUSA.equals("+1")){
                    String CadenaUsa = normalizedPhoneNumber;
                    String SubCadenaUsa = CadenaUsa.substring(1,12);
                    mCallName.setVisibility(View.VISIBLE);
                    mCallName.setFocusable(false);
                    mCallName.setText(SubCadenaUsa);
                }else {
                    String sCadena = normalizedPhoneNumber;
                    String sSubCadena = sCadena.substring(1,11);
                    mCallName.setVisibility(View.VISIBLE);
                    mCallName.setFocusable(false);
                    mCallName.setText(sSubCadena);
                }
                progressDialog = new ProgressDialog(PlaceCallActivity.this);
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setTitle("Prosesando"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();

            }


        }

        private String parseJSONResponse(String jsonString) {

            String returnString = "";

            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                returnString = jsonObject.getJSONObject("number").getString("normalizedNumber");
            } catch (JSONException e) {
                Log.d("JSONException", e.getMessage());
            }

            return returnString;
        }
    }



}
