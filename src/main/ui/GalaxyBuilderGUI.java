package ui;

import model.Galaxy;
import model.SolarSystem;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GalaxyBuilderGUI extends JFrame implements ListSelectionListener {
    private static final String JSON_STORE = "./data/galaxy.json";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private Galaxy galaxy;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel galaxyPanel;

    private DefaultListModel solarSystemsModel;
    private JList solarSystems;

    public GalaxyBuilderGUI() {
        // set up lists and readers/writers

        galaxyPanel = new JPanel();
        galaxyPanel.setLayout(new BoxLayout(galaxyPanel, BoxLayout.PAGE_AXIS));
        addImageToGUI();
        add(galaxyPanel);
        addSolarSystemsPanel();
        addButtonsPanel();
    }

    private void addButtonsPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,2));
        buttonPanel.add(new JButton(new AddSolarSystemAction()));
        buttonPanel.add(new JButton(new EditSolarSystemsAction()));
        buttonPanel.add(new JButton(new DisplaySolarSystemsAction()));
        buttonPanel.add(new JButton(new ChangeGalaxyNameAction()));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));
    }

    private void addSolarSystemsPanel() {
        solarSystemsModel = new DefaultListModel();
        updateSolarSystems();
    }

    private void updateSolarSystems() {
        solarSystemsModel.clear();
        for (SolarSystem solarSystem : galaxy.getSolarSystems().values()) {
            String solarSystemString = formatSolarSystem(solarSystem);
            solarSystemsModel.addElement(solarSystemString);
        }
    }

    private String formatSolarSystem(SolarSystem solarSystem) {
        String result = solarSystem.getName() + " - Central Body: " + solarSystem.getCentralBody().getCentralBodyType()
                + " - # of planets: " + solarSystem.getPlanetCount();
        return result;
    }

    private void addImageToGUI() {
        ImageIcon icon = new ImageIcon("./data/tobs.jpg");
        JLabel label = new JLabel(icon);
        JPanel background = new JPanel();
        background.setPreferredSize(new Dimension(150, 150));
        galaxyPanel.add(background);
        background.add(label);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    /**
     * Represents action to be taken when user wants to add a new solar
     * system to the galaxy.
     */
    private class AddSolarSystemAction extends AbstractAction {

        AddSolarSystemAction() {
            super("New Solar System");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //TODO
        }

    }

    /**
     * Represents action to be taken when user wants to add a new solar
     * system to the galaxy.
     */
    private class EditSolarSystemsAction extends AbstractAction {

        EditSolarSystemsAction() {
            super("Edit Solar Systems");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //TODO
        }
    }

    /**
     * Represents action to be taken when user wants to add a new solar
     * system to the galaxy.
     */
    private class DisplaySolarSystemsAction extends AbstractAction {

        DisplaySolarSystemsAction() {
            super("Display Solar Systems");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //TODO
        }
    }

    /**
     * Represents action to be taken when user wants to add a new solar
     * system to the galaxy.
     */
    private class ChangeGalaxyNameAction extends AbstractAction {

        ChangeGalaxyNameAction() {
            super("Change galaxy name");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //TODO
        }
    }

    /**
     * Represents action to be taken when user wants to add a new solar
     * system to the galaxy.
     */
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //TODO
        }
    }

    /**
     * Represents action to be taken when user wants to add a new solar
     * system to the galaxy.
     */
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //TODO
        }
    }
}


