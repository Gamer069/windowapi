package me.illia.windowapi;

import org.junit.jupiter.api.Test;

public class TestWindow {
	@Test
	public void canWindowSetupProperly() {
		new Window(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, "Test Window").windowBuilder().addButton(90, 20, 0, 0, "Test Button", null, null, e -> System.out.println("The Test Button Has Been Clicked")).addTextArea("Test Text Area", 10, 10, 0, 0).centerButton(0).centerTextArea(0);
		if (Window.isWindowExists() && !System.err.checkError()) {
			System.out.println("The Test Has Finished))");
		} else {
			System.exit(90);
		}
	}
	public static void main(String[] args) {
		new Window(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, "Test Window").windowBuilder().addPasswordField("Your Password Here", 10, 10, 0, 0).centerPasswordField(0);
	}
	// LOL
}
