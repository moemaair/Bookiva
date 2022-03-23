
package com.moringaschool.bookiva.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBooks {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("totalItems")
    @Expose

    //what we need!
    private int totalItems;
    @SerializedName("items")
    @Expose
    private List<Items> items = null;


    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseBooks() {
    }

    /**
     * 
     * @param totalItems
     * @param kind
     * @param items
     */
    public ResponseBooks(String kind, int totalItems, List<Items> items) {
        super();
        this.kind = kind;
        this.totalItems = totalItems;
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

}
