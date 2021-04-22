package com.example.myui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myui.MainActivity;
import com.example.myui.R;
import com.example.myui.facedetect.FaceEmotion;

import java.util.concurrent.Executor;

public class FingerprintLogin extends AppCompatActivity {
    private TextView tvFing;
    private Button loginFing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_login);

        tvFing = findViewById(R.id.tvFing);
        loginFing = findViewById(R.id.loginFing);

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvFing.setText("You ");
                    }
                }, 300);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvFing.append("can ");
                    }
                }, 400);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvFing.append("use ");
                    }
                }, 500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvFing.append("the ");
                    }
                }, 600);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvFing.append("fingerprint ");
                    }
                }, 700);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvFing.append("sensor ");
                    }
                }, 800);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvFing.append("to login! ");
                    }
                }, 900);
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                tvFing.setText("The device don't have a fingerprint sensor!");
                loginFing.setVisibility(View.GONE);
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                tvFing.setText("The biometric sensors is currently unavailable!");
                loginFing.setVisibility(View.GONE);
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                tvFing.setText("Your device don't have any fingerprint saved, please check your security settings!");
                loginFing.setVisibility(View.GONE);
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(FingerprintLogin.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                loginFing.setVisibility(View.GONE);
//                Toast.makeText(FingerprintLogin.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                findViewById(R.id.finger).animate().alpha(0f);
                findViewById(R.id.fingersensor).animate().alpha(0f);
                tvFing.setText("Login Successfully!");
                findViewById(R.id.tick).animate().alpha(1f).setDuration(3000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(FingerprintLogin.this, FaceEmotion.class);
                        EditText username = findViewById(R.id.username);
                        String name = "Rain";
                        intent.putExtra("name", name);
                        startActivity(intent);
//                        startActivity(new Intent(FingerprintLogin.this, FaceEmotion.class));
                    }
                });
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("Use the fingerprint to login to your app!")
                .setNegativeButtonText("Cancel")
                .build();

        loginFing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
                findViewById(R.id.finger).animate().alpha(0f);
                findViewById(R.id.fingersensor).animate().alpha(1f);
            }
        });

    }
}