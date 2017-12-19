//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;
import javax.swing.table.AbstractTableModel;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListSelectionModel;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import org.jdesktop.application.Application;
import spv.gen.DemonstrationConstants;
import spv.gen.Hypothesis;
import spv.gen.Axiom;
import spv.gen.Theorem;
import spv.gen.ConstantAndVariable;
import javax.swing.JFrame;
import javax.swing.JTable;
import spv.FilePanel;

public class SearchWindow extends javax.swing.JDialog {

    
    public SearchWindow(
            java.awt.Frame parent,
            boolean modal
            
            ) {
        super(parent, modal);
        initComponents();
        buttonGroup1.add(this.nameTickButton);
        buttonGroup1.add(this.contentTickButton);
        
       
         Application0 a=(Application0)Application.getInstance();


         if (a!=null)
         {
          if (a.frame0!=null)
         {
           // here we were acting on the go button
                      
           this.goButton.setEnabled(!(a.frame0.haveInclusion));
           
          //END go
          if (a.frame0.currentDemonstrationEditor!=null)
          {
                         
            //we modify content variable label
            String s="";
           int max=searchContent.size();

           for (int i=0;i<max;i++)
           {
            String  s2="",s1=searchContent.get(i).constantOrVariableText;
            if(a!=null)
            {
             if (a.frame0!=null)
             {
               if (a.frame0.source1!=null)
               {
               if (a.frame0.source1.htlmldefString1AsString2!=null)
               {
                 s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
               }
               }
             }
            }
            if (s2==null) s2=s1;
            s=s+s2;
           }

          s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
         s="<HTML>"
         +"<base href='file:"+a.path+"/symbols/  '/>"
         +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
         +"<FONT COLOR='#ffffff' SIZE=3 >"
         +s
         +"</FONT>"
         +"</P>"
         +"</HTML>";
          searchContentLabel.setText(s);
          }
          }
        }

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkbox1 = new java.awt.Checkbox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        searchContentNonfunctionalLabel = new javax.swing.JLabel();
        editTab = new javax.swing.JTabbedPane();
        directEditingPanel = new javax.swing.JPanel();
        deleteContentButton = new javax.swing.JButton();
        tableConstantsNonfunctionalLabel = new javax.swing.JLabel();
        tableVariablesNonfunctionalLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listOfVariables = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        listOfConstants = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        listContent = new javax.swing.JList();
        tableContentNonfunctionalLabel = new javax.swing.JLabel();
        insertConstantButton = new javax.swing.JButton();
        moveDownContentButton = new javax.swing.JButton();
        moveUpContentButton = new javax.swing.JButton();
        insertVariableButton = new javax.swing.JButton();
        correctnessLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listOfFound = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        propertiesButton = new javax.swing.JButton();
        nameText = new javax.swing.JTextField();
        searchNameNonfunctionalLabel = new javax.swing.JLabel();
        contentTickButton = new javax.swing.JRadioButton();
        nameTickButton = new javax.swing.JRadioButton();
        derulare_continut = new javax.swing.JScrollPane();
        searchContentLabel = new javax.swing.JLabel();
        syntaxButton = new javax.swing.JButton();
        goButton = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(SearchWindow.class);
        checkbox1.setLabel(resourceMap.getString("checkbox1.label")); // NOI18N
        checkbox1.setName("checkbox1"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jTextPane1.setName("jTextPane1"); // NOI18N
        jScrollPane5.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search"); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        searchContentNonfunctionalLabel.setForeground(new java.awt.Color(0, 51, 255));
        searchContentNonfunctionalLabel.setText("Search by content:"); // NOI18N
        searchContentNonfunctionalLabel.setName("searchContentNonfunctionalLabel"); // NOI18N

        editTab.setForeground(resourceMap.getColor("editTab.foreground")); // NOI18N
        editTab.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        editTab.setName("editTab"); // NOI18N

        directEditingPanel.setForeground(new java.awt.Color(0, 51, 255));
        directEditingPanel.setAutoscrolls(true);
        directEditingPanel.setName("directEditingPanel"); // NOI18N

        deleteContentButton.setForeground(new java.awt.Color(204, 0, 51));
        deleteContentButton.setText("Delete"); // NOI18N
        deleteContentButton.setName("deleteContentButton"); // NOI18N
        deleteContentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteContentButtonActionPerformed(evt);
            }
        });

        tableConstantsNonfunctionalLabel.setForeground(new java.awt.Color(0, 51, 255));
        tableConstantsNonfunctionalLabel.setText("List of constants:"); // NOI18N
        tableConstantsNonfunctionalLabel.setName("et_nef_lista_constante"); // NOI18N

        tableVariablesNonfunctionalLabel.setForeground(new java.awt.Color(0, 51, 255));
        tableVariablesNonfunctionalLabel.setText("List of variables:"); // NOI18N
        tableVariablesNonfunctionalLabel.setName("et_nef_lista_variabile"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        listOfVariables.setForeground(resourceMap.getColor("listOfVariables.foreground")); // NOI18N
        listOfVariables.setModel(new ModelListVariables());
        listOfVariables.setName("listOfVariables"); // NOI18N
        listOfVariables.setSelectionModel(new SelectionModelListVariables());
        jScrollPane2.setViewportView(listOfVariables);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        listOfConstants.setForeground(resourceMap.getColor("listOfConstants.foreground")); // NOI18N
        listOfConstants.setModel(new ListModelConstants());
        listOfConstants.setName("listOfConstants"); // NOI18N
        listOfConstants.setSelectionModel(new SelectionModelListConstants());
        listOfConstants.setVerifyInputWhenFocusTarget(false);
        jScrollPane3.setViewportView(listOfConstants);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        listContent.setForeground(resourceMap.getColor("listContent.foreground")); // NOI18N
        listContent.setModel( new ModelListContent());
        listContent.setName("listContent"); // NOI18N
        listContent.setSelectionModel(new SelectionModelListContent());
        jScrollPane4.setViewportView(listContent);

        tableContentNonfunctionalLabel.setForeground(resourceMap.getColor("tableContentNonfunctionalLabel.foreground")); // NOI18N
        tableContentNonfunctionalLabel.setText("Content:"); // NOI18N
        tableContentNonfunctionalLabel.setName("tableContentNonfunctionalLabel"); // NOI18N

        insertConstantButton.setForeground(resourceMap.getColor("insertConstantButton.foreground")); // NOI18N
        insertConstantButton.setText("Insert -->"); // NOI18N
        insertConstantButton.setName("insertConstantButton"); // NOI18N
        insertConstantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertConstantButtonActionPerformed(evt);
            }
        });

        moveDownContentButton.setForeground(resourceMap.getColor("moveDownContentButton.foreground")); // NOI18N
        moveDownContentButton.setText("Move Down"); // NOI18N
        moveDownContentButton.setName("moveDownContentButton"); // NOI18N
        moveDownContentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownContentButtonActionPerformed(evt);
            }
        });

        moveUpContentButton.setForeground(resourceMap.getColor("moveUpContentButton.foreground")); // NOI18N
        moveUpContentButton.setText("Move Up"); // NOI18N
        moveUpContentButton.setName("moveUpContentButton"); // NOI18N
        moveUpContentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpContentButtonActionPerformed(evt);
            }
        });

        insertVariableButton.setForeground(new java.awt.Color(0, 51, 255));
        insertVariableButton.setText("Insert -->"); // NOI18N
        insertVariableButton.setName("insertVariableButton"); // NOI18N
        insertVariableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertVariableButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout directEditingPanelLayout = new javax.swing.GroupLayout(directEditingPanel);
        directEditingPanel.setLayout(directEditingPanelLayout);
        directEditingPanelLayout.setHorizontalGroup(
            directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directEditingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableConstantsNonfunctionalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directEditingPanelLayout.createSequentialGroup()
                        .addComponent(insertConstantButton)
                        .addGap(43, 43, 43)))
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(tableVariablesNonfunctionalLabel))
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(insertVariableButton)))
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(directEditingPanelLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableContentNonfunctionalLabel)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directEditingPanelLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(moveDownContentButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(deleteContentButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(moveUpContentButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                        .addGap(103, 103, 103))))
        );
        directEditingPanelLayout.setVerticalGroup(
            directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directEditingPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tableContentNonfunctionalLabel)
                        .addComponent(tableConstantsNonfunctionalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tableVariablesNonfunctionalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, directEditingPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveUpContentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveDownContentButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(directEditingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(insertVariableButton)
                        .addComponent(insertConstantButton))
                    .addComponent(deleteContentButton))
                .addGap(21, 21, 21))
        );

        editTab.addTab("Edit content directly", directEditingPanel);

        correctnessLabel.setForeground(resourceMap.getColor("correctnessLabel.foreground")); // NOI18N
        correctnessLabel.setText(resourceMap.getString("correctnessLabel.text")); // NOI18N
        correctnessLabel.setName("correctnessLabel"); // NOI18N

        searchButton.setForeground(resourceMap.getColor("searchButton.foreground")); // NOI18N
        searchButton.setText("Search"); // NOI18N
        searchButton.setName("searchButton"); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        listOfFound.setForeground(resourceMap.getColor("listOfFound.foreground")); // NOI18N
        listOfFound.setModel(new ModelListFound());
        listOfFound.setName("listOfFound"); // NOI18N
        listOfFound.setSelectionModel(new SelectionModelListFound());
        jScrollPane1.setViewportView(listOfFound);

        addButton.setForeground(resourceMap.getColor("addButton.foreground")); // NOI18N
        addButton.setText(resourceMap.getString("addButton.text")); // NOI18N
        addButton.setName("addButton"); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        propertiesButton.setForeground(resourceMap.getColor("propertiesButton.foreground")); // NOI18N
        propertiesButton.setText(resourceMap.getString("propertiesButton.text")); // NOI18N
        propertiesButton.setName("propertiesButton"); // NOI18N
        propertiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesButtonActionPerformed(evt);
            }
        });

        nameText.setForeground(resourceMap.getColor("nameText.foreground")); // NOI18N
        nameText.setName("nameText"); // NOI18N

        searchNameNonfunctionalLabel.setName("searchNameNonfunctionalLabel"); // NOI18N

        contentTickButton.setForeground(resourceMap.getColor("contentTickButton.foreground")); // NOI18N
        contentTickButton.setSelected(true);
        contentTickButton.setText(resourceMap.getString("contentTickButton.text")); // NOI18N
        contentTickButton.setName("contentTickButton"); // NOI18N
        contentTickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentTickButtonActionPerformed(evt);
            }
        });

        nameTickButton.setForeground(resourceMap.getColor("nameTickButton.foreground")); // NOI18N
        nameTickButton.setText(resourceMap.getString("nameTickButton.text")); // NOI18N
        nameTickButton.setName("nameTickButton"); // NOI18N
        nameTickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTickButtonActionPerformed(evt);
            }
        });

        derulare_continut.setName("derulare_continut"); // NOI18N

        searchContentLabel.setForeground(resourceMap.getColor("searchContentLabel.foreground")); // NOI18N
        searchContentLabel.setText("___________________________________________________________________"); // NOI18N
        searchContentLabel.setName("searchContentLabel"); // NOI18N
        derulare_continut.setViewportView(searchContentLabel);

        syntaxButton.setForeground(resourceMap.getColor("syntaxButton.foreground")); // NOI18N
        syntaxButton.setText(resourceMap.getString("syntaxButton.text")); // NOI18N
        syntaxButton.setName("syntaxButton"); // NOI18N
        syntaxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syntaxButtonActionPerformed(evt);
            }
        });

        goButton.setForeground(resourceMap.getColor("goButton.foreground")); // NOI18N
        goButton.setText(resourceMap.getString("goButton.text")); // NOI18N
        goButton.setName("goButton"); // NOI18N
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(correctnessLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchNameNonfunctionalLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(contentTickButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 424, Short.MAX_VALUE)
                                        .addComponent(syntaxButton)
                                        .addGap(18, 18, 18))
                                    .addComponent(nameTickButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchContentNonfunctionalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(derulare_continut, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
                            .addComponent(editTab, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(propertiesButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(goButton)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(304, 304, 304))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchContentNonfunctionalLabel))
                    .addComponent(derulare_continut, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correctnessLabel)
                    .addComponent(syntaxButton)
                    .addComponent(contentTickButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchNameNonfunctionalLabel)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTickButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editTab, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(propertiesButton)
                    .addComponent(goButton)))
        );

        editTab.getAccessibleContext().setAccessibleName("Insert tokens"); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertConstantButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertConstantButtonActionPerformed
        
       if((selectedRowConstants>=0)&(selectedRowConstants<listConstants.size()))
       {
       ConstantAndVariable c=new ConstantAndVariable();//we add a new constant to the content
       c.constantOrVariable=1;//is constant
       c.constantOrVariableText=listConstants.get(selectedRowConstants);
       //we add a new constant to the content of the edited variable
       searchContent.add(c);
       
       listContent.updateUI();

       //we change the label of the edited variable
       String s="";
       int max=searchContent.size();
      Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max;i++)
       {
        String  s2="",s1=searchContent.get(i).constantOrVariableText;
        if(a!=null)
        {
         if (a.frame0!=null)
         {
           if (a.frame0.source1!=null)
           {
           if (a.frame0.source1.htlmldefString1AsString2!=null)
           {
             s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
           }
           }
         }
        }
        if (s2==null) s2=s1;
        s=s+s2;
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      searchContentLabel.setText(s);
      //we verify the rightness of the content of the variable
      SyntacticItem syntacticTree=new SyntacticItem();
      boolean correct=a.frame0.source1.verifySyntacticType
                                            (
                                            searchContent,
                                            "wff",
                                            0,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
      }
      this.listOfConstants.clearSelection();
      
      }
    }//GEN-LAST:event_insertConstantButtonActionPerformed

    private void insertVariableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertVariableButtonActionPerformed
      
      if((selectedRowVariables>=0)&(selectedRowVariables<listVariables.size()))
       {
      ConstantAndVariable c=new ConstantAndVariable();
      c.constantOrVariable=2;//is variable
      c.constantOrVariableText=listVariables.get(selectedRowVariables);
      //we add a new variable to the content of the edited variable

       //we establish the class of the variable
       Application0 a=(Application0)Application.getInstance();

          if (a.frame0!=null)
          {
          if (a.frame0.source1!=null)
             {
           if (a.frame0.source1.variableAndType!=null)
           {

             c.variableClass=a.frame0.source1.variableAndType.get(c.constantOrVariableText);
           }
           }
           }
      searchContent.add(c);

       listContent.updateUI();
         //we modify the content variable label
        String s="";
       int max=searchContent.size();
      
       for (int i=0;i<max;i++)
       {
        String  s2="",s1=searchContent.get(i).constantOrVariableText;
        if(a!=null)
        {
         if (a.frame0!=null)
         {
           if (a.frame0.source1!=null)
           {
           if (a.frame0.source1.htlmldefString1AsString2!=null)
           {
             s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
           }
           }
         }
        }
        if (s2==null) s2=s1;
        s=s+s2;
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      searchContentLabel.setText(s);
      SyntacticItem syntacticTree=new SyntacticItem();
      boolean correct=a.frame0.source1.verifySyntacticType
                                            (
                                            searchContent,
                                            "wff",
                                            0,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
      }
      this.listOfVariables.clearSelection();
     }
    }//GEN-LAST:event_insertVariableButtonActionPerformed

    private void deleteContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteContentButtonActionPerformed
       
        if((selectedRowContent>=0)&
                              (selectedRowContent<searchContent.size()))
       {
        searchContent.remove(selectedRowContent);
        listContent.updateUI();
        //we modify the content variable label
        String s="";
       int max=searchContent.size();
        Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max;i++)
       {
        String  s2="",s1=searchContent.get(i).constantOrVariableText;
        if(a!=null)
        {
         if (a.frame0!=null)
         {
           if (a.frame0.source1!=null)
           {
           if (a.frame0.source1.htlmldefString1AsString2!=null)
           {
             s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
           }
           }
         }
        }
        if (s2==null) s2=s1;
        s=s+s2;
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      searchContentLabel.setText(s);
      SyntacticItem syntacticTree=new SyntacticItem();
      boolean correct=false;
      if(!searchContent.isEmpty())
      {
        correct=a.frame0.source1.verifySyntacticType
                                            (
                                            searchContent,
                                            "wff",
                                            0,
                                            true,
                                            syntacticTree
                                            );
       }

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
      }
     listContent.clearSelection();
     }
    }//GEN-LAST:event_deleteContentButtonActionPerformed

    private void moveUpContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpContentButtonActionPerformed
       
        
      if (selectedRowContent-1>=0)
      {
        byte ciType= searchContent.
                                    get(selectedRowContent).constantOrVariable;
        String ciClass= searchContent.
                                   get(selectedRowContent).variableClass;
        String ciName= searchContent.
                                get(selectedRowContent).constantOrVariableText;

        ConstantAndVariable ci=new ConstantAndVariable();

        ci.constantOrVariable=ciType;
        ci.variableClass=ciClass;
        ci.constantOrVariableText=ciName;

        byte cim1Type= searchContent.
                                   get(selectedRowContent-1).constantOrVariable;
        String cim1Class= searchContent.
                                 get(selectedRowContent-1).variableClass;
        String cim1Name= searchContent.
                               get(selectedRowContent-1).constantOrVariableText;

        ConstantAndVariable cim1=new ConstantAndVariable();

        cim1.constantOrVariable=cim1Type;
        cim1.variableClass=cim1Class;
        cim1.constantOrVariableText=cim1Name;

        searchContent.set(selectedRowContent, cim1);
        searchContent.set(selectedRowContent-1, ci);
        listContent.updateUI();
        //we modify content variable label
        String s="";
       int max1=searchContent.size();
        Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max1;i++)
       {
        String  s2="",s1=searchContent.get(i).constantOrVariableText;
        if(a!=null)
        {
         if (a.frame0!=null)
         {
           if (a.frame0.source1!=null)
           {
           if (a.frame0.source1.htlmldefString1AsString2!=null)
           {
             s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
           }
           }
         }
        }
        if (s2==null) s2=s1;
        s=s+s2;
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      searchContentLabel.setText(s);
      SyntacticItem syntacticTree=new SyntacticItem();
      boolean correct=a.frame0.source1.verifySyntacticType
                                            (
                                            searchContent,
                                            "wff",
                                            0,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
      }
      listContent.clearSelection();
      }


    }//GEN-LAST:event_moveUpContentButtonActionPerformed

    private void moveDownContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownContentButtonActionPerformed
        
        int max=searchContent.size();
      if (selectedRowContent+1<max)
      {
        byte ciType= searchContent.
                                    get(selectedRowContent).constantOrVariable;
        String c1Class= searchContent.
                                   get(selectedRowContent).variableClass;
        String ciName= searchContent.
                                get(selectedRowContent).constantOrVariableText;

        ConstantAndVariable ci=new ConstantAndVariable();

        ci.constantOrVariable=ciType;
        ci.variableClass=c1Class;
        ci.constantOrVariableText=ciName;

        byte cip1Type= searchContent.
                                   get(selectedRowContent+1).constantOrVariable;
        String cip1Class= searchContent.
                                  get(selectedRowContent+1).variableClass;
        String cip1Name= searchContent.
                               get(selectedRowContent+1).constantOrVariableText;

        ConstantAndVariable cip1=new ConstantAndVariable();

        cip1.constantOrVariable=cip1Type;
        cip1.variableClass=cip1Class;
        cip1.constantOrVariableText=cip1Name;

        searchContent.set(selectedRowContent, cip1);
        searchContent.set(selectedRowContent+1, ci);
        listContent.updateUI();
        //we modify content variable label
        String s="";
       int max1=searchContent.size();
       Application0 a=(Application0)Application.getInstance();
       for (int i=0;i<max1;i++)
       {
        String  s2="",s1=searchContent.get(i).constantOrVariableText;
        if(a!=null)
        {
         if (a.frame0!=null)
         {
           if (a.frame0.source1!=null)
           {
           if (a.frame0.source1.htlmldefString1AsString2!=null)
           {
             s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
           }
           }
         }
        }
        if (s2==null) s2=s1;
        s=s+s2;
       }

      s =s.replaceAll("ALIGN=TOP","ALIGN=CENTER");
     s="<HTML>"
     +"<base href='file:"+a.path+"/symbols/  '/>"
     +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
     +"<FONT COLOR='#ffffff' SIZE=3 >"
     +s
     +"</FONT>"
     +"</P>"
     +"</HTML>";
      searchContentLabel.setText(s);
      SyntacticItem syntacticTree=new SyntacticItem();
      boolean correct=a.frame0.source1.verifySyntacticType
                                            (
                                            searchContent,
                                            "wff",
                                            0,
                                            true,
                                            syntacticTree
                                            );

      if (correct)
      {
         this.correctnessLabel.setText("Entered formula is:correct!");
      }
      else
      {
         this.correctnessLabel.setText("Entered formula is:incorrect!");
      }
      listContent.clearSelection();
      }

    }//GEN-LAST:event_moveDownContentButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
      
      //we modify the content of the help variable
        Application0 a=(Application0)Application.getInstance();
        int max2=this.searchContent.size();

      
     if(a!=null)
     {
      if(a.frame0!=null)
      {
       if(a.frame0.source1!=null)
       {
          a.frame0.listOfFoundItems.clear();//we delete the list
        
        if (searchOption==1)
          {
        if(max2>0)
          {
        if(a.frame0.source1.axioms!=null)
        {
          Iterator<String> it=a.frame0.source1.axioms.keySet().iterator();
           while(it.hasNext())
           {
            String name=it.next();
            Axiom ax=(Axiom)a.frame0.source1.axioms.get(name);
            int max=ax.items.size();
            
            //if the axiom is bigger or equal cu searched string
            if(max>=max2)
            {
            boolean found=true;
            for(int i=0;i<max-max2+1;i++)
            {
                   
             found=true;
             for(int j=0;j<max2;j++)
             {
              String v_j=this.searchContent.get(j).constantOrVariableText;
              String v_ipj=ax.items.get(i+j).constantOrVariableText;
              if(!v_j.equals(v_ipj)) { found=false;}

             }
             if (found) {break;}
             }
            if (found)
            {
              AppliedItem item= new AppliedItem();
              item.name=ax.name;
              if(ax.type==1) {item.type=DemonstrationConstants.SIMPLE_AXIOM;}
                  else if(ax.type==2) {item.type=DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM;}
              if(a!=null)
              {
               if(a.frame0!=null)
               {
                if(a.frame0.listOfFoundItems!=null)
                {
                  a.frame0.listOfFoundItems.add(item);
                }
               }
              }
            }

             }
           }
        }

        if(a.frame0.source1.theorems!=null)
        {
          Iterator<String> it=a.frame0.source1.theorems.keySet().iterator();
           while(it.hasNext())
           {
            String name=it.next();
            Theorem tx=(Theorem)a.frame0.source1.theorems.get(name);
            int max=tx.items.size();


            //if the theorem is bigger or equal with searched string
            if(max>=max2)
            {
            boolean found=true;
            for(int i=0;i<max-max2+1;i++)
            {
             found=true;
             for(int j=0;j<max2;j++)
             {
              String v_j=this.searchContent.get(j).constantOrVariableText;
              String v_ipj=tx.items.get(i+j).constantOrVariableText;
              if(!v_j.equals(v_ipj)) { found=false;}

             }
             if (found) {break;}
             }
            if (found)
            {
              AppliedItem item= new AppliedItem();
              item.name=tx.name;
              if(tx.type==1) {item.type=DemonstrationConstants.SIMPLE_THEOREM;}
                  else if(tx.type==2) {item.type=DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM;}
              if(a!=null)
              {
               if(a.frame0!=null)
               {
                if(a.frame0.listOfFoundItems!=null)
                {
                  a.frame0.listOfFoundItems.add(item);
                }
               }
              }
            }

             }
           }
        }
        }
        }
       else if(searchOption==2)
       {
         
        if(a.frame0.source1.axioms!=null)
        {
          Iterator<String> it=a.frame0.source1.axioms.keySet().iterator();
           while(it.hasNext())
           {
            String name=it.next();
            Axiom ax=(Axiom)a.frame0.source1.axioms.get(name);

            if (name.indexOf(this.nameText.getText())>-1)
            {
              AppliedItem item= new AppliedItem();
              item.name=ax.name;
              if(ax.type==1) {item.type=DemonstrationConstants.SIMPLE_AXIOM;}
                  else if(ax.type==2) {item.type=DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM;}
              if(a!=null)
              {
               if(a.frame0!=null)
               {
                if(a.frame0.listOfFoundItems!=null)
                {
                  a.frame0.listOfFoundItems.add(item);
                }
               }
              }
            }


           }
        }

        if(a.frame0.source1.theorems!=null)
        {
          Iterator<String> it=a.frame0.source1.theorems.keySet().iterator();
           while(it.hasNext())
           {
            String name=it.next();
            Theorem tx=(Theorem)a.frame0.source1.theorems.get(name);
            
            
            if (name.indexOf(this.nameText.getText())>-1)
            {
              AppliedItem item= new AppliedItem();
              item.name=tx.name;
              if(tx.type==1) {item.type=DemonstrationConstants.SIMPLE_THEOREM;}
                  else if(tx.type==2) {item.type=DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM;}
              if(a!=null)
              {
               if(a.frame0!=null)
               {
                if(a.frame0.listOfFoundItems!=null)
                {
                  a.frame0.listOfFoundItems.add(item);
                }
               }
              }
            }


           }
        }
        }


       }
      }
     }
     this.listOfFound.setModel(new ModelListFound());
     listOfFound.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     listOfFound.getColumnModel().getColumn(0).setPreferredWidth(45);
     listOfFound.getColumnModel().getColumn(1).setPreferredWidth(100);
     listOfFound.getColumnModel().getColumn(2).setPreferredWidth(100);
     listOfFound.getColumnModel().getColumn(3).setPreferredWidth(500);
     this.listOfFound.revalidate();
     this.listOfFound.repaint();
    
    }//GEN-LAST:event_searchButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
       Application0 a=(Application0)Application.getInstance();
      if(a!=null)
      {
      if(a.frame0!=null)
      {
      if(a.frame0.listOfFoundItems!=null)
      {

      if(selectedRowListFound>-1)
      {

          AppliedItem appliedItem=new AppliedItem();
          appliedItem.name=a.frame0.listOfFoundItems.get(selectedRowListFound).name;
          appliedItem.type=a.frame0.listOfFoundItems.get(selectedRowListFound).type;
          //we add the selected item to the list of rules
          if(a.frame0.listOfAppliedItems!=null)
          {
          a.frame0.listOfAppliedItems.add(appliedItem);
          }
      }

      }
      }
      }

    }//GEN-LAST:event_addButtonActionPerformed

    private void propertiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propertiesButtonActionPerformed

       Application0 a=(Application0)Application.getInstance();
       if(selectedRowListFound>-1)
       {
       if(a!=null)
       {
       if(a.frame0!=null)
       {
       if(a.frame0.listOfFoundItems!=null)
       {
        a.frame0.nameOfSelectedItem=
                        a.frame0.listOfFoundItems.get(selectedRowListFound).name;

        JFrame mainFrame = Application0.getApplication().getMainFrame();
        PropertiesWindow propertiesWindow = new PropertiesWindow(mainFrame,true);
        propertiesWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(propertiesWindow);
           }
           }
           }
           }
    }//GEN-LAST:event_propertiesButtonActionPerformed

    private void contentTickButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentTickButtonActionPerformed

        this.searchOption=1;
    }//GEN-LAST:event_contentTickButtonActionPerformed

    private void nameTickButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTickButtonActionPerformed

       this.searchOption=2;
    }//GEN-LAST:event_nameTickButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
        Application0 a=(Application0)Application.getInstance();
      if(a!=null)
      {
      if(a.frame0!=null)
      {
      if(a.frame0.listOfFoundItems!=null)
      {
      a.frame0.listOfFoundItems.clear();
      }
      }
      }
    }//GEN-LAST:event_formWindowClosed

    private void syntaxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syntaxButtonActionPerformed
        JFrame mainFrame = Application0.getApplication().getMainFrame();
        this.syntaxWindow = new SyntaxWindow(mainFrame,true);
        this.syntaxWindow.setLocationRelativeTo(mainFrame);

        Application0.getApplication().show(syntaxWindow);
    }//GEN-LAST:event_syntaxButtonActionPerformed

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        
        Application0 a=(Application0)Application.getInstance();
       if(selectedRowListFound>-1)
       {
       if(a!=null)
       {
       if(a.frame0!=null)
       {
       if(a.frame0.listOfFoundItems!=null)
       {
        if(a.frame0.source1!=null)
        {
        String name= a.frame0.listOfFoundItems.get(selectedRowListFound).name;

        if(a.frame0.source1.axioms!=null)
        {
         if (a.frame0.source1.axioms.containsKey(name))
           {
              Axiom ax=(Axiom)a.frame0.source1.axioms.get(name);
              ParsingItem item1=ax.sourceLabel;
              spv.FilePanel
                      p=(spv.FilePanel)a.frame0.filesTab.getComponentAt(0);
              if(p!=null)
              {
              if(item1!=null)
              {
                do
                 {
                     if((item1.fatherItem)==a.frame0.source1.dataRoot)
                     {/*we do nothing*/}
                      else
                      {
                        item1=item1.fatherItem;
                      }
                   if((item1.fatherItem)==(a.frame0.source1.dataRoot)) break;
                 }
                 while(true);
                
              p.updateContentPanel(p.loadContent(item1));
              this.dispose();
              }
              }
           }
         }

        if(a.frame0.source1.theorems!=null)
        {
        if (a.frame0.source1.theorems.containsKey(name))
           {
              Theorem tx=(Theorem)a.frame0.source1.theorems.get(name);
              ParsingItem item1=tx.sourceLabel;
              spv.FilePanel
                      p=(spv.FilePanel)a.frame0.filesTab.getComponentAt(0);
              if(p!=null)
              {
              if(item1!=null)
              {
                 do
                 {
                     if((item1.fatherItem)==a.frame0.source1.dataRoot)
                     {/*we do nothing*/}
                      else
                      {
                        item1=item1.fatherItem;
                      }
                   if((item1.fatherItem)==(a.frame0.source1.dataRoot)) break;
                 } 
                 while(true);

              p.updateContentPanel(p.loadContent(item1));
               this.dispose();
              }
              }
           }
           }

           }
           }
           }
           }
           }
    }//GEN-LAST:event_goButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SearchWindow dialog = new SearchWindow(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JRadioButton contentTickButton;
    private javax.swing.JLabel correctnessLabel;
    private javax.swing.JButton deleteContentButton;
    private javax.swing.JScrollPane derulare_continut;
    private javax.swing.JPanel directEditingPanel;
    private javax.swing.JTabbedPane editTab;
    public javax.swing.JButton goButton;
    private javax.swing.JButton insertConstantButton;
    private javax.swing.JButton insertVariableButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JList listContent;
    private javax.swing.JList listOfConstants;
    public javax.swing.JTable listOfFound;
    private javax.swing.JList listOfVariables;
    private javax.swing.JButton moveDownContentButton;
    private javax.swing.JButton moveUpContentButton;
    public javax.swing.JTextField nameText;
    private javax.swing.JRadioButton nameTickButton;
    private javax.swing.JButton propertiesButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchContentLabel;
    private javax.swing.JLabel searchContentNonfunctionalLabel;
    private javax.swing.JLabel searchNameNonfunctionalLabel;
    private javax.swing.JButton syntaxButton;
    private javax.swing.JLabel tableConstantsNonfunctionalLabel;
    private javax.swing.JLabel tableContentNonfunctionalLabel;
    private javax.swing.JLabel tableVariablesNonfunctionalLabel;
    // End of variables declaration//GEN-END:variables
    
    //here are memorized the name and the content of the variable
    
    public List<ConstantAndVariable> searchContent=new  ArrayList<ConstantAndVariable>();
    
    //here is a list of strings that represent the constants
    public List<String> listConstants=new ArrayList<String>();
    //here is a list of strings that represent the variables
    public List<String> listVariables=new ArrayList<String>();
    public int  selectedRowConstants=-1;
    public int  selectedRowVariables=-1;
    public int  selectedRowContent=-1;
    public SyntaxWindow syntaxWindow=null;

   
      public class ListModelConstants extends AbstractListModel
  {
          
      public ListModelConstants()
      {
          //here we reset the table
          Application0 a=(Application0)Application.getInstance();
          listConstants.clear();
          
          if (a.frame0!=null)
          {
          if (a.frame0.source1!=null)
             {
           if (a.frame0.source1.constants!=null)
             {
             
                Iterator<String> it=a.frame0.source1.constants.iterator();
               while(it.hasNext())
               {
                String s1=it.next();
                listConstants.add(s1);
                 
               
                }
              }
             }
            }
        }
       
      public String getElementAt(int line)
      {
          Application0 a=(Application0)Application.getInstance();

          String value="";
           if ((line>=0)&(line<listConstants.size()))
           {
               String s2="",s1=listConstants.get(line);
                s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                if (s2==null) s2=s1;
                s2="("+line+")"+s2;
                s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                s2="<HTML>"
                 +"<base href='file:"+a.path+"/symbols/  '/>"
         +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
                 +"<FONT COLOR='#ffffff' SIZE=3 >"
                 +s2
                 +"</FONT>"
                 +"</P>"
                 +"</HTML>";

             value=s2;
           }
          return value;
      }

      public int  getSize()
      {
          return listConstants.size();
      }
    }
   public class SelectionListConstantsListener
           implements ListSelectionListener
    {

        public void valueChanged(ListSelectionEvent e)
        {
         pressedRowListConstants(e);
        }

    }

   void pressedRowListConstants(ListSelectionEvent e)
   {
      
       ListSelectionModel lsm = (ListSelectionModel)e.getSource();
       if (!lsm.isSelectionEmpty())
       {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++)
            {
                if (lsm.isSelectedIndex(i))
                {
                 selectedRowConstants=i;
                 
                }
            }
       }
  
      
   }

 
  //we create selection model
  public class  SelectionModelListConstants extends DefaultListSelectionModel
  {
      public SelectionModelListConstants()
      {
         super();
         this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(
                                    new SelectionListConstantsListener()
                                  );

      }
  }

   
       public class ModelListVariables extends AbstractListModel
  {
     

      public ModelListVariables()
      {
          //here we reset the table
          
          Application0 a=(Application0)Application.getInstance();
           listVariables.clear();
          if (a.frame0!=null)
          {
          if (a.frame0.source1!=null)
             {
           if (a.frame0.source1.variables!=null)
           {
                Iterator<String> it=a.frame0.source1.variables.iterator();

               while(it.hasNext())
               {
                String s1=it.next();
                listVariables.add(s1);
                                 
                }
              }
             }
            }
        }

      public String getElementAt(int line)
      {
          String value="";
          Application0 a=(Application0)Application.getInstance();
           if ((line>=0)&(line<listVariables.size()))
           { 
                String s2="",s1=listVariables.get(line);
               s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                if (s2==null) s2=s1;
                String s3=a.frame0.source1.variableAndType.get(s1);
                s2="("+line+")"+s2+"["+s3+"]";
                s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                s2="<HTML>"
                 +"<base href='file:"+a.path+"/symbols/  '/>"
         +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
                 +"<FONT COLOR='#ffffff' SIZE=3 >"
                 +s2
                 +"</FONT>"
                 +"</P>"
                 +"</HTML>";

             value=s2;
           }
          return value;
      }



      public int  getSize()
      {
          return listVariables.size();
      }


  }
