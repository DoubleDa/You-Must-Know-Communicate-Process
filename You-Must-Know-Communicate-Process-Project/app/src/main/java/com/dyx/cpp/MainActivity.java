package com.dyx.cpp;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.TextView;

import com.dyx.cpp.aidl.ICat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.tv_result)
    TextView tvResult;

    private ICat mICat;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mICat = ICat.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mICat = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bindRemoteService();

    }

    private void bindRemoteService() {
        Intent service = new Intent();
        service.setAction("com.dyx.cpp.service.AIDLService");
        bindService(service, connection,BIND_AUTO_CREATE);
    }


    @OnClick(R.id.btn_search)
    public void onClick() {
        try {
            tvResult.setText(mICat.getColor() + "\n" + mICat.getWeight());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
