package fr.androkado.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.androkado.BO.Article;
import fr.androkado.R;
import fr.androkado.adapters.ArticlesAdapters;
import fr.androkado.metier.bdd.DatabaseHelper;
import fr.androkado.metier.dao.ArticlesDAO;
import fr.androkado.metier.dto.ArticleDTO;

public class ListeArticlesActivity extends AppCompatActivity {

    private List<ArticleDTO> listeArticles;
    private ArticlesAdapters articleAdapter;
    private Boolean switchPrice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_articles);

        //déclenche création de la base de donné
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.listArticle);

        //à ajouter pour de meilleures pérformances
        recyclerView.setHasFixedSize(true);

        // layout manager, décrivant comment les articles sont disposés
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //recuperation préférences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        switchPrice =  preferences.getBoolean(ConfigurationActivity.KEY_SORTING,false);

        if (switchPrice) {
            listeArticles = ArticlesDAO.getListArticlesByPrice(this);
        } else {
            listeArticles = ArticlesDAO.getListArticles(this);
        }

        //adapter
        articleAdapter = new ArticlesAdapters(listeArticles, this);
        recyclerView.setAdapter(articleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.article_add:
                Intent intent = new Intent(this, AddUpdateActivity.class);
                startActivity(intent);
                Toast.makeText(this,"Ajouter",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.article_configuration:
                Intent intent2 = new Intent(this, ConfigurationActivity.class);
                startActivity(intent2);
                Toast.makeText(this,"Configuration",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item); //si on ne rentre dans el switch on retourne la methode parente
    }




}