package com.superheroes.app.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MarvelHero implements Parcelable {

    private int id;
    private String name;
    private String photo;
    private String realName;
    private String gender;
    private String power;
    private String intelligence;
    private String groups;

    private MarvelHero(@NonNull MarvelHero builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.photo = builder.photo;
        this.realName = builder.realName;
        this.gender = builder.gender;
        this.power = builder.power;
        this.intelligence = builder.intelligence;
        this.groups = builder.groups;
    }

    public MarvelHero() {

    }

    protected MarvelHero(Parcel in) {
        id = in.readInt();
        name = in.readString();
        photo = in.readString();
        realName = in.readString();
        gender = in.readString();
        power = in.readString();
        intelligence = in.readString();
        groups = in.readString();
    }

    public static final Creator<MarvelHero> CREATOR = new Creator<MarvelHero>() {
        @Override
        public MarvelHero createFromParcel(Parcel in) {
            return new MarvelHero(in);
        }

        @Override
        public MarvelHero[] newArray(int size) {
            return new MarvelHero[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(photo);
        dest.writeString(realName);
        dest.writeString(gender);
        dest.writeString(power);
        dest.writeString(intelligence);
        dest.writeString(groups);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }


}
