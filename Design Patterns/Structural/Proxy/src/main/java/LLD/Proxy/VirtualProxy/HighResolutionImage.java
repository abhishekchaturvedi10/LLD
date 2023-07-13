package LLD.Proxy.VirtualProxy;

public class HighResolutionImage extends Image {

    public HighResolutionImage(String imageFilePath, String imageName) {
        super(imageFilePath, imageName);
        System.out.println("Creating a high resolution " + imageName + " object...");
    }

    private void loadImage() {
        System.out.println("Loading " + imageName + " in high resolution from disk to memory...");
    }

    public void showImage() {
        loadImage();
        System.out.println("Rendering " + imageName + " in high resolution...");
    }
}
