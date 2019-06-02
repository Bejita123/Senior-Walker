package com.example.senior_walker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<MyItem> mItems = new ArrayList<>();

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

    public View getView(int position, View convertView, ViewGroup parent) {

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
        MyItem myItem = getItem(pos);

        // 각 위젯에 세팅된 아이템을 뿌려준다
        iv_img.setImageDrawable(myItem.getIcon());
        tv_name.setText(myItem.getName());
        tv_contents.setText(myItem.getContents());


        // (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)
        moreBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, pos + "번째 버튼 선택", Toast.LENGTH_SHORT).show();
            }
        });
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
        });

        return convertView;
    }


    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(Drawable img, String name, String contents) {

        MyItem mItem = new MyItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setIcon(img);
        mItem.setName(name);
        mItem.setContents(contents);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }
}
