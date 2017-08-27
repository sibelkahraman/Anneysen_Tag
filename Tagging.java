/**
 * Created by Sibel-PC on 1.8.2017.
 */

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.io.*;
import java.io.IOException;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.border.EmptyBorder;



public class Tagging extends JFrame {
    private String upload;
    private int no1 = 1;
    private int no2 = 1;
    private int no3 = 1;
    private int i = 2;
    private int show_text_id = 1;
    private int file_boolean = 0;
    private int tag_boolean = 0;
    private int t = 1;
    private String tagged_File;
    private String strLine;
    private JFrame Frame;
    private JButton upload_button;
    private JButton tag_button;
    private JPanel Text_Pane_Area;
    private JTextArea text_area;
    private JPanel check_Boxes;
    private JButton save_button;
    JButton skip_button;
    JButton next_button;
    private JCheckBox[] box;
    private int box_id = 0;
    private static int s_button = 0;


    private Tagging() {
        Frame = new JFrame("");
        Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel gui = new JPanel(new GridLayout(3, 2, 5, 5));
        gui.setBorder(new EmptyBorder(10, 10, 10, 10));
        gui.setBackground(Color.lightGray);

        JPanel controls = new JPanel(new BorderLayout(10, 10));
        gui.add(controls, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(1, 5, 10, 10));
        controls.add(buttons, BorderLayout.CENTER);
        buttons.setBackground(Color.LIGHT_GRAY);

        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();

        buttons.add(b1);
        b1.setVisible(false);
        upload_button = new JButton("UP");
        upload_button.setPreferredSize(new Dimension(150, 100));
        upload_button.setBackground(Color.gray);
        upload_button.setOpaque(true);
        upload_button.setFont(new Font("Arial", Font.ITALIC, 25));
        upload_button.setForeground(Color.WHITE);
        // upload_button.setSize(80,60);
        upload_button.setLocation(30, 50);
        // upload_button.setBounds(150,50,70,150);
        Dimension d1 = upload_button.getPreferredSize();
        Dimension set = new Dimension(
                (int) d1.getWidth() * 2,
                (int) d1.getHeight() * 2);
        upload_button.setMaximumSize(set);
        buttons.add(upload_button);
        buttons.add(b2);
        b2.setVisible(false);

        tag_button = new JButton("TAG");
        tag_button.setPreferredSize(new Dimension(150, 100));
        tag_button.setBackground(Color.gray);
        tag_button.setOpaque(true);
        tag_button.setFont(new Font("Arial", Font.ITALIC, 25));
        tag_button.setForeground(Color.WHITE);
        tag_button.setLocation(150, 50);
        Dimension d2 = tag_button.getPreferredSize();
        Dimension sett = new Dimension(
                (int) d2.getWidth() * 2,
                (int) d2.getHeight() * 2);
        tag_button.setMaximumSize(sett);
        buttons.add(tag_button);
        buttons.add(b3);
        b3.setVisible(false);
        Text_Pane_Area = new JPanel(new BorderLayout(10, 10));
        gui.add(Text_Pane_Area, BorderLayout.CENTER);
        Text_Pane_Area.setBackground(Color.LIGHT_GRAY);

        JPanel texts = new JPanel(new GridLayout(0, 2, 30, 30));
        Text_Pane_Area.add(texts, BorderLayout.CENTER);
        texts.setBackground(Color.LIGHT_GRAY);

        JPanel text_scroll = new JPanel(new GridLayout(0, 1, 10, 10));
        texts.add(text_scroll, BorderLayout.WEST);

        JPanel scroll_pane = new JPanel(new GridLayout(0, 1, 1, 1));
        texts.add(scroll_pane, BorderLayout.NORTH);

        text_area = new JTextArea(50, 10);
        text_scroll.add(new JScrollPane(text_area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        text_area.setLineWrap(true);
        text_area.setFont(new Font("Arial", Font.ITALIC, 20));
        text_area.setForeground(Color.black);

        check_Boxes = new JPanel(new GridLayout(0, 1, 1, 1));
        JScrollPane sp = new JScrollPane(check_Boxes);

        check_Boxes.revalidate();
        check_Boxes.repaint();
        scroll_pane.add(sp);

        JPanel save_Button = new JPanel(new GridLayout(1, 1, 10, 10));
        gui.add(save_Button, BorderLayout.CENTER);
        save_Button.setBackground(Color.LIGHT_GRAY);

        JPanel s_button = new JPanel((new GridLayout(1, 1, 10, 10)));
        save_Button.add(s_button, BorderLayout.CENTER);
        s_button.setBackground(Color.LIGHT_GRAY);
        JButton b4 = new JButton("efwreq");
        b4.setVisible(false);
        b4.setPreferredSize(new Dimension(100, 100));
        skip_button = new JButton("SKIP");
        skip_button.setBackground(Color.gray);
        skip_button.setFont(new Font("Arial", Font.ITALIC, 25));
        skip_button.setForeground(Color.WHITE);
        skip_button.setVisible(true);
        skip_button.setPreferredSize(new Dimension(100, 100));

        JButton b6 = new JButton("efwreq");
        b6.setVisible(false);
        b6.setPreferredSize(new Dimension(100, 100));
        next_button = new JButton("NEXT");
        next_button.setBackground(Color.gray);
        next_button.setFont(new Font("Arial", Font.ITALIC, 25));
        next_button.setForeground(Color.WHITE);
        next_button.setVisible(true);
        next_button.setPreferredSize(new Dimension(100, 100));

        s_button.add(skip_button);
        s_button.add(b6);
        save_button = new JButton("SAVE");
        save_button.setPreferredSize(new Dimension(100, 100));
        save_button.setBackground(Color.gray);
        tag_button.setMaximumSize(new Dimension(200, 150));
        save_button.setOpaque(true);
        save_button.setFont(new Font("Arial", Font.ITALIC, 25));
        save_button.setForeground(Color.WHITE);
        Dimension d3 = save_button.getPreferredSize();
        Dimension settt = new Dimension(
                (int) d3.getWidth() * 2,
                (int) d3.getHeight() * 2);
        save_button.setPreferredSize(settt);
        s_button.add(save_button);
        s_button.add(b4);
        s_button.add(next_button);
        Frame.add(gui);
        Frame.pack();
        Frame.setVisible(true);
        check_exist_table();
        Listener();

    }

    private void check_exist_table(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet res = meta.getTables(null, null, "ANNE",
                    new String[]{"TABLE"});
            if (res.next()) {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM ANNE WHERE no=?");
                pst.setInt(1,1);
                ResultSet rs = pst.executeQuery();
                if (rs.next())
                    show_text_id = rs.getInt("last_row_number");
                file_boolean = 1;
                show_text();
            }

            DatabaseMetaData meta2 = conn.getMetaData();
            ResultSet res2 = meta2.getTables(null, null, "TAG",
                    new String[]{"TABLE"});
            if (res2.next()){
                    box = new JCheckBox[1000];
                    PreparedStatement pst = conn.prepareStatement("SELECT * FROM TAG WHERE no=?");
                    int t = box_id + 1;
                    pst.setInt(1,t);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        String s = rs.getString("tag_name");
                        box[box_id] = new JCheckBox(s);
                        check_Boxes.add(box[box_id]);
                        check_Boxes.revalidate();
                        check_Boxes.repaint();
                        box_id++;
                        t = box_id+1;
                        pst.setInt(1,t);
                        rs = pst.executeQuery();
                    }
                tag_boolean = 1;
            }
        }
        catch (SQLException ignored){
        }
    }

