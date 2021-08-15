package com.example.forsaforcompnay.model.home;

import java.util.ArrayList;

public class DataHomeCompany {
    private ArrayList<ItemDataHomeCompany> items;
    private Paginate paginate;

    public ArrayList<ItemDataHomeCompany> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDataHomeCompany> items) {
        this.items = items;
    }

    public Paginate getPaginate() {
        return paginate;
    }

    public void setPaginate(Paginate paginate) {
        this.paginate = paginate;
    }
}
