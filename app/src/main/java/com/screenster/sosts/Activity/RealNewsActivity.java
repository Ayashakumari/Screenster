package com.screenster.sosts.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.screenster.sosts.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RealNewsActivity extends AppCompatActivity {

    ImageView img_camera,img_camera2,header_back;
    TextView txt_real_news,header_txt,count_news;

    String path_img = "";


    ProgressDialog dlg = null;
    Bitmap myBitmap, myBitma, rotatedBitmap;

    Double latitude=null,longitude=null;
    byte[] imageBytes=null;
    byte[] byte_arr=null;
    String mobile_No,calling_code,userId;
    JSONObject object;
    String api_lattitude="",api_longitude="";

    private static int LOAD_IMAGE_RESULTS = 1000;
    Uri imageUri;
    int requestCode=0;
    int gmtvalue;

    String address;
    Double latis, longis;
    String lat, lang;
    String no_people, date, time, location, city, comment, address1 = "", state = "";
    public static final int RequestPermissionCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_news);

        header_txt=(TextView)findViewById(R.id.header_txt);
        count_news=findViewById(R.id.count_news);
        count_news.setVisibility(View.VISIBLE);

        header_txt.setText("Real News");


        header_back=findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        img_camera = (ImageView) findViewById(R.id.img_camera);
        img_camera2 = (ImageView) findViewById(R.id.img_camera2);
        txt_real_news=findViewById(R.id.txt_real_news);
        EnableRuntimePermission();


        txt_real_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RealNewsActivity.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                i.putExtra("flag",1);
                startActivity(i);
            }
        });


        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog1 = new Dialog(RealNewsActivity.this, R.style.DialogSlideAnim);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.setContentView(R.layout.dialog_custom);

                dialog1.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();

                RelativeLayout takepicture = (RelativeLayout) dialog1.findViewById(R.id.gallery_rl);
                RelativeLayout uploadfile = (RelativeLayout) dialog1.findViewById(R.id.camera_rl);
                RelativeLayout cancel_rl = (RelativeLayout) dialog1.findViewById(R.id.cancel_rl);

                cancel_rl.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();

                    }


                });


                takepicture.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, "New Picture");
                        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                        imageUri = null;
                        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, 1);
                        dialog1.dismiss();

                    }
                });

                uploadfile.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, 0);
                        dialog1.dismiss();

                    }
                });


            }
        });
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    path_img = cursor.getString(columnIndex);
                    cursor.close();
                    new AsyncReceiverTask().execute();


                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    // retrive the data by using getPlace() method.
