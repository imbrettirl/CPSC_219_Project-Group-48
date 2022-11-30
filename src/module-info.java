module CPSC_Project_219 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	
	opens application to javafx.graphics, javafx.fxml;
}
