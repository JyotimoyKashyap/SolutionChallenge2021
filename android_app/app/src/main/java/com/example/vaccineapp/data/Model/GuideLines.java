package com.example.vaccineapp.data.Model;

public class GuideLines {

    public String title;
    public String description;
    public int lottie_anim;

    public GuideLines(String title, String description, int lottie_anim) {
        this.title = title;
        this.description = description;
        this.lottie_anim = lottie_anim;
    }

    public GuideLines()
    {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLottie_anim() {
        return lottie_anim;
    }

    public void setLottie_anim(int lottie_anim) {
        this.lottie_anim = lottie_anim;
    }
}
