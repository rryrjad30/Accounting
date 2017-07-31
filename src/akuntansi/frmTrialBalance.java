/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akuntansi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import util.Sutil;

/**
 *
 * @author COMPUTER
 */
public class frmTrialBalance extends javax.swing.JFrame {

    private Connection conn;

    /**
     * Creates new form frmTrialBalance
     */
    public frmTrialBalance(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void load(Date from, Date till) {
        removeTableData();
        try {
            String sql = "select j.chartno, ac.chartname, sum(tb.opening) as opening, sum(j.debit) as debit, sum(j.kredit) as kredit, "
                    + "sum(j.debit-j.kredit) as ending from jurnal j inner join accountchart ac on j.chartno = ac.chartno "
                    + "inner join trialbalance tb on j.chartno = tb.chartno where j.tanggal between ? and ? group by j.chartno;";

            PreparedStatement pstatement = conn.prepareStatement(sql);

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblTrialBalance.getModel();

                    Object data[] = {
                        rs.getInt("j.chartno"),
                        rs.getString("ac.chartname"),
                        rs.getInt("opening"),
                        rs.getDouble("debit"),
                        rs.getDouble("kredit"),
                        rs.getInt("ending")
                    };
                    tableModel.addRow(data);
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblTrialBalance.getModel();
        tableModel.setRowCount(0);
    }

    private void totaldebit() {
        try {
            String sql = "select SUM(j.debit) as totaldebit from jurnal j";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    int totaldebit = rs.getInt("totaldebit");
                    txtTotalDebit.setText(String.valueOf(totaldebit));
                };
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void totalkredit() {
        try {
            String sql = "select SUM(j.kredit) as totalkredit from jurnal j";
            PreparedStatement pstatement = conn.prepareStatement(sql);;

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    int totalkredit = rs.getInt("totalkredit");
                    txtTotalKredit.setText(String.valueOf(totalkredit));

                };
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void totalopening() {
//        try {
//            String sql = "select SUM(tb.opening) as totalopening from trialbalance tb;";
//            PreparedStatement pstatement = conn.prepareStatement(sql);
//
//            ResultSet rs = pstatement.executeQuery();
//            if (rs.isBeforeFirst()) { // check is resultset not empty
//                while (rs.next()) {
//                    int totalopening = rs.getInt("totalopening");
//                    txtTotalOpening.setText(String.valueOf(totalopening));
//
//                };
//            } else {
//                util.Sutil.msg(this, "Record Empty");
//            }
//
//            rs.close();
//            pstatement.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    private void totalending() {
//        try {
//            String sql = "select SUM(tb.ending) as totalending from trialbalance tb;";
//            PreparedStatement pstatement = conn.prepareStatement(sql);
//
//            ResultSet rs = pstatement.executeQuery();
//            if (rs.isBeforeFirst()) { // check is resultset not empty
//                while (rs.next()) {
//                    int totalending = rs.getInt("totalending");
//                    txtTotalEnding.setText(String.valueOf(totalending));
//
//                };
//            } else {
//                util.Sutil.msg(this, "Record Empty");
//            }
//
//            rs.close();
//            pstatement.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        dtcFrom = new com.toedter.calendar.JDateChooser();
        dtcTill = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrialBalance = new javax.swing.JTable();
        btnFind = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTotalDebit = new javax.swing.JTextField();
        txtTotalOpening = new javax.swing.JTextField();
        txtTotalKredit = new javax.swing.JTextField();
        txtTotalEnding = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Trial Balance");
        setIconImages(null);

        jLabel1.setText("Date : ");

        jLabel2.setText("-");

        tblTrialBalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Akun", "Nama Akun", "Opening", "Debit", "Kredit", "Ending"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTrialBalance.setColumnSelectionAllowed(true);
        tblTrialBalance.setShowHorizontalLines(false);
        tblTrialBalance.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblTrialBalance);
        tblTrialBalance.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        jLabel3.setText("Total Opening");

        jLabel4.setText("Total Debit");

        jLabel5.setText("Total Kredit");

        jLabel6.setText("Total Ending");

        txtTotalEnding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalEndingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtTotalOpening, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTotalKredit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtTotalEnding, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnFind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalOpening, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalKredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalEnding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:

        try {
            if (dtcFrom.getDate() == null || dtcTill.getDate() == null) {
                Sutil.mse(this, "Pilih tanggal !");
            } else if (dtcFrom.getDate() != null && dtcTill.getDate() != null) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String datef = sdf.format(dtcFrom.getDate());
                Date datefrom = sdf.parse(datef);

                String datet = sdf.format(dtcTill.getDate());
                Date datetill = sdf.parse(datet);

                load(datefrom, datetill);
                totalOpening();
                totaldebit();
                totalkredit();
                totalEnding();
            } else {
                Sutil.mse(this, "Call your programmer ! =D , Good Luck !!!");
            }
        } catch (ParseException ex) {
            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnFindActionPerformed

    private void txtTotalEndingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalEndingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalEndingActionPerformed

    private int totalOpening() {
        int opening = 0;
        for (int i = 0; i < tblTrialBalance.getRowCount(); i++) {
            opening += Integer.parseInt(tblTrialBalance.getValueAt(i, 2).toString());
            txtTotalOpening.setText(String.valueOf(opening));
        }
        return opening;
    }

    public int totalEnding() {
        int ending = 0;
        for (int i = 0; i < tblTrialBalance.getRowCount(); i++) {
            ending += Integer.parseInt(tblTrialBalance.getValueAt(i, 5).toString());
            txtTotalEnding.setText(String.valueOf(ending));
        }
        return ending;
    }


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private com.toedter.calendar.JDateChooser dtcFrom;
    private com.toedter.calendar.JDateChooser dtcTill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTrialBalance;
    private javax.swing.JTextField txtTotalDebit;
    public static javax.swing.JTextField txtTotalEnding;
    private javax.swing.JTextField txtTotalKredit;
    private javax.swing.JTextField txtTotalOpening;
    // End of variables declaration//GEN-END:variables
}
