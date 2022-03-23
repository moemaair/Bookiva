
package com.moringaschool.bookiva.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Pdf {

    @SerializedName("isAvailable")
    @Expose
    private boolean isAvailable;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Pdf() {
    }

    /**
     * 
     * @param isAvailable
     */
    public Pdf(boolean isAvailable) {
        super();
        this.isAvailable = isAvailable;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
