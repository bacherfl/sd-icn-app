package tg.content;

/**
 * Created by florian on 12/06/15.
 */
public class DummyContentInfo {

    private String contentName;

    private double sizeInMB;

    public DummyContentInfo(String contentName, double sizeInMB) {
        this.contentName = contentName;
        this.sizeInMB = sizeInMB;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public double getSizeInMB() {
        return sizeInMB;
    }

    public void setSizeInMB(double sizeInMB) {
        this.sizeInMB = sizeInMB;
    }
}
