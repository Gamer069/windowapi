package me.illia.windowapi.config;

import me.illia.windowapi.config.other.Config;
import me.illia.windowapi.config.other.ConfigBuilder;

import javax.swing.*;
import java.awt.*;

public class ButtonConfig implements Config {
    public static ButtonConfig INSTANCE;
    public static int btnWidth;
    public static int btnHeight;
    public static JButton btn;

    ButtonConfig(int btnWidth, int btnHeight, int x, int y) {
        new ButtonConfig();
        ButtonConfig.btnWidth = btnWidth;
        ButtonConfig.btnHeight = btnHeight;
        ButtonConfig.btn = new JButton();
        ButtonConfig.setYPosition(y);
        ButtonConfig.setXPosition(x);
    }

    ButtonConfig() {
        INSTANCE = this;
    }

    public static ButtonConfig init(int btnWidth, int btnHeight) {
        return new ButtonConfig(btnWidth, btnHeight);
    }
    public static ButtonConfig init(int btnWidth, int btnHeight, int x, int y) {
        return new ButtonConfig(btnWidth, btnHeight, x, y);
    }


    public ButtonConfig(int btnWidth, int btnHeight) {
        new ButtonConfig();
        ButtonConfig.btnWidth = btnWidth;
        ButtonConfig.btnHeight = btnHeight;
        ButtonConfig.btn = new JButton();
    }

    public static void updateBtn() {
        btn.updateUI();
    }

    @Override
    public ButtonConfigBuilder create() {
        return new ButtonConfigBuilder();
    }
    public int getButtonWidth() {
        updateBtn();
        return btnWidth;
    }

    public int getButtonHeight() {
        updateBtn();
        return btnHeight;
    }
    public int getXPosition() {
        return btn.getX();
    }
    public int getYPosition() {
        return btn.getY();
    }
    public static void setYPosition(int y) {
        btn.setBounds(INSTANCE.getXPosition(), y, INSTANCE.getButtonWidth(), INSTANCE.getButtonHeight());
    }
    public static void setXPosition(int x) {
        btn.setBounds(x, INSTANCE.getYPosition(), INSTANCE.getButtonWidth(), INSTANCE.getButtonHeight());
    }

    public static class ButtonConfigBuilder implements ConfigBuilder {
        @Override
        public void build() {
            System.out.println("Button config created");
        }
        public ButtonConfigBuilder setSize(int width, int height) {
            btn.setSize(new Dimension(width, height));
            return this;
        }
        public ButtonConfigBuilder setPosition(int x, int y) {
            btn.setBounds(x, y, INSTANCE.getButtonWidth(), INSTANCE.getButtonHeight());
            return this;
        }
    }
}
