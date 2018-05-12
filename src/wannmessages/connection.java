/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wannmessages;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DoctorOne
 */
public class connection {
	
	public static final double VERSION=1.4;
	
	private String username = "";
	
	private String pass = "";
	
	private String host = "85.10.205.173";
	private String db_name = "";
	private int port = 3307;
	
	private String url;
	
	private Connection con = null;
	private Statement stmt = null;
	
	private messagePanel mPanel;
	private boolean connected=false;
	public void getPanel(messagePanel mp){
		mPanel = mp;
	}
	
	public boolean isConnected(String username,String pass){
		this.username = username;
		this.pass = pass;
		try {
            con = DriverManager.getConnection(url, this.username,this.pass);
			connected = true;
			m.start();
			return true;
        } catch (SQLException ex) { 
			connected = false;
			
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} 
	
	}
	public boolean isConnected (){
		return connected;
	}
	
	private Main m;
	connection(Main m){
		url = "jdbc:mysql://" + host + ":" + port + "/" + db_name + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
        this.m = m;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC Driver not found.\nPlease download 'Java MySQL Connector-JDBC'\nCreate the folder here and name it 'lib'\nPut the downloaded file in it.\nThan try again.");
			JOptionPane.showMessageDialog(mPanel,"JDBC Driver not found.\nPlease download 'Java MySQL Connector-JDBC'\nCreate the folder here and name it 'lib'\nPut the downloaded file in it.\nThan try again.");
			System.exit(0);
            // Logger.getLogger(DatabaseConnectionFirst.class.getName()).log(Level.SEVERE, null, ex);
        }
		hostLoginPanel hlp = new hostLoginPanel(this);
	}
	
	//CHECK USERS
	public ResultSet getTable(){
		String query = "SELECT * FROM sent";
		
		try {
			stmt = con.createStatement();
			return stmt.executeQuery(query);
			
		} catch (SQLException ex) {
		}
		return null;
	}
	public boolean checkUser(String id,String pass){
		ResultSet rs;
		String query = "SELECT * FROM users";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.first();
			do{
				if(id.equals(rs.getString("id") )&& pass.equals(rs.getString("pass"))){
					return true;
				}
					
				
			}while(rs.next());
			return false;
			
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		
	}
	
	public boolean sendMessage(String username,String message){
		
		String query = "UPDATE `sent` SET `message`= '" + message + "' ,`state`='1' WHERE `sent`.`user`= '"+ username + "'";
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException ex) {
		//	Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			mPanel.setErrorLabel("Message Error");
			return false;
		}
		
		
		
	}
	
	public void stateUpdate(String username,int state,boolean close){
		
		if(close){
		if(username.equals("DoctorOne")){
			username = "Burak";
		} else {
			username = "DoctorOne";
		}}
		
		String query = "UPDATE `sent` SET `state`=' "+ state +"' WHERE `sent`.`user`= '"+ username + "'";
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException ex) {
			mPanel.setErrorLabel("Error On");
		}
		
		
		
	}
	
	
	//FILE
	
	//GETTERS FROM DATABASE
	public ResultSet checkFile(){//GET ALL FILE NAMES
		String query = "SELECT * FROM file WHERE id>-1 ORDER BY id ASC";
		try {
			stmt = con.createStatement();
			return stmt.executeQuery(query);
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			mPanel.setErrorLabel("File List Error");
		}
		return null;
	}
	public String checkFile(int id){//SPECIFIC FILE NAME
		String query = "SELECT filename FROM file WHERE id=" + id;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			return rs.getString("filename");
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			mPanel.setErrorLabel("File List Error");
		}
		return null;
	}
	public int checkFile(String filename){//File Size
		String query = "SELECT file FROM file WHERE filename= '" + filename + "'";
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			byte[] b = rs.getBytes("file");
			    return b.length;
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			mPanel.setErrorLabel("File List Error");
		}
		return 0;
	}
	
	
	//DOWNLOAD
	private pbFrame pb;
	public void downloadFile(String filename){
		String query = "SELECT id,file FROM file WHERE filename= '" + filename+"'";
		
		//Create the download folder if not exist
		
		File folder = new File("downloads");
		
		if(!folder.exists())
			folder.mkdir();
		
		
		
		
		try(FileOutputStream fos = new FileOutputStream(new File("downloads//" + filename))) {
			
			//ProgressBar
			
			
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			
			
			byte[] b=rs.getBytes("file");
			
			
			/*Thread t = new Thread(() -> {
				pb = new pbFrame();
				pb.setResizable(false);
				pb.setTitle("DOWNLOADING");
				pb.setVisible(true);
				pb.setMax(b.length);
				System.out.println("here");
			});
		
			t.start();
			t.join();*/
			
			System.out.println("here!");
			for(byte a : b){
			    fos.write(a);
			}
			mPanel.setErrorLabel("Downloaded.");
			
		} catch (SQLException | IOException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			mPanel.setErrorLabel("Download Failed.");
		} /*catch (InterruptedException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
		}*/
		
	}
	//UPLOAD
	public void uploadFile(int id,String filename,File upload){
		mPanel.setErrorLabel("Uploading...");
		
		
		try(FileInputStream fis = new FileInputStream(upload)){
			
			String query = "UPDATE `file` SET `filename`=?,`file`=? WHERE id= ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, filename);
			
			ps.setBlob(2, fis);
			ps.setInt(3, id);
			ps.executeUpdate();
			mPanel.setErrorLabel("Uploaded.");
		} catch (FileNotFoundException ex) {
			mPanel.setErrorLabel("File not found.");
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			mPanel.setErrorLabel("Upload Failed.");
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		
		
	}
	//DELETE
	public void delFile(int id){
		String sql;
		if(id != -1)
		    sql = "UPDATE file SET file=null,filename='Empty Slot' WHERE id="+id;
		else
			sql = "UPDATE file SET file=null,filename=null WHERE id= -1";
		
			
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			mPanel.setErrorLabel("File Deleted.");
			
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			mPanel.setErrorLabel("DELETE ERROR");
		}
		
		
	}
	
	//VERSION OPERATIONS
	
	public boolean checkVersion(){ //CHECK AND UPDATE THE VERSION
		FileOutputStream fos = null;
		String sql = "SELECT * FROM version WHERE id=0";
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.first();
			
			if(VERSION < rs.getDouble("version")){
				fos = new FileOutputStream(new File("WANN Chat v" + rs.getDouble("version") + ".jar"));
				
				fos.write(rs.getBytes("file"));
				return true;
				
			}
			
			
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} catch (FileNotFoundException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} catch (IOException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}finally{
			try {
				if(fos!=null)
				fos.close();
			} catch (IOException ex) {
				Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return false;
	}
	
	public boolean updateVersion(File upload,double newVersion){
		
		
		try(FileInputStream fis = new FileInputStream(upload)){
			
			String query = "UPDATE `version` SET `version`=?,`file`=? WHERE id= 0";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDouble(1, newVersion);
			ps.setBlob(2, fis);
			ps.executeUpdate();
			return true;
		} catch (FileNotFoundException ex) {
			mPanel.setErrorLabel("File not found.");
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			mPanel.setErrorLabel("Upload Failed.");
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
		}   
		
		
		return false;
	}
	
	//WRITING...
	
	public void updateWriting(String username,int writing){ //SAYING THAT WE ARE WRITING OR NOT
		
		String query = "UPDATE writing SET writing=? WHERE user=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, writing);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	public boolean getWriting(String username){
		if(username.equals("Burak")){
			username = "DoctorOne";	
		} else {
			username = "Burak";
		}
		String sql = "SELECT writing FROM writing WHERE user = '" + username +"'";
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.first();
			if(rs.getInt("writing") == 1) return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return false;
	}
	
	
}
