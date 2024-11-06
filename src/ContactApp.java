import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class ContactApp extends JFrame {
    private JTextArea contactArea;
    private ArrayList<String> contacts;

    public ContactApp() {
        contacts = new ArrayList<>();
        loadContacts();

        // Crear los componentes de la interfaz
        contactArea = new JTextArea();
        contactArea.setEditable(false);
        contactArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Fuente más legible
        JScrollPane scrollPane = new JScrollPane(contactArea);

        // Crear panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Los botones alineados a la izquierda con margen
        buttonPanel.setBackground(Color.LIGHT_GRAY); // Fondo gris claro para los botones

        // Botones con tamaños más pequeños
        JButton addButton = new JButton("Agregar");
        JButton removeButton = new JButton("Eliminar");
        JButton searchButton = new JButton("Buscar");
        JButton sortButton = new JButton("Ordenar");

        addButton.setPreferredSize(new Dimension(100, 30)); // Botones más pequeños
        removeButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setPreferredSize(new Dimension(100, 30));
        sortButton.setPreferredSize(new Dimension(100, 30));

        // Agregar botones al panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortButton);

        // Organizar la interfaz con BorderLayout
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.NORTH); // Los botones en la parte superior
        this.add(scrollPane, BorderLayout.CENTER);  // El área de contactos ocupa el centro

        // Funciones de los botones
        addButton.addActionListener(e -> addContact());
        removeButton.addActionListener(e -> removeContact());
        searchButton.addActionListener(e -> searchContact());
        sortButton.addActionListener(e -> sortContacts());

        // Configuración de la ventana
        this.setTitle("Gestor de Contactos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null); // Centrar la ventana
        this.setVisible(true);

        // Cargar los contactos al inicio
        displayContacts();
    }

    // Método para agregar contacto
    private void addContact() {
        String name = JOptionPane.showInputDialog("Nombre del contacto:");
        String phone = JOptionPane.showInputDialog("Número de celular:");
        String email = JOptionPane.showInputDialog("Correo electrónico:");
        String address = JOptionPane.showInputDialog("Dirección:");

        String contact = name + ", " + phone + ", " + email + ", " + address;
        contacts.add(contact);
        saveContacts();
        displayContacts();
    }

    // Método para eliminar contacto
    private void removeContact() {
        String name = JOptionPane.showInputDialog("Ingrese el nombre del contacto a eliminar:");
        contacts.removeIf(contact -> contact.startsWith(name));
        saveContacts();
        displayContacts();
    }

    // Método para buscar contacto
    private void searchContact() {
        String query = JOptionPane.showInputDialog("Ingrese caracteres para buscar:");
        for (String contact : contacts) {
            if (contact.toLowerCase().contains(query.toLowerCase())) {
                JOptionPane.showMessageDialog(this, contact);
            }
        }
    }

    // Método para ordenar los contactos
    private void sortContacts() {
        Collections.sort(contacts);
        saveContacts();
        displayContacts();
    }

    // Cargar contactos desde archivo
    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contacts.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar contactos en archivo
    private void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt"))) {
            for (String contact : contacts) {
                writer.write(contact);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Mostrar los contactos en el área de texto
    private void displayContacts() {
        contactArea.setText("");
        for (String contact : contacts) {
            contactArea.append(contact + "\n");
        }
    }
}
