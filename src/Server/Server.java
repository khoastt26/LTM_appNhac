package Server;

import Sercurity.RSAUtil;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Server {

//    private final ServerSocket serverSocket;
    public DatagramSocket serverSocket;
    public DatagramPacket dpreceive, dpsend;
    public static int buffsize = 3000;

    public Server(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        DatagramSocket serverSocket = new DatagramSocket(1507);
        Server server = new Server(serverSocket);
        RSAUtil rsa = new RSAUtil();
        pushIP();
        String privateKey = Base64.getEncoder().encodeToString(rsa.getPrivatekey().getEncoded());
        String publicKey = Base64.getEncoder().encodeToString(rsa.getPublickey().getEncoded());
        System.out.println("\nServer Private key: " + privateKey);
        System.out.println("Server Public key: " + publicKey);
        server.startServer(rsa);
    }

    public void startServer(RSAUtil rsa) throws NoSuchAlgorithmException {
        try {
            while (!serverSocket.isClosed()) {
                dpreceive = new DatagramPacket(new byte[buffsize], buffsize);
                System.out.println("\nServer is waiting to receive...");
                //nhận packet
                this.serverSocket.receive(dpreceive);
                System.out.println("Server received");
                ClientHandler clientHandler = new ClientHandler(serverSocket, dpreceive, rsa);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }   //{"ip": ""}

    public static void pushIP() {
        try {
            InetAddress inet = InetAddress.getLocalHost();
            String localIP = inet.toString();
            localIP = localIP.split("/")[1];
            System.out.println("Push local IP: " + localIP);

            String api = "https://retoolapi.dev/srZL3M/ServerNhom07/1";
            String jsonData = "{\"ip\": \"" + localIP + "\"}";
            Jsoup.connect(api)
                    .ignoreContentType(true).ignoreHttpErrors(true)
                    .header("Content-Type", "application/json")
                    .requestBody(jsonData)
                    .method(Connection.Method.PUT).execute();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeServerSocket() {
        if (serverSocket != null) {
            serverSocket.close();
        }
    }

}
