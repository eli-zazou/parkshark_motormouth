package be.motormouth.testConstants;

import be.motormouth.security.users.User;
import be.motormouth.security.users.UserRole;

import static be.motormouth.testConstants.MemberTestConstants.MEMBER_SILVER;

public class UserTestConstants {
    public static final User USER_SILVER = new User(1, "silvermember", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", UserRole.MEMBER, MEMBER_SILVER);
}
