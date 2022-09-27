package me.illia.windowapi;

import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestWindow {
	@Override
	public String toString() {
		throw new Error("TestWindow cannot be converted to a string", new Throwable("what do you even want it to return?"));
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}
	@Test
	void canWindowSetupProperly() {
		new Window(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, "Test Window").windowBuilder().build();
		Window.addButton(10, 10, 15, -10, "Test Button", Color.MAGENTA, e -> {
			System.out.println(Window.getButtonTitle() + "Has Been Clicked");
		});
		System.out.println("The Test Has Finished))");
	}
	public static void main(String[] args) {
			new Window(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, "Test Window").windowBuilder().build();
			Window.addButton(10, 10, 15, -10, "Test Button", Color.MAGENTA, e -> {
				System.out.println(Window.getButtonTitle() + "Has Been Clicked");
			});
	}
}
