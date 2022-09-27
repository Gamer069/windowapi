package me.illia.windowapi;

import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

public class Window {
	private static final JPanel jPanel = new JPanel();
	public static final JButton jButton = new JButton();
	public static String buttonTitle;
	private static boolean windowExists;
	public static final int DEFAULT_WIDTH = 900;
	public static final int DEFAULT_HEIGHT = 900;
	public static int WINDOW_WIDTH;
	public static int WINDOW_HEIGHT;
	private static String title;
	public static Stream.Builder<Window> WINDOW_BUILDER = new Stream.Builder<Window>() {
		@Override
		public void accept(Window window) {
			window.windowBuilder();
		}

		@Override
		public Stream.Builder<Window> add(Window window) {
			return Stream.Builder.super.add(window);
		}

		@Override
		public Stream<Window> build() {
			makeWindow(WINDOW_WIDTH, WINDOW_HEIGHT, true, true, getTitle());
			return null;
		}
	};
	public Window(int windowWidth, int windowHeight, boolean isResizeable, boolean isVisible, String title) {
		makeWindow(windowWidth, windowHeight, isVisible, isResizeable, title);
	}
	public Stream.Builder<Window> windowBuilder() {
		return WINDOW_BUILDER;
	}
	public static String getTitle() {
		return title;
	}

	public Window(String title) {
		makeWindow(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, title);
	}

	private static void makeWindow(int windowWidth, int windowHeight, boolean isVisible, boolean isResizeable, String title) {
		WINDOW_WIDTH = windowWidth;
		WINDOW_HEIGHT = windowHeight;
		Window.title = title;
		JWindow jWindow = new JWindow();
		WindowHelper windowHelper = new WindowHelper();
		if (isVisible) {
			windowHelper.setSize(windowWidth, windowHeight);
			windowHelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			windowHelper.add(jPanel);
			windowHelper.setVisible(isVisible);
			windowHelper.setTitle(title);
			update();
		}
		while (windowExists) {
				update();
		}
		if (jWindow.isVisible() || jPanel.isVisible()) windowExists = true;
	}
	public static JButton addButton(int buttonWidth, int buttonHeight, String title, @Nullable ActionListener actionListener) {
		if (windowExists) {
			jPanel.add(jButton);
			jButton.setSize(buttonWidth, buttonHeight);
			jButton.setText(title);
			if (actionListener != null) jButton.addActionListener(actionListener);
			buttonTitle = title;
		}

		return jButton;
	}
	private static class WindowHelper extends JFrame {
		//TODO -- Make A Builder Pattern For addButton(); etc.

		public WindowHelper() {

		}
	}
	private static void update() {
		if (windowExists) {
			jButton.updateUI();
			jPanel.updateUI();
		}
	}
	public static JButton addButton(int buttonWidth, int buttonHeight, String title, Color backgroundColor, @Nullable ActionListener actionListener) {
			jPanel.add(jButton);
			jButton.setSize(buttonWidth, buttonHeight);
			jButton.setText(title);
			jButton.setBackground(backgroundColor);
			if (actionListener != null) jButton.addActionListener(actionListener);
			buttonTitle = title;
			return jButton;
	}
	public static String getButtonTitle() {
		return buttonTitle + " ";
	}
	public static boolean doesWindowExist() {
		return windowExists;
	}

	public static JButton addButton(int buttonWidth, int buttonHeight, @MagicConstant int x, @MagicConstant int y, String title, Color backgroundColor, @Nullable ActionListener actionListener) {
		addButton(buttonWidth, buttonHeight, title, actionListener);
		jButton.setAlignmentX(x);
		jButton.setAlignmentY(y);
		return jButton;
	}
}
