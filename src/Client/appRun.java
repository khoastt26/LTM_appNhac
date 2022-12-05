package Client;

import GUI.mainGUI;

public class appRun {
    public static void main(String[] args) {
        mainGUI obj = new mainGUI();
        obj.setVisible(true);
        System.out.println("Connecting...");
        obj.Init();
    }
}
