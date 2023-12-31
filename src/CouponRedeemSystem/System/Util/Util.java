package CouponRedeemSystem.System.Util;

import CouponRedeemSystem.Account.AccountManager;
import CouponRedeemSystem.Coupon.CouponManager;
import CouponRedeemSystem.Promotion.PromotionManager;
import CouponRedeemSystem.Shop.ShopManager;
import CouponRedeemSystem.Shop.model.Shop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 */
public class Util {

  /** The Constant sdf. */
  public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

  /** The Constant today. */
  public static final Date today = new Date();

  /**
   * Initialize system.
   */
  public static void initializeSystem() {
    clearSystem();

    AccountManager accountManager = AccountManager.getInstance();
    ShopManager shopManager = ShopManager.getInstance();
    CouponManager couponManager = CouponManager.getInstance();
    PromotionManager discountManager = PromotionManager.getInstance();

    accountManager.createPassword("admin", "admin");
    accountManager.createAccount("admin", "Admin");
    accountManager.createPassword("shop", "shop");
    accountManager.createAccount("shop", "Shop Manager");
    accountManager.createPassword("staff1", "staff1");
    accountManager.createAccount("staff1", "Staff");
    accountManager.createPassword("staff2", "staff2");
    accountManager.createAccount("staff2", "Staff");
    accountManager.createPassword("user", "user");
    accountManager.createAccount("user", "User", "01/01/2000", "12345678");

    Shop shop1 = shopManager.createShop("shop1");
    shop1.addStaff("staff1");
    shop1.addCoupon("P1");
    shop1.addPromotion("discount1");
    shopManager.updateShop(shop1);
    Shop shop2 = shopManager.createShop("shop2");
    shop2.addStaff("staff2");
    shop2.addCoupon("P2");
    shop2.addPromotion("discount2");
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

    discountManager.createPromotion(
      "discount1",
      shop1,
      2,
      Util.sdf.format(Util.today),
      7
    );
    discountManager.createPromotion("discount2", shop2, 2, "01/12/2023", 5);
  }

  /**
   * Clear system.
   */
  public static void clearSystem() {
    String[] dirList = new String[] {
      "Account",
      "Coupon/Purchasable",
      "Coupon/Redeemable",
      "Shop",
      "Promotion",
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
}
