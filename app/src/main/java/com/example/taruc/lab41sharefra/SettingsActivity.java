package com.example.taruc.lab41sharefra;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREF_FILE = "";
    private EditText editTextName;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //Read the Shared Preference File(use 2 need )
        sharedPreferences = getSharedPreferences(PREF_FILE, MODE_PRIVATE);
        //OR
        //sharedPreferences = getPreferences(MODE_PRIVATE);
        String name;
        int gender; //default = -1, male = 1, female = 0
        name = sharedPreferences.getString("user_name", "");
        gender = sharedPreferences.getInt("user_gender",-1);
        editTextName.setText(name);
        if(gender == 1){
            radioButtonMale.setChecked(true);
        }else if(gender == 0){
            radioButtonFemale.setChecked(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Create a Shared Pref Editor(.Editor = inner class function)
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name;
        int gender;
        name = editTextName.getText().toString();
        editor.putString("user_name",name);

        gender = radioGroupGender.getCheckedRadioButtonId();
        if(gender == R.id.radioButtonMale){
            editor.putInt("user_gender",1);
        }else if(gender == R.id.radioButtonFemale){
            editor.putInt("user_gender",-1);
        }
        //Apply all changes to the Shared Pref
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editTextName = findViewById(R.id.editTextName);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
    }
}
