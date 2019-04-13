package pers.jiangyu.yuread.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseActivity;
import pers.jiangyu.yuread.contract.InformContract;
import pers.jiangyu.yuread.databinding.ActivityInformationBinding;
import pers.jiangyu.yuread.presenter.InformPresenter;

public class InformationActivity extends BaseActivity<ActivityInformationBinding, InformContract.Presenter>implements InformContract.View {

    private String userId;
    private String nickname;
    private String address;
    private String motto;
    private String myIcon;

    private static final int TAKE_PHOTO = 0;

    private Uri imageUri;

    public static void actionStart (Activity activity,String userId){
        Intent intent = new Intent(activity,InformationActivity.class);
        intent.putExtra("userId",userId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setToolBarTittle("我的资料");
        setUseBackAndMenu(true,false);
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void isSucceeded() {
        Toast.makeText(InformationActivity.this,"资料修改成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isFailed() {
        Toast.makeText(InformationActivity.this,"请稍后重试",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView() {
        userId = getIntent().getStringExtra("userId");

        dataBinding.submitInform.setOnClickListener(view ->{
            dataBinding.myPhoneNum.setText(userId);
            nickname = dataBinding.nickname.getText().toString();
            address = dataBinding.myAddress.getText().toString();
            motto = dataBinding.myMotto.getText().toString();
            presenter.submitInform(userId,nickname,address,motto,myIcon);

        } );

    }

    /**
     * 显示修改头像的对话框
     */
    public void showChoosePicDialog(View v) {
        Log.d("dialog","yes");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case TAKE_PHOTO: // 拍照
                        takePhoto();
                        break;
                }
            }
        });
        builder.create().show();
    }
    private void takePhoto(){
        File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
        try {
            if(outputImage.exists()){
                outputImage.delete();
            }outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(InformationActivity.this,
                    "pers.jiangyu.yuread.fileprovider",outputImage);
        }else {
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       switch (requestCode){
           case TAKE_PHOTO:{
               if(resultCode ==RESULT_OK){
                   try{
                       Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                       dataBinding.myIcon.setImageBitmap(bitmap);
                       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                       bitmap.compress(Bitmap.CompressFormat.PNG,60,outputStream);
                       byte[] imageByte = outputStream.toByteArray();
                       myIcon = Base64.encodeToString(imageByte,Base64.DEFAULT);
                   }catch(FileNotFoundException e){
                       e.printStackTrace();
                   }
               }

           }
       }
    }

    @Override
    protected InformContract.Presenter getPresenter() {
        return new InformPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_information;
    }
}
