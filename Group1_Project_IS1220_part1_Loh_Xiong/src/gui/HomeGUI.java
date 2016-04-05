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

import javax.swing.*;

import cardfidelitysystem.FidelityCardFactory;
import commandinterface.CommandConsole;
import coresystem.RestaurantSystem;
import mealsystem.AbstractMeal;
import mealsystem.Meal;
import orders.Order;
import users.ClientUser;


public class HomeGUI extends JFrame{

	// system initialization
	static RestaurantSystem re1 = new RestaurantSystem("our restaurant!");
	static ArrayList<Order> orders = new ArrayList<Order>();
	static HashSet<AbstractMeal> meals = new HashSet<AbstractMeal>();;
	static HashSet<String> ingredients = new HashSet<String>();
	static Scanner sc = new Scanner(System.in);
	private static CommandConsole cl = new CommandConsole();
	static ClientUser currentUser;
	static Meal currentMeal;
	static protected FidelityCardFactory fcf = new FidelityCardFactory();


	static Meal editedMeal = null;
	
	
	static boolean globalPhase = true;
	static boolean loginPhase = true;
	static boolean loggedinPhase = false;
	

    
    public static void main(String[] args){
        new HomeGUI();
    }
	
	// GUI attributes
    JTextField usernameF;
    JTextField passwordF;
    JButton loginB;
    JButton registerB;
    JTextArea display;
    

    public HomeGUI(){
        this.setSize(900,600);

        Toolkit tlk = Toolkit.getDefaultToolkit();
        Dimension dim = tlk.getScreenSize();

        int xPos = (dim.width /2) - (this.getWidth()/2);
        int yPos = (dim.height /2) - (this.getHeight()/2);

        this.setLocation(xPos, yPos);

        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("EYMS - Home");

        JPanel loginPan = new JPanel();
        this.add(loginPan);

        JLabel usernameL = new JLabel("User name");

        usernameF = new JTextField(20);
        usernameF.setText("username");
        usernameF.requestFocus();

        JLabel passwordL = new JLabel("Password");

        passwordF = new JTextField(20);
        passwordF.setText("password");

        loginB = new JButton("Login");
        ListenForButton loginListen = new ListenForButton();
        loginB.addActionListener(loginListen);

        registerB = new JButton("Register");
        ListenForButton registerListen = new ListenForButton();
        registerB.addActionListener(registerListen);

        display = new JTextArea(30,60);

        loginPan.add(usernameL);
        loginPan.add(usernameF);
        loginPan.add(passwordL);
        loginPan.add(passwordF);
        loginPan.add(loginB);
        loginPan.add(registerB);
        loginPan.add(display);





        this.setVisible(true);
    }
    // Implement listeners
    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource() == loginB){
                // run the login method
            	String username = usernameF.getText();
            	String password = passwordF.getText();
            	try{
            		ClientUser client = cl.login(username, password);
            	}catch(RuntimeException r){
            		display.append("User does not exist!\n");
            	}
            }
            if(e.getSource() == registerB){
                // run the register method
            	RegisterGUI registerGUI = new RegisterGUI();
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
