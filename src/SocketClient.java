/**
 * Created by brianwallace on 3/31/16.
 *  * Used java's EchoClient.java example as guide to make
 */

import java.io.*;
import java.net.*;

public class SocketClient {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        // Parse user input. We need the port number
        String inputHost[] = args[0].split("=");
        String host = inputHost[inputHost.length - 1];
        String inputPort[] = args[1].split("=");
        Integer port = Integer.parseInt(inputPort[inputHost.length - 1]);

        try(
                Socket echoSocket = new Socket(host, port);
                PrintWriter out =
                    new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                    new BufferedReader(
                            new InputStreamReader(System.in))
        ){
            // Notify user that he or she has a connection to host
            System.out.println("Connected to " + host + " on port " + port);
            String clientMessage;
            while((clientMessage = stdIn.readLine()) != null){
                out.println(clientMessage);
                System.out.println("Sent " + clientMessage + "to host " + host);
                System.out.println("Host received message. Sent this message back: " + in.readLine());
            }
        }
        catch (UnknownHostException e) {
            System.err.println("Something isn't working");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Something isn't working");
            System.exit(1);
        }
    }
}
