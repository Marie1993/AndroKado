package fr.androkado.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import fr.androkado.BO.Article;
import fr.androkado.R;
import fr.androkado.activity.InfoUrlActivity;


public class MainActivity extends AppCompatActivity {


    Article article = new Article(1,"Pain au chocolat","1","c'est un peu une description",4,"http://www.painauchocolat.fr",false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    TextView tvName = findViewById(R.id.article);
    TextView tvPrice = findViewById(R.id.price);
    TextView tvDescription = findViewById(R.id.description);
    RatingBar rating = findViewById(R.id.rating);
    AppCompatButton buttonBuy = findViewById(R.id.buy);
    AppCompatButton buttonUrl =findViewById(R.id.URL);

    tvName.setText(article.name);
    tvPrice.setText(String.valueOf(article.price));
    tvDescription.setText(article.description);
    rating.setRating(article.rating);
        Log.d("myTag", "un message");
        Log.d("myTag", article.description);



    }

    public void onClickBuy(View view) {

        Log.d("tag", "article : " + article.isBought);
    }

//    public void onClickUrl(View view) {
//        Toast.makeText(this, article.url, Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this, InfoUrlActivity.class);
//        intent.putExtra(InfoUrlActivity.EXTRA_PARAMETRE, article.url);
//        startActivity(intent);
//    }
}