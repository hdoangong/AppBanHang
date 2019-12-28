package vn.edu.uit.trang.shopping.models;


public class CategoryItemGroup {

    private String Title;
    private int Image;

    public CategoryItemGroup(){

    }

    public CategoryItemGroup(String title, int image) {
        Title = title;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public int getImage() {
        return Image;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setImage(int image) {
        Image = image;
    }
}
