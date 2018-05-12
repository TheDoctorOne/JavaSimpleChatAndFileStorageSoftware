/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wannmessages;

/**
 *
 * @author DoctorOne
 */
public class Main {

	private connection con;
	private LoginFrame loginFrame;
        static Main main;
	public Main() {
		new Thread(new Runnable() {
                    @Override
                    public void run() {
                        con = new connection(main);
                        System.out.println("con end");
                    }
                }).start();
		
		
	}

	public void start(){
            new Thread(new Runnable() {
                @Override
                    public void run() {
                    loginFrame = new LoginFrame(con);
                    System.out.println("lof end");
                }
                }).start();
	}
	
	public static void main(String[] args) {
		
		main = new Main();
	}
	
}
