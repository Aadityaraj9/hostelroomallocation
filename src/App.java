import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;    
import java.awt.event.ActionListener;
import java.awt.*;

// ALTER SEQUENCE public.occupied_id_seq RESTART WITH 1;
// SELECT * FROM public.occupied

public class App {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Hotel room allocation");
		frame.setSize(1500,800);
		JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));		
	    
	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
	    
		JLabel urk = new JLabel("Please enter your register number: ");
	    final JTextField urktxt = new JTextField(15); 
	    urktxt.setHorizontalAlignment(JTextField.CENTER);
		JLabel password = new JLabel("Please enter your password: ");
	    final JTextField passwordtxt = new JTextField(15); 
	    passwordtxt.setHorizontalAlignment(JTextField.CENTER);
	    JButton next = new JButton("Next");
	    next.setFocusable(false);
	    next.setFont(font);
	    urktxt.setFont(font);
	    urk.setFont(font);
	    passwordtxt.setFont(font);
	    password.setFont(font);
	    
	    urk.setAlignmentX(Component.CENTER_ALIGNMENT);
        urktxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordtxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        next.setAlignmentX(Component.CENTER_ALIGNMENT);  
        
		JLabel label1 = new JLabel("     Hostel room allocation program     ");
		label1.setFont(font);
		label1.setOpaque(true);
		label1.setBackground(Color.RED);
		label1.setForeground(Color.WHITE);
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label2 = new JLabel("     Made by Aadityaraj Rajit     ");
		label2.setFont(font);
		label2.setOpaque(true);
		label2.setBackground(Color.RED);
		label2.setForeground(Color.WHITE);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(label1);
        panel.add(label2);
        panel.add(urk);
	    panel.add(urktxt);
	    panel.add(password);
	    panel.add(passwordtxt);
	    panel.add(next);
	    frame.add(panel);
	    
	    final JFrame mainframe = frame;
	    
	    next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	String urktxt_ = urktxt.getText();
                String password_ = passwordtxt.getText();
            	String url = "jdbc:postgresql://localhost:1818/hostel";
        		String sql = "SELECT reg_no,password FROM students";
        		String username = "postgres";
        		String password = "52411";
        		try {
        			Connection conn = DriverManager.getConnection(url, username, password);//connects to database
        			Statement stmt = conn.createStatement();//allows to send queries
                    ResultSet rs = stmt.executeQuery(sql);//allows to execute queries
                    boolean found = false;
                    while (rs.next()) {
                        String reg = rs.getString("reg_no");          
                        String pass = rs.getString("password");
                        if (urktxt_.equals(reg) && password_.equals(pass)){
                        	found = true;
							break;
                        	
                        }else {
							continue;
                        }
                        
                        
                    }
					if (found == true){
						switchWindow(mainframe, urktxt_);

					}else{
						JOptionPane.showMessageDialog(mainframe, "Invalid");

					}
                    rs.close();
                    stmt.close();
                    conn.close();
                    
        		}catch(Exception e) {
        			System.out.println(e.getMessage());
        		
                switchWindow(mainframe, urktxt_);
        		}
            }
        });
	    frame.revalidate();  
        frame.repaint();     
	    frame.setVisible(true);
	}
	public static void roomwindow1(JFrame frame, String room, String urktxt_){
		frame.getContentPane().removeAll();
	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		
		JLabel banner = new JLabel("Room: "+room, SwingConstants.CENTER);
	    banner.setFont(font);
	    banner.setBackground(Color.GREEN);
	    banner.setForeground(Color.WHITE);
	    banner.setOpaque(true); 
	    banner.setPreferredSize(new Dimension(frame.getWidth(), 50)); 

		String st1 = ""; 

		String url = "jdbc:postgresql://localhost:1818/hostel";
		String sql = "SELECT id, st1,rooms FROM \"one_seater_rooms\" WHERE rooms = '"+room+"'";
		String username = "postgres";
		String password = "52411";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);//connects to database
			Statement stmt = conn.createStatement();//allows to send queries
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                st1 = rs.getString("st1");

                

            }
            rs.close();
            stmt.close();
            conn.close();
		}catch(Exception e) {
			 System.out.println(e.getMessage());
		}
		
		
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new GridLayout(5, 2, 10, 10));
		
		JLabel student1 = new JLabel("Student 1: ", SwingConstants.CENTER);
		student1.setFont(font);
		student1.setBackground(Color.LIGHT_GRAY);
		student1.setOpaque(true); 
		mainpanel.add(student1); 
		
		JLabel studenturk1 = new JLabel(st1, SwingConstants.CENTER);
		studenturk1.setFont(font);
		studenturk1.setBackground(Color.LIGHT_GRAY);
		studenturk1.setOpaque(true); 
		mainpanel.add(studenturk1); 
		

		
		JButton select = new JButton("Select room");
		select.setFont(font);
		select.setOpaque(true);
		select.setFont(font);
		mainpanel.add(select); 
		
		final String stu1 = st1; 
		
		
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                try {
					String url = "jdbc:postgresql://localhost:1818/hostel";
					String username = "postgres";
					String password = "52411";
					try{
						boolean present = false;
						Connection conn = DriverManager.getConnection(url, username, password);//connects to database
						String sql = "SELECT urk FROM occupied";
						
						Statement stmt = conn.createStatement();//allows to send queries
            			ResultSet rs = stmt.executeQuery(sql);
						while (rs.next()) {
							String urk = rs.getString("urk");
							if (urktxt_.equals(urk)){
								present = true;
								System.out.println("occ present");
								break;

							}
			
						}
						if (present==false){
							System.out.println("[present = false]");
							if (!urktxt_.equals(stu1)) {
								System.out.println(stu1);
								System.out.println(urktxt_);
								if(stu1==null) {
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot1");
									sql = "UPDATE \"one_seater_rooms\" SET st1 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								
								}else{
									JOptionPane.showMessageDialog(frame, "This room is full.");
								}
							}else{
								JOptionPane.showMessageDialog(frame, "Room number "+room+" has already been selected by "+urktxt_+" previously.");
							}
		


						}else{
							System.out.println("already present in occupied table");
							JOptionPane.showMessageDialog(frame, "Student ("+urktxt_+") has already selected a room previously.");
						}
						rs.close();
						stmt.close();
						conn.close();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
            
                    
                 
	            }catch(Exception e){
                    System.out.println(e.getMessage());
            }	
				roomwindow1(frame, room, urktxt_);   
        }
	});
		
		JButton back = new JButton("Home");
		back.setFont(font);
		back.setOpaque(true);
		back.setFont(font);
		mainpanel.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				switchWindow(frame, urktxt_);
			}
		});

		
		frame.setLayout(new BorderLayout());
	    frame.add(mainpanel, BorderLayout.CENTER);
	    frame.add(banner, BorderLayout.NORTH);
		frame.revalidate(); 
        frame.repaint();    
        frame.setVisible(true);
	}
	public static void roomwindow2(JFrame frame,String room, String urktxt_){
		frame.getContentPane().removeAll();
	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		
		JLabel banner = new JLabel("Room: "+room, SwingConstants.CENTER);
	    banner.setFont(font);
	    banner.setBackground(Color.GREEN);
	    banner.setForeground(Color.WHITE);
	    banner.setOpaque(true);
	    banner.setPreferredSize(new Dimension(frame.getWidth(), 50)); 
		String st1 = ""; 
	    String st2 = "";

		String url = "jdbc:postgresql://localhost:1818/hostel";
		String sql = "SELECT id, st1, st2,rooms FROM \"two_seater_rooms\" WHERE rooms = '"+room+"'";
		String username = "postgres";
		String password = "52411";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);//connects to database
			Statement stmt = conn.createStatement();//allows to send queries
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                st1 = rs.getString("st1");
                st2 = rs.getString("st2");

                

            }
            rs.close();
            stmt.close();
            conn.close();
		}catch(Exception e) {
			 System.out.println(e.getMessage());
		}
		
		
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new GridLayout(5, 2, 10, 10));
		
		JLabel student1 = new JLabel("Student 1: ", SwingConstants.CENTER);
		student1.setFont(font);
		student1.setBackground(Color.LIGHT_GRAY);
		student1.setOpaque(true); 
		mainpanel.add(student1); 
		
		JLabel studenturk1 = new JLabel(st1, SwingConstants.CENTER);
		studenturk1.setFont(font);
		studenturk1.setBackground(Color.LIGHT_GRAY);
		studenturk1.setOpaque(true); 
		mainpanel.add(studenturk1); 
		
		
		JLabel student2 = new JLabel("Student 2: ", SwingConstants.CENTER);
		student2.setFont(font);
		student2.setBackground(Color.LIGHT_GRAY);
		student2.setOpaque(true); 
		mainpanel.add(student2); 
		
		JLabel studenturk2 = new JLabel(st2, SwingConstants.CENTER);
		studenturk2.setFont(font);
		studenturk2.setBackground(Color.LIGHT_GRAY);
		studenturk2.setOpaque(true); 
		mainpanel.add(studenturk2); 

		
		JButton select = new JButton("Select room");
		select.setFont(font);
		select.setOpaque(true);
		select.setFont(font);
		mainpanel.add(select); 
		
		final String stu1 = st1; 
		final String stu2 = st2; 
		
		
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                try {
					String url = "jdbc:postgresql://localhost:1818/hostel";
					String username = "postgres";
					String password = "52411";
					try{
						boolean present = false;
						Connection conn = DriverManager.getConnection(url, username, password);//connects to database
						String sql = "SELECT urk FROM occupied";
						
						Statement stmt = conn.createStatement();//allows to send queries
            			ResultSet rs = stmt.executeQuery(sql);
						while (rs.next()) {
							String urk = rs.getString("urk");
							if (urktxt_.equals(urk)){
								present = true;
								System.out.println("occ present");
								break;

							}
			
						}
						if (present==false){
							System.out.println("[present = false]");

							if (!urktxt_.equals(stu1) && !urktxt_.equals(stu2)) {
								System.out.println(stu1);
								System.out.println(urktxt_);
								if(stu1==null) {
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot1");
									sql = "UPDATE \"two_seater_rooms\" SET st1 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								}else if(stu2 == null){
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot2");
									sql = "UPDATE \"two_seater_rooms\" SET st2 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								
								}else{
									JOptionPane.showMessageDialog(frame, "This room is full.");
								}
							}else{
								JOptionPane.showMessageDialog(frame, "Room number "+room+" has already been selected by "+urktxt_+" previously.");
							}
		


						}else{
							System.out.println("already present in occupied table");
							JOptionPane.showMessageDialog(frame, "Student ("+urktxt_+") has already selected a room previously.");
						}
						rs.close();
						stmt.close();
						conn.close();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
            
                    
                 
	            }catch(Exception e){
                    System.out.println(e.getMessage());
            }	
				roomwindow2(frame, room, urktxt_);   
        }
	});
		
		JButton back = new JButton("Home");
		back.setFont(font);
		back.setOpaque(true);
		back.setFont(font);
		mainpanel.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				switchWindow(frame, urktxt_);
			}
		});

		
		frame.setLayout(new BorderLayout());
	    frame.add(mainpanel, BorderLayout.CENTER);
	    frame.add(banner, BorderLayout.NORTH);
		frame.revalidate(); 
        frame.repaint();    
        frame.setVisible(true);
	}
	public static void roomwindow3(JFrame frame, String room, String urktxt_){
		frame.getContentPane().removeAll();
	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		
		JLabel banner = new JLabel("Room: "+room, SwingConstants.CENTER);
	    banner.setFont(font);
	    banner.setBackground(Color.GREEN);
	    banner.setForeground(Color.WHITE);
	    banner.setOpaque(true); 
	    banner.setPreferredSize(new Dimension(frame.getWidth(), 50));

		String st1 = ""; 
	    String st2 = "";
	    String st3 = "";

		String url = "jdbc:postgresql://localhost:1818/hostel";
		String sql = "SELECT id, st1, st2, st3,rooms FROM \"three_seater_rooms\" WHERE rooms = '"+room+"'";
		String username = "postgres";
		String password = "52411";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);//connects to database
			Statement stmt = conn.createStatement();//allows to send queries
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                st1 = rs.getString("st1");
                st2 = rs.getString("st2");
                st3 = rs.getString("st3");

                

            }
            rs.close();
            stmt.close();
            conn.close();
		}catch(Exception e) {
			 System.out.println(e.getMessage());
		}
		
		
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new GridLayout(5, 2, 10, 10));
		
		JLabel student1 = new JLabel("Student 1: ", SwingConstants.CENTER);
		student1.setFont(font);
		student1.setBackground(Color.LIGHT_GRAY);
		student1.setOpaque(true); 
		mainpanel.add(student1); 
		
		JLabel studenturk1 = new JLabel(st1, SwingConstants.CENTER);
		studenturk1.setFont(font);
		studenturk1.setBackground(Color.LIGHT_GRAY);
		studenturk1.setOpaque(true); 
		mainpanel.add(studenturk1); 
		
		
		JLabel student2 = new JLabel("Student 2: ", SwingConstants.CENTER);
		student2.setFont(font);
		student2.setBackground(Color.LIGHT_GRAY);
		student2.setOpaque(true); 
		mainpanel.add(student2); 
		
		JLabel studenturk2 = new JLabel(st2, SwingConstants.CENTER);
		studenturk2.setFont(font);
		studenturk2.setBackground(Color.LIGHT_GRAY);
		studenturk2.setOpaque(true); 
		mainpanel.add(studenturk2); 
		
		JLabel student3 = new JLabel("Student 3: ", SwingConstants.CENTER);
		student3.setFont(font);
		student3.setBackground(Color.LIGHT_GRAY);
		student3.setOpaque(true); 
		mainpanel.add(student3); 
		
		JLabel studenturk3 = new JLabel(st3, SwingConstants.CENTER);
		studenturk3.setFont(font);
		studenturk3.setBackground(Color.LIGHT_GRAY);
		studenturk3.setOpaque(true); 
		mainpanel.add(studenturk3); 
		
		
		JButton select = new JButton("Select room");
		select.setFont(font);
		select.setOpaque(true);
		select.setFont(font);
		mainpanel.add(select); 
		
		final String stu1 = st1; 
		final String stu2 = st2; 
		final String stu3 = st3; 
		
		
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                try {
					String url = "jdbc:postgresql://localhost:1818/hostel";
					String username = "postgres";
					String password = "52411";
					try{
						boolean present = false;
						Connection conn = DriverManager.getConnection(url, username, password);//connects to database
						String sql = "SELECT urk FROM occupied";
						
						Statement stmt = conn.createStatement();//allows to send queries
            			ResultSet rs = stmt.executeQuery(sql);
						while (rs.next()) {
							String urk = rs.getString("urk");
							if (urktxt_.equals(urk)){
								present = true;
								System.out.println("occ present");
								break;

							}
			
						}
						if (present==false){
							System.out.println("[present = false]");

							if (!urktxt_.equals(stu1) && !urktxt_.equals(stu2) && !urktxt_.equals(stu3)) {
								System.out.println(stu1);
								System.out.println(urktxt_);
								if(stu1==null) {
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot1");
									sql = "UPDATE \"three_seater_rooms\" SET st1 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								}else if(stu2 == null){
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot2");
									sql = "UPDATE \"three_seater_rooms\" SET st2 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								}else if(stu3 == null){
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot3");
									sql = "UPDATE \"three_seater_rooms\" SET st3 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								}else{
									JOptionPane.showMessageDialog(frame, "This room is full.");
								}
							}else{
								JOptionPane.showMessageDialog(frame, "Room number "+room+" has already been selected by "+urktxt_+" previously.");
							}
		


						}else{
							System.out.println("already present in occupied table");
							JOptionPane.showMessageDialog(frame, "Student ("+urktxt_+") has already selected a room previously.");
						}
						rs.close();
						stmt.close();
						conn.close();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
            
                    
                 
	            }catch(Exception e){
                    System.out.println(e.getMessage());
            }	
				roomwindow3(frame, room, urktxt_);   
        }
	});
		
		JButton back = new JButton("Home");
		back.setFont(font);
		back.setOpaque(true);
		back.setFont(font);
		mainpanel.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				switchWindow(frame, urktxt_);
			}
		});

		
		frame.setLayout(new BorderLayout());
	    frame.add(mainpanel, BorderLayout.CENTER);
	    frame.add(banner, BorderLayout.NORTH);
		frame.revalidate(); 
        frame.repaint();    
        frame.setVisible(true); 
	}
	public static void roomwindow4(JFrame frame, String room, String urktxt_) {
		frame.getContentPane().removeAll();
	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		
		JLabel banner = new JLabel("Room: "+room, SwingConstants.CENTER);
	    banner.setFont(font);
	    banner.setBackground(Color.GREEN);
	    banner.setForeground(Color.WHITE);
	    banner.setOpaque(true);
	    banner.setPreferredSize(new Dimension(frame.getWidth(), 50)); 

		String st1 = ""; 
	    String st2 = "";
	    String st3 = "";
	    String st4 = "";

		String url = "jdbc:postgresql://localhost:1818/hostel";
		String sql = "SELECT id, st1, st2, st3, st4, rooms FROM \"four_seater_rooms\" WHERE rooms = '"+room+"'";
		String username = "postgres";
		String password = "52411";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);//connects to database
			Statement stmt = conn.createStatement();//allows to send queries
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                st1 = rs.getString("st1");
                st2 = rs.getString("st2");
                st3 = rs.getString("st3");
                st4 = rs.getString("st4");

                

            }
            rs.close();
            stmt.close();
            conn.close();
		}catch(Exception e) {
			 System.out.println(e.getMessage());
		}
		
		
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new GridLayout(5, 2, 10, 10));
		
		JLabel student1 = new JLabel("Student 1: ", SwingConstants.CENTER);
		student1.setFont(font);
		student1.setBackground(Color.LIGHT_GRAY);
		student1.setOpaque(true); 
		mainpanel.add(student1); 
		
		JLabel studenturk1 = new JLabel(st1, SwingConstants.CENTER);
		studenturk1.setFont(font);
		studenturk1.setBackground(Color.LIGHT_GRAY);
		studenturk1.setOpaque(true); 
		mainpanel.add(studenturk1); 
		
		
		JLabel student2 = new JLabel("Student 2: ", SwingConstants.CENTER);
		student2.setFont(font);
		student2.setBackground(Color.LIGHT_GRAY);
		student2.setOpaque(true); 
		mainpanel.add(student2); 
		
		JLabel studenturk2 = new JLabel(st2, SwingConstants.CENTER);
		studenturk2.setFont(font);
		studenturk2.setBackground(Color.LIGHT_GRAY);
		studenturk2.setOpaque(true); 
		mainpanel.add(studenturk2); 
		
		JLabel student3 = new JLabel("Student 3: ", SwingConstants.CENTER);
		student3.setFont(font);
		student3.setBackground(Color.LIGHT_GRAY);
		student3.setOpaque(true); 
		mainpanel.add(student3); 
		
		JLabel studenturk3 = new JLabel(st3, SwingConstants.CENTER);
		studenturk3.setFont(font);
		studenturk3.setBackground(Color.LIGHT_GRAY);
		studenturk3.setOpaque(true); 
		mainpanel.add(studenturk3); 
		
		JLabel student4 = new JLabel("Student 4: ", SwingConstants.CENTER);
		student4.setFont(font);
		student4.setBackground(Color.LIGHT_GRAY);
		student4.setOpaque(true); 
		mainpanel.add(student4); 
		
		
		JLabel studenturk4 = new JLabel(st4, SwingConstants.CENTER);
		studenturk4.setFont(font);
		studenturk4.setBackground(Color.LIGHT_GRAY);
		studenturk4.setOpaque(true); 
		mainpanel.add(studenturk4); 
		
		JButton select = new JButton("Select room");
		select.setFont(font);
		select.setOpaque(true);
		select.setFont(font);
		mainpanel.add(select); 
		
		final String stu1 = st1; 
		final String stu2 = st2; 
		final String stu3 = st3; 
		final String stu4 = st4; 
		
		
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                try {
					String url = "jdbc:postgresql://localhost:1818/hostel";
					String username = "postgres";
					String password = "52411";
					try{
						boolean present = false;
						Connection conn = DriverManager.getConnection(url, username, password);//connects to database
						String sql = "SELECT urk FROM occupied";
						
						Statement stmt = conn.createStatement();//allows to send queries
            			ResultSet rs = stmt.executeQuery(sql);
						while (rs.next()) {
							String urk = rs.getString("urk");
							if (urktxt_.equals(urk)){
								present = true;
								System.out.println("occ present");
								break;

							}
			
						}
						if (present==false){
							System.out.println("[present = false]");
							
							if (!urktxt_.equals(stu1) && !urktxt_.equals(stu2) && !urktxt_.equals(stu3) && !urktxt_.equals(stu4)) {
								System.out.println(stu1);
								System.out.println(urktxt_);
								if(stu1==null) {
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot1");
									sql = "UPDATE \"four_seater_rooms\" SET st1 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								}else if(stu2 == null){
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot2");
									sql = "UPDATE \"four_seater_rooms\" SET st2 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								}else if(stu3 == null){
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot3");
									sql = "UPDATE \"four_seater_rooms\" SET st3 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								}else if(stu4 == null){
									sql = "INSERT INTO occupied (urk) VALUES ('"+urktxt_+"')";
									stmt = conn.createStatement();//allows to send queries
									stmt.executeUpdate(sql);
									System.out.println("Free spot4");
									sql = "UPDATE \"four_seater_rooms\" SET st4 = '" + urktxt_ + "' WHERE rooms = '" + room + "'";
									stmt = conn.createStatement();
									stmt.executeUpdate(sql);
									JOptionPane.showMessageDialog(frame, "Room number "+room+" has been booked for "+urktxt_+" successfully.");

								}else{
									JOptionPane.showMessageDialog(frame, "This room is full.");
								}
							}else{
								JOptionPane.showMessageDialog(frame, "Room number "+room+" has already been selected by "+urktxt_+" previously.");
							}
		


						}else{
							System.out.println("already present in occupied table");
							JOptionPane.showMessageDialog(frame, "Student ("+urktxt_+") has already selected a room previously.");
						}
						rs.close();
						stmt.close();
						conn.close();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
            
                    
                 
	            }catch(Exception e){
                    System.out.println(e.getMessage());
            }	
				roomwindow4(frame, room, urktxt_);   
        }
	});
		
		JButton back = new JButton("Home");
		back.setFont(font);
		back.setOpaque(true);
		back.setFont(font);
		mainpanel.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				switchWindow(frame, urktxt_);
			}
		});

		
		frame.setLayout(new BorderLayout());
	    frame.add(mainpanel, BorderLayout.CENTER);
	    frame.add(banner, BorderLayout.NORTH);
		frame.revalidate(); 
        frame.repaint();    
        frame.setVisible(true); 

	}
	public static void switch4seater(JFrame frame, String urktxt_) {
		frame.getContentPane().removeAll();
	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		
		
		JLabel headerLabel = new JLabel("4-Seater Rooms", SwingConstants.CENTER);
	    headerLabel.setFont(font);
	    headerLabel.setBackground(Color.WHITE);  
	    headerLabel.setForeground(Color.BLACK); 
	    headerLabel.setOpaque(true);            
	    headerLabel.setPreferredSize(new Dimension(frame.getWidth(), 50)); 
	    
	    
		JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(2, 2, 10, 10)); 

	    
		String url = "jdbc:postgresql://localhost:1818/hostel";
		String sql = "SELECT id,st1,st2,st3,st4,rooms FROM \"four_seater_rooms\"";
		String username = "postgres";
		String password = "52411";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);//connects to database
			Statement stmt = conn.createStatement();//allows to send queries
            ResultSet rs = stmt.executeQuery(sql);
    	    final JFrame mainframe = frame;
            while (rs.next()) {
                final String room = rs.getString("rooms");
                String st1 = rs.getString("st1");
                String st2 = rs.getString("st2");
                String st3 = rs.getString("st3");
                String st4 = rs.getString("st4");
				int nullvals = 0;
				String ar[] = {st1, st2, st3, st4};
				for (String st:ar){
					if (st == null){
						nullvals+=1;
					}
				}
				if (nullvals>=1 && nullvals<=4){
					StringBuilder icons = new StringBuilder();
					for (int i = 0; i < nullvals; i++) {
						icons.append("<span style='font-size:20px; color:green;'>&#9679;</span>");  
					}
					for (int i = 0; i < (4 - nullvals); i++) {
						icons.append("<span style='font-size:20px; color:red;'>&#9679;</span>");    
					}
					
					JButton button = new JButton(
						"<html>" 
						+ "<div style='text-align:center;'>" 
						+ room 
						+ "<br><span style='font-size:10px; color:green;'><b>" 
						+ nullvals 
						+ " spots available</b></span><br>" 
						+ "<div style='font-size:20px;'>" 
						+ icons.toString() 
						+ "</div>"
						+ "</div>" 
						+ "</html>"
					);
									
					button.setFont(font);
					button.setBackground(Color.yellow);
					button.setFocusable(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Room " + room + " selected");
							roomwindow4(mainframe, room, urktxt_);
							
						}
					});
					buttonPanel.add(button);
				}else{
					JButton button = new JButton("<html><div style='text-align: center;'>" + room + "<br><span style='font-size:12px; color: white;'><b>" + nullvals + " spots available</b></span></div></html>");
                	button.setFont(font);
        	    	button.setBackground(Color.red);
        	    	button.setForeground(Color.white);
        	    	button.setFocusable(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Room " + room + " selected");
							roomwindow4(mainframe, room, urktxt_);
							
						}
					});
					buttonPanel.add(button);
				}

            }
            rs.close();
            stmt.close();
            conn.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		frame.setLayout(new BorderLayout());
		frame.add(headerLabel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);
		
		JButton prev = new JButton("Back"); 
		prev.setFont(font);
        prev.setBackground(Color.BLACK);
        prev.setForeground(Color.WHITE);
        prev.setFocusable(false);
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				switchWindow(frame, urktxt_);
			}
		});
		frame.add(prev, BorderLayout.SOUTH);
		
		frame.revalidate(); 
        frame.repaint();    
        frame.setVisible(true); 
	}
	public static void switch1seater(JFrame frame, String urktxt_){
		frame.getContentPane().removeAll();
	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		
		
		JLabel headerLabel = new JLabel("1-Seater Rooms", SwingConstants.CENTER);
	    headerLabel.setFont(font);
	    headerLabel.setBackground(Color.WHITE);  
	    headerLabel.setForeground(Color.BLACK); 
	    headerLabel.setOpaque(true);            
	    headerLabel.setPreferredSize(new Dimension(frame.getWidth(), 50)); 
	    
	    
		JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(2, 2, 10, 10)); 

	    
		String url = "jdbc:postgresql://localhost:1818/hostel";
		String sql = "SELECT id,st1,rooms FROM \"one_seater_rooms\"";
		String username = "postgres";
		String password = "52411";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);//connects to database
			Statement stmt = conn.createStatement();//allows to send queries
            ResultSet rs = stmt.executeQuery(sql);
    	    final JFrame mainframe = frame;
            while (rs.next()) {
                final String room = rs.getString("rooms");
                String st1 = rs.getString("st1");
				int nullvals = 0;
				String ar[] = {st1};
				for (String st:ar){
					if (st == null){
						nullvals+=1;
					}
				}
				if (nullvals>=1 && nullvals<=1){
					StringBuilder icons = new StringBuilder();
					for (int i = 0; i < nullvals; i++) {
						icons.append("<span style='font-size:20px; color:green;'>&#9679;</span>");  
					}
					for (int i = 0; i < (1 - nullvals); i++) {
						icons.append("<span style='font-size:20px; color:red;'>&#9679;</span>");  
					}
					
					JButton button = new JButton(
						"<html>" 
						+ "<div style='text-align:center;'>" 
						+ room 
						+ "<br><span style='font-size:10px; color:green;'><b>" 
						+ nullvals 
						+ " spots available</b></span><br>" 
						+ "<div style='font-size:20px;'>"  
						+ icons.toString() 
						+ "</div>"
						+ "</div>" 
						+ "</html>"
					);
									
					button.setFont(font);
					button.setBackground(Color.yellow);
					button.setFocusable(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Room " + room + " selected");
							roomwindow1(mainframe, room, urktxt_);
							
						}
					});
					buttonPanel.add(button);
				}else{
					JButton button = new JButton("<html><div style='text-align: center;'>" + room + "<br><span style='font-size:12px; color: white;'><b>" + nullvals + " spots available</b></span></div></html>");
                	button.setFont(font);
        	    	button.setBackground(Color.red);
        	    	button.setForeground(Color.white);
        	    	button.setFocusable(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Room " + room + " selected");
							roomwindow1(mainframe, room, urktxt_);
							
						}
					});
					buttonPanel.add(button);
				}

            }
            rs.close();
            stmt.close();
            conn.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}		
		frame.setLayout(new BorderLayout());
		frame.add(headerLabel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER); 
		JButton prev = new JButton("Back"); 
		prev.setFont(font);
        prev.setBackground(Color.BLACK);
        prev.setForeground(Color.WHITE);
        prev.setFocusable(false);
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				switchWindow(frame, urktxt_);
			}
		});
		frame.add(prev, BorderLayout.SOUTH);
		frame.revalidate(); 
        frame.repaint();    
        frame.setVisible(true);
	}
	public static void switch2seater(JFrame frame, String urktxt_){
		frame.getContentPane().removeAll();
	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		
		
		JLabel headerLabel = new JLabel("2-Seater Rooms", SwingConstants.CENTER);
	    headerLabel.setFont(font);
	    headerLabel.setBackground(Color.WHITE);  
	    headerLabel.setForeground(Color.BLACK); 
	    headerLabel.setOpaque(true);            
	    headerLabel.setPreferredSize(new Dimension(frame.getWidth(), 50)); 
	    
	    
		JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));  

	    
		String url = "jdbc:postgresql://localhost:1818/hostel";
		String sql = "SELECT id,st1,st2,rooms FROM \"two_seater_rooms\"";
		String username = "postgres";
		String password = "52411";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);//connects to database
			Statement stmt = conn.createStatement();//allows to send queries
            ResultSet rs = stmt.executeQuery(sql);
    	    final JFrame mainframe = frame;
            while (rs.next()) {
                final String room = rs.getString("rooms");
                String st1 = rs.getString("st1");
                String st2 = rs.getString("st2");
				int nullvals = 0;
				String ar[] = {st1, st2};
				for (String st:ar){
					if (st == null){
						nullvals+=1;
					}
				}
				if (nullvals>=1 && nullvals<=2){
					StringBuilder icons = new StringBuilder();
					for (int i = 0; i < nullvals; i++) {
						icons.append("<span style='font-size:20px; color:green;'>&#9679;</span>");  
					}
					for (int i = 0; i < (2 - nullvals); i++) {
						icons.append("<span style='font-size:20px; color:red;'>&#9679;</span>");   
					}
					
					JButton button = new JButton(
						"<html>" 
						+ "<div style='text-align:center;'>"  
						+ room 
						+ "<br><span style='font-size:10px; color:green;'><b>" 
						+ nullvals 
						+ " spots available</b></span><br>" 
						+ "<div style='font-size:20px;'>"  
						+ icons.toString() 
						+ "</div>"
						+ "</div>" 
						+ "</html>"
					);
									
					button.setFont(font);
					button.setBackground(Color.yellow);
					button.setFocusable(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Room " + room + " selected");
							roomwindow3(mainframe, room, urktxt_);
							
						}
					});
					buttonPanel.add(button);
				}else{
					JButton button = new JButton("<html><div style='text-align: center;'>" + room + "<br><span style='font-size:12px; color: white;'><b>" + nullvals + " spots available</b></span></div></html>");
                	button.setFont(font);
        	    	button.setBackground(Color.red);
        	    	button.setForeground(Color.white);
        	    	button.setFocusable(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Room " + room + " selected");
							roomwindow3(mainframe, room, urktxt_);
							
						}
					});
					buttonPanel.add(button);
				}

            }
            rs.close();
            stmt.close();
            conn.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}		
		frame.setLayout(new BorderLayout());
		frame.add(headerLabel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);  
		JButton prev = new JButton("Back");  
		prev.setFont(font);
        prev.setBackground(Color.BLACK);
        prev.setForeground(Color.WHITE);
        prev.setFocusable(false);
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				switchWindow(frame, urktxt_);
			}
		});
		frame.add(prev, BorderLayout.SOUTH);
		frame.revalidate();  
        frame.repaint();     
        frame.setVisible(true); 
	}
	public static void switch3seater(JFrame frame, String urktxt_){
		frame.getContentPane().removeAll();
	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		
		
		JLabel headerLabel = new JLabel("3-Seater Rooms", SwingConstants.CENTER);
	    headerLabel.setFont(font);
	    headerLabel.setBackground(Color.WHITE);  
	    headerLabel.setForeground(Color.BLACK); 
	    headerLabel.setOpaque(true);            
	    headerLabel.setPreferredSize(new Dimension(frame.getWidth(), 50)); 
	    
	    
		JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));  

	    
		String url = "jdbc:postgresql://localhost:1818/hostel";
		String sql = "SELECT id,st1,st2,st3,rooms FROM \"three_seater_rooms\"";
		String username = "postgres";
		String password = "52411";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);//connects to database
			Statement stmt = conn.createStatement();//allows to send queries
            ResultSet rs = stmt.executeQuery(sql);
    	    final JFrame mainframe = frame;
            while (rs.next()) {
                final String room = rs.getString("rooms");
                String st1 = rs.getString("st1");
                String st2 = rs.getString("st2");
                String st3 = rs.getString("st3");
				int nullvals = 0;
				String ar[] = {st1, st2, st3};
				for (String st:ar){
					if (st == null){
						nullvals+=1;
					}
				}
				if (nullvals>=1 && nullvals<=3){
					StringBuilder icons = new StringBuilder();
					for (int i = 0; i < nullvals; i++) {
						icons.append("<span style='font-size:20px; color:green;'>&#9679;</span>");  
					}
					for (int i = 0; i < (3 - nullvals); i++) {
						icons.append("<span style='font-size:20px; color:red;'>&#9679;</span>");    
					}
					
					JButton button = new JButton(
						"<html>" 
						+ "<div style='text-align:center;'>"  
						+ room 
						+ "<br><span style='font-size:10px; color:green;'><b>" 
						+ nullvals 
						+ " spots available</b></span><br>" 
						+ "<div style='font-size:20px;'>"  
						+ icons.toString()  
						+ "</div>"
						+ "</div>" 
						+ "</html>"
					);
									
					button.setFont(font);
					button.setBackground(Color.yellow);
					button.setFocusable(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Room " + room + " selected");
							roomwindow3(mainframe, room, urktxt_);
							
						}
					});
					buttonPanel.add(button);
				}else{
					JButton button = new JButton("<html><div style='text-align: center;'>" + room + "<br><span style='font-size:12px; color: white;'><b>" + nullvals + " spots available</b></span></div></html>");
                	button.setFont(font);
        	    	button.setBackground(Color.red);
        	    	button.setForeground(Color.white);
        	    	button.setFocusable(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Room " + room + " selected");
							roomwindow3(mainframe, room, urktxt_);
							
						}
					});
					buttonPanel.add(button);
				}

            }
            rs.close();
            stmt.close();
            conn.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		frame.setLayout(new BorderLayout());
		frame.add(headerLabel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);  
		JButton prev = new JButton("Back");  
		prev.setFont(font);
        prev.setBackground(Color.BLACK);
        prev.setForeground(Color.WHITE);
        prev.setFocusable(false);
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				switchWindow(frame, urktxt_);
			}
		});
		frame.add(prev, BorderLayout.SOUTH);
		frame.revalidate();  
        frame.repaint();     
        frame.setVisible(true); 
	}
	
	
	public static void switchWindow(JFrame frame, String urktxt_) {
    	frame.getContentPane().removeAll();  

	    Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
    	int width = frame.getWidth() - 100; 

    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BorderLayout()); 
    	mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 

    	JLabel banner = new JLabel("Logged in!", SwingConstants.CENTER);
    	banner.setFont(font);
    	banner.setBackground(Color.GREEN);
    	banner.setForeground(Color.WHITE);
    	banner.setOpaque(true); 
    	banner.setPreferredSize(new Dimension(width, 50)); 

    	JLabel choose = new JLabel("Choose room type: ", SwingConstants.CENTER);
    	choose.setFont(font);
    	choose.setForeground(Color.BLACK);
    	choose.setOpaque(true);
    	choose.setPreferredSize(new Dimension(width, 50)); 

    	mainPanel.add(banner, BorderLayout.NORTH); 
    	mainPanel.add(choose, BorderLayout.CENTER); 

    	JPanel buttonPanel = new JPanel();
    	buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 

    	JButton button1 = new JButton("1 seater");
    	JButton button2 = new JButton("2 seater");
    	JButton button3 = new JButton("3 seater");
    	JButton button4 = new JButton("4 seater");

    	button1.setFont(font);
    	button2.setFont(font);
    	button3.setFont(font);
    	button4.setFont(font);

    	button1.setBackground(Color.YELLOW);
    	button2.setBackground(Color.YELLOW);
    	button3.setBackground(Color.YELLOW);
    	button4.setBackground(Color.YELLOW);

    	Dimension buttonSize = new Dimension(frame.getWidth() / 5, 450);
    	button1.setPreferredSize(buttonSize);
    	button2.setPreferredSize(buttonSize);
    	button3.setPreferredSize(buttonSize);
    	button4.setPreferredSize(buttonSize);

    	buttonPanel.add(button1);
    	buttonPanel.add(button2);
    	buttonPanel.add(button3);
    	buttonPanel.add(button4);

    	JPanel logoutPanel = new JPanel();
    	logoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 

    	JButton logout = new JButton("Logout");
    	logout.setFont(font);
    	logout.setForeground(Color.WHITE);
    	logout.setBackground(Color.RED);
    	logout.setOpaque(true);
    	logout.setFocusable(false);

    	logoutPanel.add(logout); 

		logout.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        System.out.println("Logged out");
				frame.dispose(); 
				main(null);

    	    }
    	});

    	button1.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        System.out.println("1 seater selected");
    	        switch1seater(frame, urktxt_);
    	    }
    	});

    	button2.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        System.out.println("2 seater selected");
    	        switch2seater(frame, urktxt_);
    	    }
    	});

    	button3.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        System.out.println("3 seater selected");
    	        switch3seater(frame, urktxt_);
    	    }
    	});

    	button4.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        System.out.println("4 seater selected");
    	        switch4seater(frame, urktxt_);
    	    }
    	});

    	frame.add(mainPanel, BorderLayout.NORTH); 
    	frame.add(buttonPanel, BorderLayout.CENTER); 
    	frame.add(logoutPanel, BorderLayout.SOUTH); 

    	frame.revalidate();
    	frame.repaint();
    	frame.setVisible(true);
		}	

	}