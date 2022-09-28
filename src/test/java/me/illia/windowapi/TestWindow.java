package me.illia.windowapi;

import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestWindow {
	@Test
	void canWindowSetupProperly() {
		new Window(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, "Test Window");
		Window.addButton(10, 10, "Test Button",  e -> {
			System.out.println("Test Button Has Been Clicked");
		});
		if (Window.isWindowExists()) {
			System.out.println("The Test Has Finished))");
		} else {
			System.exit(90);
		}
	}
	public static void main(String[] args) {
			new Window(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, "Test Window").windowBuilder().addButton(90, 20, 0, 0, "Test Button", Color.magenta, Color.black, e -> {
				System.out.println("The Test Window Button Has Been Clicked");
			}).centerButton(0);
	}
}
