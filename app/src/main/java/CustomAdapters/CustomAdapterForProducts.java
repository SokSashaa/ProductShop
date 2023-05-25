package CustomAdapters;

public class CustomAdapterForProducts {

    private String img;
    private String name;
    private String price;
    private Integer count;

    public CustomAdapterForProducts(String imageView, String name, String price,Integer count)
    {
        this.img = imageView;
        this.name = name;
        this.price = price;
        this.count = count;
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

    public void setImageView(String imageView) {this.img = imageView;}
    public String getPrice() {return this.price;}
    public void setPrice(String price) {this.price = price;}
    public Integer getCount() {return this.count;}
    public void setCount(Integer count) {this.count = count;}
}

