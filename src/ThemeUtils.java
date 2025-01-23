// Utility class for UI theming

import java.awt.*;

public class ThemeUtils {

    public static void applyDarkMode(Component component, boolean darkMode) {
        if (darkMode) {
            component.setBackground(Color.DARK_GRAY);
            component.setForeground(Color.WHITE);
        } else {
            component.setBackground(Color.LIGHT_GRAY);
            component.setForeground(Color.BLACK);
        }
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                applyDarkMode(child, darkMode);
            }
        }
    }
}
