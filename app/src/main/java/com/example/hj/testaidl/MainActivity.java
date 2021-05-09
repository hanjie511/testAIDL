package com.example.hj.testaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.example.hj.aidlserver.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface aidl;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidl=IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidl=null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService();
    }
    public void add(View view) throws RemoteException {
        int value =aidl.add(50,34);
        Toast.makeText(MainActivity.this,""+value,Toast.LENGTH_SHORT).show();
    }
    private void bindService(){
        Intent intent=new Intent();
        intent.setComponent(new ComponentName("com.example.hj.aidlserver","com.example.hj.aidlserver.MyService"));
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);

    }
}