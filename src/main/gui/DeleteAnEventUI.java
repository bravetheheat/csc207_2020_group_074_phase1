package main.gui;

import main.gui_interface.IDeleteAnEventUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteEventButtonListener;
import main.guilisteners.SelectEventButtonListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * UI class for deleting an event(or selecting an event for future modification)
 *
 * @see ModifyEventUI
 * @author Steven Yuan
 */
@SuppressWarnings("FieldCanBeLocal")

public class DeleteAnEventUI extends JFrame implements IDeleteAnEventUI {

    private JPanel jPanel;
    private JButton deleteEventButton;
    private JList<Object> listOfEvents;
    private JScrollPane jScrollPane;
    private JButton backButton;
    private BackButtonListener backButtonListener;
    private DeleteEventButtonListener deleteEventButtonListener;
    private ModifyEventUI modifyEventUI;
    private JButton selectEventButton;
    private SelectEventButtonListener selectEventButtonListener;
    private int eventIndex = -1;

    public DeleteAnEventUI(ArrayList<String> listOfEventsInfo) {
        this.jPanel = new JPanel();

        this.setTitle("Available Events");
        this.setSize(600, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        listOfEvents = new JList<>(listOfEventsInfo.toArray());
        listOfEvents.setVisibleRowCount(20);
        jScrollPane = new JScrollPane(listOfEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setPreferredSize(new Dimension(550, 400));
        jScrollPane.setViewportView(listOfEvents);
        jPanel.add(jScrollPane);

        selectEventButton = new JButton("Select");
        jPanel.add(selectEventButton);

        deleteEventButton = new JButton("Delete");
        jPanel.add(deleteEventButton);

        backButton = new JButton("Back");
        jPanel.add(backButton);

        this.add(jPanel);
        this.setVisible(true);

        backButton.addActionListener(e -> notifyListenerBackButtonClicked());

        selectEventButton.addActionListener(
                e -> notifyOnSelectEventButtonClicked());

        deleteEventButton.addActionListener(
                e -> notifyListenerDeleteEventButtonClicked());
    }

    /**
     * Return the event index selected by the user.
     * @return event index
     */
    @Override
    public int getEventIndexFromList() {
        return listOfEvents.getSelectedIndex();
    }

    /**
     * Return the event index
     * @return event index
     */
    public int getEventIndex() {
        return this.eventIndex;
    }

    /**
     * Add the presenter class that implements <code>DeleteEventButtonListener</code>
     * @param listener <code>DeleteEventButtonListener</code>
     */
    @Override
    public void addDeleteEventButtonListener(DeleteEventButtonListener listener) {
        deleteEventButtonListener = listener;
    }

    /**
     * Add the presenter class that implements <code>BackButtonListener</code>
     * @param listener <code>BackButtonListener</code>
     */
    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    /**
     * Add the presenter class that implements <code>SelectEventButtonListener</code>
     * @param listener <code>SelectEventButtonListener</code>
     */
    @Override
    public void addSelectEventButtonListener(SelectEventButtonListener listener) {
        selectEventButtonListener = listener;
    }

    /**
     * Notify <code>DeleteAnEventUI</code> if the user clicks on the button
     */
    public void notifyListenerDeleteEventButtonClicked() {
        deleteEventButtonListener.onDeleteEventButtonClicked();
    }

    /**
     * Notify <code>DeleteAnEventUI</code> if the user clicks on the button
     */
    public void notifyListenerBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    /**
     * Notify <code>DeleteAnEventUI</code> if the user clicks on the button
     */
    public void notifyOnSelectEventButtonClicked() {
        eventIndex = selectEventButtonListener.onSelectEventButtonClicked();
    }

    /**
     * Display successful notification
     */
    public void deleteEventSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully deleted this event!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Display error notification
     */
    public void deleteEventError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Go to <code>ModifyEventUI</code>
     * @return code>ModifyEventUI</code>
     */
    public ModifyEventUI goToModifyEventUI() {
        modifyEventUI = new ModifyEventUI();
        this.dispose();
        return modifyEventUI;
    }
}