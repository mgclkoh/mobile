package com.example.viewpagerex1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class MyViewPager extends PagerAdapter {
    //xml객체로 만들기위해 3개 지정
    Context context;
    int[] images;
    LayoutInflater layoutInflater;

    public MyViewPager(Context context, int[] images) {
        this.context = context;
        this.images = images;
        //inflater을 넣어 형을 강제로 맞춤
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;  //page개수는 image개수
    }

    //linearlayout으로 만들어진 아이가 원하는 객체와 동일한지 확인
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);  //최상위에 붙일것이 아님
        //view객체의 내부속성
        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        Objects.requireNonNull(container).addView(itemView);   //있으면 넘어감, 없으면 itmView실행하여 넣음
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
