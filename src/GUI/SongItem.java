/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author PC
 */
import jaco.mp3.player.MP3Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import GUI.mainGUI;
import javax.swing.JTextArea;
import app.API.SongDAO;

import DTO.*;
import static app.API.SongDAO.getLyricBylrc;
import java.awt.Cursor;

public class SongItem extends JPanel {

    private JLabel lbIcon = new JLabel();
    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();
    private JButton btPlay = new JButton();
    private JButton btStop = new JButton();
    private JPanel panelText;
    private JPanel panelIcon;
    private JPanel panelButton;
    private MP3Player mp3player;

    public SongItem(SongDTO songDTO) {

        setLayout(new BorderLayout(5, 5));
        setBackground(new java.awt.Color(240, 140, 70));

        panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(lbName);
        panelText.add(lbAuthor);
        panelText.setBackground(Color.white);

        panelIcon = new JPanel();
        panelIcon.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelIcon.add(lbIcon);
        panelIcon.setBackground(Color.white);

        panelButton = new JPanel(new GridLayout(0, 1));
        panelButton.setBackground(Color.white);
        
        btPlay.setText("Play");
        btPlay.setBackground(new java.awt.Color(18, 184, 109));
        btPlay.setForeground(Color.white);
        btPlay.setBorder(new EmptyBorder(7, 24, 7, 24));
        btPlay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btStop.setText("Stop");
        btStop.setBackground(new java.awt.Color(232, 70, 70));
        btStop.setForeground(Color.white);
        btStop.setBorder(new EmptyBorder(7, 24, 7, 24));
        btStop.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        panelButton.add(btPlay);
        panelButton.add(btStop);
        

        add(panelIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
        add(panelButton, BorderLayout.EAST);

        if (songDTO.getArtist().equalsIgnoreCase("")) {
            lbName.setText("error");
        } else {
            lbName.setText(songDTO.getTitle());
            lbName.setBackground(Color.white);
            
            lbAuthor.setText(songDTO.getArtist());
            lbAuthor.setForeground(Color.blue);
            lbAuthor.setBackground(Color.white);

            try {
                lbIcon.setIcon(scaleImage(new ImageIcon(new URL(songDTO.getIconName())), 90, 90));
                URL url = new URL("https:" + songDTO.getStream());
                mp3player = new MP3Player(url);

            } catch (MalformedURLException ex) {
                Logger.getLogger(SongItem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            btPlay.addActionListener((e) -> {
                mp3player.play();
                mainGUI.txLyric.setText(getLyricBylrc(songDTO.getLyrics()));

            });
            btStop.addActionListener((e) -> {
                mp3player.stop();
                mainGUI.txLyric.setText("");
            });
            // set Opaque to change background color of JLabel
            lbName.setOpaque(true);
            lbAuthor.setOpaque(true);
            lbIcon.setOpaque(true);
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

}
