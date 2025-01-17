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

    // Method to change brightness
    public static BufferedImage changebrightness(BufferedImage inImage,int a){
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage output = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Color pixel = new Color(inImage.getRGB(j,i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                red+= (a*red)/100;
                green+=(a*green)/100;
                blue+=(a*blue)/100;
                if(red>255)
                    red=255;
                if(green>255)
                    green=255;
                if(blue>255)
                    blue=255;
                if(red<0)
                    red=0;
                if(green<0)
                    green=0;
                if(blue<0)
                    blue=0;
                Color newpixel = new Color(red,green,blue);
                inImage.setRGB(j,i,newpixel.getRGB());}}
        return inImage;
    }

    // Method to blur an image
    public static BufferedImage blurImage(BufferedImage inImage,int blrRatio){
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height , BufferedImage.TYPE_INT_RGB);
        for (int i=0;i<height;i+=blrRatio){
            for (int j=0;j<width;j+=blrRatio){
                int sumofRED=0,sumofGREEN=0,sumofBLUE=0,sumofAlpha=0;
                int count=0;
                for(int k=i;k<i+blrRatio;k++){
                    for (int l=j;l<j+blrRatio;l++){
                        if (k<height && l<width) {
                            Color pixel = new Color(inImage.getRGB(l, k));
                            sumofRED += pixel.getRed();
                            sumofGREEN += pixel.getGreen();
                            sumofBLUE += pixel.getBlue();
                            sumofAlpha += pixel.getAlpha();
                            count++;
                        }
                    }
                }
                sumofRED=sumofRED/count;
                sumofGREEN=sumofGREEN/count;
                sumofBLUE=sumofBLUE/count;
                sumofAlpha=sumofAlpha/count;
                for(int k=i;k<i+blrRatio;k++){
                    for(int l=j;l<j+blrRatio;l++){
                        if (k<height && l<width){
                            Color newpixel = new Color(sumofRED,sumofGREEN,sumofBLUE,sumofAlpha);
                            outputImage.setRGB(l,k,newpixel.getRGB());
                        }
                    }
                }

            }
        }
        return outputImage;
    }

    // Method to print pixel values of an image
    public static void printpixelvalues(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(inImage.getRGB(j, i) + " ");
            }
        }
    }

    // Method to convert to greyscale

    public static BufferedImage convertToGrayScale(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    // Method to convert to GreenScale
    public static BufferedImage GreenScale(BufferedImage inImage){
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage output = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Color pixel = new Color(inImage.getRGB(j,i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                green+=(50*green)/100;
                if(green>255)
                    green=255;
                if(green<0)
                    green=0;
                Color newpixel = new Color(red,green,blue);
                output.setRGB(j,i,newpixel.getRGB());}}
        return output;
    }

    // Method to convert to BlueScale
    public static BufferedImage BlueScale(BufferedImage inImage){
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage output = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Color pixel = new Color(inImage.getRGB(j,i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                blue+=(100*blue)/100;

                if(blue>255)
                    blue=255;

                if(blue<0)
                    blue=0;
                Color newpixel = new Color(red,green,blue);
                inImage.setRGB(j,i,newpixel.getRGB());}}
        return inImage;
    }

    // Method to convert to RedScale
    public static BufferedImage RedScale(BufferedImage inImage){
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage output = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Color pixel = new Color(inImage.getRGB(j,i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                red+= (50*red)/100;
                if(red>255)
                    red=255;
                if(red<0)
                    red=0;
                Color newpixel = new Color(red,green,blue);
                inImage.setRGB(j,i,newpixel.getRGB());}}
        return inImage;


    }

    // Method to resize an image
    public static BufferedImage resizeImage(BufferedImage inImage, int newWidth, int newHeight) {
        int width = inImage.getWidth();
        int height = inImage.getHeight();
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();

        // Apply a rendering hint for better quality
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(inImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return resizedImage;
    }

    // Method to convert an image to Sepia
    public static BufferedImage applySepiaTone(BufferedImage inImage) {
        int width = inImage.getWidth();
        int height = inImage.getHeight();
        BufferedImage sepiaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inImage.getRGB(j, i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();

                // Apply sepia tone formula
                int tr = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int tg = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                int tb = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                // Clamp values to the range [0, 255]
                red = Math.min(255, tr);
                green = Math.min(255, tg);
                blue = Math.min(255, tb);

                Color newPixel = new Color(red, green, blue);
                sepiaImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return sepiaImage;
    }

    // Method to invert the colors of an image
    public static BufferedImage invertColors(BufferedImage inImage) {
        int width = inImage.getWidth();
        int height = inImage.getHeight();
        BufferedImage invertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inImage.getRGB(j, i));
                int red = 255 - pixel.getRed();
                int green = 255 - pixel.getGreen();
                int blue = 255 - pixel.getBlue();

                Color newPixel = new Color(red, green, blue);
                invertedImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return invertedImage;
    }

    // Method to rotate an image by an arbitrary angle
    public static BufferedImage rotateImage(BufferedImage inImage, double angle) {
        int width = inImage.getWidth();
        int height = inImage.getHeight();
        double radianAngle = Math.toRadians(angle);
        double cos = Math.abs(Math.cos(radianAngle));
        double sin = Math.abs(Math.sin(radianAngle));
        int newWidth = (int) Math.floor(width * cos + height * sin);
        int newHeight = (int) Math.floor(height * cos + width * sin);
        BufferedImage output = new BufferedImage(newWidth, newHeight, inImage.getType());
        Graphics2D g2d = output.createGraphics();
        g2d.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g2d.rotate(radianAngle, width / 2, height / 2);
        g2d.drawRenderedImage(inImage, null);
        g2d.dispose();
        return output;
    }

    // Method to convert image to black and white
    public static BufferedImage convertToBlackAndWhite(BufferedImage inImage) {
        int width = inImage.getWidth();
        int height = inImage.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(inImage, 0, 0, null);
        g2d.dispose();
        return output;
    }

    // Method to add text to an image
    public static BufferedImage addTextToImage(BufferedImage inImage, String text, int x, int y) {
        BufferedImage output = new BufferedImage(inImage.getWidth(), inImage.getHeight(), inImage.getType());
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(inImage, 0, 0, null);
        g2d.setPaint(Color.RED);
        g2d.setFont(new Font("Serif", Font.BOLD, 35));
        g2d.drawString(text, x, y);
        g2d.dispose();
        return output;
    }

    public static BufferedImage applyGaussianBlur(BufferedImage inImage) {
        int radius = 5; // Radius of the Gaussian blur
        int size = radius * 2 + 1;
        float[] kernel = createGaussianKernel(radius);

        int width = inImage.getWidth();
        int height = inImage.getHeight();
        BufferedImage blurredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Apply the Gaussian blur in the horizontal direction
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float red = 0, green = 0, blue = 0;
                for (int k = -radius; k <= radius; k++) {
                    int pixelX = Math.min(Math.max(x + k, 0), width - 1);
                    Color pixel = new Color(inImage.getRGB(pixelX, y));
                    red += pixel.getRed() * kernel[k + radius];
                    green += pixel.getGreen() * kernel[k + radius];
                    blue += pixel.getBlue() * kernel[k + radius];
                }
                Color newPixel = new Color(clamp((int) red), clamp((int) green), clamp((int) blue));
                blurredImage.setRGB(x, y, newPixel.getRGB());
            }
        }

        // Apply the Gaussian blur in the vertical direction
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float red = 0, green = 0, blue = 0;
                for (int k = -radius; k <= radius; k++) {
                    int pixelY = Math.min(Math.max(y + k, 0), height - 1);
                    Color pixel = new Color(blurredImage.getRGB(x, pixelY));
                    red += pixel.getRed() * kernel[k + radius];
                    green += pixel.getGreen() * kernel[k + radius];
                    blue += pixel.getBlue() * kernel[k + radius];
                }
                Color newPixel = new Color(clamp((int) red), clamp((int) green), clamp((int) blue));
                blurredImage.setRGB(x, y, newPixel.getRGB());
            }
        }

        return blurredImage;
    }

    // Helper method to create a Gaussian kernel
    private static float[] createGaussianKernel(int radius) {
        int size = radius * 2 + 1;
        float[] kernel = new float[size];
        float sigma = radius / 3.0f;
        float sum = 0;
        for (int i = -radius; i <= radius; i++) {
            kernel[i + radius] = (float) Math.exp(-0.5 * (i * i) / (sigma * sigma));
            sum += kernel[i + radius];
        }
        for (int i = 0; i < size; i++) {
            kernel[i] /= sum;
        }
        return kernel;
    }

    // Helper method to clamp color values to the range [0, 255]
    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
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
            System.out.println("Enter 5 for Change IMAGE BRIGHTNESS");
            System.out.println("Enter 6 for convert image to BlurImage");
            System.out.println("Enter 7 for Print PIXEL VALUES of image");
            System.out.println("Enter 8 for convert image to GRAYSCALE");
            System.out.println("Enter 9 for convert image to GREENSCALE");
            System.out.println("Enter 10 for convert image to BLUESCALE");
            System.out.println("Enter 11 for convert image to REDSCALE");
            System.out.println("Enter 12 for Resize Image");
            System.out.println("Enter 13 for applying Sepia Tone");
            System.out.println("Enter 14 for Dramatic Effect");
            System.out.println("Enter 15 for rotating an image by an arbitrary angle");
            System.out.println("Enter 16 for Black and White Image");
            System.out.println("Enter 17 to add text to an image");
            System.out.println("Enter 18 for Gaussian Blur");









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
                case 5:
                    System.out.println("Please specify the percentage by which you would like to increase the brightness");
                    int a = sc.nextInt();
                    result = changebrightness(inputImage,a);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    break;
                case 6:
                    System.out.println("Please specify the ratio by which you would like to blur the image");
                    int blrRatio = sc.nextInt();
                    result = blurImage(inputImage,blrRatio);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    break;
                case 7:
                    printpixelvalues(inputImage);
                    break;
                case 8:
                    result = convertToGrayScale(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Image converted to grayscale. Output saved as output.jpg.");
                    break;
                case 9:
                    result = GreenScale(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Image converted to GreenScale. Output saved as output.jpg.");
                    break;
                case 10:
                    result = BlueScale(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Image converted to BlueScale. Output has been saved as output.jpg.");
                    break;
                case 11:
                    result = RedScale(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Image converted to RedScale. Output has been saved as output.jpg.");
                    break;
                case 12:
                    System.out.println("Enter the new width:");
                    int newWidth = sc.nextInt();
                    System.out.println("Enter the new height:");
                    int newHeight = sc.nextInt();
                    result = resizeImage(inputImage, newWidth, newHeight);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Image resized. Output saved as output.jpg.");
                    break;
                case 13:
                    result = applySepiaTone(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Sepia tone applied. Output saved as output.jpg.");
                    break;
                case 14:
                    result = invertColors(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Color inversion applied. Output saved as output.jpg.");
                    break;
                case 15:
                    System.out.println("Enter the angle by which you would like to rotate the image:");
                    double angle = sc.nextDouble();
                    result = rotateImage(inputImage, angle);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Image rotated. Output saved as output.jpg.");
                    break;
                case 16:
                    result = convertToBlackAndWhite(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Image converted to black and white. Output saved as output.jpg.");
                    break;
                case 17:
                    System.out.println("Enter the text you would like to add to the image:");
                    sc.nextLine();
                    String text = sc.nextLine();
                    System.out.println("Enter the x-coordinate where you would like to place the text:");
                    int x = sc.nextInt();
                    System.out.println("Enter the y-coordinate where you would like to place the text:");
                    int y = sc.nextInt();
                    result = addTextToImage(inputImage, text, x, y);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Text added to image. Output saved as output.jpg.");
                    break;
                case 18:
                    result = applyGaussianBlur(inputImage);
                    output = new File("output.jpg");
                    ImageIO.write(result, "jpg", output);
                    System.out.println("Gaussian blur applied. Output saved as output.jpg.");
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
