package javaswingdev.system;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

/**
 *
 * @author haris
 */
public class SystemUI {

    public static void installFlatLafUI() {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
//        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.BOLD, 12));
        FlatMacLightLaf.setup();
    }
}
