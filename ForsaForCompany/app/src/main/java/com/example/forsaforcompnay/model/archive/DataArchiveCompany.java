package com.example.forsaforcompnay.model.archive;


import java.util.ArrayList;

public class DataArchiveCompany {
    private ArrayList<ItemDataArchiveCompany> items;
    private Paginate paginate;

    public ArrayList<ItemDataArchiveCompany> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDataArchiveCompany> items) {
        this.items = items;
    }

    public Paginate getPaginate() {
        return paginate;
    }

    public void setPaginate(Paginate paginate) {
        this.paginate = paginate;
    }
}
