package soexample.umeng.com.jindong.ActivityPersenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import soexample.umeng.com.jindong.Activity.MainActivity;
import soexample.umeng.com.jindong.Activity.SelectImagePopupWindow;
import soexample.umeng.com.jindong.Activity.Sheupthe;
import soexample.umeng.com.jindong.Activity.UploadService;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;

import static android.app.Activity.RESULT_OK;

public class Sheupthepersenter extends AgeInter {
    private static String path = "/sdcard/myHead/";//sd路径
    private Bitmap head;//头像Bitmap
    private ImageView imageView;

    @Override
    public int getLayoutId() {
        return R.layout.sethupthe;
    }

    @Override
    public void initdata() {
        super.initdata();

        imageView = (ImageView) get(R.id.tou);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"shzgdshdgshdghdg",Toast.LENGTH_LONG).show();
                show();
            }
        });
    }
    private void show() {
        new SelectImagePopupWindow(context,get(R.id.activity_layout),
                new SelectImagePopupWindow.OnSelectPictureListener() {
                    @Override
                    public void onTakePhoto() {  //拍照
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                "head.png")));
                        ((Sheupthe)context).startActivityForResult(intent2, 2);//采用ForResult打开
                    }

                    @Override
                    public void onSelectPicture() {  // 从手机相册选择
                        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        ((Sheupthe)context).startActivityForResult(intent1, 1);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 127);
        intent.putExtra("outputY", 127);
        intent.putExtra("scale", true);
        intent.putExtra("noFaceDetection", false);//不启用人脸识别
        intent.putExtra("return-data", true);
        ((Sheupthe)context).startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "/head.png";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //ok上传
    private void uploadImage(String path) {
        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");
        OkHttpClient mOkHttpClent = new OkHttpClient();
        File file = new File(path);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(mediaType)
                .addFormDataPart("file", "head.png",
                        RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("uid", "22007");

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url("http://www.zhaoapi.cn/file/upload")
                .post(requestBody)
                .build();
        Call call = mOkHttpClent.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                ((Sheupthe)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("TAG", e.getMessage());
                        Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ((Sheupthe)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private Context context;
    @Override
    public void getContext(Context context) {

        this.context=context;
    }

    public void onActvity(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.png");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras == null) {
                        return;
                    }
                    head = extras.getParcelable("data");
                    if (head != null) {

                        imageView.setImageBitmap(head);
                        String fileName = path + "/head.png";//图片名字
                        setPicToView(head);//保存在SD卡中
                        File file1 = new File(fileName);
                        //uploadPic(file1);
                        uploadImage(fileName);
                    }
                }
                break;
            default:
                break;
        }

    }
}



