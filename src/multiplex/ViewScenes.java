package multiplex;

public enum ViewScenes {

    LOGIN("fxml-files/LoginPageScene.fxml", "css-files/loginPage.css"),
    REGISTER("fxml-files/CreateUserPageScene.fxml", "css-files/registerPage.css"),
    BROWSE("fxml-files/BrowseScene.fxml", "css-files/browsePage.css");

    private final String fxmlFile;
    private final String cssFile;

    ViewScenes(String fxmlFile, String cssFile) {
        this.fxmlFile = fxmlFile;
        this.cssFile = cssFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

    public String getCssFile() {
        return cssFile;
    }
}