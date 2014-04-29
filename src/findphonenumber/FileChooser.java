/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findphonenumber;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author Tuan
 */
public class FileChooser extends JFrame {

    public FileChooser() {
        super("Phonenumber Extractor");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton openButton = new JButton("Open");
        JButton extractButton = new JButton("Extract");
        JButton dirButton = new JButton("Pick Dir");
        final JLabel statusbar = new JLabel("No File Is Selected!");
        
        c.add(openButton);
        c.add(extractButton);
        c.add(statusbar);
        // Create a file chooser that opens up as an Open dialog
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser chooser = new JFileChooser();
                chooser.setMultiSelectionEnabled(false);
                int option = chooser.showOpenDialog(FileChooser.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File sf = chooser.getSelectedFile();
//                    statusbar.setText("You chose " + sf.getName());
                    statusbar.setText(sf.getPath());
                } else {
                    statusbar.setText("You canceled.");
                }
            }
        });
        
        extractButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FindPhoneNumber ff = new FindPhoneNumber();
                try {
                ff.read(statusbar.getText());
                ff.write();
                } catch (Exception b) {
                    b.printStackTrace();
                }
            }
        });
        
    }
}
