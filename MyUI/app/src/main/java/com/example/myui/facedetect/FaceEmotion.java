package com.example.myui.facedetect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.myui.MainActivity;
import com.example.myui.facedetect.FaceEmotion;
import com.example.myui.R;
import com.example.myui.facedetect.utils.base.BaseActivity;
import com.example.myui.facedetect.utils.base.Cons;
import com.example.myui.facedetect.utils.base.PublicMethods;
import com.example.myui.facedetect.utils.common.CameraSource;
import com.example.myui.facedetect.utils.common.CameraSourcePreview;
import com.example.myui.facedetect.utils.common.FrameMetadata;
import com.example.myui.facedetect.utils.common.GraphicOverlay;
import com.example.myui.facedetect.utils.interfaces.FaceDetectStatus;
import com.example.myui.facedetect.utils.interfaces.FrameReturn;
import com.example.myui.facedetect.utils.models.RectModel;
import com.example.myui.facedetect.utils.visions.FaceDetectionProcessor;
import com.example.myui.login.FingerprintLogin;
import com.google.android.gms.common.annotation.KeepName;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.io.IOException;

import static com.example.myui.facedetect.utils.base.Cons.IMG_EXTRA_KEY;

@KeepName
public final class FaceEmotion extends BaseActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback, FrameReturn, FaceDetectStatus {
    private static final String FACE_DETECTION = "Face Detection";
    private static final String TAG = "MLKitTAG";

    Bitmap originalImage = null;
    private CameraSource cameraSource = null;
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private ImageView faceFrame;
    private ImageView test;
    private Button takePhoto;
    private SmileRating smile_rating;
    private Bitmap croppedImage = null;
    private TextView etoday, textview6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_emotion);
        test = findViewById(R.id.test);
        preview = findViewById(R.id.firePreview);
        takePhoto = findViewById(R.id.takePhoto);
        faceFrame = findViewById(R.id.faceFrame);
        graphicOverlay = findViewById(R.id.fireFaceOverlay);
        smile_rating = findViewById(R.id.smile_rating);
        etoday = findViewById(R.id.etoday);
        textview6 = findViewById(R.id.textView6);
        FirebaseApp.initializeApp(this);

        textview6.animate().alpha(1f);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textview6.setText("How ");
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textview6.append("are ");
            }
        }, 1200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textview6.append("you ");
            }
        }, 1400);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textview6.append("today? ");
            }
        }, 1500);

        etoday.setTextSize(18);

        if (PublicMethods.allPermissionsGranted(this)) {
            createCameraSource();
        } else {
            PublicMethods.getRuntimePermissions(this);
        }

