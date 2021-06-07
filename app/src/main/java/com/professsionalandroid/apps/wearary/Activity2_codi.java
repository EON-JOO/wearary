package com.professsionalandroid.apps.wearary;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Activity2_codi extends AppCompatActivity {
    private static final int MY_PERMISSION_CAMERA = 1111;
    private static final int REQUEST_TAKE_ALBUM = 2222;
    private static final int REQUEST_IMAGE_CROP = 3333;

    Button btn_album;
    ImageView iv_view;

    String mCurrentPhotoPath;

    Uri photoURI, albumURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_codi);

        btn_album = (Button) findViewById(R.id.btn_album);
        iv_view = (ImageView) findViewById(R.id.iv_view);

        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAlbum();
            }
        });

        checkPermission();

    }


    public File createImageFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "CODI_" + timeStamp + ".jpg";
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "wearay");

        if (!storageDir.exists()) {
            Log.i("mCurrentPhotoPath1", storageDir.toString());
        }

        File imageFile = new File(storageDir, imageFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();

        return imageFile;
    }


    private void getAlbum() {
        Log.i("getAlbum", "Call");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_TAKE_ALBUM);
    }

    // 카메라 전용 크랍
    public void cropImage() {
        Log.i("cropImage", "Call");
        Log.i("cropImage", "photoURI : " + photoURI + " / albumURI : " + albumURI);

        Intent cropIntent = new Intent("com.android.camera.action.CROP");

        // 50x50픽셀미만은 편집할 수 없다는 문구 처리 + 갤러리, 포토 둘다 호환하는 방법
        cropIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cropIntent.setDataAndType(photoURI, "image/*");
        cropIntent.putExtra("aspectX", 1); // crop 박스의 x축 비율, 1&1이면 정사각형
        cropIntent.putExtra("aspectY", 1); // crop 박스의 y축 비율
        cropIntent.putExtra("scale", true);
        cropIntent.putExtra("output", albumURI); // 크랍된 이미지를 해당 경로에 저장
        startActivityForResult(cropIntent, REQUEST_IMAGE_CROP);
    }

    //Bitmap 받아서 이미지 합성
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Bitmap overlayBitmap(Uri original) throws IOException {

        Intent intent = getIntent();
        Bitmap originalResizeBmp;
        Bitmap stickerBmp;
        Bitmap stickerBmp1;
        Bitmap resultOverlayBmp = null;
        Drawable drawable;

        if (intent != null) {
            String sticker = intent.getStringExtra("sticker");
            String cold = "cold";
            String hot = "hot";
            String soso = "soso";
            String good = "good";

            if (Objects.equals(sticker, cold)) {
                stickerBmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.cold);
            } else if (Objects.equals(sticker, hot)) {
                stickerBmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.hot);
            } else if (Objects.equals(sticker, soso)) {
                stickerBmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.soso);
            } else {
                stickerBmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.good);
            }

            //stickerBmp size 축소
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;

            int width = 95; // 축소시킬 너비
            int height = 95; // 축소시킬 높이
            float bmpWidth = stickerBmp.getWidth();
            float bmpHeight = stickerBmp.getHeight();

            if (bmpWidth > width) {
                // 원하는 너비보다 클 경우의 설정
                float mWidth = bmpWidth / 100;
                float scale = width / mWidth;
                bmpWidth *= (scale / 100);
                bmpHeight *= (scale / 100);
            } else if (bmpHeight > height) {
                // 원하는 높이보다 클 경우의 설정
                float mHeight = bmpHeight / 100;
                float scale = height / mHeight;
                bmpWidth *= (scale / 100);
                bmpHeight *= (scale / 100);
            }

            Bitmap resizedBmp = Bitmap.createScaledBitmap(stickerBmp, (int) bmpWidth, (int) bmpHeight, true);

            originalResizeBmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), original);

            //결과값 저장을 위한 Bitmap
            resultOverlayBmp = Bitmap.createBitmap(originalResizeBmp.getWidth(), originalResizeBmp.getHeight(), originalResizeBmp.getConfig());

            //캔버스를 통해 비트맵을 겹치기한다.

            Canvas canvas = new Canvas(resultOverlayBmp);
            canvas.drawBitmap(originalResizeBmp, 0, 0, null);
            canvas.drawBitmap(resizedBmp, 30, 30, null);

        }
        return resultOverlayBmp;
    }

    private void galleryAddPic(Bitmap bitmap) throws IOException {
        Log.i("galleryAddPic", "Call");
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        // 해당 경로에 있는 파일을 객체화(새로 파일을 만든다는 것으로 이해하면 안 됨)
        File f = new File(mCurrentPhotoPath);

        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
        Toast.makeText(this, "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show();
    }

    //bitmap 저장
    public void saveBitmaptoJpeg(Bitmap bitmap) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String file_name = "CODI_" + timeStamp + ".jpg";
        String string_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/wearay/";

        File file_path;
        try {
            file_path = new File(string_path);
            if (!file_path.exists()) {
                file_path.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(string_path + file_name);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();

        } catch (FileNotFoundException exception) {
            Log.e("FileNotFoundException", exception.getMessage());
        } catch (IOException exception) {
            Log.e("IOException", exception.getMessage());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Create an image file name
        super.onActivityResult(requestCode, resultCode, data);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "CODI_" + timeStamp + ".jpg";
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "wearay");

        if (!storageDir.exists()) {
            Log.i("mCurrentPhotoPath1", storageDir.toString());
        }

        File imageFile = new File(storageDir, imageFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();

        switch (requestCode) {

            case REQUEST_TAKE_ALBUM:
                if (resultCode == Activity.RESULT_OK) {

                    if (data.getData() != null) {
                        try {
                            File albumFile = createImageFile();
                            photoURI = data.getData();
                            albumURI = Uri.fromFile(albumFile);
                            cropImage();
                        } catch (Exception e) {
                            Log.e("TAKE_ALBUM_SINGLE ERROR", e.toString());
                        }
                    }
                }
                break;

            case REQUEST_IMAGE_CROP:
                Bitmap stickerphoto = null;
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        stickerphoto = overlayBitmap(albumURI);
                        saveBitmaptoJpeg(stickerphoto);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        galleryAddPic(stickerphoto);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    iv_view.setImageBitmap(stickerphoto);
                }
                break;
        }
    }


    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 처음 호출시엔 if()안의 부분은 false로 리턴 됨 -> else{..}의 요청으로 넘어감
            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))) {
                new AlertDialog.Builder(this)
                        .setTitle("알림")
                        .setMessage("저장소 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                        .setNeutralButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
            }
        }
    }
}