public class ListVariablesSelectionListener
           implements ListSelectionListener
    {

        public void valueChanged(ListSelectionEvent e)
        {
         pressedRowListVariables(e);
        }

    }

   void pressedRowListVariables(ListSelectionEvent e)
   {
      ListSelectionModel lsm = (ListSelectionModel)e.getSource();
       if (!lsm.isSelectionEmpty())
       {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++)
            {
                if (lsm.isSelectedIndex(i))
                {
                 selectedRowVariables=i;
                
                }
            }
       }
   }


  //we create the selection model
  public class  SelectionModelListVariables extends DefaultListSelectionModel
  {
      public SelectionModelListVariables()
      {
         super();
         this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(
                                    new ListVariablesSelectionListener()
                                  );

      }
  }


 public class ModelListContent extends AbstractListModel
  {
     

      public ModelListContent()
      {

      
      }

      public String getElementAt(int line)
      {
          Application0 a=(Application0)Application.getInstance();
          String valoare="";
           if ((line>=0)&(line<searchContent.size()))
           {   
               String s1=searchContent.get(line).constantOrVariableText;
               String s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                if (s2==null) s2=s1;
                s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                s2="<HTML>"
                 +"<base href='file:"+a.path+"/symbols/  '/>"
         +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
                 +"<FONT COLOR='#ffffff' SIZE=3 >"
                 +s2
                 +"</FONT>"
                 +"</P>"
                 +"</HTML>";
             valoare=s2;
           }
          return valoare;
      }

      public int  getSize()
      {
          return searchContent.size();
      }
  }

 public class ListContentSelectionListener
           implements ListSelectionListener
    {

        public void valueChanged(ListSelectionEvent e)
        {
         pressedRowListContent(e);
        }

    }

