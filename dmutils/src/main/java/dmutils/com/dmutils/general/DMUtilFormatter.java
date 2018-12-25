package dmutils.com.dmutils.general;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class DMUtilFormatter {

    public static boolean isNumeric(final String s) {
        boolean isNum = true;
        try {
            Double.parseDouble(s.replaceAll(",", ""));
        } catch (NumberFormatException e) {
            isNum = false;
        }

        return isNum;
    }

    public static String deFormatNumber(final String number) {
        return number.replaceAll("[^0-9-.]", "");
    }

    public static String getOnlyNumbersByStr(String str) {
        return str.replaceAll("\\D+", "");
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
