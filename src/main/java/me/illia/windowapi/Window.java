package me.illia.windowapi;

import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Window {
	private static final JPanel jPanel = new JPanel();
	public static final JButton jButton = new JButton();
	public static String buttonTitle;
	private static boolean windowExists;
	public static final int DEFAULT_WIDTH = 900;
	public static final int DEFAULT_HEIGHT = 900;
	public Window(int windowWidth, int windowHeight, boolean isResizeable, boolean isVisible, String title) {
		makeWindow(windowWidth, windowHeight, isVisible, isResizeable, title);
	}

	public Window(String title) {
		makeWindow(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, title);
	}
	private static void makeWindow(int windowWidth, int windowHeight, boolean isVisible, boolean isResizeable, String title) {
		JWindow jWindow = new JWindow();
		WindowHelper windowHelper = new WindowHelper();
		if (isVisible) {
			windowHelper.setSize(windowWidth, windowHeight);
			windowHelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			windowHelper.add(jPanel);
			windowHelper.setVisible(true);
			windowHelper.setTitle(title);
			update();
		} else {
			jWindow.setVisible(true);
		}
		for (int i = 0; i == i + 1; i++) {
			while (windowExists) {
				update();
			}
		}
		if (jWindow.isVisible()) windowExists = true;
		if (jPanel.isVisible()) windowExists = true;
	}
	public static void addButton(int buttonWidth, int buttonHeight, String title, @Nullable ActionListener actionListener) {
		if (windowExists) {
			jPanel.add(jButton);
			jButton.setSize(buttonWidth, buttonHeight);
			jButton.setText(title);
			if (actionListener != null) jButton.addActionListener(actionListener);
			buttonTitle = title;
		}
	}
	private static class WindowHelper extends JFrame {
		//TODO -- Make A Builder Pattern For addButton(); etc.
	}
	private static void update() {
		if (windowExists) {
			jButton.updateUI();
			jPanel.updateUI();
		}
	}
	public static void addButton(int buttonWidth, int buttonHeight, String title, Color backgroundColor, @Nullable ActionListener actionListener) {
			jPanel.add(jButton);
			jButton.setSize(buttonWidth, buttonHeight);
			jButton.setText(title);
			jButton.setBackground(backgroundColor);
			if (actionListener != null) jButton.addActionListener(actionListener);
			buttonTitle = title;
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
