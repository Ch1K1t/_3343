package CouponRedeemSystem.Page.model;

import CouponRedeemSystem.Account.AccountManager;
import CouponRedeemSystem.Shop.ShopManager;
import CouponRedeemSystem.Shop.model.Shop;
import CouponRedeemSystem.System.Password.PasswordManager;
import CouponRedeemSystem.System.Util.Util;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Page.
 */
public abstract class Page {

  /** The s. */
  protected static Scanner s;

  /**
   * Instantiates a new page.
   */
  public Page() {
    if (s == null) {
      s = new Scanner(System.in);
    }
  }

  /**
   * Str input.
   *
   * @param fieldName the field name
   * @return the string
   */
  public String strInput(String fieldName) {
    System.out.println();
    System.out.println("Please input the " + fieldName + ":");
    String input;
    do {
      input = s.nextLine();
      if (input.isEmpty()) {
        System.out.println("Please input the " + fieldName + ":");
      }
    } while (input.isEmpty());
    return input;
  }

  /**
   * Int input.
   *
   * @param fieldName the field name
   * @return the int
   */
  public int intInput(String fieldName) {
    System.out.println();
    System.out.println("Please input the " + fieldName + ":");
    String input;
    boolean isInt;
    do {
      input = s.nextLine();
      isInt = input.matches("\\d+");
      if (!isInt) {
        System.out.println("Invalid value, please input again:");
      }
    } while (!isInt);

    return Integer.parseInt(input);
  }

  /**
   * Double input.
   *
   * @param fieldName the field name
   * @return the double
   */
  public double doubleInput(String fieldName) {
    System.out.println();
    System.out.println("Please input the " + fieldName + ":");
    String input;
    boolean isDouble;
    do {
      input = s.nextLine();
      isDouble = input.matches("\\d+(\\.\\d+)?");
      if (!isDouble) {
        System.out.println("Invalid value, please input again:");
      }
    } while (!isDouble);
    return Double.parseDouble(input);
  }

  /**
   * Tel input.
   *
   * @return the string
   */
  public String telInput() {
    System.out.println();
    System.out.println("Please input the telephone number:");
    String telNo;
    boolean isValid;
    do {
      telNo = s.nextLine();
      isValid = telNo.matches("[0-9]{8}");
      if (!isValid) {
        System.out.println("Please input a 8 digit telephone number:");
      }
    } while (!isValid);
    return telNo;
  }

  /**
   * Before date input.
   *
   * @param fieldName the field name
   * @return the string
   */
  public String beforeDateInput(String fieldName) {
    try {
      System.out.println();
      System.out.println("Please input the " + fieldName + " (dd/MM/yyyy):");
      String dateStr;
      boolean isDate = false;
      boolean isBeforeToday = false;
      do {
        dateStr = s.nextLine();
        isDate =
          dateStr.matches(
            "^((0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|2[0-1])[0-9]{2})$"
          );
        if (!isDate) {
          System.out.println("Invalid date format, please input again:");
          continue;
        }
        Date date = Util.sdf.parse(dateStr);
        isBeforeToday = date.compareTo(Util.today) <= 0;
        if (!isBeforeToday) {
          System.out.println("Date must be before today, please input again:");
          continue;
        }
      } while (!isDate || !isBeforeToday);

      return dateStr;
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * After date input.
   *
   * @param fieldName the field name
   * @return the string
   */
  public String afterDateInput(String fieldName) {
    try {
      System.out.println();
      System.out.println("Please input the " + fieldName + " (dd/MM/yyyy):");
      String dateStr;
      boolean isDate = false;
      boolean isAfterToday = false;
      do {
        dateStr = s.nextLine();
        isDate =
          dateStr.matches(
            "^((0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|2[0-1])[0-9]{2})$"
          );
        if (!isDate) {
          System.out.println("Invalid date format, please input again:");
          continue;
        }
        Date date = Util.sdf.parse(dateStr);
        isAfterToday = date.compareTo(Util.today) >= 0;
        if (!isAfterToday) {
          System.out.println("Date must be after today, please input again:");
          continue;
        }
      } while (!isDate || !isAfterToday);

      return dateStr;
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Creates the account.
   *
   * @param role the role
   */
  public void createAccount(String role) {
    PasswordManager passwordManager = PasswordManager.getInstance();
    AccountManager accountManager = AccountManager.getInstance();

    String userName = strInput("user name");
    String password = strInput("password");
    if (
      !passwordManager
        .checkPasswordValid(userName, password)
        .equals("not found")
    ) {
      System.out.println();
      System.out.println("User already exists");
      return;
    }

    accountManager.createPassword(userName, password);

    if (role.equals("Staff")) {
      ShopManager shopManager = ShopManager.getInstance();

      String shopName;
      Shop shop;
      do {
        shopName = strInput("shop name");
        shop = shopManager.getShop(shopName);
        if (shop == null) {
          System.out.println();
          System.out.println("Shop " + shopName + " not found!");
        }
      } while (shop == null);
      shop.addStaff(userName);
      shopManager.updateShop(shop);

      accountManager.createAccount(userName, role);
      System.out.println();
      System.out.println("Account created");

      return;
    } else if (!role.equals("User")) {
      accountManager.createAccount(userName, role);
      System.out.println();
      System.out.println("Account created");

      return;
    }

    String dob = beforeDateInput("date of birth");
    String telNo = telInput();

    accountManager.createAccount(userName, role, dob, telNo);
    System.out.println();
    System.out.println("Account created");
  }

  /**
   * Cmd execute.
   *
   * @param cmdMap the cmd map
   * @param cmd the cmd
   */
  public void cmdExecute(Map<String, Runnable> cmdMap, String cmd) {
    Runnable command = cmdMap.get(cmd);
    if (command != null) {
      command.run();
    } else {
      System.out.println("Unknown command");
    }
  }

  /**
   * Exit.
   */
  public void exit() {
    System.out.println();
    System.out.println("Thank you for using Coupon Redeem System");
    System.out.println("Goodbye");
    System.out.println();
    s.close();
    // System.exit(0);
  }

  /**
   * Sets the s.
   *
   * @param s the new s
   */
  public static void setS(Scanner s) {
    Page.s = s;
  }
}
