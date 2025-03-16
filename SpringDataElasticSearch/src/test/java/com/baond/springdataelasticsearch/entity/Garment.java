package com.baond.springdataelasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.util.List;

@Document(indexName = "garments")
@Mapping(mappingPath = "config/garments/index-mapping.json")
public class Garment {

    private String id;
    private String name;
    private Integer price;
    private List<String> color;
    private List<String> size;
    private String material;
    private String brand;
    private String occasion;
    private String neckStyle;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getNeckStyle() {
        return neckStyle;
    }

    public void setNeckStyle(String neckStyle) {
        this.neckStyle = neckStyle;
    }

    @Override
    public String toString() {
        return "Garment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color=" + color +
                ", size=" + size +
                ", material='" + material + '\'' +
                ", brand='" + brand + '\'' +
                ", occasion='" + occasion + '\'' +
                ", neckStyle='" + neckStyle + '\'' +
                '}';
    }
}
