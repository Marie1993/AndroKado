package fr.androkado.metier.bdd;

import android.provider.BaseColumns;

public class BaseContrat {

    //constructeur privé afin de ne pas instancier la classe
    private BaseContrat() {}
    //contenu de la table "article"
    public static class ArticlesContrat implements BaseColumns
    {
        public static final String TABLE_ARTICLES = "articles";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_PRICE = "price";

        public static final String COLUMN_DESCRIPTION = "description";

        public static final String COLUMN_RATING = "rating";

        public static final String COLUMN_URL = "url";

        public static final String COLUMN_IS_BOUGHT = "isBought";
    }

}
