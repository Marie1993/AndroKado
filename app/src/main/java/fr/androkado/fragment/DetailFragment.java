package fr.androkado.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;


import org.parceler.Parcels;

import fr.androkado.R;
import fr.androkado.activity.InfoUrlActivity;
import fr.androkado.metier.dto.ArticleDTO;


public class DetailFragment extends Fragment {


    public static final String EXTRA_ARTICLE = "article";
    private ArticleDTO article;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            ArticleDTO article = Parcels.unwrap(bundle.getParcelable(EXTRA_ARTICLE)) ;
            TextView textViewDetail = view.findViewById(R.id.article);
            textViewDetail.setText(article.name );

            TextView tvPrice = view.findViewById(R.id.price);
            tvPrice.setText(article.price + " €");

            RatingBar ratingBar = view.findViewById(R.id.rating);
            ratingBar.setRating(article.rating);

            TextView tvDescription = view.findViewById(R.id.description);
            tvDescription.setText(article.description);

            AppCompatButton mImageButton = view.findViewById(R.id.URL);

            mImageButton.setOnClickListener(view1 -> {
                Intent intent = new Intent(view.getContext(), InfoUrlActivity.class);
                intent.putExtra(InfoUrlActivity.EXTRA_PARAMETRE, Parcels.wrap(article));
                startActivity(intent);
            });


        }
    }


}
