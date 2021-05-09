package com.example.hj.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service{
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  iBinder;
    }
    private IBinder iBinder=new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
        @Override
        public int add(int a, int b) throws RemoteException {
            return a+b;
        }
    };
}