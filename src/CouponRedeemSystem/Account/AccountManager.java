package CouponRedeemSystem.Account;

import CouponRedeemSystem.Account.model.Account;
import CouponRedeemSystem.System.File.CRSJsonFileManager;
import CouponRedeemSystem.System.Password.PasswordManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.LazyDynaBean;

public class AccountManager {

  private static AccountManager instance;
  private CRSJsonFileManager jsonFileManager = CRSJsonFileManager.getInstance();
  private PasswordManager passwordManager = PasswordManager.getInstance();

  private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

  private AccountManager() {}

  public static AccountManager getInstance() {
    if (instance == null) {
      instance = new AccountManager();
    }
    return instance;
  }

  public boolean createPassword(String userName, String Password) {
    JSONObject jsonObject = jsonFileManager.searchJSON(userName);
    if (jsonObject != null) {
      System.out.println("User " + userName + " already exists");
      return false;
    }

    passwordManager.createNewPassword(userName, Password);
    return true;
  }

  // Create new user account
  public void createAccount(
    String userName,
    String role,
    int age,
    String telNo,
    String dob
  ) {
    Account account = new Account(userName, role, age, telNo, dob);

    LazyDynaBean bean = new LazyDynaBean();
    bean.set("userName", account.getUserName());
    bean.set("role", account.getRole());
    bean.set("age", account.getAge());
    bean.set("telNo", account.getTelNo());
    bean.set("points", account.getPoints());
    bean.set("dateOfBirth", sdf.format(account.getDateOfBirth()));
    bean.set("couponIDs", account.getCouponIDs());

    jsonFileManager.modifyJSON("Account", userName, bean);

    System.out.println();
    System.out.println("Account created");
  }

  // Create new non-user account
  public void createAccount(String userName, String role) {
    Account account = new Account(userName, role);

    LazyDynaBean bean = new LazyDynaBean();
    bean.set("userName", account.getUserName());
    bean.set("role", account.getRole());

    jsonFileManager.modifyJSON("Account", userName, bean);

    System.out.println();
    System.out.println("Account created");
  }

  public void deleteAccount(String userName) {
    Account account = getAccount(userName);
    if (account == null) {
      System.out.println("Account " + userName + " does not exist");
      return;
    }

    jsonFileManager.deleteJSON("Account", account.getUserName());

    System.out.println();
    System.out.println("Account deleted");
  }

  public void updateAccount(Account account) {
    String role = account.getRole();

    LazyDynaBean bean = new LazyDynaBean();
    bean.set("userName", account.getUserName());
    bean.set("role", role);
    if (role.equals("User")) {
      bean.set("age", account.getAge());
      bean.set("telNo", account.getTelNo());
      bean.set("points", account.getPoints());
      bean.set("dateOfBirth", sdf.format(account.getDateOfBirth()));
      bean.set("couponIDs", account.getCouponIDs());
    }

    jsonFileManager.modifyJSON("Account", account.getUserName(), bean);

    System.out.println();
    System.out.println("Account updated");
  }

  public Account getAccount(String userName) {
    JSONObject accountJson = jsonFileManager.searchJSON(userName);

    if (accountJson == null) {
      return null;
    } else {
      return extractAccountFromJson(accountJson);
    }
  }

  private Account extractAccountFromJson(JSONObject accountJson) {
    try {
      String userName = accountJson.getString("userName");
      String role = accountJson.getString("role");
      if (!role.equals("User")) {
        return new Account(userName, role);
      }

      int age = accountJson.getInt("age");
      String telNo = accountJson.getString("telNo");
      double points = accountJson.getDouble("points");
      Date dateOfBirth = sdf.parse(accountJson.getString("dateOfBirth"));
      JSONArray couponIDsArray = accountJson.getJSONArray("couponIDs");
      List<String> couponIDs = new ArrayList<>();
      for (int i = 0; i < couponIDsArray.size(); i++) {
        couponIDs.add(couponIDsArray.getString(i));
      }

      return new Account(
        userName,
        role,
        age,
        telNo,
        dateOfBirth,
        points,
        couponIDs
      );
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void generateDemoAccount() {
    boolean notExist;
    notExist = createPassword("admin", "admin");
    if (notExist) {
      createAccount("admin", "Admin");
    }
    notExist = createPassword("shop", "shop");
    if (notExist) {
      createAccount("shop", "Shop Manager");
    }
    notExist = createPassword("staff", "staff");
    if (notExist) {
      createAccount("staff", "Staff");
    }
    notExist = createPassword("user", "user");
    if (notExist) {
      createAccount("user", "User", 20, "12345678", "01/01/2000");
    }
  }
}
