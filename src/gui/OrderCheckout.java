package gui;

import utils.ComponentStyle;

import javax.swing.*;

public class OrderCheckout implements ComponentStyle {
    private JPanel contentPane;
    private JButton button1;
    public OrderCheckout() {
        setBackgroundDefault(contentPane);
        setPrimaryButtonStyle(button1);
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}

