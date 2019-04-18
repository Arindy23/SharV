package de.arindy.sharv.gui;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import static javafx.application.Application.Parameters;

@Singleton
public class ApplicationParametersProvider {

    private Parameters parameters;

    public @Produces
    Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
