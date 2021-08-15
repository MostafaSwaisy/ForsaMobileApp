package com.example.forsaforcompnay.model;

import java.io.Serializable;
import java.net.URL;

public class ProfileLink implements Serializable {
  private URL facebook , twitter , whatsapp , instagram , linkedin , behance , website;

    public URL getFacebook() {
        return facebook;
    }

    public void setFacebook(URL facebook) {
        this.facebook = facebook;
    }

    public URL getTwitter() {
        return twitter;
    }

    public void setTwitter(URL twitter) {
        this.twitter = twitter;
    }

    public URL getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(URL whatsapp) {
        this.whatsapp = whatsapp;
    }

    public URL getInstagram() {
        return instagram;
    }

    public void setInstagram(URL instagram) {
        this.instagram = instagram;
    }

    public URL getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(URL linkedin) {
        this.linkedin = linkedin;
    }

    public URL getBehance() {
        return behance;
    }

    public void setBehance(URL behance) {
        this.behance = behance;
    }

    public URL getWebsite() {
        return website;
    }

    public void setWebsite(URL website) {
        this.website = website;
    }
}