//                    Place place = PlaceAutocomplete.getPlace(this, data);
//                    Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
//                    location_edit.setText(place.getAddress());
//                    List<Address> addressList = null;
////                locationSearch.setText(place.getAddress());
//                    location = location_edit.getText().toString();
//
//                    if (location != null && !location.equals("")) {
//                        Geocoder geocoder = new Geocoder(this);
//                        try {
//                            addressList = geocoder.getFromLocationName(location, 1);
//                            Address address = addressList.get(0);
//                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//                            latitude=String.valueOf(latLng.latitude);
//                            longitude=String.valueOf(latLng.longitude);
//                            Log.d("helper.latitude", ""+latitude);
//                            Log.d("helper.longitude", ""+longitude);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }


                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                }
                break;
            case 1:

                ContentResolver cr = getContentResolver();
                Cursor metaCursor = cr.query(imageUri,
                        new String[]{MediaStore.MediaColumns.DATA}, null, null, null);
                if (metaCursor != null) {
                    try {
                        if (metaCursor.moveToFirst()) {
                            path_img = metaCursor.getString(0);
                        }
                        new AsyncReceiverTask().execute();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        imageUri = null;
                        path_img = "";
                    } finally {
                        metaCursor.close();

                    }
                }
                break;
        }
    }

    class AsyncReceiverTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if (Build.VERSION.SDK_INT >= 11) {
                dlg = new ProgressDialog(RealNewsActivity.this, AlertDialog.THEME_HOLO_LIGHT);
            } else {
                dlg = new ProgressDialog(RealNewsActivity.this);
            }
            dlg.setMessage("loading..");
            dlg.show();
        }

        @Override
        protected String doInBackground(String... params) {


            if (myBitmap != null) {
                myBitmap.recycle();
                myBitmap = null;
            }
            if (myBitma != null) {
                myBitma.recycle();
                myBitma = null;
            }

            try {

                BitmapFactory.Options bfOptions = new BitmapFactory.Options();
                bfOptions.inJustDecodeBounds = false;
                bfOptions.inPreferredConfig = Bitmap.Config.RGB_565;
                bfOptions.inDither = true;

                File file = new File(path_img);


                FileInputStream fs = null;
                if (file.exists()) {


                    try {
                        fs = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Log.e("Camera", "Image Picker PATH::::" + path_img);
                    try {
                        if (fs != null) {
                            myBitma = BitmapFactory.decodeFile(file.getAbsolutePath(), bfOptions);
                            ByteArrayOutputStream bao = new ByteArrayOutputStream();
                            if (myBitma != null) {
                                myBitma.compress(Bitmap.CompressFormat.JPEG, 80, bao);
                                myBitmap = getResizedBitmap(myBitma, 400, 400);
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        myBitma.recycle();
                        myBitma = null;

                    } finally {
                        if (fs != null) {
                            try {
                                fs.close();
                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                        }
                    }

                    Log.d("Bitmap", "Actual bitmap item::Width:" + myBitmap.getWidth()
                            + "   Height:" + myBitmap.getHeight());


                    Log.d("setting", "img on bitmap from camera");
                    rotatedBitmap = gettingRotatedBitmap(myBitmap, path_img);

                    Log.d("Bitmap", "Rotaated item::Width:" + rotatedBitmap.getWidth()
                            + "   Height:" + rotatedBitmap.getHeight());

                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bao);
                    Log.d("inside", "camera");

                    Log.d("11111111111111111", "1111111111111");
//                imgresume = path_img;
                    byte_arr = bao.toByteArray();


                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (dlg.isShowing() && dlg != null) {
                dlg.dismiss();
            }
            try {

                Log.d("load", "photo upload:" + rotatedBitmap.getHeight() + "   width:" + rotatedBitmap.getWidth());
                //myprofile_img_photo.setImageBitmap(rotatedBitmap);
                img_camera2.setImageBitmap(rotatedBitmap);
                //setcircularimage(rotatedBitmap);
                // helper.company_logo=rotatedBitmap;
                //counter = "photo";
                img_camera.setVisibility(View.GONE);
                img_camera2.setVisibility(View.VISIBLE);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    protected Bitmap gettingRotatedBitmap(Bitmap bitmap_leftn, String path) {
        Bitmap rotatedBitmap = null;

        int width = bitmap_leftn.getWidth();
        int height = bitmap_leftn.getHeight();


        ExifInterface exif;
        try {
            exif = new ExifInterface(path);
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);


            Log.e("bitmap", "11 width:"
                    + width + "   height:" + height + "   filepath:" + path);

            Log.d("ROTATION", "ORITATION::::" + rotation
                    + "  rotation 90:" + ExifInterface.ORIENTATION_ROTATE_90
                    + "  rotation 180:" + ExifInterface.ORIENTATION_ROTATE_180 +
                    "   rotation 270:" + ExifInterface.ORIENTATION_ROTATE_270);
            //if(width > height){
//
            Log.e("bitmap", "22 width:" + width + "   height:" + height);
            Matrix matrix = new Matrix();

            switch (rotation) {
                case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    matrix.setScale(-1, 1);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.setRotate(180);
                    break;

                case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    matrix.setRotate(180);
                    matrix.postScale(-1, 1);
                    break;

                case ExifInterface.ORIENTATION_TRANSPOSE:
                    matrix.setRotate(90);
                    matrix.postScale(-1, 1);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_90:
                    Log.e("bitmap", "SWITCH MATRIX:");
                    matrix.setRotate(90);
                    break;

                case ExifInterface.ORIENTATION_TRANSVERSE:
                    matrix.setRotate(-90);
                    matrix.postScale(-1, 1);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.setRotate(-90);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    break;
            }
            Log.e("bitmap", "33111 width:" + width + "   height:" + height);

            rotatedBitmap = Bitmap.createBitmap(bitmap_leftn, 0, 0, 400, 400, matrix, true);

            Log.e("bitmap", "33222 width:" + rotatedBitmap.getWidth() + "   height:" + rotatedBitmap.getHeight());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return rotatedBitmap;
    }


    private void setcircularimage(Bitmap myBitmap) {
        Bitmap newBItmap = getResizedBitmap(myBitmap, 450, 450);
        Bitmap circleBitmap = Bitmap.createBitmap(newBItmap.getWidth(), newBItmap.getHeight(), Bitmap.Config.ARGB_8888);

        BitmapShader shader = new BitmapShader(newBItmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        paint.setAntiAlias(true);
        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(newBItmap.getWidth() / 2, newBItmap.getHeight() / 2, newBItmap.getWidth() / 2, paint);


        Log.d("11111111111111111", "1111111111111");
//                imgresume = path_img;
     /*   photo_image1_add.setImageBitmap(circleBitmap);
        photo_image2_add.setImageBitmap(circleBitmap);
        photo_image3_add.setImageBitmap(circleBitmap);*/


    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        Log.d("SellItemsNew", "GET_RESIZED_BITMAP:");
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);
        return resizedBitmap;
    }
    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(RealNewsActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            Toast.makeText(RealNewsActivity.this, "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(RealNewsActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA},RequestPermissionCode);

        }
    }



}

