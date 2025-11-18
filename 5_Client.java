import java.io.*;
import java.net.*;

public class UdpDnsClient {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress ipAddress;

        if (args.length == 0) {
            ipAddress = InetAddress.getLocalHost();
        } else {
            ipAddress = InetAddress.getByName(args[0]);
        }

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        int port = 1362;

        System.out.print("Enter the hostname: ");
        String sentence = br.readLine();

        sendData = sentence.getBytes();

        DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, ipAddress, port);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String modified = new String(receivePacket.getData()).trim();

        System.out.println("IP Address: " + modified);

        clientSocket.close();
    }
}
