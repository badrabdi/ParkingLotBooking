package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LoggedIn {
	
	
	@FXML
	private Button logout;
	
	public void userLogout(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("main.fxml");
	}
	
}
