package CouponRedeemSystem.Account.model;

import CouponRedeemSystem.Coupon.model.Coupon;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

  int userId;
  int age;
  int telNo;
  double points;
  Date dateOfBirth;
  String userName;
  String address;
  List<Coupon> coupons;

  public Account(
    int userId,
    int age,
    int telNo,
    String dateOfBirth,
    String userName,
    String address
  ) throws ParseException {
    this.userId = userId;
    this.age = age;
    this.telNo = telNo;
    this.points = 0;
    this.dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
    this.userName = userName;
    this.address = address;
    coupons = new ArrayList<Coupon>();
  }

  public int getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public int getAge() {
    return age;
  }

  public int getTelNo() {
    return telNo;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public String getAddress() {
    return address;
  }

  public List<Coupon> getCoupons() {
    return coupons;
  }

  public double getPoints() {
    return points;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setTelNo(int telNo) {
    this.telNo = telNo;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setCoupons(List<Coupon> coupons) {
    this.coupons = coupons;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPoints(double score) {
    this.points = score;
  }
}
