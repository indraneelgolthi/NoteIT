package thatbiriyaniguy.NoteIt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class StartingActivity extends AppCompatActivity {

    private ImageView btnReg, btnLog;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        btnLog =  findViewById(R.id.start_log_btn);
        btnReg =  findViewById(R.id.start_reg_btn);

        fAuth = FirebaseAuth.getInstance();
        updateUI();

        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                register();
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                login();
            }
        });
    }

    private void register(){
        Intent regIntent = new Intent(StartingActivity.this,Register.class);
        startActivity(regIntent);
    }

    private void login(){
        Intent logIntent = new Intent(StartingActivity.this,LandingPage.class);
        startActivity(logIntent);
    }


    private void updateUI(){
        if(fAuth.getCurrentUser()!= null){
            Log.i("StartingActivity", "fAuth != null");
            Intent startIntent = new Intent(StartingActivity.this,MainActivity.class);
            startActivity(startIntent);
            finish();

        }else{
            Log.i("StartingActivity","fAuth==null");
        }
    }
}
