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
class server extends JFrame implements ActionListener{
	JTextField   tf1;
	JButton      b;
	JButton ad;
	BufferedReader br ;
	DataOutputStream dout;
	DataInputStream ds;
	int count = 0;
	//DefaultListModel<String> l1;
	//DefaultListModel<String> l2;
	//JPanel pan;
	//JSplitPane splitPane;
	//JPanel topPanel;
	JPanel textPanel;
	JScrollPane scrollPane;
	//JTextArea textArea;
	JPanel inputPanel;
	//JTextField textField;
	//JButton button;
	OutputStream os;
	FileInputStream fis;
	static Socket s;
	static Socket s1;
	static ServerSocket ss;
	static ServerSocket ss1;
	BufferedInputStream bis = null;
	JTextArea            chatBox;
	 int                  bytesRead;
    int                  current = 0;
    FileOutputStream     fos = null;
    BufferedOutputStream bos = null;

	server(){

		JFrame f = new JFrame("Server");
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

		//splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pan,textPanel);

		//pan = new JPanel();
		//textPanel = new JPanel();

		//scrollPane = new JScrollPane();
		//textArea = new JTextArea();

		//inputPanel = new JPanel();
		tf1 = new JTextField(30);
		tf1.requestFocusInWindow();


		b = new JButton("KS");
		ad = new JButton("%");



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





        southPanel.add(tf1, left);
        southPanel.add(b, right);
        southPanel.add(ad,right);
        b.addActionListener(this);
        ad.addActionListener(this);




        mainPanel.add(BorderLayout.SOUTH, southPanel);

        f.add(mainPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(470, 300);
        f.setVisible(true);






	

		
		try{
			
			
			


			ds = new DataInputStream(s1.getInputStream());
         dout = new DataOutputStream(s1.getOutputStream());
         br = new BufferedReader(new InputStreamReader(System.in));
        String str="";

        
       


        while(!str.equals("stop")){
        str = (String)ds.readUTF();
        chatBox.append("<client>: "+ str+ "\n");
        System.out.println("str");
        //System.out.println(s1 + "is the message");
        tf1.setText("");


        //l2.addElement(str);
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
		
		 
		

          try{//ss1 = new ServerSocket(1125);
			//s1 = ss1.accept();
		//ss = new ServerSocket(1025);
		//s = ss.accept();
        
        //str2=br.readLine();
        
        //s.close();
    
    
    //ds.close();
    
        
    }
    

    
        catch(Exception e1)
        {
        	System.out.println(e1);
        }


    
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == b){
		//String s1 = tf1.getText();
			System.out.println("ser button");
		String str2 = "";
			 str2 = tf1.getText();
		try{ 
			//System.out.println(str2);
			System.out.println("sop");
            dout.writeUTF(str2);
            chatBox.append("<server>:  " + str2 + "\n");
            count++;
            dout.flush();
		}
		catch(Exception e2)
		{
			System.out.println(e2);
		}
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sonoo","root","/456QWEasd/");
			Statement stmt = con.createStatement();
			 stmt.executeUpdate("truncate table server");
			 stmt.executeUpdate("INSERT INTO server (id,message) VALUES ("+count+",'"+str2+"'"+")");
			ResultSet rs = stmt.executeQuery("select * from server where id >= " + count );
			while(rs.next())
				{System.out.println(rs.getInt(1)+" "+rs.getString(2));
				//	l1.addElement(rs.getString(2));
		}
			con.close();

		}
		catch(Exception x){System.out.println(x);}
		/*try {Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sonoo","root","/456QWEasd/");
		Statement stmt = con.createStatement();
		stmt.executeQuery("truncate table server");
		con.close();}
		catch(Exception x){System.out.println(x);}*/

	}
	if(e.getSource() == ad)
	{	
		 System.out.println("xD");
		JFileChooser fc = new JFileChooser();
		int i = fc.showOpenDialog(this);
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
	public static void main(String[] args) {
		try{ss1 = new ServerSocket(1125);
		s1 = ss1.accept();
		ss = new ServerSocket(1025);
		s =  ss.accept();}
		catch(Exception du)
		{
			System.out.println(du);
		}

		server p = new server();
		//p.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
