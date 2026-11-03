package com.qa.retail.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	//Login Page
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "/retail/index.php?";
	
	//Handling hard-coded waits
	public static final int SHORT_DEFAULT_WAIT=5;
	public static final int MEDIUM_DEFAULT_WAIT=10;
	public static final int LONG_DEFAULT_WAIT=20;
	public static final int POLLING_DEFAULT_WAIT=2;

	
	//AccountPage
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION="/retail/index.php?";
	public static final int ACCOUNT_PAGE_HEADERS_COUNT = 4;
	public static final List<String> ACCOUNT_PAGE_HEADERS_LIST= Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	public static final String USER_REGISTER_SUCCESS_MESSAGE= "Your Account Has Been Created!";
}
