// Assignment #: Arizona State University CSE205 #6
//         Name: Alan Griffieth
//    StudentID: 1212575453
//      Lecture: MWF 9:40 - 10:30 a.m.
//  Description: PurchasePane displays a list of available laptops
//  from which a user can select to buy. It also shows how many
//  laptops are selected and what is the total amount.


import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;	//**Need to import to handle event
import javafx.event.EventHandler;	//**Need to import to handle event

import java.util.ArrayList;
import java.text.DecimalFormat;

//import all other necessary javafx classes
//----
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PurchasePane extends VBox
{
   //GUI components
   private ArrayList<Laptop> laptopList, selectedList;

   //laptopLV for top ListView; selectedLV for bottom ListView
   private ListView<Laptop> laptopLV, selectedLV;

   //declare all other necessary GUI variables here
   //----
   private ObservableList<Laptop> laptopOV;
   private ObservableList<Laptop> selectedOV;
   private int quantity = 0;
   private double total = 0.00;
   private Button btPick = new Button("Pick a Laptop");
   private Button btRemove = new Button("Remove a Laptop");
   private int selectedIndex;
   private Laptop selectedLaptop;
   private Label totalAmount = new Label("Total Amt: $0.00");
   private Label totalSelected = new Label("Qty Selected: " + quantity);
 //constructor
   public PurchasePane(ArrayList<Laptop> list)
   {
   	  //initialize instance variables
      laptopList = list; 
      selectedList = new ArrayList<Laptop>();
      laptopOV = FXCollections.observableArrayList(this.laptopList);
      laptopLV = new ListView<>(laptopOV);
      selectedOV = FXCollections.observableArrayList(this.selectedList);
      selectedLV = new ListView<>(selectedOV);
      
      laptopLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      
  
      //set up the layout
      //----
      HBox hbox = new HBox();
      hbox.setSpacing(20);
      hbox.setPadding(new Insets(10,10,40,10));
      hbox.setAlignment(Pos.CENTER);
      hbox.getChildren().addAll(btPick, btRemove);
      
      Label available = new Label("Available Laptops");
      available.setTextFill(Color.BLUE);
      available.setStyle("-fx-font-weight: bold");
      
      Label selected = new Label("Selected Laptops");
      selected.setTextFill(Color.BLUE);
      selected.setStyle("-fx-font-weight: bold");
      
      DecimalFormat fmt1 = new DecimalFormat("$0.00");
     
      totalAmount.setText("Total Amt: " + fmt1.format(total));
      totalAmount.setTextFill(Color.BLUE);
      totalSelected.setTextFill(Color.BLUE);
      
      HBox hbox2 = new HBox();
      hbox2.setSpacing(40);;
      hbox2.setPadding(new Insets(10,10,10,10));
      hbox2.setAlignment(Pos.CENTER);
      hbox2.getChildren().addAll(totalSelected, totalAmount);
      
   	 //PurchasePane is a VBox - add the components here
   	 //----
      this.getChildren().addAll(available, laptopLV, hbox, selected, selectedLV, hbox2);
	  //Step #3: Register the button with its handler class
	  //----
      ButtonHandler2 bhandler = new ButtonHandler2();
      btPick.setOnAction(bhandler);
      btRemove.setOnAction(bhandler);

   } //end of constructor

 //This method refresh the ListView whenever there's new laptop added in InputPane
 //you will need to update the underline ObservableList object in order for ListView
 //object to show the updated laptop list
 public void updateLaptopList(Laptop newLaptop)
 {
     laptopList.add(newLaptop);
     laptopOV.add(newLaptop);
 }

//Step #2: Create a ButtonHandler class
 private class ButtonHandler2 implements EventHandler<ActionEvent>
 {
  	//Override the abstact method handle()
    public void handle(ActionEvent e)
    {
    	Object source = e.getSource();
		//When "Pick a Laptop" button is pressed and a laptop is selected from
		//the top list
        if (source == btPick && selectedIndex >= 0) {
        
        	selectedIndex = laptopLV.getSelectionModel().getSelectedIndex() + 1;
			selectedLaptop = laptopList.get(selectedIndex);
			
			selectedList.add(selectedLaptop);
			selectedOV.add(selectedLaptop);
        }
        //when "Remove a Laptop" button is pushed and a laptop is selected from
        //the bottom list
        else if (source == btRemove && selectedIndex >= 0) {
        	selectedIndex = selectedLV.getSelectionModel().getSelectedIndex();
        	selectedLaptop = selectedList.get(selectedIndex);
        	
        	selectedList.remove(selectedLaptop);
        	selectedOV.remove(selectedLaptop);
        }
         {

         }
        // else //for all other cases
		 {
		 	 //All invalid action, do nothing here;
	     }
      	 //update the QtySelect and AmtSelect labels
      	 //----
		 total = 0;
		 for(int i = 0; i < selectedList.size(); i++) {
			 total += selectedList.get(i).getPrice();
		 }
		 DecimalFormat fmt1 = new DecimalFormat("$0.00");
		 
		 quantity = selectedList.size();
		 totalAmount.setText("Total Amt: " + fmt1.format(total));
		 totalSelected.setText("Qty Selected: " + quantity);
       }
   } //end of ButtonHandler class
} //end of PurchasePane class