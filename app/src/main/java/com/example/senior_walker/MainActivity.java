package com.example.senior_walker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

//    private RecyclerAdapter adapter;
//
    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private Bottom1Fragment bottom1Fragment = new Bottom1Fragment();
    private Bottom2Fragment bottom2Fragment = new Bottom2Fragment();
    private Bottom3Fragment bottom3Fragment = new Bottom3Fragment();
    private Bottom4Fragment bottom4Fragment = new Bottom4Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        init();
//        getData();
//
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, bottom1Fragment).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        transaction.replace(R.id.frame_layout, bottom1Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu2: {
                        transaction.replace(R.id.frame_layout, bottom2Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu3: {
                        transaction.replace(R.id.frame_layout, bottom3Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu4: {
                        transaction.replace(R.id.frame_layout, bottom4Fragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });
    }

//    private void init() {
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        adapter = new RecyclerAdapter();
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void getData() {
//        // 임의의 데이터입니다.
//        List<String> listTitle = Arrays.asList("테스트1", "테스트2", "테스트3", "테스트4", "테스트5", "테스트6", "테스트7", "테스트8",
//                "테스트9", "테스트10", "테스트11", "테스트12", "테스트13", "테스트14", "테스트15", "테스트16");
//        List<String> listContent = Arrays.asList(
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다.",
//                "테스트입니다."
//        );
//        List<Integer> listResId = Arrays.asList(
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp,
//                R.drawable.ic_email_black_24dp
//        );
//        for (int i = 0; i < listTitle.size(); i++) {
//            // 각 List의 값들을 data 객체에 set 해줍니다.
//            RecyclerData data = new RecyclerData();
//            data.setTitle(listTitle.get(i));
//            data.setContent(listContent.get(i));
//            data.setResId(listResId.get(i));
//
//            // 각 값이 들어간 data를 adapter에 추가합니다.
//            adapter.addItem(data);
//        }
//
//        // adapter의 값이 변경되었다는 것을 알려줍니다.
//        adapter.notifyDataSetChanged();
//    }
}
