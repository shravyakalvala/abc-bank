package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static DateProvider dateProviderInstance = null;

    //changed get instance to get provider instance
    public static DateProvider getDateProviderInstance() {
        if (dateProviderInstance == null)
        	dateProviderInstance = new DateProvider();
        return dateProviderInstance;
    }

    public Date getTimeStamp() {
        return Calendar.getInstance().getTime();
    }
}
