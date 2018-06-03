package com.stl.entity;

public class Rating {
    private int total = 0;
    private int[] stars = {0,0,0,0,0};

    public void rate(Stars star) {
        total++;
        stars[star.getValue()]++;
    }

    public int getStarCounter(Stars star) {
         return stars[star.getValue()];
    }

    public int getAverage() {
        double sum = 0;

        for (int i = 0; i < stars.length; i++) {
            sum += (double) (stars[i] * (i+1));
        }

        return (int) Math.round((double) sum / total);
    }

    public int getTotal() {
        return total;
    }
}
