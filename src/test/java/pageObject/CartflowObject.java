package pageObject;

import org.openqa.selenium.By;
public class CartflowObject {

    public By BlackScreenCloseBTN(){return By.xpath("//button[@data-synth='close-offer-collection']");}
    public By DeliveryLocationTextBox(){return By.xpath("//input[@placeholder='Enter your location for delivery']");}
    public By DeliveryLocationText(){return By.xpath("//div[text()='Lulu Mall Bengaluru']");}

    public By StartYourOrderWithTime(){return By.xpath("//span[text()='Start your order']");}

    public By DealsBTN(){return By.xpath("//a[contains(@class,'active')]/span[text()='Deals']");}
    public By VegetarianSwitchBTN(){return By.xpath("(//span[contains(@class,'grey-light border-grey-light')]/span[@class='rounded-full bg-white'])[1]");}
    public By PizzaMenu(){return By.xpath("(//span[text()='Pizzas'])[2]");}
    public By margherita_Pizza(){return By.xpath("(//button[contains(@data-synth,'margherita')])[1]");}
    public By AddedPizzaAtBasket(){return By.xpath("//a[contains(@href,'margherita')]/../div[@data-synth='basket-item-type--pizza']");}
    public By PizzaPriceAmount(){return By.xpath("//a[contains(@href,'margherita')]/../div[2]");}
    public By RestaurentCharges(){return By.xpath("//span[text()='Restaurant Handling Charges']/../../div[2]");}
    public By TaxPriceAmount(){return By.xpath("//span[text()='Total Tax']/../../span[2]");}
    public By TotalPriceAmount(){return By.xpath("//span[text()='Amount payable']/../../span[2]");}
    public By ItemOptionUnderCheckout(){return By.xpath("(//a[@data-synth='link--checkout'])[2]/span/span[contains(.,'item')]|//button[@data-synth='link--checkout']/span/span[contains(.,'item')]");}
    public By PriceOptionUnderCheckout(String price){return By.xpath("(//a[@data-synth='link--checkout'])[2]//span[text()='"+price+"']");}
    public By DrinksMenu(){return By.xpath("(//span[text()='Drinks'])[2]");}
    public By Pepsi_Drinks(){return By.xpath("(//button[contains(@data-synth,'pepsi')])[1]");}
    public By RemovePizzaBTN(){return By.xpath("//a[contains(@href,'margherita')]/../button[2]");}
    public By CheckoutBTN(){return By.xpath("//span[text()='Checkout']");}
    public By MinOrderRequiredText(){return By.xpath("//span[contains(.,'away from minimum delivery spend')]");}
    public By SidesMenu(){return By.xpath("(//span[text()='Sides'])[2]");}

    public By cheeseBread_Sides(){return By.xpath("(//button[contains(@data-synth,'cheese-garlic-bread')])[1]");}
    public By Mirinda_Drinks(){return By.xpath("(//button[contains(@data-synth,'mirinda')])[1]");}
    public By SecureCheckoutTitle(){return By.xpath("//span[text()='Secure Checkout']");}
    public By OnlinePayment_Default(){return By.xpath("//label[contains(@class,' border-green border-solid')]//span[text()='Online Payment']");}
    public By CashPaymentOption(){return By.xpath("//label[@for='payment-method--cash']/i");}
    public By IAgreeCheckBox(){return By.xpath("//label[@for='marketingOptIn']//input");}
    public By name(){return By.name("name");}
    public By phone(){return By.name("phone");}
    public By email(){return By.name("email");}
    public By GiftCardLink(){return By.xpath("//span[text()='Apply Gift Card']");}
    public By VoucherOption(){return By.xpath("//span[text()='Voucher']");}
    public By VoucherInput(){return By.name("voucherId");}
    public By ReedemBTN(){return By.xpath("//span[text()='Redeem']");}
    public By ErrorVoucher(){return By.xpath("//span[contains(.,'Sorry')]");}
    public By CancelVoucher(){return By.xpath("//button[text()='Cancel']");}
    public By YourBasketTitle(){return By.xpath("//span[text()='Your Basket']");}












}
