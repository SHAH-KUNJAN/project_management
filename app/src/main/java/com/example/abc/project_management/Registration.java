package com.example.abc.project_management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {

    private static final String TAG = "Registration ";
    EditText ed1,ed3,ed4,ed5,ed6,ed7;
    Button b1;
    TextView txtLogin;
    String MobilePattern = "[0-9]{10}";
    String LicencePattern = "[a-z0-9]{14}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ed1=(EditText) findViewById(R.id.et1);
        ed3=(EditText) findViewById(R.id.et3);
        ed4=(EditText) findViewById(R.id.et4);
        ed5=(EditText) findViewById(R.id.et5);
        ed6=(EditText) findViewById(R.id.et6);
        ed7=(EditText) findViewById(R.id.et7);



        txtLogin=(TextView) findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,LoginActivity.class));
            }
        });

        b1=(Button)findViewById(R.id.btnSignUp);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });



    }
    public void signup()
    {
        String emailid=ed1.getText().toString().trim();
        String password=ed3.getText().toString().trim();
        String mobileno=ed4.getText().toString().trim();
        String licence=ed5.getText().toString().trim();
        String firstname=ed6.getText().toString().trim();
        String lastname=ed7.getText().toString().trim();

        if(firstname.isEmpty()){
            ed6.setError("First Name is required");
            ed6.requestFocus();
            return;
        }
        if(lastname.isEmpty()){
            ed7.setError("Last Name is required");
            ed7.requestFocus();
            return;
        }
        if(emailid.isEmpty()){
            ed1.setError("Email is required");
            ed1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()){
            ed1.setError("Enter a valid email");
            ed1.requestFocus();
            return;
        }
        if(password.isEmpty()){
            ed3.setError("Password is required");
            ed3.requestFocus();
            return;
        }
        if(password.length()<6){
            ed3.setError("Password Should be  atleast 6 character long ");
            ed3.requestFocus();
            return;
        }
        if(licence.isEmpty()){
            ed5.setError("LicenceNo is required");
            ed5.requestFocus();
            return;
        }
        if((!licence.matches(LicencePattern)) || licence.length()<14){
            ed5.setError("LicenceNo Must be  14 character long ");
            ed5.requestFocus();
            return;
        }
        if(mobileno.isEmpty()){
            ed4.setError("MobileNo is required");
            ed4.requestFocus();
            return;
        }
        if((!mobileno.matches(MobilePattern))|| mobileno.length()<10){
            ed4.setError("MobileNo Must be 10 Digit long ");
            ed4.requestFocus();
            return;
        }



        UserDetails user = new UserDetails(emailid,password,Long.parseLong(mobileno),licence,firstname,lastname);

        Call<ResponseBody> call =RetrofitClient
                .getInstance()
                .getApi()
                .add(user);


        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


               // Log.i(TAG, "onResponse: " + response);

                  if(response.isSuccessful()){
                      try {
                          String s=response.body().string();
                         // startActivity(new Intent(Registration.this,LoginActivity.class));
                         // Toast.makeText(Registration.this,s,Toast.LENGTH_SHORT).show();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                    /*  try {
                          JSONObject jsonObject=new JSONObject(response.body().string());
                          Toast.makeText(Registration.this,jsonObject.toString(),Toast.LENGTH_SHORT).show();
                      } catch (IOException e) {
                          e.printStackTrace();
                      } catch (JSONException e) {
                          e.printStackTrace();
                      }*/
                       // Toast.makeText(Registration.this, response.body() != null ? response.body().toString() : null, Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("jojowqwq",response.errorBody().toString());
                        Toast.makeText(Registration.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                      try {
                          Log.i(TAG, "onResponse: " + response.errorBody().string());
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }

                /*try {
                    if(response.isSuccessful())
                    ResponseBody s = response.body();
                    Log.v("Response ",s.);
                    JSONObject jsonObject=new JSONObject(s);

                    Toast.makeText(Registration.this,jsonObject.toString(),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(Registration.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
