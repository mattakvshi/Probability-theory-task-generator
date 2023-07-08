package HomeClasses.AppFiles;

import HomeClasses.ActionPerformedClasses.*;
import HomeClasses.ConfigurationClasses.ThemesWRC;
import HomeClasses.TaskManager.Task;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class App extends JFrame {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton buttonTask1;
    private JButton buttonTask2;
    private JButton buttonTask3;
    private JButton buttonTask4;
    private JButton buttonTask5;
    private JButton buttonTask6;
    private JButton buttonTask7;
    private JButton buttonTask8;
    private JButton buttonTask9;
    private JButton buttonTask10;
    private JButton buttonTask11;
    private JButton buttonTask12;
    private JButton buttonTask13;
    private JButton buttonTask14;
    private JButton buttonTask15;
    private JButton buttonTask16;
    private JButton buttonTask17;
    private JButton buttonTask18;
    private JButton buttonTask19;
    private JButton buttonTask20;
    private JButton buttonTask21;
    private JTextField textFieldCountVar;
    private JLabel labelInputCountVar;
    private JPanel lowPanel;
    private JButton buttonCreateVar;
    private JCheckBox checkAllTask;
    private JLabel labelChoseTask;
    private JPanel topPanel;
    private JTabbedPane tabPane1;
    private JPanel page1Panel;
    private JPanel pageSettings;
    private JRadioButton radioButtonLight;
    private JRadioButton radioButtonDark;
    private JRadioButton radioButtonContrast;
    private JLabel labelChooseTheme;
    private JPanel settingsTopPanel;
    private JPanel settingsTreePanel;
    private JLabel labelChoosePath;
    private JButton buttonChoose;
    private JComboBox comboBoxFontFamily;
    private JComboBox comboBoxFontSize;
    private JLabel labelChoseFontFamily;
    private JLabel labelChoseFontSize;
    private JTabbedPane tabbedPane1;
    private JList helpList;
    private JList licenceList;
    final ColorListener colorListener;
    final RadioListener radioListener;
    final PathListener pathListener;
    final CreateVarListener createVarListener;
    final ThemesDesign themesDesign;
    final CheckItemListener checkItemListener;
    final ButtonProperties buttonProperties;
    final FontSizeListener fontSizeListener;
    final FontFamilyListener fontFamilyListener;
    ThemesWRC themesWRC = new ThemesWRC();
    String savedUserTheme;

    {
        try {
            savedUserTheme = themesWRC.readFromTxt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    App() {
        super("Генератора задач по Теории вероятности");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(818, 580);
        ImageIcon img = new ImageIcon("C:\\Program Files (x86)\\Probability-theory-task-generator\\image\\PTTG-image.png");
        setIconImage(img.getImage());
        setResizable(false);
        setVisible(true);
        Set<Task> taskSet = new HashSet<>();
        JButton[] buttons = new JButton[]
                {
                        buttonTask1, buttonTask2, buttonTask3, buttonTask4, buttonTask5, buttonTask6, buttonTask7,
                        buttonTask8, buttonTask9, buttonTask10, buttonTask11, buttonTask12, buttonTask13, buttonTask14,
                        buttonTask15, buttonTask16, buttonTask17, buttonTask18, buttonTask19, buttonTask20, buttonTask21
                };
        colorListener = new ColorListener(taskSet);
        fontSizeListener = new FontSizeListener();
        fontFamilyListener = new FontFamilyListener();
        createVarListener = new CreateVarListener(colorListener, mainPanel, textFieldCountVar, fontSizeListener, fontFamilyListener);
        String userFilePath = CreateVarListener.getUserFilePath();
        if (userFilePath != null) {
            labelChoosePath.setText(userFilePath);
            buttonChoose.setText("Изменить");
        }
        pathListener = new PathListener(labelChoosePath, buttonChoose, createVarListener);
        themesDesign = new ThemesDesign
                (
                        textFieldCountVar, labelInputCountVar, lowPanel, buttonCreateVar,
                        checkAllTask, labelChoseTask, topPanel, tabPane1, page1Panel, pageSettings, radioButtonLight,
                        radioButtonDark, radioButtonContrast, labelChooseTheme, settingsTopPanel, settingsTreePanel,
                        labelChoosePath, mainPanel, buttonPanel, labelChoseFontFamily, labelChoseFontSize, helpList, licenceList
                );
        radioListener = new RadioListener(radioButtonLight, radioButtonDark, radioButtonContrast, mainPanel, themesDesign);
        buttonProperties = new ButtonProperties(colorListener, buttons);
        buttonProperties.buttonPropertiesRun();
        checkItemListener = new CheckItemListener(taskSet, checkAllTask, buttons);
        buttonCreateVar.addActionListener(createVarListener);
        comboBoxFontFamily.addActionListener(fontFamilyListener);
        comboBoxFontSize.addActionListener(fontSizeListener);
        buttonChoose.addActionListener(pathListener);
        radioButtonDark.addActionListener(radioListener);
        radioButtonLight.addActionListener(radioListener);
        radioButtonContrast.addActionListener(radioListener);
        checkAllTask.addItemListener(checkItemListener);
        if (Objects.equals(savedUserTheme, "Original")) {
            try {
                UIManager.setLookAndFeel(new FlatMacDarkLaf());
            } catch (Exception ex) {
                setDefaultLookAndFeelDecorated(true);
                ex.printStackTrace();
            }
            themesDesign.initPropertiesBlack();
            SwingUtilities.updateComponentTreeUI(mainPanel);
            radioButtonDark.setSelected(true);
        } else if (Objects.equals(savedUserTheme, "Light")) {
            try {
                UIManager.setLookAndFeel(new FlatMacLightLaf());
            } catch (Exception ex) {
                setDefaultLookAndFeelDecorated(true);
                ex.printStackTrace();
            }
            themesDesign.initPropertiesLight();
            SwingUtilities.updateComponentTreeUI(mainPanel);
            radioButtonLight.setSelected(true);
        } else if (Objects.equals(savedUserTheme, "Dark")) {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (Exception ex) {
                setDefaultLookAndFeelDecorated(true);
                ex.printStackTrace();
            }
            themesDesign.initPropertiesContrast();
            SwingUtilities.updateComponentTreeUI(mainPanel);
            radioButtonContrast.setSelected(true);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane1 = new JTabbedPane();
        mainPanel.add(tabPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        page1Panel = new JPanel();
        page1Panel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane1.addTab("Основная", page1Panel);
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 5, 0, 0), -1, -1));
        page1Panel.add(topPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelChoseTask = new JLabel();
        labelChoseTask.setText("Выберите номера: ");
        topPanel.add(labelChoseTask, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        topPanel.add(spacer1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelInputCountVar = new JLabel();
        labelInputCountVar.setText("Количество вариантов:");
        topPanel.add(labelInputCountVar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldCountVar = new JTextField();
        textFieldCountVar.setText("");
        topPanel.add(textFieldCountVar, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 30), new Dimension(80, 30), new Dimension(80, 30), 0, false));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayoutManager(3, 7, new Insets(0, 10, 0, 10), -1, -1));
        page1Panel.add(buttonPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonTask1 = new JButton();
        buttonTask1.setText("1");
        buttonPanel.add(buttonTask1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask2 = new JButton();
        buttonTask2.setText("2");
        buttonPanel.add(buttonTask2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask3 = new JButton();
        buttonTask3.setText("3");
        buttonPanel.add(buttonTask3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask4 = new JButton();
        buttonTask4.setText("4");
        buttonPanel.add(buttonTask4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask6 = new JButton();
        buttonTask6.setText("6");
        buttonPanel.add(buttonTask6, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask7 = new JButton();
        buttonTask7.setText("7");
        buttonPanel.add(buttonTask7, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask8 = new JButton();
        buttonTask8.setText("8");
        buttonPanel.add(buttonTask8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask9 = new JButton();
        buttonTask9.setText("9");
        buttonPanel.add(buttonTask9, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask10 = new JButton();
        buttonTask10.setText("10");
        buttonPanel.add(buttonTask10, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask11 = new JButton();
        buttonTask11.setText("11");
        buttonPanel.add(buttonTask11, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask12 = new JButton();
        buttonTask12.setText("12");
        buttonPanel.add(buttonTask12, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask13 = new JButton();
        buttonTask13.setText("13");
        buttonPanel.add(buttonTask13, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask14 = new JButton();
        buttonTask14.setText("14");
        buttonPanel.add(buttonTask14, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask5 = new JButton();
        buttonTask5.setText("5");
        buttonPanel.add(buttonTask5, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask15 = new JButton();
        buttonTask15.setText("15");
        buttonPanel.add(buttonTask15, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask16 = new JButton();
        buttonTask16.setText("16");
        buttonPanel.add(buttonTask16, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask17 = new JButton();
        buttonTask17.setText("17");
        buttonPanel.add(buttonTask17, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask18 = new JButton();
        buttonTask18.setText("18");
        buttonPanel.add(buttonTask18, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask19 = new JButton();
        buttonTask19.setText("19");
        buttonPanel.add(buttonTask19, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask20 = new JButton();
        buttonTask20.setText("20");
        buttonPanel.add(buttonTask20, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        buttonTask21 = new JButton();
        buttonTask21.setText("21");
        buttonPanel.add(buttonTask21, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, new Dimension(70, 70), 0, false));
        lowPanel = new JPanel();
        lowPanel.setLayout(new GridLayoutManager(2, 3, new Insets(10, 5, 0, 0), -1, -1));
        page1Panel.add(lowPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkAllTask = new JCheckBox();
        checkAllTask.setText("Выбрать все");
        lowPanel.add(checkAllTask, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCreateVar = new JButton();
        buttonCreateVar.setText("Составить варианты");
        lowPanel.add(buttonCreateVar, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, 40), null, new Dimension(300, 40), 0, false));
        final Spacer spacer2 = new Spacer();
        lowPanel.add(spacer2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        lowPanel.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pageSettings = new JPanel();
        pageSettings.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabPane1.addTab("Настройки", pageSettings);
        settingsTopPanel = new JPanel();
        settingsTopPanel.setLayout(new GridLayoutManager(2, 6, new Insets(10, 10, 0, 0), -1, -1));
        pageSettings.add(settingsTopPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(800, 200), null, new Dimension(800, 100), 0, false));
        labelChooseTheme = new JLabel();
        labelChooseTheme.setText("Выберете цвет темы:");
        settingsTopPanel.add(labelChooseTheme, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonDark = new JRadioButton();
        radioButtonDark.setActionCommand("Тёмная");
        radioButtonDark.setSelected(false);
        radioButtonDark.setText("Стандартная");
        settingsTopPanel.add(radioButtonDark, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonLight = new JRadioButton();
        radioButtonLight.setActionCommand("RadioButton");
        radioButtonLight.setText("Светлая");
        settingsTopPanel.add(radioButtonLight, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        settingsTreePanel = new JPanel();
        settingsTreePanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        settingsTopPanel.add(settingsTreePanel, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelChoosePath = new JLabel();
        labelChoosePath.setText("  Выбор пути сохранения файлов");
        settingsTreePanel.add(labelChoosePath, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, -1), null, new Dimension(300, -1), 0, false));
        buttonChoose = new JButton();
        buttonChoose.setText("Открыть");
        settingsTreePanel.add(buttonChoose, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonContrast = new JRadioButton();
        radioButtonContrast.setText("Тёмная");
        settingsTopPanel.add(radioButtonContrast, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelChoseFontFamily = new JLabel();
        labelChoseFontFamily.setText("Выберите шрифт:");
        settingsTopPanel.add(labelChoseFontFamily, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelChoseFontSize = new JLabel();
        labelChoseFontSize.setText("Размер шрифта:");
        settingsTopPanel.add(labelChoseFontSize, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxFontSize = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("<default>");
        defaultComboBoxModel1.addElement("11");
        defaultComboBoxModel1.addElement("12");
        defaultComboBoxModel1.addElement("13");
        defaultComboBoxModel1.addElement("14");
        defaultComboBoxModel1.addElement("15");
        defaultComboBoxModel1.addElement("16");
        defaultComboBoxModel1.addElement("17");
        defaultComboBoxModel1.addElement("18");
        comboBoxFontSize.setModel(defaultComboBoxModel1);
        settingsTopPanel.add(comboBoxFontSize, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxFontFamily = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("<default>");
        defaultComboBoxModel2.addElement("Times New Roman");
        defaultComboBoxModel2.addElement("Calibri");
        defaultComboBoxModel2.addElement("Arial");
        defaultComboBoxModel2.addElement("Verdana");
        defaultComboBoxModel2.addElement("Comic Sans MS");
        defaultComboBoxModel2.addElement("Georgia");
        defaultComboBoxModel2.addElement("Impact");
        comboBoxFontFamily.setModel(defaultComboBoxModel2);
        settingsTopPanel.add(comboBoxFontFamily, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tabbedPane1 = new JTabbedPane();
        tabPane1.addTab("Помощь", tabbedPane1);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Инструкция по использованию", panel1);
        helpList = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        defaultListModel1.addElement("При запуске программы вас встречает окно с тремя разделами: \"Основная\", \"Настройки\", \"Помощь\".");
        defaultListModel1.addElement("Сечас в находитесь в разделе \"Помощь\".");
        defaultListModel1.addElement("Далее об основном функционале:");
        defaultListModel1.addElement("1) Основная страница");
        defaultListModel1.addElement("a)На основной странице в окошке \"Колличество вариантов\" вы вписываете число вариантов, которое необходимо");
        defaultListModel1.addElement("сгенерировать.");
        defaultListModel1.addElement("б)Далее в области \"Выбирете номера\" нужно указать, из какого набора номеров будет состоять каждый вариант.");
        defaultListModel1.addElement("в)Можно выбрать как конкретные номера, так и все номера сразу поставив галочку в чекбоксе \"Выбрать все\".");
        defaultListModel1.addElement("г)И далее нажимаем на кнопку \"Составить варианты\" для их генерации.");
        defaultListModel1.addElement("2) Страница настроек");
        defaultListModel1.addElement("a)В настройках вы по желанию можете сменить тему приложения на одну из трёх на выбор.");
        defaultListModel1.addElement("б)Так же вы можете выбрать шрифт и размер шрифта, который будет использован в генерируемых файлах.");
        defaultListModel1.addElement("в)Ну и наконец вы можете выбрать путь куда будут сохранятся сгенерированные файлы: для этого вы нвжимаете");
        defaultListModel1.addElement("на кнопочку \"Открыть\" в открывшемся окне выбираете место, куда будет сохранятся папка с вариантами и в");
        defaultListModel1.addElement("нижней части окна в окошке нужно прописать название папки, в которую будут сохранены варианты.");
        defaultListModel1.addElement("Спасибо, что используете наш продукт!");
        defaultListModel1.addElement("С уважением, EVC inc.");
        defaultListModel1.addElement("По вопросам обращайтесь на почту: ytube9504@gmail.com.");
        helpList.setModel(defaultListModel1);
        panel1.add(helpList, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Лицензионное соглашение", panel2);
        licenceList = new JList();
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        defaultListModel2.addElement("Использование этого приложения официально возможно при условии стоящих автоматов у студентов:");
        defaultListModel2.addElement("1. Емельяненко Александр 24/1");
        defaultListModel2.addElement("2. Владарчук Дмитрий 24/1");
        defaultListModel2.addElement("3. Сидоренко Максим 24/1");
        defaultListModel2.addElement("");
        defaultListModel2.addElement("(С)EVC inc.");
        licenceList.setModel(defaultListModel2);
        panel2.add(licenceList, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonLight);
        buttonGroup.add(radioButtonDark);
        buttonGroup.add(radioButtonContrast);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
