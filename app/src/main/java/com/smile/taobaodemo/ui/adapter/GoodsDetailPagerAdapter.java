package com.smile.taobaodemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smile.taobaodemo.base.Type;
import com.smile.taobaodemo.model.entity.CommentBase;
import com.smile.taobaodemo.model.entity.DescBase;
import com.smile.taobaodemo.ui.fragment.GoodsCommentFragment;
import com.smile.taobaodemo.ui.fragment.GoodsDescFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Smile Wei
 * @since 2016/8/6.
 */
public class GoodsDetailPagerAdapter extends FragmentPagerAdapter {
    private String[] title;

    public GoodsDetailPagerAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                List<DescBase> list = new ArrayList<>();
                DescBase bean = new DescBase(DescBase.TYPE_DESC, "这件不买你就真的太傻了\n" +
                        "一点不怕告诉你们\n" +
                        "这件衣服是我做了所有衣服以来\n" +
                        "被周围人拿走最多的一件\n" +
                        "我怕我再不上架，都被拿空了\n" +
                        "管它男的女的都来要\n" +
                        "女的买回去男的来要\n" +
                        "店里小姑娘穿回去舍友来要\n" +
                        "连快递来拿件看了他们穿的也要\n" +
                        "人见人爱 波及范围相当广\n" +
                        "可能它的确太实用太保暖质量太喜人吧\n" +
                        "显瘦死了保暖死了 全用的今年最好的鸭绒\n" +
                        "我的羽绒系列绝对是闭眼入\n" +
                        "废话不说 明晚来抢\n" +
                        "之前说错了，是均码，男女都能穿", "");
                list.add(bean);
                list.add(new DescBase(DescBase.TYPE_PICTURE, "", "https://img.alicdn.com/bao/uploaded/i1/TB1sDqKLXXXXXanXXXXXXXXXXXX_!!0-item_pic.jpg_800x800.jpg"));
                list.add(new DescBase(DescBase.TYPE_PICTURE, "", "https://img.alicdn.com/bao/uploaded/i2/2171322350/TB2SqaTaNaJ.eBjSsziXXaJ_XXa_!!2171322350.jpg_800x800.jpg"));
                list.add(new DescBase(DescBase.TYPE_PICTURE, "", "https://img.alicdn.com/bao/uploaded/i4/TB1Ke9CJpXXXXa9XpXXXXXXXXXX_!!0-item_pic.jpg_800x800.jpg"));
                list.add(new DescBase(DescBase.TYPE_PICTURE, "", "https://asearch.alicdn.com/bao/uploaded/i2/159530341092325317/TB2mMMbaHBnpuFjSZFGXXX51pXa_!!0-saturn_solar.jpg_800x800.jpg"));
                list.add(new DescBase(DescBase.TYPE_PICTURE, "", "https://img.alicdn.com/bao/uploaded/i3/20821802/TB2S7E9abVkpuFjSspcXXbSMVXa_!!20821802.jpg_800x800.jpg"));
                return GoodsDescFragment.newInstance(list);
            }
            default: {
                List<CommentBase> list = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    CommentBase bean = new CommentBase();
                    bean.setContent("废话不说 明晚来抢");
                    bean.setPicture("https://cdn.56come.cn/upload/6571/2016/0428/8uxd63yvy4umfwuprwhsuqb5g.thumb.jpg");
                    bean.setProduct_score(5);
                    bean.setType(Type.TYPE_SHOW);
                    bean.setCreate_date(System.currentTimeMillis() - 10000000 * i);
                    bean.setUser_name("Smile" + i);
                    list.add(bean);
                }
                return GoodsCommentFragment.newInstance(list);
            }
        }
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
