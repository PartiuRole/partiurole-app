package com.partiurole.partiurole.model;

public class Event {
    private String id;
    private String name;
    private String date;
    private Double minPrice;
    private Double maxPrice;
    private String ageGroup;
    private String location;
    private String city;
    private String region;
    private String mapsUrl;
    private String eventUrl;
    private String openingTime;
    private String startTime;
    private String principalAttractionTime;
    private String description;
    private String imageUrl;
    private String attractions;
    private boolean isFavorite;

    public Event(String id, String name, String date, Double minPrice, Double maxPrice, String ageGroup, String location, String city, String region, String mapsUrl, String eventUrl, String openingTime, String startTime, String principalAttractionTime, String description, String imageUrl, String attractions, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.ageGroup = ageGroup;
        this.location = location;
        this.city = city;
        this.region = region;
        this.mapsUrl = mapsUrl;
        this.eventUrl = eventUrl;
        this.openingTime = openingTime;
        this.startTime = startTime;
        this.principalAttractionTime = principalAttractionTime;
        this.description = description;
        this.imageUrl = imageUrl;
        this.attractions = attractions;
        this.isFavorite = isFavorite;
    }

    public Event(){

    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMapsUrl() {
        return mapsUrl;
    }

    public void setMapsUrl(String mapsUrl) {
        this.mapsUrl = mapsUrl;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPrincipalAttractionTime() {
        return principalAttractionTime;
    }

    public void setPrincipalAttractionTime(String principalAttractionTime) {
        this.principalAttractionTime = principalAttractionTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAttractions() {
        return attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }


}
