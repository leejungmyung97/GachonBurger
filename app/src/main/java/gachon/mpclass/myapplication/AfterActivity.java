package gachon.mpclass.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AfterActivity extends AppCompatActivity implements View.OnClickListener {
    Button LogOut;
    Button Revoke;
    Button Order;
    Button menu;
    Button Charge;
    Button Chat;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_after);

        Revoke = (Button)findViewById(R.id.revoke);
        LogOut = (Button)findViewById(R.id.logout);
        Order = (Button)findViewById(R.id.order);
        menu = (Button)findViewById(R.id.menu);
        Charge = (Button)findViewById(R.id.pointCharge);
        Chat = (Button)findViewById(R.id.chat);

        mAuth = FirebaseAuth.getInstance();

        LogOut.setOnClickListener(this);
        Revoke.setOnClickListener(this);
        Order.setOnClickListener(this);
        menu.setOnClickListener(this);
        Charge.setOnClickListener(this);
        Chat.setOnClickListener(this);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void revokeAccess() {
        mAuth.getCurrentUser().delete();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                signOut();
                finishAffinity();
                break;
            case R.id.revoke:
                revokeAccess();
                finishAffinity();
                break;
            case R.id.order :
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.menu :
                Intent intent2 = new Intent(this, sideMenu.class);
                startActivity(intent2);
                break;
            case R.id.pointCharge :
                Intent intent3 = new Intent(this, MapActivity.class);
                startActivity(intent3);
                break;
            case R.id.chat :
                Intent intent4 = new Intent(this, ChatActivity.class);
                startActivity(intent4);
                break;
        }
    }


}
