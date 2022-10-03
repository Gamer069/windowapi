package me.illia.windowapi;

import org.junit.jupiter.api.Test;

public class TestWindow {
	@Test
	void canWindowSetupProperly() {
		new Window(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, "Test Window").windowBuilder().addButton(90, 20, 0, 0, "Test Button", null, null, e -> {
			System.out.println("The Test Button Has Been Clicked");
		}).centerButton(0);
		if (Window.isWindowExists() && !System.err.checkError()) {
			System.out.println("The Test Has Finished))");
		} else {
			System.exit(90);
		}
	}
	public static void main(String[] args) {
		new Window(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, "Test Window").windowBuilder().addTextArea("Test Text Field", 10, 10, 10, 2).addButton(90, 20, 10, 10, "Test Button", null, null, e -> {
			System.out.println("The Test Button Has Been Clicked");
		}).centerTextArea(0);
	}
}
