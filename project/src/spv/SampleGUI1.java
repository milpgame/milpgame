//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

public class SampleGUI1 extends javax.swing.JFrame {

    /** Creates new form SampleGUI1 */
    public SampleGUI1() {
        initComponents();
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panou_tab = new javax.swing.JTabbedPane();
        panou_1 = new javax.swing.JPanel();
        panou_2 = new javax.swing.JPanel();
        pan_div_ed_dem = new javax.swing.JSplitPane();
        pan_rul_sus_ed_dem = new javax.swing.JScrollPane();
        pan_sus_ed_dem = new javax.swing.JPanel();
        pan_tab_jos_ed_dem = new javax.swing.JTabbedPane();
        pan_BC_0 = new javax.swing.JPanel();
        pan_FC_0 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setName("Form"); // NOI18N

        panou_tab.setName("panou_tab"); // NOI18N

        panou_1.setName("panou_1"); // NOI18N

        javax.swing.GroupLayout panou_1Layout = new javax.swing.GroupLayout(panou_1);
        panou_1.setLayout(panou_1Layout);
        panou_1Layout.setHorizontalGroup(
            panou_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        panou_1Layout.setVerticalGroup(
            panou_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(spv.Application0.class).getContext().getResourceMap(SampleGUI1.class);
        panou_tab.addTab(resourceMap.getString("panou_1.TabConstraints.tabTitle"), panou_1); // NOI18N

        panou_2.setName("panou_2"); // NOI18N

        pan_div_ed_dem.setDividerLocation(200);
        pan_div_ed_dem.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        pan_div_ed_dem.setName("pan_div_ed_dem"); // NOI18N

        pan_rul_sus_ed_dem.setName("pan_rul_sus_ed_dem"); // NOI18N

        pan_sus_ed_dem.setName("pan_sus_ed_dem"); // NOI18N

        javax.swing.GroupLayout pan_sus_ed_demLayout = new javax.swing.GroupLayout(pan_sus_ed_dem);
        pan_sus_ed_dem.setLayout(pan_sus_ed_demLayout);
        pan_sus_ed_demLayout.setHorizontalGroup(
            pan_sus_ed_demLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );
        pan_sus_ed_demLayout.setVerticalGroup(
            pan_sus_ed_demLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );

        pan_rul_sus_ed_dem.setViewportView(pan_sus_ed_dem);

        pan_div_ed_dem.setTopComponent(pan_rul_sus_ed_dem);

        pan_tab_jos_ed_dem.setName("pan_tab_jos_ed_dem"); // NOI18N

        pan_BC_0.setName("pan_BC_0"); // NOI18N

        javax.swing.GroupLayout pan_BC_0Layout = new javax.swing.GroupLayout(pan_BC_0);
        pan_BC_0.setLayout(pan_BC_0Layout);
        pan_BC_0Layout.setHorizontalGroup(
            pan_BC_0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
        );
        pan_BC_0Layout.setVerticalGroup(
            pan_BC_0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        pan_tab_jos_ed_dem.addTab(resourceMap.getString("pan_BC_0.TabConstraints.tabTitle"), pan_BC_0); // NOI18N

        pan_FC_0.setName("pan_FC_0"); // NOI18N

        javax.swing.GroupLayout pan_FC_0Layout = new javax.swing.GroupLayout(pan_FC_0);
        pan_FC_0.setLayout(pan_FC_0Layout);
        pan_FC_0Layout.setHorizontalGroup(
            pan_FC_0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
        );
        pan_FC_0Layout.setVerticalGroup(
            pan_FC_0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        pan_tab_jos_ed_dem.addTab(resourceMap.getString("pan_FC_0.TabConstraints.tabTitle"), pan_FC_0); // NOI18N

        pan_div_ed_dem.setRightComponent(pan_tab_jos_ed_dem);

        javax.swing.GroupLayout panou_2Layout = new javax.swing.GroupLayout(panou_2);
        panou_2.setLayout(panou_2Layout);
        panou_2Layout.setHorizontalGroup(
            panou_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_div_ed_dem, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
        panou_2Layout.setVerticalGroup(
            panou_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_div_ed_dem, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );

        panou_tab.addTab(resourceMap.getString("panou_2.TabConstraints.tabTitle"), panou_2); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panou_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(348, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panou_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SampleGUI1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel pan_BC_0;
    private javax.swing.JPanel pan_FC_0;
    private javax.swing.JSplitPane pan_div_ed_dem;
    private javax.swing.JScrollPane pan_rul_sus_ed_dem;
    private javax.swing.JPanel pan_sus_ed_dem;
    private javax.swing.JTabbedPane pan_tab_jos_ed_dem;
    private javax.swing.JPanel panou_1;
    private javax.swing.JPanel panou_2;
    private javax.swing.JTabbedPane panou_tab;
    // End of variables declaration//GEN-END:variables

}
