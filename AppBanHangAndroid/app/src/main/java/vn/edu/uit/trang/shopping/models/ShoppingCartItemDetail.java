package vn.edu.uit.trang.shopping.models;

public class ShoppingCartItemDetail {

    private String id;
    private String name;
    private String seller;
    private String image;
    private double price;
    private int numberOfItem;



    public ShoppingCartItemDetail(){

    }

    public ShoppingCartItemDetail(String name, String seller, String image, double price, int numberOfItem) {
        this.name = name;
        this.seller = seller;
        this.image = image;
        this.price = price;
        this.numberOfItem = numberOfItem;
    }

    public ShoppingCartItemDetail(String id, String name, String seller, String image, double price, int numberOfItem) {
        this.id = id;
        this.name = name;
        this.seller = seller;
        this.image = image;
        this.price = price;
        this.numberOfItem = numberOfItem;
    }

    public double getTotalPrice() {
        return price * numberOfItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public void setNumberOfItem(int numberOfItem) {
        this.numberOfItem = numberOfItem;
    }
}
