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
import static java.util.Date.from;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import util.Sutil;

/**
 *
 * @author COMPUTER
 */
public class frmBalanceSheet extends javax.swing.JFrame {

    private Connection conn;

    /**
     * Creates new form frmBalanceSheet
     */
    public frmBalanceSheet(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void loadAsset(Date from, Date till) {
        try {
            String sql = "select ac.chartname, j.debit-j.kredit as cash from jurnal j inner join accountchart ac on "
                    + "j.chartno = ac.chartno where j.chartno like '1%%%' and "
                    + "j.tanggal between ? and ? group by j.chartno;";

            PreparedStatement pstatement = conn.prepareStatement(sql);

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblAsset.getModel();

                    Object data[] = {
                        rs.getString("ac.chartname"),
                        rs.getInt("cash")
                    };
                    tableModel.addRow(data);
                }
            } else {
//                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmBalanceSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadLiability(Date from, Date till) {
        try {
            String sql = "select ac.chartname, -(j.debit-j.kredit) as liability from jurnal j inner join accountchart ac on "
                    + "j.chartno = ac.chartno where j.chartno like '2%%%' and "
                    + "j.tanggal between ? and ? group by j.chartno;";

            PreparedStatement pstatement = conn.prepareStatement(sql);

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblLiability.getModel();

                    Object data[] = {
                        rs.getString("ac.chartname"),
                        rs.getInt("liability")
                    };
                    tableModel.addRow(data);
                }
            } else {
//                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmBalanceSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCapital(Date from, Date till) {
        try {
            String sql = "select ac.chartname, -(j.debit-j.kredit) as capital from jurnal j inner join accountchart ac on "
                    + "j.chartno = ac.chartno where j.chartno like '3%%%' and "
                    + "j.tanggal between ? and ? group by j.chartno;";

            PreparedStatement pstatement = conn.prepareStatement(sql);

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblCapital.getModel();
                    
                    Object data[] = {
                        rs.getString("ac.chartname"),
                        rs.getInt("capital")
                    };
                    tableModel.addRow(data);

                }
            } else {
//                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmBalanceSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadProfitLoss(Date from, Date till) {
        try {
            String sql = "select profitloss from profitloss where tanggaltill between ? and ? ;";

            PreparedStatement pstatement = conn.prepareStatement(sql);

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblCapital.getModel();

                    String profit = "Profit-Loss";
                    double valueprofit = -(rs.getDouble("profitloss"));
                    Object data[] = {
                        profit,
                        valueprofit
                    };
                    tableModel.addRow(data);
                }
            } else {
//                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmBalanceSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dtcTill = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLiability = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsset = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCapital = new javax.swing.JTable();
        btnFind = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtCapital = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAsset = new javax.swing.JTextField();
        txtLiability = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotalLC = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Balance Sheet");
        setIconImages(null);

        jLabel1.setText("Balance Sheet");

        jLabel2.setText("Period :");

        jLabel3.setText("-");

        tblLiability.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chart Name", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLiability.setShowHorizontalLines(false);
        tblLiability.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tblLiability);

        jLabel6.setText("Liability");

        tblAsset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chart Name", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAsset.setShowHorizontalLines(false);
        tblAsset.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblAsset);

        jLabel10.setText("Capital");

        jLabel4.setText("Asset");

        tblCapital.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chart Name", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCapital.setShowHorizontalLines(false);
        tblCapital.setShowVerticalLines(false);
        jScrollPane3.setViewportView(tblCapital);

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        jLabel11.setText("Total Capital :");

        txtCapital.setEnabled(false);

        jLabel7.setText("Total Asset :");

        txtAsset.setEnabled(false);

        txtLiability.setEnabled(false);

        jLabel8.setText("Total Liability :");

        jLabel9.setText("Total Liability + Capital :");

        txtTotalLC.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAsset))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtLiability)))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCapital))
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalLC, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane2, jScrollPane3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnFind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(dtcTill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtAsset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtLiability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtCapital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTotalLC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        executeFind();
    }//GEN-LAST:event_btnFindActionPerformed

    private void executeFind() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            if (dtcFrom.getDate() == null || dtcTill.getDate() == null) {
                Sutil.mse(this, "Pilih tanggal !");
            } else if (dtcFrom.getDate() != null && dtcTill.getDate() != null) {

                String datef = sdf.format(dtcFrom.getDate());
                Date datefrom = sdf.parse(datef);

                String datet = sdf.format(dtcTill.getDate());
                Date datetill = sdf.parse(datet);

//                Asset
                loadAsset(datefrom, datetill);
                totalAsset();

//                Liability
                loadLiability(datefrom, datetill);
                totalLiability();

//                Capital
                loadCapital(datefrom, datetill);
                loadProfitLoss(datefrom, datetill);
                totalCapital();

                double total = totalLiability() + totalCapital();
                txtTotalLC.setText(String.valueOf(total));
            } else {
                Sutil.mse(this, "Call your programmer ! =D , Good Luck !!!");
            }
        } catch (ParseException ex) {
            Logger.getLogger(frmBalanceSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private double totalAsset() {
        int totalasset = 0;
        if (tblAsset.getRowCount() <= 0) {
            txtAsset.setText(String.valueOf(totalasset));
        } else {
            for (int i = 0; i < tblAsset.getRowCount(); i++) {
                totalasset += Double.valueOf(tblAsset.getValueAt(i, 1).toString());
                txtAsset.setText(String.valueOf(totalasset));
            }

        }
        return totalasset;
    }

    private double totalLiability() {
        int totallb = 0;
        if (tblLiability.getRowCount() <= 0) {
            txtLiability.setText(String.valueOf(totallb));
        } else {
            for (int i = 0; i < tblLiability.getRowCount(); i++) {
                totallb += Double.valueOf(tblLiability.getValueAt(i, 1).toString());
                txtLiability.setText(String.valueOf(totallb));
            }

        }
        return totallb;
    }

    private double totalCapital() {
        int totalcp = 0;
        if (tblCapital.getRowCount() <= 0) {
            txtCapital.setText(String.valueOf(totalcp));
        } else {
            for (int i = 0; i < tblCapital.getRowCount(); i++) {
                totalcp += Double.valueOf(tblCapital.getValueAt(i, 1).toString());
                txtCapital.setText(String.valueOf(totalcp));
            }

        }
        return totalcp;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private com.toedter.calendar.JDateChooser dtcFrom;
    private com.toedter.calendar.JDateChooser dtcTill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblAsset;
    private javax.swing.JTable tblCapital;
    private javax.swing.JTable tblLiability;
    private javax.swing.JTextField txtAsset;
    private javax.swing.JTextField txtCapital;
    private javax.swing.JTextField txtLiability;
    private javax.swing.JTextField txtTotalLC;
    // End of variables declaration//GEN-END:variables
}
