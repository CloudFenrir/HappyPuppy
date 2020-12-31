package Pages;

import Utils.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends DriverSetup {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getPuppies() {
        return puppies;
    }

    @FindBy(xpath = "//input[@class='rounded_button']")
    private List<WebElement> puppies;

    @FindBy(xpath = "//form[@action='/puppies/4']//div//input")
    private WebElement viewDetailsBrook;

    @FindBy(xpath = "//form[@action='/puppies/3']//div//input")
    private WebElement viewDetailsHanna2;

    public WebElement getConfirmationLabel() {
        return confirmationLabel;
    }

    @FindBy(id = "notice")
    private WebElement confirmationLabel;

    @FindBy(xpath = "//a[@class='next_page']")
    private WebElement nextPageLink;

    @FindBy(xpath = "//span[@class='next_page disabled']")
    private WebElement nextPageLinkDisabled;

    @FindBy(xpath = "//span[@class='next_page disabled'] | //a[@class='next_page']")
    private WebElement nextPageButton;

    public boolean verifyNumberOfPuppiesPerPage() {
        final int MAX_NUMB_OF_PUPPIES = 4;
        try {
            while (nextPageButton.getAttribute("class").equals("next_page")) {
                if (puppies.size() <= MAX_NUMB_OF_PUPPIES) {
                    nextPageLink.click();
                } else {
                    return false;
                }
            }
            return true;
        } catch (NoSuchElementException ex) {
            ex.getMessage();
        }
        return false;
    }

    public PuppyDetailPage selectPuppie() {
        viewDetailsBrook.click();
        return new PuppyDetailPage(getDriver());
    }

    public PuppyDetailPage select2ndPuppy() {
        viewDetailsHanna2.click();
        return new PuppyDetailPage(getDriver());
    }

    public PuppyDetailPage selectDog(int number) {
        for (int i = 0; i < number; i++) {
            puppies.get(i).click();
            return new PuppyDetailPage(getDriver());
        }
        return null;
    }

    public PuppyDetailPage selectDogs(WebElement puppy) {
        puppy.click();
        return new PuppyDetailPage(getDriver());
    }

}
