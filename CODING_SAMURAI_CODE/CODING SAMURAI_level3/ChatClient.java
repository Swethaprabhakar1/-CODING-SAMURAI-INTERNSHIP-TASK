import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        try (
            Socket socket = new Socket(host, port);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // Thread to receive messages from server
            Thread readThread = new Thread(() -> {
                String msgFromServer;
                try {
                    while ((msgFromServer = in.readLine()) != null) {
                        System.out.println(msgFromServer);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            readThread.start();

            // Main thread to send messages
            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                out.println(userMessage);
            }
        } catch (IOException e) {
            System.out.println("Unable to connect to server.");
            e.printStackTrace();
        }
    }
}
