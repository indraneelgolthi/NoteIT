package thatbiriyaniguy.NoteIt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LandingPage extends AppCompatActivity {

    private ImageView btnLogIn;
    private EditText inputEmail, inputPass;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        inputEmail = findViewById(R.id.input_log_email);
        inputPass = findViewById(R.id.input_log_pass);

        btnLogIn = findViewById(R.id.btn_log);
        fAuth = FirebaseAuth.getInstance();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lEmail = inputEmail.getText().toString().trim();
                String lPass = inputPass.getText().toString().trim();

                if(!TextUtils.isEmpty(lEmail) && !TextUtils.isEmpty(lPass)){
                    logIn(lEmail,lPass);
                }
            }
        });
    }

    private void logIn(String email, String password){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing in, please wait...");
        progressDialog.show();

        fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){

                    Intent mainIntent = new Intent(LandingPage.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    Toast.makeText(LandingPage.this,"Sign in successful",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LandingPage.this,"Error: " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
