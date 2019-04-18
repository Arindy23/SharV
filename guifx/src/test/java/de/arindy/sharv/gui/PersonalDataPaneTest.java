package de.arindy.sharv.gui;

import de.arindy.sharv.api.gui.DefaultPersonalDataListener;
import de.arindy.sharv.api.gui.PersonalDataView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextFlowMatchers;

import java.io.IOException;
import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.testfx.api.FxAssert.verifyThat;

class PersonalDataPaneTest extends HeadlessGUITest {

    private PersonalDataView personalData;
    private DefaultPersonalDataListener personalDataListener;

    @Start
    private void start(Stage stage) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/fxml/personalData.fxml"),
                ResourceBundle.getBundle("lang/personalData")
        );
        super.start(stage, new Scene(fxmlLoader.load()));
        personalDataListener = new DefaultPersonalDataListener();
        personalData = ((PersonalDataPane) fxmlLoader.getController()).withPersonalDataListener(personalDataListener);
    }

    @Test
    void setKarmaShouldUpdateKarmaLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setKarma(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#karma"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setKarmaMaxShouldUpdateKarmaMaxLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setKarmaMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#karmaMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setNuyenMaxShouldUpdatenuyenLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setNuyen(666));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#nuyen"), LabeledMatchers.hasText("666"));
    }

    @Test
    void changingNameShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#name").write("Cthulhu");
        verifyThat(personalDataListener.getName(), is("Cthulhu"));
    }

    @Test
    void changingStreetnameShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#streetname").write("Cthulhu");
        verifyThat(personalDataListener.getStreetname(), is("Cthulhu"));
    }

    @Test
    void changingAgeShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#age").write("23");
        verifyThat(personalDataListener.getAge(), is(23));
    }

    @Test
    void ageShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#age").write("noInt");
        verifyThat(personalDataListener.getAge(), is(0));
    }

    @Test
    void changingHeightShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#height").write("180");
        verifyThat(personalDataListener.getHeight(), is(180));
    }

    @Test
    void heightShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#height").write("noInt");
        verifyThat(personalDataListener.getHeight(), is(0));
    }

    @Test
    void changingWeightShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#weight").write("70");
        verifyThat(personalDataListener.getWeight(), is(70));
    }

    @Test
    void weightShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#weight").write("noInt");
        verifyThat(personalDataListener.getWeight(), is(0));
    }

    @Test
    void changingEthnicityShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#ethnicity").write("english");
        verifyThat(personalDataListener.getEthnicity(), is("english"));
    }

    @Test
    void changingConceptShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#concept").write("Chaosmage");
        verifyThat(personalDataListener.getConcept(), is("Chaosmage"));
    }

    @Test
    void changingStreetCredShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#streetCred").write("23");
        verifyThat(personalDataListener.getStreetCred(), is(23));
    }

    @Test
    void streetCredShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#streetCred").write("noInt");
        verifyThat(personalDataListener.getStreetCred(), is(0));
    }

    @Test
    void changingNotorietyShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#notoriety").write("23");
        verifyThat(personalDataListener.getNotoriety(), is(23));
    }

    @Test
    void notorietyShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#notoriety").write("noInt");
        verifyThat(personalDataListener.getNotoriety(), is(0));
    }

    @Test
    void changingPublicAwarenessShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#publicAwareness").write("23");
        verifyThat(personalDataListener.getPublicAwareness(), is(23));
    }

    @Test
    void publicAwarenessShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#publicAwareness").write("noInt");
        verifyThat(personalDataListener.getPublicAwareness(), is(0));
    }

    @Test
    void changingMetatypeShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            personalData.removeMetatypes();
            personalData.addMetatypes("Human", "Elf");
        });
        robot(r).selectComboBoxItem("#metatype", "Human");
        verifyThat(personalDataListener.getMetatype(), is("Human"));
    }

    @Test
    void changingSexShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            personalData.removeAllSexes();
            personalData.addSexes("male", "female");
        });
        robot(r).selectComboBoxItem("#sex", "female");
        verifyThat(personalDataListener.getSex(), is("female"));
    }

    @Test
    void addingRacialBonusesShouldUpdateRacialBonusesTextArea(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            personalData.removeAllRacialBonuses();
            personalData.addRacialBonuses("firstBonus", "secondBonus");
        });
        awaitSharV();
        verifyThat(robot(r).lookupTextFlow("#racialBonuses"), TextFlowMatchers.hasText(String.format("%s%n%s", "firstBonus", "secondBonus")));
    }

    @Test
    void removingRacialBonusesShouldUpdateRacialBonusesTextArea(final FxRobot r) throws Exception {
        Platform.runLater(() -> robot(r).lookupTextFlow("#racialBonuses").getChildren().addAll(
                new Text("firstBonus"),
                new Text("\n"),
                new Text("secondBonus")
        ));
        Platform.runLater(() -> personalData.removeAllRacialBonuses());
        awaitSharV();
        verifyThat(robot(r).lookupTextFlow("#racialBonuses"), TextFlowMatchers.hasText(""));
    }

    @Test
    void setNameShouldUpdateNameTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setName("Finnigan Koss"));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#name"), TextFieldMatchers.hasText("Finnigan Koss"));
    }

    @Test
    void setStreetnameShouldUpdateStreetnameTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setStreetname("FiasKo"));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#streetname"), TextFieldMatchers.hasText("FiasKo"));
    }

    @Test
    void setMetatypeShouldUpdateMetatypeComboBox(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            personalData.addMetatypes("Toll", "Elv", "Human");
            personalData.setMetatype("Human");
        });
        awaitSharV();
        verifyThat(robot(r).lookupComboBox("#metatype"), ComboBoxMatchers.hasSelectedItem("Human"));
    }

    @Test
    void setSexShouldUpdateSexComboBox(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            personalData.addSexes("Male", "Female");
            personalData.setSex("Female");
        });
        awaitSharV();
        verifyThat(robot(r).lookupComboBox("#sex"), ComboBoxMatchers.hasSelectedItem("Female"));
    }

    @Test
    void setAgeShouldUpdateAgeTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setAge(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#age"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setHeightShouldUpdateHeightTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setHeight(666));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#height"), TextFieldMatchers.hasText("666"));
    }

    @Test
    void setWeightShouldUpdateWeightTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setWeight(666));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#weight"), TextFieldMatchers.hasText("666"));
    }

    @Test
    void setEthnicityShouldUpdateEthnicityTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setEthnicity("German"));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#ethnicity"), TextFieldMatchers.hasText("German"));
    }

    @Test
    void setConceptShouldUpdateConceptTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setConcept("Force Adept"));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#concept"), TextFieldMatchers.hasText("Force Adept"));
    }

    @Test
    void setStreetCredShouldUpdateStreetCredTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setStreetCred(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#streetCred"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setNotorietyShouldUpdateNotorietyTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setNotoriety(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#notoriety"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setPublicAwarenessShouldUpdatePublicAwarenessTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> personalData.setPublicAwareness(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#publicAwareness"), TextFieldMatchers.hasText("23"));
    }

}
