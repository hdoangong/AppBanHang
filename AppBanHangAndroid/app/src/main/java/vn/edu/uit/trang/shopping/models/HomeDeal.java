package vn.edu.uit.trang.shopping.models;

public class HomeDeal {

    private String title;
    private int image;
    private int newPrice;
    private int oldPrice;

    public HomeDeal(){

    }

    public HomeDeal(String title, int image, int newPrice, int oldPrice) {
        this.title = title;
        this.image = image;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

}
