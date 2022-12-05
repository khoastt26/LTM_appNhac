package Client;

import Sercurity.AESUtil;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class Client {

    public DatagramSocket socket;
    public DatagramPacket dpreceive, dpsend;
    public AESUtil aes;
    public PublicKey pubkey;
    public InetAddress iadd;

    public static int destPort = 1507;
    public static String APIHostname = getIPServer();
    public String myIP = getMyIP();
    public static int buffsize = 10000;

    public Client(DatagramSocket socket) throws NoSuchAlgorithmException {
        try {
            this.aes = new AESUtil();
            this.aes.init();
            this.iadd = InetAddress.getByName(APIHostname);
            this.dpsend = new DatagramPacket("e".getBytes(), "e".getBytes().length, iadd, destPort);
            this.dpreceive = new DatagramPacket(new byte[buffsize], buffsize);
            this.socket = socket;
        } catch (IOException e) {
            closeEverything(socket);
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeEverything(DatagramSocket socket) {
        if (socket != null) {
            socket.close();
        }
    }

    public static String getIPServer() {
        String api = "https://retoolapi.dev/srZL3M/ServerNhom07/1";
        try {
            Document doc = Jsoup.connect(api)
                    .ignoreContentType(true).ignoreHttpErrors(true)
                    .header("Content-Type", "application/json")
                    .method(Connection.Method.GET).execute().parse();
            JSONObject jsonObject = new JSONObject(doc.text());
            System.out.println("Get Server IP: " + jsonObject.get("ip").toString());
            return jsonObject.get("ip").toString();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "192.168.13.213";
    }
    
    public static String getMyIP(){
        InetAddress my = null;
        try {
            my = InetAddress.getLocalHost();
            return my.toString();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return my.toString();
    }
}
