/* ILIMITADO POR EL MOMENTO, DESCONEXION CON BAKEND 

package booktracker.ui;

import modelo.Libro;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BookTrackerMain extends JFrame {
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;
    private JLabel perfilLabel;
    private JLabel nombreUsuarioLabel;
    private ArrayList<Libro> listaLibros;
    
    public BookTrackerMain() {
        this.listaLibros = new ArrayList<>();
        
        //Configuracion de la ventana principal
        setTitle("Book Tracker - Mis Libros Leidos");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        //Crear los paneles
        JPanel headerPanel = createHeaderPanel();
        JPanel centerPanel = createCenterPanel();
        JPanel bottomPanel = createBottomPanel();
        
        // Agregar paneles al panel principal
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Cargar datos de ejemplo
        cargarDatosEjemplo();
    }
    
     // ==================== PANEL SUPERIOR (HEADER) ====================
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(15, 0));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        // Panel izquierdo - Perfil del usuario
        JPanel perfilPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        perfilPanel.setBackground(Color.WHITE);
        
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        
        nombreUsuarioLabel = new JLabel("Mi Biblioteca");
        nombreUsuarioLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nombreUsuarioLabel.setForeground(new Color(51, 51, 51));
        
        JLabel estadoLabel = new JLabel("Historial de lectura");
        estadoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        estadoLabel.setForeground(new Color(102, 102, 102));
        
        infoPanel.add(nombreUsuarioLabel);
        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(estadoLabel);
        
        perfilPanel.add(perfilLabel);
        perfilPanel.add(infoPanel);
        
        // Panel derecho - Titulo de la aplicación
        JLabel tituloApp = new JLabel("Book Tracker");
        tituloApp.setFont(new Font("Arial", Font.BOLD, 28));
        tituloApp.setForeground(new Color(66, 133, 244));
        
        // Panel para centrar el emoji
        JPanel tituloPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        tituloPanel.setBackground(Color.WHITE);
        
        JLabel iconoLibro = new JLabel("");
        iconoLibro.setFont(new Font("Arial", Font.PLAIN, 32));
        
        tituloPanel.add(iconoLibro);
        tituloPanel.add(tituloApp);
        
        headerPanel.add(perfilPanel, BorderLayout.WEST);
        headerPanel.add(tituloPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    // ==================== PANEL CENTRAL (TABLA/HISTORIAL) ====================
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(0, 15));
        centerPanel.setBackground(new Color(245, 245, 245));
        
        // Panel superior con titulo y contador
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 245));
        
        // Titulo
        JLabel tituloLabel = new JLabel("Libros Leidos");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 22));
        tituloLabel.setForeground(new Color(0, 0, 0));
        
        //Contador
        JLabel contadorLabel = new JLabel("Total: 0 libros");
        contadorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contadorLabel.setForeground(new Color(102, 102, 102));
        
        topPanel.add(tituloLabel, BorderLayout.WEST);
        topPanel.add(contadorLabel, BorderLayout.EAST);
        
        // Creador de la tabla
        String[] columnas = {"ID", "Titulo del Libro", "Autor", "Genero", "Fecha de Lectura", "Calificacion"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };
        
        //Especificaciones de la Tabla
        tablaLibros = new JTable(modeloTabla);
        tablaLibros.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaLibros.setRowHeight(35);
        tablaLibros.setSelectionBackground(new Color(66, 133, 244, 60));
        tablaLibros.setSelectionForeground(Color.BLACK);
        tablaLibros.setShowGrid(true);
        tablaLibros.setGridColor(new Color(230, 230, 230));
        
        // Estilo del encabezado
        JTableHeader header = tablaLibros.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(66, 133, 244));
        header.setForeground(Color.BLACK);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Ajustar ancho de columnas
        tablaLibros.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablaLibros.getColumnModel().getColumn(1).setPreferredWidth(280);
        tablaLibros.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablaLibros.getColumnModel().getColumn(3).setPreferredWidth(130);
        tablaLibros.getColumnModel().getColumn(4).setPreferredWidth(140);
        tablaLibros.getColumnModel().getColumn(5).setPreferredWidth(120);
        
        // Centrar el contenido de algunas columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaLibros.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablaLibros.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tablaLibros.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        // Listener para actualizar el contador
        modeloTabla.addTableModelListener(e -> {
            contadorLabel.setText("Total: " + modeloTabla.getRowCount() + " libros");
        });
        
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        return centerPanel;
    }
    
    // ==================== PANEL INFERIOR (BOTONES) ====================
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 15));
        bottomPanel.setBackground(new Color(245, 245, 245));
        
        // Boton Agregar
        JButton agregarBtn = createStyledButton("+ Agregar Libro", new Color(76, 175, 80), "Agregar un nuevo libro");
        agregarBtn.addActionListener(e -> agregarLibro());
        
        // Boton Editar
        JButton editarBtn = createStyledButton("Editar", new Color(255, 152, 0), "Editar libro seleccionado");
        editarBtn.addActionListener(e -> editarLibro());
        
        // Boton Eliminar
        JButton eliminarBtn = createStyledButton("Eliminar Libro", new Color(244, 67, 54), "Eliminar libro seleccionado");
        eliminarBtn.addActionListener(e -> eliminarLibro());
        
        // Boton Ver Detalles
        JButton detallesBtn = createStyledButton("Ver Detalles", new Color(33, 150, 243), "Ver informacion completa");
        detallesBtn.addActionListener(e -> verDetalles());
        
        // Boton Estadisticas
        JButton estadisticasBtn = createStyledButton("Estadisticas", new Color(33, 150, 243), "Ver estadisticas de lectura");
        estadisticasBtn.addActionListener(e -> mostrarEstadisticas());
        
        bottomPanel.add(agregarBtn);
        bottomPanel.add(eliminarBtn);
        bottomPanel.add(editarBtn);
        bottomPanel.add(detallesBtn);
        bottomPanel.add(estadisticasBtn);
        
        return bottomPanel;
    }
    
    // ==================== CREA EL ESTILO DE LOS BOTONES ====================
    private JButton createStyledButton(String text, Color color, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 45));
        button.setToolTipText(tooltip);
        
        // Efecto hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    // ==================== CREA ICONO DE PERFIL ====================
    private ImageIcon createProfileIcon() {
        int size = 70;
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
            size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        // Activar anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Circulo de fondo
        g2d.setColor(new Color(66, 133, 244));
        g2d.fillOval(0, 0, size, size);
        
        // Icono de libro
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        
        g2d.dispose();
        return new ImageIcon(img);
    }
    
    // ==================== FUNCIONES DE BOTONES ====================

    // Agregar nuevo libro
    private void agregarLibro() {
        JDialog dialog = new JDialog(this, "Agregar Nuevo Libro", true);
        dialog.setSize(450, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        mainPanel.setBackground(Color.WHITE);
        
        // Título del diologo
        JLabel tituloDialog = new JLabel("Nuevo Libro");
        tituloDialog.setFont(new Font("Arial", Font.BOLD, 20));
        tituloDialog.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(tituloDialog);
        mainPanel.add(Box.createVerticalStrut(25));
        
        // Campos del formulario
        JTextField tituloField = createFormField(mainPanel, "Titulo del libro:");
        JTextField autorField = createFormField(mainPanel, "Autor:");
        JTextField generoField = createFormField(mainPanel, "Genero:");
        JTextField fechaField = createFormField(mainPanel, "Fecha de lectura (DD/MM/YYYY):");
        
        // ComboBox para calificacion
        mainPanel.add(createLabel("Calificacion:"));
        String[] calificaciones = {"1", "2", "3", "4", "5"};
        JComboBox<String> calificacionCombo = new JComboBox<>(calificaciones);
        calificacionCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        calificacionCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(calificacionCombo);
        mainPanel.add(Box.createVerticalStrut(25));
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton guardarBtn = new JButton("Guardar");
        guardarBtn.setBackground(new Color(76, 175, 80));
        guardarBtn.setForeground(Color.WHITE);
        guardarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        guardarBtn.setPreferredSize(new Dimension(120, 40));
        
        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.setBackground(new Color(158, 158, 158));
        cancelarBtn.setForeground(Color.WHITE);
        cancelarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cancelarBtn.setPreferredSize(new Dimension(120, 40));
        
        guardarBtn.addActionListener(e -> {
            String titulo = tituloField.getText().trim();
            String autor = autorField.getText().trim();
            String genero = generoField.getText().trim();
            String fecha = fechaField.getText().trim();
            String calificacion = String.valueOf(calificacionCombo.getSelectedIndex() + 1);
            
            if (titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, 
                    "El titulo y el autor son obligatorios", 
                    "Campos requeridos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = listaLibros.size() + 1;
            Libro libro = new Libro(id, titulo, autor, genero, fecha, calificacion);
            listaLibros.add(libro);
            
            modeloTabla.addRow(new Object[]{
                id, libro.titulo, libro.autor, libro.genero, libro.fecha, calificacion
            });
            
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Libro agregado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
        });
        
        cancelarBtn.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(guardarBtn);
        buttonPanel.add(cancelarBtn);
        mainPanel.add(buttonPanel);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    // Editar libro existente
    private void editarLibro() {
        int selectedRow = tablaLibros.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona un libro de la tabla para editar", 
                "No hay seleccion", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Libro libro = listaLibros.get(selectedRow);
        
        JDialog dialog = new JDialog(this, "Editar Libro", true);
        dialog.setSize(450, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        mainPanel.setBackground(Color.WHITE);
        
        JLabel tituloDialog = new JLabel("Editar Libro");
        tituloDialog.setFont(new Font("Arial", Font.BOLD, 20));
        tituloDialog.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(tituloDialog);
        mainPanel.add(Box.createVerticalStrut(25));
        
        JTextField tituloField = createFormField(mainPanel, "Titulo del libro:");
        tituloField.setText(libro.titulo);
        
        JTextField autorField = createFormField(mainPanel, "Autor:");
        autorField.setText(libro.autor);
        
        JTextField generoField = createFormField(mainPanel, "Genero:");
        generoField.setText(libro.genero);
        
        JTextField fechaField = createFormField(mainPanel, "Fecha de lectura:");
        fechaField.setText(libro.fecha);
        
        mainPanel.add(createLabel("Calificacion:"));
        String[] calificaciones = {"1", "2", "3", "4", "5"};
        JComboBox<String> calificacionCombo = new JComboBox<>(calificaciones);
        calificacionCombo.setSelectedIndex(Integer.parseInt(libro.calificacion) - 1);
        calificacionCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        mainPanel.add(calificacionCombo);
        mainPanel.add(Box.createVerticalStrut(25));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton guardarBtn = new JButton("Guardar Cambios");
        guardarBtn.setBackground(new Color(255, 152, 0));
        guardarBtn.setForeground(Color.WHITE);
        guardarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        guardarBtn.setPreferredSize(new Dimension(150, 40));
        
        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.setBackground(new Color(158, 158, 158));
        cancelarBtn.setForeground(Color.WHITE);
        cancelarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cancelarBtn.setPreferredSize(new Dimension(120, 40));
        
        guardarBtn.addActionListener(e -> {
            libro.titulo = tituloField.getText();
            libro.autor = autorField.getText();
            libro.genero = generoField.getText();
            libro.fecha = fechaField.getText();
            libro.calificacion = String.valueOf(calificacionCombo.getSelectedIndex() + 1);
            
            String estrellas = "*".repeat(Integer.parseInt(libro.calificacion));
            modeloTabla.setValueAt(libro.titulo, selectedRow, 1);
            modeloTabla.setValueAt(libro.autor, selectedRow, 2);
            modeloTabla.setValueAt(libro.genero, selectedRow, 3);
            modeloTabla.setValueAt(libro.fecha, selectedRow, 4);
            modeloTabla.setValueAt(libro.calificacion, selectedRow, 5);
            
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Libro actualizado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        });
        
        cancelarBtn.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(guardarBtn);
        buttonPanel.add(cancelarBtn);
        mainPanel.add(buttonPanel);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    // Eliminar libro
    private void eliminarLibro() {
        int selectedRow = tablaLibros.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona un libro de la tabla para eliminar", 
                "No hay seleccion", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String tituloLibro = (String) modeloTabla.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Estas seguro de que deseas eliminar el libro:\n\"" + tituloLibro + "\"?", 
            "Confirmar eliminacion", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            listaLibros.remove(selectedRow);
            modeloTabla.removeRow(selectedRow);
            
            // Actualizar IDs
            for (int i = 0; i < listaLibros.size(); i++) {
                listaLibros.get(i).id = i + 1;
                modeloTabla.setValueAt(i + 1, i, 0);
            }
            
            JOptionPane.showMessageDialog(this, "Libro eliminado", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Ver detalles del libro
    private void verDetalles() {
        int selectedRow = tablaLibros.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona un libro para ver detalles", 
                "No hay seleccion", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Libro libro = listaLibros.get(selectedRow);
        
        String detalles = String.format(
            "DETALLES DEL LIBRO\n\n" +
            "ID: %d\n" +
            "Titulo: %s\n" +
            "Autor: %s\n" +
            "Genero: %s\n" +
            "Fecha de lectura: %s\n" +
            "Calificacion: %s/5",
            libro.id, libro.titulo, libro.autor, libro.genero, 
            libro.fecha, libro.calificacion
        );
        
        JOptionPane.showMessageDialog(this, detalles, "Informacion del Libro", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Mostrar estadisticas
    private void mostrarEstadisticas() {
        if (listaLibros.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay libros registrados para mostrar estadisticas", 
                "Sin datos", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Calcular estadisticas
        int totalLibros = listaLibros.size();
        double sumaCalificaciones = 0;
        int maxCalificacion = 0;
        String libroMejorCalificado = "";
        
        for (Libro libro : listaLibros) {
            int cal = Integer.parseInt(libro.calificacion);
            sumaCalificaciones += cal;
            if (cal > maxCalificacion) {
                maxCalificacion = cal;
                libroMejorCalificado = libro.titulo;
            }
        }
        
        double promedio = sumaCalificaciones / totalLibros;
        
        String mensaje = String.format(
            "ESTADISTICAS DE LECTURA\n\n" +
            "Total de libros leidos: %d\n" +
            "Calificacion promedio: %.1f/5\n" +
            "Mejor calificado: %s (%d*)\n" +
            "Genero mas leido: Ficcion",
            totalLibros, promedio, libroMejorCalificado, maxCalificacion
        );
        
        JOptionPane.showMessageDialog(this, mensaje, "Estadisticas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // ==================== FUNCIONES AUXILIARES ====================

    private JTextField createFormField(JPanel panel, String labelText) {
        panel.add(createLabel(labelText));
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(field);
        panel.add(Box.createVerticalStrut(15));
        return field;
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    // Cargar datos de ejemplo
    private void cargarDatosEjemplo() {
        listaLibros.add(new Libro(1, "Cien anos de soledad", "Gabriel Garcia Marquez", "Ficcion", "15/03/2024", "5"));
        listaLibros.add(new Libro(2, "El principito", "Antoine de Saint-Exupery", "Infantil", "20/04/2024", "4"));
        listaLibros.add(new Libro(3, "1984", "George Orwell", "Distopia", "10/05/2024", "5"));
        listaLibros.add(new Libro(4, "Don Quijote de la Mancha", "Miguel de Cervantes", "Clasico", "05/06/2024", "4"));
        
        for (Libro libro : listaLibros) {
            modeloTabla.addRow(new Object[]{
                libro.id, libro.titulo, libro.autor, libro.genero, libro.fecha, libro.calificacion
            });
        }
    }
}
*/