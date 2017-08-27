/**
 * Created by Sibel-PC on 1.8.2017.
 */

import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Frame.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JList;


public class Tagging_Interface extends JFrame {
    private TextArea textArea;
    private JFrame  mainFrame;
    private String upload;
    private int i = 2;
    int file_boolean = 0;
    int tag_boolean = 0;
    int t = 1;
    String tagged_File;
    String strLine;
    private JList <String> list;
    private Tagging_Interface(){
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500,700);
        mainFrame.setLocation(500,150);
        mainFrame.setLayout(null);
        mainFrame.setResizable(true);
        mainFrame.setForeground(Color.BLACK);

        JButton uploadTextFile = new JButton("text");
        uploadTextFile.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println("Do Something Clicked");
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    upload = chooser.getSelectedFile().getPath();
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getPath());
                            show_text(chooser.getSelectedFile().getPath(),1);
                }
            }
        });
        uploadTextFile.setSize(mainFrame.getWidth()/6,mainFrame.getWidth()/9);
        uploadTextFile.setLocation(mainFrame.getWidth()/4,mainFrame.getHeight()/4);

        JButton uploadTagText = new JButton("tag");
        uploadTagText.setSize(mainFrame.getWidth()/6,mainFrame.getWidth()/9);
        uploadTagText.setLocation((mainFrame.getWidth() - mainFrame.getWidth()/4 - mainFrame.getWidth()/6),mainFrame.getHeight()/4);

        uploadTagText.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getPath());
                    Set_tag_boxes(chooser.getSelectedFile().getPath());
                }
            }
        });

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setSize(mainFrame.getWidth()/3,mainFrame.getHeight()/5);
        textArea.setLocation(mainFrame.getWidth()/6,mainFrame.getHeight()/2- mainFrame.getHeight()/9);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        Checkbox checkbox = new Checkbox(" sibel  ");
        checkbox.setSize(mainFrame.getWidth()/3,mainFrame.getHeight()/5);
        checkbox.setLocation((mainFrame.getWidth() - mainFrame.getWidth()/8 - mainFrame.getWidth()/3),mainFrame.getHeight()/2- mainFrame.getHeight()/5);

        JButton beforeButton = new JButton("before");
        beforeButton.setSize(mainFrame.getWidth()/6,mainFrame.getWidth()/13);
        beforeButton.setLocation(mainFrame.getWidth()/7,mainFrame.getHeight() - mainFrame.getHeight()/4);

        JButton afterButton = new JButton("after");
        afterButton.setSize(mainFrame.getWidth()/6,mainFrame.getWidth()/13);
        afterButton.setLocation(mainFrame.getWidth() - mainFrame.getWidth()/6 - mainFrame.getWidth()/7,mainFrame.getHeight() - mainFrame.getHeight()/4);

        JButton save = new JButton("save");
        save.setSize(80,50);
        save.setLocation(mainFrame.getWidth()/2 -40,mainFrame.getHeight()/6*4);
        save.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //firstly check file is upload or tags are upload?
                if (file_boolean ==0 || tag_boolean == 0 ){
                    JOptionPane.showMessageDialog(null,"Firstly Upload a file");
                }
                if(file_boolean == 1 && tag_boolean == 1) {
                    File fw = null;
                    BufferedWriter bw;

                    try {
                        if(t == 1) {
                            tagged_File = JOptionPane.showInputDialog(null, "Give a Name to New File");
                            tagged_File = tagged_File.concat(".txt");
                            fw = new File(tagged_File);
                            t++;
                        }
                        //FileOutputStream fos = new FileOutputStream(fw,true);
                        bw = new BufferedWriter(new FileWriter(tagged_File,true));
                        String newS = new String();
                        newS = strLine.concat(" ");
                        bw.append(newS);
                        bw.append(list.getSelectedValuesList().toString());
                        bw.newLine();
                        bw.close();
                    } catch (IOException k) {

                        k.printStackTrace();

                    }

                    System.out.println("Save Button" + upload);
                    show_text(upload, i);
                    i++;
                }
            }
        });

        mainFrame.add(uploadTagText);
        mainFrame.add(uploadTextFile);
        mainFrame.add(textArea);
        mainFrame.add(checkbox);
     //   mainFrame.add(beforeButton);
     //   mainFrame.add(afterButton);
        mainFrame.add(save);
        mainFrame.setVisible(true);


    }
    private void show_text(String text,int i){
        BufferedReader br = null;
        int j = 1;
            try {
                br = new BufferedReader(new FileReader(text));
                while((strLine = br.readLine()) != null && j <= i){
                    System.out.println(strLine);
                    if(i == j) {
                        file_boolean = 1;
                        textArea.setText(strLine);
                        return;
                    }
                    j++;
                }

                if((strLine = br.readLine()) == null){
                    JOptionPane.showMessageDialog(null, "End of the File");
                    file_boolean = 0;
                }
            } catch (FileNotFoundException e) {
                System.err.println("Unable to find the file: fileName");
            } catch (IOException e) {
                System.err.println("Unable to read the file: fileName");
            }
    }

    DefaultListModel list_Model;

    public void Set_tag_boxes(String tag){
        BufferedReader br;
        String strLine;
        JPanel panel = new JPanel();
        list_Model = new DefaultListModel();
        JScrollPane pane;
        JLabel label = new JLabel("Tags");
        try {
            br = new BufferedReader( new FileReader(tag));
            while( (strLine = br.readLine()) != null){
                list_Model.addElement(strLine);
            }
            list = new JList(list_Model);
            list.setVisibleRowCount(8);
            list.setSelectionMode(2);
            list.setSize(120,200);
            list.revalidate();
            pane = new JScrollPane(list);
            pane.setSize(120,150);
            pane.setVisible(true);
            panel.add(label);
            panel.add(pane);
            panel.setSize(120,150);
            panel.setLocation(265,255);
            System.out.println(list.getSelectedValuesList());
            mainFrame.add(panel,FlowLayout.CENTER);
            mainFrame.revalidate();
            tag_boolean = 1;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }
    }

    public static void main(String[] args) {
        /*
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500,500);
        mainFrame.setLocation(500,150);
        mainFrame.setLayout(null);

        JMenuItem menuItem = new JMenuItem();

        JMenuBar menuBar = new JMenuBar();

        menuBar.setBounds(100,100,50,20);
        JButton upLoadFile = new JButton("UpLoadFile");
        upLoadFile.setBackground(Color.orange);
        upLoadFile.setForeground(Color.black);
        upLoadFile.setSize(50,50);
        upLoadFile.setBounds(70,70,100,70);

        TextArea textArea = new TextArea();
        textArea.setSize(100,70);
        textArea.setBounds(70,150,100,70);

        JButton upLoadTags = new JButton("Tags");
        upLoadTags.setBackground(Color.CYAN);
        upLoadTags.setSize(50,50);
        upLoadTags.setBounds(300,70,100,70);

        JCheckBoxMenuItem tags = new JCheckBoxMenuItem("sibel");
        tags.setSize(50,50);
        tags.setBounds(300,150,60,20);
        tags.setHorizontalTextPosition(JMenuItem.RIGHT);

        JCheckBoxMenuItem tags2 = new JCheckBoxMenuItem("sibel2");
        tags2.setSize(10,10);
        tags2.setBounds(300,200,60,20);
        tags2.setHorizontalTextPosition(JMenuItem.RIGHT);
        menuItem.add(tags);
        menuItem.add(tags2);
        menuBar.add(menuItem);
        upLoadFile.setVisible(true);
        mainFrame.getContentPane().add(upLoadFile,BorderLayout.CENTER);
        mainFrame.getContentPane().add(textArea,BorderLayout.CENTER);
        mainFrame.getContentPane().add(upLoadTags,BorderLayout.CENTER);
      //  mainFrame.getContentPane().add(menuBar,BorderLayout.CENTER);
      //  mainFrame.getContentPane().add(tags2,BorderLayout.CENTER);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setVisible(true);
        */
        new Tagging_Interface();

    }
}

