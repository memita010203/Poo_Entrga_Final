package app;

import gui.login.LoginFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}