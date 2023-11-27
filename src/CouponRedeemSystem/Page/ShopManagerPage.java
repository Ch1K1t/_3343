package CouponRedeemSystem.Page;

import CouponRedeemSystem.Account.AccountManager;
import CouponRedeemSystem.Account.model.Account;
import CouponRedeemSystem.Page.model.Page;
import CouponRedeemSystem.Shop.ShopManager;
import CouponRedeemSystem.Shop.model.Shop;

public class ShopManagerPage extends Page {

  public void getInstruction() {
    System.out.println();
    System.out.println("1. Create Shop");
    System.out.println("2. Delete Shop");
    System.out.println("3. Create Staff Account");
    System.out.println("4. Delete Staff Account");
    System.out.println("5. Signout");
    System.out.println("6. Exit");
    System.out.println();
  }

  public void createShop() {
    ShopManager shopManager = ShopManager.getInstance();

    String shopName = strInput("shop name");
    Shop shop = shopManager.createShop(shopName);

    if (shop != null) {
      System.out.println();
      System.out.println("Shop created");
    } else {
      System.out.println();
      System.out.println("Shop creation failed");
    }
  }

  public void deleteShop() {
    ShopManager shopManager = ShopManager.getInstance();

    String shopName = strInput("shop name");
    Shop shop = shopManager.getShop(shopName);
    boolean isDeleted = shopManager.deleteShop(shop);

    if (isDeleted) {
      System.out.println();
      System.out.println("Shop deleted");
    } else {
      System.out.println();
      System.out.println("Shop deletion failed");
    }
  }

  public void deleteAccount() {
    AccountManager accountManager = AccountManager.getInstance();
    ShopManager shopManager = ShopManager.getInstance();

    String username = strInput("staff name");
    Account account = accountManager.getAccount(username);
    if (account == null) {
      System.out.println();
      System.out.println("Account does not exist");
      return;
    }

    if (account.getRole().equals("Staff")) {
      Shop shop = shopManager.getShopByStaff(username);
      shop.removeStaff(username);
      shopManager.updateShop(shop);

      boolean isDeleted = accountManager.deleteAccount(account);
      if (isDeleted) {
        System.out.println();
        System.out.println("Account deleted");
      } else {
        System.out.println();
        System.out.println("Account deletion failed");
      }
    } else {
      System.out.println();
      System.out.println("This account is not a staff account");
    }
  }

  public void execute() {
    String cmd;
    do {
      getInstruction();
      cmd = s.nextLine().toLowerCase();

      switch (cmd) {
        case "1":
          createShop();
          break;
        case "2":
          deleteShop();
          break;
        case "3":
          createAccount("Staff");
          break;
        case "4":
          deleteAccount();
          break;
        case "5":
          System.out.println("Signout successfully");
          break;
        case "6":
          exit();
          break;
        default:
          System.out.println("Unknown command");
          break;
      }
    } while (!cmd.equals("5"));
  }
}