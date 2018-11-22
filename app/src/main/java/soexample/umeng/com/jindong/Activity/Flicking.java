package soexample.umeng.com.jindong.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.android.PermissionUtils;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;
import com.yzq.zxinglibrary.encode.CodeCreator;

import soexample.umeng.com.jindong.R;

public class Flicking extends AppCompatActivity {
    private TextView mTxt;
    private ImageView mImage;
    private EditText mEdit;
    private final int RESULT_CODE = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flicking);
        mTxt = (TextView) findViewById(R.id.tv_mess);
        mImage = (ImageView) findViewById(R.id.image);
        mEdit = (EditText) findViewById(R.id.edit);
        findViewById(R.id.sao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sCan();
            }
        });
        findViewById(R.id.dai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCaptrue(0);
            }
        });
        //不带logo
        findViewById(R.id.budai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCaptrue(1);
            }
        });

    }

    //扫一扫
    private void sCan() {
        PermissionUtils.permission(this, new PermissionUtils.PermissionListener() {
            @Override
            public void success() {
                Intent intent = new Intent(Flicking.this, CaptureActivity.class);
                ZxingConfig config = new ZxingConfig();
                config.setShowbottomLayout(false);
                config.setPlayBeep(true);
                intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                startActivityForResult(intent, RESULT_CODE);
            }
        });
    }

    private void setCaptrue(int type) {
        String message = mEdit.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "请输入一点东西啊", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        if (type == 0) {
            bitmap = null;
        }
        try {
            Bitmap bitmap2 = CodeCreator.createQRCode(message, 200, 200, bitmap);
            mImage.setImageBitmap(bitmap2);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
