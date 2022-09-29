package fr.androkado.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.parceler.Parcels;

import fr.androkado.BO.Article;
import fr.androkado.R;
import fr.androkado.fragment.DetailFragment;
import fr.androkado.metier.dto.ArticleDTO;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ARTICLE = "article";
    private ArticleDTO article;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //récupération de l'article
        article = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_ARTICLE));

        //fragment
        DetailFragment fragment = new DetailFragment();

        //arguments
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailFragment.EXTRA_ARTICLE, Parcels.wrap(article));
        fragment.setArguments(bundle);

        //ajout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.conteneur_fragment,fragment, "tagDetail");
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.article_update:
                Intent intent = new Intent(this, AddUpdateActivity.class);
                intent.putExtra(AddUpdateActivity.EXTRA_PARAMETRE, Parcels.wrap(article));
                startActivity(intent);
                return true;
            case R.id.article_send:
                Toast.makeText(this,"Envoyer",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.article_delete:
                Intent intent3 = new Intent(this, DeleteActivity.class);
                intent3.putExtra(AddUpdateActivity.EXTRA_PARAMETRE, Parcels.wrap(article));
                startActivity(intent3);
                return true;
        }
        return super.onOptionsItemSelected(item); //si on ne rentre dans el switch on retourne la methode parente
    }

   // public void onClickUrl (View view) {
        //Toast.makeText(this, article.url, Toast.LENGTH_LONG).show();
        //Log.d("TAG", "onClickUrl: " + article.url);
       // Intent intent = new Intent(this, InfoUrlActivity.class);
       // intent.putExtra(InfoUrlActivity.EXTRA_PARAMETRE, article.url);
       // Log.d("TAG", "onClickUrl: " + article.url);
      //  startActivity(intent);
  //  }

 //  public void onClickBuy (View view) {

 //      if (article.isBought == 0) {
 //          msg = "pas acheté";
 //      } else {
 //          msg = "deja acheté";
 //      }
 //      Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
 //  }
}