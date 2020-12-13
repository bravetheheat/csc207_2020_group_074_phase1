package main.gui;

import main.gui_interface.IModifySpeakerUI;
import main.guilisteners.BackButtonListener;
import main.guilisteners.ConfirmSelectSpeakerButtonListener;
import main.guilisteners.DeleteSpeakerButtonListener;

import javax.swing.*;
import java.util.ArrayList;

public class ModifySpeakerUI extends JFrame implements IModifySpeakerUI {

    private JPanel jPanel;
    private JList<Object> speakerList;
    private JScrollPane jScrollPane1;
    private JButton backButton;
    private JButton selectButton;
    private JButton deleteButton;
    private BackButtonListener backButtonListener;
    private ModifyEventUI modifyEventUI;
    private ConfirmSelectSpeakerButtonListener confirmSelectSpeakerButtonListener;
    private DeleteSpeakerButtonListener deleteSpeakerButtonListener;
    private JList eventSpeakerList;
    private JScrollPane jScrollPane2;
    private int eventIndex = -1;

    public ModifySpeakerUI(ArrayList<String> listOfSpeakerInfo,
                           ArrayList<String> listOfEventSpeakers) {
        jPanel = new JPanel();

        setSize(1000, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // list of all speakers
        speakerList = new JList<>(listOfSpeakerInfo.toArray());

        speakerList.setVisibleRowCount(10);
        jScrollPane1 = new JScrollPane(speakerList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(jScrollPane1);

        // select button
        selectButton = new JButton("Add speaker");
        jPanel.add(selectButton);

        // list of all speakers
        eventSpeakerList = new JList<>(listOfEventSpeakers.toArray());

        eventSpeakerList.setVisibleRowCount(10);
        jScrollPane2 = new JScrollPane(eventSpeakerList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(jScrollPane2);

        // delete button
        deleteButton = new JButton("Delete speaker");
        jPanel.add(deleteButton);

        // back button
        backButton = new JButton("Back");
        jPanel.add(backButton);

        selectButton.addActionListener(e ->
                confirmSelectSpeakerButtonListener.onConfirmSelectSpeakerButtonClicked());

        deleteButton.addActionListener(e ->
                deleteSpeakerButtonListener.onDeleteSpeakerButtonClicked());

        backButton.addActionListener(e -> backButtonListener.onBackButtonClicked());

        add(jPanel);
        setVisible(true);
    }

    public void storeEventIndexFromModifyEvent(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    public int getEventIndex() {
        return this.eventIndex;
    }

    public int[] getSpeakerIndices() {
        return speakerList.getSelectedIndices();
    }

    public int getEventSpeakerIndex() {
        return eventSpeakerList.getSelectedIndex();
    }

    @Override
    public void addBackButtonListener(BackButtonListener listener) {
        backButtonListener = listener;
    }

    @Override
    public void addConfirmSelectSpeakerButtonListener(
            ConfirmSelectSpeakerButtonListener listener) {
        confirmSelectSpeakerButtonListener = listener;
    }

    @Override
    public void addDeleteSpeakerButtonListener(DeleteSpeakerButtonListener listener) {
        deleteSpeakerButtonListener = listener;
    }

    public void modifySpeakerSuccessful() {
        JOptionPane.showMessageDialog(this,
                "Operation succeeded!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void modifySpeakerError() {
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
