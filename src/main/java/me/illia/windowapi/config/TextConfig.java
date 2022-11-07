package me.illia.windowapi.config;

import me.illia.windowapi.config.other.Config;
import me.illia.windowapi.config.other.ConfigBuilder;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class TextConfig implements Config {
    public static TextConfig INSTANCE;
    public static boolean forPassword;
    public static JPasswordField passwordField = null;
    public static JTextArea textArea = null;
    @Override
    public TextConfigBuilder create() {
        return new TextConfigBuilder();
    }
    public static TextConfig init(int width, int height, boolean forPassword) {
        TextConfig.forPassword = forPassword;
        return new TextConfig(width, height);
    }
    public static TextConfig init(int width, int height, boolean forPassword, int x, int y) {
        TextConfig.forPassword = forPassword;
        return new TextConfig(width, height, x, y);
    }
    protected JTextComponent getUsedField() {
        return forPassword ? passwordField : textArea;
    }

    TextConfig(int width, int height) {
        INSTANCE = this;
        if (forPassword) passwordField = new JPasswordField();
        else textArea = new JTextArea();
    }
    TextConfig(int width, int height, int x, int y) {
        new TextConfig(width, height);
        getUsedField().setBounds(x, y, width, height);
    }
    public int getXPos() {
        return getUsedField().getX();
    }
    public int getYPos() {
        return getUsedField().getY();
    }
    public int getWidth() {
        return getUsedField().getWidth();
    }
    public int getHeight() {
        return getUsedField().getHeight();
    }

    public static class TextConfigBuilder implements ConfigBuilder {
        @Override
        public void build() {
            System.out.println("Created text config");
        }
        public TextConfigBuilder setPosition(int x, int y) {
            if (forPassword) passwordField.setBounds(x, y, INSTANCE.getWidth(), INSTANCE.getHeight());
            else textArea.setBounds(x, y, INSTANCE.getWidth(), INSTANCE.getHeight());
            return this;
        }
        public TextConfigBuilder setSize(int width, int height) {
            if (forPassword) passwordField.setBounds(INSTANCE.getXPos(), INSTANCE.getYPos(), width, height);
            return this;
        }
    }
}
