package com.cs.entity;

public class Goods {
    private Integer id;
    private String name;
    private String pic;
    private Integer price;
    private String description;
    private Integer stock;

    public Goods() {
    }

    public Goods(Integer id, String name, String pic, Integer price, String description, Integer stock) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public Goods(String name, String pic, Integer price, String description, Integer stock) {
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                '}';
    }
}
