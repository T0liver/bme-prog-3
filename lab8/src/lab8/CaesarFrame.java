package lab8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CaesarFrame extends JFrame {
    private JComboBox<Object> charsCBox;
    private JTextField inputFld;
    private JTextField outputFld;
    private JLabel outTxtLbl;
    private JButton codeBtn;

    public CaesarFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SwingLab");
        setSize(400, 110);
        setMinimumSize(new Dimension(350, 110));
        setResizable(true);
        setLayout(new BorderLayout());

        Object[] abc = new Object[26];
        // I mean this is faster because the cpu doesn't have to look always for a end of a for cycle and the schedulder can work flawless
        // Object[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        // but suuuure we are not going after effiency this language is not about that...
        for (int i = 0; i < 26; i++) {
            abc[i] = (char) ('A' + i);
        }
        charsCBox = new JComboBox<>(abc);
        inputFld = new JTextField(20);
        codeBtn =  new JButton("Decode!");
        codeBtn.addActionListener(new OkButtonActionListener());
        inputFld.getDocument().addDocumentListener(new InputFieldDocumentListener());
        JPanel felsoPanel = new JPanel();
        felsoPanel.add(charsCBox);
        felsoPanel.add(inputFld);
        felsoPanel.add(codeBtn);
        charsCBox.addActionListener(new OkButtonActionListener());

        outTxtLbl = new JLabel("Output: ");
        outputFld = new JTextField(20);
        //outputFld.getDocument().addDocumentListener(new OutputFieldDocumentListener());
        JPanel alsoPanel = new JPanel();
        alsoPanel.add(outTxtLbl);
        alsoPanel.add(outputFld);

        JPanel thingsPanel = new JPanel(new BorderLayout());
        thingsPanel.add(felsoPanel, BorderLayout.NORTH);
        thingsPanel.add(alsoPanel, BorderLayout.SOUTH);
        add(thingsPanel, BorderLayout.NORTH);
    }

    private class OkButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doDeCaesar();
        }
        
    }

    private class InputFieldDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            doCaesar();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            doCaesar();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            doCaesar();
        }
    }

    /*
    * this is what we want
    private class OutputFieldDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            SwingUtilities.invokeLater(() -> doDeCaesar());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            SwingUtilities.invokeLater(() -> doDeCaesar());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            SwingUtilities.invokeLater(() -> doDeCaesar());
        }
    }
    */


    private static String caesarCode(String input, char offset) {
        String in = input.toUpperCase();
        int shift = (offset - 'A') % 26;
        String ret = "";

        for (char c : in.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char sh = (char)(((c - 'A' + shift) % 26) + 'A');
                ret = ret + sh;
            } else {
                ret = ret + c;
            }
        }

        return ret;
    }

    private static String decodeCaesarCode(String input, char offset) {
        String in = input.toUpperCase();
        int shift = (offset - 'A') % 26;
        String ret = "";

        for (char c : in.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char sh = (char)(((c - 'A' - shift +26) % 26) + 'A');
                ret = ret + sh;
            } else {
                ret = ret + c;
            }
        }

        return ret;
    }

    private void doCaesar() {
        String input = inputFld.getText();
        char offset = (char)charsCBox.getSelectedItem();

        String txt = caesarCode(input, offset);

        outputFld.setText(txt);
    }

    private void doDeCaesar() {
        String input = outputFld.getText();
        char offset = (char)charsCBox.getSelectedItem();

        String txt = decodeCaesarCode(input, offset);

        inputFld.setText(txt);
    }

    public static void main(String[] args) {
        CaesarFrame frame = new CaesarFrame();
        frame.setVisible(true);
    }
}
