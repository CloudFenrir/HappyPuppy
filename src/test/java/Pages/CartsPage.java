package Pages;

import Utils.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartsPage extends DriverSetup {

    public CartsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//form[@action='/orders/new']//div/input")
    private WebElement completeAdoptionButton;

    @FindBy(xpath = "//form[@action='/']//div//input[1]")
    private WebElement adoptAnotherPuppyButton;

    @FindBy(xpath = "//tr//td[1]//img")
    private List<WebElement> NumberOfPuppiesOnCart;

    @FindBy(xpath = "//tr//td[4]//h2")
    private List<WebElement> listOfCostsOfPuppies;

    @FindBy(xpath = "//td[@class='total_cell']//h2")
    private WebElement totalCost;

    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> additionalProductsServices;

    @FindBy(xpath = "//tr//td[3]//div")
    private List<WebElement> listOfCostOfAdditionsAndServices;

    public double sumAllAdditionalProductsServices() {
        double sumOfAdditions = 0;
        for (WebElement additions : listOfCostOfAdditionsAndServices) {
            sumOfAdditions += Double.parseDouble(additions.getText().substring(1));
        }
        return sumOfAdditions;
    }

    public double totalCost() {
        return Double.parseDouble(totalCost.getText().substring(1));
    }

    public double sumOfCostsOfPuppies() {
        double total = 0;
        for (WebElement listOfCost : listOfCostsOfPuppies) {
            total += Double.parseDouble(listOfCost.getText().substring(1));
        }
        return total;
    }

    public OrdersPage completeAdoption() {
        completeAdoptionButton.click();
        return new OrdersPage(getDriver());
    }

    public HomePage adoptAnotherPuppy() {
        adoptAnotherPuppyButton.click();
        return new HomePage(getDriver());
    }

    public List<WebElement> getNumberOfPuppiesOnCart() {
        return NumberOfPuppiesOnCart;
    }

    public void selectAllAdditionsAndServices() {
        for (WebElement boxes : additionalProductsServices) {
            boxes.click();
        }
    }
}
