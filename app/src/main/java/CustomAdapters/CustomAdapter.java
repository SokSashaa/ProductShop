package CustomAdapters;

public class CustomAdapter {

    private String img;
    private String name;

    public CustomAdapter(String imageView, String name)
    {
        this.img = imageView;
        this.name = name;
    }

    public String getNames()
    {
        return this.name;
    }

    public String getImageViews() {
        return this.img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageView(String imageView) {
        this.img = imageView;
    }
}
