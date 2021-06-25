
package notepad;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends JFrame implements ActionListener{

    JTextArea  area;
    JScrollPane pane;
    String text;
    Notepad()
    {
     
     JMenuBar menubar=new JMenuBar();
     
     JMenu file=new JMenu("File");
     file.setFont(new Font("san-serif",Font.BOLD,20));
     
     JMenuItem newdoc=new JMenuItem("New");
     newdoc.setFont(new Font("san-serif",Font.BOLD,18));
     newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
     newdoc.addActionListener(this);
     
     JMenuItem open=new JMenuItem("Open");
     open.setFont(new Font("san-serif",Font.BOLD,18));
     open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
     open.addActionListener(this);
     
     JMenuItem save=new JMenuItem("Save");
     save.setFont(new Font("san-serif",Font.BOLD,18));
     save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
     save.addActionListener(this);
     
     JMenuItem print=new JMenuItem("Print");
     print.setFont(new Font("san-serif",Font.BOLD,18));
     print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
     print.addActionListener(this);
     
     JMenuItem exit=new JMenuItem("Exit");
     exit.setFont(new Font("san-serif",Font.BOLD,18));
     exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
     exit.addActionListener(this);
     
     file.add(open);
     file.add(save);
     file.add(newdoc);
     file.add(print);
     file.add(exit);
             
     
     JMenu edit=new JMenu("Edit");
     
     edit.setFont(new Font("san-serif",Font.BOLD,20));
     JMenuItem cut=new JMenuItem("Cut");
     cut.setFont(new Font("san-serif",Font.BOLD,18));
     cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
     cut.addActionListener(this);
     
     JMenuItem copy=new JMenuItem("Copy");
     copy.setFont(new Font("san-serif",Font.BOLD,18));
     copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
     copy.addActionListener(this);
     
     JMenuItem paste=new JMenuItem("Paste");
    paste.setFont(new Font("san-serif",Font.BOLD,18));
     paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
     paste.addActionListener(this);
     
     JMenuItem selectall=new JMenuItem("Select All");
     selectall.setFont(new Font("san-serif",Font.BOLD,18));
     selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
     selectall.addActionListener(this);
     
     
     edit.add(cut);
     edit.add(copy);
     edit.add(paste);
     edit.add(selectall);
     
     JMenu help=new JMenu("Help");
     help.setFont(new Font("san-serif",Font.BOLD,18));
     help.setFont(new Font("san-serif",Font.BOLD,20));
     JMenuItem about=new JMenuItem("About The Notepad");
     about.addActionListener(this);
     about.setFont(new Font("san-serif",Font.BOLD,18));
     
     help.add(about);
     
     menubar.add(file);
     menubar.add(edit);
     menubar.add(help);
     
     
     setJMenuBar(menubar);
     
     area=new JTextArea();
     area.setLineWrap(true);
     area.setWrapStyleWord(true);
     area.setFont(new Font("san-serif",Font.BOLD,20));
     pane=new JScrollPane(area);
     pane.setBorder(BorderFactory.createEmptyBorder());
     add(pane,BorderLayout.CENTER);
        
        
     setBounds(0,0,1950,1050);
     setTitle("Notepad");
     
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("New"))
        {
            area.setText("");
        }
        else if(e.getActionCommand().equals("Open"))
        {
            JFileChooser choose=new JFileChooser();
            choose.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict=new  FileNameExtensionFilter("onlu .txt files","txt");
            choose.addChoosableFileFilter(restrict);
            
            int action=choose.showOpenDialog(this);
         if(action != JFileChooser.APPROVE_OPTION)
         {
             return;
         }
         
         File file=choose.getSelectedFile();
          try{
             BufferedReader reader=new BufferedReader(new FileReader(file));
             area.read(reader,null);
         }catch(Exception ex){System.out.println(ex);};
         
        }
        else if(e.getActionCommand().equals("Save"))
        {
         JFileChooser saveas=new JFileChooser();
         saveas.setApproveButtonText("Save");
         int action=saveas.showOpenDialog(this);
         if(action != JFileChooser.APPROVE_OPTION)
         {
             return;
         }
         
         File filename = new File(saveas.getSelectedFile()+".txt");
         BufferedWriter outfile=null;
         try{
             outfile=new BufferedWriter(new FileWriter(filename));
             area.write(outfile);
         }catch(Exception ex){System.out.println(ex);};
         
        }
        else if(e.getActionCommand().equals("Print"))
        {
            try{
                area.print();
            }catch(Exception ex){System.out.println(ex);};
        }
        else if(e.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
        else if(e.getActionCommand().equals("Cut"))
        {
            area.getSelectedText();
            area.replaceRange(" ", area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(e.getActionCommand().equals("Copy"))
        {
            text=area.getSelectedText();
        }
        else if(e.getActionCommand().equals("Paste"))
        {
            area.insert(text, area.getCaretPosition());
        }
        else if(e.getActionCommand().equals("Select All"))
        {
            area.selectAll();
        }
        else if(e.getActionCommand().equals("About The Notepad"))
        {
            new about().setVisible(true);
        }
        
    }
    
    
    
    public static void main(String[] args) {
        new Notepad().setVisible(true);
    }

    



    
}
