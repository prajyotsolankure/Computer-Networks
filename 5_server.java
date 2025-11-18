import java.io.*;
import java.net.*;

public class UdpDnsServer {

    private static int indexOf(String[] array, String str) {
        str = str.trim();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(str))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        
        String[] hosts = { "yahoo.com", "gmail.com", "cricinfo.com", "facebook.com" };
        String[] ip = { "68.180.206.184", "209.85.148.19", "80.168.92.140", "69.63.189.16" };

        DatagramSocket serverSocket = new DatagramSocket(1362);
        
        System.out.println("UDP DNS Server Started on port 1362");
        System.out.println("Press Ctrl+C to Quit");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        while (true) {

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String receivedHost = new String(receivePacket.getData()).trim();
            InetAddress clientIP = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            System.out.println("Request for host: " + receivedHost);

            String response;

            int idx = indexOf(hosts, receivedHost);
            if (idx != -1) {
                response = ip[idx];
            } else {
                response = "Host Not Found";
            }

            sendData = response.getBytes();

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, clientIP, clientPort);

            serverSocket.send(sendPacket);
        }
    }
}
