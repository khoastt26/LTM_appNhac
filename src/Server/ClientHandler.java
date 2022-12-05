package Server;

import app.API.ArtistDAO;
import app.API.SongDAO;
import Sercurity.AESUtil;
import Sercurity.RSAUtil;
import static Server.Server.buffsize;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import DTO.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class ClientHandler {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public static int clientCount = 0;
    public static int clientInputCount = 0;
    private DatagramSocket socket;
    private DatagramPacket dpreceive, dpsend;
    private RSAUtil rsa;
    private SecretKey key;
    private AESUtil aes;
//    private static String aes;

    public ClientHandler(DatagramSocket socket, DatagramPacket dpreceive, RSAUtil rsa) throws NoSuchAlgorithmException {
        try {
            System.out.println("\n=========== New ClientHandler ============");
            String data = new String(dpreceive.getData(), 0, dpreceive.getLength());
            System.out.println("Server received: " + data);
            int index = -1;

            //check tồn tại
            for (ClientHandler clientHandler : clientHandlers) {
                String ipClient = clientHandler.dpreceive.getAddress().toString();
                String ipCurrent = dpreceive.getAddress().toString();
                if (ipClient.equalsIgnoreCase(ipCurrent)) {
                    if (data.equals("e") && clientHandlers.size() > 1) {
                        clientHandlers.remove(clientHandler);
                    } else if (data.equals("e") && clientHandlers.size() == 1) {
                        index = clientHandlers.indexOf(clientHandler);
                    }
                }
            }
            //khởi tạo client mới
            if (data.equals("e")) {
                System.out.println("Client " + dpreceive.getAddress().toString() + " is created");
                System.out.println("Number of times client open the app:  " + ++clientCount);
                
                this.aes = new AESUtil();
                this.socket = socket;
                this.rsa = rsa;
                this.dpreceive = dpreceive;
                this.dpsend = new DatagramPacket(new byte[buffsize], buffsize,
                        dpreceive.getAddress(), dpreceive.getPort());
                clientHandlers.add(this);
                if (index != -1) {
                    clientHandlers.remove(index);
                }
                sendPublickey(rsa.getPublickey());
            }
            
            //khởi tạo cho client đang sử dụng app để search
            else {
                System.out.println("Client " + dpreceive.getAddress().toString() + " is searching");
                System.out.println("Number of times client search:  " + ++clientInputCount);
                System.out.println("Number of times client open the app:  " + clientCount);
                
                for (ClientHandler clientHandler : clientHandlers) {
                    String ipClient = clientHandler.dpreceive.getAddress().toString();
                    String ipCurrent = dpreceive.getAddress().toString();

                    if (ipClient.equalsIgnoreCase(ipCurrent)) {
                        this.socket = clientHandler.socket;
                        this.rsa = clientHandler.rsa;
                        this.dpreceive = dpreceive;
                        this.aes = clientHandler.aes;
                        this.dpsend = new DatagramPacket(new byte[buffsize], buffsize,
                                dpreceive.getAddress(), dpreceive.getPort());
                        break;
                    }
                }
            }
            start();

        } catch (Exception ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            closeEverything(socket);
        }
    }

    public void start() {
        String messageFromClient;
        try {
            //ép kiểu dữ liệu nhận được từ dpreceive thành kiểu String
            String check = new String(this.dpreceive.getData(), 0, this.dpreceive.getLength());
            if (check.equals("e")) {
                DatagramPacket dpSecret = new DatagramPacket(new byte[buffsize], buffsize);
                this.socket.receive(dpSecret);
                messageFromClient = new String(dpSecret.getData(), 0, dpSecret.getLength());
            } else {
                messageFromClient = new String(this.dpreceive.getData(), 0, this.dpreceive.getLength());
            }
            
            String msg = messageFromClient;
            //giải mã và set key AES Client gửi về
            if (!msg.contains("#AES#")) {
                setAESkey(msg);
            } 
            //nhận chuỗi input Search của Client và xử lý tách chuỗi
            else if (msg.contains("#AES#")) {
                String inputSearch = msg.split("#AES#")[1];
                runApp(inputSearch);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAESkey(String msg) {
        try {
            String messageFromClient = msg;
            System.out.println("Receive AES from client (not Decrypted): " + msg);
            messageFromClient = rsa.Decrypt(msg);
            System.out.println("Receive AES from client (Decrypted): " + messageFromClient);
            
            String params = messageFromClient.split("#secretkey#")[1];
            byte[] decodedKey = Base64.getDecoder().decode(params);
            this.key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            aes.setKey(key);
            System.out.println("Set AES (secrect) key sucessfully ");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void runApp(String messageFromClient) {
        try {
            if (messageFromClient.contains("#song#")) {
                runSong(messageFromClient);

            } else if (messageFromClient.contains("#artist#")) {
                runArtist(messageFromClient);

            } else {
                sendData(""); //tránh lỗi
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void runSong(String messageFromClient) {
        String inputSong = messageFromClient.split("#song#")[1];
        String input = "";
        try {
            System.out.println("Input search song (not decrypted): " + inputSong);
            input = aes.decrypt(inputSong, aes.encodeToString(aes.getKey().getEncoded()));
            System.out.println("Input search song (decrypted): " + input);
            System.out.println("Searching... ");
            ArrayList<SongDTO> dsSongInfo;
            SongDAO data = new SongDAO();
            dsSongInfo = new ArrayList<SongDTO>();
            dsSongInfo = data.docSongInfo(input);
            String output = "";
            for (SongDTO song : dsSongInfo) {
                output += "songName#" + song.getTitle() + ";";
                output += "artistName#" + song.getArtist() + ";";
                output += "image#" + song.getIconName() + ";";
                output += "lyric#" + song.getLyrics() + ";";
                output += "stream#" + song.getStream() + "$";
            }
            System.out.println("Search successfully!");
            sendData(output);
        } catch (Exception e) {
            System.out.println("error song");
            sendData("#;$"); //tránh lỗi
        }
    }

    public void runArtist(String messageFromClient) {
        String inputArtist = messageFromClient.split("#artist#")[1];
        String input = "";
        try {
            System.out.println("Input search artist (not decrypted): " + inputArtist);
            input = aes.decrypt(inputArtist, aes.encodeToString(aes.getKey().getEncoded()));
            System.out.println("Input search song (decrypted): " + input);
            System.out.println("Searching... ");
            ArrayList<ArtistDTO> dsArtistInfo;
            ArtistDAO data = new ArtistDAO();
            dsArtistInfo = new ArrayList<ArtistDTO>();
            dsArtistInfo = data.docArtistInfo(input);
            String output = "";
            for (ArtistDTO song : dsArtistInfo) {
                output += "image#" + song.getImage() + "!";
                output += "nameArtist#" + song.getNameArtist() + "!";
                output += "birthday#" + song.getBirthday() + "!";
                output += "gender#" + song.getGender() + "!";
                output += "country#" + song.getCountry() + "!";
                output += "infolife#" + song.getInfolife() + "!";
                output += "songTop#" + song.getSongTop() + "!";
                output += "album#" + song.getAlbum() + "";
            }
            System.out.println("Search successfully!");
            sendData(output);
        } catch (Exception e) {
            System.out.println("error song");
            sendData("country#error!image#https://www.computerhope.com/jargon/e/error.png!album#error;error!nameArtist#error:Unfound$"); //tránh lỗi
        }
    }

    public void sendData(String output) {
        System.out.println("Server send (not encrypted): " + output);
        String encode = aes.encrypt(output, aes.encodeToString(aes.getKey().getEncoded()));
        dpsend = new DatagramPacket(encode.getBytes(), encode.getBytes().length,
                dpreceive.getAddress(), dpreceive.getPort());
        try {
            System.out.println("Server send (encrypted): " + encode);
            socket.send(dpsend);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendPublickey(PublicKey publics) {
        try {
            if (this.dpreceive.getAddress().toString().equals(dpreceive.getAddress().toString())) {
                String publicK = Base64.getEncoder().encodeToString(publics.getEncoded());
                String strPublicK = "#publickey#" + publicK;

                //đóng gói key
                dpsend = new DatagramPacket(strPublicK.getBytes(), strPublicK.getBytes().length,
                        dpreceive.getAddress(), dpreceive.getPort());
                //gửi
                System.out.println("Server send public key to Client IP: " + dpreceive.getAddress().toString());
                socket.send(dpsend);
            }
        } catch (IOException e) {
            closeEverything(socket);
        } catch (Exception ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeEverything(DatagramSocket socket) {
        if (socket != null) {
            socket.close();
        }
    }
}
