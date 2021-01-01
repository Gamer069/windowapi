package me.illia.windowapi.config;

import me.illia.windowapi.config.other.Config;
import me.illia.windowapi.config.other.ConfigBuilder;

import javax.swing.*;

public class TextConfig implements Config {
    public static TextConfig INSTANCE;
    public static boolean forPassword;
    public static JPasswordField passwordField = null;
    public static JTextArea textArea = null;
    @Override
    public TextConfigBuilder create() {
        return new TextConfigBuilder();
    }
    public void toPassword() {
        forPassword = true;
    }
    public static TextConfig init(int width, int height, boolean forPassword) {
        TextConfig.forPassword = forPassword;
        return new TextConfig(width, height);
    }
    public static TextConfig init(int width, int height, boolean forPassword, int x, int y) {
        TextConfig.forPassword = forPassword;
        return new TextConfig(width, height, x, y);
    }

    TextConfig(int width, int height) {
        INSTANCE = this;
        if (forPassword) passwordField = new JPasswordField();
        else textArea = new JTextArea();
    }
    TextConfig(int width, int height, int x, int y) {
        INSTANCE = this;
        if (forPassword) {
            passwordField = new JPasswordField();
            passwordField.setBounds(x, y, width, height);
        } else {
            textArea = new JTextArea();
            textArea.setBounds(x, y, width, height);
        }
    }
    public int getXPos() {
        if (forPassword) return passwordField.getX();
        else return textArea.getX();
    }
    public int getYPos() {
        if (forPassword) return passwordField.getY();
        else return textArea.getY();
    }
    public int getWidth() {
        if (forPassword) return passwordField.getWidth();
        else return textArea.getY();
    }
    public int getHeight() {
        if (forPassword) return passwordField.getHeight();
        else return textArea.getHeight();
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
