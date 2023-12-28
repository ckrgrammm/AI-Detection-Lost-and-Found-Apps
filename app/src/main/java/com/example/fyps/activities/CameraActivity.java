package com.example.fyps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyps.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2{
    private static final String TAG="MainActivity";
    private boolean inCaptureMode = false; // New variable to track capture mode



    private Mat mRgba;
    private Mat mGray;
    private CameraBridgeViewBase mOpenCvCameraView;
    private objectDetectorClass objectDetectorClass;
    private BaseLoaderCallback mLoaderCallback =new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface
                        .SUCCESS:{
                    Log.i(TAG,"OpenCv Is loaded");
                    mOpenCvCameraView.enableView();
                }
                default:
                {
                    super.onManagerConnected(status);

                }
                break;
            }
        }
    };







    public CameraActivity(){
        Log.i(TAG,"Instantiated new "+this.getClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        int MY_PERMISSIONS_REQUEST_CAMERA = 0;
        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(CameraActivity.this, new String[] {Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }

        setContentView(R.layout.activity_camera);
        Button captureButton = findViewById(R.id.btn_capture);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inCaptureMode = true; // Switch to capture mode
            }
        });
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.frame_Surface);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        try {
            objectDetectorClass = new objectDetectorClass(getAssets(), "ssd_mobilenet.tflite", "labelmap.txt", 300);
            Log.d("MainActivity", "Model is successfully loaded");
        } catch (IOException e) {
            Log.d("MainActivity", "Getting some error");
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()){
            Log.d(TAG,"Opencv initialization is done");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        else{
            Log.d(TAG,"Opencv is not loaded. try again");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0,this,mLoaderCallback);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mOpenCvCameraView !=null){
            mOpenCvCameraView.disableView();
        }
    }

    public void onDestroy(){
        super.onDestroy();
        if(mOpenCvCameraView !=null){
            mOpenCvCameraView.disableView();
        }

    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);
    }


    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }


    private boolean isDialogShown = false; // Flag to track if the dialog is already shown

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        mGray = inputFrame.gray();

        if (inCaptureMode) {
            Mat out = objectDetectorClass.recognizeImage(mRgba);
            String detectedObjectName = objectDetectorClass.getDetectedObjectName();
            if (detectedObjectName != null && !detectedObjectName.isEmpty() && !isDialogShown) {
                isDialogShown = true;
                inCaptureMode = false;
                runOnUiThread(() -> onObjectDetected(detectedObjectName, mRgba));
            }
            return out;
        } else {
            return mRgba;
        }


    }

    private void onObjectDetected(String detectedObjectName, Mat capturedFrame) {
        final String finalDetectedObjectName = detectedObjectName != null && !detectedObjectName.isEmpty()
                ? detectedObjectName.substring(0, 1).toUpperCase() + detectedObjectName.substring(1)
                : detectedObjectName;

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        ImageView iconImageView = dialogView.findViewById(R.id.imageView);
        iconImageView.setImageResource(R.drawable.icon_warning);
        TextView titleTextView = dialogView.findViewById(R.id.textView);
        titleTextView.setText("Object Detected");
        TextView messageTextView = dialogView.findViewById(R.id.textView2);
        messageTextView.setText("Detected object: " + finalDetectedObjectName + ". Use this object?");
        AppCompatButton cancelButton = dialogView.findViewById(R.id.btn_cancel);
        AppCompatButton okayButton = dialogView.findViewById(R.id.btn_okay);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        cancelButton.setOnClickListener(v -> {
            new Handler().postDelayed(() -> {
                dialog.dismiss();
                isDialogShown = false;
            }, 500);
        });

        okayButton.setOnClickListener(v -> {
            new Handler().postDelayed(() -> {
                Bitmap capturedBitmap = convertMatToBitmap(capturedFrame);

                String filename = "temp_image";
                try {
                    FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
                    capturedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    stream.close();
                    capturedBitmap.recycle();

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("DetectedObjectName", finalDetectedObjectName);
                    resultIntent.putExtra("CapturedImageFilename", filename);
                    setResult(Activity.RESULT_OK, resultIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
                finish();
            }, 500);
        });
        dialog.show();
    }



    private Bitmap convertMatToBitmap(Mat mat) {
        Bitmap bmp = null;
        Mat tmp = new Mat(mat.height(), mat.width(), CvType.CV_8U, new Scalar(4));
        try {
            Imgproc.cvtColor(mat, tmp, Imgproc.COLOR_RGB2BGRA);
            bmp = Bitmap.createBitmap(tmp.cols(), tmp.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(tmp, bmp);

            Matrix matrix = new Matrix();
            matrix.postRotate(90); // Rotate 90 degrees
            bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        }
        catch (CvException e) {
            Log.d(TAG, e.getMessage());
        }
        return bmp;
    }


}