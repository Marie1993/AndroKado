package fr.androkado.metier.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    // Constructeur :
    public DatabaseHelper(Context context)
    {
        super(context, "courses.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + BaseContrat.ArticlesContrat.TABLE_ARTICLES + " ("
                + BaseContrat.ArticlesContrat._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BaseContrat.ArticlesContrat.COLUMN_NAME + " TEXT NOT NULL, "
                + BaseContrat.ArticlesContrat.COLUMN_PRICE + " INTEGER NOT NULL, "
                + BaseContrat.ArticlesContrat.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + BaseContrat.ArticlesContrat.COLUMN_RATING + " INTEGER NOT NULL, "
                + BaseContrat.ArticlesContrat.COLUMN_URL + " TEXT NOT NULL, "
                + BaseContrat.ArticlesContrat.COLUMN_IS_BOUGHT + " INTEGER NOT NULL "
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
