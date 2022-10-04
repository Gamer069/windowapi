package me.illia.windowapi;

import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window {
	private static final JButton jButton = new JButton();
	private static final JPanel jPanel = new JPanel();
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
	public static ArrayList<JTextArea> jTextFields = new ArrayList<>();
	public static ArrayList<JPopupMenu> jPopups = new ArrayList<>();
	public static ArrayList<JPasswordField> jPasswordFields = new ArrayList<>();
	public WindowHelper.WindowBuilder WindowBuilder() {
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
		windowHelper.setVisible(isVisible);
		if (isVisible) {
			windowHelper.setSize(windowWidth, windowHeight);
			windowHelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			windowHelper.add(jPanel);
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
	public static JButton addButton(int buttonWidth, int buttonHeight, int x, int y, String title, @Nullable Color bgColor, @Nullable Color fgColor, @Nullable ActionListener actionListener) {
		addButton(buttonWidth, buttonHeight, title, actionListener);
		jButton.setOpaque(true);
		jButton.setBounds(x, y, buttonWidth, buttonHeight);
		if (bgColor != null) jButton.setBackground(bgColor);
		if (fgColor != null) jButton.setForeground(fgColor);
		return jButton;
	}
	public static JTextArea addTextArea(String defaultText, int width, int height, int x, int y) {
		JTextArea jTextArea = new JTextArea(defaultText);
		jTextArea.setBounds(x, y, width, height);
		jTextArea.setVisible(true);
		jTextArea.setText(defaultText);
		jTextArea.setSize(width, height);
		windowHelper.add(jTextArea);
		jPanel.add(jTextArea);
		jTextFields.add(jTextArea);
		return jTextArea;
	}
	public static JPopupMenu addPopupMenu(String label, boolean isVisible, int width, int height, int x, int y) {
		JPopupMenu jPopup = new JPopupMenu(label);
		jPopup.setToolTipText(label);
		jPopup.setVisible(isVisible);
		jPopup.setPopupSize(width, height);
		jPopup.setBounds(x, y, width, height);
		jPanel.add(jPopup);
		jPopups.add(jPopup);
		return jPopup;
	}
	public static JPasswordField addPasswordField(String text, int width, int height, int x, int y) {
		JPasswordField jPasswordField = new JPasswordField(text);
		jPasswordFields.add(jPasswordField);
		jPasswordField.setFont(new Font(text, Font.BOLD, 12));
		jPasswordField.setBounds(x, y, width, height);
		return jPasswordField;
	}
	public static JPopupMenu addPopupMenu() {
		JPopupMenu jPopup = new JPopupMenu();
		jPopup.setVisible(true);
		jPanel.add(jPopup);
		jPopups.add(jPopup);
		return jPopup;
	}
	private static class WindowHelper extends JFrame {
		public static class WindowBuilder {
			public WindowBuilder addButton(int buttonWidth, int buttonHeight, String title, ActionListener actionListener) {
				Window.addButton(buttonWidth, buttonHeight, title, actionListener);
				return this;
			}
			public WindowBuilder addButton(int buttonWidth, int buttonHeight, @MagicConstant int x, @MagicConstant int y, String title, @Nullable Color bgColor, @Nullable Color fgColor, @Nullable ActionListener actionListener) {
				Window.addButton(buttonWidth, buttonHeight, x, y, title, bgColor, fgColor, actionListener);
				return this;
			}


			public WindowBuilder addTextArea(String defaultText, int width, int height, int x, int y) {
				Window.addTextArea(defaultText, width, height, x, y);
				return this;
			}
			public WindowBuilder addPopup(String title, boolean isVisible, int width, int height, int x, int y) {
				Window.addPopupMenu(title, isVisible, width, height, x, y);
				return this;
			}
			public WindowBuilder addPopup() {
				Window.addPopupMenu();
				return this;
			}
			public WindowBuilder addPasswordField(String text, int width, int height, int x, int y) {
				Window.addPasswordField(text, width, height, x, y);
				return this;
			}
			public WindowBuilder centerPasswordField(int index) {
				windowHelper.setLayout(new GridBagLayout());
				windowHelper.add(jPasswordFields.get(index), new GridBagConstraints());
				return this;
			}
			public WindowBuilder centerPopup(int index) {
				windowHelper.setLayout(new GridBagLayout());
				windowHelper.add(jPopups.get(index), new GridBagConstraints());
				return this;
			}
			public WindowBuilder centerTextArea(int index) {
				windowHelper.setLayout(new GridBagLayout());
				windowHelper.add(jTextFields.get(index), new GridBagConstraints());
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
