package de.arindy.sharv.gui;

import de.arindy.sharv.api.gui.PersonalDataListener;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

class PersonalDataPaneTest extends HeadlessGUITest {

    private PersonalDataPane personalDataPane;
    private TestPersonalDataListener personalDataListener;

    @Start
    private void start(Stage stage) {
        personalDataPane = new PersonalDataPane();
        personalDataListener = new TestPersonalDataListener();
        personalDataPane.registerListener(personalDataListener);
        stage.setScene(new Scene(personalDataPane));
        stage.show();
    }

    @Test
    void setKarmaShouldUpdateKarmaLabel(final FxRobot r) throws Exception {
        SharV.performAction(() -> personalDataPane.setKarma(23));
        awaitSharV();
        assertThat(robot(r).lookupLabel("#karma")).hasText("23");
    }

    @Test
    void setKarmaMaxShouldUpdateKarmaMaxLabel(final FxRobot r) throws Exception {
        SharV.performAction(() -> personalDataPane.setKarmaMax(23));
        awaitSharV();
        assertThat(robot(r).lookupLabel("#karmaMax")).hasText("23");
    }

    @Test
    void changingNameShouldTriggerListener(final FxRobot r) throws Exception {
        robot(r).clickOnTextInput("#name").write("Cthulhu");
        assertThat(personalDataListener.name).isEqualTo("Cthulhu");
    }

    @Test
    void changingNameOverLimitShouldCutAdditional(final FxRobot r) throws Exception {
        robot(r).clickOnTextInput("#name").write("............................#");
        assertThat(personalDataListener.name).isEqualTo("............................");
    }

    private class TestPersonalDataListener implements PersonalDataListener {

        private String name;
        private String streetname;
        private String metatype;
        private String sex;
        private String age;
        private int height;
        private int weight;
        private String ethnicity;
        private String concept;
        private int streetCred;
        private int notoriety;
        private int publicAwareness;

        @Override
        public void changeName(String name) {
            this.name = name;
        }

        @Override
        public void changeStreetname(String streetname) {
            this.streetname = streetname;
        }

        @Override
        public void changeMetatype(String metatype) {
            this.metatype = metatype;
        }

        @Override
        public void changeSex(String sex) {
            this.sex = sex;
        }

        @Override
        public void changeAge(String age) {
            this.age = age;
        }

        @Override
        public void changeHeight(int height) {
            this.height = height;
        }

        @Override
        public void changeWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public void changeEthnicity(String ethnicity) {
            this.ethnicity = ethnicity;
        }

        @Override
        public void changeConcept(String concept) {
            this.concept = concept;
        }

        @Override
        public void changeStreetCred(int streetCred) {
            this.streetCred = streetCred;
        }

        @Override
        public void changeNotoriety(int notoriety) {
            this.notoriety = notoriety;
        }

        @Override
        public void changePublicAwareness(int publicAwareness) {
            this.publicAwareness = publicAwareness;
        }
    }

}
