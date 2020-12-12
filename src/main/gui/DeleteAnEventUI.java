package main.gui;

import main.gui_interface.IDeleteAnEventUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.DeleteEventButtonListener;
import main.guilisteners.SelectEventButtonListener;

import javax.swing.*;
import java.util.ArrayList;

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
    private int eventIndex;

    public DeleteAnEventUI(ArrayList<String> listOfEventsInfo) {
        this.jPanel = new JPanel();

        this.setTitle("Available Events");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        listOfEvents = new JList<>(listOfEventsInfo.toArray());
        listOfEvents.setVisibleRowCount(20);
        jScrollPane = new JScrollPane(listOfEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
                e -> selectEventButtonListener.onSelectEventButtonClicked());

        deleteEventButton.addActionListener(
                e -> notifyListenerDeleteEventButtonClicked());
    }

    @Override
    public int getEventIndexFromList() {
        return listOfEvents.getSelectedIndex();
    }

    public int getEventIndex() {
        return this.eventIndex;
    }

    @Override
    public void addDeleteEventButtonListener(DeleteEventButtonListener listener) {
        deleteEventButtonListener = listener;
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    @Override
    public void addSelectEventButtonListener(SelectEventButtonListener listener) {
        selectEventButtonListener = listener;
    }

    public void notifyListenerDeleteEventButtonClicked() {
        deleteEventButtonListener.onDeleteEventButtonClicked();
    }

    public void notifyListenerBackButtonClicked() {
        backButtonListener.onBackButtonClicked();
    }

    public void notifySelectEventButtonClicked() {
        eventIndex = selectEventButtonListener.onSelectEventButtonClicked();
    }

    public void deleteEventSuccessful() {
        JOptionPane.showMessageDialog(this,
                "You have successfully deleted this event!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void deleteEventError() {
        JOptionPane.showMessageDialog(this,
                "Please try again!",
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public ModifyEventUI goToModifyEventUI() {
        modifyEventUI = new ModifyEventUI();
        this.dispose();
        return modifyEventUI;
    }
}
