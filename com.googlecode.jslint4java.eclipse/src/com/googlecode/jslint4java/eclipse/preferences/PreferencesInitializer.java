package com.googlecode.jslint4java.eclipse.preferences;

import java.util.EnumSet;
import java.util.Set;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import com.googlecode.jslint4java.Option;
import com.googlecode.jslint4java.eclipse.JSLintPlugin;

/**
 * Set up the default preferences. By default,we enable:
 * <ul>
 * <li> {@link Option#EQEQEQ}
 * <li> {@link Option#SEMIREQ}
 * <li> {@link Option#NEEDCURLY}
 * <li> {@link Option#FORIN}
 * </ul>
 * <p>
 * And assign default values to:
 * <ul>
 * <li> {@link Option#INDENT}
 * <li> {@link Option#MAXERR}
 * </ul>
 */
public class PreferencesInitializer extends AbstractPreferenceInitializer {

    private static final int DEFAULT_INDENT = 4;
    private static final int DEFAULT_MAXERR = 50;

    // Set the initial defaults so the included JSLint matches standard JSLint
    
    private final Set<Option> defaultEnable = EnumSet.of(Option.EQEQEQ, Option.SEMIREQ, 
            Option.NEEDCURLY);

    @Override
    public void initializeDefaultPreferences() {
        IEclipsePreferences node = new DefaultScope().getNode(JSLintPlugin.PLUGIN_ID);
        for (Option o : defaultEnable) {
            node.putBoolean(o.getLowerName(), true);
        }
        // Hand code these.
        node.putInt(Option.INDENT.getLowerName(), DEFAULT_INDENT);
        node.putInt(Option.MAXERR.getLowerName(), DEFAULT_MAXERR);
        
        // All possible excluded directories are initially excluded
        for (String s : Option.getExcludeDirectoryOptions()) {
            node.putBoolean(s, true);
        }
    }
}
