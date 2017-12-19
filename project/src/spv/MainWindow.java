//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TreeSelectionEvent;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
import spv.gen.*;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.jdesktop.application.Application;


/**
 * The application's main frame.
 */
public class MainWindow extends FrameView {

    public MainWindow(SingleFrameApplication app) {
        super(app);
        initComponents();
        

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stateMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                stateAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        stateAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        stateAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    stateAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    stateMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });


    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Application0.getApplication().getMainFrame();
            aboutBox = new About(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Application0.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        filesTab = new javax.swing.JTabbedPane();
        menuTab = new javax.swing.JTabbedPane();
        filesPanel = new javax.swing.JPanel();
        openButton = new javax.swing.JButton();
        viewPanel = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        editPanel = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        formulaButton = new javax.swing.JButton();
        toolsBar = new javax.swing.JToolBar();
        branchCreationButton = new javax.swing.JButton();
        branchDeletingButton = new javax.swing.JButton();
        addToListButton = new javax.swing.JButton();
        listOfRulesButton = new javax.swing.JButton();
        moveUpButton = new javax.swing.JButton();
        moveDownButton = new javax.swing.JButton();
        editVariablesButton = new javax.swing.JButton();
        addExistingStatementButton = new javax.swing.JButton();
        propertiesButton = new javax.swing.JButton();
        syntaxButton = new javax.swing.JButton();
        statePanel = new javax.swing.JPanel();
        javax.swing.JSeparator statePillar = new javax.swing.JSeparator();
        stateMessageLabel = new javax.swing.JLabel();
        stateAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        colors = new javax.swing.JLabel();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(670, 550));

        filesTab.setForeground(new java.awt.Color(0, 0, 255));
        filesTab.setAutoscrolls(true);
        filesTab.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        filesTab.setDoubleBuffered(true);
        filesTab.setName("filesTab"); // NOI18N

        menuTab.setForeground(new java.awt.Color(0, 0, 255));
        menuTab.setName("Tabul_meniu"); // NOI18N

        filesPanel.setName("Panou_fisier_menu"); // NOI18N

        openButton.setBackground(new java.awt.Color(4, 255, 129));
        openButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        openButton.setForeground(new java.awt.Color(255, 255, 255));
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(MainWindow.class);
        openButton.setText(resourceMap.getString("bDeschide.text")); // NOI18N
        openButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        openButton.setName("bDeschide"); // NOI18N
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout filesPanelLayout = new javax.swing.GroupLayout(filesPanel);
        filesPanel.setLayout(filesPanelLayout);
        filesPanelLayout.setHorizontalGroup(
            filesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filesPanelLayout.createSequentialGroup()
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        filesPanelLayout.setVerticalGroup(
            filesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filesPanelLayout.createSequentialGroup()
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        openButton.getAccessibleContext().setAccessibleName(resourceMap.getString("bDeschide.AccessibleContext.accessibleName")); // NOI18N

        menuTab.addTab(resourceMap.getString("Panou_fisier_menu.TabConstraints.tabTitle"), filesPanel); // NOI18N

        viewPanel.setName("panou_ajutor"); // NOI18N

        helpButton.setBackground(new java.awt.Color(4, 255, 129));
        helpButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        helpButton.setForeground(new java.awt.Color(255, 255, 255));
        helpButton.setText(resourceMap.getString("bAjutor.text")); // NOI18N
        helpButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        helpButton.setName("bAjutor"); // NOI18N
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getActionMap(MainWindow.class, this);
        aboutButton.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutButton.setBackground(resourceMap.getColor("bDespre.background")); // NOI18N
        aboutButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        aboutButton.setForeground(new java.awt.Color(255, 255, 255));
        aboutButton.setText(resourceMap.getString("bDespre.text")); // NOI18N
        aboutButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        aboutButton.setName("bDespre"); // NOI18N

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPanelLayout.createSequentialGroup()
                .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(aboutButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(helpButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(274, 274, 274))
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(helpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboutButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpButton.getAccessibleContext().setAccessibleName(resourceMap.getString("bAjutor.AccessibleContext.accessibleName")); // NOI18N
        aboutButton.getAccessibleContext().setAccessibleName(resourceMap.getString("jButton8.AccessibleContext.accessibleName")); // NOI18N

        menuTab.addTab(resourceMap.getString("panou_ajutor.TabConstraints.tabTitle"), viewPanel); // NOI18N

        editPanel.setName("editPanel"); // NOI18N

        searchButton.setBackground(resourceMap.getColor("searchButton.background")); // NOI18N
        searchButton.setFont(resourceMap.getFont("searchButton.font")); // NOI18N
        searchButton.setForeground(resourceMap.getColor("searchButton.foreground")); // NOI18N
        searchButton.setText(resourceMap.getString("searchButton.text")); // NOI18N
        searchButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchButton.setName("searchButton"); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        formulaButton.setBackground(resourceMap.getColor("formulaButton.background")); // NOI18N
        formulaButton.setFont(resourceMap.getFont("formulaButton.font")); // NOI18N
        formulaButton.setForeground(resourceMap.getColor("formulaButton.foreground")); // NOI18N
        formulaButton.setText(resourceMap.getString("formulaButton.text")); // NOI18N
        formulaButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        formulaButton.setName("formulaButton"); // NOI18N
        formulaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formulaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editPanelLayout = new javax.swing.GroupLayout(editPanel);
        editPanel.setLayout(editPanelLayout);
        editPanelLayout.setHorizontalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formulaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addContainerGap())
        );
        editPanelLayout.setVerticalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(formulaButton))
        );

        menuTab.addTab(resourceMap.getString("editPanel.TabConstraints.tabTitle"), editPanel); // NOI18N

        toolsBar.setFloatable(false);
        toolsBar.setName("Tool Box"); // NOI18N

        branchCreationButton.setForeground(new java.awt.Color(0, 51, 255));
        branchCreationButton.setText("<HTML> Apply Rule:  </HTML>"); // NOI18N
        branchCreationButton.setActionCommand(resourceMap.getString("b1.actionCommand")); // NOI18N
        branchCreationButton.setFocusable(false);
        branchCreationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        branchCreationButton.setName("b1"); // NOI18N
        branchCreationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        branchCreationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchCreationButtonActionPerformed(evt);
            }
        });
        toolsBar.add(branchCreationButton);
        branchCreationButton.getAccessibleContext().setAccessibleName(resourceMap.getString("b1.AccessibleContext.accessibleName")); // NOI18N

        branchDeletingButton.setFont(resourceMap.getFont("b1.font")); // NOI18N
        branchDeletingButton.setForeground(new java.awt.Color(204, 0, 51));
        branchDeletingButton.setFocusable(false);
        branchDeletingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        branchDeletingButton.setLabel(resourceMap.getString("b1.label")); // NOI18N
        branchDeletingButton.setName("b1"); // NOI18N
        branchDeletingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        branchDeletingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchDeletingButtonActionPerformed(evt);
            }
        });
        toolsBar.add(branchDeletingButton);

        addToListButton.setForeground(resourceMap.getColor("addToListButton.foreground")); // NOI18N
        addToListButton.setText(resourceMap.getString("addToListButton.text")); // NOI18N
        addToListButton.setFocusable(false);
        addToListButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addToListButton.setName("addToListButton"); // NOI18N
        addToListButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addToListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToListButtonActionPerformed(evt);
            }
        });
        toolsBar.add(addToListButton);

        listOfRulesButton.setForeground(resourceMap.getColor("listOfRulesButton.foreground")); // NOI18N
        listOfRulesButton.setText(resourceMap.getString("listOfRulesButton.text")); // NOI18N
        listOfRulesButton.setFocusable(false);
        listOfRulesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        listOfRulesButton.setName("listOfRulesButton"); // NOI18N
        listOfRulesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        listOfRulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listOfRulesButtonActionPerformed(evt);
            }
        });
        toolsBar.add(listOfRulesButton);

        moveUpButton.setForeground(resourceMap.getColor("moveUpButton.foreground")); // NOI18N
        moveUpButton.setText(resourceMap.getString("moveUpButton.text")); // NOI18N
        moveUpButton.setName("moveUpButton"); // NOI18N
        moveUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpButtonActionPerformed(evt);
            }
        });
        toolsBar.add(moveUpButton);

        moveDownButton.setForeground(resourceMap.getColor("moveDownButton.foreground")); // NOI18N
        moveDownButton.setText(resourceMap.getString("moveDownButton.text")); // NOI18N
        moveDownButton.setName("moveDownButton"); // NOI18N
        moveDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownButtonActionPerformed(evt);
            }
        });
        toolsBar.add(moveDownButton);

        editVariablesButton.setForeground(resourceMap.getColor("editVariablesButton.foreground")); // NOI18N
        editVariablesButton.setText(resourceMap.getString("editVariablesButton.text")); // NOI18N
        editVariablesButton.setFocusable(false);
        editVariablesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editVariablesButton.setName("editVariablesButton"); // NOI18N
        editVariablesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editVariablesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVariablesButtonActionPerformed(evt);
            }
        });
        toolsBar.add(editVariablesButton);

        addExistingStatementButton.setForeground(resourceMap.getColor("addExistingStatementButton.foreground")); // NOI18N
        addExistingStatementButton.setText(resourceMap.getString("addExistingStatementButton.text")); // NOI18N
        addExistingStatementButton.setFocusable(false);
        addExistingStatementButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addExistingStatementButton.setName("addExistingStatementButton"); // NOI18N
        addExistingStatementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addExistingStatementButtonActionPerformed(evt);
            }
        });
        toolsBar.add(addExistingStatementButton);

        propertiesButton.setForeground(resourceMap.getColor("propertiesButton.foreground")); // NOI18N
        propertiesButton.setText(resourceMap.getString("propertiesButton.text")); // NOI18N
        propertiesButton.setFocusable(false);
        propertiesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        propertiesButton.setName("propertiesButton"); // NOI18N
        propertiesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        propertiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesButtonActionPerformed(evt);
            }
        });
        toolsBar.add(propertiesButton);

        syntaxButton.setForeground(resourceMap.getColor("syntaxButton.foreground")); // NOI18N
        syntaxButton.setText(resourceMap.getString("syntaxButton.text")); // NOI18N
        syntaxButton.setName("syntaxButton"); // NOI18N
        syntaxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syntaxButtonActionPerformed(evt);
            }
        });
        toolsBar.add(syntaxButton);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuTab, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(toolsBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
            .addComponent(filesTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuTab, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toolsBar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filesTab, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );

        filesTab.getAccessibleContext().setAccessibleName(resourceMap.getString("Tabul1.AccessibleContext.accessibleName")); // NOI18N
        menuTab.getAccessibleContext().setAccessibleName(resourceMap.getString("Tabul_meniu.AccessibleContext.accessibleName")); // NOI18N

        statePanel.setName("statePanel"); // NOI18N

        statePillar.setName("statePillar"); // NOI18N

        stateMessageLabel.setName("stateMessageLabel"); // NOI18N

        stateAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        stateAnimationLabel.setName("stateAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        colors.setText("<html><font size=\"-1\"><b>Colors of\nvariables:</b>  <span class=\"wff\" style=\"color:blue;font-style:normal\">wff</span>\n<span class=\"set\" style=\"color:red;font-style:normal\">set</span> <span class=\"class\" style=\"color:#f226bf;font-\nstyle:normal\">class</span></font></html>"); // NOI18N
        colors.setName("colors"); // NOI18N

        javax.swing.GroupLayout statePanelLayout = new javax.swing.GroupLayout(statePanel);
        statePanel.setLayout(statePanelLayout);
        statePanelLayout.setHorizontalGroup(
            statePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stateMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statePillar, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statePanelLayout.createSequentialGroup()
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateAnimationLabel)
                        .addContainerGap())))
        );
        statePanelLayout.setVerticalGroup(
            statePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statePanelLayout.createSequentialGroup()
                .addComponent(statePillar, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(statePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stateMessageLabel)
                    .addComponent(stateAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
            .addGroup(statePanelLayout.createSequentialGroup()
                .addComponent(colors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setComponent(mainPanel);
        setStatusBar(statePanel);
    }// </editor-fold>//GEN-END:initComponents

    private void branchCreationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchCreationButtonActionPerformed
        
        if (this.currentDemonstrationEditor!=null)
         {
            if (this.currentDemonstrationEditor.selectedDemonstrationItem!=null)
            {
                this.currentDemonstrationEditor.createBranchAndUpdate();
                

                if (this.currentDemonstrationEditor.demonstrationStrategyType==0)
                {
                //we verify the existence of the solution
                 if(this.currentDemonstrationEditor.haveSolutionBackwardChaining())
                {
                   JOptionPane.showMessageDialog
                    (null,
                     "You have found a proof using Backward Chaining Strategy!",
                     "Congratulations!",
                     JOptionPane.INFORMATION_MESSAGE
                     );
                 }
                }
                else if (this.currentDemonstrationEditor.demonstrationStrategyType==1)
                {
                //we verify the existence of the solution
                 if(this.currentDemonstrationEditor.haveSolutionForwardChaining())
                 {
                   JOptionPane.showMessageDialog
                    (null,
                     "You have found a proof using Forward Chaining Strategy!",
                     "Congratulations!",
                     JOptionPane.INFORMATION_MESSAGE
                     );
                 }
                }



            }
        }

    }//GEN-LAST:event_branchCreationButtonActionPerformed

    private void branchDeletingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchDeletingButtonActionPerformed
         if (this.currentDemonstrationEditor!=null)
         {
        if (this.currentDemonstrationEditor.selectedDemonstrationItem!=null)
        {
            this.currentDemonstrationEditor.deleteBranchAndUpdateGraph();
           
        }
        }

    }//GEN-LAST:event_branchDeletingButtonActionPerformed

    private void listOfRulesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listOfRulesButtonActionPerformed
         
        JFrame mainFrame = Application0.getApplication().getMainFrame();
        this.listOfRulesWindow = new ListOfRulesWindow(mainFrame,true);
        this.listOfRulesWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(listOfRulesWindow);
    }//GEN-LAST:event_listOfRulesButtonActionPerformed

    private void addToListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToListButtonActionPerformed
       
      
          if (
                  (this.nameOfSelectedItem!=null)

             )
          {
          AppliedItem appliedItem=new AppliedItem();
          appliedItem.name=this.nameOfSelectedItem;
          appliedItem.type=this.typeOfSelectedItem;
          //we add the selected item to the list
          this.listOfAppliedItems.add(appliedItem);
          }

    }//GEN-LAST:event_addToListButtonActionPerformed

    private void moveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpButtonActionPerformed
        
      if (this.currentDemonstrationEditor!=null)
         {
            if (this.currentDemonstrationEditor.selectedDemonstrationItem!=null)
            {
            if (this.currentDemonstrationEditor.demonstrationStrategyType==1)
                {
                this.currentDemonstrationEditor.moveUpFCAndUpdate();

                }


            }
        }

        
    }//GEN-LAST:event_moveUpButtonActionPerformed

    private void moveDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownButtonActionPerformed
        

      if (this.currentDemonstrationEditor!=null)
         {
            if (this.currentDemonstrationEditor.selectedDemonstrationItem!=null)
            {
            if (this.currentDemonstrationEditor.demonstrationStrategyType==1)
                {
                this.currentDemonstrationEditor.moveDownFCAndUpdate();

                }


            }
        }

    }//GEN-LAST:event_moveDownButtonActionPerformed

    private void propertiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propertiesButtonActionPerformed


        JFrame mainFrame = Application0.getApplication().getMainFrame();
        PropertiesWindow propertiesWindow = new PropertiesWindow(mainFrame,true);
        propertiesWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(propertiesWindow);



    }//GEN-LAST:event_propertiesButtonActionPerformed

    private void editVariablesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVariablesButtonActionPerformed
       

        JFrame mainFrame = Application0.getApplication().getMainFrame();
        listOfVariablesWindow=new VariablesListWindow(mainFrame,true);
       Application0.getApplication().show(listOfVariablesWindow);

    }//GEN-LAST:event_editVariablesButtonActionPerformed

    private void addExistingStatementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addExistingStatementButtonActionPerformed
               
        JFrame mainFrame = Application0.getApplication().getMainFrame();
        
       Application0.getApplication().show(new StatementsListWindow(mainFrame,true));
    }//GEN-LAST:event_addExistingStatementButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

       JFrame mainFrame = Application0.getApplication().getMainFrame();
        this.searchWindow = new SearchWindow(mainFrame,true);
        this.searchWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(searchWindow);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void formulaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formulaButtonActionPerformed

        JFrame mainFrame = Application0.getApplication().getMainFrame();
        this.formulaEditorWindow = new FormulaEditorWindow(mainFrame,true);
        this.formulaEditorWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(formulaEditorWindow);
    }//GEN-LAST:event_formulaButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        
       String url = "http://www.milpgame.com";

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                try {
                    desktop.browse(new URI(url));
                } catch (IOException ex) {
                    Logger.getLogger(
              MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch ( URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            }
    }//GEN-LAST:event_helpButtonActionPerformed

    private void syntaxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syntaxButtonActionPerformed
         JFrame mainFrame = Application0.getApplication().getMainFrame();
        this.syntaxWindow = new SyntaxWindow(mainFrame,true);
        this.syntaxWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(syntaxWindow);
    }//GEN-LAST:event_syntaxButtonActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
     int numberOfTabs=this.filesTab.getTabCount();
     int optionNumber=0;
     if(numberOfTabs>0){
      optionNumber=JOptionPane.showConfirmDialog
        (null,
        "Opening a new file it will close all other files!",
        "Attention!",
        JOptionPane.YES_NO_OPTION
        );
     }

      if (optionNumber==0)
      {
          Application0 a= (Application0) Application.getInstance();
         if(a!=null)
           {
         if(a.frame0!=null)
           {
             //we reset the variable at a new opening
             a.frame0.haveInclusion=false;
           }
           }
      //we released all the tabs before opening a file
     this.filesTab.removeAll();
     javax.swing.JFileChooser Fisier=new javax.swing.JFileChooser("c:\\");
     Fisier.setFileFilter(new Filter("mm","*.mm"));
     Fisier.showOpenDialog(null);
     java.io.File f=null;
     f=Fisier.getSelectedFile();
      if(f!=null)
     {
     System.out.println(f.getParent());
     System.out.println(f.getAbsolutePath());
     //the file where the theory or test is
     source1=null;
     source1=new Source(f.getAbsolutePath());
     source1.folderPath=f.getParent();

    JFrame cadru_baza = Application0.getApplication().getMainFrame();
    dialog = new ReadingSourceDialog(cadru_baza,false,source1);
    dialog.setLocationRelativeTo(cadru_baza);



     thread=new ReadingThread(source1,this);
     thread2=new UpdatePercentReadingThread(source1,dialog);
     thread.start();
     thread2.start();
     Application0.getApplication().show(dialog);
     }

     }
    }//GEN-LAST:event_openButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    public javax.swing.JButton addExistingStatementButton;
    public javax.swing.JButton addToListButton;
    public javax.swing.JButton branchCreationButton;
    public javax.swing.JButton branchDeletingButton;
    private javax.swing.JLabel colors;
    private javax.swing.JPanel editPanel;
    private javax.swing.JButton editVariablesButton;
    private javax.swing.JPanel filesPanel;
    public javax.swing.JTabbedPane filesTab;
    private javax.swing.JButton formulaButton;
    private javax.swing.JButton helpButton;
    public javax.swing.JButton listOfRulesButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTabbedPane menuTab;
    private javax.swing.JButton moveDownButton;
    private javax.swing.JButton moveUpButton;
    private javax.swing.JButton openButton;
    private javax.swing.JProgressBar progressBar;
    public javax.swing.JButton propertiesButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel stateAnimationLabel;
    private javax.swing.JLabel stateMessageLabel;
    private javax.swing.JPanel statePanel;
    private javax.swing.JButton syntaxButton;
    private javax.swing.JToolBar toolsBar;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables

     //own declarations
       
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    public  ReadingSourceDialog dialog;
   
    
    //public MainWindows this
    public Source source1=null;
    public ReadingThread thread=null;
    public UpdatePercentReadingThread thread2=null;
    public String nameOfSelectedItem="";//selected axiom,theorem or variable
    public int typeOfSelectedItem=0;//the type of selected item
    public String nameOfMemorizedItem="";//memorized axiom,theorem or variable
    public int typeOfMemorizedItem=0;//the type of memorized item
    //the list of item that will applied at a given moment
    //to create demonstration
    public List<AppliedItem>
            listOfAppliedItems=new ArrayList<AppliedItem>();
     //the items found after the search
     public List<AppliedItem>
            listOfFoundItems=new ArrayList<AppliedItem>();
     public boolean haveInclusion=false;
     public String errorsString="";
    //new classes declarations
    
    //current demonstration editor
    public DemonstrationEditor currentDemonstrationEditor=null;
    //window used for view of the rules
    public ListOfRulesWindow listOfRulesWindow=null;
    public VariableEditorWindow variableEditorWindow=null;
    public SearchWindow searchWindow=null;
    public FormulaEditorWindow formulaEditorWindow=null;
    public VariablesListWindow listOfVariablesWindow=null;
    public SyntaxWindow syntaxWindow=null;

   class Filter extends javax.swing.filechooser.FileFilter
   {
       private String description=null;
       private String extension=null;
       public Filter(String extension0,String description0)
       {
        this.description=description0;
        this.extension="."+extension0.toLowerCase();
       }
       public String getDescription()
       {
           return description;
       }
       public boolean accept(File f)
       {
           if (f==null) return false;
           if (f.isDirectory()) return true;
           return f.getName().toLowerCase().endsWith(extension);
       }
   }
}