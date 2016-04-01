package com.example.androidcodetest;

import com.google.zxing.WriterException;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private Button btnSaoMiao;
	private TextView tvShow;
	private EditText etInput;
	private Button btnDisplay;
	private ImageView ivQr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSaoMiao=(Button) findViewById(R.id.btnSaoMiao);
        tvShow=(TextView) findViewById(R.id.tvShow);
        etInput=(EditText) findViewById(R.id.etInput);
        btnDisplay=(Button) findViewById(R.id.btnShenChen);
        ivQr=(ImageView) findViewById(R.id.ivQr);
        /**
         * 点击开始扫描二维码
         */
        btnSaoMiao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,CaptureActivity.class);
				startActivityForResult(intent, 0);
			}
		});
        /**
         * 生成二维码
         */
        btnDisplay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String input=etInput.getText().toString();
				if (TextUtils.isEmpty(input)) {
					Toast.makeText(MainActivity.this, "请输入内容...", Toast.LENGTH_SHORT).show();
				}else {
					try {
						Bitmap bitmap= EncodingHandler.createQRCode(input, 400);
						ivQr.setImageBitmap(bitmap);
					} catch (WriterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("生成失败");
					}
				}
			}
		});
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	if (requestCode==0) {
			if (resultCode==RESULT_OK) {
				String qrCode=data.getStringExtra("result");
				tvShow.setText("内容："+qrCode);
			}
		}
    }
}
