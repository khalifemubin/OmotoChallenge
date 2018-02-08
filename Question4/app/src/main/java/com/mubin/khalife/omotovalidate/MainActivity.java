package com.mubin.khalife.omotovalidate;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText userName, userPhone, userEmail;
    Spinner userCity;
    RelativeLayout relSelectCity;
    boolean submitClicked = false;
    Drawable originalDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userName = (EditText) findViewById(R.id.userName);
        userPhone = (EditText) findViewById(R.id.userPhone);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userCity = (Spinner) findViewById(R.id.userCity);
        relSelectCity = (RelativeLayout) findViewById(R.id.relSelectCity);

       originalDrawable = userName.getBackground();

        String[] arrUserCity = getResources().getStringArray(R.array.userCity);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrUserCity);
        userCity.setAdapter(adapter);


        Button btnValidate = (Button) findViewById(R.id.btnValidate);
        btnValidate.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               submitClicked = true;
                                               boolean bValidateFurther = true;
                                               if (TextUtils.isEmpty(userName.getText().toString().trim())) {
                                                   userName.setBackgroundResource(R.color.colorError);
                                                   bValidateFurther = false;
                                                   userName.requestFocus();
                                               } else if (validateName(userName.getText().toString()) && bValidateFurther) {
                                                   userName.setBackgroundResource(R.color.colorError);
                                                   bValidateFurther = false;
                                                   userName.requestFocus();
                                               } else {
                                                   //userName.setBackgroundResource(0);
                                                   if (TextUtils.isEmpty(userPhone.getText().toString().trim()) && bValidateFurther) {
                                                       userPhone.setBackgroundResource(R.color.colorError);
                                                       bValidateFurther = false;
                                                       userPhone.requestFocus();
                                                   } else if (validateNumber(userPhone.getText().toString()) && bValidateFurther) {
                                                       userPhone.setBackgroundResource(R.color.colorError);
                                                       bValidateFurther = false;
                                                       userPhone.requestFocus();
                                                   } else {
                                                       userPhone.setBackgroundResource(0);
                                                       if (TextUtils.isEmpty(userEmail.getText().toString().trim()) && bValidateFurther) {
                                                           userEmail.setBackgroundResource(R.color.colorError);
                                                           bValidateFurther = false;
                                                           userEmail.requestFocus();
                                                       } else if (validateEmail(userEmail.getText().toString().trim()) && bValidateFurther) {
                                                           userEmail.setBackgroundResource(R.color.colorError);
                                                           bValidateFurther = false;
                                                           userEmail.requestFocus();
                                                       } else {
                                                           userEmail.setBackgroundResource(0);

                                                           if (userCity.getSelectedItem().toString().trim().equals("Select City") && bValidateFurther) {
                                                               relSelectCity.setBackgroundResource(R.color.colorError);
                                                               bValidateFurther = false;
                                                           }else{
                                                               relSelectCity.setBackgroundResource(0);
                                                           }

                                                           if (bValidateFurther) {
                                                               Toast.makeText(MainActivity.this, "Validation Success", Toast.LENGTH_SHORT).show();
                                                           }

                                                       }
                                                   }
                                               }

                                           }
                                       }

        );

        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(userName.getText().toString().trim())) {
                    userName.setBackgroundResource(R.color.colorError);
                    userName.requestFocus();
                } else if (validateName(userName.getText().toString())) {
                    userName.setBackgroundResource(R.color.colorError);
                    userName.requestFocus();
                } else {
                    //userName.setBackgroundResource(android.R.drawable.edit_text);
                    userName.setBackground(originalDrawable);
                }
            }
        });

        userPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(userPhone.getText().toString().trim())) {
                    userPhone.setBackgroundResource(R.color.colorError);
                    userPhone.requestFocus();
                } else if (validateNumber(userPhone.getText().toString())) {
                    userPhone.setBackgroundResource(R.color.colorError);
                    userPhone.requestFocus();
                } else {
                    userPhone.setBackground(originalDrawable);
                }
            }
        });

        userEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(userEmail.getText().toString().trim())) {
                    userEmail.setBackgroundResource(R.color.colorError);
                    userEmail.requestFocus();
                } else if (validateEmail(userEmail.getText().toString().trim())) {
                    userEmail.setBackgroundResource(R.color.colorError);
                    userEmail.requestFocus();
                } else {
                    userEmail.setBackground(originalDrawable);
                }
            }
        });

        userCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (userCity.getSelectedItem().toString().trim().equals("Select City") && submitClicked) {
                    relSelectCity.setBackgroundResource(R.color.colorError);
                }else{
                    relSelectCity.setBackgroundResource(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public static boolean validateName(String txt) {
        String regx = "^[\\\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }

    public static boolean validateNumber(String txt) {
        String regx = ".*[^0-9].*";
        //String regx = "^[0-9]*$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }

    public static boolean validateEmail(String txt) {
        String regx = "^[A-Za-z][A-Za-z0-9]*([._-]?[A-Za-z0-9]+)@[A-Za-z].[A-Za-z]{0,3}?.[A-Za-z]{0,2}$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }


}
