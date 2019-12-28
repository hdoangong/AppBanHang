package vn.edu.uit.trang.shopping.models;

import androidx.cardview.widget.CardView;

public class HomeItemGroup {

    private CardView cardView;
    private String title;
    private int image;

    public HomeItemGroup(String title, int image) {
        this.title = title;
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
