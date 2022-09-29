package fr.androkado.BO;


import org.parceler.Parcel;

@Parcel

public class Article {

    public long id;
    public String name;
    public String price;
    public String description;
    public float rating;
    public String url;
    public boolean isBought;



    public Article(){}

    public Article(long id,String name, String price, String description, float rating, String url, boolean isBought) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.url = url;
        this.isBought = isBought;
    }


}
