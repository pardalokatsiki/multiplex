package multiplex;

public enum ViewScenes {

    LOGIN("/multiplex/fxml-files/LoginPageScene.fxml", "/multiplex/css-files/loginPage.css"),
    REGISTER("/multiplex/fxml-files/CreateUserPageScene.fxml", "/multiplex/css-files/registerPage.css"),
    BROWSE("/multiplex/fxml-files/BrowseScene.fxml", "/multiplex/css-files/browsePage.css"),
    DAY("/multiplex/fxml-files/SelectDaysScene.fxml", "/multiplex/css-files/selectDays.css"),
    TIME("/multiplex/fxml-files/SelectTimeScene.fxml", "/multiplex/css-files/selectTime.css"),
    SEAT("/multiplex/fxml-files/SelectSeatScene.fxml", "/multiplex/css-files/selectSeat.css"),
    CANTEEN("/multiplex/fxml-files/CanteenScene.fxml", "/multiplex/css-files/canteenPage.css"),
    PAYMENT("/multiplex/fxml-files/paymentScene.fxml", "/multiplex/css-files/paymentPage.css"),
    RESULT("/multiplex/fxml-files/PaymentResultScene.fxml", "/multiplex/css-files/paymentResult.css");

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