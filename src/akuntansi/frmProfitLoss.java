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
public class frmProfitLoss extends javax.swing.JFrame {

    private Connection conn;
    Integer endingvalue;

    /**
     * Creates new form frmProfitLoss
     */
    public frmProfitLoss(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void insertProfitLoss(Date tanggalfrom, Date tanggaltill, double profitloss) {
        try {
            if (conn != null) {
                String sql = "insert into profitloss (tanggalfrom, tanggaltill, profitloss) values (?,?,?);";

                java.sql.Date sqlDateFrom = new java.sql.Date(tanggalfrom.getTime());
                java.sql.Date sqlDateTill = new java.sql.Date(tanggaltill.getTime());
                
                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setDate(1, sqlDateFrom);
                pstatement.setDate(2, sqlDateTill);
                pstatement.setDouble(3, profitloss);

                pstatement.executeUpdate();
                System.out.println("insert modal");
                pstatement.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void loadSalesIncome(Date from, Date till) {
        try {
            String sql = "select ac.chartname, SUM(j.kredit)- SUM(j.debit) as salesincome from jurnal j inner join accountchart ac on "
                    + "j.chartno = ac.chartno where j.chartno = 4010 and tanggal between ? and ?;  ";

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblSalesRevenue.getModel();

                    Object data[] = {
                        rs.getString("chartname"),
                        rs.getDouble("salesincome")
                    };
                    if (rs.getDouble("salesincome") == 0) {

                    } else {
                        tableModel.addRow(data);
                    }
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

    private void loadSalesDiscount(Date from, Date till) {
        try {
            String sql = "select ac.chartname, SUM(j.debit)- SUM(j.kredit) as salesdiscount from jurnal j inner join accountchart ac on "
                    + "j.chartno = ac.chartno where j.chartno = 4020 and tanggal between ? and ?;  ";

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblSalesRevenue.getModel();

                    Object data[] = {
                        rs.getString("chartname"),
                        rs.getDouble("salesdiscount")
                    };
                    if (rs.getDouble("salesdiscount") == 0) {

                    } else {
                        tableModel.addRow(data);
                    }
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

    private void loadSalesReturn(Date from, Date till) {
        try {
            String sql = "select ac.chartname, SUM(j.debit)- SUM(j.kredit) as salesreturn from jurnal j inner join accountchart ac on "
                    + "j.chartno = ac.chartno where j.chartno = 4030 and tanggal between ? and ?;  ";

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblSalesRevenue.getModel();

                    Object data[] = {
                        rs.getString("chartname"),
                        rs.getDouble("salesreturn")
                    };
                    if (rs.getDouble("salesreturn") == 0) {

                    } else {
                        tableModel.addRow(data);
                    }
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

//    private void loadOthersIncome(Date from, Date till) {
//        try {
//            String sql = "select ac.chartname, SUM(j.debit)- SUM(j.kredit) as othersincome from jurnal j inner join accountchart ac on "
//                    + "j.chartno = ac.chartno where j.chartno = 7010 and tanggal between ? and ?;  ";
//
//            java.sql.Date datefrom = new java.sql.Date(from.getTime());
//            java.sql.Date datetill = new java.sql.Date(till.getTime());
//
//            PreparedStatement pstatement = conn.prepareStatement(sql);
//            pstatement.setDate(1, datefrom);
//            pstatement.setDate(2, datetill);
//
//            ResultSet rs = pstatement.executeQuery();
//            if (rs.isBeforeFirst()) { // check is resultset not empty
//                while (rs.next()) {
//                    DefaultTableModel tableModel = (DefaultTableModel) tblSalesRevenue.getModel();
//
//                    Object data[] = {
//                        rs.getString("chartname"),
//                        rs.getDouble("othersincome")
//                    };
//                    if (rs.getDouble("othersincome") == 0) {
//
//                    } else {
//                        tableModel.addRow(data);
//                    }
//                }
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

    private void loadBeginningInventory(Date from, Date till) {
        try {
            String sql = "select ac.chartname, tb.opening as beginninginventory from "
                    + "accountchart ac inner join trialbalance tb on ac.chartno = tb.chartno "
                    + "inner join jurnal j on tb.chartno = j.chartno "
                    + "where j.tanggal between ? and ? and tb.chartno = 1040;  ";

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {

                }
            } else {
                DefaultTableModel tableModel = (DefaultTableModel) tblCOGS.getModel();
                int opening = 0;
                String chartname = "Beginning Inventory";
                Object data[] = {
                    chartname,
                    opening
                };
                tableModel.addRow(data);
            }
            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPurchasing(Date from, Date till) {
        try {
            String sql = "select ac.chartname, SUM(j.debit)- SUM(j.kredit) as purchasing from jurnal j inner join "
                    + "accountchart ac on j.chartno = ac.chartno where j.chartno = 5110 and tanggal between ? and ?;  ";

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblCOGS.getModel();

                    Object data[] = {
                        rs.getString("chartname"),
                        rs.getDouble("purchasing")
                    };
                    if (rs.getDouble("purchasing") == 0) {

                    } else {
                        tableModel.addRow(data);
                    }
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

    private void loadPurchasingDisc(Date from, Date till) {
        try {
            String sql = "select ac.chartname, SUM(j.debit)- SUM(j.kredit) as purchasingdisc from jurnal j"
                    + " inner join accountchart ac on j.chartno = ac.chartno where j.chartno = 5120 "
                    + "and tanggal between ? and ?;  ";

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblCOGS.getModel();

                    Object data[] = {
                        rs.getString("chartname"),
                        rs.getDouble("purchasingdisc")
                    };
                    if (rs.getDouble("purchasingdisc") == 0) {

                    } else {
                        tableModel.addRow(data);
                    }
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

//    private void loadEndingInventory(Date from, Date till) {
//        try {
////            String sql = "select sum(stock * harga_beli) as value from stock ;";
//
//            String sql = "select sum(j.kredit) as kredit, sum(j.debit) as debit, kredit - debit as valueending "
//                    + "from jurnal j where j.kredit = 4010 and j.debit = 5110 and tanggal between ? and ?";
//            java.sql.Date datefrom = new java.sql.Date(from.getTime());
//            java.sql.Date datetill = new java.sql.Date(till.getTime());
//
//            PreparedStatement pstatement = conn.prepareStatement(sql);
//            pstatement.setDate(1, datefrom);
//            pstatement.setDate(2, datetill);
//
//            ResultSet rs = pstatement.executeQuery();
//            if (rs.isBeforeFirst()) { // check is resultset not empty
//                while (rs.next()) {
//                    DefaultTableModel tableModel = (DefaultTableModel) tblCOGS.getModel();
//                    String chartname = "Ending Inventory";
//                    Object data[] = {
//                        chartname,
//                        rs.getDouble("valueending")
//                    };
//
//                }
//            } else {
//                Sutil.mse(this, "Record empty!");
//            }
//            rs.close();
//            pstatement.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private void loadGeneralExpenditure(Date from, Date till) {
        try {
            String sql = "select ac.chartname,SUM(j.debit)-SUM(j.kredit) as generalexpenditure from jurnal j inner join"
                    + " accountchart ac on j.chartno = ac.chartno where j.chartno = 6010 and tanggal between ? and ?;  ";

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblExpenditure.getModel();

                    Object data[] = {
                        rs.getString("chartname"),
                        rs.getDouble("generalexpenditure")
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

    private void loadMarketingExpenditure(Date from, Date till) {
        try {
            String sql = "select ac.chartname,SUM(j.debit)-SUM(j.kredit) as marketingexpenditure from jurnal j inner join"
                    + " accountchart ac on j.chartno = ac.chartno where j.chartno = 6020 and tanggal between ? and ?;  ";

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblExpenditure.getModel();

                    Object data[] = {
                        rs.getString("chartname"),
                        rs.getDouble("marketingexpenditure")
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

    private void loadHRExpenditure(Date from, Date till) {
        try {
            String sql = "select ac.chartname,SUM(j.debit)-SUM(j.kredit) as HRexpenditure from jurnal j inner join"
                    + " accountchart ac on j.chartno = ac.chartno where j.chartno = 6010 and tanggal between ? and ?;  ";

            java.sql.Date datefrom = new java.sql.Date(from.getTime());
            java.sql.Date datetill = new java.sql.Date(till.getTime());

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setDate(1, datefrom);
            pstatement.setDate(2, datetill);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblExpenditure.getModel();

                    Object data[] = {
                        rs.getString("chartname"),
                        rs.getDouble("HRexpenditure")
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dtcFrom = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        dtcTill = new com.toedter.calendar.JDateChooser();
        btnFind = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSalesRevenue = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCOGS = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSalesRevenue = new javax.swing.JTextField();
        txtCOGS = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtGrossProfit = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblExpenditure = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtExp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNet = new javax.swing.JTextField();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Profit-Loss");
        setIconImages(null);

        jLabel1.setText("Profit Loss");

        jLabel2.setText("Period :");

        jLabel3.setText("-");

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        tblSalesRevenue.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSalesRevenue.setShowHorizontalLines(false);
        tblSalesRevenue.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblSalesRevenue);

        jLabel4.setText("Sales Revenue");

        tblCOGS.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCOGS.setShowHorizontalLines(false);
        tblCOGS.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tblCOGS);

        jLabel6.setText("Cost of Goods Sold");

        jLabel7.setText("Total Sales Revenue :");

        txtSalesRevenue.setEnabled(false);

        txtCOGS.setEnabled(false);

        jLabel8.setText("Total COGS :");

        jLabel9.setText("Gross Profit :");

        txtGrossProfit.setEnabled(false);

        jLabel10.setText("Operational Expenditure");

        tblExpenditure.setModel(new javax.swing.table.DefaultTableModel(
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
        tblExpenditure.setShowHorizontalLines(false);
        tblExpenditure.setShowVerticalLines(false);
        jScrollPane3.setViewportView(tblExpenditure);

        jLabel11.setText("Total Operational :");

        txtExp.setEnabled(false);

        jLabel12.setText("Net Provit :");

        txtNet.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSalesRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(txtGrossProfit)
                                    .addComponent(txtNet)))
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCOGS)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtExp)
                                .addGap(57, 57, 57))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(46, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
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
                            .addComponent(jLabel2)
                            .addComponent(dtcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dtcTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSalesRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCOGS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtGrossProfit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

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

//              Sales
                loadSalesIncome(datefrom, datetill);
                loadSalesDiscount(datefrom, datetill);
                loadSalesReturn(datefrom, datetill);
                totalSalesRevenue();

//                COGS
                loadBeginningInventory(datefrom, datetill);
                loadPurchasing(datefrom, datetill);
                loadPurchasingDisc(datefrom, datetill);
                totalCOGS();

//                Expenditure
                loadGeneralExpenditure(datefrom, datetill);
                loadMarketingExpenditure(datefrom, datetill);
                loadHRExpenditure(datefrom, datetill);
                totalExp();

//                total
                grossProfit();
                netProfit();

                insertProfitLoss(datefrom, datetill, Double.valueOf(txtNet.getText()));
            } else {
                Sutil.mse(this, "Call your programmer ! =D , Good Luck !!!");
            }
        } catch (ParseException ex) {
            Logger.getLogger(frmGL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private double totalSalesRevenue() {
        double totalsalesrevenue = 0;
        if (tblSalesRevenue.getRowCount() <= 0) {
            txtSalesRevenue.setText(String.valueOf(totalsalesrevenue));
        } else {
            for (int i = 0; i < tblSalesRevenue.getRowCount(); i++) {
                totalsalesrevenue += Double.valueOf(tblSalesRevenue.getValueAt(i, 1).toString());
                txtSalesRevenue.setText(String.valueOf(totalsalesrevenue));
            }

        }
        return totalsalesrevenue;
    }

    private double totalCOGS() {
        double totalCOGS = 0;
        if (tblCOGS.getRowCount() < 0) {
            txtCOGS.setText(String.valueOf(totalCOGS));
        } else {
            for (int i = 0; i < tblCOGS.getRowCount(); i++) {
                totalCOGS += Double.valueOf(tblCOGS.getValueAt(i, 1).toString());
                txtCOGS.setText(String.valueOf(totalCOGS));
            }

        }
        return totalCOGS;
    }

    private double totalExp() {
        double totalExp = 0;
        if (tblExpenditure.getRowCount() < 0) {
            txtExp.setText(String.valueOf(totalExp));
        } else {
            for (int i = 0; i < tblExpenditure.getRowCount(); i++) {
                totalExp += Double.valueOf(tblExpenditure.getValueAt(i, 1).toString());
                txtExp.setText(String.valueOf(totalExp));
            }

        }
        return totalExp;
    }

    private double grossProfit() {
        double grossprofit = 0;
        double totalSales = Double.valueOf(txtSalesRevenue.getText());
        double totalCOGS = Double.valueOf(txtCOGS.getText());
        grossprofit += totalSales - totalCOGS;
        txtGrossProfit.setText(String.valueOf(grossprofit));
        return grossprofit;
    }

    private double netProfit() {
        double netprofit = 0;
        double totalSales = Double.valueOf(txtSalesRevenue.getText());
        double totalCOGS = Double.valueOf(txtCOGS.getText());
        double totalExp = Double.valueOf(txtExp.getText());
        netprofit += totalSales - totalCOGS - totalExp;
        txtNet.setText(String.valueOf(netprofit));
        return netprofit;
    }

//    private double endingInventory() {
//        double endinginventory = 0;
//        if(tblCOGS.getRowCount() > 0){
//            for(int i = 0; i < tblCOGS.getRowCount();i++){
//                endinginventory += Double.valueOf(tblCOGS.getValueAt(i, 1))
//            }
//            
//        }
//        return endinginventory;
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private com.toedter.calendar.JDateChooser dtcFrom;
    private com.toedter.calendar.JDateChooser dtcTill;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblCOGS;
    private javax.swing.JTable tblExpenditure;
    private javax.swing.JTable tblSalesRevenue;
    private javax.swing.JTextField txtCOGS;
    private javax.swing.JTextField txtExp;
    private javax.swing.JTextField txtGrossProfit;
    private javax.swing.JTextField txtNet;
    private javax.swing.JTextField txtSalesRevenue;
    // End of variables declaration//GEN-END:variables
}
