import java.net.*;
import java.io.*;

public class EClient {
    public static void main(String arg[]) {
        Socket c = null;
        String line;
        DataInputStream is, is1;
        PrintStream os;

        try {
            // Connect to server running on localhost at port 9000
            InetAddress ia = InetAddress.getLocalHost();
            c = new Socket(ia, 9000);
            System.out.println("Connected to server.");
        } 
        catch (IOException e) {
            System.out.println("Error connecting to server: " + e);
            return;
        }

        try {
            // Create input/output streams
            os = new PrintStream(c.getOutputStream());
            is = new DataInputStream(System.in);
            is1 = new DataInputStream(c.getInputStream());

            // Communication loop
            while (true) {
                System.out.print("Client: ");
                line = is.readLine();
                os.println(line);

                if (line.equalsIgnoreCase("exit")) {
                    System.out.println("Connection closed by client.");
                    break;
                }

                System.out.println("Server: " + is1.readLine());
            }

            // Close resources
            os.close();
            is.close();
            is1.close();
            c.close();
        } 
        catch (IOException e) {
            System.out.println("Socket Closed! " + e);
        }
    }
}
