package dmutils.com.dmutils.general;

public class DMFormatter {

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

    public static String getOnlyNumbersByStr(String str){
        return str.replaceAll("\\D+","");
    }


}
