/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.*;
import Client.SongListClient;
import Client.Client;
import Sercurity.RSAUtil;

import java.awt.BorderLayout;
import java.awt.Image;

import java.io.IOException;

import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.DatagramPacket;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.ImageIcon;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author PC
 */
public final class mainGUI extends javax.swing.JFrame {

    /**
     * Creates new form mainGUI
     */
    private Client client;
    private SongListClient songList;

    public static int destPort = 1507;

    public void Init() {
        try {
            DatagramSocket socket = new DatagramSocket();
            this.client = new Client(socket);
            System.out.println("\n=========== Connected ============ ");
            
            sendEmpty();
            while (true) {
                if (listenForMessage()) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public mainGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        pnList = new javax.swing.JPanel();
        inputSong = new javax.swing.JTextField();
        btSong = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txLyric = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        inputArtist = new javax.swing.JTextField();
        btArtist = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pnImage = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbBirthDay = new javax.swing.JLabel();
        lbGender = new javax.swing.JLabel();
        lbCountry = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txInfoLife = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txAlbumTop = new javax.swing.JTextArea();
        pnSongTop = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bài hát");
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("TÌM THÔNG TIN BÀI HÁT - CA SĨ");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(51, 51, 51));

        pnList.setBackground(new java.awt.Color(255, 255, 255));
        pnList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnList.setForeground(new java.awt.Color(51, 51, 51));
        pnList.setPreferredSize(new java.awt.Dimension(506, 300));
        pnList.setLayout(new java.awt.BorderLayout());

        inputSong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        inputSong.setToolTipText("");
        inputSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSongActionPerformed(evt);
            }
        });

        btSong.setBackground(new java.awt.Color(255, 153, 102));
        btSong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btSong.setForeground(new java.awt.Color(255, 255, 255));
        btSong.setText("Tìm kiếm");
        btSong.setBorder(null);
        btSong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSongActionPerformed(evt);
            }
        });

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setForeground(new java.awt.Color(51, 51, 51));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(51, 51, 51));

        txLyric.setEditable(false);
        txLyric.setBackground(new java.awt.Color(255, 255, 255));
        txLyric.setColumns(20);
        txLyric.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txLyric.setForeground(new java.awt.Color(51, 51, 51));
        txLyric.setLineWrap(true);
        txLyric.setRows(5);
        txLyric.setWrapStyleWord(true);
        txLyric.setCaretColor(new java.awt.Color(51, 51, 51));
        txLyric.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txLyric.setSelectionColor(new java.awt.Color(255, 153, 102));
        jScrollPane1.setViewportView(txLyric);

        jScrollPane3.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inputSong, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnList, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btSong, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputSong, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(btSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnList, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bài hát", jPanel3);

        inputArtist.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        inputArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputArtistActionPerformed(evt);
            }
        });

        btArtist.setBackground(new java.awt.Color(255, 153, 102));
        btArtist.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btArtist.setForeground(new java.awt.Color(255, 255, 255));
        btArtist.setText("Tìm kiếm");
        btArtist.setBorder(null);
        btArtist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btArtistActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pnImage.setBackground(new java.awt.Color(255, 153, 102));

        javax.swing.GroupLayout pnImageLayout = new javax.swing.GroupLayout(pnImage);
        pnImage.setLayout(pnImageLayout);
        pnImageLayout.setHorizontalGroup(
            pnImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );
        pnImageLayout.setVerticalGroup(
            pnImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(51, 51, 51));
        lbName.setText("Họ tên");

        lbBirthDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbBirthDay.setForeground(new java.awt.Color(51, 51, 51));
        lbBirthDay.setText("Ngày sinh");

        lbGender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbGender.setForeground(new java.awt.Color(51, 51, 51));
        lbGender.setText("Giới tính");

        lbCountry.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCountry.setForeground(new java.awt.Color(51, 51, 51));
        lbCountry.setText("Quê quán");

        txInfoLife.setEditable(false);
        txInfoLife.setBackground(new java.awt.Color(255, 255, 255));
        txInfoLife.setColumns(10);
        txInfoLife.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txInfoLife.setForeground(new java.awt.Color(51, 51, 51));
        txInfoLife.setLineWrap(true);
        txInfoLife.setRows(5);
        txInfoLife.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txInfoLife);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Bài hát nổi tiếng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Album");

        txAlbumTop.setEditable(false);
        txAlbumTop.setBackground(new java.awt.Color(255, 255, 255));
        txAlbumTop.setColumns(20);
        txAlbumTop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txAlbumTop.setForeground(new java.awt.Color(51, 51, 51));
        txAlbumTop.setRows(5);
        jScrollPane5.setViewportView(txAlbumTop);

        pnSongTop.setBackground(new java.awt.Color(255, 255, 255));
        pnSongTop.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnSongTop.setPreferredSize(new java.awt.Dimension(506, 300));
        pnSongTop.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Tiểu sử");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 295, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pnSongTop, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76))
                            .addComponent(jScrollPane5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbBirthDay, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(lbGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 123, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbName)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbBirthDay)
                                .addGap(18, 18, 18)
                                .addComponent(lbGender)
                                .addGap(18, 18, 18)
                                .addComponent(lbCountry)
                                .addGap(13, 13, 13))
                            .addComponent(pnImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnSongTop, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(inputArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputArtist, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btArtist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ca sĩ", jPanel4);

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(311, 311, 311))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Bài hát");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputSongActionPerformed

    private void inputArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputArtistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputArtistActionPerformed

    private void btSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSongActionPerformed
        try {
            String messageSend = inputSong.getText().trim();
            //gửi
            sendWhat(messageSend, "#song#");
            //chờ server gửi trả kết quả
            //nhận về
            String strDTO = receive();
            String input = client.aes.decrypt(strDTO, client.aes.encodeToString(client.aes.getKey().getEncoded()));
            System.out.println("Client received (decrypted): " + input);
            ArrayList<SongDTO> arrSongDTO = supportBtnSong(input);
            //giao diện
            pnList.removeAll();
            pnList.repaint();
            songList = new SongListClient(arrSongDTO);
            pnList.add(songList, BorderLayout.CENTER);
        } catch (Exception ex) {
            Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSongActionPerformed
    private void btArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btArtistActionPerformed
        try {
        // =============================== lấy thông tin ca sĩ =================================
            String messageSend = inputArtist.getText().trim();
            //gửi
            sendWhat(messageSend, "#artist#");
            //chờ server gửi trả kết quả
            //nhận về
            String strDTO = receive();
            String input = client.aes.decrypt(strDTO, client.aes.encodeToString(client.aes.getKey().getEncoded()));
            System.out.println("Client received (decrypted): " + input);

            ArrayList<ArtistDTO> arrArtistDTO = supportBtnArtist(input);
            //check lỗi
            if (arrArtistDTO.get(0).getCountry().equalsIgnoreCase("")) {
                showArtistError();
            } else {
                //giao diện
                ArtistDTO artistDTO = new ArtistDTO();
                artistDTO = arrArtistDTO.get(0); //lấy tên ca sĩ
                showArtist(artistDTO);
                
        // =========================== lấy danh sách bài hát top ================================
                StringTokenizer stSong = new StringTokenizer(artistDTO.getNameArtist(), ";");
                String song = stSong.nextToken();
                messageSend = song;
                //gửi
                sendWhat(messageSend, "#song#");
                //nhận
                strDTO = receive();
                input = client.aes.decrypt(strDTO, client.aes.encodeToString(client.aes.getKey().getEncoded()));
                System.out.println("Client received (decrypted): " + input);
                
                ArrayList<SongDTO> arrSongDTO = supportBtnSong(input);
                //giao diện
                pnSongTop.removeAll();
                pnSongTop.repaint();
                songList = new SongListClient(arrSongDTO);
                pnSongTop.add(songList, BorderLayout.CENTER);
            }
        } catch (Exception ex) {
            Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btArtistActionPerformed

    public void showArtistError(){
        lbName.setText("error");
        lbBirthDay.setText("");
        lbGender.setText("");
        lbCountry.setText("");
        txInfoLife.setText("");
        txAlbumTop.setText("");
        pnSongTop.removeAll();
        try {
            lbIcon.setIcon(scaleImage(new ImageIcon(new URL("https://www.computerhope.com/jargon/e/error.png")), 136, 136));
        } catch (MalformedURLException ex) {
            Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showArtist(ArtistDTO artistDTO){
        try {
            lbIcon.setIcon(scaleImage(new ImageIcon(new URL(artistDTO.getImage())), 136, 136));
        } catch (MalformedURLException ex) {
            Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbName.setText(artistDTO.getNameArtist());
        lbBirthDay.setText(artistDTO.getBirthday());
        lbGender.setText(artistDTO.getGender());
        lbCountry.setText(artistDTO.getCountry());
        txInfoLife.setText(artistDTO.getInfolife());
        String tempAlbum = "";
        StringTokenizer st = new StringTokenizer(artistDTO.getAlbum(), ";");
        while (st.hasMoreTokens()) {
            tempAlbum += st.nextToken() + "\n";
        }
        txAlbumTop.setText(tempAlbum);
    }
    
    public void sendWhat(String messageSend, String type) {
        String encode = client.aes.encrypt(messageSend, client.aes.encodeToString(client.aes.getKey().getEncoded()));
        String encodeSend = "#AES#" + type + encode;
        System.out.println("\nClient IP "+client.myIP+" send: " + encodeSend);
        //gửi
        client.dpsend = new DatagramPacket(
                encodeSend.getBytes(),
                encodeSend.getBytes().length,
                client.dpsend.getAddress(),
                client.dpsend.getPort());

        try {
            client.socket.send(client.dpsend);
        } catch (IOException ex) {
            Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String receive() {
        DatagramPacket dpreceiveNew = new DatagramPacket(new byte[client.buffsize], client.buffsize);
        try {
            client.socket.receive(dpreceiveNew);
        } catch (IOException ex) {
            Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String strDTO = new String(dpreceiveNew.getData(), 0, dpreceiveNew.getLength());
        System.out.println("Client received: " + strDTO);
        return strDTO;
    }

    public boolean listenForMessage() {
        String msgFromGroupChat = "";
        try {
            client.socket.receive(client.dpreceive);
            msgFromGroupChat = new String(client.dpreceive.getData(),
                    0,
                    client.dpreceive.getLength());
            System.out.println("Client received public key from Server: " + msgFromGroupChat);

            if (msgFromGroupChat.contains("#publickey#")) {
                RSAUtil rsa = new RSAUtil(); //để sử dụng function encode và decode
                String tachkey = msgFromGroupChat.split("#publickey#")[1];

                //biến cái tachkey thành đối tượng Publickey() để set rsa public key
                byte[] publicBytes = Base64.getDecoder().decode(tachkey);
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey pubKey = keyFactory.generatePublic(keySpec);

                rsa.setPublickey(pubKey);
                String encodedKey = Base64.getEncoder().encodeToString(client.aes.getKey().getEncoded());
                String encryptkey = rsa.Encrypt("#secretkey#" + encodedKey);
                System.out.println("Secrect Key (AES) (not encrypted): " + encodedKey);
                System.out.println("Secrect Key (AES) (encrypted): " + encryptkey);
                System.out.println("Client send Secrect Key to Server...");
                client.dpsend = new DatagramPacket(
                        encryptkey.getBytes(),
                        encryptkey.getBytes().length,
                        client.dpsend.getAddress(),
                        client.dpsend.getPort());

                //gửi aes key đã mã hóa bằng RSA 
                client.socket.send(client.dpsend);

                return true;
            }
        } catch (NoSuchAlgorithmException | IOException
                | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | InvalidKeySpecException | BadPaddingException ex) {
            Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<SongDTO> supportBtnSong(String strDTO) {
        StringTokenizer stDoiTuong = new StringTokenizer(strDTO, "$");
        ArrayList<String> arrDoiTuongs = new ArrayList<>();
        ArrayList<SongDTO> arrSongDTO = new ArrayList<>();
        for (int i = 0; stDoiTuong.hasMoreTokens(); i++) {
            String temp = stDoiTuong.nextToken();
            if (!temp.equalsIgnoreCase("")) {
                arrDoiTuongs.add(temp);
            }
        }
        int count = 0;
        int countThuoc = 0;
        for (String doiTuong : arrDoiTuongs) {
            StringTokenizer stThuocTinh = new StringTokenizer(doiTuong, ";");
            SongDTO songDTO = new SongDTO();
            while (stThuocTinh.hasMoreTokens()) {
                String thuocTinh = stThuocTinh.nextToken();
                StringTokenizer stData = new StringTokenizer(thuocTinh, "#");
                String tenThuocTinh = stData.nextToken();
                String data = "";
                if (stData.hasMoreTokens()) {
                    data = stData.nextToken();
                }
                if (tenThuocTinh.equalsIgnoreCase("songName")) {
                    songDTO.setTitle(data);
                } else if (tenThuocTinh.equalsIgnoreCase("artistName")) {
                    songDTO.setArtist(data);
                } else if (tenThuocTinh.equalsIgnoreCase("image")) {
                    songDTO.setIconName(data);
                } else if (tenThuocTinh.equalsIgnoreCase("lyric")) {
                    songDTO.setLyrics(data);
                } else if (tenThuocTinh.equalsIgnoreCase("stream")) {
                    songDTO.setStream(data);
                }
            }
            arrSongDTO.add(songDTO);
        }
        return arrSongDTO;
    }

    public ArrayList<ArtistDTO> supportBtnArtist(String strDTO) {
        ArrayList<String> arrDoiTuongs = new ArrayList<>();
        ArrayList<ArtistDTO> arrArtistDTO = new ArrayList<>();
        arrDoiTuongs.add(strDTO);

        int count = 0;
        int countThuoc = 0;
        for (String doiTuong : arrDoiTuongs) {
            StringTokenizer stThuocTinh = new StringTokenizer(doiTuong, "!");
            ArtistDTO artistDTO = new ArtistDTO();
            while (stThuocTinh.hasMoreTokens()) {
                String thuocTinh = stThuocTinh.nextToken();
                StringTokenizer stData = new StringTokenizer(thuocTinh, "#");
                String tenThuocTinh = stData.nextToken();
                String data = "";
                if (stData.hasMoreTokens()) {
                    data = stData.nextToken();
                }

                if (tenThuocTinh.equalsIgnoreCase("image")) {
                    artistDTO.setImage(data);
                } else if (tenThuocTinh.equalsIgnoreCase("nameArtist")) {
                    artistDTO.setNameArtist(data);
                } else if (tenThuocTinh.equalsIgnoreCase("birthday")) {
                    artistDTO.setBirthday(data);
                } else if (tenThuocTinh.equalsIgnoreCase("gender")) {
                    artistDTO.setGender(data);
                } else if (tenThuocTinh.equalsIgnoreCase("country")) {
                    artistDTO.setCountry(data);
                } else if (tenThuocTinh.equalsIgnoreCase("infolife")) {
                    artistDTO.setInfolife(data);
                } else if (tenThuocTinh.equalsIgnoreCase("songTop")) {
                    artistDTO.setSongTop(data);
                } else if (tenThuocTinh.equalsIgnoreCase("album")) {
                    artistDTO.setAlbum(data);
                }
            }

            arrArtistDTO.add(artistDTO);

        }

        return arrArtistDTO;

    }

    public void sendEmpty() {
        try {
            System.out.println("Client send: e");
            DatagramPacket dpe = new DatagramPacket("e".getBytes(), "e".getBytes().length, client.dpsend.getAddress(), client.dpsend.getPort());
            client.socket.send(dpe);
        } catch (IOException e) {
            client.closeEverything(client.socket);
        }
    }

    private ImageIcon scaleImage(ImageIcon icon, int w, int h) {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if (icon.getIconWidth() > w) {
            nw = w;
            nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if (nh > h) {
            nh = h;
            nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_SMOOTH));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btArtist;
    private javax.swing.JButton btSong;
    private javax.swing.JTextField inputArtist;
    private javax.swing.JTextField inputSong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbBirthDay;
    private javax.swing.JLabel lbCountry;
    private javax.swing.JLabel lbGender;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    private javax.swing.JPanel pnImage;
    private javax.swing.JPanel pnList;
    private javax.swing.JPanel pnSongTop;
    private javax.swing.JTextArea txAlbumTop;
    private javax.swing.JTextArea txInfoLife;
    public static javax.swing.JTextArea txLyric;
    // End of variables declaration//GEN-END:variables
}
