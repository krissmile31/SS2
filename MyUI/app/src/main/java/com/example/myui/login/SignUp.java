package com.example.myui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.myui.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class SignUp extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    private TextView tvs1, tvs2, tvs3, tvs4;
    private EditText signname, signpass;
    private CheckBox checkBox;
    private GoogleApiClient googleApiClient;

    //put SiteKey
    private String SiteKey = "6Ld3U7UaAAAAAJEqo7-pSubg1F24dXdQOatc3iSF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tvs1 = findViewById(R.id.tvs1);
        tvs2 = findViewById(R.id.tvs2);
        tvs3 = findViewById(R.id.tvs3);
        tvs4 = findViewById(R.id.tvs4);
        signname = findViewById(R.id.signname);
        signpass = findViewById(R.id.signpass);
        checkBox = findViewById(R.id.checkrobot);

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signname.getText().toString().isEmpty() || signpass.getText().toString().isEmpty()) {
                    findViewById(R.id.tvs1).animate().alpha(1f).setDuration(1000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvs1.setText("Please ");
                        }
                    }, 300);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvs1.append("fill ");
                        }
                    }, 400);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvs1.append("the ");
                        }
                    }, 500);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvs1.append("required ");
                        }
                    }, 600);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvs1.append("info! ");
                        }
                    }, 700);
                }

                else {
                    findViewById(R.id.signup).animate().alpha(0f);
                    findViewById(R.id.tvs6).animate().alpha(1f);
                    findViewById(R.id.radioAccept).animate().alpha(1f);
                    findViewById(R.id.radioRefuse).animate().alpha(1f);

                    findViewById(R.id.radioAccept).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tvs1.animate().alpha(1f).setDuration(1000);
                            tvs2.animate().alpha(1f).setDuration(2000);
                            tvs3.animate().alpha(1f).setDuration(3000);
                            tvs4.animate().alpha(1f).setDuration(4000);
                            YoYo.with(Techniques.Bounce).repeat(50).playOn(tvs3);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs1.setText("Sign ");
                                }
                            }, 300);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs1.append("Up ");
                                }
                            }, 400);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs1.append("successfully!");
                                }
                            }, 500);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs2.setText("You ");
                                }
                            }, 600);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs2.append("need ");
                                }
                            }, 700);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs2.append("to ");
                                }
                            }, 800);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs3.setText("Login ");
                                }
                            }, 900);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs4.setText("to ");
                                }
                            }, 1000);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs4.append("access ");
                                }
                            }, 1100);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs4.append("application");
                                }
                            }, 1200);
                            }
                    });

                    findViewById(R.id.radioRefuse).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tvs1.animate().alpha(1f).setDuration(1000);
                            tvs2.animate().alpha(0f).setDuration(1000);
                            tvs3.animate().alpha(0f).setDuration(1000);
                            tvs4.animate().alpha(0f).setDuration(1000);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs1.setText("Please ");
                                }
                            }, 300);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs1.append("accept ");
                                }
                            }, 400);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs1.append("privacy ");
                                }
                            }, 500);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvs1.append("policy");
                                }
                            }, 600);
                        }
                    });
                }
            }
        });

        findViewById(R.id.loginn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });

        findViewById(R.id.tvs3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });

        // i am not a robot
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(SignUp.this)
                .build();

        googleApiClient.connect();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if (status != null && status.isSuccess()) {
                                        Toast.makeText(SignUp.this, "Success", Toast.LENGTH_SHORT).show();
                                        checkBox.setTextColor(Color.GREEN);
                                    }
                                }
                            });
                }
                else {
                    checkBox.setTextColor(Color.BLACK);
                }
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}