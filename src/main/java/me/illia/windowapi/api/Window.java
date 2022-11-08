package me.illia.windowapi.api;

import me.illia.windowapi.config.ButtonConfig;
import me.illia.windowapi.config.TextConfig;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static me.illia.windowapi.other.ElementTypes.*;

public class Window {
	private static final JPanel J_PANEL = new JPanel();
	private static boolean WINDOW_EXISTS;
	public static WindowHelper WINDOW_HELPER = new WindowHelper();
	private static final WindowHelper.WindowBuilder WINDOW_BUILDER = new WindowHelper.WindowBuilder();
	public static ArrayList<JButton> jButtons = new ArrayList<>();
	public static ArrayList<JTextArea> jTextFields = new ArrayList<>();
	public static ArrayList<JPopupMenu> jPopups = new ArrayList<>();
	public static ArrayList<JPasswordField> jPasswordFields = new ArrayList<>();
	public static ArrayList<JMenuBar> jMenuBars = new ArrayList<>();
	public static ArrayList<JToggleButton> jToggleButtons = new ArrayList<>();
	public static ArrayList<JMenu> jMenus = new ArrayList<>();

	public Window(String title) {
		createWindow(title);
		PropertiesManagement.readConfigProperties();
	}
	public WindowHelper.WindowBuilder windowBuilder() {
		return WINDOW_BUILDER;
	}

	protected void createWindow(String title) {
		WINDOW_HELPER.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WINDOW_HELPER.add(J_PANEL);
		WINDOW_HELPER.setTitle(title);
		update();
		if (J_PANEL.isVisible()) WINDOW_EXISTS = true;
	}
	static void addButton(int buttonType, ButtonConfig config, String title, @Nullable ActionListener actionListener) {
		if (!WINDOW_EXISTS) {
			throw new Error("No FLOATING BUTTONS, ok?");
		}
		if (buttonType == NORMAL_BTN) {
			JButton jButton = new JButton(title);
			J_PANEL.add(jButton);
			jButton.setBounds(new Rectangle(config.getButtonWidth(), config.getButtonHeight()));
			jButton.setSize(new Dimension(config.getButtonWidth(), config.getButtonHeight()));
			jButton.setText(title);
			jButton.addActionListener(actionListener);
			jButtons.add(jButton);
			return;
		} else if (buttonType == TOGGLE_BTN) {
			JToggleButton jToggleButton = new JToggleButton(title);
			jToggleButton.setSize(new Dimension(config.getButtonWidth(), config.getButtonHeight()));
			jToggleButton.addActionListener(actionListener);
			J_PANEL.add(jToggleButton);
			jToggleButtons.add(jToggleButton);
			WINDOW_HELPER.add(jToggleButton, "Center");
			return;
		}
		throw new IllegalArgumentException("Sorry, but " + buttonType + " isn't a BUTTON type");
	}
	static void addButton(int buttonType, ButtonConfig config, String title, @Nullable Color bgColor, @Nullable Color fgColor, @Nullable ActionListener actionListener) {
		addButton(buttonType, config, title, actionListener);
		JButton jButton = new JButton(title);
		jButton.setOpaque(true);
		jButton.addActionListener(actionListener);
		jButton.setBounds(config.getXPosition(), config.getYPosition(), config.getButtonWidth(), config.getButtonHeight());
		if (bgColor != null) jButton.setBackground(bgColor);
		if (fgColor != null) jButton.setForeground(fgColor);
	}
	public static void addTextArea(String defaultText, TextConfig config, int x, int y) {
		JTextArea jTextArea = new JTextArea(defaultText);
		jTextArea.setBounds(x, y, config.getWidth(), config.getHeight());
		jTextArea.setVisible(true);
		jTextArea.setText(defaultText);
		jTextArea.setSize(config.getWidth(), config.getHeight());
		WINDOW_HELPER.add(jTextArea);
		J_PANEL.add(jTextArea);
		jTextFields.add(jTextArea);
	}
	static void addPopupMenu(String label, boolean isVisible, int width, int height, int x, int y) {
		JPopupMenu jPopup = new JPopupMenu(label);
		jPopup.setToolTipText(label);
		jPopup.setVisible(isVisible);
		jPopup.setPopupSize(width, height);
		jPopup.setBounds(x, y, width, height);
		J_PANEL.add(jPopup);
		jPopups.add(jPopup);
	}
	static void addPasswordField(String text, TextConfig config) {
		JPasswordField jPasswordField = new JPasswordField(text);
		jPasswordFields.add(jPasswordField);
		jPasswordField.setFont(new Font(text, Font.BOLD, 12));
	}
	static void addPopupMenu() {
		JPopupMenu jPopup = new JPopupMenu();
		jPopup.setVisible(true);
		J_PANEL.add(jPopup);
		jPopups.add(jPopup);
	}
	static void addMenu(String name) {
		JMenuBar jMenuBar = new JMenuBar();
		JMenu jMenu = new JMenu(name);
		jMenuBar.add(jMenu);
		jMenuBars.add(jMenuBar);
		jMenus.add(jMenu);
		WINDOW_HELPER.setJMenuBar(jMenuBar);
	}
	static void addMenuItem(int itemType, String name, int menuIndex) {
		if (!WINDOW_EXISTS) throw new Error("-1");
		JMenu jMenu = jMenus.get(menuIndex);
		if (itemType == CHECKBOX_MENU_ITEM) {
			JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(name);
			jMenu.add(jCheckBoxMenuItem);
		}
	}
	public static class WindowHelper extends JFrame {
		protected static final WindowHelper INSTANCE = new WindowHelper();
		private boolean useJavaFX;

