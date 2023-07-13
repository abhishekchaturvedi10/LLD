package LLD.Proxy.VirtualProxy;

public class ImageProxy extends Image {

    private Image proxifiedImage;

    public ImageProxy(String imageFilePath, String imageName) {
        super(imageFilePath, imageName);
    }

    public void showImage() {
        System.out.println("Initialising high resolution " + imageName + " object in proxy...");
        proxifiedImage = new HighResolutionImage(imageFilePath, imageName);
        System.out.println("Calling high resolution " + imageName + " object's \"showImage\" in proxy...");
        proxifiedImage.showImage();
    }
}
