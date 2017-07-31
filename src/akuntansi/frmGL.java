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
public class frmGL extends javax.swing.JFrame {

    private Connection conn;

    /**
     * Creates new form frmGL
     */
    public frmGL(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void load(Date from, Date till, int nochart) {
        removeTableData();
        try {
            String sql = "SELECT j.chartno, j.kode_jurnal, j.tanggal, j.debit, j.kredit, j.description, j.debit-j.kredit as balance "
                    + "FROM jurnal j where tanggal between ? and ? and chartno = ? group by j.kode_jurnal;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);
            pstatement.setInt(3, nochart);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblGL.getModel();

                    Object data[] = {
                        rs.getDate("j.tanggal"),
                        rs.getInt("j.kode_jurnal"),
                        rs.getString("j.description"),
                        rs.getDouble("debit"),
                        rs.getDouble("kredit"),
                        rs.getDouble("balance")
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
    
    private void totaldebit (int nochart) {
         try {
            String sql = "select SUM(j.debit) as totaldebit from jurnal j where chartno = ?";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            pstatement.setInt(1, nochart);

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
    
    private void totalkredit (int nochart) {
         try {
            String sql = "select SUM(j.kredit) as totalkredit from jurnal j where chartno = ?";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            pstatement.setInt(1, nochart);

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
    
    private void totalbalance (int nochart) {
         try {
            String sql = "select SUM(j.debit)-SUM(j.kredit) as totalbalance from jurnal j where chartno = ?;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            pstatement.setInt(1, nochart);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                        int totalbalance = rs.getInt("totalbalance");
                        txtTotalBalance.setText(String.valueOf(totalbalance));

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
        dtcTill = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGL = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNoAkun = new javax.swing.JTextField();
        txtNamaAkun = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotalDebit = new javax.swing.JTextField();
        txtTotalKredit = new javax.swing.JTextField();
        txtTotalBalance = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("General Ledger");
        setIconImages(null);
        setPreferredSize(new java.awt.Dimension(830, 520));

        jLabel1.setText("Period : ");

        jLabel2.setText("-");

        tblGL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "No Jurnal", "Desciption", "Debit", "Kredit", "Balance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
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
        tblGL.setColumnSelectionAllowed(true);
        tblGL.setShowHorizontalLines(false);
        tblGL.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblGL);
        tblGL.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel3.setText("No. Akun : ");

        jLabel4.setText("Nama Akun : ");

        txtNoAkun.setEnabled(false);
        txtNoAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoAkunActionPerformed(evt);
            }
        });

        txtNamaAkun.setEnabled(false);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel5.setText("Total Debit");

        jLabel6.setText("Total Kredit");

        jLabel7.setText("Total Balance");

        txtTotalDebit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalDebitActionPerformed(evt);
            }
        });

        txtTotalKredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalKreditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNoAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNamaAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(521, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtTotalKredit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtTotalBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNoAkun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNamaAkun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalKredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoAkunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoAkunActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        dlgAkunCodeSearch akunsearch = new dlgAkunCodeSearch(this, true, conn);
        akunsearch.setVisible(true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            String date = sdf.format(dtcFrom.getDate());
//            Date date1 = sdf.parse(date);
//
//            String date2 = sdf.format(dtcTill.getDate());
//            Date date3 = sdf.parse(date2);

            if (dtcFrom.getDate() == null || dtcTill.getDate() == null) {
                Sutil.mse(this, "Pilih tanggal !");
            } else if (txtNoAkun.getText().equals("") || txtNamaAkun.getText().equals("")) {
                Sutil.mse(this, "Pilih no.akun dan nama akun !");
            } else if (dtcFrom.getDate() != null && dtcTill.getDate() != null 
                    && !txtNoAkun.getText().equals("") && !txtNamaAkun.getText().equals("")) {

                String datef = sdf.format(dtcFrom.getDate());
                Date datefrom = sdf.parse(datef);

                String datet = sdf.format(dtcTill.getDate());
                Date datetill = sdf.parse(datet);

                load(datefrom, datetill, Integer.valueOf(txtNoAkun.getText()));

            } else {
                Sutil.mse(this, "Call your programmer ! =D , Good Luck !!!");
            }
        } catch (ParseException ex) {
            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
        }
        totaldebit(Integer.valueOf(txtNoAkun.getText()));
        totalkredit(Integer.valueOf(txtNoAkun.getText()));
        totalbalance(Integer.valueOf(txtNoAkun.getText()));
    }//GEN-LAST:event_btnOkActionPerformed

    private void txtTotalKreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalKreditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKreditActionPerformed

    private void txtTotalDebitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalDebitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDebitActionPerformed

    public static void pilihAkun(int code, String name) {
        txtNoAkun.setText(String.valueOf(code));
        txtNamaAkun.setText(name);
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblGL.getModel();
        tableModel.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnSearch;
    private com.toedter.calendar.JDateChooser dtcFrom;
    private com.toedter.calendar.JDateChooser dtcTill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblGL;
    public static javax.swing.JTextField txtNamaAkun;
    public static javax.swing.JTextField txtNoAkun;
    private javax.swing.JTextField txtTotalBalance;
    private javax.swing.JTextField txtTotalDebit;
    private javax.swing.JTextField txtTotalKredit;
    // End of variables declaration//GEN-END:variables
}
