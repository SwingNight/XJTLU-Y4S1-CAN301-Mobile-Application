package cn.edu.xjtlu.testapp1210;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private DBOpenHelper mDBOpenHelper;
    EditText account_temp;
    EditText password_temp;
    static String account;
    String password;
    static int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account_temp = (EditText) findViewById(R.id.account);
        password_temp = (EditText) findViewById(R.id.password);
        mDBOpenHelper = new DBOpenHelper(this);
    }

    public void Login(View v) {
        String user = account_temp.getText().toString().trim();
        String pwd = password_temp.getText().toString().trim();
        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pwd)) {
            ArrayList<User> data = mDBOpenHelper.getAllData();
            boolean match = false;
            for (int i = 0; i < data.size(); i++) {
                User this_user = data.get(i);
                if (user.equals(this_user.getName()) && pwd.equals(this_user.getPassword())) {
                    match = true;
                    String classify;
                    classify = this_user.getCategory();
                    if (classify.equals("student")){
                        category = 0;
                    } else {
                        category = 1;
                    }
                    break;
                } else {
                    match = false;
                }
            }
            if (match) {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                account = user;
                password = pwd;
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please correct your password.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            Toast.makeText(this, "Please fill in the blank.", Toast.LENGTH_SHORT).show();
        }
    }
}