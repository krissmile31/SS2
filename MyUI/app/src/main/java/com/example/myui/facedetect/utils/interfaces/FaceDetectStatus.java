package com.example.myui.facedetect.utils.interfaces;

import com.example.myui.facedetect.utils.models.RectModel;

public interface FaceDetectStatus {
    void onFaceLocated(RectModel rectModel);
    void onFaceNotLocated() ;
}
