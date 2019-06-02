package com.mp.senior_walker;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mp.senior_walker.activity.SpecificProfile;
import com.mp.senior_walker.activity.WalkerMainActivity;
import com.mp.senior_walker.activity.Walker_bottom1;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<MyItem> mItems = new ArrayList<>();

    public CustomAdapter(ArrayList<MyItem> a){
        this.mItems = a;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MyItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    public View getView(int position, View convertView, final ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        // 'listview_custom' Layout을 inflate하여 convertView 참조 획득
        if (convertView == null) {
            Log.d("converView = null", "");
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.customlayout, parent, false);
        }
        else{
            Log.d("converView != null", "");
        }
        // 'listview_custom'에 정의된 위젯에 대한 참조 획득
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.dogWalkerImg) ;
        final TextView tv_name = (TextView) convertView.findViewById(R.id.textView_name) ;
        final TextView tv_contents = (TextView) convertView.findViewById(R.id.textView_residence) ;
        final TextView moreBtn = (TextView) convertView.findViewById(R.id.btnMore) ;

        // 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용
        final MyItem myItem = getItem(pos);
        Glide.with(context).load(myItem.getUrlpath()).centerCrop().override(500).into(iv_img);

        // 각 위젯에 세팅된 아이템을 뿌려준다
        tv_name.setText(myItem.getName());
        tv_contents.setText(myItem.getContents());


        // (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)
        moreBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SpecificProfile.class);
                intent.putExtra("UID", myItem.getUid());
                context.startActivity(intent);
                Toast.makeText(context, pos + "번째 버튼 선택", Toast.LENGTH_SHORT).show();
            }
        });
        /*
        iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, pos + "번째 이미지 선택", Toast.LENGTH_SHORT).show();
            }
        });
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "선택: "+tv_name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        tv_contents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "선택: "+tv_contents.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });*/

        return convertView;
    }



}
