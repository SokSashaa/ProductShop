package CustomAdapters;

public class CustomAdapterForSkidka {

    private String img;
    private String name;
    private String newPrice;
    private String prevPrice;
    private Integer count;

    public CustomAdapterForSkidka(String imageView, String name,String newPrice,String prevPrice)
    {
        this.img = imageView;
        this.name = name;
        this.newPrice = newPrice;
        this.prevPrice = prevPrice;
        this.count=0;
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
    public String getNewPrice(){return this.newPrice;}
    public void setNewPrice(String newPrice){this.newPrice=newPrice;}

    public String getPrevPrice() {return this.prevPrice;}

    public void setPrevPrice(String prevPrice) {this.prevPrice = prevPrice;}

    public Integer getCount() {return count;}

    public void setCount(Integer count) {this.count = count;}
}