		public void useJavaFX(boolean useJavaFX) {
            INSTANCE.useJavaFX = useJavaFX;
        }

        public boolean isJavaFX() {
            return INSTANCE.useJavaFX;
        }

		@Override
		public void setTitle(String title) {
			super.setTitle(title);
		}

		public static final class WindowBuilder {
			@MagicConstant
			public WindowBuilder addButton(int buttonType, ButtonConfig config, String title, ActionListener actionListener) {
				Window.addButton(buttonType, config, title, actionListener);
				return this;
			}
			public WindowBuilder addButton(int buttonType, ButtonConfig config, String title, @Nullable Color bgColor, @Nullable Color fgColor, @Nullable ActionListener actionListener) {
				Window.addButton(buttonType, config, title, bgColor, fgColor, actionListener);
				return this;
			}

			public WindowBuilder addMenu(String name) {
				Window.addMenu(name);
				return this;
			}
			public WindowBuilder addMenuItem(int itemType, String name, int menuIndex) {
				Window.addMenuItem(itemType, name, menuIndex);
				return this;
			}
			public WindowBuilder addTextArea(String defaultText, TextConfig config, int x, int y) {
				Window.addTextArea(defaultText, config, x, y);
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
			public WindowBuilder addPasswordField(String text, TextConfig config) {
				Window.addPasswordField(text, config);
				return this;
			}
			public WindowBuilder centerPasswordField(int index) {
				if (!jPasswordFields.isEmpty()) {
					WINDOW_HELPER.setLayout(new GridBagLayout());
					WINDOW_HELPER.add(jPasswordFields.get(index), new GridBagConstraints());
				}
				return this;
			}
			public WindowBuilder centerPopup(int index) {
				if (!jPopups.isEmpty()) {
					WINDOW_HELPER.setLayout(new GridBagLayout());
					WINDOW_HELPER.add(jPopups.get(index), new GridBagConstraints());
				}
				return this;
			}

			public void centerTextArea(int index) {
				if (!jTextFields.isEmpty()) {
					WINDOW_HELPER.setLayout(new GridBagLayout());
					WINDOW_HELPER.add(jTextFields.get(index), new GridBagConstraints());
				}
			}
			public WindowBuilder centerButton(int index) {
				if (!jButtons.isEmpty()) {
					WINDOW_HELPER.setLayout(new GridBagLayout());
					WINDOW_HELPER.add(jButtons.get(index), new GridBagConstraints());
					Window.update();
				} else if (!jToggleButtons.isEmpty()) {
					WINDOW_HELPER.setLayout(new GridBagLayout());
					WINDOW_HELPER.add(jToggleButtons.get(index), new GridBagConstraints());
					Window.update();
				}
				return this;
			}
		}
	}

	private static void update() {
		while (WINDOW_EXISTS) {
			J_PANEL.updateUI();
		}
	}
	public static boolean isWindowExists() {
		return WINDOW_EXISTS;
	}
}
