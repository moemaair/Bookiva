
package com.moringaschool.bookiva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageLinks {

    @SerializedName("smallThumbnail")
    @Expose
    private String smallThumbnail;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("small")
    @Expose
    private String small;

    @SerializedName("large")
    @Expose
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getExtraLarge() {
        return extraLarge;
    }

    public void setExtraLarge(String extraLarge) {
        this.extraLarge = extraLarge;
    }

    @SerializedName("extraLarge")
    @Expose
    private String extraLarge;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ImageLinks() {
    }

    public ImageLinks(String smallThumbnail, String thumbnail, String small, String large, String extraLarge) {
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
        this.small = small;
        this.large = large;
        this.extraLarge = extraLarge;
    }

  


    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
