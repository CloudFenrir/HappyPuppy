package Pages;

import Utils.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrdersPage extends DriverSetup {

    public OrdersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "order_name")
    private WebElement nameField;

    @FindBy(id = "order_address")
    private WebElement addressField;

    @FindBy(id = "order_email")
    private WebElement emailField;

    @FindBy(id = "order_pay_type")
    private WebElement payTypeSelect;

    @FindBy(xpath = "//input[@name='commit']")
    private WebElement placeOrderButton;

    private void selectPayType(String type) {
        Select payType = new Select(payTypeSelect);
        payType.selectByValue(type);
    }

    private void fillOrderInformation(String name, String address, String email, String payType) {
        nameField.sendKeys(name);
        addressField.sendKeys(address);
        emailField.sendKeys(email);
        selectPayType(payType);
    }

    public HomePage completeOrder(String name, String address, String email, String payType) {
        fillOrderInformation(name, address, email, payType);
        placeOrderButton.click();
        return new HomePage(getDriver());
    }



}
