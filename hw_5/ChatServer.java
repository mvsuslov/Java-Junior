import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    public static final int PORT = 12345;
    private Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }

    public void run() throws IOException {
        System.out.println("Chat Server is running...");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (clientWriters) {
                        if (!name.isEmpty() && !clientWriters.contains(out)) {
                            clientWriters.add(out);
                            break;
                        }
                    }
                }

                out.println("NAMEACCEPTED");
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    System.out.println(name + ": " + input);
                    for (PrintWriter writer : clientWriters) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (name != null) {
                    System.out.println(name + " is leaving");
                }
                if (out != null) {
                    clientWriters.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
