package com.example.senior_walker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bottom1Fragment extends Fragment {

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> listData = new ArrayList<>();

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        getData();
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bottom1, container, false);

        //recyclerview
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(listData);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getData();

        return v;
    }

    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("테스트1", "테스트2", "테스트3", "테스트4", "테스트5", "테스트6", "테스트7", "테스트8",
                "테스트9", "테스트10", "테스트11", "테스트12", "테스트13", "테스트14", "테스트15", "테스트16");
        List<String> listContent = Arrays.asList(
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다.",
                "테스트입니다."
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp,
                R.drawable.ic_email_black_24dp
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            RecyclerData data = new RecyclerData();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}