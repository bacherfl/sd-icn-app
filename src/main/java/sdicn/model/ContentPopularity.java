package sdicn.model;

/**
 * Created by florian on 20.05.15.
 */
public class ContentPopularity {

    private String contentName;
    private double popularity;
    private int count = 0;

    public ContentPopularity() {
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        count++;
    }
}
