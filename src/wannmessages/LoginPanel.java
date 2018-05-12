/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wannmessages;

import com.sun.glass.events.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author DoctorOne
 */
public class LoginPanel extends javax.swing.JPanel implements ActionListener{

	/**
	 * Creates new form LoginPanel
	 */
	private Timer timer;
	private connection con;
	private String loginedUsername;
	private messagePanel mPanel;
	private JFrame messageFrame;
	private LoginFrame lf;
	private String saved;
	public LoginPanel(connection con,LoginFrame lf) {
		this.con = con;
		this.lf = lf;
		initComponents();
		
		try(FileInputStream fis = new FileInputStream("remember.txt")){
			String s = "";
			int i=0;
			while(i != -1){
			i = fis.read();
			if(i == -1) break;
			char c = (char) i;
			s += c;
			}
			String q;
			q = s.trim();
			if(!q.equals("")){
			username.setText(s);
			saved = s;
			rememberMe_.setSelected(true);}
			
		} catch (IOException ex) {
			Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        rememberMe_ = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        setBackground(java.awt.Color.darkGray);
        setForeground(new java.awt.Color(151, 151, 151));

        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
        });

        jButton1.setBackground(java.awt.Color.darkGray);
        jButton1.setForeground(java.awt.Color.white);
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Password");

        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });

        rememberMe_.setBackground(java.awt.Color.darkGray);
        rememberMe_.setForeground(new java.awt.Color(255, 255, 0));
        rememberMe_.setText("Remember me");
        rememberMe_.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rememberMe_KeyPressed(evt);
            }
        });

        jButton2.setBackground(java.awt.Color.darkGray);
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("CHECK VERSION");
        jButton2.setMaximumSize(new java.awt.Dimension(123, 34));
        jButton2.setMinimumSize(new java.awt.Dimension(123, 34));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rememberMe_)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(139, 139, 139)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rememberMe_))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	public void MessagesFrame(){
		if(con.checkUser(username.getText(), pass.getText())){
			
			
			loginedUsername = username.getText();
			rememberWrite();
			pass.setText("");
			messageFrame = new JFrame();
			mPanel = new messagePanel(con,loginedUsername);
			messageFrame.add(mPanel);
			messageFrame.setVisible(true);
			messageFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			messageFrame.setTitle("Welcome "+ loginedUsername +"! ADAMSIN!");
			messageFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/4, Toolkit.getDefaultToolkit().getScreenSize().height/4);
			messageFrame.setResizable(false);
			messageFrame.pack();
			lf.setVisible(false);
			timer = new Timer(100,this);
			timer.start();
			
			
			
			
		}else{
			pass.setText("");
			JOptionPane.showMessageDialog(this, "Error.");
		}
	}
	
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
		MessagesFrame();
		
    }//GEN-LAST:event_jButton1ActionPerformed

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        // TODO add your handling code here:
		if(evt.getKeyCode() == KeyEvent.VK_ENTER){
		
		    MessagesFrame();
		
		}
    }//GEN-LAST:event_usernameKeyPressed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        // TODO add your handling code here:
		if(evt.getKeyCode() == KeyEvent.VK_ENTER)
		    MessagesFrame();
    }//GEN-LAST:event_passKeyPressed

    private void rememberMe_KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rememberMe_KeyPressed
        // TODO add your handling code here:
		if(evt.getKeyCode() == KeyEvent.VK_ENTER)
		    MessagesFrame();
    }//GEN-LAST:event_rememberMe_KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
		if(con.checkVersion()){
			JOptionPane.showMessageDialog(this, "New Version Downloaded. Check the Folder!");
		}else
			JOptionPane.showMessageDialog(this, "No New Version Detected.");
    }//GEN-LAST:event_jButton2ActionPerformed
	public void rememberWrite(){
		if(rememberMe_.isSelected() && !username.getText().equals(saved)){
				try(FileOutputStream fos = new FileOutputStream("remember.txt")){
					byte[] b= username.getText().getBytes();
					
					fos.write(b);
					
				} catch (FileNotFoundException ex) {
					rememberWrite();
					Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
				} 
				
				
			}
			else {
			
			try(FileOutputStream fos = new FileOutputStream("remember.txt")){
					fos.write(' ');
					username.setText("");
				} catch (FileNotFoundException ex) {
					Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
				} 
			
			}
	}
	public void lfVisible(){
		if(!messageFrame.isVisible() || messageFrame == null){
		lf.setVisible(true);
		timer.stop();
		mPanel.stopTimer();
		con.stateUpdate(loginedUsername, 0, false);
		con.updateWriting(loginedUsername, 0);
		mPanel.setExecutor();
		}
	}
	
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField pass;
    private javax.swing.JCheckBox rememberMe_;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables

	@Override
	public void actionPerformed(ActionEvent e) {
		lfVisible();
	}
}