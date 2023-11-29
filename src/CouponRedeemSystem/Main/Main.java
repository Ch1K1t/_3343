package CouponRedeemSystem.Main;

import CouponRedeemSystem.Account.AccountManager;
import CouponRedeemSystem.Coupon.CouponManager;
import CouponRedeemSystem.Discount.DiscountManager;
import CouponRedeemSystem.Page.AdminPage;
import CouponRedeemSystem.Page.HomePage;
import CouponRedeemSystem.Page.ShopManagerPage;
import CouponRedeemSystem.Page.StaffPage;
import CouponRedeemSystem.Page.UserPage;
import CouponRedeemSystem.Page.model.Page;
import CouponRedeemSystem.Shop.ShopManager;
import CouponRedeemSystem.Shop.model.Shop;
import CouponRedeemSystem.System.Util.Util;
import java.io.File;
import java.util.Date;

public class Main {

  public static void initializeSystem() {
    clearSystem();

    AccountManager accountManager = AccountManager.getInstance();
    ShopManager shopManager = ShopManager.getInstance();
    CouponManager couponManager = CouponManager.getInstance();
    DiscountManager discountManager = DiscountManager.getInstance();

    accountManager.createPassword("admin", "admin");
    accountManager.createAccount("admin", "Admin");
    accountManager.createPassword("shop", "shop");
    accountManager.createAccount("shop", "Shop Manager");
    accountManager.createPassword("staff1", "staff1");
    accountManager.createAccount("staff1", "Staff");
    accountManager.createPassword("staff2", "staff2");
    accountManager.createAccount("staff2", "Staff");
    accountManager.createPassword("user", "user");
    accountManager.createAccount("user", "User", "12345678", "01/01/2000");

    Shop shop1 = shopManager.createShop("shop1");
    shop1.addStaff("staff1");
    shop1.addPurchasableCoupon("P1");
    shop1.addDiscount("discount1");
    shopManager.updateShop(shop1);
    Shop shop2 = shopManager.createShop("shop2");
    shop2.addStaff("staff2");
    shop2.addPurchasableCoupon("P2");
    shop2.addDiscount("discount2");
    shopManager.updateShop(shop2);

    couponManager.createCoupon(
      "P1",
      10.0,
      15.0,
      shop1,
      "Purchasable",
      "11/11/2025"
    );
    couponManager.createCoupon(
      "P2",
      10.0,
      15.0,
      shop2,
      "Purchasable",
      "11/11/2025"
    );
    couponManager.createCoupon("R1", 1.0, "Redeemable", "11/11/2025");
    couponManager.createCoupon("R2", 1.0, "Redeemable", "11/11/2025");

    discountManager.createDiscount(
      "discount1",
      shop1,
      2,
      Util.sdf.format(new Date()),
      7
    );
    discountManager.createDiscount("discount2", shop2, 2, "01/12/2023", 5);
  }

  public static void clearSystem() {
    String[] dirList = new String[] {
      "Account",
      "Coupon/Purchasable",
      "Coupon/Redeemable",
      "Shop",
      "Discount",
      "Password",
    };

    for (String dir : dirList) {
      File file = new File("Data/" + dir);
      if (!file.exists()) {
        file.mkdirs();
      }
      File[] fileList = file.listFiles();
      for (File f : fileList) {
        f.delete();
      }
    }
  }

  public static void main(String[] args) {
    // new HomePage().execute();
    // new AdminPage().execute();
    // new ShopManagerPage().execute();
    // new StaffPage("staff1").execute();
    // new UserPage("user").execute();

    // clearSystem();
    // initializeSystem();
  }
}
