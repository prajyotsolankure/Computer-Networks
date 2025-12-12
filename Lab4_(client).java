import java.io.*;
import java.net.*;

public class client{
    public static void main(String args[]){
        try {
            Socket socket = new Socket(InetAddress.getLocalHost() , 9000);
            System.out.println("Connected to Server");
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out  = new PrintWriter(socket.getOutputStream(),true);
            while(true){
                System.out.println("Client : ");
                String msg = keyboard.readLine();
                out.println(msg);

                String msg1 = in.readLine();
                System.out.println("Server : "+ msg1);

            }
        } catch (IOException e){
            System.out.println("Connection Closed : "+e);
        }
    }
}
