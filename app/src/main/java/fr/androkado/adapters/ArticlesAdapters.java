package fr.androkado.adapters;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;

import java.text.MessageFormat;
import java.util.List;

import fr.androkado.BO.Article;
import fr.androkado.R;
import fr.androkado.activity.DetailActivity;
import fr.androkado.activity.ListeArticlesActivity;
import fr.androkado.fragment.DetailFragment;
import fr.androkado.metier.dto.ArticleDTO;

public class ArticlesAdapters extends RecyclerView.Adapter<ArticlesAdapters.ArticleViewHolder>{

    private List<ArticleDTO> listArticles;
    private ListeArticlesActivity listActivity;

    public ArticlesAdapters(List<ArticleDTO> listArticles, ListeArticlesActivity listActivity) {
        this.listArticles = listArticles;
        this.listActivity = listActivity;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewArticle = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_article,parent,false);
        return new ArticleViewHolder(viewArticle);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {

        holder.tvWordingArticle.setText(listArticles.get(position).name);
       // holder.tvPrice.setText((int) listArticles.get(position).price);
        holder.tvPrice.setText(MessageFormat.format("{0}", listArticles.get(position).price));
        holder.ratingBar.setRating(listArticles.get(position).rating);
        holder.tvEuro.setText(" €");
    }

    @Override
    public int getItemCount() {

        return listArticles.size();
    }

    public void updateArticles ( List<ArticleDTO> listArticles ) {
        this.listArticles = listArticles;
        notifyDataSetChanged(); // rafraichi l'affichage
    }


    class ArticleViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvWordingArticle;
        public TextView tvPrice;
        public RatingBar ratingBar;
        public TextView tvEuro;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWordingArticle = itemView.findViewById(R.id.wording_article);
            tvPrice = itemView.findViewById(R.id.price);
            ratingBar = itemView.findViewById(R.id.rating);
            tvEuro = itemView.findViewById(R.id.euro);

            itemView.setOnClickListener(view -> {

                ArticleDTO article = listArticles.get(getAdapterPosition());

                //on verifi dans quel xml on se trouve, si fragment existe
                if (listActivity.findViewById(R.id.conteneur_fragment) != null)
                {
                    //fragment
                    DetailFragment fragment = new DetailFragment();

                    //arguments
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(DetailFragment.EXTRA_ARTICLE, Parcels.wrap(article));
                    fragment.setArguments(bundle);

                    //ajout
                    FragmentTransaction transaction = listActivity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.conteneur_fragment,fragment, "tagDetail");
                    transaction.commit();
                }
                else
                {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_ARTICLE, Parcels.wrap(article));
                    itemView.getContext().startActivity(intent);
                }






            });


        }
    }

}
