package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Account extends Controller {

  public static void index() {
    Logger.info("Rendering Account");
    Member member = Accounts.getLoggedInMember();
    render("account.html", member);
  }

  public static void changeFirstName(String firstname) {
    Logger.info("Changing First Name");
    Member member = Accounts.getLoggedInMember();
    member.setFirstname(firstname);
    member.save();
    render("account.html", member);
  }

  public static void changeLastName(String lastname) {
    Logger.info("Changing Last Name" + lastname);
    Member member = Accounts.getLoggedInMember();
    member.setLastname(lastname);
    member.save();
    render("account.html", member);
  }

  public static void changePassword(String password) {
    Logger.info("Changing Password");
    Member member = Accounts.getLoggedInMember();
    member.setPassword(password);
    member.save();
    render("account.html", member);
  }
}