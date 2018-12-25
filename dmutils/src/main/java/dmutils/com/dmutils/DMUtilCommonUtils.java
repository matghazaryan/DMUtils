package dmutils.com.dmutils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public final class DMUtilCommonUtils {

    public static boolean checkFileExtension(final String filePath, final String[] extensions) {

        if (filePath != null) {
            final String ext = filePath.substring(filePath.lastIndexOf("."));

            for (int i = 0; i < extensions.length; i++) {
                if (ext.equalsIgnoreCase(extensions[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void openWebsite(final Activity pActivity, final String pWebUrl) {
        final Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pWebUrl));
        pActivity.startActivity(webIntent);
    }

    public static void openGoogleMap(final Activity pActivity, final double pLat, final double pLng) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + pLat + "&daddr=" + pLng));
        pActivity.startActivity(intent);
    }

    public static void openGoogleMapGeo(final Activity pActivity, final double pLat, final double pLng) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String data = String.format("geo:%s,%s", pLat, pLng);

        intent.setData(Uri.parse(data));
        pActivity.startActivity(intent);
    }

    public static void openDialerIntent(final Activity pActivity, String pPhone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + pPhone));
        pActivity.startActivity(intent);
    }

    public static void openEmailIntent(final Activity pActivity, final String pEmail) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{pEmail});
//        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
//        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            pActivity.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

    public static void openPlayStore(final Activity pActivity) {
        final String appPackageName = pActivity.getPackageName();
        try {
            pActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            pActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static void openShareIntent(final Activity pActivity, final String pShareUrl) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, pShareUrl);

        pActivity.startActivity(Intent.createChooser(share, ""));
    }
}
