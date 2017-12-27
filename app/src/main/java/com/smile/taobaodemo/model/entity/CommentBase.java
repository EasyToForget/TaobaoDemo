package com.smile.taobaodemo.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.smile.taobaodemo.base.Type;

/**
 * @author Smile Wei
 * @since 2016/11/19.
 */

public class CommentBase implements Parcelable {

    private int type = Type.TYPE_SHOW;
    private String content;
    private int product_score;
    private String user_id;
    private long create_date;
    private String picture;
    private String user_name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProduct_score() {
        return product_score;
    }

    public void setProduct_score(int product_score) {
        this.product_score = product_score;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.content);
        dest.writeInt(this.product_score);
        dest.writeString(this.user_id);
        dest.writeLong(this.create_date);
        dest.writeString(this.picture);
        dest.writeString(this.user_name);
    }

    public CommentBase() {
    }

    protected CommentBase(Parcel in) {
        this.type = in.readInt();
        this.content = in.readString();
        this.product_score = in.readInt();
        this.user_id = in.readString();
        this.create_date = in.readLong();
        this.picture = in.readString();
        this.user_name = in.readString();
    }

    public static final Parcelable.Creator<CommentBase> CREATOR = new Parcelable.Creator<CommentBase>() {
        @Override
        public CommentBase createFromParcel(Parcel source) {
            return new CommentBase(source);
        }

        @Override
        public CommentBase[] newArray(int size) {
            return new CommentBase[size];
        }
    };
}
