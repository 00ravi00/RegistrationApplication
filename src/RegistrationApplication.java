import javax.swing.*;

import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;  

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class RegistrationApplication extends JFrame implements ActionListener{
	//creating panels
	JPanel suPl = new JPanel(null);
	JPanel siPl = new JPanel(null);
	JTabbedPane tbp = new JTabbedPane();
	 // declaring variables
	JLabel intro, FirstName, Email, CrePass, ConPass, LastName, state, Gender, email2, password2;
	JTextField fntf, lntf, emtf, sttf, em2tf;
	JButton btn1, btn2, btn3, btn4;
	JPasswordField p1, p2, p3;
	JRadioButton rdb1, rdb2;
	// constructor
	public RegistrationApplication(){
		
		setVisible(true);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registration Form by KAMALAKAR");
		
        tbp.add("SignUp", suPl);
		tbp.add("SignIn", siPl);
		add(tbp);
		
// Creating instances and Assigning to variables
        intro = new JLabel("Registration Form Application:");
        intro.setForeground(Color.blue);
        intro.setFont(new Font("Serif", Font.BOLD, 20));
 
        FirstName = new JLabel("FirstName*:");
        LastName = new JLabel("LastName*:");
        Email = new JLabel("Email-ID*:");
        CrePass = new JLabel("Create Passowrd*:");
        ConPass = new JLabel("Confirm Password*:");
        Gender = new JLabel("Gender:");
        state = new JLabel("State*:");
        email2 = new JLabel("Email-ID*:");
        email2.setFont(new Font("American Typewriter", Font.PLAIN, 20));
        password2 = new JLabel("Enter Password*:");
        password2.setFont(new Font("American Typewriter", Font.PLAIN, 20));
         
        fntf = new JTextField();
        lntf = new JTextField();
        emtf = new JTextField();
        em2tf = new JTextField();
        sttf = new JTextField();
        
        rdb1 = new JRadioButton("Male");
        rdb2 = new JRadioButton("Female");
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rdb1);
        bg.add(rdb2);
        
        p1 = new JPasswordField();
        p2 = new JPasswordField();
        p3 = new JPasswordField();
        
        btn1 = new JButton("SignUp");
        btn2 = new JButton("Clear");
        btn3 = new JButton("SignIn");
        btn4 = new JButton("Clear");
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        
        // setting exact position where they should appear on panels
        intro.setBounds(100, 30, 400, 30);
        FirstName.setBounds(80, 70, 200, 30);
        LastName.setBounds(80, 110, 200, 30);
        Email.setBounds(80, 150, 200, 30);
        CrePass.setBounds(80, 190, 200, 30);
        ConPass.setBounds(80, 230, 200, 30);
        Gender.setBounds(80, 270, 200, 30);
        state.setBounds(80, 310, 200, 30);
        fntf.setBounds(300, 70, 200, 30);
        lntf.setBounds(300, 110, 200, 30);
        emtf.setBounds(300, 150, 200, 30);
        p1.setBounds(300, 190, 200, 30);
        p2.setBounds(300, 230, 200, 30);
        rdb1.setBounds(300, 270, 100, 30);
        rdb2.setBounds(400, 270, 100, 30);
        sttf.setBounds(300, 310, 200, 30);
        btn1.setBounds(50, 350, 100, 30);
        btn2.setBounds(170, 350, 100, 30);
        btn3.setBounds(170, 350, 100, 30);
        btn4.setBounds(300, 350, 100, 30);
        em2tf.setBounds(330, 100, 250, 30);
        email2.setBounds(100, 100, 100, 30);
        p3.setBounds(330, 200, 250, 30);
        password2.setBounds(100, 200, 250, 30);
               // adding objects to panels
        suPl.add(intro);
        suPl.add(FirstName);
        suPl.add(fntf);
        suPl.add(LastName);
        suPl.add(lntf);
        suPl.add(Email);
        suPl.add(p1);
        suPl.add(CrePass);
        suPl.add(p2);
        suPl.add(ConPass);
        suPl.add(emtf);
        suPl.add(Gender);
        suPl.add(rdb1);
        suPl.add(rdb2);
        suPl.add(state);
        suPl.add(sttf);
        suPl.add(btn1);
        suPl.add(btn2);
        siPl.add(em2tf);
        siPl.add(p3);
		siPl.add(email2);
		siPl.add(password2);
		siPl.add(btn3);
		siPl.add(btn4);
	}
	@Override
	// events to happen when buttons are clicked
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		// for button SignUp
		if(event.getSource()==btn1) {
			int x = 0;
            String s1 = fntf.getText();
            String s2 = lntf.getText();
            String s5 = emtf.getText();
            
            String s7 = sttf.getText();
 
            char[] s3 = p1.getPassword();
            char[] s4 = p2.getPassword(); 
            String s8 = new String(s3);
            String s9 = new String(s4);
            // checking if all important arfields are filled or not
            if(s1.isEmpty()||s2.isEmpty()||s8.isEmpty()||s5.isEmpty()||s7.isEmpty()||s9.isEmpty()) {
            	JOptionPane.showMessageDialog(btn1, "Please fill all important fields");
            }  
            // checking if password
            else if (s8.equals(s9)) {
            
		    // storing data in database
				try{  
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ravi?useSSL=false","root","00lavi00");
					PreparedStatement ps = con.prepareStatement("insert into userdata(firstName, lastName, password, email, state) values(?,?,?,?,?)");
				      ps.setString(1, s1);
	                  ps.setString(2, s2);
	                  ps.setString(3, s8);
	                  ps.setString(4, s5);
	                  ps.setString(5, s7);
	                  // sending email and also checks if email exists or not
	                  String to = s5;
                      String msg = "Your credentials are\n" + s1+ "\n"+ s2 + "\n" + s8+ "\n" + s4+ "\n" + s7+ "\n";
                      send(btn1,to,msg);
                      
	                  ps.executeUpdate();
	                  x++;
	                  if (x > 0) 
	                  {
	                      JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");
	                      
	                  }
	              }
				//checks if email exists or not
				catch (MySQLIntegrityConstraintViolationException e) 
	              {
					JOptionPane.showMessageDialog(btn1, "email already exists");
	              }
	             catch (Exception ex) 
	              {
	                  System.out.println(ex);
	              }
            }
            
            else
		         {
		              JOptionPane.showMessageDialog(btn1, "Password Does Not Match");
		          } 
		} 
		// for button clear in signup panel
        else if(event.getSource()==btn2) {
     
          fntf.setText("");
          lntf.setText("");
          p1.setText("");
          p2.setText("");
          emtf.setText("");
          
          sttf.setText("");
            }
		// for button signin in signin panel
        else if(event.getSource()==btn3) {
        	String s1 = em2tf.getText();
        	char[] s2 = p3.getPassword();
        	String b = new String(s2);
        	// retrives data
        	try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ravi?useSSL=false","root","00lavi00");
				PreparedStatement ps = con.prepareStatement("SELECT password, firstName FROM userdata WHERE email =" + s1 );
				ResultSet rs = ps.executeQuery();
				rs.next();
				
				if(b.equals(rs.getString("password"))){
					JOptionPane.showMessageDialog(btn3, "Hello " + rs.getString("firstName"));
				}
				else JOptionPane.showMessageDialog(btn3, "Incorrect Password");
			} 
        	catch (SQLException e) {
				
        		JOptionPane.showMessageDialog(btn3, "Incorrect Password or UserName");
			}  
        	catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
        	
        }
		// for clear button in signin pannel
        else if(event.getSource()==btn4) {
        	em2tf.setText("");
        	p3.setText("");
        }
    } 
				
	public static void send(JButton btn1, String to,String msg){  
          
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
          
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication("testingpurpoes@gmail.com","00lavi00");  
         }    
        });    
           
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));    
         message.setSubject("Hello");    
         message.setText(msg);    
           
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } 
        catch (SendFailedException e) {
        	JOptionPane.showMessageDialog(btn1, "Email-ID doesnt exist");
        	throw new RuntimeException(e);
        }
        catch (MessagingException e) {throw new RuntimeException(e);}    
           
  }  
	
	
	public static void main(String args[])
	   {
	        new RegistrationApplication();
	    }
}
