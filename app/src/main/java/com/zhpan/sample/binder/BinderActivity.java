package com.zhpan.sample.binder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zhpan.sample.R;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.zhpan.sample.binder.server.GradeService.REQUEST_CODE;

public class BinderActivity extends AppCompatActivity {

    private IBinder mRemoteBinder;

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mRemoteBinder = iBinder;
            Toast.makeText(BinderActivity.this, "已连接远程服务，可以查询成绩了。", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteBinder = null;
            Toast.makeText(BinderActivity.this, "已断开远程服务", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        findViewById(R.id.btn_bind_service).setOnClickListener(view -> {
            String action = "android.intent.action.server.gradeservice";
            Intent intent = new Intent(action);
            intent.setPackage(getPackageName());
            bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        });
        findViewById(R.id.btn_find_grade).setOnClickListener(view -> {
            int grade = getStudentGrade("Anna");
            Toast.makeText(BinderActivity.this, "Anna grade is " + grade, Toast.LENGTH_SHORT).show();
        });
    }

    private int getStudentGrade(String name) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int grade = 0;
        data.writeString(name);
        try {
            if (mRemoteBinder == null) {
                Toast.makeText(BinderActivity.this, "Need Bind Remote Server...", Toast.LENGTH_SHORT).show();
                throw new IllegalStateException("Need Bind Remote Server...");
            }
            mRemoteBinder.transact(REQUEST_CODE, data, reply, 0);
            grade = reply.readInt();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return grade;
    }
}