import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class client {

    String               appName     = "parzival chat";
    client               client;
    JFrame               newFrame    = new JFrame(appName);
    JButton              sendMessage;
    JButton              sendFile;
    JTextField           messageBox;
    JTextArea            chatBox;
    JTextField           usernameChooser;
    JFrame               preFrame;
    DataOutputStream     dot;
    int                  count = 0;
    String               str2 = "";
    static Socket        s;
    static Socket        s1;
    int                  bytesRead;
    int                  current = 0;
    FileOutputStream     fos = null;
    BufferedOutputStream bos = null;
    OutputStream         os;
    FileInputStream      fis;
    BufferedInputStream  bis = null;
client ()
{ JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendFile    = new JButton("File");


       



        




       

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);
        southPanel.add(sendFile, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);


        sendMessage.addActionListener(new sendMessageButtonListener());
        sendFile.addActionListener(new sendFileButtonListener());



        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(470, 300);
        newFrame.setVisible(true);



        try{
            

                 



                 DataInputStream din=new DataInputStream(s1.getInputStream());  
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                System.out.println("sop");

                while(!str2.equals("stop")){
                str2 =(String) din.readUTF();
                System.out.println(str2);
                chatBox.append("<Server>:  " + str2 + "\n");

                }




                 byte [] mybytearray  = new byte [60322];
                 InputStream is = s.getInputStream();
                 fos = new FileOutputStream("/home/kushal/Desktop/java_day2/xoxo1.java");
                 bos = new BufferedOutputStream(fos);
                 bytesRead = is.read(mybytearray,0,mybytearray.length);
                 current = bytesRead;
                 do {
                    bytesRead =
                     is.read(mybytearray, current, (mybytearray.length-current));
                      if(bytesRead >= 0) current += bytesRead;
                    } while(bytesRead > -1);

                 bos.write(mybytearray, 0 , current);
                 bos.flush();





            
                


        


            
       



    }
        
        catch(Exception xo)
        {
            System.out.println(xo);
        }




       
      


}
    public static void main(String[] args) {
        try{
            s1 = new Socket("localhost",1125);
            s = new Socket("localhost",1025);
        }
        catch(Exception bt)
        {
            System.out.println(bt);
        }
       client client = new client();
    }

    public void preDisplay() {


        preFrame.setVisible(false);
        


    }
public void visible(){
    try{
           
    }

        catch(Exception xo){System.out.println(xo);}
}
    public void display() {
       


    }





    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {
                try{
                  dot = new DataOutputStream(s1.getOutputStream());
                

                dot.writeUTF(messageBox.getText());
                count++;
            dot.flush();
            
        }
            catch(Exception e2){System.out.println(e2);}                
            chatBox.append("<Client>:  " + messageBox.getText() + "\n"); 
                

                messageBox.setText("");
            }






            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sonoo","kushal","/456QWEasd/");
            
            Statement stmt = con.createStatement();
            stmt.executeUpdate("truncate table message");
             stmt.executeUpdate("INSERT INTO message (id,mess) VALUES ("+count+",'"+messageBox.getText()+"'"+")");
            ResultSet rs = stmt.executeQuery("select * from message");
            while(rs.next())
                {System.out.println(rs.getInt(1)+" "+rs.getString(2));
                    //l1.addElement(rs.getString(2));
                }           con.close();

        }
        catch(Exception x){System.out.println(x);}



        




            messageBox.requestFocusInWindow();
        }
    }





class sendFileButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
           

            System.out.println("xD");
        JFileChooser fc = new JFileChooser();
        int i = (int)fc.showOpenDialog(newFrame);
        if(i==JFileChooser.APPROVE_OPTION)
        {
            File f = fc.getSelectedFile();

            String filepath = f.getPath();
            System.out.println(filepath);
            try{
            //ServerSocket socket = new ServerSocket(2555);
        
            //Socket clientSocket = socket.accept();
                File myFile = new File(filepath);
                byte[] mybytearray = new byte[(int)myFile.length()];
             fis= new FileInputStream(myFile);
             bis = new BufferedInputStream(fis);
             bis.read(mybytearray,0,mybytearray.length);

            os = s.getOutputStream();
            os.write(mybytearray,0,mybytearray.length);
            
            /*int ik;
            while((ik = fis.read(buffer))> 0)
            {
                os.write(ik);
                System.out.println("bc");
            }*/
            os.flush();
            System.out.println("xds");

          if (bis != null) bis.close();
          if (os != null) os.close();
          //if (s1!=null) s1.close();
         // if(ss1!= null) ss1.close();
        
            //fis.close();
            //os.close();
            
            //clientSocket.close();
        
    }

    catch(Exception ec){
        System.out.println(ec);
        }



       }
        }
    }






    String  username;

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            if (username.length() < 1) {
                System.out.println("No!");
            } 
            else {
                preFrame.setVisible(false);
                display();
             /* try{ s = new Socket("localhost",1025);
             dot = new DataOutputStream(s.getOutputStream());
                DataInputStream din=new DataInputStream(s.getInputStream());  
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

                
        
                
                while(!str2.equals("stop")){
                str2 = din.readUTF();

                
                //tf1.setText(str2);
                //l2.addElement(str2);



        }
         
        System.out.println(str2);
        chatBox.append("<Server>:  " + str2 + "\n");} 
              catch(Exception ex)
              {
                System.out.println(ex);
              }*/
                
            }
        }

    }
}
