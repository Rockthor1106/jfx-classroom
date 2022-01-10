  
package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	
    @FXML
    private BorderPane mainPane;

    private Classroom classroom;
    
    public ClassroomGUI(Classroom classroom) {
    	this.classroom = classroom;
    }
    
    //------------------------------Alerts-----------------------------------------
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
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("You are logged in");
	    alert.setHeaderText(":D");
	    alert.setContentText("successful login. Welcome");
	    alert.showAndWait();
    }
    
    @FXML 
    public void alertCreatedAccount() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Create account");
    	alert.setContentText("Your account has been created succesfully");
    	alert.showAndWait();
    }
    
    @FXML
    public void alertEmptyFields() {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Create account");
    	alert.setContentText("Some fields are empty");
    	alert.showAndWait();
    }
    //----------------------------------------------------------------------------------
    
    //--------------------------------global methods------------------------------------
    public void displaylogInScreen() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	
    	fxmlLoader.setController(this);
    	
    	Parent loginScreenParent = fxmlLoader.load();
    	
    	mainPane.setTop(loginScreenParent);
    }   
    //-----------------------------------------------------------------------------------
    
    //---------------------------------login.fxml----------------------------------------
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    public void logIn(ActionEvent event) throws IOException {
    	String strUsername = txtUsername.getText();
    	String strPassword = txtPassword.getText();
    	if (classroom.userExists(strUsername, strPassword)==true) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
    		
    		fxmlLoader.setController(this);
    		
    		Parent accountListScreen = fxmlLoader.load();
    		
    		mainPane.getChildren().clear();
    		mainPane.setTop(accountListScreen);
    		alertAcces();
    		initializeAccountsTableView();
    		usernameLbl.setText(strUsername);
    		setProfilePhoto(strUsername);
    	}
    	else {
    		alertError();
    	}
    }
    
    @FXML
    public void singUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
    	
    	fxmlLoader.setController(this);
    	
    	Parent registerScreen = fxmlLoader.load();
    	
    	mainPane.getChildren().clear();
    	mainPane.setTop(registerScreen);
    	setFavoriteBrowserComboBox();
    }
    //-----------------------------------------------------------------------------------

    //--------------------------------register.fxml--------------------------------------
    @FXML
    private CheckBox IE; //Industrial Engineering

    @FXML
    private CheckBox SE; //Software Engineering

    @FXML
    private CheckBox TE; //Telematic Engineering
    
    @FXML
    private DatePicker birthday;

    @FXML
    private ComboBox<String> browser;

    @FXML
    private ToggleGroup gender;
    
    @FXML
    private TextField photoAddres;
    
    private String photoPath = null;
    
    @FXML
    private TextField registerUS;

    @FXML
    private TextField registerPS;
    
    @FXML
    public void createAccount(ActionEvent event) {
    	if (registerUS.getText().equals(" ")) {
			alertEmptyFields();
		}
    	else if(registerPS.getText().equals(" ")) {
    		alertEmptyFields();
    	}
    	else if (photoAddres.getText().equals(" ")) {
			alertEmptyFields();
		}
    	else if (gender.getSelectedToggle() == null) {
			alertEmptyFields();
		}
    	else if (birthday.getValue() == null) {
    		alertEmptyFields();
		}
    	else if (browser.getSelectionModel().getSelectedItem().equals("Seleccionar")) {
    		alertEmptyFields();
		}
    	else {
        	String username = registerUS.getText();
        	String password = registerPS.getText();
        	String photo = photoPath;
        	RadioButton genderRButton = (RadioButton) gender.getSelectedToggle();
        	String genderStr = genderRButton.getText();
        	ArrayList<String> careerList = new ArrayList<String>();
        	
        	if (SE.isSelected() && TE.isSelected() && IE.isSelected()) {
    			careerList.add(SE.getText());
    			careerList.add(TE.getText());
    			careerList.add(IE.getText());
    		}
        	else if (SE.isSelected() && TE.isSelected()) {
    			careerList.add(SE.getText());
    			careerList.add(TE.getText());
    		}
        	if (SE.isSelected() && IE.isSelected()) {
    			careerList.add(SE.getText());
    			careerList.add(IE.getText());
    		}
        	else if (TE.isSelected() && IE.isSelected()) {
				careerList.add(TE.getText());
				careerList.add(IE.getText());
			}
        	else if (SE.isSelected()) {
				careerList.add(SE.getText());
			}
        	else if (TE.isSelected()) {
				careerList.add(TE.getText());
			}
        	else {
				alertEmptyFields();
			}
        	String birthdayStr = birthday.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        	String favoriteBrowser = browser.getSelectionModel().getSelectedItem();
        	if (!careerList.isEmpty()) {
        		classroom.addUserAccounts(username, password, photo, genderStr, careerList, birthdayStr, favoriteBrowser);
        		alertCreatedAccount();
			}
    	}
    }
    
    @FXML
    public void returnToLogin(ActionEvent event) throws IOException {
    	displaylogInScreen();
    }
    
    @FXML
    public void searchForProfilePhoto() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Seleccionar foto de perfil");
    	File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());
    	if (file != null) {
    		photoPath = file.getAbsolutePath();
		}
    	photoAddres.setText(photoPath);
    }
    
    public void setFavoriteBrowserComboBox() {
    	ObservableList<String> browserList = FXCollections.observableArrayList("Google Chorme", "Mozilla", "Edge", "Brave", "Opera", "Zafari");
    	browser.setValue("Seleccionar");
    	browser.setItems(browserList);
    }
    //-----------------------------------------------------------------------------------
    
    //---------------------------account-list.fxml---------------------------------------
    
    @FXML
    private TableView<UserAccount> accountsTV;
    
    @FXML
    private TableColumn<UserAccount, String> BrowserTC;

    @FXML
    private TableColumn<UserAccount, String> usernameTC;

    @FXML
    private TableColumn<UserAccount, String> birthdayTC;

    @FXML
    private TableColumn<UserAccount, ArrayList<String>> careerTC;

    @FXML
    private TableColumn<UserAccount, String> genderTC;

    @FXML
    private ImageView photo;

    @FXML
    private Label usernameLbl;

    @FXML
    public void logOut(ActionEvent event) throws IOException {
    	displaylogInScreen();
    }
    
    public void initializeAccountsTableView() {
    	ObservableList<UserAccount> accounts;
    	accounts = FXCollections.observableArrayList(classroom.getUserAccounts());
    	
    	accountsTV.setItems(accounts);
    	usernameTC.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username"));
    	genderTC.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender"));
    	careerTC.setCellValueFactory(new PropertyValueFactory<UserAccount,ArrayList<String>>("career"));
    	birthdayTC.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday"));
    	BrowserTC.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("favoriteBrowser"));
    	
    }
    
    public void setProfilePhoto(String username) throws FileNotFoundException {
    	String path = classroom.searchUser(username).getPhoto();
		InputStream inputStream = new FileInputStream(path);
		Image image = new Image(inputStream);
		photo.setImage(image);
    }
    //---------------------------------------------------------------------------------
}
