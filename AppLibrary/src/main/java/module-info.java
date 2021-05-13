module com.martaarjona.AppLibrary {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	//requires jaxb.api;
	requires java.xml.bind;

    opens com.martaarjona.AppLibrary to javafx.fxml,javafx.base;
    opens com.martaarjona.AppLibrary.model to javafx.base;
    opens com.martaarjona.AppLibrary.utils to java.xml.bind;
    exports com.martaarjona.AppLibrary;
    exports com.martaarjona.AppLibrary.utils;
    
    
}