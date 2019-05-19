#FXToolbox

####A toolbox for making certain operations like scene switching, adding animations and using templates simpler.

Classes:
Window (A custom window for javafx application)
Router (A router for custom Window)
SceneSwitcher (Switching scenes)

#####Example using Window class:
```java
new Window(primaryStage)
    .bind("login", "fxml/login.fxml")
    .bind("date", new DatePicker())
    .setView("login")
    .setTitle("Custom Window")
    .setHeaderStyle("-fx-background-color: lightblue;")
    .show();
```

#####Example using Window with Router to easily switch view:

```java
Router.connect(new Window(primaryStage)
        .bind("login", "fxml/login.fxml")
        .bind("system", new SystemView())
        .setView("login")
        .setTitle("Custom Window")
        .setHeaderStyle("-fx-background-color: lightblue;")
        .show()
);
```
#####Switching a view with Router:

logInButton.setOnAction(event -> Router.switchTo("system"));

Example using SceneSwitcher class:
```java
SceneSwitcher.getInstance("SceneOne", "Application Title")
        .applySwitchable(primaryStage)
        .bindDirectory("fxml", new String[] {"SceneOne", "SceneTwo"})
        .show();
```
SceneSwitcher is used to easily change view of the scene, but only on Stage. If you choose to use Window class, use Router class.
Router holds a SceneSwitcher, but not all methods are usable since Window is not actually a Stage. Its only a BorderPane layout made
to mimic a stage.

#####Control the view of scene:

Preferred setup is to make the application switchable and bind all fxml documents, or classes if you don't use fxml
to keys. Example setup:
```java
SceneSwitcher.getInstance("SceneOne") // the first view to be loaded when application starts (Optional)
        .applySwitchable(primaryStage) // Make application switchable
        .bindDirectory("fxml", new String[] {"SceneOne", "SceneTwo"}) // Bind fxml directory in resources folder to array of keys
        .show(); // Show the stage
```
#####Bind multiple custom classes
```java
SceneSwitcher.getInstance("SceneOne")
        .applySwitchable(primaryStage)
        .bindListOfViews(
                new Parent[] {new BorderPane(), new Login(), new GridPane()},
                new String[] {"BorderPane", "Login", "GridPane"})
        .bindDirectory(
                "fxml",
                new String[] {"SceneOne", "SceneTwo"})
        .show();
```
#####Bind in sequence:
```java
SceneSwitcher.getInstance("Login", "Application Title")
        .applySwitchable(primaryStage)
        .bind("SceneOne", "fxml/scene_one.fxml")
        .bind("SceneTwo", "fxml/scene_two.fxml")
        .bind("BorderPaneOne", new BorderPane())
        .bind("Login", new Login())
        .show();
```
#####How to switch scenes when application is switchable:
```java
switchButton.setOnAction(event -> SceneSwitcher
        .getInstance()
        .changeToScene("SceneTwo")); // The key that points to the requested layout

or, every controller can hold a SceneSwitcher so that one does not need to use the getInstance() all the time.
```
```java
{
private SceneSwitcher ss = SceneSwitcher.getInstance();

    public void initialize() {
        switchButton.setOnAction(event -> ss.changeToScene("SceneTwo"));
    }
}
```
#####Applying scene switching to buttons if there is no key, value bindings:
```java
button1.setOnAction(event -> {
    SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();
    sceneSwitcher.changeToScene(event, "fxml/scene_two.fxml"); // Relative path to resources folder
});
```
```java
button2.setOnAction(event -> {
    try {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/scene_two.fxml"));
        SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();
        sceneSwitcher.changeToScene(event, root); // Using preloaded fxml document
    } catch (IOException e) {
        e.printStackTrace();
    }
});
```
```java
button3.setOnAction(event -> {
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(new Label("It worked!"));
    SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();
    sceneSwitcher.changeToScene(event, borderPane); // Using classes
});
```