//        takePhoto.setOnClickListener(v -> takePhoto());

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaceEmotion.this, MainActivity.class);
//                EditText username = findViewById(R.id.username);
                String name = getIntent().getExtras().getString("name");
                intent.putExtra("name", name);
                startActivity(intent);            }
        });
    }


    private void createCameraSource() {
        if (cameraSource == null) {
            cameraSource = new CameraSource(this, graphicOverlay);
        }
        try {
            FaceDetectionProcessor processor = new FaceDetectionProcessor(getResources());
            processor.frameHandler = this;
            processor.faceDetectStatus = this;
            cameraSource.setMachineLearningFrameProcessor(processor);
        } catch (Exception e) {
            Log.e(TAG, "Can not create image processor: " + FACE_DETECTION, e);
            Toast.makeText(
                    getApplicationContext(),
                    "Can not create image processor: " + e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }


    private void startCameraSource() {
        if (cameraSource != null) {
            try {
                preview.start(cameraSource, graphicOverlay);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                cameraSource.release();
                cameraSource = null;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startCameraSource();
    }

    @Override
    protected void onPause() {
        super.onPause();
        preview.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PublicMethods.allPermissionsGranted(this)) {
            createCameraSource();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //calls with each frame includes by face
    @Override
    public void onFrame(Bitmap image, FirebaseVisionFace face, FrameMetadata frameMetadata, GraphicOverlay graphicOverlay) {
        originalImage = image;
        if (face.getLeftEyeOpenProbability() < 0.4) {
            findViewById(R.id.rightEyeStatus).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.rightEyeStatus).setVisibility(View.INVISIBLE);
        }
        if (face.getRightEyeOpenProbability() < 0.4) {
            findViewById(R.id.leftEyeStatus).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.leftEyeStatus).setVisibility(View.INVISIBLE);
        }

        int smile = 0;

        if (face.getSmilingProbability() > .8) {
            smile = BaseRating.GREAT ;

            etoday.setText("Perfect!!! Yor are great today. Let's access to application to manage your time effectively!");

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.setText("Perfect!!! ");
//                }
//            }, 1000);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.append("You ");
//                }
//            }, 1000);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.append("are ");
//                }
//            }, 1200);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.append("great ");
//                }
//            }, 1400);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.append("today. \n Let's access to application to manage your time effectively!");
//                }
//            }, 1500);

            if (PublicMethods.allPermissionsGranted(this)) {
                createCameraSource();
            } else {
                PublicMethods.getRuntimePermissions(this);
            }
        } else if (face.getSmilingProbability() <= .8 && face.getSmilingProbability() > .6) {
            smile = BaseRating.GOOD;

            etoday.setText("Very good!! Let's access to application to manage your time effectively!");

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.setText("Very good!! ");
//                }
//            }, 1000);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.append("\n Let's access to application to manage your time effectively! ");
//                }
//            }, 2000);

        } else if (face.getSmilingProbability() <= .6 && face.getSmilingProbability() > .4) {
            smile = BaseRating.OKAY ;

            etoday.setText("Are you OK? Please cheer up! Let's access to application to manage your time effectively!");

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.setText("Are you OK? \n");
//                }
//            }, 1000);
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.append("Please cheer up! ");
//                }
//            }, 2000);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.append("\n Let's access to application to manage your time effectively! ");
//                }
//            }, 2200);

        } else if (face.getSmilingProbability() <= .4 && face.getSmilingProbability() > .2) {
            smile = BaseRating.BAD ;

            etoday.setText("OH NO! What's happening with you? Let's take a smile to start a wonderful day! ");

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.setText("OH NO! What's happening with you? ");
//                }
//            }, 1000);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.setText("Let's take a smile to start a wonderful day! ");
//                }
//            }, 2000);
        }
        else {
            smile = BaseRating.TERRIBLE ;
            etoday.setText("OH my gosh! What's happening with you? Stay positive and keep your smile on your face. Stay strong and move forward!");

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.setText("OH my gosh! What's happening with you? ");
//                }
//            }, 1000);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    etoday.setText("Stay positive and keep your smile on your face. \n Stay strong and move forward!");
//                }
//            }, 2000);
        }
        smile_rating.setSelectedSmile(smile, true);

    }

    @Override
    public void onFaceLocated(RectModel rectModel) {
        faceFrame.setColorFilter(ContextCompat.getColor(this, R.color.green));
        takePhoto.setEnabled(true);

        float left = (float) (originalImage.getWidth() * 0.2);
        float newWidth = (float) (originalImage.getWidth() * 0.6);

        float top = (float) (originalImage.getHeight() * 0.2);
        float newHeight = (float) (originalImage.getHeight() * 0.6);
        croppedImage =
                Bitmap.createBitmap(originalImage,
                        ((int) (left)),
                        (int) (top),
                        ((int) (newWidth)),
                        (int) (newHeight));
        test.setImageBitmap(croppedImage);
    }

//    private void takePhoto() {
//        if (croppedImage != null) {
//            String path = PublicMethods.saveToInternalStorage(croppedImage, Cons.IMG_FILE, mActivity);
//            startActivity(new Intent(mActivity, PhotoViewerActivity.class)
//                    .putExtra(IMG_EXTRA_KEY, path));
//        }
//    }

    @Override
    public void onFaceNotLocated() {
        faceFrame.setColorFilter(ContextCompat.getColor(this, R.color.red));
        takePhoto.setEnabled(true);
    }
}
