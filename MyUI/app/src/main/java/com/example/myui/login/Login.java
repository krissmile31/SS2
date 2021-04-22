package com.example.myui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myui.MainActivity;
import com.example.myui.R;
import com.example.myui.facedetect.FaceEmotion;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private EditText username, password;
    private TextView tvl;
    private ImageView loginFb, loginTwitter, loginGg;
    private CallbackManager callbackManager;
    private static final int SIGN_IN =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        tvl = findViewById(R.id.tvl);
        loginFb = findViewById(R.id.loginFb);
        loginTwitter = findViewById(R.id.loginTwitter);
        loginGg = findViewById(R.id.loginGg);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Login.this, FaceEmotion.class);
                    String name = username.getText().toString();
                    intent.putExtra("name", name);
                    startActivity(intent);
//                    startActivity(new Intent(Login.this, FaceEmotion.class));
                }

                else {
                    tvl.animate().alpha(1f).setDuration(1000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvl.setText("Please ");
                        }
                    }, 300);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvl.append("fill ");
                        }
                    }, 400);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvl.append("the ");
                        }
                    }, 500);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvl.append("required ");
                        }
                    }, 600);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvl.append("info! ");
                        }
                    }, 700);
                }
            }
        });

        findViewById(R.id.signupp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });

        findViewById(R.id.fingerprint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, FingerprintLogin.class));
            }
        });

        findViewById(R.id.faceID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, MainActivity.class);
//                String name = "Rain";
//                intent.putExtra("name", name);
//                startActivity(intent);      

                startActivity(new Intent(Login.this, FaceEmotion.class));
            }
        });

        LoginButton loginButton = findViewById(R.id.login_fb);

        loginFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.loginBackground).animate().alpha(0f);
                findViewById(R.id.fbBackground).animate().alpha(1f);
            }
        });

        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions("email", "avatar");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("Demo", "Success");
//                TextView fname = findViewById(R.id.fname);
//                ImageView fimage = findViewById(R.id.fimage);
//                Picasso.get().load("https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?return_ss1_resources=1").into(fimage);
                Intent intent = new Intent(Login.this, FaceEmotion.class);
                String name = "Rain";
                intent.putExtra("name", name);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("Demo", "Cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("Demo", "Error");
            }
        });

        // login with gg
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

        loginGg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()){
                Intent intent = new Intent(Login.this, GoogleLogin.class);
//                String name = "Rain";
//                intent.putExtra("name", name);
                startActivity(intent);
//                finish();
            }
        }

    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                LoginManager.getInstance().logOut();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}