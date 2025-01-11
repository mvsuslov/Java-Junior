import java.io.*;
import java.net.*;

public class ChatClient {
    private String serverAddress;
    private String userName;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ChatClient(String serverAddress, String userName) {
        this.serverAddress = serverAddress;
        this.userName = userName;
    }

    public void run() throws IOException {
        socket = new Socket(serverAddress, ChatServer.PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println(userName);

        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(userName);
            } else if (line.startsWith("NAMEACCEPTED")) {
                break;
            } else if (line.startsWith("MESSAGE")) {
                System.out.println(line.substring(8));
            }
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String userInput = stdIn.readLine();
            if (userInput != null) {
                out.println(userInput);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java ChatClient <Server Address> <User Name>");
            return;
        }
        String serverAddress = args[0];
        String userName = args[1];
        new ChatClient(serverAddress, userName).run();
    }
}
