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
    private JScrollPane jScrollPane;
    private JButton backButton;
    private JButton selectButton;
    private JButton deleteButton;
    private BackButtonListener backButtonListener;
    private ModifyEventUI modifyEventUI;
    private ConfirmSelectSpeakerButtonListener confirmSelectSpeakerButtonListener;
    private DeleteSpeakerButtonListener deleteSpeakerButtonListener;

    public ModifySpeakerUI(ArrayList<String> listOfSpeakerInfo) {
        jPanel = new JPanel();

        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // list of users
        speakerList = new JList<>(listOfSpeakerInfo.toArray());

        speakerList.setVisibleRowCount(20);
        jScrollPane = new JScrollPane(speakerList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(jScrollPane);

        // select button
        selectButton = new JButton("Select");
        jPanel.add(selectButton);

        // delete button
        deleteButton = new JButton("Delete");
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

    public ModifyEventUI goToModifyEventUI() {
        modifyEventUI = new ModifyEventUI();
        this.dispose();
        return modifyEventUI;
    }
}
