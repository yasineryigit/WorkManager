package com.ossovita.workmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDatabase extends Worker {

    Context myContext;
    public RefreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.myContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        //Bize bir input verilecek, bunu al diyoruz
        Data data = getInputData();
        int myNumber = data.getInt("intKey",0);
        refreshDatabase(myNumber);
        return Result.success();
    }

    private void refreshDatabase(int myNumber){
        //sp deki veriyi 1 arttırır
        SharedPreferences sp = myContext.getSharedPreferences("com.ossovita.workmanager",Context.MODE_PRIVATE);
        int mySavedNumber = sp.getInt("myNumber",0);
        mySavedNumber = mySavedNumber + myNumber;
        System.out.println(mySavedNumber);
        sp.edit().putInt("myNumber",mySavedNumber);
    }
}
