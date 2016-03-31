/**
 * Created by brianwallace on 3/31/16.
 * Used java's EchoServer.java example as guide to make
 */


import java.net.*;
import java.io.*;


public class SocketServer {

    public static void main(String[] args) {
        // Make sure user enters correct arguements
        if(args.length != 1){
            System.out.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        // Server acknowledging it receives a port to listen to
        String input[] = args[0].split(":");
        int portNum = Integer.parseInt(input[input.length-1]);
        System.out.println("Setting up listening on port " + portNum);

        try (
            ServerSocket serverSocket =
                    new ServerSocket(portNum);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

        ){
            String clientMessage;
            // While client sends valid inputs
            while((clientMessage = in.readLine()) != null){

                out.println(clientMessage);
                System.out.println("Client says " + clientMessage);
            }
        }
        catch (IOException e) {
            System.out.println("Problem listening on port" + portNum);
            e.printStackTrace();
        }


    }


}
