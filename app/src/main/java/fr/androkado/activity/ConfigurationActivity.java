package fr.androkado.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import fr.androkado.R;

public class ConfigurationActivity extends AppCompatActivity {

    public final static String KEY_SORTING = "KEY_SORTING";
    public final static String KEY_DEFAULT_PRICE = "KEY_DEFAULT_PRICE";

    EditText mEtDefaultPrice;
    Switch mSorting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        setTitle("Configuration");

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean switchPrice =  sp.getBoolean(KEY_SORTING,false);
        String defaultPrice = sp.getString(KEY_DEFAULT_PRICE,"");

        mSorting = findViewById(R.id.switch_sorting_price);
        mEtDefaultPrice = findViewById(R.id.et_default_price);

        mSorting.setChecked(switchPrice);
        mEtDefaultPrice.setText(defaultPrice);


    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_DEFAULT_PRICE,mEtDefaultPrice.getText().toString());
        editor.putBoolean(KEY_SORTING,mSorting.isChecked());
        editor.apply();

    }
}