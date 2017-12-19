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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panou_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(525, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(panou_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
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
