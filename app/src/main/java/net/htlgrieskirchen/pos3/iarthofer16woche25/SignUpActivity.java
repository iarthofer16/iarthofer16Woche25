package net.htlgrieskirchen.pos3.iarthofer16woche25;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView username_TV;
    TextView password_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        username_TV = findViewById(R.id.email_TV);
        password_TV = findViewById(R.id.passwrd_TV);
    }

    public void signUp(View view) {

        String username = String.valueOf(username_TV.getText());
        String password = String.valueOf(password_TV.getText());

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("signUp", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                        } else {

                            Log.d("signUp", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });


    }

    public void signIn(View view) {
        String username = String.valueOf(username_TV.getText());
        String password = String.valueOf(password_TV.getText());

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("signIn", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                        } else {
                            Log.d("signIn", "Authentication failed!", task.getException());
                        }
                    }
                });
    }
}
