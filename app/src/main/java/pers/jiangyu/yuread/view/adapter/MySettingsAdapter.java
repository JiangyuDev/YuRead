package pers.jiangyu.yuread.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.bean.MySettings;

public class MySettingsAdapter extends ArrayAdapter<MySettings> {

    private int resourceId;

    public MySettingsAdapter(@NonNull Context context, int textResourceId, @NonNull List<MySettings> objects) {
        super(context, textResourceId, objects);
        this.resourceId = textResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MySettings meSetBean = getItem(position); //获取当前实例

        View view;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else {
            view = convertView;
        }
        ImageView imageView = (ImageView)view.findViewById(R.id.me_set_image);
        TextView textView = (TextView) view.findViewById(R.id.me_set_name);
        imageView.setImageResource(meSetBean.getImageId());
        textView.setText(meSetBean.getName());
        return view;
    }
}
