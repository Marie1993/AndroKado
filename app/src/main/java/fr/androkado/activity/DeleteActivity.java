package fr.androkado.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import org.parceler.Parcels;

import fr.androkado.R;
import fr.androkado.metier.bdd.DatabaseHelper;
import fr.androkado.metier.dao.ArticlesDAO;
import fr.androkado.metier.dto.ArticleDTO;

public class DeleteActivity extends AppCompatActivity {

    public static final String EXTRA_PARAMETRE = "parametre";
    private ArticleDTO article;
    private Button btnDeleteArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        setTitle("Supprimer un article");

        btnDeleteArticle = findViewById(R.id.button_delete);

        //déclenche création de la base de donné
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();

        //on recupère l'article si il s'agit d'une modification d'un article existant
        article = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_PARAMETRE));

        btnDeleteArticle.setOnClickListener(view -> {

            ArticlesDAO.deleteArticle(article, this);

            Toast.makeText(this,"article " +article.name + " supprimé",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ListeArticlesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}

