




package thieuhoang.nhan.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thieuhoang.nhan.myapplication.R;

public class login extends AppCompatActivity {
    EditText edtUserName,edtPassword;
    Button btnLogin;
    public static String username;
    public static String pw;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControls();
        setEvents();
    }

    private void setEvents() {
        btnLogin.setOnClickListener(v->{
            if(edtUserName.getText().toString().equals("admin") && edtPassword.getText().toString().equals("admin")){
                Intent intent = new Intent(login.this,brand.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this,R.string.warring_account,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setControls() {
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }
}
