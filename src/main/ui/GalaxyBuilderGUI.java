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
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Galaxy galaxy;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JSplitPane splitPaneTop;
    private JSplitPane splitPane;
    private JPanel galaxyPanel;

    private DefaultListModel solarSystemsModel;
    private JList solarSystems;

    public GalaxyBuilderGUI() {
        // set up lists and readers/writers
        frame = new JFrame();
        initializeGalaxy();
        Dimension minSize = new Dimension(100, 50);

        JScrollPane solarSystemPane = addSolarSystemsPane();
        JScrollPane imagePane = addImageToGUI();
        JScrollPane buttonsPane = addButtonsPane();

        splitPaneTop = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                solarSystemPane, imagePane);
        splitPaneTop.setDividerLocation(250);
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                splitPaneTop, buttonsPane);

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(375);

        splitPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        solarSystemPane.setMinimumSize(minSize);
        buttonsPane.setMinimumSize(minSize);
        imagePane.setMinimumSize(minSize);
        frame.add(splitPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Galaxy Builder");
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeGalaxy() {
        String name;
        name = JOptionPane.showInputDialog(null,
                "Name your galaxy! \n (You can change this later)",
                "Name galaxy",
                JOptionPane.QUESTION_MESSAGE);

        if (name != null) {
            galaxy = new Galaxy(name);
        } else {
            galaxy = new Galaxy("Default");
        }
    }

    private JScrollPane addButtonsPane() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,3));
        buttonPanel.add(new JButton(new AddSolarSystemAction()));
        JButton editButton = new JButton(new EditSolarSystemsAction());
        buttonPanel.add(editButton);
        JButton displayButton = new JButton(new DisplaySolarSystemsAction());
        buttonPanel.add(displayButton);
        buttonPanel.add(new JButton(new ChangeGalaxyNameAction()));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));

        editButton.setEnabled(false);
        displayButton.setEnabled(false);

        JScrollPane buttonsPane = new JScrollPane(buttonPanel);
        return buttonsPane;
    }

    private JScrollPane addSolarSystemsPane() {
        solarSystemsModel = new DefaultListModel();
        updateSolarSystems();
        solarSystems = new JList(solarSystemsModel);

        solarSystems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        solarSystems.setSelectedIndex(0);
        solarSystems.addListSelectionListener(this);

        JScrollPane solarSystemsScrollPane = new JScrollPane(solarSystems);

        return solarSystemsScrollPane;
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

    private JScrollPane addImageToGUI() {
        ImageIcon icon = new ImageIcon("./data/galaxy.jpg");
        JLabel galaxyImage = new JLabel(icon);
        JLabel galaxyName = new JLabel("The " + galaxy.getName() + " galaxy!");
        galaxyName.setFont(new Font("Serif", Font.BOLD, 28));
        galaxyName.setForeground(Color.white);
        JPanel background = new JPanel();
        background.setPreferredSize(new Dimension(50, 50));
        background.setBackground(Color.black);
        background.add(galaxyName);
        background.add(galaxyImage);

        JScrollPane imagePane = new JScrollPane(background);
        return imagePane;
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


