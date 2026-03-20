package com.example.starsgallery.service;

import com.example.starsgallery.beans.Star;
import java.util.ArrayList;
import java.util.List;

public class StarService {

    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    public static StarService getInstance() {
        if (instance == null) {
            instance = new StarService();
        }
        return instance;
    }

    private void seed() {
        stars.add(new Star("Emma Watson", "https://picsum.photos/200", 4.5f));
        stars.add(new Star("Tom Cruise", "https://picsum.photos/201", 4.2f));
        stars.add(new Star("Scarlett Johansson", "https://picsum.photos/202", 4.7f));
        stars.add(new Star("Leonardo DiCaprio", "https://picsum.photos/203", 4.8f));
    }

    public List<Star> findAll() {
        return stars;
    }

    public boolean update(Star star) {
        for (Star s : stars) {
            if (s.getId() == star.getId()) {
                s.setName(star.getName());
                s.setImg(star.getImg());
                s.setStar(star.getStar());
                return true;
            }
        }
        return false;
    }
}