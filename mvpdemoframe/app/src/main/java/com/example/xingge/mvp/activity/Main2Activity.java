package com.example.xingge.mvp.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.xingge.mvp.R;
import com.example.xingge.mvp.app.App;
import com.example.xingge.mvp.base.BaseActivity;
import com.example.xingge.mvp.net.OkHttpUtils;
import com.example.xingge.mvp.net.callback.MyNetWorkCallback;

import java.io.File;
import java.io.IOException;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class Main2Activity extends BaseActivity implements View.OnClickListener {

    private Button button;
    private RelativeLayout activity_main2;

    //上传文件到服务器的地址(使用的时候替换成自己的服务器地址)
    private static final String POST_FILE_URL = "http://172.16.52.31:8080/FileUploadDemo/FileUploadServlet";
    private String filePath;


    private String INTENT_TYPE = "image/*";
    private int REQUESTCODE = 100;
    private ImageView imageView;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void init() {
        button = (Button) findViewById(R.id.button);
        activity_main2 = (RelativeLayout) findViewById(R.id.activity_main2);
        imageView = (ImageView) findViewById(R.id.imageView);

        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:


                //使用intent调用系统提供的相册功能，
//使用startActivityForResult是为了获取用户选择的图片
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(INTENT_TYPE);
                startActivityForResult(intent, REQUESTCODE);



                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Log.e("TAG--->onresult", "ActivityResult resultCode error");
            return;
        }

        //获得图片
        Bitmap bitmap = null;
        ContentResolver resolver = getContentResolver();
        if (requestCode == REQUESTCODE) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);//获得图片
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageView.setImageBitmap(bitmap);


        //获得路径
        if (requestCode == REQUESTCODE) {
            Uri uri = data.getData();
            uri = geturi(data);//解决方案
            String[] pro = {MediaStore.Images.Media.DATA};
            //好像是android多媒体数据库的封装接口，具体的看Android文档
            Cursor cursor = managedQuery(uri, pro, null, null, null);
            Cursor cursor1 = getContentResolver().query(uri, pro, null, null, null);
            //拿到引索
            int index = cursor1.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            //移动到光标开头
            cursor.moveToFirst();
            //最后根据索引值获取图片路径
            String path = cursor.getString(index);




            OkHttpUtils.getInstance().upload(POST_FILE_URL,path,null,"files", App.MEDIA_TYPE_PNG, new MyNetWorkCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    System.out.println("========"+s);
                    Toast.makeText(Main2Activity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    Main2Activity.this.finish();
                }

                @Override
                public void onError(int errorCode, String errorMsg) {
                    System.out.println("========"+errorMsg);
                    Toast.makeText(Main2Activity.this, "上传失败", Toast.LENGTH_SHORT).show();
                    Main2Activity.this.finish();
                }
            });

            Log.d("Tag--->path", path);

        }

    }

    /**
     * 解决小米手机上获取图片路径为null的情况
     * @param intent
     * @return
     */
    public Uri geturi(android.content.Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }



    //初始化上传文件的数据
    private String initUploadFile() {

        File file = null;
        file = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + File.separator + "test.txt");
        if (!file.exists() && !file.isFile()) {
            //txt文件
            filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + File.separator + "test.txt";
        } else {
            try {
                file.createNewFile();
                //txt文件
                filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        + File.separator + "test.txt";
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


       /*
       file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + File.separator + "a.jpg");
       if (file.exists()) {
            file.mkdir();
            fileNames.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + File.separator + "a.jpg"); //图片
        }*/
//        fileNames.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
//                + File.separator + "kobe.mp4"); //视频
//        fileNames.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
//                + File.separator + "xinnian.mp3"); //音乐
        return filePath;
    }


}
