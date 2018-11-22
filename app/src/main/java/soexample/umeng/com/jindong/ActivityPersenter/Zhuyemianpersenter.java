package soexample.umeng.com.jindong.ActivityPersenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Activity.MainActivity;
import soexample.umeng.com.jindong.Activity.Zhuyemian;
import soexample.umeng.com.jindong.Framnter.LeibiaoFreamer;
import soexample.umeng.com.jindong.Framnter.MiFramnter;
import soexample.umeng.com.jindong.Framnter.ShopFramner;
import soexample.umeng.com.jindong.Framnter.ShouyeFramnter;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;

public class Zhuyemianpersenter extends AgeInter {
    private List<Fragment> list = new ArrayList<>();
    private ViewPager viewPager;
    private ImageView img1, img2, img3, img4;
    private TextView zi1;
    private TextView zi2;
    private TextView zi4;
    private TextView zi3;

    @Override
    public int getLayoutId() {
        return R.layout.zhuyemian;
    }

    @Override
    public void initdata() {
        super.initdata();
        viewPager = get(R.id.vp_view_main);
        img1 = get(R.id.iv_img1_main);
        img2 = get(R.id.iv_img2_main);
        img3 = get(R.id.iv_img3_main);
        img4 = get(R.id.iv_img4_main);
        zi1 = get(R.id.zi1);
        zi2 = get(R.id.zi2);
        zi3 = get(R.id.zi3);
        zi4 = get(R.id.zi4);
        list.add(new ShouyeFramnter());
        list.add(new LeibiaoFreamer());
        list.add(new ShopFramner());
        list.add(new MiFramnter());
        MyAdapterFragment myAdapterFragment = new MyAdapterFragment(((Zhuyemian) context).getSupportFragmentManager());
        viewPager.setAdapter(myAdapterFragment);
        viewPager.setOffscreenPageLimit(4);
        setCilck(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                img1.setImageResource(R.drawable.index_yes);
                img2.setImageResource(R.drawable.list_no);
                img3.setImageResource(R.drawable.car_no);
                img4.setImageResource(R.drawable.me_no);
                zi1.setTextColor(Color.parseColor("#d43c3c"));
                zi2.setTextColor(Color.parseColor("#778899"));
                zi3.setTextColor(Color.parseColor("#778899"));
                zi4.setTextColor(Color.parseColor("#778899"));
            }
        }, R.id.iv_img1_main);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                img1.setImageResource(R.drawable.index_no);
                img2.setImageResource(R.drawable.list_yes);
                img3.setImageResource(R.drawable.car_no);
                img4.setImageResource(R.drawable.me_no);
                zi1.setTextColor(Color.parseColor("#778899"));
                zi2.setTextColor(Color.parseColor("#d43c3c"));
                zi3.setTextColor(Color.parseColor("#778899"));
                zi4.setTextColor(Color.parseColor("#778899"));

            }
        }, R.id.iv_img2_main);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                img1.setImageResource(R.drawable.index_no);
                img2.setImageResource(R.drawable.list_no);
                img3.setImageResource(R.drawable.car_yes);
                img4.setImageResource(R.drawable.me_no);
                zi1.setTextColor(Color.parseColor("#778899"));
                zi2.setTextColor(Color.parseColor("#778899"));
                zi3.setTextColor(Color.parseColor("#d43c3c"));
                zi4.setTextColor(Color.parseColor("#778899"));
            }
        }, R.id.iv_img3_main);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
                img1.setImageResource(R.drawable.index_no);
                img2.setImageResource(R.drawable.list_no);
                img3.setImageResource(R.drawable.car_no);
                img4.setImageResource(R.drawable.me_yes);
                zi1.setTextColor(Color.parseColor("#778899"));
                zi2.setTextColor(Color.parseColor("#778899"));
                zi3.setTextColor(Color.parseColor("#778899"));
                zi4.setTextColor(Color.parseColor("#d43c3c"));
            }
        }, R.id.iv_img4_main);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
                    img1.setImageResource(R.drawable.index_yes);
                    img2.setImageResource(R.drawable.list_no);
                    img3.setImageResource(R.drawable.car_no);
                    img4.setImageResource(R.drawable.me_no);
                    zi1.setTextColor(Color.parseColor("#d43c3c"));
                    zi2.setTextColor(Color.parseColor("#778899"));
                    zi3.setTextColor(Color.parseColor("#778899"));
                    zi4.setTextColor(Color.parseColor("#778899"));

                } else if (1 == position) {
                    img1.setImageResource(R.drawable.index_no);
                    img2.setImageResource(R.drawable.list_yes);
                    img3.setImageResource(R.drawable.car_no);
                    img4.setImageResource(R.drawable.me_no);
                    zi1.setTextColor(Color.parseColor("#778899"));
                    zi2.setTextColor(Color.parseColor("#d43c3c"));
                    zi3.setTextColor(Color.parseColor("#778899"));
                    zi4.setTextColor(Color.parseColor("#778899"));
                } else if (2 == position) {
                    img1.setImageResource(R.drawable.index_no);
                    img2.setImageResource(R.drawable.list_no);
                    img3.setImageResource(R.drawable.car_yes);
                    img4.setImageResource(R.drawable.me_no);
                    zi1.setTextColor(Color.parseColor("#778899"));
                    zi2.setTextColor(Color.parseColor("#778899"));
                    zi3.setTextColor(Color.parseColor("#d43c3c"));
                    zi4.setTextColor(Color.parseColor("#778899"));
                } else if (3 == position) {
                    img1.setImageResource(R.drawable.index_no);
                    img2.setImageResource(R.drawable.list_no);
                    img3.setImageResource(R.drawable.car_no);
                    img4.setImageResource(R.drawable.me_yes);
                    zi1.setTextColor(Color.parseColor("#778899"));
                    zi2.setTextColor(Color.parseColor("#778899"));
                    zi3.setTextColor(Color.parseColor("#778899"));
                    zi4.setTextColor(Color.parseColor("#d43c3c"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyAdapterFragment extends FragmentPagerAdapter {

        public MyAdapterFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }


}
