package com.smile.taobaodemo.model.entity;

/**
 * @author Smile Wei
 * @since 2016/8/10.
 */
public class HomeBase {
    public static final String NULL_STRING = "";
    public static final double ZERO = 0;
    public static final int TYPE_PLACE = -1;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CAROUSEL = 3;
    public static final int TYPE_CATEGORY = 4;
    public static final int TYPE_HEADLINE = 5;
    public static final int TYPE_DIVIDER = 6;
    public static final int TYPE_LIVE = 7;
    public static final int TYPE_HOT = 9;
    public static final int TYPE_RECOMMEND = 12;


    private long id;
    private String name;
    private String url;
    private double price;
    private int type = TYPE_RECOMMEND;
    private int spanCount = 150;

    public HomeBase() {
    }

    public HomeBase(long id, double price, String url, String name, int type, int spanCount) {
        this.id = id;
        this.price = price;
        this.url = url;
        this.name = name;
        this.type = type;
        this.spanCount = spanCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return "￥";
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }
}
