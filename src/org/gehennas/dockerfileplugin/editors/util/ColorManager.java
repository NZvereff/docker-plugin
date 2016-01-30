package org.gehennas.dockerfileplugin.editors.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * The standard color manager that helps to pick Color by RGB.
 * 
 * @author Nikita Zverev
 * @version 1.0.1
 */
public class ColorManager {

	private Map<RGB, Color> fColorTable = new HashMap<RGB, Color>(10);

	/**
	 * Disposes this object
	 */
	public void dispose() {
		Iterator<Color> e = fColorTable.values().iterator();
		while (e.hasNext())
			 (e.next()).dispose();
	}
	
	/**
	 * Returns color by RGB and creates one if it doesn't exist
	 * 
	 * @param rgb - the {@link RGB} representation of color
	 * @return the associated {@link Color} object for the current display
	 */
	public Color getColor(RGB rgb) {
		Color color = (Color) fColorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}
}
