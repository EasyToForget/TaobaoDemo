package com.smile.taobaodemo.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Smile Wei
 * @since 2016/8/10.
 */
public class HomeBase implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeDouble(this.price);
        dest.writeInt(this.type);
        dest.writeInt(this.spanCount);
    }

    protected HomeBase(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.url = in.readString();
        this.price = in.readDouble();
        this.type = in.readInt();
        this.spanCount = in.readInt();
    }

    public static final Parcelable.Creator<HomeBase> CREATOR = new Parcelable.Creator<HomeBase>() {
        @Override
        public HomeBase createFromParcel(Parcel source) {
            return new HomeBase(source);
        }

        @Override
        public HomeBase[] newArray(int size) {
            return new HomeBase[size];
        }
    };
}
