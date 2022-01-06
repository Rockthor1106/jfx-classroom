  
package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import model.Classroom;

public class ClassroomGUI {
	
    @FXML
    private BorderPane mainPanel;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private TextField registerUS;

    @FXML
    private TextField registerPS;
    
    @FXML
    private DatePicker birthday;

    @FXML
    private ComboBox<String> browser;

    @FXML
    private ToggleGroup gender;
    
    @FXML
    public void singUp(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent register = fxmlLoader.load();
    	
		mainPanel.getChildren().clear();
    	mainPanel.setTop(register);
    	setFavoriteBrowserComboBox();
    }

    @FXML
    public void createAccount(ActionEvent event) {
    	String username = registerUS.getText();
    	String password = registerPS.getText();
    	new Classroom().addUserAccounts(username, password);	
    }
    
    @FXML
    public void returnToLogin(ActionEvent event) throws IOException {
        Parent loginScreen = FXMLLoader.load(getClass().getResource("login.fxml"));
        mainPanel.setTop(loginScreen);
    }
    
    @FXML
    public void alertError() {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Login Error");
	    alert.setHeaderText(":C");
	    alert.setContentText("Username or password are incorrect");
	
	    alert.showAndWait();
    }
    
    @FXML
    public void alertAcces() {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("You are logged in");
	    alert.setHeaderText(":D");
	    alert.setContentText("successful login. Welcome");
	
	    alert.showAndWait();
    }
    
    @FXML
    public void logIn(ActionEvent event) throws IOException {
    	String strusername = txtUsername.getText();
    	String strpassword = txtPassword.getText();
    	if (new Classroom().userExists(strusername, strpassword)==true) {
    		Parent accountList = FXMLLoader.load(getClass().getResource("account-list.fxml"));
    		mainPanel.getChildren().clear();
    		mainPanel.setTop(accountList);
    		alertAcces();
    	}
    	else {
    		alertError();
    	}
    }
    
    public void setFavoriteBrowserComboBox() {
    	ObservableList<String> browserList = FXCollections.observableArrayList("Google Chorme", "Mozilla", "Edge", "Brave", "Opera", "Zafari");
    	browser.setValue("Seleccionar");
    	browser.setItems(browserList);
    }
    
    @FXML
    public void searchForProfilePhoto() {
    	
    }
}
