package com.smile.taobaodemo.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Smile Wei
 * @since 2016/11/19.
 */

public class DescBase implements Parcelable {
    public static final int TYPE_DESC = 1;
    public static final int TYPE_TITLE = 2;
    public static final int TYPE_PICTURE = 3;
    private int type;
    private String name;
    private String url;

    public DescBase(int type, String name, String url){
        this.type = type;
        this.name = name;
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    protected DescBase(Parcel in) {
        this.type = in.readInt();
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<DescBase> CREATOR = new Parcelable.Creator<DescBase>() {
        @Override
        public DescBase createFromParcel(Parcel source) {
            return new DescBase(source);
        }

        @Override
        public DescBase[] newArray(int size) {
            return new DescBase[size];
        }
    };
}
