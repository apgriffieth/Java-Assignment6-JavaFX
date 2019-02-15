// Assignment #: Arizona State University CSE205 #6
//         Name: Alan Griffieth
//    StudentID: 1212575453
//      Lecture: MWF 9:40 - 10:30 a.m.
//  Description: InputPane generates a pane where a user can enter
//  a laptop information and create a list of available laptops.

import java.util.ArrayList;
import javafx.scene.layout.HBox;
//import all other necessary javafx classes here
//----
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;	
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.TextArea;

public class InputPane extends HBox
{
	//GUI components
	private ArrayList<Laptop> laptopList;

	//The relationship between InputPane and PurchasePane is Aggregation
	private PurchasePane purchasePane;
	//----
	private TextField brandField = new TextField();
	private TextField modelField = new TextField();
	private TextField CPUField = new TextField();
	private TextField RAMField = new TextField();
	private TextField priceField = new TextField();
	private HBox error = new HBox();
	private TextArea textArea = new TextArea();
	//constructor
	public InputPane(ArrayList<Laptop> list, PurchasePane pPane)
	{
		this.laptopList = list;
		this.purchasePane = pPane;

		//Step #1: initialize each instance variable and set up the layout
		//----
		error.setPadding(new Insets(10, 0, 10, 0));

		Label brand = new Label("Brand");
		Label model = new Label("Model");
		Label CPU = new Label("CPU(GHz)");
		Label RAM = new Label("RAM(GB)");
		Label price = new Label("Price($)");

		//create a GridPane hold those labels & text fields
		//consider using .setPadding() or setHgap(), setVgap()
		//to control the spacing and gap, etc.
		//----
		GridPane inputPane = new GridPane();
		inputPane.setHgap(5.5);
		inputPane.setVgap(5.5);
		inputPane.setPadding(new Insets(50, 10, 10, 30));

		//You might need to create a sub pane to hold the button
		//----
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 0, 30, 0));

		Button btAdd = new Button("Enter a Laptop Info.");
		hbox.getChildren().add(btAdd);

		//Set up the layout for the left half of the InputPane.
		//----
		inputPane.add(error, 0, 0);

		inputPane.add(brand, 0, 1);
		inputPane.add(brandField, 1, 1);

		inputPane.add(model, 0, 2);
		inputPane.add(modelField, 1, 2);

		inputPane.add(CPU, 0, 3);
		inputPane.add(CPUField, 1, 3);

		inputPane.add(RAM, 0, 4);
		inputPane.add(RAMField, 1, 4);

		inputPane.add(price, 0, 5);
		inputPane.add(priceField, 1, 5);

		inputPane.add(hbox, 1, 6);




		//the right half of the InputPane is simply a TextArea object
		//Note: a ScrollPane will be added to it automatically when there are no
		//enough space

		textArea.setText("No laptops");



		//Add the left half and right half to the InputPane
		//Note: InputPane extends from HBox

		this.getChildren().addAll(inputPane, textArea);


		//----
		//Step #3: register source object with event handler
		//----
		ButtonHandler aHandler = new ButtonHandler();
		btAdd.setOnAction(aHandler);

	} //end of constructor

	//Step #2: Create a ButtonHandler class
	//ButtonHandler listens to see if the buttont "Enter a Laptop Info." is pushed or not,
	//When the event occurs, it get a laptop's brand, model, CPU, RAM and price
	//information from the relevant text fields, then create a new Laptop object and add it inside
	//the laptopList. Meanwhile it will display the laptop's information inside the text area.
	//It also does error checking in case any of the textfields are empty or wrong data was entered.
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
		//Override the abstact method handle()
		public void handle(ActionEvent e)
		{
			Label errorMessage = new Label();
			errorMessage.setTextFill(Color.RED);
			try {
				//declare any necessary local variables here
				//---

				Label addMessage = new Label("Laptop added.");
				addMessage.setTextFill(Color.RED);
				//when a text field is empty and the button is pushed
				if (brandField.getText().trim().equals(""))
				{
					error.getChildren().clear();
					errorMessage.setText("Please fill all fields");
					error.getChildren().add(errorMessage);

				}

				else if (modelField.getText().trim().equals("")) {
					error.getChildren().clear();
					errorMessage.setText("Please fill all fields");
					error.getChildren().add(errorMessage);
				}

				else if (CPUField.getText().trim().equals("")) {
					error.getChildren().clear();
					errorMessage.setText("Please fill all fields");
					error.getChildren().add(errorMessage);
				}

				else if (RAMField.getText().trim().equals("")) {
					error.getChildren().clear();
					errorMessage.setText("Please fill all fields");
					error.getChildren().add(errorMessage);
				}

				else if (priceField.getText().trim().equals("")) {
					error.getChildren().clear();
					errorMessage.setText("Please fill all fields");
					error.getChildren().add(errorMessage);
				}

				else if (textArea.getText().trim().equals("No laptops")) {
					textArea.clear();
					error.getChildren().clear();
					error.getChildren().add(addMessage);
					Laptop laptop1 = new Laptop(brandField.getText().trim(), modelField.getText().trim(), 
							Double.parseDouble(CPUField.getText().trim()), Double.parseDouble(RAMField.getText().trim()),
							Double.parseDouble(priceField.getText().trim()));

					laptopList.add(laptop1);
					purchasePane.updateLaptopList(laptop1);
					textArea.appendText(laptop1.toString());

					brandField.clear();
					modelField.clear();
					CPUField.clear();
					RAMField.clear();
					priceField.clear();

				}
				else	//for all other cases
				{

					Laptop laptop1 = new Laptop(brandField.getText().trim(), modelField.getText().trim(), 
							Double.parseDouble(CPUField.getText().trim()), Double.parseDouble(RAMField.getText().trim()),
							Double.parseDouble(priceField.getText().trim()));
						
						if (laptopList.toString().contains(laptop1.toString())) {
							error.getChildren().clear();
							errorMessage.setText("Laptop not added - duplicate");
							error.getChildren().add(errorMessage);
							
							brandField.clear();
							modelField.clear();
							CPUField.clear();
							RAMField.clear();
							priceField.clear();
						}

						else {
							error.getChildren().clear();
							error.getChildren().add(addMessage);
							laptopList.add(laptop1);
							purchasePane.updateLaptopList(laptop1);
							textArea.appendText(laptop1.toString());

							brandField.clear();
							modelField.clear();
							CPUField.clear();
							RAMField.clear();
							priceField.clear();
						}
					
				}

				//----
				//at the end, don't forget to update the new arrayList
				//information on the ListView of the Purchase Pane
				//----

				//Also somewhere you will need to use try & catch block to catch
				//the NumberFormatException
			}

			catch (NumberFormatException exception) {
				error.getChildren().clear();
				errorMessage.setText("Incorrect data format");
				error.getChildren().add(errorMessage);
			}
		} //end of handle() method
	} //end of ButtonHandler class

}