package com.smile.taobaodemo.model.entity;

import java.util.List;

/**
 * @author Smile Wei
 * @since 2016/9/21.
 */

public class HomeTop {

    private List<Carousel> carousel;
    private List<HomeBase> headlines;
    private List<HomeBase> list;


    public List<HomeBase> getHeadlines() {
        return headlines;
    }

    public void setHeadlines(List<HomeBase> headlines) {
        this.headlines = headlines;
    }


    public List<Carousel> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<Carousel> carousel) {
        this.carousel = carousel;
    }

    public List<HomeBase> getList() {
        return list;
    }

    public void setList(List<HomeBase> list) {
        this.list = list;
    }

    public static class Carousel {
        private String url;
        private int id;

        public Carousel(int id, String url) {
            this.id = id;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
