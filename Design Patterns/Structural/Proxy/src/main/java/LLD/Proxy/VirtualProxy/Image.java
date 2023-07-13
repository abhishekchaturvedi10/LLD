package LLD.Proxy.VirtualProxy;

public abstract class Image {

    protected String imageFilePath;
    protected String imageName;

    public Image(String imageFilePath, String imageName) {
        this.imageFilePath = imageFilePath;
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public abstract void showImage();
}
