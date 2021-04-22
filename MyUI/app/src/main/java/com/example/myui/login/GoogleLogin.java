package com.example.myui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myui.R;
import com.example.myui.facedetect.FaceEmotion;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.squareup.picasso.Picasso;

public class GoogleLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private ImageView profile;
    private TextView gname, gemail, gid;
    private Button signout;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);

        profile = findViewById(R.id.profile);
        gname = findViewById(R.id.ggname);
        gemail = findViewById(R.id.ggemail);
        gid = findViewById(R.id.ggid);
        signout = findViewById(R.id.sign_out);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        startActivity(new Intent(GoogleLogin.this, Login.class));

                    }
                });
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult googleSignInResult){
        if (googleSignInResult.isSuccess()){
            GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
            gname.setText(googleSignInAccount.getDisplayName());
            gemail.setText(googleSignInAccount.getEmail());
            gid.setText(googleSignInAccount.getId());
            Picasso.get().load(googleSignInAccount.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(profile);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(GoogleLogin.this, FaceEmotion.class);
        String name = "Rain";
        intent.putExtra("name", name);
        startActivity(intent);
////                    startActivity(new Intent(Login.this, FaceEmotion.class));

        OptionalPendingResult<GoogleSignInResult> optionalPendingResult = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (optionalPendingResult.isDone()){
            GoogleSignInResult googleSignInResult = optionalPendingResult.get();
            handleSignInResult(googleSignInResult);

        }

        else {
            optionalPendingResult.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }
}