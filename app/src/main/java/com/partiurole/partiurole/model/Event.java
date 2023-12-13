package com.partiurole.partiurole.model;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;
    @SerializedName("price")
    private Double price;
    @SerializedName("description")
    private String description;
    @SerializedName("image_url")
    private String image;
    @SerializedName("url")
    private String url;
    @SerializedName("location_text")
    private String location;
    @SerializedName("location_url")
    private String locationUrl;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private Boolean isFavorite;
    private String imageBase64;

    public Event(String uuid, String name, String date, Double price, String description, String image, String url, String location, String locationUrl, String createdAt, String updatedAt, Boolean isFavorite) {
        this.uuid = uuid;
        this.name = name;
        this.date = date;
        this.price = price;
        this.description = description;
        this.image = image;
        this.url = url;
        this.location = location;
        this.locationUrl = locationUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isFavorite = isFavorite;
        this.imageBase64 = "";
    }

    public Event(){}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price == null ? 0.0 : price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image == null ? "" : image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getIsFavorite() {
        return isFavorite == null ? false : isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getLocation() {
        return location == null ? "" : location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationUrl() {
        return locationUrl == null ? "" : locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public String getImageBase64() {
        return imageBase64 == null ? "" : imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
