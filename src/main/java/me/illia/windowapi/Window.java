package me.illia.windowapi;

import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window {
	private static final JPanel jPanel = new JPanel();
	public static final JButton jButton = new JButton();
	private static boolean windowExists;
	public static final int DEFAULT_WIDTH = 900;
	public static final int DEFAULT_HEIGHT = 900;
	public static int WINDOW_WIDTH;
	public static int WINDOW_HEIGHT;
	private static String title;
	public static JWindow jWindow = new JWindow();
	public static WindowHelper windowHelper = new WindowHelper();
	private static final WindowHelper.WindowBuilder WINDOW_BUILDER = new WindowHelper.WindowBuilder();
	public static ArrayList<JButton> jButtons = new ArrayList<>();
	public WindowHelper.WindowBuilder Window() {
		return windowBuilder();
	}

	public Window(int windowWidth, int windowHeight, boolean isResizeable, boolean isVisible, String title) {
		makeWindow(windowWidth, windowHeight, isVisible, isResizeable, title);
	}
	public WindowHelper.WindowBuilder windowBuilder() {
		return WINDOW_BUILDER;
	}
	public static String getTitle() {
		return title;
	}

	public Window(String title) {
		makeWindow(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT, true, true, title);
	}

	private static JWindow makeWindow(int windowWidth, int windowHeight, boolean isVisible, boolean isResizeable, String title) {
		WINDOW_WIDTH = windowWidth;
		WINDOW_HEIGHT = windowHeight;
		Window.title = title;

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
		return jWindow;
	}
	public static JButton addButton(int buttonWidth, int buttonHeight, String title, @Nullable ActionListener actionListener) {
		if (windowExists) {
			jPanel.add(jButton);
			jButton.setSize(buttonWidth, buttonHeight);
			jButton.setText(title);
			if (actionListener != null) jButton.addActionListener(actionListener);
			jButtons.add(jButton);
		}

		return jButton;
	}
	private static class WindowHelper extends JFrame {
		public static class WindowBuilder {
			public WindowBuilder addButton(int buttonWidth, int buttonHeight, String title, ActionListener actionListener) {
				Window.addButton(buttonWidth, buttonHeight, title, actionListener);
				return this;
			}
			public WindowBuilder addButton(int buttonWidth, int buttonHeight, @MagicConstant @Nullable int x, @MagicConstant @Nullable int y, String title, @Nullable Color bgColor, @Nullable Color fgColor, @Nullable ActionListener actionListener) {
				Window.addButton(buttonWidth, buttonHeight, title, actionListener);
				jButton.setOpaque(true);
				jButton.setBounds(x, y, buttonWidth, buttonHeight);
				if (bgColor != null) jButton.setBackground(bgColor);
				if (fgColor != null) jButton.setForeground(fgColor);
				return this;
			}
			public WindowBuilder centerButton(int index) {
				windowHelper.setLayout(new GridBagLayout());
				windowHelper.add(jButtons.get(index), new GridBagConstraints());
				return this;
			}
		}
	}
	private static void update() {
		if (windowExists) {
			jButton.updateUI();
			jPanel.updateUI();
		}
	}
	public static boolean isWindowExists() {
		return windowExists;
	}
}
