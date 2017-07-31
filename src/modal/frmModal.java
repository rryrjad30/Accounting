/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

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

/**
 *
 * @author COMPUTER
 */
public class frmModal extends javax.swing.JFrame {

    private Connection conn;

    /**
     * Creates new form frmModal
     */
    public frmModal(Connection conn) {
        this.conn = conn;
        initComponents();
        getLastValueOfKodeJurnal();
        setLocationRelativeTo(null);
    }

    private void insertToJurnalPrive(int kodejurnal, Date tanggal, int chartno, double debit, double kredit, String description) {
        try {
            String sql = "insert into jurnal (kode_jurnal, tanggal, chartno, debit, kredit, description) values (?,?,?,?,?,?); ";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            java.sql.Date sqlDate = new java.sql.Date(tanggal.getTime());
            pstatement.setInt(1, kodejurnal);
            pstatement.setDate(2, sqlDate);
            pstatement.setInt(3, chartno);
            pstatement.setDouble(4, debit);
            pstatement.setDouble(5, kredit);
            pstatement.setString(6, description);

            pstatement.executeUpdate();
            System.out.println("Jurnal Prive record insert.");

            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(frmModal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void insertToJurnalModal(int kodejurnal, Date tanggal, int chartno, double debit, double kredit, String description) {
        try {
            String sql = "insert into jurnal (kode_jurnal, tanggal, chartno, debit, kredit, description) values (?,?,?,?,?,?); ";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            java.sql.Date sqlDate = new java.sql.Date(tanggal.getTime());
            pstatement.setInt(1, kodejurnal);
            pstatement.setDate(2, sqlDate);
            pstatement.setInt(3, chartno);
            pstatement.setDouble(4, debit);
            pstatement.setDouble(5, kredit);
            pstatement.setString(6, description);

            pstatement.executeUpdate();
            System.out.println("Modal record insert.");

            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(frmModal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void insertToJurnal(int kodejurnal, Date tanggal, int chartno, double debit, double kredit, String description) {
        try {
            String sql = "insert into jurnal (kode_jurnal, tanggal, chartno, debit, kredit, description) values (?,?,?,?,?,?); ";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            java.sql.Date sqlDate = new java.sql.Date(tanggal.getTime());
            pstatement.setInt(1, kodejurnal);
            pstatement.setDate(2, sqlDate);
            pstatement.setInt(3, chartno);
            pstatement.setDouble(4, debit);
            pstatement.setDouble(5, kredit);
            pstatement.setString(6, description);

            pstatement.executeUpdate();
            System.out.println("Jurnal record insert.");

            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(frmModal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void insertPrive(Date tanggal, int kodejurnal, int chartno, double debit, double kredit, double saldo, String description) {
        try {
            String sql = "insert into modal (tanggal, kode_jurnal, chartno, debit, kredit, saldo, description) values (?,?,?,?,?,?,?); ";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(tanggal.getTime());
            pstatement.setDate(1, sqlDate);
            pstatement.setInt(2, kodejurnal);
            pstatement.setInt(3, chartno);
            pstatement.setDouble(4, debit);
            pstatement.setDouble(5, kredit);
            pstatement.setDouble(6, saldo);
            pstatement.setString(7, description);

            pstatement.executeUpdate();
            System.out.println("Prive record insert.");

            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertModal(Date tanggal, int kodejurnal, int chartno, double debit, double kredit, double saldo, String description) {
        try {
            String sql = "insert into modal (tanggal, kode_jurnal, chartno, debit, kredit, saldo, description) values (?,?,?,?,?,?,?); ";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(tanggal.getTime());
            pstatement.setDate(1, sqlDate);
            pstatement.setInt(2, kodejurnal);
            pstatement.setInt(3, chartno);
            pstatement.setDouble(4, debit);
            pstatement.setDouble(5, kredit);
            pstatement.setDouble(6, saldo);
            pstatement.setString(7, description);

            pstatement.executeUpdate();
            System.out.println("Modal record insert.");

            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getChartNamebyChartNo(int chartno) throws SQLException {
        String chartname = "";
        String sqlPenyewaLookup = "Select chartname from accountchart where chartno = ? ;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
        pstPenyewaLookup.setInt(1, chartno);

        ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
        while (rsPenyewaLookup.next()) {
            chartname = rsPenyewaLookup.getString("chartname");
        }
        return chartname;
    }

    private void loadPrive(int nochartprive, int chartno, String description) {
        removeTableData();
        try {
            String sql = "SELECT * FROM modal where chartno in (?,?) and description like ?;";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, nochartprive);
            pstatement.setInt(2, chartno);
            pstatement.setString(3, description);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblModal.getModel();

                    int chartno1 = rs.getInt("chartno");
                    Object data[] = {
                        rs.getDate("tanggal"),
                        rs.getInt("kode_jurnal"),
                        chartno1,
                        getChartNamebyChartNo(chartno1),
                        rs.getDouble("debit"),
                        rs.getDouble("kredit"),
                        rs.getDouble("saldo")
                    };
                    tableModel.addRow(data);
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(frmModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllDatabase(int nochart, int chartnoprive, String description) {
        removeTableData();
        try {
            String sql = "SELECT * FROM modal where chartno in (?,?) and description like ?;";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, nochart);
            pstatement.setInt(2, chartnoprive);
            pstatement.setString(3, "%" + description + "%");

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblModal.getModel();

                    int chartno = rs.getInt("chartno");
                    Object data[] = {
                        rs.getDate("tanggal"),
                        rs.getInt("kode_jurnal"),
                        chartno,
                        getChartNamebyChartNo(chartno),
                        rs.getDouble("debit"),
                        rs.getDouble("kredit"),
                        rs.getDouble("saldo")
                    };
                    tableModel.addRow(data);
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(frmModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setLastValueOfKodeJurnal() {
        try {
            if (conn != null) {

                int kodejurnal = Integer.parseInt(txtJ.getText());
                kodejurnal++;
                String sql = "UPDATE lastvalue set kodejurnal = ? "
                        + "where id = 1;";

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setInt(1, kodejurnal);

                pstatement.executeUpdate();
                System.out.println("Record insert.");

                pstatement.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void getLastValueOfKodeJurnal() {
        try {
            if (conn != null) {
                String sql = "select kodejurnal from lastvalue;";
                PreparedStatement pstatement = conn.prepareStatement(sql);
                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int kodejurnal = rs.getInt("kodejurnal");
                        txtJ.setText(String.valueOf(kodejurnal));
                    };
                } else {
                    util.Sutil.msg(this, "Record Empty");
                }
                rs.close();
                pstatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmModal.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        txtNoChart = new javax.swing.JTextField();
        txtNamaChart = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblModal = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        dtcDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtJ = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        btnSearchFrom = new javax.swing.JButton();
        txtnochartfrom = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modal");
        setIconImages(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("No Chart : ");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Nama Chart :");

        txtNoChart.setEnabled(false);

        txtNamaChart.setEnabled(false);

        tblModal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Jurnal", "No Chart", "Nama Chart", "Debit", "Kredit", "Balance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblModal.setShowHorizontalLines(false);
        tblModal.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblModal);

        jLabel3.setText("Jumlah :");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel4.setText("J - ");

        txtJ.setEnabled(false);

        jLabel5.setText("Date : ");

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabel6.setText("From : ");

        txtFrom.setEnabled(false);

        btnSearchFrom.setText("Search");
        btnSearchFrom.setEnabled(false);
        btnSearchFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchFromActionPerformed(evt);
            }
        });

        txtnochartfrom.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNoChart, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                    .addComponent(txtJ))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dtcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSearch)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnochartfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSearchFrom))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(txtNamaChart))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnochartfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchFrom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNamaChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(dtcDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNoChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearch)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel6)))
                        .addGap(24, 24, 24)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd)
                    .addComponent(btnNew))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        dlgAkunCodeSearch akuncode = new dlgAkunCodeSearch(this, true, conn);
        akuncode.setVisible(true);
        if (txtNoChart.getText().equals("3030")) {
            btnSearchFrom.setEnabled(true);
        } else if (!txtNoChart.getText().equals("3030")) {
            btnSearchFrom.setEnabled(false);
            if (!txtNamaChart.getText().equals("")) {
                String description = txtNamaChart.getText();
                loadAllDatabase(Integer.valueOf(txtNoChart.getText()), 3030, description);
            } else if (txtNamaChart.getText().equals("")) {
                util.Sutil.mse(this, "Pilih Chart !");
            }
        }
        btnSearch.setEnabled(false);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        executeAdd();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSearchFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchFromActionPerformed
        // TODO add your handling code here:
        dlgAkunCodeSearch akuncode = new dlgAkunCodeSearch(this, true, conn);
        akuncode.setVisible(true);
        loadPrive(Integer.valueOf(txtNoChart.getText()), Integer.valueOf(txtnochartfrom.getText()), txtFrom.getText());
    }//GEN-LAST:event_btnSearchFromActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        executeNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void executeAdd() {
        try {
            if (dtcDate.getDate() == null) {
                util.Sutil.mse(this, "Pilih tanggal !");
            } else if (dtcDate.getDate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String date = sdf.format(dtcDate.getDate());
                Date date1 = sdf.parse(date);

                if (txtNoChart.getText().equals("1010")) {
                    int nochartcash = 1010;
                    int nochartmodal = 3010;
                    double debitcash = Double.parseDouble(txtJumlah.getText());
                    double kreditcash = 0;
                    String description = "Add " + txtNamaChart.getText();
                    String descriptioncash = txtNamaChart.getText();
                    if (txtNamaChart.getText().equals("") || txtNoChart.getText().equals("")) {
                        util.Sutil.mse(this, "No.chart and Nama chart must not empty !");
                    } else {
                        if (tblModal.getRowCount() == 0) {
                            double saldo = debitcash;
                            insertModal(date1, Integer.valueOf(txtJ.getText()), nochartcash,
                                    debitcash, kreditcash, saldo, descriptioncash);
                            insertToJurnal(Integer.valueOf(txtJ.getText()), date1, nochartcash,
                                    debitcash, kreditcash, description);
                            double debitmodal = 0;
                            double kreditmodal = Double.parseDouble(txtJumlah.getText());
                            insertToJurnalModal(Integer.valueOf(txtJ.getText()), date1, nochartmodal,
                                    debitmodal, kreditmodal, description);
                        } else if (tblModal.getRowCount() > 0) {
                            double saldo = Double.parseDouble(tblModal.getValueAt(tblModal.getModel().getRowCount() - 1, 6).toString());
                            saldo = saldo + debitcash;
                            insertModal(date1, Integer.valueOf(txtJ.getText()), nochartcash,
                                    debitcash, kreditcash, saldo, descriptioncash);
                            insertToJurnal(Integer.valueOf(txtJ.getText()), date1, nochartcash,
                                    debitcash, kreditcash, description);
                            double debitmodal = 0;
                            double kreditmodal = Double.parseDouble(txtJumlah.getText());
                            insertToJurnalModal(Integer.valueOf(txtJ.getText()), date1, nochartmodal,
                                    debitmodal, kreditmodal, description);
                        }

                        loadAllDatabase(Integer.valueOf(txtNoChart.getText()), 3030, descriptioncash);
                        setLastValueOfKodeJurnal();
                    }
                } else if (txtNoChart.getText().equals("1020")) {
                    int nochartbank = 1020;
                    int nochartmodal = 3010;
                    double debitbank = Double.parseDouble(txtJumlah.getText());
                    double kreditbank = 0;
                    String description = "Add " + txtNamaChart.getText();
                    String descriptionbank = txtNamaChart.getText();
                    if (txtNamaChart.getText().equals("") || txtNoChart.getText().equals("")) {
                        util.Sutil.mse(this, "No.chart and Nama chart must not empty !");
                    } else {
                        if (tblModal.getRowCount() == 0) {
                            double saldo = debitbank;
                            insertModal(date1, Integer.valueOf(txtJ.getText()), nochartbank,
                                    debitbank, kreditbank, saldo, descriptionbank);
                            insertToJurnal(Integer.valueOf(txtJ.getText()), date1, nochartbank,
                                    debitbank, kreditbank, description);
                            double debitmodal = 0;
                            double kreditmodal = Double.parseDouble(txtJumlah.getText());
                            insertToJurnalModal(Integer.valueOf(txtJ.getText()), date1, nochartmodal,
                                    debitmodal, kreditmodal, description);
                        } else if (tblModal.getRowCount() > 0) {
                            double saldo = Double.parseDouble(tblModal.getValueAt(tblModal.getModel().getRowCount() - 1, 6).toString());
                            saldo = saldo + debitbank;
                            insertModal(date1, Integer.valueOf(txtJ.getText()), nochartbank,
                                    debitbank, kreditbank, saldo, descriptionbank);
                            insertToJurnal(Integer.valueOf(txtJ.getText()), date1, nochartbank,
                                    debitbank, kreditbank, description);
                            double debitmodal = 0;
                            double kreditmodal = Double.parseDouble(txtJumlah.getText());
                            insertToJurnalModal(Integer.valueOf(txtJ.getText()), date1, nochartmodal,
                                    debitmodal, kreditmodal, description);
                        }
                    }
                    loadAllDatabase(Integer.valueOf(txtNoChart.getText()), 3030, descriptionbank);
                    setLastValueOfKodeJurnal();
                } else if (txtNoChart.getText().equals("3030")) {
                    int nochartprive = 3030;
                    int nochartmodal = 3010;
                    double debitprive = 0;
                    double kreditprive = Double.parseDouble(txtJumlah.getText());
                    String description = "Prive from " + txtFrom.getText();
                    String descriptionprive = txtFrom.getText();
                    if (txtNamaChart.getText().equals("") || txtNoChart.getText().equals("") || 
                            txtnochartfrom.getText().equals("") || txtFrom.getText().equals("")) {
                        util.Sutil.mse(this, "Field from must not empty");
                    } else {
                        if (tblModal.getRowCount() == 0) {
                            double saldo = debitprive;
                            insertPrive(date1, Integer.valueOf(txtJ.getText()), nochartprive,
                                    debitprive, kreditprive, saldo, descriptionprive);
                            double debitmodal = Double.parseDouble(txtJumlah.getText());
                            double kreditmodal = 0;
                            insertToJurnalModal(Integer.valueOf(txtJ.getText()), date1, nochartmodal,
                                    debitmodal, kreditmodal, description);
                            insertToJurnalPrive(Integer.valueOf(txtJ.getText()), date1, nochartprive,
                                    debitprive, kreditprive, description);

                        } else if (tblModal.getRowCount() > 0) {
                            double saldo = Double.parseDouble(tblModal.getValueAt(tblModal.getModel().getRowCount()
                                    - tblModal.getRowCount(), 6).toString());
                            saldo = saldo - kreditprive;
                            insertPrive(date1, Integer.valueOf(txtJ.getText()), nochartprive,
                                    debitprive, kreditprive, saldo, descriptionprive);
                            double debitmodal = Double.parseDouble(txtJumlah.getText());
                            double kreditmodal = 0;
                            insertToJurnalModal(Integer.valueOf(txtJ.getText()), date1, nochartmodal,
                                    debitmodal, kreditmodal, description);
                            insertToJurnalPrive(Integer.valueOf(txtJ.getText()), date1, nochartprive,
                                    debitprive, kreditprive, description);
                        }
                    }

                    loadPrive(Integer.valueOf(txtNoChart.getText()), Integer.valueOf(txtnochartfrom.getText()), txtFrom.getText());
                    setLastValueOfKodeJurnal();
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(frmModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void executeNew() {
        getLastValueOfKodeJurnal();
        txtJumlah.setText("");
        btnSearchFrom.setEnabled(false);
        txtNoChart.setText("");
        txtNamaChart.setText("");
        txtFrom.setText("");
        txtnochartfrom.setText("");
        btnSearch.setEnabled(true);
        removeTableData();
    }

    public static void pilihAkun(int nochart, String namachart) {
        if (!btnSearchFrom.isEnabled()) {
            txtNoChart.setText(String.valueOf(nochart));
            txtNamaChart.setText(namachart);
        } else if (btnSearchFrom.isEnabled()) {
            txtnochartfrom.setText(String.valueOf(nochart));
            txtFrom.setText(namachart);
        }

    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblModal.getModel();
        tableModel.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    public static javax.swing.JButton btnSearchFrom;
    private com.toedter.calendar.JDateChooser dtcDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblModal;
    public static javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtJ;
    private javax.swing.JTextField txtJumlah;
    public static javax.swing.JTextField txtNamaChart;
    public static javax.swing.JTextField txtNoChart;
    public static javax.swing.JTextField txtnochartfrom;
    // End of variables declaration//GEN-END:variables
}
