import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<ClientHandler> clients = new HashSet<>();

    public static void main(String[] args) {
        int port = 1234;
        System.out.println("Server started on port " + port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientThread = new ClientHandler(socket);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private String clientName;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Enter your name: ");
                clientName = in.readLine();
                System.out.println(clientName + " joined the chat.");
                broadcast(clientName + " joined the chat.", this);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(clientName + ": " + message);
                    broadcast(clientName + ": " + message, this);
                }
            } catch (IOException e) {
                System.out.println(clientName + " left the chat.");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clients.remove(this);
                broadcast(clientName + " left the chat.", this);
            }
        }

        void sendMessage(String message) {
            out.println(message);
        }
    }
}
