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

public class CaesarFrame_4esig extends JFrame {
    private JComboBox<Object> charsCBox;
    private JTextField inputFld;
    private JTextField outputFld;
    private JLabel outTxtLbl;
    private JButton codeBtn;

    public CaesarFrame_4esig() {
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
        codeBtn =  new JButton("Code!");
        codeBtn.addActionListener(new OkButtonActionListener());
        JPanel felsoPanel = new JPanel();
        felsoPanel.add(charsCBox);
        felsoPanel.add(inputFld);
        felsoPanel.add(codeBtn);

        outTxtLbl = new JLabel("Output: ");
        outputFld = new JTextField(20);
        outputFld.setEditable(false);
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
            String input = inputFld.getText();
            char offset = (char)charsCBox.getSelectedItem();

            String txt = caesarCode(input, offset);

            outputFld.setText(txt);
        }
        
    }

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

    public static void main(String[] args) {
        CaesarFrame_4esig frame = new CaesarFrame_4esig();
        frame.setVisible(true);
    }
}
