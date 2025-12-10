package gui.tema;

import modelo.AppTheme;
import java.awt.*;
import javax.swing.*;

public class TemaManager {

    public static void aplicarTema(Component comp) {
        aplicarTemaRecursivo(comp);
        comp.revalidate();
        comp.repaint();
    }

    private static void aplicarTemaRecursivo(Component comp) {

        // SIEMPRE cambiar fuente
        comp.setFont(AppTheme.getFuente());

        // ===== BOTONES =====
        if (comp instanceof JButton boton) {
            // NO cambiar el color del texto
            // (Swing usa su propio color por defecto)
            // boton.setForeground(NO TOCAR)

            // Mantener estilo original
            boton.setContentAreaFilled(true);
            boton.setOpaque(false);
            boton.setBorderPainted(true);
            boton.setFocusPainted(false);

            return; // 🚀 Importante: no aplicar más cambios al botón
        }

        // ===== OTROS COMPONENTES (labels, panels, etc.) =====
        comp.setForeground(AppTheme.colorTexto);

        // Paneles sí usan el color de fondo del tema
        if (comp instanceof JPanel) {
            comp.setBackground(AppTheme.colorFondo);
        }

        // Recursión sobre hijos
        if (comp instanceof Container cont) {
            for (Component hijo : cont.getComponents()) {
                aplicarTemaRecursivo(hijo);
            }
        }
    }
}