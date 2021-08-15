package com.example.forsaforcompnay.model.archive;

import java.net.URL;

public class Paginate {
    private int current_page, from, last_page, next_page_url, per_page, prev_page_url, to, total;
    private URL first_page_url, last_page_url;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(int next_page_url) {
        this.next_page_url = next_page_url;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(int prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public URL getFirst_page_url() {
        return first_page_url;
    }

    public void setFirst_page_url(URL first_page_url) {
        this.first_page_url = first_page_url;
    }

    public URL getLast_page_url() {
        return last_page_url;
    }

    public void setLast_page_url(URL last_page_url) {
        this.last_page_url = last_page_url;
    }
}
