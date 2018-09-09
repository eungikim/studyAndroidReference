package eungi.happy.webbasedcontent.util;

/**
 * Created by Eungi on 2018-08-01.
 */
public class Constants {
    private Constants() {}

    public static final String TAG = "LHSMARTHOME";
    public interface BUILD_MODE {
        String PRODUCT = "PRODUCT";
        String DEV = "DEV";
        String LOCAL = "LOCAL";
    }

    public interface WEB_URL {
        String PRODUCT = "http://13.125.90.107:8083";
        String DEV = "http://13.125.90.107:8083";
        String LOCAL = "http://192.168.1.55:8080";
    }
    public interface WEB_PATH {
        String LOGIN = "/login";
        String LOGIN_ = "/login/loginProc";
        String DASHBOARD = "/dashboard/dashboard";
        String LIVONCARE = "/dashboard/aix";
    }


    public interface PREF {
        String NAME = "kr.hidea.lhsmarthome.sharedpreferences";
        String KEY_TOPIC = "kr.hidea.lhsmarthome.sharedpreferences.topic";
    }
}
