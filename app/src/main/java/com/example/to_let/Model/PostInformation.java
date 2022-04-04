package com.example.to_let.Model;

public class PostInformation {
    private String phoneNumber,area,bedroom,latest,price,size,image;
    public PostInformation(){

    }

    public PostInformation(String phoneNumber,String area,String bedroom,String image,String latest, String price,String size) {
        this.phoneNumber=phoneNumber;
        this.area = area;
        this.bedroom = bedroom;
        this.image = image;
        this.latest = latest;
        this.price = price;
        this.size = size;


    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }
}
