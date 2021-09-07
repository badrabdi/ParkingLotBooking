package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class systemAdminGUI {

	
	@FXML private Button addParking;
	@FXML private Button removeButton;
	@FXML private Button update;
	@FXML private Button verify;
	
	
	public void add(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("systemAdd.fxml");
	}
	
	public void remove(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("removeAuth.fxml");
	}
	
	public void update(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("updateStatus.fxml");
	}
	public void logout(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("main.fxml");
	}
	public void verify(ActionEvent event) throws IOException{
		
	}
	
}
