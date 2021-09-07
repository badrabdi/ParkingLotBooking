package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import system.System;
import system.ParkingAuthority;

public class removeParkAuth implements Initializable{

	
	public removeParkAuth() {
		
	}
	

	
	@FXML private ListView<String> listView;
	@FXML private Label warning;
	
	private System system;
	private ArrayList<ParkingAuthority> pAuth;
	
	public void back(ActionEvent event)throws IOException {
		Main main = new Main();
		main.cs("sysAdmin.fxml");
	}
	public void logout(ActionEvent event)throws IOException {
		Main main = new Main();
		main.cs("maim.fxml");
	}
	public void delete(ActionEvent event)throws Exception {
		if(listView.getSelectionModel().getSelectedItem() != null) {
			String user = listView.getSelectionModel().getSelectedItem().toString();
			ParkingAuthority found = null;
			for(ParkingAuthority auth: pAuth) {
				if(auth.getUser().equals(user)) {
					found = auth;
				}
			}
			pAuth.remove(found);
			system.updateAuth(pAuth);
			Main main = new Main();
			main.cs("sysAdmin.fxml");
		}
		else {
			warning.setText("Select Account!");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			system = new System();
			pAuth = system.getParkingAuth();
			
			for(ParkingAuthority auth : pAuth) {
				listView.getItems().add(auth.getUser());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
