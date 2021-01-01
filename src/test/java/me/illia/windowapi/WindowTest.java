package me.illia.windowapi;

import me.illia.windowapi.api.Window;
import me.illia.windowapi.config.ButtonConfig;
import me.illia.windowapi.config.TextConfig;
import org.junit.jupiter.api.Test;

import static me.illia.windowapi.other.ElementTypes.NORMAL_BTN;

public class WindowTest {
	public static final ButtonConfig btnConfig = ButtonConfig.init(20, 20, 100, 20);
	public static final TextConfig textConfig = TextConfig.init(20, 20, false);
	@Test
	public static void canWindowSetupProperly() {
		new Window("Test Window")
				.windowBuilder()
				.addButton(
						NORMAL_BTN,
						btnConfig,
						"Test Button",
						null,
						null,
						e -> System.out.println("The Test Button Has Been Clicked"))
				.addTextArea(
						"Test Text Area",
						textConfig,
						10,
						0
				)
				.centerButton(0)
				.centerTextArea(0);
		if (Window.isWindowExists() && !System.err.checkError()) {
			System.out.println("The test has finished))");
		} else {
			System.err.println("The test has failed((");
			System.exit(90);
		}
	}
	public static void main(String[] args) {
		new Window("Window test");
	}
}