    private void Listener() {
        upload_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    upload = chooser.getSelectedFile().getPath();
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getPath());
                    save_database_text(chooser.getSelectedFile().getPath());
                    show_text();
                }
            }
        });

        tag_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getPath());
                    Set_tag_boxes(chooser.getSelectedFile().getPath());
                }
            }
        });

        skip_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //write to database s to status column
                if(file_boolean == 1){
                    status("SKIP");
                    update_table("ANNE",show_text_id);
                    show_text();
                    clear_checkBox();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Firstly Upload a file");
                }


            }
        });

        next_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //write to database s to status column
                if(file_boolean == 1){
                    if(s_button == 1) {
                        update_table("ANNE", show_text_id);
                        show_text();
                        clear_checkBox();
                        s_button = 0;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Firstly Click Save or Skip Button");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(null, "Firstly Upload a file");
                }


            }
        });

        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file_boolean == 0 || tag_boolean == 0) {
                    JOptionPane.showMessageDialog(null, "Firstly Upload a file");
                }
                if (file_boolean == 1 && tag_boolean == 1) {

                   /* while (t == 1) {
                        tagged_File = JOptionPane.showInputDialog(null, "Give a Name to New File");
                        tagged_File = tagged_File.concat(".txt");
                        File f = new File(tagged_File);
                        if (f.exists()) {
                            int selectedOption = JOptionPane.showConfirmDialog(null,
                                    "The File is Exist! \n Do You Want to Write on the File?",
                                    "Yes",
                                    JOptionPane.YES_NO_OPTION);
                            if (selectedOption == 0) {
                                t++;
                            }
                        } else {
                            t++;
                        }
                    }*/
                    s_button = 1;
                    tagging();
                    update_table("ANNE",show_text_id);
                    status("SAVE");
                    i++;
                }
            }
        });
    }

    private void status (String status){
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");

                String query = "UPDATE ANNE SET status = ? WHERE no = ?";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, status);
                preparedStmt.setInt(2, show_text_id-1);
                preparedStmt.executeUpdate();
            } catch (SQLException ignored) {
            }
        }

    private void save_to_DB(int id, String title, String context) {
        Statement statement ;
        Connection con1 ;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet res = meta.getTables(null, null, "ANNE",
                    new String[] {"TABLE"});
            if(!res.next()) {
                con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");
                // SQL statement for creating a new table
                String sql = "CREATE TABLE IF NOT EXISTS ANNE (\n"
                        + " no INT PRIMARY KEY,\n"
                        + "	id INT,\n"
                        + "	title TEXT,\n"
                        + "	context TEXT,\n"
                        + " status TEXT,\n"
                        + " last_row_number INT\n"
                        + ");";
                statement = con1.createStatement();
                statement.execute(sql);
                con1.close();
            }
           /*else {
                //if table exist gets last row number
                PreparedStatement pst = null;
                pst = conn.prepareStatement("SELECT * FROM ANNE WHERE no=?");
                pst.setInt(1,1);
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    no1 = rs.getInt("last_row_number");
                    no1++;
                }
            }*/
            //inserts new datum
            String insert = "INSERT INTO ANNE(no, id, title, context,status , last_row_number) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setInt(1, no1);
            stmt.setInt(2, id);
            stmt.setString(3, title);
            stmt.setString(4, context);
            stmt.setString(5,"X");
            stmt.setInt(6, 1);
            stmt.execute();
            conn.close();
            no1++;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void tagging (){
        int text_id = 0 ;
        int tag_id  = 0;
        PreparedStatement text_statement ;
        PreparedStatement tag_statement ;
        Statement statement ;
        Connection con2 ;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet res = meta.getTables(null, null, "TAG_TEXT",
                    new String[] {"TABLE"});
            if(!res.next()) {
                con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");
                // SQL statement for creating a new table
                String sql = "CREATE TABLE IF NOT EXISTS TAG_TEXT (\n"
                        + " no INT PRIMARY KEY,\n"
                        + "	text_id INT,\n"
                        + " tag_id INT,\n"
                        + " last_row_number INT\n"
                        + ");";
                statement = con2.createStatement();
                statement.execute(sql);
                con2.close();
            }
            else{
                //if table exist gets last row number
                PreparedStatement pst;
                pst = conn.prepareStatement("SELECT * FROM TAG_TEXT WHERE no=?");
                pst.setInt(1,1);
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    no3 = rs.getInt("last_row_number");
                    no3++;
                }
            }

            text_statement = conn.prepareStatement("SELECT * FROM ANNE WHERE no=?");
            int s = show_text_id;
            s--;
            text_statement.setInt(1,s);
            ResultSet r_set1 = text_statement.executeQuery();
            if (r_set1.next()){
                text_id = r_set1.getInt("id");
            }
            int b_id = 0;
            while(b_id < box_id ){
                if (box[b_id].isSelected()){
                    tag_statement = conn.prepareStatement("SELECT * FROM TAG WHERE tag_name=?");
                    tag_statement.setString(1,box[b_id].getText());
                    ResultSet r_set2 = tag_statement.executeQuery();
                    if (r_set2.next()){
                        tag_id = r_set2.getInt("no");
                    }
                    String insert = "INSERT INTO TAG_TEXT(no, text_id, tag_id, last_row_number) VALUES (?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(insert);
                    stmt.setInt(1, no3);
                    stmt.setInt(2, text_id);
                    stmt.setInt(3,tag_id);
                    stmt.setInt(4,1);
                    update_table("TAG_TEXT",no3);
                    stmt.execute();
                    update_table("TAG_TEXT",no3);
                    no3++;
                }

                b_id++;
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    private void clear_checkBox(){

        for(int a = 0; a < box_id; a++){
            box[a].setSelected(false);
        }
        check_Boxes.revalidate();
        check_Boxes.repaint();
    }

    private void update_table(String table_name,int number){
        //updates last row number
        String query ;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");
            if (table_name.compareTo("ANNE") == 0) {
                query = "UPDATE ANNE SET last_row_number = ? WHERE no = ?";
             /*   PreparedStatement pst = conn.prepareStatement("SELECT * FROM ANNE WHERE no=?");
                pst.setInt(1,1);
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    number = rs.getInt("last_row_number");
                }*/
            }
            else {
                //updates last row number
                query = "UPDATE TAG_TEXT SET last_row_number = ? WHERE no = ?";
               /* PreparedStatement pst = conn.prepareStatement("SELECT * FROM TAG_TEXT WHERE no=?");
                pst.setInt(1,1);
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    number = rs.getInt("last_row_number");
                }*/
            }
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, number);
            preparedStmt.setInt(2, 1);
            preparedStmt.executeUpdate();
        }
        catch (SQLException ignored){

        }
    }

    private void show_text() {
        String text_context ;
        PreparedStatement pst ;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");
            pst = conn.prepareStatement("SELECT * FROM ANNE WHERE no=?");
            pst.setInt(1,show_text_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                text_context = rs.getString("context");
                text_area.setEditable(false);
                text_area.setText(text_context);
                text_area.validate();
            }
            show_text_id++;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }


    }

    private void save_database_text(String text) {
        //this string is using in split function its is split a sentence according to ," or ","
        String split = "(,\")|(\",\")";
        String s2 = "\t";
        BufferedReader br;
        int buffer_size = 100* 1024;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(text),"UTF-8"),buffer_size);
            while ((strLine = br.readLine()) != null){
                String line[] = strLine.split(s2);
                int id = Integer.parseInt(line[0]);
                save_to_DB(id,line[1],line[2]);
                file_boolean = 1;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }
    }

    private void tag_table(String tag_name){
        Statement statement;
        Connection con2 ;
        try {
            con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");
            // SQL statement for creating a new table
            String sql = "CREATE TABLE IF NOT EXISTS TAG (\n"
                    + " no INT PRIMARY KEY,\n"
                    + "	tag_name TEXT\n"
                    + ");";
            statement = con2.createStatement();
            statement.execute(sql);
            con2.close();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "sibel", "123456");
            String insert = "INSERT INTO TAG(no, tag_name) VALUES (?,?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setInt(1, no2);
            stmt.setString(2, tag_name);
            stmt.execute();
            conn.close();
            no2++;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void Set_tag_boxes(String tag) {
        BufferedReader br;
        String strLine;
        try {
            br = new BufferedReader(new FileReader(tag));
            box = new JCheckBox[1000];
            box_id = 0;
            while ((strLine = br.readLine()) != null) {
                box[box_id] = new JCheckBox(strLine);
                check_Boxes.add(box[box_id]);
                check_Boxes.revalidate();
                check_Boxes.repaint();
                tag_table(box[box_id].getText());
                box_id++;
            }
            tag_boolean = 1;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }
    }

    public static void main(String[] args) {
        new Tagging();


}
}

