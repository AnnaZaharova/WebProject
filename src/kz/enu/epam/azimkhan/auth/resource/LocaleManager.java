package kz.enu.epam.azimkhan.auth.resource;

import com.sun.script.util.BindingsEntrySet;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Manages locales
 */
public enum LocaleManager {
    INSTANCE;

    public static final String REQUEST_PARAMETER = "lang";

    private Locale defaultLocale = Locale.forLanguageTag("ru");

    private HashMap<String, Locale> locales = new HashMap<String, Locale>();
    {
        locales.put("English", Locale.forLanguageTag("en"));
        locales.put("Русский", Locale.forLanguageTag("ru"));
    }

    public Locale getDefaultLocale(){
        return defaultLocale;
    }

    public HashMap<String, Locale> getLocales(){
        return locales;
    }

    /**
     * Get locale from code
     * @return
     */
    public Locale resolveLocale(ServletRequest request){
        String code = request.getParameter(REQUEST_PARAMETER);

        if (code != null){
            Iterator<Map.Entry<String, Locale>> iterator = locales.entrySet().iterator();

            while(iterator.hasNext()){
                Locale locale = iterator.next().getValue();
                Logger.getRootLogger().debug(locale.toLanguageTag() + " " + code);
                if (locale.toLanguageTag().equalsIgnoreCase(code)){
                    return locale;
                }
            }
        }

        return defaultLocale;
    }

}
