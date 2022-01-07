  
package ui;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
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
    private CheckBox IE; //Industrial Engineering

    @FXML
    private CheckBox SE; //Software Engineering

    @FXML
    private CheckBox TE; //Telematic Engineering

    @FXML
    private TextField photoAddres;
    
    private String photoPath = null;
    
    private Classroom classroom;

    public ClassroomGUI(Classroom classroom) {
    	this.classroom = classroom;
    }
    
    @FXML
    public void singUp(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent registerScreen = fxmlLoader.load();
    	
		mainPanel.getChildren().clear();
    	mainPanel.setTop(registerScreen);
    	setFavoriteBrowserComboBox();
    }

    @FXML
    public void createAccount(ActionEvent event) {
    	String username = registerUS.getText();
    	String password = registerPS.getText();
    	String photo = photoPath;
    	RadioButton genderRButton = (RadioButton) gender.getSelectedToggle();
    	String genderStr = genderRButton.getText();
    	ArrayList<String> careerList = new ArrayList<String>();
    	if (SE.isSelected()) {
			careerList.add(SE.getText());
		}
    	if (TE.isSelected()) {
			careerList.add(TE.getText());
		}
    	if (IE.isSelected()) {
			careerList.add(IE.getText());
		}
    	String birthdayStr = birthday.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    	String favoriteBrowser = browser.getSelectionModel().getSelectedItem();
    	classroom.addUserAccounts(username, password, photo, genderStr, careerList, birthdayStr, favoriteBrowser);
    	System.out.println(classroom.getUserAccounts().size());
    	System.out.println(classroom.getUserAccounts().toString());
    }
    
    @FXML
    public void returnToLogin(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent loginScreen = fxmlLoader.load();
		
		mainPanel.getChildren().clear();
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
    	String strUsername = txtUsername.getText();
    	String strPassword = txtPassword.getText();
//    	System.out.println(classroom.getUserAccounts().size());
//    	System.out.println(classroom.getUserAccounts().get(0).getUsername());
//    	System.out.println(classroom.getUserAccounts().get(0).getPassword());
    	if (classroom.userExists(strUsername, strPassword)==true) {
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
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Seleccionar foto de perfil");
    	File file = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
    	if (file != null) {
    		photoPath = file.getAbsolutePath();
		}
    	photoAddres.setText(photoPath);
    }
}
