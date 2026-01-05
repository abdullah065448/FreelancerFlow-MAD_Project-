package com.example.freelancerflow;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ForgotPasswordActivity extends AppCompatActivity {

    FirebaseAuth auth;
    TextInputEditText emailInput;
    Button btnLogin;
    TextView tvResend, tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();

        emailInput = findViewById(R.id.edtEmail);
        btnLogin = findViewById(R.id.btnLogin);
        tvResend = findViewById(R.id.tvResend);
        tvBack = findViewById(R.id.tvBackToLogin);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void resetPassword() {

        String email = emailInput.getText().toString();

        // Check empty
        if (email.length() == 0) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check valid email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPasswordActivity.this, "Reset link sent to email", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "Failed to send reset link", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
