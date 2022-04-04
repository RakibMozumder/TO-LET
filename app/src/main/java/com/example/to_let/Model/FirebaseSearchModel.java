package com.example.to_let.Model;

public class FirebaseSearchModel {
    private String area,bedroom,latest,price,size,phoneNumber,image;
    public  FirebaseSearchModel (){

    }


    public  FirebaseSearchModel (String area, String bedroom,String latest, String price,String size, String phoneNumber,String image) {
        this.area = area;
        this.bedroom = bedroom;
        this.latest = latest;
        this.price = price;
        this.size = size;
        this.phoneNumber= phoneNumber;
        this.image=image;

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

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
