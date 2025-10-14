import java.net.*;
import java.io.*;

public class EServer {
    public static void main(String args[]) {
        ServerSocket s = null;
        Socket c = null;
        String line;
        DataInputStream is;
        PrintStream ps;

        try {
            // Create a server socket listening on port 9000
            s = new ServerSocket(9000);
            System.out.println("Server started. Waiting for client...");
        } 
        catch (IOException e) {
            System.out.println("Error creating server socket: " + e);
            return;
        }

        try {
            // Accept client connection
            c = s.accept();
            System.out.println("Client connected.");

            // Create input and output streams
            is = new DataInputStream(c.getInputStream());
            ps = new PrintStream(c.getOutputStream());

            // Continuously read messages and echo back to client
            while (true) {
                line = is.readLine();
                if (line == null || line.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Received from client: " + line);
                ps.println(line); // Echo the same message
            }

            // Close resources
            is.close();
            ps.close();
            c.close();
            s.close();
        } 
        catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
}
