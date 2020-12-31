package Tests;

import Pages.CartsPage;
import Pages.HomePage;
import Pages.OrdersPage;
import Pages.PuppyDetailPage;
import Utils.DriverSetup;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class PuppiesFlows extends DriverSetup {

    SoftAssert asserts = new SoftAssert();

    @BeforeMethod
    public void pageSetup() {
        setupDriver();
    }

    //Ex 1
    @Test
    public void adoptOnePuppy() {
        final int PUPPIES_TO_ADOPT = 1;
        HomePage homePage = navigateHomePage();
        CartsPage cartsPage = adoptPuppies(homePage, PUPPIES_TO_ADOPT);
        OrdersPage ordersPage = cartsPage.completeAdoption();
        String confirmationText = placeOrder(ordersPage,
                "Family guy", "34 Bryan St", "Meg@gmail.com", "Credit card");
        asserts.assertEquals(confirmationText, "Thank you for adopting a puppy!", "Message not found");
        asserts.assertAll();
    }

    //Ex 2
    @Test
    public void adoptTwoPuppies() {
        final int PUPPIES_TO_ADOPT = 2;
        HomePage homePage = navigateHomePage();
        CartsPage cartsPage = adoptPuppies(homePage, PUPPIES_TO_ADOPT);
        OrdersPage ordersPage = cartsPage.completeAdoption();
        String confirmationText = placeOrder(ordersPage,
                "Luis", "10 Groove St", "lm_21@gmail.com", "Credit card");
        asserts.assertEquals(confirmationText, "Thank you for adopting a puppy!", "Message not found");
        asserts.assertAll();
    }

    //Ex 3
    @Test
    public void threePuppiesOnCart() {
        final int PUPPIES_TO_ADOPT = 3;
        HomePage homePage = navigateHomePage();
        CartsPage cartsPage = adoptPuppies(homePage, PUPPIES_TO_ADOPT);
        int noOfPuppiesOnCart = cartsPage.getNumberOfPuppiesOnCart().size();
        System.out.println("Puppies on cart "+noOfPuppiesOnCart);
        asserts.assertEquals(noOfPuppiesOnCart, PUPPIES_TO_ADOPT,
                "There are not "+PUPPIES_TO_ADOPT+" different puppies on cart");
        asserts.assertAll();
    }

    //Ex 4
    @Test
    public void twoPuppiesCorrectAmountOnCart() {
        final int PUPPIES_TO_ADOPT = 2;
        HomePage homePage = navigateHomePage();
        CartsPage cartsPage = adoptPuppies(homePage, PUPPIES_TO_ADOPT);
        double sumOfCosts = cartsPage.sumOfCostsOfPuppies();
        double totalCosts = cartsPage.totalCost();
        asserts.assertEquals(sumOfCosts, totalCosts,
                "The total cost is not the sum of the prices of the puppies");
        asserts.assertAll();
    }

    //Ex 5
    @Test
    public void twoPuppiesCorrectAmountOnCartWithAdditions() {
        final int PUPPIES_TO_ADOPT = 2;
        HomePage homePage = navigateHomePage();
        CartsPage cartsPage = adoptPuppies(homePage, PUPPIES_TO_ADOPT);
        cartsPage.selectAllAdditionsAndServices();
        double sumOfCostsOfPuppies = cartsPage.sumOfCostsOfPuppies();
        double sumOfAdditions = cartsPage.sumAllAdditionalProductsServices();
        double totalCosts = cartsPage.totalCost();
        double sumOfPuppiesAdditionsServices = Double.sum(sumOfCostsOfPuppies, sumOfAdditions);
        asserts.assertEquals(sumOfPuppiesAdditionsServices, totalCosts,
                "The total cost is not the sum of the prices of the puppies and additions");
        asserts.assertAll();
    }

    //Ex 6
    @Test
    public void fourPuppiesDisplayedPerPage() {
        HomePage homePage = navigateHomePage();
        boolean lessThan4Puppies = homePage.verifyNumberOfPuppiesPerPage();
        asserts.assertTrue(lessThan4Puppies, "The amount of puppies per page is more than 4");
        asserts.assertAll();
    }

    private String placeOrder(OrdersPage ordersPage, String name, String address, String email, String payment) {
        HomePage homePage = ordersPage.completeOrder(name, address, email, payment);
        return homePage.getConfirmationLabel().getText();
    }

    private CartsPage adoptPuppies(HomePage homePage, int PUPPIES_TO_ADOPT) {
        List<WebElement> puppies = homePage.getPuppies();
        CartsPage cartsPage = null;
        try {
            for (int dogIndex = 0; dogIndex < PUPPIES_TO_ADOPT; dogIndex++) {
                PuppyDetailPage puppyDetailPage = homePage.selectDogs(puppies.get(dogIndex));
                cartsPage = puppyDetailPage.adoptDog();
                if (dogIndex < PUPPIES_TO_ADOPT - 1) {
                    homePage = cartsPage.adoptAnotherPuppy();
                }
            }
        }catch(NullPointerException ex) {
            ex.printStackTrace();
        }

        return cartsPage;
    }

    @AfterMethod
    public void afterTests() {
        quitDriver();
    }
}
