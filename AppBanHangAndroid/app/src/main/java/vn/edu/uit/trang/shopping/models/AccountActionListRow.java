package vn.edu.uit.trang.shopping.models;

public class AccountActionListRow {

    int icon;
    String title;

    public AccountActionListRow(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
