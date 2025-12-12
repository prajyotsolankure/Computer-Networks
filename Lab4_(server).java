import java.net.*;
import java.io.*;

public class Main {

    public static void main(String args[]) {
        try {
            // Create a server socket on port 9000
            ServerSocket server = new ServerSocket(9000);
            System.out.println("Server started... Waiting for client...");

            // Accept client connection
            Socket client = server.accept();
            System.out.println("Client connected!");

            // Input & Output streams
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            // Keep reading & sending back (echo)
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client: " + message);
                out.println(message);  // Echo back
            }

            // Close all
            in.close();
            out.close();
            client.close();
            server.close();

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
