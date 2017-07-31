/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author COMPUTER
 */
public class dlgItem extends javax.swing.JDialog {

    private Connection conn;

    /**
     * Creates new form dlgItem
     */
    public dlgItem(java.awt.Frame parent, boolean modal, Connection conn) {
        super(parent, modal);
        this.conn = conn;
        initComponents();
        loadAllDatabase();
        kodeProduk();
        tableSelectionListener();
        setLocationRelativeTo(null);
    }
    
    private void updateDatabase(int kodeproduk, String namaproduk, String kategori, double hargabeli, double hargajual) {

        if (conn != null) {
            try {
                String sql = "UPDATE stock set "
                        + "nama_produk = '" + namaproduk + "' , "
                        + "kategori = '" + kategori + "' , "
                        + "harga_beli = " + hargabeli + " , "
                        + "harga_jual = " + hargajual + " "
                        + "where kode_produk = " + kodeproduk + ";";
                PreparedStatement pstatement = conn.prepareStatement(sql);

                pstatement.executeUpdate();
                pstatement.close();
                loadAllDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(dlgCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveDatabase(int kodeproduk, String namaproduk, String kategori, int jumlah, double hargabeli, double hargajual) throws SQLException {
        if (conn != null) {
            System.out.println("Connected to DB!\n");

            String sql = "INSERT INTO stock "
                    + "(kode_produk, nama_produk, kategori, jumlah, harga_beli, harga_jual)"
                    + "VALUES (?,?,?,?,?,?);";

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, kodeproduk);
            pstatement.setString(2, namaproduk);
            pstatement.setString(3, kategori);
            pstatement.setInt(4, jumlah);
            pstatement.setDouble(5, hargabeli);
            pstatement.setDouble(6, hargajual);

            pstatement.executeUpdate();
            System.out.println("Record insert.");

            pstatement.close();
        }
        loadAllDatabase();
    }

    private void loadAllDatabase() {
        removeTableData();
        try {
            String sql = "SELECT * FROM stock;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblItem.getModel();

                    Object data[] = {
                        rs.getString("kode_produk"),
                        rs.getString("nama_produk"),
                        rs.getString("kategori"),
                        rs.getDouble("harga_beli"),
                        rs.getDouble("harga_jual")
                    };
                    tableModel.addRow(data);
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(dlgCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblItem.getModel();
        tableModel.setRowCount(0);
    }

    public void tableSelectionListener() {
        ListSelectionListener listener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = tblItem.getSelectedRow();
                if (row >= 0) {
                    txtKodeProduk.setText(tblItem.getValueAt(row, 0).toString());
                    txtNamaProduk.setText(tblItem.getValueAt(row, 1).toString());
                    txtKategori.setText(tblItem.getValueAt(row, 2).toString());
                    txtHargaBeli.setText(tblItem.getValueAt(row, 3).toString());
                    txtHargaJual.setText(tblItem.getValueAt(row, 4).toString());
                }
            }
        };
        tblItem.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblItem.getSelectionModel().addListSelectionListener(listener);
    }

    private void kodeProduk() {
        try {
            String sql = "SELECT max(kode_produk) FROM stock;";
            Integer lastDataValue = 0;
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty

                while (rs.next()) {
                    lastDataValue = rs.getInt("max(kode_produk)");
                };
                // tableModel.addRow(data);
            } else {
                util.Sutil.msg(this, "Record Empty");
            }
            ++lastDataValue;
            txtKodeProduk.setText(lastDataValue.toString());

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(dlgItem.class.getName()).log(Level.SEVERE, null, ex);
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
        tblItem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKodeProduk = new javax.swing.JTextField();
        txtNamaProduk = new javax.swing.JTextField();
        txtKategori = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtHargaBeli = new javax.swing.JTextField();
        txtHargaJual = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Item");
        setIconImages(null);

        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Produk", "Nama Produk", "Kategori", "Harga Beli", "Harga Jual"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItem.setColumnSelectionAllowed(true);
        tblItem.setShowHorizontalLines(false);
        tblItem.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblItem);
        tblItem.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel1.setText("Kode Produk");

        jLabel2.setText("Nama Produk");

        jLabel3.setText("Kategori");

        txtKodeProduk.setEnabled(false);

        jLabel4.setText("Harga Beli");

        jLabel5.setText("Harga Jual");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKodeProduk)
                    .addComponent(txtNamaProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(txtKategori))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKodeProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        executeSave();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        executeNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        executeUpdate();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void executeNew() {
        kodeProduk();
        txtNamaProduk.setText("");
        txtKategori.setText("");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
    }

    private void executeSave() {
        try {
            if (!txtKodeProduk.equals("") && !txtNamaProduk.equals("") && !txtKategori.equals("")
                    && !txtHargaBeli.equals("") && !txtHargaJual.equals("")) {
                int jumlah = 0;
                saveDatabase(Integer.parseInt(txtKodeProduk.getText()), txtNamaProduk.getText(), txtKategori.getText(),
                         jumlah, Double.parseDouble(txtHargaBeli.getText()), Double.parseDouble(txtHargaJual.getText()));
                executeNew();
                util.Sutil.msg(this, "Saved!");
            } else {
                util.Sutil.mse(this, "Please input the field");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dlgItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void executeUpdate() {
        if (!txtKodeProduk.equals("") && !txtNamaProduk.equals("") && !txtKategori.equals("")
                    && !txtHargaBeli.equals("") && !txtHargaJual.equals("")) {
            updateDatabase(Integer.parseInt(txtKodeProduk.getText()), txtNamaProduk.getText(), txtKategori.getText(),
                         Double.parseDouble(txtHargaBeli.getText()), Double.parseDouble(txtHargaJual.getText()));
            executeNew();
            util.Sutil.msg(this, "Updated!");
        } else {
            util.Sutil.mse(this, "Please select data");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblItem;
    private javax.swing.JTextField txtHargaBeli;
    private javax.swing.JTextField txtHargaJual;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtKodeProduk;
    private javax.swing.JTextField txtNamaProduk;
    // End of variables declaration//GEN-END:variables
}
