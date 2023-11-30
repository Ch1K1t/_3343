package CouponRedeemSystem.Test;

import CouponRedeemSystem.Account.model.Account;
import CouponRedeemSystem.Coupon.model.Coupon;
import CouponRedeemSystem.Page.AdminPage;
import CouponRedeemSystem.Page.HomePage;
import CouponRedeemSystem.Page.ShopManagerPage;
import CouponRedeemSystem.Page.SigninPage;
import CouponRedeemSystem.Page.StaffPage;
import CouponRedeemSystem.Page.UserPage;
import CouponRedeemSystem.Page.model.Page;
import CouponRedeemSystem.Shop.model.Shop;
import CouponRedeemSystem.System.Util.Util;
import CouponRedeemSystem.Test.model.MainTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class PageTest extends MainTest {

  @Rule
  public TextFromStandardInputStream systemIn = TextFromStandardInputStream.emptyStandardInputStream();

  @Rule
  public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

  private final String userName2 = "accountTest2";
  private final String password2 = "passwordTest2";

  private final String homePageInstruction =
    String.format("\r\nPlease select the command and input the number:\r\n") +
    String.format("1. Signin\r\n") +
    String.format("2. Signup\r\n") +
    String.format("3. Exit\r\n\r\n");

  private final String adminPageInstruction =
    String.format("\r\nPlease select the command and input the number:\r\n") +
    String.format("1. Create Admin Account\r\n") +
    String.format("2. Create Shop Manager Account\r\n") +
    String.format("3. Create Staff Account\r\n") +
    String.format("4. Create User Account\r\n") +
    String.format("5. Delete Account\r\n") +
    String.format("6. Create Redeemable Coupon\r\n") +
    String.format("7. Signout\r\n\r\n");

  private final String shopManagerPageInstruction =
    String.format("\r\nPlease select the command and input the number:\r\n") +
    String.format("1. Create Shop\r\n") +
    String.format("2. Delete Shop\r\n") +
    String.format("3. Create Staff Account\r\n") +
    String.format("4. Delete Staff Account\r\n") +
    String.format("5. Signout\r\n\r\n");

  private final String staffPageInstruction =
    String.format("\r\nPlease select the command and input the number:\r\n") +
    String.format("1. Check Coupon\r\n") +
    String.format("2. Create Purchasable Coupon\r\n") +
    String.format("3. Delete Coupon\r\n") +
    String.format("4. Check Discount\r\n") +
    String.format("5. Create Discount\r\n") +
    String.format("6. Delete Discount\r\n") +
    String.format("7. Signout\r\n\r\n");

  @Before
  @After
  public void reset() {
    Page.setS(null);
    Util.clearSystem();
  }

  @Test
  public void strInputTest() {
    String input = "test";
    systemIn.provideLines(input);

    Page page = new HomePage();
    String result = page.strInput(fieldName);
    String expectedOutput = String.format(
      "\r\nPlease input the " + fieldName + ":\r\n"
    );

    Assert.assertEquals(input, result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void strInputMultipleTest() {
    String input = "test";
    systemIn.provideLines("", input);

    Page page = new HomePage();
    String result = page.strInput(fieldName);
    String expectedOutput =
      String.format("\r\nPlease input the " + fieldName + ":\r\n") +
      String.format("Please input the " + fieldName + ":\r\n");

    Assert.assertEquals(input, result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void intInputTest() {
    String input = "1";
    systemIn.provideLines(input);

    Page page = new HomePage();
    int result = page.intInput(fieldName);
    String expectedOutput = String.format(
      "\r\nPlease input the " + fieldName + ":\r\n"
    );

    Assert.assertEquals(Integer.parseInt(input), result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void intInputMultipleTest() {
    String input = "a\r\n1";
    systemIn.provideLines(input);

    Page page = new HomePage();
    int result = page.intInput(fieldName);
    String expectedOutput =
      String.format("\r\nPlease input the " + fieldName + ":\r\n") +
      String.format("Invalid value, please input again:\r\n");

    Assert.assertEquals(1, result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void doubleInputTest() {
    String input = "1.0";
    systemIn.provideLines(input);

    Page page = new HomePage();
    double result = page.doubleInput(fieldName);
    String expectedOutput = String.format(
      "\r\nPlease input the " + fieldName + ":\r\n"
    );

    Assert.assertEquals(Double.parseDouble(input), result, 0.0);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void doubleInputMultipleTest() {
    String input = "a\r\n1.0";
    systemIn.provideLines(input);

    Page page = new HomePage();
    double result = page.doubleInput(fieldName);
    String expectedOutput =
      String.format("\r\nPlease input the " + fieldName + ":\r\n") +
      String.format("Invalid value, please input again:\r\n");

    Assert.assertEquals(1.0, result, 0.0);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void telInputTest() {
    String input = "12345678";
    systemIn.provideLines(input);

    Page page = new HomePage();
    String result = page.telInput();
    String expectedOutput = String.format(
      "\r\nPlease input the telephone number:\r\n"
    );

    Assert.assertEquals(input, result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void telInputMultipleTest() {
    String input = "a\r\n12345678";
    systemIn.provideLines(input);

    Page page = new HomePage();
    String result = page.telInput();
    String expectedOutput =
      String.format("\r\nPlease input the telephone number:\r\n") +
      String.format("Please input a 8 digit telephone number:\r\n");

    Assert.assertEquals("12345678", result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void beforeDateInputTest() {
    String input = "01/01/2000";
    systemIn.provideLines(input);

    Page page = new HomePage();
    String result = page.beforeDateInput(fieldName);
    String expectedOutput = String.format(
      "\r\nPlease input the " + fieldName + " (dd/MM/yyyy):\r\n"
    );

    Assert.assertEquals(input, result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void beforeDateInputMultipleTest() {
    String input = "a\r\n01/01/2100\r\n01/01/2000";
    systemIn.provideLines(input);

    Page page = new HomePage();
    String result = page.beforeDateInput(fieldName);
    String expectedOutput =
      String.format(
        "\r\nPlease input the " + fieldName + " (dd/MM/yyyy):\r\n"
      ) +
      String.format("Invalid date format, please input again:\r\n") +
      String.format("Date must be before today, please input again:\r\n");

    Assert.assertEquals("01/01/2000", result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void afterDateInputTest() {
    String input = "01/01/2100";
    systemIn.provideLines(input);

    Page page = new HomePage();
    String result = page.afterDateInput(fieldName);
    String expectedOutput = String.format(
      "\r\nPlease input the " + fieldName + " (dd/MM/yyyy):\r\n"
    );

    Assert.assertEquals(input, result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void afterDateInputMultipleTest() {
    String input = "a\r\n01/01/2000\r\n01/01/2100";
    systemIn.provideLines(input);

    Page page = new HomePage();
    String result = page.afterDateInput(fieldName);
    String expectedOutput =
      String.format(
        "\r\nPlease input the " + fieldName + " (dd/MM/yyyy):\r\n"
      ) +
      String.format("Invalid date format, please input again:\r\n") +
      String.format("Date must be after today, please input again:\r\n");

    Assert.assertEquals("01/01/2100", result);

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // create User account
  @Test
  public void createAccountTest() {
    systemIn.provideLines(userName, password, dateOfBirth, telNo);

    Page page = new HomePage();
    page.createAccount("User");
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nPlease input the date of birth (dd/MM/yyyy):\r\n") +
      String.format("\r\nPlease input the telephone number:\r\n") +
      String.format("\r\nAccount created\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // create Staff account with correct shop name
  @Test
  public void createAccountTest2() {
    shopManager.createShop(shopName);
    systemIn.provideLines(userName, password, shopName);

    Page page = new HomePage();
    page.createAccount("Staff");
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nAccount created\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // create Staff account with incorrect shop name
  @Test
  public void createAccountTest3() {
    shopManager.createShop(shopName);
    systemIn.provideLines(userName, password, "incorrectShop", shopName);

    Page page = new HomePage();
    page.createAccount("Staff");
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nShop incorrectShop not found!\r\n") +
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nAccount created\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // create Admin account
  @Test
  public void createAccountTest4() {
    systemIn.provideLines(userName, password);

    Page page = new HomePage();
    page.createAccount("Admin");
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nAccount created\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // create Shop Manager account
  @Test
  public void createAccountTest5() {
    systemIn.provideLines(userName, password);

    Page page = new HomePage();
    page.createAccount("Shop Manager");
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nAccount created\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // create existing account
  @Test
  public void createAccountTestFail() {
    passwordManager.createNewPassword(userName, password);
    systemIn.provideLines(userName, password);

    Page page = new HomePage();
    page.createAccount("User");
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nUser already exists\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void signinTest() {
    passwordManager.createNewPassword(userName, password);
    systemIn.provideLines(userName, password);

    SigninPage page = new SigninPage();
    page.signin();
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // signin with non-existing account
  @Test
  public void signinTestFail() {
    systemIn.provideLines(userName, password);

    SigninPage page = new SigninPage();
    page.signin();
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nAccount is not found!\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // signin with incorrect password
  @Test
  public void signinTestFail2() {
    passwordManager.createNewPassword(userName, password);
    systemIn.provideLines(userName, "incorrectPassword");

    SigninPage page = new SigninPage();
    page.signin();
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nPassword is not correct!\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void deleteAccountTest() {
    accountManager.createAccount(userName, "Admin");
    systemIn.provideLines(userName);

    AdminPage page = new AdminPage();
    page.deleteAccount();
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nAccount deleted\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void deleteAccountTestFail() {
    systemIn.provideLines(userName);

    AdminPage page = new AdminPage();
    page.deleteAccount();
    String expectedOutput =
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nAccount not found\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void createRedeemableCouponTest() {
    systemIn.provideLines(
      "rCouponTest",
      Double.toString(intrinsicValue),
      expirationDate
    );

    AdminPage page = new AdminPage();
    page.createRedeemableCoupon();
    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nPlease input the coupon's intrinsic value:\r\n") +
      String.format(
        "\r\nPlease input the coupon's expiration date (dd/MM/yyyy):\r\n"
      ) +
      String.format("\r\nCoupon created\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void createRedeemableCouponTestFail() {
    String couponCode = "rCouponTest";
    String type = "Redeemable";

    couponManager.createCoupon(
      couponCode,
      intrinsicValue,
      type,
      expirationDate
    );
    systemIn.provideLines(couponCode);

    AdminPage page = new AdminPage();
    page.createRedeemableCoupon();
    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon already exists\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void createShopTest() {
    systemIn.provideLines(shopName);

    ShopManagerPage page = new ShopManagerPage();
    page.createShop();
    String expectedOutput =
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nShop created\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void createShopTestFail() {
    shopManager.createShop(shopName);
    systemIn.provideLines(shopName);

    ShopManagerPage page = new ShopManagerPage();
    page.createShop();
    String expectedOutput =
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nShop already exists\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void deleteShopTest() {
    shopManager.createShop(shopName);
    systemIn.provideLines(shopName);

    ShopManagerPage page = new ShopManagerPage();
    page.deleteShop();
    String expectedOutput =
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nShop deleted\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void deleteShopTestFail() {
    systemIn.provideLines(shopName);

    ShopManagerPage page = new ShopManagerPage();
    page.deleteShop();
    String expectedOutput =
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nShop not found\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void shopManagerDeleteAccountTest() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);
    systemIn.provideLines(userName);

    ShopManagerPage page = new ShopManagerPage();
    page.deleteAccount();
    String expectedOutput =
      String.format("\r\nPlease input the staff name:\r\n") +
      String.format("\r\nAccount deleted\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // delete non-existing account
  @Test
  public void shopManagerDeleteAccountTestFail() {
    systemIn.provideLines(userName);

    ShopManagerPage page = new ShopManagerPage();
    page.deleteAccount();
    String expectedOutput =
      String.format("\r\nPlease input the staff name:\r\n") +
      String.format("\r\nAccount not found\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // delete account that is not a staff account
  @Test
  public void shopManagerDeleteAccountTestFail2() {
    accountManager.createAccount(userName, "Admin");
    systemIn.provideLines(userName);

    ShopManagerPage page = new ShopManagerPage();
    page.deleteAccount();
    String expectedOutput =
      String.format("\r\nPlease input the staff name:\r\n") +
      String.format("\r\nThis account is not a staff account\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Have coupon
  @Test
  public void checkCouponTest() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    couponManager.createCoupon(
      "pCouponTest2",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    shop.addPurchasableCoupon("pCouponTest");
    shop.addPurchasableCoupon("pCouponTest2");
    shopManager.updateShop(shop);

    StaffPage page = new StaffPage(userName);
    page.checkCoupon();

    String expectedOutput =
      String.format("\r\nCoupon Code: pCouponTest\r\n") +
      String.format("Coupon Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("Coupon Purchasing Value: " + purchasingValue + "\r\n") +
      String.format("Coupon Expiration Date: " + expirationDate + "\r\n") +
      String.format("\r\nCoupon Code: pCouponTest2\r\n") +
      String.format("Coupon Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("Coupon Purchasing Value: " + purchasingValue + "\r\n") +
      String.format("Coupon Expiration Date: " + expirationDate + "\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // No coupon
  @Test
  public void checkCouponTest2() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    StaffPage page = new StaffPage(userName);
    page.checkCoupon();

    String expectedOutput = String.format("\r\nThere is no coupon\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void createPurchasableCouponTest() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    systemIn.provideLines(
      "pCouponTest",
      Double.toString(intrinsicValue),
      Double.toString(purchasingValue),
      expirationDate
    );

    StaffPage page = new StaffPage(userName);
    page.createPurchasableCoupon();

    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nPlease input the coupon's intrinsic value:\r\n") +
      String.format("\r\nPlease input the coupon's purchasing value:\r\n") +
      String.format(
        "\r\nPlease input the coupon's expiration date (dd/MM/yyyy):\r\n"
      ) +
      String.format("\r\nCoupon created\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void createPurchasableCouponTestFail() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    systemIn.provideLines("pCouponTest");

    StaffPage page = new StaffPage(userName);
    page.createPurchasableCoupon();

    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon already exists\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void deleteCouponTest() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    systemIn.provideLines("pCouponTest");

    StaffPage page = new StaffPage(userName);
    page.deleteCoupon();

    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon deleted\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void deleteCouponTestFail() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    systemIn.provideLines("pCouponTest");

    StaffPage page = new StaffPage(userName);
    page.deleteCoupon();

    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon not found\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Have discount
  @Test
  public void checkDiscountTest() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    discountManager.createDiscount(discountName, shop, value, startDate, day);
    discountManager.createDiscount(
      "discountTest2",
      shop,
      value,
      startDate,
      day
    );
    shop.addDiscount(discountName);
    shop.addDiscount("discountTest2");
    shopManager.updateShop(shop);

    StaffPage page = new StaffPage(userName);
    page.checkDiscount();

    String expectedOutput =
      String.format("\r\nDiscount Name: " + discountName + "\r\n") +
      String.format("Discount Value: " + value + "\r\n") +
      String.format("Discount Start Date: " + startDate + "\r\n") +
      String.format("Discount End Date: " + endDate + "\r\n") +
      String.format("\r\nDiscount Name: discountTest2\r\n") +
      String.format("Discount Value: " + value + "\r\n") +
      String.format("Discount Start Date: " + startDate + "\r\n") +
      String.format("Discount End Date: " + endDate + "\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // No discount
  @Test
  public void checkDiscountTest2() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    StaffPage page = new StaffPage(userName);
    page.checkDiscount();

    String expectedOutput = String.format("\r\nThere is no discount\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void createDiscountTest() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    systemIn.provideLines(
      discountName,
      Double.toString(value),
      startDate,
      Integer.toString(day)
    );

    StaffPage page = new StaffPage(userName);
    page.createDiscount();

    String expectedOutput =
      String.format("\r\nPlease input the discount's name:\r\n") +
      String.format("\r\nPlease input the discount's value:\r\n") +
      String.format(
        "\r\nPlease input the discount's start date (dd/MM/yyyy):\r\n"
      ) +
      String.format("\r\nPlease input the discount's duration in day:\r\n") +
      String.format("\r\nDiscount created\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void createDiscountTestFail() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);
    discountManager.createDiscount(discountName, shop, value, startDate, day);

    systemIn.provideLines(
      discountName,
      Double.toString(value),
      startDate,
      Integer.toString(day)
    );

    StaffPage page = new StaffPage(userName);
    page.createDiscount();

    String expectedOutput =
      String.format("\r\nPlease input the discount's name:\r\n") +
      String.format("\r\nDiscount already exists\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void deleteDiscountTest() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shop.addDiscount(discountName);
    shopManager.updateShop(shop);
    discountManager.createDiscount(discountName, shop, value, startDate, day);

    systemIn.provideLines(discountName);

    StaffPage page = new StaffPage(userName);
    page.deleteDiscount();

    String expectedOutput =
      String.format("\r\nPlease input the discount's name:\r\n") +
      String.format("\r\nDiscount deleted\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void deleteDiscountTestFail() {
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shop.addDiscount(discountName);
    shopManager.updateShop(shop);

    systemIn.provideLines(discountName);

    StaffPage page = new StaffPage(userName);
    page.deleteDiscount();

    String expectedOutput =
      String.format("\r\nPlease input the discount's name:\r\n") +
      String.format("\r\nDiscount not found\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void checkRemainingPointsTest() {
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);
    systemIn.provideLines(userName);

    UserPage page = new UserPage(userName);
    page.checkRemainingPoints();

    String expectedOutput = String.format(
      "\r\nYour remaining points is: 0.0\r\n"
    );

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Have coupon
  @Test
  public void getPurchasableCouponListTest() {
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);
    Shop shop = shopManager.createShop(shopName);
    shop.addPurchasableCoupon("pCouponTest");
    shopManager.updateShop(shop);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );

    UserPage page = new UserPage(userName);
    page.getPurchasableCouponList();

    String expectedOutput =
      String.format("\r\nYour balance is: 0.0\r\n") +
      String.format("\r\nThe available coupons are:\r\n") +
      String.format("\r\nShop " + shopName + ":\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Required Points: " + purchasingValue + "\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Have coupon with discount
  @Test
  public void getPurchasableCouponListTest2() {
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);
    Shop shop = shopManager.createShop(shopName);
    shop.addPurchasableCoupon("pCouponTest");
    shop.addDiscount(discountName);
    shopManager.updateShop(shop);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    discountManager.createDiscount(discountName, shop, value, startDate, day);

    UserPage page = new UserPage(userName);
    page.getPurchasableCouponList();

    String expectedOutput =
      String.format("\r\nYour balance is: 0.0\r\n") +
      String.format("\r\nThe available coupons are:\r\n") +
      String.format("\r\nShop " + shopName + ":\r\n") +
      String.format(
        "This shop is holding a discount event, for each coupon, it will be discounted by " +
        (int) value +
        " points\r\n"
      ) +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("%-30s", "Original Points: " + purchasingValue) +
      String.format("After Discount: " + (purchasingValue - value) + "\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // No coupon
  @Test
  public void getPurchasableCouponListTest3() {
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);
    shopManager.createShop(shopName);

    UserPage page = new UserPage(userName);
    page.getPurchasableCouponList();

    String expectedOutput = String.format(
      "\r\nThere is no coupon to purchase\r\n"
    );

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void purchaseCouponTest() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    account.addPoints(purchasingValue + 10.0);
    accountManager.updateAccount(account);
    Shop shop = shopManager.createShop(shopName);
    shop.addPurchasableCoupon("pCouponTest");
    shopManager.updateShop(shop);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );

    systemIn.provideLines("pCouponTest");

    UserPage page = new UserPage(userName);
    page.purchaseCoupon();

    String expectedOutput =
      String.format("\r\nYour balance is: " + (purchasingValue + 10) + "\r\n") +
      String.format("\r\nThe available coupons are:\r\n") +
      String.format("\r\nShop " + shopName + ":\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Required Points: " + purchasingValue + "\r\n") +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon purchased\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
    Account updatedAccount = accountManager.getAccount(userName);
    Assert.assertEquals(10, updatedAccount.getPoints(), 0.0);
    Assert.assertEquals("pCouponTest", updatedAccount.getCouponIDs().get(0));
  }

  // No coupon
  @Test
  public void purchaseCouponTestFail() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    account.addPoints(purchasingValue);
    accountManager.updateAccount(account);
    Shop shop = shopManager.createShop(shopName);
    shop.addPurchasableCoupon("pCouponTest");
    shopManager.updateShop(shop);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );

    systemIn.provideLines("pCouponTest2");

    UserPage page = new UserPage(userName);
    page.purchaseCoupon();

    String expectedOutput =
      String.format("\r\nYour balance is: " + purchasingValue + "\r\n") +
      String.format("\r\nThe available coupons are:\r\n") +
      String.format("\r\nShop " + shopName + ":\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Required Points: " + purchasingValue + "\r\n") +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon not found\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Wrong coupon type
  @Test
  public void purchaseCouponTestFail2() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    account.addPoints(purchasingValue);
    accountManager.updateAccount(account);
    Shop shop = shopManager.createShop(shopName);
    shop.addPurchasableCoupon("pCouponTest");
    shopManager.updateShop(shop);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    couponManager.createCoupon(
      "rCouponTest",
      intrinsicValue,
      "Redeemable",
      expirationDate
    );

    systemIn.provideLines("rCouponTest");

    UserPage page = new UserPage(userName);
    page.purchaseCoupon();

    String expectedOutput =
      String.format("\r\nYour balance is: " + purchasingValue + "\r\n") +
      String.format("\r\nThe available coupons are:\r\n") +
      String.format("\r\nShop " + shopName + ":\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Required Points: " + purchasingValue + "\r\n") +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nThis coupon is not purchasable\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Insufficient points
  @Test
  public void purchaseCouponTestFail3() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    accountManager.updateAccount(account);
    Shop shop = shopManager.createShop(shopName);
    shop.addPurchasableCoupon("pCouponTest");
    shopManager.updateShop(shop);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );

    systemIn.provideLines("pCouponTest");

    UserPage page = new UserPage(userName);
    page.purchaseCoupon();

    String expectedOutput =
      String.format("\r\nYour balance is: 0.0\r\n") +
      String.format("\r\nThe available coupons are:\r\n") +
      String.format("\r\nShop " + shopName + ":\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Required Points: " + purchasingValue + "\r\n") +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nInsufficient points\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Expired coupon
  @Test
  public void purchaseCouponTestFail4() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    account.addPoints(purchasingValue);
    accountManager.updateAccount(account);
    Shop shop = shopManager.createShop(shopName);
    shop.addPurchasableCoupon("pCouponTest");
    shopManager.updateShop(shop);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    couponManager.createCoupon(
      "pCouponTest2",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      "01/01/2000"
    );

    systemIn.provideLines("pCouponTest2");

    UserPage page = new UserPage(userName);
    page.purchaseCoupon();

    String expectedOutput =
      String.format("\r\nYour balance is: " + purchasingValue + "\r\n") +
      String.format("\r\nThe available coupons are:\r\n") +
      String.format("\r\nShop " + shopName + ":\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Required Points: " + purchasingValue + "\r\n") +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon has expired\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void redeemCouponTest() {
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);
    Coupon coupon = couponManager.createCoupon(
      "rCouponTest",
      intrinsicValue,
      "Redeemable",
      expirationDate
    );

    systemIn.provideLines("rCouponTest");

    UserPage page = new UserPage(userName);
    page.redeemCoupon();

    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon redeemed\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
    Account updatedAccount = accountManager.getAccount(userName);
    Assert.assertEquals(
      coupon.pointConversion(),
      updatedAccount.getPoints(),
      0.0
    );
  }

  // No coupon
  @Test
  public void redeemCouponTestFail() {
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);

    systemIn.provideLines("rCouponTest");

    UserPage page = new UserPage(userName);
    page.redeemCoupon();

    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon not found\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Used coupon
  @Test
  public void redeemCouponTestFail2() {
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);
    Coupon coupon = couponManager.createCoupon(
      "rCouponTest",
      intrinsicValue,
      "Redeemable",
      expirationDate
    );
    coupon.setActive(false);
    couponManager.updateCoupon(coupon);

    systemIn.provideLines("rCouponTest");

    UserPage page = new UserPage(userName);
    page.redeemCoupon();

    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon has been used!\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Expired coupon
  @Test
  public void redeemCouponTestFail3() {
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);
    couponManager.createCoupon(
      "rCouponTest",
      intrinsicValue,
      "Redeemable",
      "01/01/2000"
    );

    systemIn.provideLines("rCouponTest");

    UserPage page = new UserPage(userName);
    page.redeemCoupon();

    String expectedOutput =
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon has expired!\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void getOwnedCouponListTest() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    Shop shop = shopManager.createShop(shopName);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    couponManager.createCoupon(
      "pCouponTest2",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );

    account.addCouponID("pCouponTest");
    account.addCouponID("pCouponTest2");
    accountManager.updateAccount(account);

    UserPage page = new UserPage(userName);
    page.getOwnedCouponList();

    String expectedOutput =
      String.format("\r\nYour owned coupons are:\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("%-30s", "Code: pCouponTest2") +
      String.format("Intrinsic Value: " + intrinsicValue + "\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // No coupon
  @Test
  public void getOwnedCouponListTest2() {
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);

    UserPage page = new UserPage(userName);
    page.getOwnedCouponList();

    String expectedOutput = String.format("\r\nYou have no coupon\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void useCouponTest() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    Shop shop = shopManager.createShop(shopName);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    couponManager.createCoupon(
      "pCouponTest2",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );

    account.addCouponID("pCouponTest");
    account.addCouponID("pCouponTest2");
    accountManager.updateAccount(account);

    systemIn.provideLines("pCouponTest");

    UserPage page = new UserPage(userName);
    page.useCoupon();

    String expectedOutput =
      String.format("\r\nYour owned coupons are:\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("%-30s", "Code: pCouponTest2") +
      String.format("Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon used\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
    Account updatedAccount = accountManager.getAccount(userName);
    Assert.assertEquals("pCouponTest2", updatedAccount.getCouponIDs().get(0));
  }

  // Wrong coupon
  @Test
  public void useCouponTestFail() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    Shop shop = shopManager.createShop(shopName);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    account.addCouponID("pCouponTest");
    accountManager.updateAccount(account);

    systemIn.provideLines("pCouponTest2");

    UserPage page = new UserPage(userName);
    page.useCoupon();

    String expectedOutput =
      String.format("\r\nYour owned coupons are:\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon not found\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Used coupon
  @Test
  public void useCouponTestFail2() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    Shop shop = shopManager.createShop(shopName);
    Coupon coupon = couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    coupon.setActive(false);
    couponManager.updateCoupon(coupon);

    account.addCouponID("pCouponTest");
    accountManager.updateAccount(account);

    systemIn.provideLines("pCouponTest");

    UserPage page = new UserPage(userName);
    page.useCoupon();

    String expectedOutput =
      String.format("\r\nYour owned coupons are:\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon has been used!\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Expired coupon
  @Test
  public void useCouponTestFail3() {
    Account account = accountManager.createAccount(
      userName,
      "User",
      dateOfBirth,
      telNo
    );
    Shop shop = shopManager.createShop(shopName);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    couponManager.createCoupon(
      "pCouponTest2",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      "01/01/2000"
    );
    account.addCouponID("pCouponTest");
    account.addCouponID("pCouponTest2");
    accountManager.updateAccount(account);

    systemIn.provideLines("pCouponTest2");

    UserPage page = new UserPage(userName);
    page.useCoupon();

    String expectedOutput =
      String.format("\r\nYour owned coupons are:\r\n") +
      String.format("%-30s", "Code: pCouponTest") +
      String.format("Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon has expired!\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeHome2() {
    systemIn.provideLines("2", userName, password, dateOfBirth, telNo, "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nPlease input the date of birth (dd/MM/yyyy):\r\n") +
      String.format("\r\nPlease input the telephone number:\r\n") +
      String.format("\r\nAccount created\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeHome2Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "User", dateOfBirth, telNo);

    systemIn.provideLines("2", userName, password, "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nUser already exists\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeAdmin1And2() {
    for (int i = 1; i < 3; i++) {
      accountManager.createPassword(userName, password);
      accountManager.createAccount(userName, "Admin");

      systemIn.provideLines(
        "1",
        userName,
        password,
        Integer.toString(i),
        userName2,
        password2,
        "7",
        "3"
      );

      HomePage page = new HomePage();
      page.execute();

      String expectedOutput =
        String.format("Welcome to Coupon Redeem System\r\n") +
        String.format(
          "\r\nPlease select the command and input the number:\r\n"
        ) +
        String.format("1. Signin\r\n") +
        String.format("2. Signup\r\n") +
        String.format("3. Exit\r\n\r\n") +
        String.format("\r\nPlease input the user name:\r\n") +
        String.format("\r\nPlease input the password:\r\n") +
        String.format("\r\nSignin successfully\r\n") +
        adminPageInstruction +
        String.format("\r\nPlease input the user name:\r\n") +
        String.format("\r\nPlease input the password:\r\n") +
        String.format("\r\nAccount created\r\n") +
        adminPageInstruction +
        String.format("Signout successfully\r\n") +
        String.format(
          "\r\nPlease select the command and input the number:\r\n"
        ) +
        String.format("1. Signin\r\n") +
        String.format("2. Signup\r\n") +
        String.format("3. Exit\r\n\r\n") +
        String.format("\r\nThank you for using Coupon Redeem System\r\n") +
        String.format("Goodbye\r\n\r\n");

      Assert.assertEquals(expectedOutput, systemOutRule.getLog());
      systemOutRule.clearLog();
      reset();
    }
  }

  @Test
  public void executeAdmin3() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Admin");
    shopManager.createShop(shopName);

    systemIn.provideLines(
      "1",
      userName,
      password,
      "3",
      userName2,
      password2,
      shopName,
      "7",
      "3"
    );

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      adminPageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nAccount created\r\n") +
      adminPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeAdmin5() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Admin");
    accountManager.createAccount(userName2, "Admin");

    systemIn.provideLines("1", userName, password, "5", userName2, "7", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      adminPageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nAccount deleted\r\n") +
      adminPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeAdmin5Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Admin");

    systemIn.provideLines("1", userName, password, "5", userName2, "7", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      adminPageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nAccount not found\r\n") +
      adminPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeAdmin6() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Admin");

    systemIn.provideLines(
      "1",
      userName,
      password,
      "6",
      "rCouponTest",
      Double.toString(intrinsicValue),
      expirationDate,
      "7",
      "3"
    );

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      adminPageInstruction +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nPlease input the coupon's intrinsic value:\r\n") +
      String.format(
        "\r\nPlease input the coupon's expiration date (dd/MM/yyyy):\r\n"
      ) +
      String.format("\r\nCoupon created\r\n") +
      adminPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeAdmin6Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Admin");
    couponManager.createCoupon(
      "rCouponTest",
      intrinsicValue,
      "Redeemable",
      expirationDate
    );

    systemIn.provideLines(
      "1",
      userName,
      password,
      "6",
      "rCouponTest",
      "7",
      "3"
    );

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      adminPageInstruction +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon already exists\r\n") +
      adminPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeShopManager1() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Shop Manager");

    systemIn.provideLines("1", userName, password, "1", shopName, "5", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      shopManagerPageInstruction +
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nShop created\r\n") +
      shopManagerPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeShopManager1Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Shop Manager");
    shopManager.createShop(shopName);

    systemIn.provideLines("1", userName, password, "1", shopName, "5", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      shopManagerPageInstruction +
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nShop already exists\r\n") +
      shopManagerPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeShopManager2() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Shop Manager");
    shopManager.createShop(shopName);

    systemIn.provideLines("1", userName, password, "2", shopName, "5", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      shopManagerPageInstruction +
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nShop deleted\r\n") +
      shopManagerPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeShopManager2Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Shop Manager");

    systemIn.provideLines("1", userName, password, "2", shopName, "5", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      shopManagerPageInstruction +
      String.format("\r\nPlease input the shop name:\r\n") +
      String.format("\r\nShop not found\r\n") +
      shopManagerPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeShopManager4() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Shop Manager");
    accountManager.createAccount(userName2, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName2);
    shopManager.updateShop(shop);

    systemIn.provideLines("1", userName, password, "4", userName2, "5", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      shopManagerPageInstruction +
      String.format("\r\nPlease input the staff name:\r\n") +
      String.format("\r\nAccount deleted\r\n") +
      shopManagerPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // No staff
  @Test
  public void executeShopManager4Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Shop Manager");

    systemIn.provideLines("1", userName, password, "4", userName2, "5", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      shopManagerPageInstruction +
      String.format("\r\nPlease input the staff name:\r\n") +
      String.format("\r\nAccount not found\r\n") +
      shopManagerPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // Not staff
  @Test
  public void executeShopManager4Fail2() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Shop Manager");
    accountManager.createAccount(userName2, "Admin");

    systemIn.provideLines("1", userName, password, "4", userName2, "5", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      shopManagerPageInstruction +
      String.format("\r\nPlease input the staff name:\r\n") +
      String.format("\r\nThis account is not a staff account\r\n") +
      shopManagerPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeStaff1() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );

    couponManager.createCoupon(
      "pCouponTest2",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );

    shop.addStaff(userName);
    shop.addPurchasableCoupon("pCouponTest");
    shop.addPurchasableCoupon("pCouponTest2");
    shopManager.updateShop(shop);

    systemIn.provideLines("1", userName, password, "1", "7", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nCoupon Code: pCouponTest\r\n") +
      String.format("Coupon Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("Coupon Purchasing Value: " + purchasingValue + "\r\n") +
      String.format("Coupon Expiration Date: " + expirationDate + "\r\n") +
      String.format("\r\nCoupon Code: pCouponTest2\r\n") +
      String.format("Coupon Intrinsic Value: " + intrinsicValue + "\r\n") +
      String.format("Coupon Purchasing Value: " + purchasingValue + "\r\n") +
      String.format("Coupon Expiration Date: " + expirationDate + "\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // No coupon
  @Test
  public void executeStaff1_2() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);

    shop.addStaff(userName);
    shopManager.updateShop(shop);

    systemIn.provideLines("1", userName, password, "1", "7", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nThere is no coupon\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeStaff2() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    systemIn.provideLines(
      "1",
      userName,
      password,
      "2",
      "pCouponTest",
      Double.toString(intrinsicValue),
      Double.toString(purchasingValue),
      expirationDate,
      "7",
      "3"
    );

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nPlease input the coupon's intrinsic value:\r\n") +
      String.format("\r\nPlease input the coupon's purchasing value:\r\n") +
      String.format(
        "\r\nPlease input the coupon's expiration date (dd/MM/yyyy):\r\n"
      ) +
      String.format("\r\nCoupon created\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeStaff2Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    shop.addStaff(userName);
    shop.addPurchasableCoupon("pCouponTest");
    shopManager.updateShop(shop);

    systemIn.provideLines(
      "1",
      userName,
      password,
      "2",
      "pCouponTest",
      "7",
      "3"
    );

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon already exists\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    String actualOutput = systemOutRule.getLog();
    Assert.assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void executeStaff3() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    couponManager.createCoupon(
      "pCouponTest",
      intrinsicValue,
      purchasingValue,
      shop,
      "Purchasable",
      expirationDate
    );
    shop.addStaff(userName);
    shop.addPurchasableCoupon("pCouponTest");
    shopManager.updateShop(shop);

    systemIn.provideLines(
      "1",
      userName,
      password,
      "3",
      "pCouponTest",
      "7",
      "3"
    );

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon deleted\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeStaff3Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    systemIn.provideLines(
      "1",
      userName,
      password,
      "3",
      "pCouponTest",
      "7",
      "3"
    );

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nPlease input the coupon's code:\r\n") +
      String.format("\r\nCoupon not found\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    String actualOutput = systemOutRule.getLog();
    Assert.assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void executeStaff4() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    discountManager.createDiscount(discountName, shop, value, startDate, day);
    discountManager.createDiscount(
      discountName + "2",
      shop,
      value,
      startDate,
      day
    );
    shop.addStaff(userName);
    shop.addDiscount(discountName);
    shop.addDiscount(discountName + "2");
    shopManager.updateShop(shop);

    systemIn.provideLines("1", userName, password, "4", "7", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nDiscount Name: " + discountName + "\r\n") +
      String.format("Discount Value: " + value + "\r\n") +
      String.format("Discount Start Date: " + startDate + "\r\n") +
      String.format("Discount End Date: " + endDate + "\r\n") +
      String.format("\r\nDiscount Name: " + discountName + "2\r\n") +
      String.format("Discount Value: " + value + "\r\n") +
      String.format("Discount Start Date: " + startDate + "\r\n") +
      String.format("Discount End Date: " + endDate + "\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  // No discount
  @Test
  public void executeStaff4_2() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    systemIn.provideLines("1", userName, password, "4", "7", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nThere is no discount\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeStaff5() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    systemIn.provideLines(
      "1",
      userName,
      password,
      "5",
      discountName,
      Double.toString(value),
      startDate,
      Integer.toString(day),
      "7",
      "3"
    );

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nPlease input the discount's name:\r\n") +
      String.format("\r\nPlease input the discount's value:\r\n") +
      String.format(
        "\r\nPlease input the discount's start date (dd/MM/yyyy):\r\n"
      ) +
      String.format("\r\nPlease input the discount's duration in day:\r\n") +
      String.format("\r\nDiscount created\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeStaff5Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    discountManager.createDiscount(discountName, shop, value, startDate, day);
    shop.addStaff(userName);
    shop.addDiscount(discountName);
    shopManager.updateShop(shop);

    systemIn.provideLines("1", userName, password, "5", discountName, "7", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nPlease input the discount's name:\r\n") +
      String.format("\r\nDiscount already exists\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    String actualOutput = systemOutRule.getLog();
    Assert.assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void executeStaff6() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    discountManager.createDiscount(discountName, shop, value, startDate, day);
    shop.addStaff(userName);
    shop.addDiscount(discountName);
    shopManager.updateShop(shop);

    systemIn.provideLines("1", userName, password, "6", discountName, "7", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nPlease input the discount's name:\r\n") +
      String.format("\r\nDiscount deleted\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    Assert.assertEquals(expectedOutput, systemOutRule.getLog());
  }

  @Test
  public void executeStaff6Fail() {
    accountManager.createPassword(userName, password);
    accountManager.createAccount(userName, "Staff");
    Shop shop = shopManager.createShop(shopName);
    shop.addStaff(userName);
    shopManager.updateShop(shop);

    systemIn.provideLines("1", userName, password, "6", discountName, "7", "3");

    HomePage page = new HomePage();
    page.execute();

    String expectedOutput =
      String.format("Welcome to Coupon Redeem System\r\n") +
      homePageInstruction +
      String.format("\r\nPlease input the user name:\r\n") +
      String.format("\r\nPlease input the password:\r\n") +
      String.format("\r\nSignin successfully\r\n") +
      staffPageInstruction +
      String.format("\r\nPlease input the discount's name:\r\n") +
      String.format("\r\nDiscount not found\r\n") +
      staffPageInstruction +
      String.format("Signout successfully\r\n") +
      homePageInstruction +
      String.format("\r\nThank you for using Coupon Redeem System\r\n") +
      String.format("Goodbye\r\n\r\n");

    String actualOutput = systemOutRule.getLog();
    Assert.assertEquals(expectedOutput, actualOutput);
  }
}
