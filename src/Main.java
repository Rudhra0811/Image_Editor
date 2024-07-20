import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Main {

    // Method to vertically invert an image
    public static BufferedImage verticallyInvert(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage vInvertImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                vInvertImage.setRGB(j, height - i - 1, inImage.getRGB(j, i));
            }
        }
        return vInvertImage;
    }

    // Method to horizontally invert an image
    public static BufferedImage horizontallyinvert(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage HinvertImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                HinvertImage.setRGB(width - j - 1, i, inImage.getRGB(j, i));
            }
        }

        return HinvertImage;
    }

    // Left the image

    public static BufferedImage Lefttransposeimage(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedImage.setRGB(i,j,inImage.getRGB(j,i));
            }
        }
        int index=rotatedImage.getHeight()-1;
        for(int j=0;j<rotatedImage.getWidth();j++){
            for(int i=0;i<rotatedImage.getHeight()/2;i++){
                Color temp=new Color(rotatedImage.getRGB(j,i));
                rotatedImage.setRGB(j,i,rotatedImage.getRGB(j,index-i));
                rotatedImage.setRGB(j,index-i,temp.getRGB());
            }
        }
        return rotatedImage;
    }

    // Right the image

    public static BufferedImage Righttransposeimage(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedImage.setRGB(i,j,inImage.getRGB(j,i));
            }
        }
        int index=rotatedImage.getWidth()-1;
        for(int i=0;i<rotatedImage.getHeight();i++){
            for(int j=0;j<rotatedImage.getWidth()/2;j++){
                Color temp=new Color(rotatedImage.getRGB(j,i));
                rotatedImage.setRGB(j,i,rotatedImage.getRGB(index-j,i));
                rotatedImage.setRGB(index-j,i,temp.getRGB());
            }
        }
        return rotatedImage;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the full path of the file: ");
        String S = sc.nextLine();

        try {
            File inputFile = new File(S);
            if (!inputFile.exists()) {
                System.out.println("The specified file does not exist.");
                return;
            }

            BufferedImage inputImage = ImageIO.read(inputFile);
            if (inputImage == null) {
                System.out.println("The file is not a valid image.");
                return;
            }

            // CLI UI
            System.out.println("Enter 1 for VERTICAL Inversion");
            System.out.println("Enter 2 for HORIZONTAL Inversion");
            System.out.println("Enter 3 for Rotate Left");
            System.out.println("Enter 4 for Rotate Right");



            // Output processing
            int operation = sc.nextInt();
            BufferedImage result;
            File output;

            switch (operation) {
                case 1:
                    result = verticallyInvert(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Vertical inversion completed. Output saved as output.jpg.");
                    break;
                case 2:
                    result = horizontallyinvert(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Horizontal inversion completed. Output saved as output.jpg.");
                    break;
                case 3:
                    result = Lefttransposeimage(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Left transpose completed. Output saved as output.jpg.");
                    break;
                case 4:
                    result = Righttransposeimage(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Right transpose completed. Output saved as output.jpg.");
                    break;
                default:
                    System.out.println("Invalid operation");
                    break;
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
