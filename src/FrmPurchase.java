
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author COMPUTER
 */
public class FrmPurchase extends javax.swing.JFrame {

    Connection conn;

    /**
     * Creates new form FrmPurchase
     */
    public FrmPurchase(Connection conn) {
        this.conn = conn;
        initComponents();
        getLastValueOfKodeJurnal();
        txtDisc.setText(String.valueOf(0));
        setLocationRelativeTo(null);
    }

    private void updateModal(double cash, int kodejurnal) {
        try {
            if (conn != null) {
                String sql = "UPDATE modal set saldo = saldo - ? where kode_jurnal = ?;";

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setDouble(1, cash);
                pstatement.setInt(2, kodejurnal);
                pstatement.executeUpdate();
                System.out.println("Updated purchase modal");
                pstatement.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void insertModal(Date tanggal, int kodejurnal, int chartno, double debit, double kredit) {
        try {
            if (conn != null) {
                String sql = "insert into modal (tanggal, kode_jurnal, chartno, debit, kredit) values (?,?,?,?,?);";

                java.sql.Date sqlDate = new java.sql.Date(tanggal.getTime());

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setDate(1, sqlDate);
                pstatement.setInt(2, kodejurnal);
                pstatement.setInt(3, chartno);
                pstatement.setDouble(4, debit);
                pstatement.setDouble(5, kredit);

                pstatement.executeUpdate();
                System.out.println("insert modal");
                pstatement.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void updateInventory(int qty, int kodeproduk) {
        try {
            if (conn != null) {
                String sql = "update inventory set masuk = masuk + ? where kode_produk = ? ;";

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setInt(1, qty);
                pstatement.setInt(2, kodeproduk);

                pstatement.executeUpdate();
                System.out.println("Insert into inventory");
                pstatement.close();

            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void updateStock(int qty, int stock, String item) {
        try {
            if (conn != null) {
                String sql = "UPDATE stock set jumlahpembelian = jumlahpembelian + ?, stock = stock + ? "
                        + "where nama_produk like ?;";

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setInt(1, qty);
                pstatement.setInt(2, qty);
                pstatement.setString(3, "%" + item + "%");

                pstatement.executeUpdate();
                System.out.println("Updated purchase stock");
                pstatement.close();

            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void insertToJurnalPPN(int kodejurnal, Date tanggal, int chartno, double debit, double kredit, String description) {
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
            System.out.println("PPN record insert.");

            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(dlgRegister.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void insertToJurnalDisc(int kodejurnal, Date tanggal, int chartno, double debit, double kredit, String description) {
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
            System.out.println("Disc record insert.");

            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(dlgRegister.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("Cash record insert.");

            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(dlgRegister.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void insertToJurnalPurchase(int kodejurnal, Date tanggal, int chartno, double debit, double kredit, String description) {
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
            System.out.println("Purchase record insert.");

            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(dlgRegister.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void insertToPembelian(Date tanggalpembelian, int idpembelian, int idsupplier, int kode_produk, int qty,
            double subtotal, double disc, double ppn, double grandtotal) {
        try {

            String sql = "insert into pembelian (tanggalpembelian, idpembelian, idsupplier, kode_produk, qty"
                    + ", subtotal, kodediskon, kodepajak, grandtotal) values (?,?,?,?,?,?,?,?,?); ";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            java.sql.Date sqlDate = new java.sql.Date(tanggalpembelian.getTime());
            pstatement.setDate(1, sqlDate);
            pstatement.setInt(2, idpembelian);
            pstatement.setInt(3, idsupplier);
            pstatement.setInt(4, kode_produk);
            pstatement.setInt(5, qty);
            pstatement.setDouble(6, subtotal);
            pstatement.setDouble(7, disc);
            pstatement.setDouble(8, ppn);
            pstatement.setDouble(9, grandtotal);

            pstatement.executeUpdate();
            System.out.println("Purchase record insert to pembelian.");

            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(dlgRegister.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setLastValueOfKodeJurnal() {
        try {
            if (conn != null) {
                int kodejurnal = Integer.parseInt(txtJ.getText());
                kodejurnal++;

                int idpembelian = Integer.parseInt(txtKode.getText());
                idpembelian++;

                String sql = "UPDATE lastvalue set idpembelian = ?, kodejurnal = ? "
                        + "where id = 1;";

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setInt(1, idpembelian);
                pstatement.setInt(2, kodejurnal);

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
                String sql = "select idpembelian, kodejurnal from lastvalue;";
                PreparedStatement pstatement = conn.prepareStatement(sql);
                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int idpembelian = rs.getInt("idpembelian");
                        txtKode.setText(String.valueOf(idpembelian));

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
            Logger.getLogger(FrmPurchase.class.getName()).log(Level.SEVERE, null, ex);
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
        txtKode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        txtItem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSearchItem = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPPN = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDisc = new javax.swing.JTextField();
        btnCode = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        dtcDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMain = new javax.swing.JTable();
        txtAllTotal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cboPayment = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        btnCalculate = new javax.swing.JButton();
        txtIdSupplier = new javax.swing.JTextField();
        txtkodeproduk = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtJ = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Purchase");
        setIconImages(null);

        jLabel1.setText("Kode :");

        txtKode.setEnabled(false);

        jLabel5.setText("Qty");

        txtItem.setEnabled(false);

        jLabel6.setText("Item");

        btnSearchItem.setText("Search Item");
        btnSearchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchItemActionPerformed(evt);
            }
        });

        jLabel7.setText("Price");

        jLabel8.setText("Total");

        txtTotal.setEnabled(false);

        jLabel9.setText("PPN");

        txtPPN.setEnabled(false);

        jLabel11.setText("Disc");

        txtDisc.setEnabled(false);
        txtDisc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscActionPerformed(evt);
            }
        });

        btnCode.setText("Code");
        btnCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodeActionPerformed(evt);
            }
        });

        jLabel10.setText("Sub Total");

        txtSubTotal.setEnabled(false);

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel3.setText("Date : ");

        jLabel4.setText("Name : ");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        tblMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Qty", "Item", "Total", "Disc", "PPN", "Sub Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
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
        tblMain.setColumnSelectionAllowed(true);
        tblMain.setShowHorizontalLines(false);
        tblMain.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblMain);
        tblMain.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        txtAllTotal.setEnabled(false);

        jLabel12.setText("Grand Total");

        cboPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Transfer", "Kredit", "Giro" }));

        jLabel13.setText("Payment :");

        btnCalculate.setText("Calculate");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });

        txtIdSupplier.setEnabled(false);

        txtkodeproduk.setEnabled(false);

        jLabel2.setText("J -");

        txtJ.setEnabled(false);

        jLabel14.setText("Stock : ");

        txtStock.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAllTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtkodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearchItem))
                            .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel8)
                                .addGap(106, 106, 106)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDisc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPPN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCalculate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtJ, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(dtcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(156, 156, 156)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtIdSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cboPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(txtIdSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dtcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(txtkodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchItem)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(btnCode)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalculate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAllTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchItemActionPerformed
        // TODO add your handling code here:
        dlgSearchItemSupplier item = new dlgSearchItemSupplier(this, true, conn);
        item.setVisible(true);
    }//GEN-LAST:event_btnSearchItemActionPerformed

    private void btnCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodeActionPerformed
        // TODO add your handling code here:
        dlgSearchDiscSupplier disc = new dlgSearchDiscSupplier(this, true, conn);
        disc.setVisible(true);
    }//GEN-LAST:event_btnCodeActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        executeOk();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        dlgSearchSupplier supplier = new dlgSearchSupplier(this, true, conn);
        supplier.setVisible(true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        executeNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        saveJurnalPembelian();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        // TODO add your handling code here:
        if (txtQty.getText().equals("") || txtItem.getText().equals("") || txtPrice.getText().equals("")) {
            util.Sutil.mse(this, "Qty, Item and Price cannot empty !");
        } else if (txtQty.getText().equals("")) {
            util.Sutil.mse(this, "Insert Qty !");
        } else if (txtItem.getText().equals("")) {
            util.Sutil.mse(this, "Choose Item !");
        } else if (txtPrice.getText().equals("")) {
            util.Sutil.mse(this, "Insert price");
        } else {
            int qty = Integer.parseInt(txtQty.getText());
            double price = Double.parseDouble(txtPrice.getText());
            double disc = Double.parseDouble(txtDisc.getText());
            double total = qty * price;
            txtTotal.setText(String.valueOf(total));
            double ppn = (total - (total * disc)) * 0.1;
            txtPPN.setText(String.valueOf(ppn));
            double alltotal = total - (total * disc) + ppn;
            txtSubTotal.setText(String.valueOf(alltotal));
        }
    }//GEN-LAST:event_btnCalculateActionPerformed

    private void txtDiscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiscActionPerformed

    private void executeOk() {
        try {
            if (dtcDate.getDate() == null) {
                util.Sutil.mse(this, "Pilih tanggal !");
            } else if (dtcDate.getDate() != null) {
                double total = Double.valueOf(txtTotal.getText());
                double disc = total * Double.valueOf(txtDisc.getText());

                DefaultTableModel tableModel = (DefaultTableModel) tblMain.getModel();
                Object data[] = {
                    Integer.valueOf(txtKode.getText()),
                    Integer.valueOf(txtQty.getText()),
                    txtItem.getText(),
                    Double.parseDouble(txtTotal.getText()),
                    disc,
                    Double.parseDouble(txtPPN.getText()),
                    Double.parseDouble(txtSubTotal.getText())
                };
                tableModel.addRow(data);

                // -- tabel pembelian -- //
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String date = sdf.format(dtcDate.getDate());
                Date date1 = sdf.parse(date);
                int idpembelian = 1000 + Integer.parseInt(txtKode.getText());

                insertToPembelian(date1, idpembelian, Integer.parseInt(txtIdSupplier.getText()),
                        Integer.parseInt(txtkodeproduk.getText()), Integer.parseInt(txtQty.getText()),
                        Double.parseDouble(txtTotal.getText()), Double.parseDouble(txtDisc.getText()),
                        Double.parseDouble(txtPPN.getText()), Double.parseDouble(txtSubTotal.getText()));
                updateStock(Integer.valueOf(txtQty.getText()), Integer.valueOf(txtStock.getText()), txtItem.getText());

                // -- clear -- //
                txtQty.setText("");
                txtItem.setText("");
                txtPrice.setText("");
                txtTotal.setText("");
                txtPPN.setText("");
                txtSubTotal.setText("");
                // -- -- //

                grandTotal();

            }
        } catch (ParseException ex) {
            Logger.getLogger(FrmPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private double grandTotal() {
        double grandtotal = 0;
        int i = 0;
        if (tblMain.getRowCount() >= 0) {
            for (i = 0; i < tblMain.getRowCount(); i++) {
                grandtotal += Double.valueOf(tblMain.getValueAt(i, 6).toString());
                txtAllTotal.setText(String.valueOf(grandtotal));
            }
            i = 0;
        }
        return grandtotal;
    }

    private void saveInventory() {
        for (int i = 0; i < tblMain.getRowCount(); i++) {
            int qty = Integer.valueOf(tblMain.getValueAt(i, 1).toString());
            updateInventory(qty, Integer.valueOf(txtkodeproduk.getText()));
        }
    }

    private void saveJurnalPembelian() {
        if (dtcDate.getDate() == null) {
            util.Sutil.mse(this, "Pilih tanggal !");
        } else if (dtcDate.getDate() != null) {
            if (cboPayment.getSelectedItem().equals("Cash")) {
                if (!txtDisc.getText().equals("")) {
                    saveJurnalCash();
                } else {
                    saveJurnalCash();
                }
            } else if (cboPayment.getSelectedItem().equals("Transfer")) {
                if (!txtDisc.getText().equals("")) {
                    saveJurnalTransfer();
                } else {
                    saveJurnalTransfer();
                }
            } else if (cboPayment.getSelectedItem().equals("Kredit")) {
                if (!txtDisc.getText().equals("")) {
                    saveJurnalKredit();
                } else {
                    saveJurnalKredit();
                }
            } else if (cboPayment.getSelectedItem().equals("Giro")) {
                if (!txtDisc.getText().equals("")) {
                    saveJurnalGiro();
                } else {
                    saveJurnalGiro();
                }
            }
        }
    }

    private void saveJurnalCash() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(dtcDate.getDate());
            Date date1 = sdf.parse(date);

            // -- jurnal -- //
            int nochartpurchase = 5110;
            int nochartppn = 1080;
            int nochartdisc = 5120;
            int nochartcash = 1010;
            int kodejurnal = Integer.parseInt(txtJ.getText());

            String description = "Purchase from " + txtName.getText();

            // -- purchase -- //
            for (int i = 0; i < tblMain.getRowCount(); i++) {
                double debitpurchase = 0;
                debitpurchase += Double.valueOf(tblMain.getValueAt(i, 3).toString());
                double kreditpurchase = 0;
                insertToJurnalPurchase(kodejurnal, date1, nochartpurchase, debitpurchase, kreditpurchase, description);

                // -- PPN -- //
                double debitppn = 0;
                debitppn += Double.valueOf(tblMain.getValueAt(i, 5).toString());
                double kreditppn = 0;
                insertToJurnalPPN(kodejurnal, date1, nochartppn, debitppn, kreditppn, description);

                // -- disc -- //
                double debitdisc = 0;
                double kreditdisc = 0;
                kreditdisc += Double.valueOf(tblMain.getValueAt(i, 4).toString());
                insertToJurnalDisc(kodejurnal, date1, nochartdisc, debitdisc, kreditdisc, description);

                // -- cash -- //
                double debitcash = 0;
                double kreditcash = debitpurchase - kreditdisc + debitppn;
                insertToJurnal(kodejurnal, date1, nochartcash, debitcash, kreditcash, description);

                saveInventory();
                insertModal(date1, kodejurnal, nochartcash, debitcash, kreditcash);
                updateModal(kreditcash, kodejurnal);
            }
            setLastValueOfKodeJurnal();

        } catch (ParseException ex) {
            Logger.getLogger(FrmPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveJurnalTransfer() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(dtcDate.getDate());
            Date date1 = sdf.parse(date);
            

            // -- jurnal -- //
            int nochartpurchase = 5110;
            int nochartppn = 1080;
            int nochartdisc = 5120;
            int nochartTT = 1020;
            int kodejurnal = Integer.parseInt(txtJ.getText());

            String description = "Purchase from " + txtName.getText();

            // -- purchase -- //
            for (int i = 0; i < tblMain.getRowCount(); i++) {
                double debitpurchase = 0;
                debitpurchase += Double.valueOf(tblMain.getValueAt(i, 3).toString());
                double kreditpurchase = 0;
                insertToJurnalPurchase(kodejurnal, date1, nochartpurchase, debitpurchase, kreditpurchase, description);

                // -- PPN -- //
                double debitppn = 0;
                debitppn += Double.valueOf(tblMain.getValueAt(i, 5).toString());
                double kreditppn = 0;
                insertToJurnalPPN(kodejurnal, date1, nochartppn, debitppn, kreditppn, description);

                // -- disc -- //
                double debitdisc = 0;
                double kreditdisc = 0;
                kreditdisc += Double.valueOf(tblMain.getValueAt(i, 4).toString());
                insertToJurnalDisc(kodejurnal, date1, nochartdisc, debitdisc, kreditdisc, description);

                // -- transfer (TT) -- //
                double debitTT = 0;
                double kreditTT = debitpurchase - kreditdisc + debitppn;
                insertToJurnal(kodejurnal, date1, nochartTT, debitTT, kreditTT, description);

                saveInventory();
                insertModal(date1, kodejurnal, nochartTT, debitTT, kreditTT);
                updateModal(kreditTT, kodejurnal);
            }
            setLastValueOfKodeJurnal();
        } catch (ParseException ex) {
            Logger.getLogger(FrmPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveJurnalKredit() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(dtcDate.getDate());
            Date date1 = sdf.parse(date);

            int nochartpurchase = 5110;
            int nochartppn = 1080;
            int nochartdisc = 5120;
            int nochartpayable = 2020;
            int kodejurnal = Integer.parseInt(txtKode.getText());

            String description = "Purchase from " + txtName.getText();

            // -- purchase -- //
            for (int i = 0; i < tblMain.getRowCount(); i++) {
                double debitpurchase = 0;
                debitpurchase += Double.valueOf(tblMain.getValueAt(i, 3).toString());
                double kreditpurchase = 0;
                insertToJurnalPurchase(kodejurnal, date1, nochartpurchase, debitpurchase, kreditpurchase, description);

                // -- PPN -- //
                double debitppn = 0;
                debitppn += Double.valueOf(tblMain.getValueAt(i, 5).toString());
                double kreditppn = 0;
                insertToJurnalPPN(kodejurnal, date1, nochartppn, debitppn, kreditppn, description);

                // -- disc -- //
                double debitdisc = 0;
                double kreditdisc = 0;
                kreditdisc += Double.valueOf(tblMain.getValueAt(i, 4).toString());
                insertToJurnalDisc(kodejurnal, date1, nochartdisc, debitdisc, kreditdisc, description);

                // -- account payable(ap) -- //
                double debitap = 0;
                double kreditap = debitpurchase - kreditdisc + debitppn;
                insertToJurnal(kodejurnal, date1, nochartpayable, debitap, kreditap, description);

                saveInventory();
            }
            setLastValueOfKodeJurnal();
        } catch (ParseException ex) {
            Logger.getLogger(FrmPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveJurnalGiro() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(dtcDate.getDate());
            Date date1 = sdf.parse(date);

            int nochartpurchase = 5110;
            int nochartppn = 1080;
            int nochartdisc = 5120;
            int nochartgp = 2030;
            int kodejurnal = Integer.parseInt(txtKode.getText());

            String description = "Purchase from " + txtName.getText();

            // -- purchase -- //
            for (int i = 0; i < tblMain.getRowCount(); i++) {
                double debitpurchase = 0;
                debitpurchase += Double.valueOf(tblMain.getValueAt(i, 3).toString());
                double kreditpurchase = 0;
                insertToJurnalPurchase(kodejurnal, date1, nochartpurchase, debitpurchase, kreditpurchase, description);

                // -- PPN -- //
                double debitppn = 0;
                debitppn += Double.valueOf(tblMain.getValueAt(i, 5).toString());
                double kreditppn = 0;
                insertToJurnalPPN(kodejurnal, date1, nochartppn, debitppn, kreditppn, description);

                // -- disc -- //
                double debitdisc = 0;
                double kreditdisc = 0;
                kreditdisc += Double.valueOf(tblMain.getValueAt(i, 4).toString());
                insertToJurnalDisc(kodejurnal, date1, nochartdisc, debitdisc, kreditdisc, description);

                // -- giro payable(gp) -- //
                double debitgp = 0;
                double kreditgp = debitpurchase - kreditdisc + debitppn;
                insertToJurnal(kodejurnal, date1, nochartgp, debitgp, kreditgp, description);

                saveInventory();
            }
            setLastValueOfKodeJurnal();
        } catch (ParseException ex) {
            Logger.getLogger(FrmPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void executeNew() {
        txtIdSupplier.setText("");
        txtName.setText("");
        txtQty.setText("");
        txtStock.setText("");
        txtkodeproduk.setText("");
        txtItem.setText("");
        txtPrice.setText("");
        txtDisc.setText(String.valueOf(0));
        txtTotal.setText("");
        txtPPN.setText("");
        txtSubTotal.setText("");
        txtAllTotal.setText("");
        getLastValueOfKodeJurnal();
        removeTableData();
    }

    public static void pilihName(String name, int idsupplier) {
        txtName.setText(name);
        txtIdSupplier.setText(String.valueOf(idsupplier));
    }

    public static void pilihItem(String item, String price, int kodeproduk, int stock) {
        txtItem.setText(item);
        txtPrice.setText(String.valueOf(price));
        txtkodeproduk.setText(String.valueOf(kodeproduk));
        txtStock.setText(String.valueOf(stock));
    }

    public static void pilihDisc(double disc) {
        txtDisc.setText(String.valueOf(disc));
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblMain.getModel();
        tableModel.setRowCount(0);
    }

//    @Override
//    public void dispose() {
//        executeExit();
//    }
//
//    private void executeExit() {
//        if (util.Sutil.msq(this, "Are you sure to exit ? ") == 0) {
//            FrmPurchase
//        } else {
//
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnCode;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchItem;
    private javax.swing.JComboBox<String> cboPayment;
    private com.toedter.calendar.JDateChooser dtcDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMain;
    private javax.swing.JTextField txtAllTotal;
    public static javax.swing.JTextField txtDisc;
    public static javax.swing.JTextField txtIdSupplier;
    public static javax.swing.JTextField txtItem;
    private javax.swing.JTextField txtJ;
    private javax.swing.JTextField txtKode;
    public static javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPPN;
    public static javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    public static javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtkodeproduk;
    // End of variables declaration//GEN-END:variables
}
