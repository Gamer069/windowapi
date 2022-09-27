package me.illia.windowapi;

import org.intellij.lang.annotations.MagicConstant;

import javax.swing.border.TitledBorder;
import javax.swing.text.Position;
import java.awt.*;

public class TestWindow {
	public static void main(String[] args) {
		new Window(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, "Test Window");
		Window.addButton(10, 10, 15, -10, "Test Window Button", Color.MAGENTA, e -> {
			System.out.println(Window.getButtonTitle() + "Has Been Clicked");
		});
	}
}
