package fr.androkado.metier.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.androkado.BO.Article;
import fr.androkado.metier.bdd.BaseContrat;
import fr.androkado.metier.bdd.DatabaseHelper;
import fr.androkado.metier.dto.ArticleDTO;

public class ArticlesDAO {

    @SuppressLint("Range")
    public static List<ArticleDTO> getListArticles (Context context)
    {
        //récupération de la bdd
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        //tri
        String tri = BaseContrat.ArticlesContrat.COLUMN_NAME+" ASC ";

        //requete
        Cursor cursor = db.query(
                false, // tru si SELECT DISTINCT
                BaseContrat.ArticlesContrat.TABLE_ARTICLES, // table sur laquelle on fait la requête
                null, // colonnes à retourner
                null, // colonnes pour la clause WHERE -> null on prend tout
                null, // valeurs pour la clause WHERE
                null, // GROUP BY (inactif)
                null, // HAVING (inactif)
                tri, // ordre de tri
                null); // LIMIT (inactif)

        List<ArticleDTO> listArticles = new ArrayList<>();
        if (cursor != null)
        {
            try
            {
                while (cursor.moveToNext())
                {
                    // conversion des données remontées en un objet métier :
                    listArticles.add(new ArticleDTO(

                            cursor.getLong(cursor.getColumnIndex(BaseContrat.ArticlesContrat._ID)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_NAME)),
                            cursor.getFloat(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_PRICE)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_DESCRIPTION)),
                            cursor.getFloat(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_RATING)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_URL)),
                            cursor.getInt(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_IS_BOUGHT))));
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            finally
            {
                cursor.close();
            }
        }
        return listArticles;
    }

    @SuppressLint("Range")
    public static List<ArticleDTO> getListArticlesByPrice (Context context)
    {
        //récupération de la bdd
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        //tri
        String tri = BaseContrat.ArticlesContrat.COLUMN_PRICE +" ASC ";

        //requete
        Cursor cursor = db.query(
                false, // tru si SELECT DISTINCT
                BaseContrat.ArticlesContrat.TABLE_ARTICLES, // table sur laquelle on fait la requête
                null, // colonnes à retourner
                null, // colonnes pour la clause WHERE -> null on prend tout
                null, // valeurs pour la clause WHERE
                null, // GROUP BY (inactif)
                null, // HAVING (inactif)
                tri, // ordre de tri
                null); // LIMIT (inactif)

        List<ArticleDTO> listArticles = new ArrayList<>();
        if (cursor != null)
        {
            try
            {
                while (cursor.moveToNext())
                {
                    // conversion des données remontées en un objet métier :
                    listArticles.add(new ArticleDTO(

                            cursor.getLong(cursor.getColumnIndex(BaseContrat.ArticlesContrat._ID)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_NAME)),
                            cursor.getFloat(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_PRICE)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_DESCRIPTION)),
                            cursor.getFloat(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_RATING)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_URL)),
                            cursor.getInt(cursor.getColumnIndex(BaseContrat.ArticlesContrat.COLUMN_IS_BOUGHT))));
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            finally
            {
                cursor.close();
            }
        }
        return listArticles;
    }

    public static long addArticle (ArticleDTO article, Context context)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        //objet de valeurs
        ContentValues values = new ContentValues();
        values.put(BaseContrat.ArticlesContrat.COLUMN_NAME, article.name);
        values.put(BaseContrat.ArticlesContrat.COLUMN_PRICE, article.price);
        values.put(BaseContrat.ArticlesContrat.COLUMN_DESCRIPTION, article.description);
        values.put(BaseContrat.ArticlesContrat.COLUMN_RATING, article.rating);
        values.put(BaseContrat.ArticlesContrat.COLUMN_URL, article.url);
        values.put(BaseContrat.ArticlesContrat.COLUMN_IS_BOUGHT, article.isBought);



        //ajout
        return db.insert(BaseContrat.ArticlesContrat.TABLE_ARTICLES, null, values);

    }

    public static long editAricle(ArticleDTO article, Context context) {

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        //preparation requete
        ContentValues values = new ContentValues();
        values.put(BaseContrat.ArticlesContrat.COLUMN_NAME, article.name);
        values.put(BaseContrat.ArticlesContrat.COLUMN_PRICE, article.price);
        values.put(BaseContrat.ArticlesContrat.COLUMN_DESCRIPTION, article.description);
        values.put(BaseContrat.ArticlesContrat.COLUMN_RATING, article.rating);
        values.put(BaseContrat.ArticlesContrat.COLUMN_URL, article.url);
        values.put(BaseContrat.ArticlesContrat.COLUMN_IS_BOUGHT, article.isBought);

        String whereClause = BaseContrat.ArticlesContrat._ID+" == ?";
        String[] whereParams = new String[]{Long.toString(article.id)};

        return db.update(BaseContrat.ArticlesContrat.TABLE_ARTICLES,values,whereClause,whereParams);

    }

    public static long deleteArticle (ArticleDTO article, Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        //filtre clause where
        String whereClause = BaseContrat.ArticlesContrat._ID+ " = ? ";
        String[] whereParams= new String[]{article.id+""};

        //requête
        return db.delete(BaseContrat.ArticlesContrat.TABLE_ARTICLES, whereClause, whereParams);
    }


}