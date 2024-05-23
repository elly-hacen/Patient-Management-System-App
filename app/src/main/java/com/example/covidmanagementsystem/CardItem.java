package com.example.covidmanagementsystem;

// CardItem.java
// CardItem.java
public class CardItem {
    private int imageResourceId; // Added field for image resource ID
    private String itemTitle;
    private String medicalStatus;
    private String uniqueId;

    public CardItem(int imageResourceId, String itemTitle, String medicalStatus, String uniqueId) {
        this.imageResourceId = imageResourceId;
        this.itemTitle = itemTitle;
        this.medicalStatus = medicalStatus;
        this.uniqueId = uniqueId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getMedicalStatus() {
        return medicalStatus;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
