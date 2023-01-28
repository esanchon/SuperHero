package com.superheroes.app.domain.usecases;


import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

public interface Callback<T> {
    @MainThread
    void onFinish(@NonNull Result<T> result);
}