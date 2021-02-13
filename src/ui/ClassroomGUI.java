  
package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    public void singUp(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent register = fxmlLoader.load();
    	
		mainPanel.getChildren().clear();
    	mainPanel.setTop(register);
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
    public void logIn(ActionEvent event) throws IOException {
    	String strusername = txtUsername.getText();
    	String strpassword = txtPassword.getText();
    	if (new Classroom().userExists(strusername, strpassword)==true) {
    		Parent accountList = FXMLLoader.load(getClass().getResource("account-list.fxml"));
    		mainPanel.getChildren().clear();
    		mainPanel.setTop(accountList);
    	}
    }
}
