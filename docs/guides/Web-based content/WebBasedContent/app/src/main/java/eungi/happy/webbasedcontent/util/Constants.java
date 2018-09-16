package eungi.happy.webbasedcontent.util;

/**
 * Created by Eungi on 2018-08-01.
 */
public class Constants {
    private Constants() {}

    public static final String TAG = "WEB_VIEW_SAMPLE";
    public interface BUILD_MODE {
        String PRODUCT = "PRODUCT";
        String DEV = "DEV";
        String LOCAL = "LOCAL";
    }

    public interface WEB_URL {
        String PRODUCT = "https://admin.livoncare.com";
        String DEV = "https://admin.livoncare.com";
        String LOCAL = "https://admin.livoncare.com";
    }
    public interface WEB_PATH {
        String LOGIN = "/login";
        String LOGIN_ = "/login/loginProc";
        String DASHBOARD = "/test.html";
        String LIVONCARE = "/dashboard/aix";
    }

}
