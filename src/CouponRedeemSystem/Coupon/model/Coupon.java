package CouponRedeemSystem.Coupon.model;

import CouponRedeemSystem.Account.model.Account;
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
  double point;

  public Coupon(
    double intrinsicValue,
    Shop shop,
    Date expirationDate,
    String couponCode
  ) {
    this.intrinsicValue = intrinsicValue;
    this.shop = shop;
    this.expirationDate = expirationDate;
    this.couponCode = couponCode;
    this.active = true;
    this.owner = null;
    // hardcode
    this.point = 100;
  }

  public static void couponToPoints(Coupon coupon) {
    CRSJsonFileManager jsonFileManager = CRSJsonFileManager.getInstance();

    Date currentDate = new Date();

    if (!coupon.isActive()) return;
    if (coupon.getExpirationDate().compareTo(currentDate) <= 0) return;
    boolean exist =
      jsonFileManager.searchFile(coupon.getCouponCode(), null) == null;
    if (!exist) return;

    double points;
    try {
      points = coupon.pointConversion();
      double newPoints = coupon.getOwner().getPoints() + points;
      coupon.getOwner().setPoints(newPoints);
      coupon.setActive(false);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public static void pointsToCoupon(Account user, Coupon coupon) {
    CRSJsonFileManager jsonFileManager = CRSJsonFileManager.getInstance();
    if (!coupon.isActive()) return;

    // May not be necessary
    Date currentDate = new Date();
    if (coupon.getExpirationDate().compareTo(currentDate) <= 0) return;

    boolean exist =
      jsonFileManager.searchFile(coupon.getCouponCode(), null) == null;
    if (!exist) return;
    if (user.getPoints() < coupon.point) return;
    coupon.setOwner(user);
    user.setPoints(user.getPoints() - coupon.point);
    
    // String newCoupon = coupon.getCouponCode().toString();
    // String[] newCouponList = new String[user.getCoupons().length + 1];
    // System.arraycopy(user.getCoupons(), 0, newCouponList, 0, user.getCoupons().length);
    // newCouponList[newCouponList.length - 1] = newCoupon;
    
    // Add coupons to user's coupons history
    List<Coupon> coupons = coupon.getOwner().getCoupons();
    coupons.add(coupon);
    coupon.getOwner().setCoupons(coupons);
    // Modify coupon owner
    LazyDynaBean bean = new LazyDynaBean();
    bean.set("owner", coupon.getOwner().getUserId());
    try {
        jsonFileManager.modifyJSON("Coupon", coupon.getCouponCode(), bean);
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