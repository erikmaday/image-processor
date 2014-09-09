class ImageProcessor {
    private Pic picture;

    public ImageProcessor(Pic picture) {
        this.picture = picture;
    }

    /**
    * Averages the RGB color channels for each pixel and sets each
    * channel to that averaged value.
    *
    * @return The modified copy of the picture.
    */
    public Pic greyscale() {
        Pic temp = picture.deepCopy();
        Pixel[][] data = temp.getPixels();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int averageColor = (data[i][j].getRed()
                    + data[i][j].getGreen() + data[i][j].getBlue()) / 3;
                data[i][j].setRed(averageColor);
                data[i][j].setGreen(averageColor);
                data[i][j].setBlue(averageColor);
            }
        }
        return temp;
    }

    /**
    * Subtracts each of the RGB channel values from the maximum value
    * allowed (255) and sets each channel to its respective difference.
    *
    * @return The modified copy of the picture.
    */
    public Pic invert() {
        Pic temp = picture.deepCopy();
        Pixel[][] data = temp.getPixels();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j].setRed(255 - data[i][j].getRed());
                data[i][j].setGreen(255 - data[i][j].getGreen());
                data[i][j].setBlue(255 - data[i][j].getBlue());
            }
        }
        return temp;
    }

    /**
    * Sets of red value of each pixel to zero, leaving only the
    * green and the blue channels to affect the image.
    *
    * @return The modified copy of the picture.
    */
    public Pic noRed() {
        Pic temp = picture.deepCopy();
        Pixel[][] data = temp.getPixels();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j].setRed(0);
            }
        }
        return temp;
    }

    /**
    * Averages the RGB color channels for each pixel and if the
    * average is above the 127 threshold the pixel is set to
    * white, otherwise it is set to black.
    *
    * @return The modified copy of the picture.
    */
    public Pic blackAndWhite() {
        Pic temp = picture.deepCopy();
        Pixel[][] data = temp.getPixels();
        int replacementColor;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (((data[i][j].getRed() + data[i][j].getGreen()
                    + data[i][j].getBlue()) / 3) > 127) {
                    replacementColor = 255;
                } else {
                    replacementColor = 0;
                }
                data[i][j].setRed(replacementColor);
                data[i][j].setGreen(replacementColor);
                data[i][j].setBlue(replacementColor);
            }
        }
        return temp;
    }

    /**
    * For each pixel finds the channel(s) with the highest value
    * and sets the other channels to zero.
    *
    * @return The modified copy of the picture.
    */
    public Pic maximize() {
        Pic temp = picture.deepCopy();
        Pixel[][] data = temp.getPixels();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int red = data[i][j].getRed();
                int green = data[i][j].getGreen();
                int blue = data[i][j].getBlue();
                if (red < green || red < blue) {
                    data[i][j].setRed(0);
                }
                if (green < red || green < blue) {
                    data[i][j].setGreen(0);
                }
                if (blue < green || blue < red) {
                    data[i][j].setBlue(0);
                }
            }
        }
        return temp;
    }

    /**
    * Averages the RGB values of two images starting in
    * the top left corner for both images.
    *
    * @param imageName The name of the image you want to load, as a String.
    * Includes file type.
    * @return The modified copy of the picture.
    */
    public Pic overlay(Pic other) {
        Pic temp = picture.deepCopy();
        Pixel[][] data1 = temp.getPixels();
        Pixel[][] data2 = other.getPixels();
        int minHeight, minWidth;
        if (picture.getWidth() > other.getWidth()) {
            minWidth = other.getWidth();
        } else {
            minWidth = picture.getWidth();
        }
        if (picture.getHeight() > other.getHeight()) {
            minHeight = other.getHeight();
        } else {
            minHeight = picture.getHeight();
        }
        for (int i = 0; i < minHeight; i++) {
            for (int j = 0; j < minWidth; j++) {
                data1[i][j].setRed((data1[i][j].getRed()
                    + data2[i][j].getRed()) / 2);
                data1[i][j].setGreen((data1[i][j].getGreen()
                    + data2[i][j].getGreen()) / 2);
                data1[i][j].setBlue((data1[i][j].getBlue()
                    + data2[i][j].getBlue()) / 2);
            }
        }
        return temp;
    }
}