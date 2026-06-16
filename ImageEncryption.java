import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageEncryption {
    public static void main(String[] args) {
        try {
            File inputFile = new File("sample.png");
            BufferedImage image = ImageIO.read(inputFile);

            int key = 50;

            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {

                    int pixel = image.getRGB(x, y);

                    int a = (pixel >> 24) & 0xff;
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >> 8) & 0xff;
                    int b = pixel & 0xff;

                    r = (r + key) % 256;
                    g = (g + key) % 256;
                    b = (b + key) % 256;

                    int encryptedPixel =
                            (a << 24) |
                            (r << 16) |
                            (g << 8) |
                            b;

                    image.setRGB(x, y, encryptedPixel);
                }
            }

            File outputFile = new File("encrypted.png");
            ImageIO.write(image, "png", outputFile);

            System.out.println("Image Encrypted Successfully!");
            System.out.println("Encrypted image saved as encrypted.png");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}