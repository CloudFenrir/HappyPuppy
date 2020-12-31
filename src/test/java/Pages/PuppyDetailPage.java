package Pages;

import Utils.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PuppyDetailPage extends DriverSetup {

    public PuppyDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//form[@action='/adoptions?puppy_id=4']//div//input[1]")
    private WebElement adoptBrookButton;

    @FindBy(xpath = "//form[@action='/adoptions?puppy_id=3']//div//input[1]")
    private WebElement adoptHanna2Button;

    @FindBy(xpath = "//form[@action='/puppies/1']//div//input[1]")
    private WebElement adoptMaggieMae;

    @FindBy(xpath = "//input[@class='rounded_button']")
    private WebElement adoptDog;


    public CartsPage adoptDog() {
        adoptDog.click();
        return new CartsPage(getDriver());
    }

    public CartsPage adoptBrookButton() {
        adoptBrookButton.click();
        return new CartsPage(getDriver());
    }

    public CartsPage adoptHanna2Button() {
        adoptHanna2Button.click();
        return new CartsPage(getDriver());
    }
}
