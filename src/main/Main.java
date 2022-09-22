package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        executeBrowser();
        storeSysInfo();

    }

    public static void executeBrowser(){
        try {
            // Creates a Desktop object
            Desktop desktop = Desktop.getDesktop();
            // Invokes a desktop method browse and opens the provided link
            desktop.browse(new URI("https://hips.hearstapps.com/hmg-prod/images/gettyimages-1185282377.jpg?resize=1200:*"));
        } catch (URISyntaxException uri) {
            // Catches the Exceptions
            System.out.println("Error Incorrect String");
        } catch (IOException io){
            // Catches the Exceptions
            System.out.println("Error IO Exception Thrown");
        }
    }

    public static void storeSysInfo() throws UnknownHostException {

        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArchitecture = System.getProperty("os.arch");
        String osManufacturer = System.getProperty("os.manufacturer");
        String osArchDataModel = System.getProperty("sun.arch.data.model");
        String virtualMachineVersion = System.getProperty("java.vm.version");
        String userDir = System.getProperty("user.dir");
        String userHome = System.getProperty("user.home");
        String userName = System.getProperty("user.name");
        String userCountry = System.getProperty("user.country");
        String userLanguage = System.getProperty("user.language");
        String systemName = InetAddress.getLocalHost().getHostName();
        String hostIPAddress = InetAddress.getLocalHost().getHostAddress();
        String processorIdentifier = System.getenv("PROCESSOR_IDENTIFIER");
        String processorArchitecture = System.getenv("PROCESSOR_ARCHITECTURE");
        String processorNumbers = System.getenv("NUMBER_OF_PROCESSORS");

        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        String userTimezone = timeZone.getDisplayName();
        String userTimezoneID = timeZone.getID();


        JFrame windowPopup = new JFrame();
        ImageIcon image = new ImageIcon(Main.class.getResource("Hacked.png"));
        Image getImage = image.getImage();
        Image resizedImage = getImage.getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resizedImage);
        JOptionPane.showMessageDialog(
                windowPopup,
                "OS NAME: " + osName + "\n" +
                        "Version: " + osVersion + "\n" +
                        "System Type: " + osArchDataModel + "\n" +
                        "OS Architecture: " + osArchitecture + "\n" +
                        "OS Manufacturer: " + osManufacturer + "\n" +
                        "User Dir: " + userDir + "\n" +
                        "User Home: " + userHome + "\n" +
                        "User Name: " + userName + "\n" +
                        "System Name: " + systemName + "\n" +
                        "Host IP Address: " + hostIPAddress + "\n" +
                        "Processor Identifier: " + processorIdentifier + "\n" +
                        "Processor Architecture: " + processorArchitecture + "\n" +
                        "Processor Number: " + processorNumbers + "\n" +
                        "User Country: " + userCountry + "\n" +
                        "User Timezone: " + userTimezone + "\n" +
                        "User Timezone ID: " + userTimezoneID + "\n" +
                        "User Language: " + userLanguage + "\n",
                "YOU HAVE BEEN HACKED",
                JOptionPane.INFORMATION_MESSAGE,
                image);

    }



}
