import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Create server on port 139
            ServerSocket server = new ServerSocket(1309);
            System.out.println("ARP Server Started... Waiting for client...");

            Socket client = server.accept();
            System.out.println("Client Connected!");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            // IP–MAC table
            String[] ipList  = { "165.165.80.80", "165.165.79.1" };
            String[] macList = { "6A:08:AA:C2",   "8A:BC:E3:FA" };

            while (true) {
                // Read requested IP
                String requestIP = in.readLine();
                if (requestIP == null) break;

                System.out.println("Requested IP: " + requestIP);

                boolean found = false;

                // Search for IP
                for (int i = 0; i < ipList.length; i++) {
                    if (requestIP.equals(ipList[i])) {
                        out.println(macList[i]);   // Send MAC address
                        found = true;
                        break;
                    }
                }

                // If not found
                if (!found) {
                    out.println("MAC Not Found");
                }
            }

            client.close();
            server.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
