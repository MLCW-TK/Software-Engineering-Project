package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cardfidelitysystem.FidelityCardFactory;
import coresystem.RestaurantSystem;

import mealsystem.AbstractMeal;
import mealsystem.Meal;

import orders.Order;

import users.ClientUser;

public class RegisterGUI extends JFrame{
	// GUI attributes
    JTextField firstnameF;
    JTextField lastnameF;
    JTextField usernameF;
    JTextField passwordF;
    JTextField emailF;
    JTextField dobF;
    JTextField staffKeyF;
    JButton registerB;
    JTextArea display;


	 public RegisterGUI(){
	        this.setSize(350,500);

	        Toolkit tlk = Toolkit.getDefaultToolkit();
	        Dimension dim = tlk.getScreenSize();

	        int xPos = (dim.width /2) - (this.getWidth()/2);
	        int yPos = (dim.height /2) - (this.getHeight()/2);

	        this.setLocation(xPos, yPos);

	        this.setResizable(false);

	        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        this.setTitle("EYMS - Register");

	        JPanel regPan = new JPanel();
	        this.add(regPan);

            JLabel firstnameL = new JLabel("First Name:");
            firstnameF = new JTextField(20);
            firstnameF.setText("Your first name");

            JLabel lastnameL = new JLabel("Last Name:");
            lastnameF = new JTextField(20);
            lastnameF.setText("Your last name");


	        JLabel usernameL = new JLabel("User Name:");

	        usernameF = new JTextField(20);
	        usernameF.setText("your desired username");
	        usernameF.requestFocus();

	        JLabel passwordL = new JLabel(" Password:");
	        passwordF = new JTextField(20);
	        passwordF.setText("password");

	        JLabel emailL = new JLabel("    Email:");
	        emailF = new JTextField(20);
	        emailF.setText("your valid email address");
	        
	        JLabel staffKeyL = new JLabel("Staff Key:");
	        staffKeyF = new JTextField(20);
	        staffKeyF.setText("The staff key to register as a staff");

	        JLabel dobL = new JLabel("Date of Birth(optional)");
	        dobF = new JTextField(15);
            dobF.setText("dd/mm/yyyy");

	        registerB = new JButton("Register");
            ListenForButton registerListen = new ListenForButton();
            registerB.addActionListener(registerListen);

                

	        display = new JTextArea(10,25);
	        display.setEditable(false);

                
	        regPan.add(firstnameL);
	        regPan.add(firstnameF);
	        regPan.add(lastnameL);
	        regPan.add(lastnameF);
	        regPan.add(usernameL);
	        regPan.add(usernameF);
	        regPan.add(passwordL);
	        regPan.add(passwordF);
	        regPan.add(emailL);
	        regPan.add(emailF);
            regPan.add(dobL);
            regPan.add(dobF);
            regPan.add(staffKeyL);
            regPan.add(staffKeyF);
	        regPan.add(registerB);
	        regPan.add(display);





	        this.setVisible(true);
	    }
	    // Implement listeners
	    private class ListenForButton implements ActionListener {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
	            if(e.getSource() == registerB){
                        String firstname = firstnameF.getText();
                        String lastname = lastnameF.getText();
                        String username = usernameF.getText();
                        String password = passwordF.getText();
                        String email = emailF.getText();
                        String dob = dobF.getText();
                        String staffKey = staffKeyF.getText();
                        HomeGUI.res.register(firstname, lastname, username, password, email, dob, staffKey);
                        display.setText("Registration recorded,\nplease return to the home window to login.\n");
	            }

	        }
	    }


	    private class ListenForKey implements KeyListener {

	        @Override
	        public void keyPressed(KeyEvent e) {
	                // TODO Auto-generated method stub

	        }

	        @Override
	        public void keyTyped(KeyEvent e) {
	                // TODO Auto-generated method stub

	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	                // TODO Auto-generated method stub

	        }


	        @Override
	        public int hashCode() {
	                // TODO Auto-generated method stub
	                return super.hashCode();
	        }

	        @Override
	        protected void finalize() throws Throwable {
	                // TODO Auto-generated method stub
	                super.finalize();
	        }

	        @Override
	        public boolean equals(Object obj) {
	                // TODO Auto-generated method stub
	                return super.equals(obj);
	        }

	        @Override
	        public String toString() {
	                // TODO Auto-generated method stub
	                return super.toString();
	        }

	        @Override
	        protected Object clone() throws CloneNotSupportedException {
	                // TODO Auto-generated method stub
	                        return super.clone();
	                }

	        }


}
