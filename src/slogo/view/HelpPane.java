package slogo.view;

import javafx.scene.image.Image;
import java.io.File;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HelpPane extends Pane {

  private static final int SIZE = 200;
  private VBox vbox;
  private GridPane displayWindow;
  private Label helpLabel;
  private ListView<ChoiceBox<String>> list;
  ResourceBundle resources;
  private ChoiceBox<String> commandList = new ChoiceBox<>();
  private ChoiceBox<String> queriesList = new ChoiceBox();
  private ChoiceBox<String> mathList = new ChoiceBox();
  private ChoiceBox<String> booleanList = new ChoiceBox();
  private ChoiceBox<String> variablesList = new ChoiceBox();
  private ChoiceBox<String> userCommandList = new ChoiceBox();
  private Label helpDescription = new Label();
  Button backButton;
  ImageView helpIcon;
  private final String helpIconLocation = "resources/images/helpicon.png";

  public HelpPane(ResourceBundle resource) {
    this.resources = resource;
    createHelpWindow();
    getChildren().add(vbox);
  }

  private void createHelpWindow() {
    createDisplayWindow();
    createList();
    createDisplayLanguages();
    createListAction();
    createButtonAction();
    setId();
    vbox = new VBox(displayWindow, list);
    vbox.setPadding(new Insets(0, 0, 0, 2));
    vbox.setPrefSize(SIZE, 3 * SIZE);
    vbox.setSpacing(5);
  }

  private void createList() {
    list = new ListView<>();
    ObservableList<ChoiceBox<String>> items =
        FXCollections.observableArrayList(
            commandList, queriesList, mathList, booleanList, variablesList, userCommandList);
    list.setItems(items);
    list.setMinSize(SIZE, 2 * SIZE);
  }

  private void createDisplayLanguages() {
    helpLabel.setText(resources.getString("helpTitle"));
    commandList.getItems().clear();
    commandList.getItems().addAll(resources.getString("commandList").split(","));
    queriesList.getItems().clear();
    queriesList.getItems().addAll(resources.getString("queriesList").split(","));
    mathList.getItems().clear();
    mathList.getItems().addAll(resources.getString("mathList").split(","));
    booleanList.getItems().clear();
    booleanList.getItems().addAll(resources.getString("booleanList").split(","));
    variablesList.getItems().clear();
    variablesList.getItems().addAll(resources.getString("variableList").split(","));
    userCommandList.getItems().clear();
    userCommandList.getItems().addAll(resources.getString("userList").split(","));
    addDefaultChoiceBoxTest();
  }

  private void addDefaultChoiceBoxTest() {
    removeListener();
    commandList.setValue(resources.getString("command"));
    queriesList.setValue(resources.getString("queries"));
    mathList.setValue(resources.getString("math"));
    booleanList.setValue(resources.getString("boolean"));
    variablesList.setValue(resources.getString("variable"));
    userCommandList.setValue(resources.getString("user"));
    createListAction();
  }

  private void createListAction() {
    addHelpText(commandList);
    addHelpText(queriesList);
    addHelpText(booleanList);
    addHelpText(variablesList);
    addHelpText(userCommandList);
    addHelpText(mathList);
  }

  private void addHelpText(ChoiceBox<String> typeList) {

    typeList.setOnAction(
        e -> {
          helpDescription.setText(resources.getString(typeList.getValue()));
          helpDescription.setWrapText(true);
          ;
          vbox.getChildren().remove(list);
          vbox.getChildren().add(helpDescription);
          vbox.getChildren().add(backButton);
        });
  }

  private void createButtonAction() {
    backButton = new Button("Back to Menu");
    backButton.setOnAction(
        e -> {
          vbox.getChildren().clear();
          vbox.getChildren().addAll(displayWindow, list);
          addDefaultChoiceBoxTest();
        });
  }

  private void removeListener() {
    commandList.setOnAction(null);
    queriesList.setOnAction(null);
    mathList.setOnAction(null);
    variablesList.setOnAction(null);
    booleanList.setOnAction(null);
    userCommandList.setOnAction(null);
  }

  private void createDisplayWindow() {
    displayWindow = new GridPane();
    helpIcon =
        new ImageView(new Image(getClass().getResourceAsStream(helpIconLocation)));
    helpIcon.setFitHeight(.3 * SIZE);
    helpIcon.setFitWidth(.4 * SIZE);
    helpLabel = new Label();
    displayWindow.add(helpIcon, 0, 0);
    displayWindow.add(helpLabel, 1, 0);
    displayWindow.setHgap(10);
  }


  /**
   * Sets the language of the components
   */
  public void setResources(ResourceBundle resource) {
    this.resources = resource;
    addDefaultChoiceBoxTest();
    createDisplayLanguages();
  }

  private void setId() {
    commandList.setId("commandList");
    helpLabel.setId("helpLabel");
    helpIcon.setId("helpIcon");
    backButton.setId("backButton");
    helpDescription.setId("helpText");

  }
}
