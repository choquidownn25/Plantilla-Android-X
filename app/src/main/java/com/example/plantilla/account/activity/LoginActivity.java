package com.example.plantilla.account.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plantilla.R;
import com.example.plantilla.account.activity.model.LoginInfo;
import com.example.plantilla.account.activity.model.Users;
import com.example.plantilla.api.ILoguinService;
import com.example.plantilla.api.RetrofitClientInstance;
import com.example.plantilla.error.MessajeError;
import com.example.plantilla.ui.activity.MainActivity;
import com.example.plantilla.utilidad.SessionPrefs;
import com.example.plantilla.webrtc.chat.Mensaje;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    //region Atributos
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button resetButton;
    private Button btnSignup;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth; //Clase Firebase
    private ILoguinService mHaceTokkenApi;
    private RetrofitClientInstance retrofitClientInstance;
    private Users users;
    private String grant_type = "password";
    private LoginInfo loginInfo;
    private Context context;
    private MessajeError menssajeError;
    //endregion

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();
        email  = (EditText) findViewById(R.id.email);
        password  = (EditText) findViewById(R.id.password);
        loginButton  = (Button) findViewById(R.id.login_button);
        resetButton  = (Button) findViewById(R.id.reset_button);
        btnSignup  = (Button) findViewById(R.id.btn_signup);
        progressBar  = (ProgressBar) findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(authStateListener);

        // proceso de inicio de sesión automático
        // pasar a la actividad principal si el usuario ya inició sesión

        /*if (firebaseAuth.getCurrentUser() != null) {
            // User is logged in
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }*/
        //Retorno de instancia de esta clase
        //firebaseAuth = FirebaseAuth.getInstance();
        //Registrar usuario
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        //Recuparar clave
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ResetActivity.class));
            }
        });
        //Acceder
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getText().toString();
                final String userpassword = password.getText().toString();

                if (TextUtils.isEmpty(useremail)) {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userpassword)) {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //login user
                firebaseAuth.signInWithEmailAndPassword(useremail,userpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {

                                    if (userpassword.length() < 6) {
                                        password.setError(LoginActivity.this.getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, LoginActivity.this.getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, PagerActivity.class));
                                    LoginActivity.this.finish();
                                }
                            }
                        });

            }
        });
    }

    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

            if (firebaseUser == null) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
            if (firebaseUser != null) {

//                mHaceTokkenApi = RetrofitClientInstance.getRetrofit().create(ILoguinService.class);
//                String userName = String.valueOf(email);
//                String contrasena = String.valueOf(password);
//                users = new Users();
//                users.setToken(grant_type);
//                users.setUserName(userName);
//                users.setPasswordHash(contrasena);
//
//                Call<LoginInfo> call = mHaceTokkenApi.basicLogin("password", userName, contrasena);
//                call.enqueue(new Callback<LoginInfo>() {
//                    @Override
//                    public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
//
//                        Log.d("TAG",response.code()+"");
//                        loginInfo = new LoginInfo(grant_type, userName, contrasena);
//
//                        SessionPrefs.get(LoginActivity.this).saveAffiliate(response.body());
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginInfo> call, Throwable t) {
//
//                        Log.d("TAG-",t.getMessage() +"");
//                        menssajeError.errorAutenticacion(context);
//                    }
//                });

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }
    };
}
