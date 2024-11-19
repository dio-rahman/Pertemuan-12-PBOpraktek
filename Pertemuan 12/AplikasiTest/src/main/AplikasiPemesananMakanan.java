
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class AplikasiPemesananMakanan extends JFrame {

    private final JTextField nameField;
    private final JTextField addressField;
    private final JTextField phoneField;
    private final JTextField emailField;
    private final JTextField totalField;
    private final JCheckBox steakCheckBox;
    private final JCheckBox spaghettiCheckBox;
    private final JCheckBox pizzaCheckBox;
    private final JCheckBox seblakCheckBox;
    private final JCheckBox basoCheckBox;
    private final JCheckBox rendangCheckBox;
    private final JTextArea orderTextArea;
    private int total = 0;

    public AplikasiPemesananMakanan() {
        setTitle("Aplikasi Pemesanan Makanan");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Membuat label untuk judul aplikasi
        JLabel titleLabel = new JLabel("Aplikasi Pemesanan Makanan", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Panel Data Customer
        JPanel customerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        customerPanel.setBorder(BorderFactory.createTitledBorder("Data Customer"));
        customerPanel.setPreferredSize(new Dimension(450, 200));

        // Atur font untuk label Nama
        JLabel nameLabel = new JLabel("Nama :");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        customerPanel.add(nameLabel);
        nameField = new JTextField();
        customerPanel.add(nameField);

        // Atur font untuk label Alamat
        JLabel addressLabel = new JLabel("Alamat :");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        customerPanel.add(addressLabel);
        addressField = new JTextField();
        customerPanel.add(addressField);

        // Atur font untuk label No Telp
        JLabel phoneLabel = new JLabel("No Telp :");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        customerPanel.add(phoneLabel);
        phoneField = new JTextField();
        customerPanel.add(phoneField);

        // Atur font untuk label Email
        JLabel emailLabel = new JLabel("Email :");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        customerPanel.add(emailLabel);
        emailField = new JTextField();
        customerPanel.add(emailField);


        // Panel Pilih Menu
        JPanel menuPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Pilih Menu"));
        menuPanel.setPreferredSize(new Dimension(330, 200));
        steakCheckBox = new JCheckBox("Steak (50000)");
        spaghettiCheckBox = new JCheckBox("Spaghetti (40000)");
        pizzaCheckBox = new JCheckBox("Pizza (80000)");
        seblakCheckBox = new JCheckBox("Seblak Ceker (10000)");
        basoCheckBox = new JCheckBox("Mi Baso Special (15000)");
        rendangCheckBox = new JCheckBox("Rendang Sambel Cabe Ijo (20000)");
        menuPanel.add(steakCheckBox);
        menuPanel.add(spaghettiCheckBox);
        menuPanel.add(pizzaCheckBox);
        menuPanel.add(seblakCheckBox);
        menuPanel.add(basoCheckBox);
        menuPanel.add(rendangCheckBox);

        // Panel Total dan Tambah
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(new JLabel("TOTAL BAYAR"));
        totalField = new JTextField(10);
        totalField.setEditable(false);
        totalField.setHorizontalAlignment(JTextField.RIGHT);
        totalPanel.add(totalField);
        JButton addButton = new JButton("TAMBAH");
        totalPanel.add(addButton);

        // Gabungkan menuPanel dan totalPanel dalam panel kanan
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(menuPanel, BorderLayout.CENTER);
        rightPanel.add(totalPanel, BorderLayout.SOUTH);

        // Area Pesanan
        orderTextArea = new JTextArea(20, 50);
        orderTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Data Penjualan"));

        // Listener untuk setiap menu untuk update total harga
        steakCheckBox.addActionListener(e -> updateTotal());
        spaghettiCheckBox.addActionListener(e -> updateTotal());
        pizzaCheckBox.addActionListener(e -> updateTotal());
        seblakCheckBox.addActionListener(e -> updateTotal());
        basoCheckBox.addActionListener(e -> updateTotal());
        rendangCheckBox.addActionListener(e -> updateTotal());

        // Listener untuk tombol Tambah
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrder();
            }
        });

        // Panel Utama
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(customerPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);  // Tambahkan rightPanel ke posisi EAST

        add(mainPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void updateTotal() {
        total = 0;
        if (steakCheckBox.isSelected()) {
            total += 50000;
        }
        if (spaghettiCheckBox.isSelected()) {
            total += 40000;
        }
        if (pizzaCheckBox.isSelected()) {
            total += 80000;
        }
        if (seblakCheckBox.isSelected()) {
            total += 10000;
        }
        if (basoCheckBox.isSelected()) {
            total += 15000;
        }
        if (rendangCheckBox.isSelected()) {
            total += 20000;
        }
        totalField.setText("Rp. " + total);
    }

    private void addOrder() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Nama: ").append(nameField.getText()).append("\n");
        orderDetails.append("Alamat: ").append(addressField.getText()).append("\n");
        orderDetails.append("Telp: ").append(phoneField.getText()).append("\n");
        orderDetails.append("Email: ").append(emailField.getText()).append("\n");
        orderDetails.append("--------------------------------------------------\n");
        orderDetails.append("Pesanan:\n");

        if (steakCheckBox.isSelected()) {
            orderDetails.append("- Steak (50000)\n");
        }
        if (spaghettiCheckBox.isSelected()) {
            orderDetails.append("- Spaghetti (40000)\n");
        }
        if (pizzaCheckBox.isSelected()) {
            orderDetails.append("- Pizza (80000)\n");
        }
        if (seblakCheckBox.isSelected()) {
            orderDetails.append("- Seblak Ceker (10000)\n");
        }
        if (basoCheckBox.isSelected()) {
            orderDetails.append("- Mi Baso Special (15000)\n");
        }
        if (rendangCheckBox.isSelected()) {
            orderDetails.append("- Rendang Sambel Cabe Ijo (20000)\n");
        }
        orderDetails.append("--------------------------------------------------\n");
        orderDetails.append("Total Bayar: Rp. ").append(total).append("\n");
        orderDetails.append("--------------------------------------------------\n");
        orderDetails.append("\n\n");
        orderTextArea.append(orderDetails.toString());
        clearFields();
    }

    private void clearFields() {
        nameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
        steakCheckBox.setSelected(false);
        spaghettiCheckBox.setSelected(false);
        pizzaCheckBox.setSelected(false);
        seblakCheckBox.setSelected(false);
        basoCheckBox.setSelected(false);
        rendangCheckBox.setSelected(false);
        totalField.setText("");
        total = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiPemesananMakanan().setVisible(true);
        });
    }
}
