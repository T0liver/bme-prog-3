package swingmvclab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * A hallgatók adatait tároló osztály.
 */
public class StudentData extends AbstractTableModel {

    /*
     * Ez a tagváltozó tárolja a hallgatói adatokat.
     * NE MÓDOSÍTSD!
     */
    List<Student> students = new ArrayList<Student>();

    /**
     * Innentől kezdődik a hiányzó metódusok felüldefiniálása
     */
    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Student std = students.get(row);

        switch (column) {
            case 0: return std.getName();
            case 1: return std.getNeptun();
            case 2: return std.hasSignature();
            default: return std.getGrade();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Név";
            case 1: return "Neptun";
            case 2: return "Aláírás";
            case 3: return "Jegy";
            default: return String.valueOf('A' + column);
        }
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            default:
            case 0:
            case 1: return String.class;
            case 2: return Boolean.class;
            case 3: return Integer.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column > 1 && column <= 4;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        Student act = students.get(row);
        if (isCellEditable(row, column)) {
            if (column == 2) {
                act.setSignature((Boolean) value);
            } else {
                act.setGrade((Integer) value);
            }
        }
    }

    public void addStudent(String name, String neptun) {
        students.add(new Student(name, neptun, false, 0));
        fireTableDataChanged();
    }
}