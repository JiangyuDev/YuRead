package pers.jiangyu.yuread.bean;

public class MySettings {

    private  String name;

    private int imageId;

    public MySettings(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}
