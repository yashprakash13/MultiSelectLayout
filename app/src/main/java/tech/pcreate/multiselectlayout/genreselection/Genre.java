package tech.pcreate.multiselectlayout.genreselection;

public class Genre {

    String name;
    private boolean isSelected = false;

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre(String name) {

        this.name = name;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }
}
