package com.superheroes.app.domain.usecases;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Result<P> {

    public enum Status {
        OK,
        ERROR
    }
    private final Status mStatus;
    private final P mData;

    public Result(@NonNull Result<P> wrappingResult) {
        this(wrappingResult.status(), wrappingResult.data());
    }


    public Result(@NonNull Status status, @Nullable P data) {
        mStatus = status;
        mData = data;
    }


    @Nullable
    public P data() {
        return mData;
    }


    @NonNull
    public Status status() {
        return mStatus;
    }
}
