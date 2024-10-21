package com.example.bai5;

import android.content.SharedPreferences;
import android.media.MediaParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnXacNhan;
    EditText edtUserName, edtPassword;
    CheckBox checkBox;

    SharedPreferences share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_facebook);

        AnhXa();

        share = getSharedPreferences("dataLogin",MODE_PRIVATE);

        //lấy giá trị
        edtUserName.setText(share.getString("taiKhoan",""));
        edtPassword.setText(share.getString("matkhau",""));
        checkBox.setChecked(share.getBoolean("checked",false));

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if(username.equals("admin") && password.equals("admin")){
                    Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    if(checkBox.isChecked()){
                        SharedPreferences.Editor editor = share.edit();
                        editor.putString("taiKhoan",username);
                        editor.putString("matkhau",password);
                        editor.putBoolean("checked",true);
                        editor.commit();
                    }else{
                        SharedPreferences.Editor editor = share.edit();
                        editor.remove("taiKhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void AnhXa(){
        btnXacNhan = (Button) findViewById(R.id.buttonLogin);
        edtUserName = (EditText) findViewById(R.id.email);
        edtPassword = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.cbRemember);
    }
}