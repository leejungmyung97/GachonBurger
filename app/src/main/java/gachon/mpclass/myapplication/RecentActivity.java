package gachon.mpclass.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import gachon.mpclass.myapplication.R;

public class RecentActivity extends AppCompatActivity {
    TextView name;
    TextView time;
    TextView sum;
    TextView am;
    TextView lt;
    TextView cp;
    TextView cm;


    FirebaseUser user;
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_order);

        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = (TextView)findViewById(R.id.recent_name);
                time = (TextView)findViewById(R.id.recent_time);
                sum = (TextView)findViewById(R.id.recent_pay);
                am = (TextView)findViewById(R.id.recent_order1);
                lt = (TextView)findViewById(R.id.recent_order2);
                cp = (TextView)findViewById(R.id.recent_order3);
                cm = (TextView)findViewById(R.id.recent_order4);
                user = FirebaseAuth.getInstance().getCurrentUser();
                String username = user.getDisplayName();
                DataSnapshot data =dataSnapshot.child("users").child(username).child("order").child("sum");
                DataSnapshot data2 =dataSnapshot.child("users").child(username).child("order").child("username");
                DataSnapshot data3 =dataSnapshot.child("users").child(username).child("order").child("time");
                DataSnapshot data4 =dataSnapshot.child("users").child(username).child("order").child("order1");
                DataSnapshot data5 =dataSnapshot.child("users").child(username).child("order").child("order2");
                DataSnapshot data6 =dataSnapshot.child("users").child(username).child("order").child("order3");
                DataSnapshot data7 =dataSnapshot.child("users").child(username).child("order").child("order4");
                if(data.getValue() != null) {
                    time.setText("???????????? : " + data3.getValue().toString());
                    name.setText(data2.getValue().toString() + "?????? ?????? ?????? ?????? ?????????.");
                    sum.setText("?????? ?????? " + data.getValue().toString());
                    am.setText("????????? ?????? ?????? ?????? " + data4.getValue().toString());
                    lt.setText("?????? ?????? ?????? ?????? " + data5.getValue().toString());
                    cp.setText("?????? ?????? ?????? " + data6.getValue().toString());
                    cm.setText("?????? ?????? ?????? " + data7.getValue().toString());
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
