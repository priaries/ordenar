import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crea la ventana de la aplicación
            JFrame frame = new ContactApp();
            frame.setTitle("Gestor de Contactos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setVisible(true);
        });
    }
}
