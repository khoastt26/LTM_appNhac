/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author PC
 */
//import BUS.SongBUS;
import DTO.*;
import GUI.SongItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class SongListClient extends JScrollPane {

    private JPanel container;
    private ArrayList<SongDTO> inputSong;

    public SongListClient(ArrayList<SongDTO> input) {
        inputSong = input;
        // set display
        container = createMainPanel();
        this.setViewportView(container);
    }
   

    public ArrayList<SongDTO> getInput() {
        return inputSong;
    }

    public void setInput(ArrayList<SongDTO> input) {
        inputSong = input;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.white);
        createListBooks(panel);

        return panel;
    }

    private void createListBooks(JPanel panel) {
        inputSong.stream().map(song -> new SongItem(song)).forEachOrdered(songItem -> {
            songItem.setBackground(Color.white);
            songItem.setBorder(new EmptyBorder(10, 4, 10, 4));
            panel.add(songItem);
            songItem.setPreferredSize(new Dimension(0, 80));
        });
    }

}
