package swingmvclab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableCellRenderer;

/*
 * A megjelenítendő ablakunk osztálya.
 */
public class StudentFrame extends JFrame {
    
    /*
     * Ebben az objektumban vannak a hallgatói adatok.
     * A program indulás után betölti az adatokat fájlból, bezáráskor pedig kimenti oda.
     * 
     * NE MÓDOSÍTSD!
     */
    private StudentData data;
    
    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különböző komponenseket
     * (táblázat, beviteli mező, gomb).
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        JTable tablazat = new JTable();
        tablazat.setModel(data);
        tablazat.setFillsViewportHeight(true);
        tablazat.setRowSorter(new TableRowSorter<StudentData>(data));

        tablazat.setDefaultRenderer(String.class, new TableRender(tablazat.getDefaultRenderer(String.class)));
        tablazat.setDefaultRenderer(Boolean.class, new TableRender(tablazat.getDefaultRenderer(Boolean.class)));
        tablazat.setDefaultRenderer(Integer.class, new TableRender(tablazat.getDefaultRenderer(Integer.class)));

        add(new JScrollPane(tablazat),BorderLayout.CENTER);

        JTextField ujNevFld = new JTextField(20);
        JTextField ujKodFld = new JTextField(6);
        JButton ujBtn = new JButton("Felvesz");
        JPanel ujHallgatoPnl = new JPanel();
        ujHallgatoPnl.add(new JLabel("Név:"));
        ujHallgatoPnl.add(ujNevFld);
        ujHallgatoPnl.add(new JLabel("Neptun:"));
        ujHallgatoPnl.add(ujKodFld);
        ujHallgatoPnl.add(ujBtn);

        add(ujHallgatoPnl, BorderLayout.SOUTH);

        ujBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addStudent(ujNevFld.getText(), ujKodFld.getText());
                tablazat.updateUI();
            }
        });

        tablazat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                data.fireTableDataChanged();
                tablazat.updateUI();
            }
        });
    }

    /*
     * Az ablak konstruktora.
     * 
     * NE MÓDOSÍTSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgatói nyilvántartás");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Induláskor betöltjük az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bezáráskor mentjük az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Felépítjük az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }

    /*
     * A program belépési pontja.
     * 
     * NE MÓDOSÍTSD!
     */
    public static void main(String[] args) {
        // Megjelenítjük az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }

    private class TableRender implements TableCellRenderer {
        private final TableCellRenderer renderer;

        public TableRender(TableCellRenderer renderer) {
            this.renderer = renderer;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = renderer.getTableCellRendererComponent(table, value, false, false, row, column);
            Student act = data.students.get(table.getRowSorter().convertRowIndexToModel(row));
            Color bg;
            if (act.getGrade() <= 1 || !act.hasSignature()) {
                bg = new Color(255, 0, 34);
            } else {
                bg = new Color(3, 252, 186);
            }

            comp.setBackground(bg);

            return comp;
        }
    
        
    }
}
