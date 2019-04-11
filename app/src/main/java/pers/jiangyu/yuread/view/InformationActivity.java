package pers.jiangyu.yuread.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseActivity;
import pers.jiangyu.yuread.contract.InformContract;
import pers.jiangyu.yuread.databinding.ActivityInformationBinding;

public class InformationActivity extends BaseActivity<ActivityInformationBinding, InformContract.Presenter>implements InformContract.View {

    private String userId;
    private String nickname;
    private String address;
    private String motto;

    private static final int TAKE_PHOTO = 1;

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

    }

    @Override
    public void isFailed() {

    }

    @Override
    protected void initView() {
        userId = getIntent().getStringExtra("userId");
        dataBinding.myPhoneNum.setText(userId);
        dataBinding.submitInform.setOnClickListener(view ->{
            nickname = dataBinding.nickname.getText().toString();
            address = dataBinding.myAddress.getText().toString();
            motto = dataBinding.myMotto.getText().toString();
        } );

    }

    /**
     * 显示修改头像的对话框
     */
    public void showChoosePicDialog(View v) {
        Log.d("dialog","yes");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case TAKE_PHOTO: // 拍照

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

               }

           }
       }
    }

    @Override
    protected InformContract.Presenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_information;
    }
}
