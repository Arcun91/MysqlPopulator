package it.arcun.mysqlpopulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Panel(){
		
		JFrame frame = new JFrame();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		frame.setTitle("MysqlPopulator");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(350, 200);
		frame.setResizable(false);
		JPanel content = new JPanel(new FlowLayout());
		JPanel content2 = new JPanel(new BorderLayout());
		JPanel content3 = new JPanel(new BorderLayout());
		JPanel content4 = new JPanel(new BorderLayout());
		JPanel content5 = new JPanel(new BorderLayout());
		JPanel content6 = new JPanel(new BorderLayout());
		JPanel contentc = new JPanel(new BorderLayout());
		JPanel contents = new JPanel(new BorderLayout());
		JPanel contentn = new JPanel(new BorderLayout());
		JPanel contentce = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new BorderLayout());
		JLabel textarea = new JLabel("<html>Benvenuto nell'applicazione che ti permette di creare<br>"
				+ "una tabella chiamata 'Persons' nel Database MySql.<br>"
				+ "Clicca sul tasto avanti per inserire i dati del DB.<br>"
				+ "I campi sono i seguenti:<br>"
				+ "<table align=center><tr>"
				+ "<td>ID</td>"
				+ "<td>Cognome</td>"
				+ "<td>Nome</td>"
				+ "<td>Indirizzo</td>"
				+ "<td>Città</td>"
				+ "</tr></table><br>"
				+ "Se il DB non esiste lo crea.<br>"
				+ "Invece se la tabella esiste già, <br>il programma non la modifica.</html>");
		textarea.setSize(new Dimension(200,40));
		JButton next = new JButton("Avanti");
		JButton next2 = new JButton("Inserisci");
		JButton back = new JButton("Chiudi App");
		JButton back2 = new JButton("Indietro");
		next.setPreferredSize(new Dimension(100,20));
		next2.setPreferredSize(new Dimension(100,20));
		back.setPreferredSize(new Dimension(100,20));
		back2.setPreferredSize(new Dimension(100,20));
		JLabel username = new JLabel("Nome Utente:");
		JTextArea userField = new JTextArea("root");
		JLabel psw = new JLabel("Password:     ");
		JPasswordField passwordField = new JPasswordField(10);
		JLabel Labelport = new JLabel("Porta:            ");
		JTextArea portField = new JTextArea("3306");
		JLabel LabelDB = new JLabel("Nome DB:      ");
		JTextArea DBField = new JTextArea("mysql");
		JLabel version = new JLabel("v1.0.0");
		next.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				content.setVisible(false);
				content2.add(contentn, BorderLayout.NORTH);
				contentn.add(content3, BorderLayout.NORTH);
				contentn.add(content4, BorderLayout.CENTER);
				
				content3.add(username, BorderLayout.WEST);
				content3.add(userField, BorderLayout.CENTER);
				
				content2.add(content4, BorderLayout.CENTER);
				
				content4.add(contentc, BorderLayout.NORTH);
				content4.add(contentce, BorderLayout.CENTER);
				contentce.add(content5, BorderLayout.NORTH);
				contentce.add(content6, BorderLayout.CENTER);
				
				content5.add(LabelDB, BorderLayout.WEST);
				content5.add(DBField, BorderLayout.CENTER);
				contentc.add(psw, BorderLayout.WEST);
				contentc.add(passwordField, BorderLayout.CENTER);
				
				content6.add(contents, BorderLayout.NORTH);
				contents.add(Labelport, BorderLayout.WEST);
				contents.add(portField, BorderLayout.CENTER);
				
				content2.add(version, BorderLayout.SOUTH);
				
				content2.setVisible(true);
				
				frame.add(content2);
				buttons.remove(next);
				buttons.remove(back);
				buttons.add(next2, BorderLayout.EAST);
				buttons.add(back2, BorderLayout.WEST);

				
			}
			
		});
		
		
		
		back.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
			
		});
		
		next2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String username = userField.getText();
				String password = String.copyValueOf(passwordField.getPassword());
				String DB = DBField.getText();
				int port = Integer.parseInt(portField.getText());
				if(username.equals("")||port==0){
					JFrame frameError = new JFrame();
					frameError.setTitle("MysqlPopulator");
					frameError.setSize(150, 150);
					frameError.setLayout(new BorderLayout());
					JLabel errorText = new JLabel("<html><br><br>Errore nei campi<br> inseriti, riprovare</html>", SwingConstants.CENTER);
					JButton okButton = new JButton("OK");
					
					okButton.addActionListener(new ActionListener(){

						public void actionPerformed(ActionEvent e) {
							
							frameError.dispose();
							
						}
						
					});
					frameError.setLocation((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2);
					frameError.add(errorText, BorderLayout.NORTH);
					frameError.add(okButton, BorderLayout.SOUTH);
					frameError.setVisible(true);
				}else{
				
				
				
					try {
						if(Connect.SetConnection(username, password, port)){
							JFrame reject = new JFrame();
							reject.setTitle("MysqlPopulator");
							reject.setSize(250, 200);
							reject.setLayout(new BorderLayout());
							JLabel errorText = new JLabel("<html><br><br>Errore, Database non attivo<br>oppure nome utente e password<br>non validi</html>", SwingConstants.CENTER);
							JButton okButton = new JButton("OK");
							reject.setLocation((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2);
							reject.add(errorText, BorderLayout.NORTH);
							reject.add(okButton, BorderLayout.SOUTH);
							okButton.addActionListener(new ActionListener(){

								public void actionPerformed(ActionEvent e) {
									
									reject.dispose();
									
								}
								
							});
							reject.setVisible(true);
						}else{
							if(Connect.Create(username, password, port, DB)){
								JFrame done = new JFrame();
								done.setTitle("MysqlPopulator");
								done.setSize(250, 200);
								done.setLayout(new BorderLayout());
								JLabel okText = new JLabel("<html><br><br>Operazione Completata!</html>", SwingConstants.CENTER);
								JButton okButton = new JButton("OK");
								done.setLocation((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2);
								done.add(okText, BorderLayout.NORTH);
								done.add(okButton, BorderLayout.SOUTH);
								okButton.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent e) {
										
										done.dispose();
										
									}
									
								});
								done.setVisible(true);
							}else{
								JFrame reject = new JFrame();
								reject.setTitle("MysqlPopulator");
								reject.setSize(250, 200);
								reject.setLayout(new BorderLayout());
								JLabel errorText = new JLabel("<html><br><br>Errore, Tabella già esistente</html>", SwingConstants.CENTER);
								JButton okButton = new JButton("OK");
								reject.setLocation((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2);
								reject.add(errorText, BorderLayout.NORTH);
								reject.add(okButton, BorderLayout.SOUTH);
								okButton.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent e) {
										
										reject.dispose();
										
									}
									
								});
								reject.setVisible(true);
							}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
				}
			}
		});
		
		back2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				content.setVisible(true);
				content2.setVisible(false);
				buttons.remove(next2);
				buttons.remove(back2);
				buttons.add(next, BorderLayout.EAST);
				buttons.add(back, BorderLayout.WEST);
				
			}
			
		});
		content.add(textarea);
		frame.add(content);
		buttons.add(back, BorderLayout.WEST);
		buttons.add(next, BorderLayout.EAST);
		frame.add(buttons, BorderLayout.SOUTH);
		frame.setLocation((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2);
		frame.setVisible(true);
		
	}
}
