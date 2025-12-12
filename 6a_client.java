import java.io.*;
import java.net.*;

public class client{
    public static void main(String args[]){
        try {
            Socket socket = new Socket(InetAddress.getLocalHost() , 1309);
            System.out.println("Connected to Server");
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out  = new PrintWriter(socket.getOutputStream(),true);

                System.out.println("Enter ip address : ");
                String msg = keyboard.readLine();
                out.println(msg);

                String msg1 = in.readLine();
                if(msg1.equals("MAC Not Found")){
                    System.out.println("Server : "+ msg1);
                } else {
                    System.out.println("Physical address: " + msg1);
                }

        } catch (IOException e){
            System.out.println("Connection Closed : "+e);
        }
    }
}
