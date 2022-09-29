package fr.androkado.metier.dto;

import org.parceler.Parcel;

@Parcel
public class ArticleDTO {

    public long id;
    public String name;
    public float price;
    public String description;
    public float rating;
    public String url;
    public int isBought;



    public ArticleDTO(){}

    public ArticleDTO(long id,String name, float price, String description, float rating, String url, int isBought) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.url = url;
        this.isBought = isBought;
    }


}
