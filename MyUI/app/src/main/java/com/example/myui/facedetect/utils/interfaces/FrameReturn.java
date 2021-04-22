package com.example.myui.facedetect.utils.interfaces;

import android.graphics.Bitmap;

import com.google.firebase.ml.vision.face.FirebaseVisionFace;

import com.example.myui.facedetect.utils.common.FrameMetadata;
import com.example.myui.facedetect.utils.common.GraphicOverlay;

public interface FrameReturn{
    void onFrame(
            Bitmap image ,
            FirebaseVisionFace face ,
            FrameMetadata frameMetadata,
            GraphicOverlay graphicOverlay
    );
}