package fr.androkado.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import fr.androkado.BO.Article;
import fr.androkado.R;
import fr.androkado.adapters.ArticlesAdapters;
import fr.androkado.metier.bdd.DatabaseHelper;
import fr.androkado.metier.dao.ArticlesDAO;
import fr.androkado.metier.dto.ArticleDTO;

public class AddUpdateActivity extends AppCompatActivity {

    public static final String EXTRA_PARAMETRE = "parametre";

    private ArrayList<Article> ListArticles = new ArrayList<>();
    private ArticlesAdapters articleAdapter;
    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private EditText editTextRating;
    private EditText editTextUrl;
    private ArticleDTO article;
    private Boolean articleToEdit = false;
    Button btnArticleValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update);
        setTitle("Ajouter un article");

        editTextName=findViewById(R.id.article_name);
        editTextPrice = findViewById(R.id.article_price);
        editTextDescription = findViewById(R.id.article_description);
        editTextRating = findViewById(R.id.article_rating);
        editTextUrl = findViewById(R.id.article_url);
        btnArticleValidate = findViewById(R.id.button_addEdit);

        //déclenche création de la base de donné
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();

        //on recupère l'article si il s'agit d'une modification d'un article existant
        article = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_PARAMETRE));

        if (article != null)
        {
            setTitle("Modifier un article");
            editTextName.setText(article.name);
            editTextPrice.setText(article.price+"");
            editTextDescription.setText(article.description);
            editTextRating.setText(article.rating+"");
            editTextUrl.setText(article.url);

            articleToEdit = true;
        } else {
            Log.d("TAG", "onClickAdd: ARTICLE EXISTE PAS");
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String defaultPrice = preferences.getString(ConfigurationActivity.KEY_DEFAULT_PRICE,"");

            editTextPrice = findViewById(R.id.article_price);
            editTextPrice.setText(defaultPrice);
        }

        btnArticleValidate.setOnClickListener(view -> {

            if (articleToEdit)
            {

                article.name = editTextName.getText().toString();
                Toast.makeText(this,"article prèt à étre modif : " + article.name,Toast.LENGTH_SHORT).show();
                article.price = Float.parseFloat(editTextPrice.getText().toString());
                article.description = editTextDescription.getText().toString();
                article.rating = Float.parseFloat(editTextRating.getText().toString());
                article.url = editTextUrl.getText().toString();

                ArticleDTO a = new ArticleDTO( article.id, article.name, article.price, article.description, article.rating, article.url, article.isBought);

                ArticlesDAO.editAricle(a,this);

                List<ArticleDTO> listArticles = ArticlesDAO.getListArticles(this);
                //rafraichir page
                //articleAdapter.updateArticles(listArticles);
                //this.finish();
            }
            else {

                Log.d("TAG", "onClickAdd:  AJOUT");

                //récupération saisie utilisateur
                editTextName = findViewById(R.id.article_name);
                String articleName = editTextName.getText().toString();

                editTextPrice = findViewById(R.id.article_price);
                String articlePrice = editTextPrice.getText().toString();
                float finalArticlePrice = Float.parseFloat(articlePrice);
                if (articlePrice == null) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    String defaultPrice = preferences.getString(ConfigurationActivity.KEY_DEFAULT_PRICE,"");
                    finalArticlePrice = Float.parseFloat(defaultPrice);
                }

                editTextDescription = findViewById(R.id.article_description);
                String articleDescription = editTextDescription.getText().toString();

                editTextRating = findViewById(R.id.article_rating);
                String articleRating = editTextRating.getText().toString();
                float finalArticleRating = Float.parseFloat(articleRating);

                editTextUrl = findViewById(R.id.article_url);
                String articleUrl = editTextUrl.getText().toString();

                ArticleDTO a = new ArticleDTO( 0, articleName, finalArticlePrice, articleDescription,finalArticleRating,articleUrl,0);

                ArticlesDAO.addArticle(a,this);
                List<ArticleDTO> listArticles = ArticlesDAO.getListArticles(this);
                //rafraichir page
                articleAdapter.updateArticles(listArticles);
            }

            Intent intent = new Intent(this,ListeArticlesActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        });





    }


 //  public void onClickAdd(View view) {

 //      if (articleToEdit)
 //      {

 //          article.name = editTextName.getText().toString();
 //          Toast.makeText(this,"article prèt à étre modif : " + article.name,Toast.LENGTH_SHORT).show();
 //          article.price = Float.parseFloat(editTextPrice.getText().toString());
 //          article.description = editTextDescription.getText().toString();
 //          article.rating = Float.parseFloat(editTextRating.getText().toString());
 //          article.url = editTextUrl.getText().toString();




 //      }
 //      else {

 //          Log.d("TAG", "onClickAdd:  AJOUT");

 //          //récupération saisie utilisateur
 //          editTextName = findViewById(R.id.article_name);
 //          String articleName = editTextName.getText().toString();

 //          editTextPrice = findViewById(R.id.article_price);
 //          String articlePrice = editTextPrice.getText().toString();
 //          float finalArticlePrice = Float.parseFloat(articlePrice);
 //          if (articlePrice == null) {
 //              SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
 //              String defaultPrice = preferences.getString(ConfigurationActivity.KEY_DEFAULT_PRICE,"");
 //              finalArticlePrice = Float.parseFloat(defaultPrice);
 //          }

 //          editTextDescription = findViewById(R.id.article_description);
 //          String articleDescription = editTextDescription.getText().toString();

 //          editTextRating = findViewById(R.id.article_rating);
 //          String articleRating = editTextRating.getText().toString();
 //          float finalArticleRating = Float.parseFloat(articleRating);

 //          editTextUrl = findViewById(R.id.article_url);
 //          String articleUrl = editTextUrl.getText().toString();

 //          ArticleDTO a = new ArticleDTO( 0, articleName, finalArticlePrice, articleDescription,finalArticleRating,articleUrl,0);

 //          ArticlesDAO.addArticle(a,this);
 //          List<ArticleDTO> listArticles = ArticlesDAO.getListArticles(this);
 //          //rafraichir page
 //          articleAdapter.updateArticles(listArticles);
 //      }


  //  }

}