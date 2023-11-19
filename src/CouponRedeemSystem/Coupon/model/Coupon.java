package CouponRedeemSystem.Coupon.model;

import CouponRedeemSystem.Account.model.Account;
import CouponRedeemSystem.Coupon.CouponManager;
import CouponRedeemSystem.Shop.model.Shop;
import CouponRedeemSystem.System.File.CRSJsonFileManager;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.beanutils.LazyDynaBean;

public abstract class Coupon {

  double intrinsicValue;
  Shop shop;
  Date expirationDate;
  boolean active;
  String couponCode;
  Account owner;
  Double points;

  public Coupon(
    double intrinsicValue,
    Shop shop,
    Date expirationDate,
    String couponCode,
    boolean active,
    double points
  ) {
    this.intrinsicValue = intrinsicValue;
    this.shop = shop;
    this.expirationDate = expirationDate;
    this.couponCode = couponCode;
    this.active = active;
    this.owner = null;
    this.points = points;
  }

  public static void couponToPoints(String couponCode) {
    CouponManager couponManager = CouponManager.getInstance();
    try {
      Coupon coupon = couponManager.getCoupon(couponCode);
      if (coupon == null) {
        System.out.println("No coupon found!");
        return;
      }

      if (!coupon.isActive()) {
        System.out.println("Coupon has been used!");
        return;
      }

      Date currentDate = new Date();
      if (coupon.getExpirationDate().compareTo(currentDate) <= 0) {
        System.out.println("Coupon has expired!");
        return;
      }

      double points = coupon.pointConversion();
      coupon.getOwner().setPoints(coupon.getOwner().getPoints() + points);
      coupon.setActive(false);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public static void pointsToCoupon(Account user, String couponCode)
    throws IOException, ParseException {
    CouponManager couponManager = CouponManager.getInstance();
    CRSJsonFileManager jsonFileManager = CRSJsonFileManager.getInstance();
    Coupon coupon = couponManager.getCoupon(couponCode);
    if (coupon == null) {
      System.out.println("No coupon found!");
      return;
    }

    if (!coupon.isActive()) {
      System.out.println("Coupon is not available!");
      return;
    }

    // May not be necessary
    Date currentDate = new Date();
    if (coupon.getExpirationDate().compareTo(currentDate) <= 0) {
      System.out.println("Coupon has expired!");
      return;
    }

    if (user.getPoints() < coupon.points) {
      System.out.println("Insufficient points!");
      return;
    }

    coupon.setOwner(user);
    user.setPoints(user.getPoints() - coupon.points);
    coupon.setActive(false);

    // Add coupons to user's coupons history
    List<String> coupons = coupon.getOwner().getCouponIDs();
    if (coupons.size() > 10) {
      System.out.println("You have reached the account's purchasing limit!");
      return;
    }
    coupons.add(coupon.getCouponCode());
    coupon.getOwner().setCoupons(coupons);

    // Modify coupon owner
    LazyDynaBean bean = new LazyDynaBean();
    bean.set("owner", coupon.getOwner().getUserName());
    try {
      jsonFileManager.modifyJSON(
        "Coupon/Purchasable",
        coupon.getCouponCode(),
        bean
      );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public double pointConversion() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
    Date currentDate = sdf.parse(new Date().toString());
    Date expire = sdf.parse(expirationDate.toString());

    long daysBeforeExpire = Math.abs(expire.getTime() - currentDate.getTime());
    // (Coupon Value + (Days to Expiration * Weight)) * Conversion Rate
    // Remarks: conversion rate refers to the amount of points rewarded per dollar
    // 1 -> 1 point per dollar
    return (this.getIntrinsicValue() + (daysBeforeExpire * 0.5)) * 1;
  }

  public double getIntrinsicValue() {
    return intrinsicValue;
  }

  public void setIntrinsicValue(double intrinsicValue) {
    this.intrinsicValue = intrinsicValue;
  }

  public Shop getShop() {
    return shop;
  }

  public void setShop(Shop shop) {
    this.shop = shop;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getCouponCode() {
    return couponCode;
  }

  public void setCouponCode(String couponCode) {
    this.couponCode = couponCode;
  }

  public Account getOwner() {
    return owner;
  }

  public void setOwner(Account owner) {
    this.owner = owner;
  }
}
