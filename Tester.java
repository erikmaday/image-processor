class Tester {
    public static void main(String[] args) {
        ImageProcessor pro = new ImageProcessor(new Pic("images/marshall.jpg"));
        pro.greyscale().show();
        pro.invert().show();
        pro.noRed().show();
        pro.blackAndWhite().show();
        pro.maximize().show();
        pro.overlay(new Pic("images/bearRoar.jpg")).show();
        pro.overlay(new Pic("images/logo.jpg")).show();
    }
}