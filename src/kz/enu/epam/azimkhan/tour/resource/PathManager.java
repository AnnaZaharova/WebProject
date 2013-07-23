package kz.enu.epam.azimkhan.tour.resource;

import java.util.ResourceBundle;

/**
 * Url manager
 */
public enum PathManager {
    INSTANCE;

    private static final String BUNDLE_NAME = "path";
    private final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Get path
     * @param key
     * @return
     */
    public synchronized String getString(String key) {
        return bundle.getString(key);
    }
}