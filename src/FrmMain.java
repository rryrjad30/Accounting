
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author COMPUTER
 */
public class FrmMain extends javax.swing.JFrame {

    private Connection conn;
    private boolean tm = true;

    /**
     * Creates new form FrmMain
     */
    public FrmMain(Connection conn) {
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
                System.out.println("Updated sales modal");
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
                String sql = "update inventory set keluar = keluar + ? where kode_produk = ? ;";
                
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
                String sql = "UPDATE stock set jumlahpenjualan = jumlahpenjualan + ?, stock = ? - jumlahpenjualan "
                        + "where nama_produk like ?;";

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setInt(1, qty);
                pstatement.setInt(2, stock);
                pstatement.setString(3, "%" + item + "%");

                pstatement.executeUpdate();
                System.out.println("Updated sales stock");
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

    private void insertToJurnalSales(int kodejurnal, Date tanggal, int chartno, double debit, double kredit, String description) {
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

    private void insertToTransaksiDetail(int nofaktur, int kode_produk, int qty, double subtotal, double kodepajak,
            double kodediskon, double grandtotal) {
        try {
            String sql = "insert into transaksidetail (nofaktur, kode_produk, qty, "
                    + "subtotal, kodepajak, kodediskon, grandtotal) values (?,?,?,?,?,?,?); ";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, nofaktur);
            pstatement.setInt(2, kode_produk);
            pstatement.setInt(3, qty);
            pstatement.setDouble(4, subtotal);
            pstatement.setDouble(5, kodepajak);
            pstatement.setDouble(6, kodediskon);
            pstatement.setDouble(7, grandtotal);

            pstatement.executeUpdate();
            System.out.println("Sales record insert to transaksi detail.");

            pstatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(dlgRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createTransaksiMaster(int nofaktur, Date tanggaltransaksi, int idcustomer) {
        try {
            String sql = "insert into transaksimaster (nofaktur, tanggaltransaksi, idcustomer) values (?,?,?); ";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            pstatement.setInt(1, nofaktur);
            java.sql.Date sqlDate = new java.sql.Date(tanggaltransaksi.getTime());
            pstatement.setDate(2, sqlDate);
            pstatement.setInt(3, idcustomer);

            pstatement.executeUpdate();
            System.out.println("Transaksi Master record insert.");

            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getStockQty(String namaproduk) {
        try {
            if (conn != null) {
                String sql = "select stock from stock where nama_produk  like ?;";
                PreparedStatement pstatement = conn.prepareStatement(sql);
                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        rs.getInt("stock");
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

    private void setLastValueOfKodeJurnal() {
        try {
            if (conn != null) {
                int idpenjualan = Integer.parseInt(txtKode.getText());
                idpenjualan++;

                int kodejurnal = Integer.parseInt(txtJ.getText());
                kodejurnal++;
                String sql = "UPDATE lastvalue set idpenjualan = ?, kodejurnal = ? "
                        + "where id = 1;";

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setInt(1, idpenjualan);
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
                String sql = "select idpenjualan, kodejurnal from lastvalue;";
                PreparedStatement pstatement = conn.prepareStatement(sql);
                ResultSet rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    while (rs.next()) {
                        int idpenjualan = rs.getInt("idpenjualan");
                        txtKode.setText(String.valueOf(idpenjualan));

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

        jLabel2 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dtcDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtItem = new javax.swing.JTextField();
        btnSearchItem = new javax.swing.JButton();
        txtPrice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPPN = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMain = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtDisc = new javax.swing.JTextField();
        btnCode = new javax.swing.JButton();
        btnCalculate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        txtAllTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cboPayment = new javax.swing.JComboBox<>();
        txtkodeproduk = new javax.swing.JTextField();
        txtIdCustomer = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtJ = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mniPurchase = new javax.swing.JMenuItem();
        mniKodeAkun = new javax.swing.JMenuItem();
        mniDiscCode = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniExit = new javax.swing.JMenuItem();
        mnuInput = new javax.swing.JMenu();
        mniCustomer = new javax.swing.JMenuItem();
        mniSupplier = new javax.swing.JMenuItem();
        mniItems = new javax.swing.JMenuItem();
        mniModal = new javax.swing.JMenuItem();
        mnuData = new javax.swing.JMenu();
        mniPurchase2 = new javax.swing.JMenuItem();
        mniSales = new javax.swing.JMenuItem();
        mniStock = new javax.swing.JMenuItem();
        mnuAccounting = new javax.swing.JMenu();
        mniJurnal = new javax.swing.JMenuItem();
        mniGL = new javax.swing.JMenuItem();
        mniTrialBalance = new javax.swing.JMenuItem();
        mniInventoryValue = new javax.swing.JMenuItem();
        mniProfitLoss = new javax.swing.JMenuItem();
        mniBalanceSheet = new javax.swing.JMenuItem();
        Settings = new javax.swing.JMenu();
        mniUser = new javax.swing.JMenuItem();
        mniAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transaction");
        setIconImages(null);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Kode :");

        txtKode.setEnabled(false);
        txtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Date : ");

        jLabel4.setText("Name : ");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel5.setText("Qty");

        jLabel6.setText("Item");

        txtItem.setEnabled(false);

        btnSearchItem.setText("Search Item");
        btnSearchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchItemActionPerformed(evt);
            }
        });

        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        jLabel7.setText("Price");

        jLabel8.setText("Total");

        txtTotal.setEnabled(false);

        jLabel9.setText("PPN");

        txtPPN.setEnabled(false);

        jLabel10.setText("Sub Total");

        txtSubTotal.setEnabled(false);

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

        jLabel11.setText("Disc");

        txtDisc.setEnabled(false);

        btnCode.setText("Code");
        btnCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodeActionPerformed(evt);
            }
        });

        btnCalculate.setText("Calculate");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });

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

        txtAllTotal.setEnabled(false);

        jLabel1.setText("Grand Total");

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel13.setText("Payment :");

        cboPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Transfer", "Kredit", "Giro" }));

        txtkodeproduk.setEnabled(false);

        txtIdCustomer.setEnabled(false);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("J - ");

        txtJ.setEnabled(false);
        txtJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJActionPerformed(evt);
            }
        });

        jLabel14.setText("Stock : ");

        txtStock.setEnabled(false);

        mnuFile.setText("File");

        mniPurchase.setText("Purchase");
        mniPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPurchaseActionPerformed(evt);
            }
        });
        mnuFile.add(mniPurchase);

        mniKodeAkun.setText("Akun Code");
        mniKodeAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKodeAkunActionPerformed(evt);
            }
        });
        mnuFile.add(mniKodeAkun);

        mniDiscCode.setText("Disc Code");
        mniDiscCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDiscCodeActionPerformed(evt);
            }
        });
        mnuFile.add(mniDiscCode);
        mnuFile.add(jSeparator1);

        mniExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mniExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit.png"))); // NOI18N
        mniExit.setText("Exit");
        mniExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniExitActionPerformed(evt);
            }
        });
        mnuFile.add(mniExit);

        jMenuBar1.add(mnuFile);

        mnuInput.setText("Input");

        mniCustomer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        mniCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bullet_user.png"))); // NOI18N
        mniCustomer.setText("Customer");
        mniCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCustomerActionPerformed(evt);
            }
        });
        mnuInput.add(mniCustomer);

        mniSupplier.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        mniSupplier.setText("Supplier");
        mniSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSupplierActionPerformed(evt);
            }
        });
        mnuInput.add(mniSupplier);

        mniItems.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        mniItems.setText("Item");
        mniItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniItemsActionPerformed(evt);
            }
        });
        mnuInput.add(mniItems);

        mniModal.setText("Modal");
        mniModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniModalActionPerformed(evt);
            }
        });
        mnuInput.add(mniModal);

        jMenuBar1.add(mnuInput);

        mnuData.setText("Data");

        mniPurchase2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mniPurchase2.setText("Purchase Data");
        mniPurchase2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPurchase2ActionPerformed(evt);
            }
        });
        mnuData.add(mniPurchase2);

        mniSales.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        mniSales.setText("Sales Data");
        mniSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSalesActionPerformed(evt);
            }
        });
        mnuData.add(mniSales);

        mniStock.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        mniStock.setText("Stock Data");
        mniStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniStockActionPerformed(evt);
            }
        });
        mnuData.add(mniStock);

        jMenuBar1.add(mnuData);

        mnuAccounting.setText("Akuntansi");

        mniJurnal.setText("Jurnal");
        mniJurnal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniJurnalActionPerformed(evt);
            }
        });
        mnuAccounting.add(mniJurnal);

        mniGL.setText("GL");
        mniGL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGLActionPerformed(evt);
            }
        });
        mnuAccounting.add(mniGL);

        mniTrialBalance.setText("Trial Balance");
        mniTrialBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTrialBalanceActionPerformed(evt);
            }
        });
        mnuAccounting.add(mniTrialBalance);

        mniInventoryValue.setText("Inventory Value");
        mniInventoryValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniInventoryValueActionPerformed(evt);
            }
        });
        mnuAccounting.add(mniInventoryValue);

        mniProfitLoss.setText("Profit-Loss");
        mniProfitLoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProfitLossActionPerformed(evt);
            }
        });
        mnuAccounting.add(mniProfitLoss);

        mniBalanceSheet.setText("Balance Sheet");
        mniBalanceSheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBalanceSheetActionPerformed(evt);
            }
        });
        mnuAccounting.add(mniBalanceSheet);

        jMenuBar1.add(mnuAccounting);

        Settings.setText("Settings");

        mniUser.setText("User");
        mniUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUserActionPerformed(evt);
            }
        });
        Settings.add(mniUser);

        mniAbout.setText("About");
        mniAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAboutActionPerformed(evt);
            }
        });
        Settings.add(mniAbout);

        jMenuBar1.add(Settings);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAllTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQty, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtkodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(btnSearchItem))
                            .addComponent(txtItem))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(47, 47, 47)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPPN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDisc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(70, 70, 70)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCode)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnCalculate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch)
                            .addComponent(txtIdCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(dtcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(btnSearchItem)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(btnCode)
                    .addComponent(txtkodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalculate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOk)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAllTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        executeNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        saveJurnalPembelian();

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        dlgSearchCustomer customer = new dlgSearchCustomer(this, true, conn);
        customer.setVisible(true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSearchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchItemActionPerformed
        // TODO add your handling code here:
        dlgSearchItemCustomer item = new dlgSearchItemCustomer(this, true, conn);
        item.setVisible(true);
    }//GEN-LAST:event_btnSearchItemActionPerformed

    private void btnCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodeActionPerformed
        // TODO add your handling code here:
        dlgSearchDiscCustomer disc = new dlgSearchDiscCustomer(this, true, conn);
        disc.setVisible(true);
    }//GEN-LAST:event_btnCodeActionPerformed

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

    private void mniSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSalesActionPerformed
        // TODO add your handling code here:
        data1.dlgSalesData sales = new data1.dlgSalesData(this, true, conn);
        sales.setVisible(true);
    }//GEN-LAST:event_mniSalesActionPerformed

    private void mniCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCustomerActionPerformed
        // TODO add your handling code here:
        data.dlgCustomer customer = new data.dlgCustomer(this, true, conn);
        customer.setVisible(true);
    }//GEN-LAST:event_mniCustomerActionPerformed

    private void mniPurchase2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPurchase2ActionPerformed
        // TODO add your handling code here:
        data1.dlgPurchaseData purchase = new data1.dlgPurchaseData(this, true, conn);
        purchase.setVisible(true);
    }//GEN-LAST:event_mniPurchase2ActionPerformed

    private void mniSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSupplierActionPerformed
        // TODO add your handling code here:
        data.dlgSupplier supplier = new data.dlgSupplier(this, true, conn);
        supplier.setVisible(true);
    }//GEN-LAST:event_mniSupplierActionPerformed

    private void mniJurnalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniJurnalActionPerformed
        // TODO add your handling code here:
        akuntansi.frmJurnal jurnal = new akuntansi.frmJurnal(conn);
        jurnal.setVisible(true);
    }//GEN-LAST:event_mniJurnalActionPerformed

    private void mniGLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGLActionPerformed
        // TODO add your handling code here:
        akuntansi.frmGL gl = new akuntansi.frmGL(conn);
        gl.setVisible(true);
    }//GEN-LAST:event_mniGLActionPerformed

    private void mniTrialBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTrialBalanceActionPerformed
        // TODO add your handling code here:
        akuntansi.frmTrialBalance trialbalance = new akuntansi.frmTrialBalance(conn);
        trialbalance.setVisible(true);
    }//GEN-LAST:event_mniTrialBalanceActionPerformed

    private void mniInventoryValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniInventoryValueActionPerformed
        // TODO add your handling code here:
        akuntansi.frmInventoryValue inventoryvalue = new akuntansi.frmInventoryValue(conn);
        inventoryvalue.setVisible(true);
    }//GEN-LAST:event_mniInventoryValueActionPerformed

    private void mniProfitLossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniProfitLossActionPerformed
        // TODO add your handling code here:
        akuntansi.frmProfitLoss profitloss = new akuntansi.frmProfitLoss(conn);
        profitloss.setVisible(true);
    }//GEN-LAST:event_mniProfitLossActionPerformed

    private void mniBalanceSheetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBalanceSheetActionPerformed
        // TODO add your handling code here:
        akuntansi.frmBalanceSheet balancesheet = new akuntansi.frmBalanceSheet(conn);
        balancesheet.setVisible(true);
    }//GEN-LAST:event_mniBalanceSheetActionPerformed

    private void mniExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExitActionPerformed
        // TODO add your handling code here:
        executeLogOut();
    }//GEN-LAST:event_mniExitActionPerformed

    private void mniDiscCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDiscCodeActionPerformed
        // TODO add your handling code here:
        dlgDisc disc = new dlgDisc(this, true, conn);
        disc.setVisible(true);
    }//GEN-LAST:event_mniDiscCodeActionPerformed

    private void mniKodeAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKodeAkunActionPerformed
        // TODO add your handling code here:
        dlgAkunCode code = new dlgAkunCode(this, true, conn);
        code.setVisible(true);
    }//GEN-LAST:event_mniKodeAkunActionPerformed

    private void txtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeActionPerformed

    private void mniUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUserActionPerformed
        // TODO add your handling code here:
        FrmUser user = new FrmUser(conn);
        user.setVisible(true);
    }//GEN-LAST:event_mniUserActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        executeOk();
    }//GEN-LAST:event_btnOkActionPerformed

    private void mniItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniItemsActionPerformed
        // TODO add your handling code here:
        data.dlgItem item = new data.dlgItem(this, true, conn);
        item.setVisible(true);
    }//GEN-LAST:event_mniItemsActionPerformed

    private void mniAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAboutActionPerformed
        // TODO add your handling code here:
        dlgAbout about = new dlgAbout(this, true);
        about.setVisible(true);
    }//GEN-LAST:event_mniAboutActionPerformed

    private void mniStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniStockActionPerformed
        // TODO add your handling code here:
        data1.dlgStockData stockdata = new data1.dlgStockData(this, true, conn);
        stockdata.setVisible(true);
    }//GEN-LAST:event_mniStockActionPerformed

    private void mniPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPurchaseActionPerformed
        // TODO add your handling code here:
        FrmPurchase frmpurchase = new FrmPurchase(conn);
        frmpurchase.setVisible(true);
    }//GEN-LAST:event_mniPurchaseActionPerformed

    private void txtJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJActionPerformed

    private void mniModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniModalActionPerformed
        // TODO add your handling code here:
        modal.frmModal modal = new modal.frmModal(conn);
        modal.setVisible(true);
    }//GEN-LAST:event_mniModalActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void executeOk() {
        int qty = Integer.parseInt(txtQty.getText());
        int stock = Integer.parseInt(txtStock.getText());
        try {
            if (dtcDate.getDate() == null) {
                util.Sutil.mse(this, "Pilih tanggal !");
            } else if (stock < qty) {
                util.Sutil.mse(this, "Stock tidak cukup!");
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

                int nofaktur = 1000 + Integer.parseInt(txtKode.getText());

                if (tm = true) {
                    createTransaksiMaster(nofaktur, date1, Integer.parseInt(txtIdCustomer.getText()));
                    tm = false;
                }
                insertToTransaksiDetail(nofaktur, Integer.parseInt(txtkodeproduk.getText()), Integer.parseInt(txtQty.getText()),
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
            Logger.getLogger(FrmPurchase.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            } else if (cboPayment.getSelectedItem().equals("Bank")) {
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
            int nochartsales = 4010;
            int nochartppn = 1050;
            int nochartdisc = 4020;
            int nochartcash = 1010;
            int kodejurnal = Integer.parseInt(txtJ.getText());

            // -- sales -- //
            for (int i = 0; i < tblMain.getRowCount(); i++) {

                // -- PPN -- //
                double debitppn = 0;
                double kreditppn = 0;
                kreditppn += Double.valueOf(tblMain.getValueAt(i, 5).toString());

                // -- sales -- //
                double debitsales = 0;
                double kreditsales = 0;
                kreditsales += Double.valueOf(tblMain.getValueAt(i, 3).toString());

                // -- disc -- //
                double debitdisc = 0;
                debitdisc += Double.valueOf(tblMain.getValueAt(i, 4).toString());
                double kreditdisc = 0;

                // -- cash -- //
                double debitcash = 0;
                debitcash += Double.valueOf(tblMain.getValueAt(i, 6).toString());
                double kreditcash = 0;

                String description = "Sales to " + txtName.getText();

                insertToJurnal(kodejurnal, date1, nochartcash, debitcash, kreditcash, description);
                insertToJurnalDisc(kodejurnal, date1, nochartdisc, debitdisc, kreditdisc, description);
                insertToJurnalSales(kodejurnal, date1, nochartsales, debitsales, kreditsales, description);
                insertToJurnalPPN(kodejurnal, date1, nochartppn, debitppn, kreditppn, description);

                saveInventory();
                insertModal(date1, kodejurnal, nochartsales, debitsales, kreditsales);
                updateModal(kreditsales, kodejurnal);
            }
            setLastValueOfKodeJurnal();

        } catch (ParseException ex) {
            Logger.getLogger(FrmPurchase.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveJurnalTransfer() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(dtcDate.getDate());
            Date date1 = sdf.parse(date);

            // -- jurnal -- //
            int nochartsales = 4010;
            int nochartppn = 1050;
            int nochartdisc = 4020;
            int nochartTT = 1020;
            int kodejurnal = Integer.parseInt(txtJ.getText());

            // -- sales -- //
            for (int i = 0; i < tblMain.getRowCount(); i++) {

                // -- PPN -- //
                double debitppn = 0;
                double kreditppn = 0;
                kreditppn += Double.valueOf(tblMain.getValueAt(i, 5).toString());

                // -- sales -- //
                double debitsales = 0;
                double kreditsales = 0;
                kreditsales += Double.valueOf(tblMain.getValueAt(i, 3).toString());

                // -- disc -- //
                double debitdisc = 0;
                debitdisc += Double.valueOf(tblMain.getValueAt(i, 4).toString());
                double kreditdisc = 0;

                // -- transfer (TT) -- //
                double debitTT = 0;
                debitTT += Double.valueOf(tblMain.getValueAt(i, 6).toString());
                double kreditTT = 0;

                String description = "Sales to " + txtName.getText();

                insertToJurnal(kodejurnal, date1, nochartTT, debitTT, kreditTT, description);
                insertToJurnalDisc(kodejurnal, date1, nochartdisc, debitdisc, kreditdisc, description);
                insertToJurnalSales(kodejurnal, date1, nochartsales, debitsales, kreditsales, description);
                insertToJurnalPPN(kodejurnal, date1, nochartppn, debitppn, kreditppn, description);
                
                saveInventory();
                insertModal(date1, kodejurnal, nochartsales, debitsales, kreditsales);
                updateModal(kreditsales, kodejurnal);
            }
            setLastValueOfKodeJurnal();

        } catch (ParseException ex) {
            Logger.getLogger(FrmPurchase.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveJurnalKredit() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(dtcDate.getDate());
            Date date1 = sdf.parse(date);

            int nochartsales = 4010;
            int nochartppn = 1050;
            int nochartdisc = 4020;
            int nochartreceiveable = 1030;
            int kodejurnal = Integer.parseInt(txtJ.getText());

            // -- sales -- //
            for (int i = 0; i < tblMain.getRowCount(); i++) {
                // -- sales -- //
                double debitsales = 0;
                double kreditsales = 0;
                kreditsales += Double.valueOf(tblMain.getValueAt(i, 3).toString());

                // -- PPN -- //
                double debitppn = 0;
                double kreditppn = 0;
                kreditppn += Double.valueOf(tblMain.getValueAt(i, 5).toString());

                // -- disc -- //
                double debitdisc = 0;
                debitdisc += Double.valueOf(tblMain.getValueAt(i, 4).toString());
                double kreditdisc = 0;

                // -- recieveable payable(rp) -- //
                double debitrp = 0;
                debitrp = kreditsales - debitdisc + kreditppn;
                double kreditrp = 0;

                String description = "Sales to " + txtName.getText();

                insertToJurnal(kodejurnal, date1, nochartreceiveable, debitrp, kreditrp, description);
                insertToJurnalDisc(kodejurnal, date1, nochartdisc, debitdisc, kreditdisc, description);
                insertToJurnalSales(kodejurnal, date1, nochartsales, debitsales, kreditsales, description);
                insertToJurnalPPN(kodejurnal, date1, nochartppn, debitppn, kreditppn, description);
                
                saveInventory();
                insertModal(date1, kodejurnal, nochartsales, debitsales, kreditsales);
                updateModal(kreditsales, kodejurnal);
            }
            setLastValueOfKodeJurnal();

        } catch (ParseException ex) {
            Logger.getLogger(FrmPurchase.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveJurnalGiro() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(dtcDate.getDate());
            Date date1 = sdf.parse(date);

            int nochartsales = 4010;
            int nochartppn = 1050;
            int nochartdisc = 4020;
            int nochartgr = 2030;
            int kodejurnal = Integer.parseInt(txtJ.getText());

            // -- sales -- //
            for (int i = 0; i < tblMain.getRowCount(); i++) {
                double debitsales = 0;
                double kreditsales = 0;
                kreditsales += Double.valueOf(tblMain.getValueAt(i, 3).toString());

                // -- PPN -- //
                double debitppn = 0;
                double kreditppn = 0;
                kreditppn += Double.valueOf(tblMain.getValueAt(i, 5).toString());

                // -- disc -- //
                double debitdisc = 0;
                debitdisc += Double.valueOf(tblMain.getValueAt(i, 4).toString());
                double kreditdisc = 0;

                // -- giro recieveable(gr) -- //
                double debitgr = 0;
                debitgr = debitsales - kreditdisc + debitppn;
                double kreditgr = 0;

                String description = "Sales to " + txtName.getText();

                insertToJurnal(kodejurnal, date1, nochartgr, debitgr, kreditgr, description);
                insertToJurnalDisc(kodejurnal, date1, nochartdisc, debitdisc, kreditdisc, description);
                insertToJurnalSales(kodejurnal, date1, nochartsales, debitsales, kreditsales, description);
                insertToJurnalPPN(kodejurnal, date1, nochartppn, debitppn, kreditppn, description);
            
                saveInventory();
                insertModal(date1, kodejurnal, nochartsales, debitsales, kreditsales);
                updateModal(kreditsales, kodejurnal);
            }
            setLastValueOfKodeJurnal();

        } catch (ParseException ex) {
            Logger.getLogger(FrmPurchase.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void executeLogOut() {
        this.setVisible(false);
        DlgLogin.instance.setVisible(true);
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblMain.getModel();
        tableModel.setRowCount(0);
    }

    private void executeNew() {
        txtIdCustomer.setText("");
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
        tm = true;
        getLastValueOfKodeJurnal();
        removeTableData();
    }

    public static void pilihName(String name, int idcustomer) {
        txtName.setText(name);
        txtIdCustomer.setText(String.valueOf(idcustomer));
    }

    public static void pilihItem(String item, double price, int kodeproduk, int stock) {
        txtItem.setText(item);
        txtPrice.setText(String.valueOf(price));
        txtkodeproduk.setText(String.valueOf(kodeproduk));
        txtStock.setText(String.valueOf(stock));
    }

    public static void pilihDisc(double disc) {
        txtDisc.setText(String.valueOf(disc));
    }

    @Override
    public void dispose() {
        executeExit();
    }

    private void executeExit() {
        if (util.Sutil.msq(this, "Are you sure to exit ? ") == 0) {
            System.exit(0);
        } else {

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Settings;
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
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem mniAbout;
    private javax.swing.JMenuItem mniBalanceSheet;
    private javax.swing.JMenuItem mniCustomer;
    private javax.swing.JMenuItem mniDiscCode;
    private javax.swing.JMenuItem mniExit;
    private javax.swing.JMenuItem mniGL;
    private javax.swing.JMenuItem mniInventoryValue;
    private javax.swing.JMenuItem mniItems;
    private javax.swing.JMenuItem mniJurnal;
    private javax.swing.JMenuItem mniKodeAkun;
    private javax.swing.JMenuItem mniModal;
    private javax.swing.JMenuItem mniProfitLoss;
    private javax.swing.JMenuItem mniPurchase;
    private javax.swing.JMenuItem mniPurchase2;
    private javax.swing.JMenuItem mniSales;
    private javax.swing.JMenuItem mniStock;
    private javax.swing.JMenuItem mniSupplier;
    private javax.swing.JMenuItem mniTrialBalance;
    private javax.swing.JMenuItem mniUser;
    private javax.swing.JMenu mnuAccounting;
    private javax.swing.JMenu mnuData;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuInput;
    private javax.swing.JTable tblMain;
    private javax.swing.JTextField txtAllTotal;
    public static javax.swing.JTextField txtDisc;
    public static javax.swing.JTextField txtIdCustomer;
    public static javax.swing.JTextField txtItem;
    private javax.swing.JTextField txtJ;
    private javax.swing.JTextField txtKode;
    public static javax.swing.JTextField txtName;
    public static javax.swing.JTextField txtPPN;
    public static javax.swing.JTextField txtPrice;
    public static javax.swing.JTextField txtQty;
    public static javax.swing.JTextField txtStock;
    public static javax.swing.JTextField txtSubTotal;
    public static javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtkodeproduk;
    // End of variables declaration//GEN-END:variables
}
