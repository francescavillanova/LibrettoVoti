package it.polito.tdp.librettovoti;

import it.polito.tdp.librettovoti.model.Libretto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       // Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Scene.fxml")); //metto in input ciò che c'era tra parentesi nell'operazione originale
        Parent root = loader.load(); //nodo radice
        
        FXMLController controller = loader.getController(); //riferimento alla classe Controller
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        Libretto model=new Libretto();  //sono nel main e creo la classe model
        controller.setModel(model);  //richiamo setModel cosicchè il main sappia su quale modello lavorare
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
