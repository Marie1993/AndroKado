package fr.androkado.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import fr.androkado.R;
import fr.androkado.metier.dto.ArticleDTO;

public class InfoUrlActivity extends AppCompatActivity {

    public static final String EXTRA_PARAMETRE = "parametre";
    private ArticleDTO article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);


        article = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_PARAMETRE));
        //String value = getIntent().getStringExtra(EXTRA_PARAMETRE);
        //Toast.makeText(this,"TEST " + article.url, Toast.LENGTH_SHORT).show();
        Log.d("TAG", "onCreate: " + article.url);

        TextView tvUrl = findViewById(R.id.string_url);
        tvUrl.setText(article.url);
    }
}