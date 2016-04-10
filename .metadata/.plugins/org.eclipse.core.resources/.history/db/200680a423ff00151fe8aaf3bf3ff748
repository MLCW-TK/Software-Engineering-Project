package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

import javax.swing.*;

import mealsystem.AbstractMeal;
import users.StaffUser;
import users.User;

public class LogInGUI extends JFrame{
	User user;
	JTextArea display;
	JPanel logInPan;
	JButton createMeal;
	
	
	public LogInGUI(User u){
		this.user = u;
		
		this.setSize(900,600);

        Toolkit tlk = Toolkit.getDefaultToolkit();
        Dimension dim = tlk.getScreenSize();

        int xPos = (dim.width /2) - (this.getWidth()/2);
        int yPos = (dim.height /2) - (this.getHeight()/2);

        this.setLocation(xPos, yPos);

        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setTitle("EYMS - "+ this.user.getUsername());

        logInPan = new JPanel();
        this.add(logInPan);
        
        HashSet<AbstractMeal> m = HomeGUI.res.getMeal_list();
        for(AbstractMeal meal : m){
        	logInPan.add(new JButton(meal.getName()));
        }
        
        if(((Object)this.user) instanceof StaffUser){
        	this.stafflogin();
        }
        
        


        //HashSet<AbstractMeal> mealString = HomeGUI.res.getMeal_list();
        
        

        display = new JTextArea(10,20);
            

        logInPan.add(display);





        this.setVisible(true);
	}
	public void clientlogin(){
		
	}
	public void stafflogin(){
		createMeal = new JButton("Create Meal");
		this.logInPan.add(createMeal);
	}
	
	
	
    // Implement listeners
    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource() == createMeal){
            	new CreateMealGUI();
            }

        }
    }
}