void pressedRowListContent(ListSelectionEvent e)
   {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
       if (!lsm.isSelectionEmpty())
       {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++)
            {
                if (lsm.isSelectedIndex(i))
                {
                 selectedRowContent=i;
                 
                }
            }
       }
    
   }

  //we create the selection model
  public class  SelectionModelListContent extends DefaultListSelectionModel
  {
      public SelectionModelListContent()
      {
         super();
         this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(
                                    new ListContentSelectionListener()
                                  );
      }
  }

   public class ModelListFound extends AbstractTableModel
  {
      List<List<String>> table=null;
      String[] namesColumns={"No.","Type","Name","Content"};


      public ModelListFound()
      {
          table= new ArrayList<List<String>>();
          Application0 a=(Application0)Application.getInstance();
          List<AppliedItem> found=null;
          if (a.frame0!=null)
          {

           if (a.frame0.listOfAppliedItems!=null)
             {
               found=a.frame0.listOfFoundItems;
             }

          }

          if (found!=null)
          {
           if (!found.isEmpty())
           {
               int max=found.size();
               for (int i=0;i<max;i++)
               {
                List<String> line=new ArrayList<String>();
                AppliedItem item=found.get(i);
                int type=item.type;
                String name=item.name;
                String displayedType="";
                String content="";

                if (type==DemonstrationConstants.AXIOM_FROM_COMPOSED_AXIOM)
                {
                    displayedType="Complex axiom";
                    if (a.frame0.source1.axioms.containsKey(name))
                    {
                      Axiom ax=(Axiom)a.frame0.source1.axioms.get(name);
                      if (ax.items.size()>0)
                        {
                        int j=0;
                        do
                        {
                         //we display component items of the axiom
                         //display htmldef
                        String s1=ax.items.get(j).constantOrVariableText,s2="";
                           s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                            if (s2==null) s2=s1;
                           s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                        //END display htmldef
                        content=content+" "+s2;
                        j++;
                        } while (j<ax.items.size());
                        }
                    }
                }
                else if (type==DemonstrationConstants.SIMPLE_AXIOM)
                {
                    displayedType="Simple axiom";
                    if (a.frame0.source1.axioms.containsKey(name))
                    {
                      Axiom ax=(Axiom)a.frame0.source1.axioms.get(name);
                      if (ax.items.size()>0)
                        {
                        int j=0;
                        do
                        {
                        //we display the component items of the axiom
                        //display htmldef
                        String s1=ax.items.get(j).constantOrVariableText,s2="";
                           s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                            if (s2==null) s2=s1;
                           s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                        //END display htmldef
                        content=content+" "+s2;
                        j++;
                        } while (j<ax.items.size());
                        }
                    }
                }
                else if (type==DemonstrationConstants.THEOREM_FROM_COMPOSED_THEOREM)
                {
                    displayedType="Complex theorem";
                    if (a.frame0.source1.theorems.containsKey(name))
                    {
                      Theorem tx=(Theorem)a.frame0.source1.theorems.get(name);
                      if (tx.items.size()>0)
                        {
                        int j=0;
                        do
                        {
                         //we display the component items of the theorem
                         //display htmldef
                        String s1=tx.items.get(j).constantOrVariableText,s2="";
                           s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                            if (s2==null) s2=s1;
                           s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                         //END display htmldef
                        content=content+" "+s2;
                        j++;
                        } while (j<tx.items.size());
                        }
                    }
                }
                else if (type==DemonstrationConstants.SIMPLE_THEOREM)
                {
                    displayedType="Simple theorem";
                    if (a.frame0.source1.theorems.containsKey(name))
                    {
                      Theorem tx=(Theorem)a.frame0.source1.theorems.get(name);
                      if (tx.items.size()>0)
                        {
                        int j=0;
                        do
                        {
                         //we display the component items of the theorem
                         //display htmldef
                        String s1=tx.items.get(j).constantOrVariableText,s2="";
                           s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                            if (s2==null) s2=s1;
                           s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                        //END display htmldef
                        content=content+" "+s2;
                        j++;
                        } while (j<tx.items.size());
                        }
                    }
                }
                else if (type==DemonstrationConstants.PROPER_HYPOTHESIS)
                {
                 displayedType="Hypothesis";

                if (a!=null)
                 {
                 if (a.frame0!=null)
                 {
                 if (a.frame0.source1!=null)
                 {
                 if(a.frame0.source1.hypotheses!=null)
                 {
                 if(a.frame0.source1.hypotheses.get(name)!=null)
                 {
                 Hypothesis xHypothesis=(Hypothesis)a.frame0.source1.hypotheses.get(name);
                 if
                   (xHypothesis!=null)
                 {
                if (xHypothesis.items.size()>0)
                {
                int j=0;
                do
                {
                //we display the component items of the hypothesis
                //display htmldef
                String s1=xHypothesis.items.get(j).constantOrVariableText,s2="";
                   s2=a.frame0.source1.htlmldefString1AsString2.get(s1);
                    if (s2==null) s2=s1;
                   s2=s2.replaceAll("ALIGN=TOP","ALIGN=CENTER");
                //END display htmldef
                content=content+" "+s2;
                j++;
                } while (j<xHypothesis.items.size());

                }
                }
                }
                }
                }
                }
                }

                }

                //we create i line from the list
                line.add(""+i);
                line.add(displayedType);
                line.add(name);
                content="<HTML>"
                 +"<base href='file:"+a.path+"/symbols/  '/>"
                 +"<P CLASS='western' STYLE='margin-bottom: 0cm; background: #04ff81'>"
                 +"<FONT COLOR='#ffffff' SIZE=3 >"
                +content
                +"</FONT>"
                +"</P>"
                +"</HTML>";
                line.add(content);
                //we add i line to the table(list)
                table.add(line);
               }
           }
          }


      }
      public String getValueAt(int line,int column)
      {
          String value="";
          List<String> xLine=null;
          if ((line>=0)&(line<table.size()))
          {
            xLine=table.get(line);
            if (xLine!=null)
            {
                if ((column>=0)&(column<xLine.size()))
                 {
                  value=xLine.get(column);
                 }
            }

          }
          return value;
      }

      public int getColumnCount()
      {
          return 4;
      }

      public int  getRowCount()
      {
          return table.size();
      }
      @Override
      public String getColumnName(int coloana)
      {
          return namesColumns[coloana];
      }

  }

       public class ListFoundSelectionListener
           implements ListSelectionListener
    {

        public void valueChanged(ListSelectionEvent e)
        {
         pressedRowListFound(e);
        }

    }

   void pressedRowListFound(ListSelectionEvent e)
   {      
         ListSelectionModel lsm = (ListSelectionModel)e.getSource();
       if (!lsm.isSelectionEmpty())
       {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++)
            {
                if (lsm.isSelectedIndex(i))
                {
                 selectedRowListFound=i;
                 
                }
            }
       }
      
   }

  public int  selectedRowListFound=-1;
  public int searchOption=1;
  //we create selection model
  public class  SelectionModelListFound extends DefaultListSelectionModel
  {
      public SelectionModelListFound()
      {
      super();
      this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
      this.addListSelectionListener(new ListFoundSelectionListener() );

        

      }
  }
}