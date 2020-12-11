package main.gui;

import main.gui_interface.INotificationUI;
import main.guilisteners.BackButtonListener;

import javax.swing.*;
import java.awt.*;

/**
 * A frame that display messages to the user.
 *
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class NotificationUI extends JFrame implements INotificationUI {
    private JButton backButton;
    private BackButtonListener backButtonListener;

    public NotificationUI() {
        this.setLayout(null);

        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // back button
        backButton = new JButton("Back");
        Dimension dimBackButton = backButton.getPreferredSize();
        backButton.setBounds(0, 0, dimBackButton.width, dimBackButton.height);
        add(backButton);

        backButton.addActionListener(e -> notifyListenerOnBackButtonClicked());
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    @Override
    public void notifyListenerOnBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    @Override
    public void addMessage(JLabel jLabel, String message) {
        jLabel.setText(message);
        Dimension dim = jLabel.getPreferredSize();
        int xPos = (this.getWidth() - dim.width) / 2;
        int yPos = (this.getHeight() - dim.height) / 2;
        jLabel.setBounds(xPos, yPos, dim.width, dim.height);
        this.add(jLabel);
    }

}
