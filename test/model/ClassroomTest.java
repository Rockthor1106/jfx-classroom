package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ClassroomTest {
	
	private Classroom classroom;
	
	private void setUpScenary1() {
		classroom = new Classroom();
	}
	
	private void setUpScenary2() {
		classroom = new Classroom();
		ArrayList<String> careerList = new ArrayList<>();
		careerList.add("Software Engineering");
		classroom.addUserAccounts("Rockthor", "123", "C:/workspace/images/bla-bla", "Male", careerList, "28/11/1975", "Edge");
		assertEquals("Rockthor", classroom.getUserAccounts().get(0).getUsername());
	}
	
	@Test
	void testAddUserAccount() throws IOException {
		setUpScenary1();
		ArrayList<String> careerList = new ArrayList<>();
		careerList.add("Software Engineering");
		classroom.addUserAccounts("Rockthor", "123", "C:/workspace/images/bla-bla", "Male", careerList, "28/11/1975", "Edge");
		assertEquals("Rockthor", classroom.getUserAccounts().get(0).getUsername());
	}
	
	@Test
	void testUserExists() {
		setUpScenary2();
		assertEquals(true, classroom.userExists("Rockthor", "123"));
	}

}
