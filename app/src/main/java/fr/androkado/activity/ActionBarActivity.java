package fr.androkado.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fr.androkado.R;

public class ActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
    }
}