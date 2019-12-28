package vn.edu.uit.trang.shopping.models;

public class HomeAdvertisement {

    private String Title;
    private int Image;

    public HomeAdvertisement(){

    }

    public HomeAdvertisement(String title, int image) {
        Image = image;
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
