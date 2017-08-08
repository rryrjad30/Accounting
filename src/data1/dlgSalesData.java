/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author COMPUTER
 */
public class dlgSalesData extends javax.swing.JDialog {

    Connection conn;

    /**
     * Creates new form dlgSales
     */
    public dlgSalesData(java.awt.Frame parent, boolean modal, Connection conn) {
        super(parent, modal);
        this.conn = conn;
        initComponents();
        loadAllDatabase();
        setLocationRelativeTo(null);
    }

    private double getGrandTotalbyIdTransaksi(int idtransaksi) throws SQLException {
        double grandtotal = 0;
        String sqlPenyewaLookup = "Select grandtotal from transaksidetail where idtransaksi = ? ;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
        pstPenyewaLookup.setInt(1, idtransaksi);

        ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
        while (rsPenyewaLookup.next()) {
            grandtotal = rsPenyewaLookup.getDouble("grandtotal");
        }
        return grandtotal;
    }

    private String getNamebyNoFaktur(int nofaktur) throws SQLException {
        String name = "";
        String sqlPenyewaLookup = "Select dc.namacustomer from datacustomer dc inner join transaksimaster tm on "
                + "dc.idcustomer =  tm.idcustomer where tm.nofaktur = ? ;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
        pstPenyewaLookup.setInt(1, nofaktur);

        ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
        while (rsPenyewaLookup.next()) {
            name = rsPenyewaLookup.getString("namacustomer");
        }
        return name;
    }
  
    private Date getDatebyNoFaktur(int nofaktur) throws SQLException {
        Date tanggaltransaksi = null;
        String sqlPenyewaLookup = "Select tanggaltransaksi from transaksimaster where nofaktur = ? ;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
        pstPenyewaLookup.setInt(1, nofaktur);

        ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
        while (rsPenyewaLookup.next()) {
            tanggaltransaksi = rsPenyewaLookup.getDate("tanggaltransaksi");
        }
        return tanggaltransaksi;
    }
    
    private int getNoFakturbyIdTransaksi(int idtransaksi) throws SQLException {
        int nofaktur = 0;
        String sqlPenyewaLookup = "Select nofaktur from transaksidetail where idtransaksi = ? ;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
        pstPenyewaLookup.setInt(1, idtransaksi);

        ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
        while (rsPenyewaLookup.next()) {
            nofaktur = rsPenyewaLookup.getInt("nofaktur");
        }
        return nofaktur;
    }

    private void executeGrandTotal() {
        try {
            String sql = "SELECT sum(grandtotal) FROM transaksidetail;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    txtGrandTotal.setText(String.valueOf(rs.getDouble("sum(grandtotal)")));
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(dlgSalesData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllDatabase() {
        removeTableData();
        try {
            String sql = "SELECT * FROM transaksidetail;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblSalesData.getModel();
                    int nofaktur = rs.getInt("nofaktur");
                    int idtransaksi = rs.getInt("idtransaksi");
                    Object data[] = {
                        getNoFakturbyIdTransaksi(idtransaksi),
                        getDatebyNoFaktur(nofaktur),
                        getNamebyNoFaktur(nofaktur),
                        getGrandTotalbyIdTransaksi(idtransaksi)
                    };
                    tableModel.addRow(data);
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
            executeGrandTotal();
        } catch (SQLException ex) {
            Logger.getLogger(dlgSalesData.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSalesData = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearchCustomer = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        txtGrandTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sales Data");

        tblSalesData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Faktur", "Tanggal", "Nama Customer", "Grand Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSalesData.setColumnSelectionAllowed(true);
        tblSalesData.setShowHorizontalLines(false);
        tblSalesData.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblSalesData);
        tblSalesData.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel1.setText("Search by Name : ");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtGrandTotal.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addContainerGap(254, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 587, Short.MAX_VALUE)
                        .addComponent(txtGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchbyName();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void searchbyName() {
        try {
            removeTableData();
            String sql = "SELECT tm.nofaktur, tm.tanggaltransaksi, dc.namacustomer, td.grandtotal FROM "
                    + "((transaksimaster tm inner join transaksidetail td on tm.nofaktur = td.nofaktur) "
                    + "inner join datacustomer dc on tm.idcustomer = dc.idcustomer) where dc.namacustomer like ?;";

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1, "%" + txtSearchCustomer.getText().trim() + "%");

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                DefaultTableModel tableModel = (DefaultTableModel) tblSalesData.getModel();
                while (rs.next()) {
                    Object data[] = {
                        rs.getInt("nofaktur"),
                        rs.getDate("tanggaltransaksi"),
                        rs.getString("dc.namacustomer"),
                        rs.getDouble("td.grandtotal")
                    };
                    tableModel.addRow(data);
                }
            }

            rs.close();
            pstatement.close();
            executeGrandTotalSearch();
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void executeGrandTotalSearch() {
        for (int i = 0; i < tblSalesData.getRowCount(); i++) {
            double total = 0;
            double Amount = (double) tblSalesData.getValueAt(i, 3);
            total = Amount + total;
            txtGrandTotal.setText(String.valueOf(total));
        }

    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblSalesData.getModel();
        tableModel.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSalesData;
    private javax.swing.JTextField txtGrandTotal;
    private javax.swing.JTextField txtSearchCustomer;
    // End of variables declaration//GEN-END:variables
}
