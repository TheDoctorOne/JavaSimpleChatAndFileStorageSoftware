/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wannmessages;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author DoctorOne
 */
public class LoginFrame extends JFrame{
	
	private LoginPanel loginPanel;
	private connection con;
	LoginFrame(connection con) {
		loginPanel = new LoginPanel(con,this);
		
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/5, Toolkit.getDefaultToolkit().getScreenSize().height/5);
		this.add(loginPanel);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("WANN Chat v" + connection.VERSION);
	}
}
