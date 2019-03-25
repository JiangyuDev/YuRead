package pers.jiangyu.yuread.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import pers.jiangyu.yuread.R;

public class MainActivity extends AppCompatActivity {

    public static void actionStart(Activity activity, String userId){
        Intent intent = new Intent(activity,MainActivity.class);
        intent.putExtra("userId",userId);
        activity.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
