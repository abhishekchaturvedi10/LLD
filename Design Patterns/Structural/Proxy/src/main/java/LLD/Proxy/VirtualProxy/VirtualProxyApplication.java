package LLD.Proxy.VirtualProxy;

import java.util.ArrayList;

public class VirtualProxyApplication {

    public static void main(String[] args) {

        ArrayList<Image> imageFolder = new ArrayList<>();

        Image image1 = new ImageProxy("sample/veryHighResPhoto1.jpeg", "Image1");
        Image image2 = new ImageProxy("sample/veryHighResPhoto2.jpeg", "Image2");
        Image image3 = new ImageProxy("sample/veryHighResPhoto3.jpeg", "Image3");

        imageFolder.add(image1);
        imageFolder.add(image2);
        imageFolder.add(image3);

        System.out.println("Names of images in the folder");
        for (Image image : imageFolder) {
            System.out.println(image.getImageName());
        }

        image2.showImage();
    }
}
