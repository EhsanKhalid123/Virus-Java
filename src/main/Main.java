package main;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Main {

    public static void main(String[] args) {
        try {
            // Creates a Desktop object
            Desktop desktop = Desktop.getDesktop();
            // Invokes a desktop
            desktop.browse(new URI("https://hips.hearstapps.com/hmg-prod/images/gettyimages-1185282377.jpg?resize=1200:*"));
        } catch (URISyntaxException uri) {
            System.out.println("Error Incorrect String");
        } catch (IOException io){
            System.out.println("Error IO Exception Thrown");
        }
    }
}
