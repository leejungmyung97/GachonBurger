package gachon.mpclass.myapplication;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;

public class PayActivity extends AppCompatActivity implements View.OnClickListener{
    Button send;
    TextView sum;
    private TextView timer;
    private FirebaseAuth mAuth;
    private TimePickerDialog.OnTimeSetListener callbackMethod;
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;
    private FirebaseUser user;
    private String username = null;
    private int point;
    private int pay_sum;

    ImageView timeImage = null;



    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        send = (Button)findViewById(R.id.send);
        sum = (TextView)findViewById(R.id.sum);


        timeImage = (ImageView)findViewById(R.id.paymentImage);

        Intent intent = getIntent();
        String[] arr1 = intent.getExtras().getStringArray("americano");
        String[] arr2 = intent.getExtras().getStringArray("latte");
        String[] arr3 = intent.getExtras().getStringArray("capuchino");
        String[] arr4 = intent.getExtras().getStringArray("camomile");

        int am = Integer.parseInt(arr1[2]);
        int la = Integer.parseInt(arr2[2]);
        int ca = Integer.parseInt(arr3[2]);
        int mi = Integer.parseInt(arr4[2]);
        pay_sum = am+la+ca+mi;

        sum.setText(NumberFormat.getCurrencyInstance().format(am+la+ca+mi));

        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mAuth = FirebaseAuth.getInstance();
                user = FirebaseAuth.getInstance().getCurrentUser();
                username = mAuth.getCurrentUser().getDisplayName();
                DataSnapshot data =dataSnapshot.child("users").child(username).child("point");
                point = Integer.parseInt(data.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        username = mAuth.getCurrentUser().getDisplayName();
        switch (v.getId()) {

            case R.id.send:

                sum = (TextView) findViewById(R.id.sum);
                timer = (TextView) findViewById(R.id.timer);
                Intent intent = getIntent();
                String[] arr1 = intent.getExtras().getStringArray("americano");
                String[] arr2 = intent.getExtras().getStringArray("latte");
                String[] arr3 = intent.getExtras().getStringArray("capuchino");
                String[] arr4 = intent.getExtras().getStringArray("camomile");


                if(true)
                {
                    Toast.makeText(PayActivity.this, "주문이 완료되었습니다.", Toast.LENGTH_LONG).show();
                    finish();
                    break;
                }
        }
    }



    public void OnClickHandler(View view)
    {
        TimePickerDialog dialog = new TimePickerDialog(this, callbackMethod, 12, 0, false);
        dialog.show();
    }

    private void writeNewPost(String username, String order1, String order2, String order3, String order4, String time, String sum) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("users").child(username).child("order");
        User user = new User(username, order1, order2,order3,order4, time, sum);
        mDatabase.setValue(user);
    }
}
