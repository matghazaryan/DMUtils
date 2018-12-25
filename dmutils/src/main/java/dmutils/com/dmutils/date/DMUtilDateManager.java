package dmutils.com.dmutils.date;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DMUtilDateManager {

    public static String convertDaterFormatTo(final String pDate, final String pFrom, final String pTo, Locale pLocale) {
        String sDate = "";
        try {
            final Date date = new SimpleDateFormat(pFrom, pLocale).parse(pDate);
            sDate = new SimpleDateFormat(pTo, pLocale).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sDate;
    }

    public static String convertDaterFormatTo(final String pDate, final String pFrom, final String pTo) {
        String sDate = "";
        try {
            final Date date = new SimpleDateFormat(pFrom).parse(pDate);
            sDate = new SimpleDateFormat(pTo).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sDate;
    }

    /**
     * @param date new Date()
     * @param gmt  like +0400
     * @return like /Date(1419364800000+0400)/
     */
    public static String convertDateToMicrosoftFormat(final Date date, final String gmt) {
        return "/Date(" + date.getTime() + gmt + ")/";
    }

    public static String convertDateToMicrosoftFormat(final String stringDate, final String gmt, final String format, final Locale locale) {
        final Date date = stringToDate(stringDate, format, locale);
        if (date != null) {
            return "/Date(" + date.getTime() + gmt + ")/";
        }
        return null;
    }

    /**
     * @param microsoftDate like /Date(1419364800000+0400)/
     * @return new Date()
     */
    public static Date convertMicrosoftDateToNormal(String microsoftDate) {
        Date date = null;
        if (microsoftDate != null) {

            int sign = 1;
            if (microsoftDate.contains("-")) {
                sign = -1;
                microsoftDate = microsoftDate.replace("-", "");
            }

            final Pattern datePatt = Pattern.compile("^/Date\\((\\d+)([+-]\\d+)?\\)/$");
            final Matcher m = datePatt.matcher(microsoftDate);
            if (m.matches()) {
                final Long l = Long.parseLong(m.group(1));
                date = new Date(l);
                // Time zone is not needed to calculate date
            } else {
                throw new IllegalArgumentException("Wrong date format");
            }
        }

        return date;
    }

    public static String convertMicrosoftDateToNormal(String microsoftDate, final String format, final Locale locale) {
        if (microsoftDate != null) {

            int sign = 1;
            if (microsoftDate.contains("-")) {
                sign = -1;
                microsoftDate = microsoftDate.replace("-", "");
            }

            final Pattern datePatt = Pattern.compile("^/Date\\((\\d+)([+-]\\d+)?\\)/$");
            final Matcher m = datePatt.matcher(microsoftDate);
            if (m.matches()) {
                final Long l = Long.parseLong(m.group(1)) * sign;
                final Date date = new Date(l);

                return dateToString(date, format, locale);
                // Time zone is not needed to calculate date
            } else {
                Log.wtf("MGDateManager", "Wrong date format");
            }
        }

        return null;
    }

    @SuppressLint("SimpleDateFormat")
    public static String dateToString(final Date date, final String format, final Locale locale) {
        if (date == null) return "";
        return new SimpleDateFormat(format, locale).format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(final String stringDate, final String format, final Locale locale) {
        if (stringDate == null || stringDate.length() == 0) {
            return null;
        }
        try {
            return new SimpleDateFormat(format, locale).parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * HOUR, MINUTE and SECOND to 00:00:00
     *
     * @param date
     */
    public static void zeroTime(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
