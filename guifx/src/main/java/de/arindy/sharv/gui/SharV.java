package de.arindy.sharv.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.util.AnnotationLiteral;

public class SharV extends Application {

    public static void main(String[] args) {
        Application.launch(SharV.class, args);
    }

    @Override
    public void start(final Stage stage) {
        WeldContainer weldContainer = new Weld().initialize();
        weldContainer.instance().select(ApplicationParametersProvider.class).get().setParameters(getParameters());
        weldContainer.event().select(Stage.class, new AnnotationLiteral<StartupScene>() {
        }).fire(stage);
    }

}
