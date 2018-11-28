package com.a360degree.a360darage.AccountPages;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a360degree.a360darage.App.AppConfig;
import com.a360degree.a360darage.App.AppSingletonRequestQueue;
import com.a360degree.a360darage.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    ImageButton btnClose;
    TextInputEditText etUserName, etPassWord;
    Button btnLogin;
    TextView txtGoToRegister, txtRememberPassword;
    RelativeLayout btnSingInWhitGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    void init() {

        btnClose = findViewById(R.id.btn_close);
        etUserName = findViewById(R.id.et_username_or_email);
        etPassWord = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        txtGoToRegister = findViewById(R.id.txt_go_to_register);
        txtRememberPassword = findViewById(R.id.txt_remember);
        btnSingInWhitGoogle = findViewById(R.id.btn_reg_by_google);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameOrEmail = etUserName.getText().toString();
                String password = etPassWord.getText().toString();

                // Check for empty data in the form
                if (!usernameOrEmail.isEmpty()
                        && !password.isEmpty()
                        && usernameOrEmail.length() > 3
                        && usernameOrEmail.length() < 25
                        && password.length() < 30) {

//                    //recognize username type
//                    if (isEmail(usernameOrEmail)) {
//                        USERNAME_TYPE = "email";
//                    } else {
//                        USERNAME_TYPE = "username";
//                    }

                    // login user
                    LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
                    loginAsyncTask.execute(usernameOrEmail, password);

                } else {
                    // Prompt user to enter credentials
                    if (usernameOrEmail.isEmpty()) {
                        etUserName.setError("لطفا نام کاربری یا ایمیل خود را وارد کنید");
                    }
                    if (usernameOrEmail.length() < 3) {
                        etUserName.setError("نام کاربری شما باید حداقل 3 کاراکتر باشد");
                    }
                    if (usernameOrEmail.length() > 25) {
                        etUserName.setError("نام کاربری شما باید کمتر از 25 کاراکتر باشد");
                    }
                    if (password.isEmpty()) {
                        etPassWord.setError("لطفا رمز عبور خود را وارد کنید");
                    }
                    if (password.length() <= 6) {
                        etPassWord.setError("رمزعبور شما باید حداقل 6 کاراکتر باشد");
                    }
                }
            }
        });

        txtGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                LoginActivity.this.finish();
                /* animation ezafe shavad */
            }
        });

        txtRememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSingInWhitGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private boolean isEmail(String usernameOrEmail) {
        return usernameOrEmail.contains("@gmail.com")
                || usernameOrEmail.contains("@yahoo.com")
                || usernameOrEmail.contains("@outlook.com");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    private class LoginAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            btnLogin.setEnabled(false);

            //customize shavad
            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {
            checkLogin(strings[0], //username or email
                    strings[1]); //password
            return null;
        }

        private void checkLogin(final String usernameOrEmail, final String password) {
            // Tag used to cancel the request
            String TAG_REQ_LOGIN = "req_login";

            StringRequest strLoginReq = new StringRequest(Request.Method.POST,
                    AppConfig.URL_LOGIN, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Login Response: " + response.toString());

                    try {
                        JSONObject jObjResponse = new JSONObject(response);
                        int code = jObjResponse.getInt("code");

                        // Check for error node in json
                        if (code == 400) {
                            // user successfully logged in
                            // Create login session


                            // Now store the user in SQLite
                    /*    String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");

                        // Inserting row in users table
//                        db.addUser(name, email, uid, created_at);
*/

                            // tran(username, password);
                        } else {
                            // Error in login. Get the error message
                            String errorMsg = jObjResponse.getString("error_msg");
                            Toast.makeText(getApplicationContext(),
                                    errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Login Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", usernameOrEmail);
                    params.put("password", password);

                    return params;
                }

            };

            // Adding request to request queue
            AppSingletonRequestQueue.getInstance(getApplicationContext()).addToRequestQueue(strLoginReq);
        }
    }
}
