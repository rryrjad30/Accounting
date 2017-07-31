package akuntansi;

import data.dlgCustomer;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author COMPUTER
 */
public class dlgAkunCodeSearch extends javax.swing.JDialog {

    private Connection conn;

    /**
     * Creates new form dlgAkunCode
     */
    public dlgAkunCodeSearch(java.awt.Frame parent, boolean modal, Connection conn) {
        super(parent, modal);
        this.conn = conn;
        initComponents();
        loadAllDatabase();
        tableSelectionListener();
        setLocationRelativeTo(null);
    }

    private void loadAllDatabase() {
        removeTableData();
        try {
            String sql = "SELECT * FROM accountchart;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblAccountChart.getModel();

                    Object data[] = {
                        rs.getInt("chartno"),
                        rs.getString("chartname"),};
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

    public void tableSelectionListener() {
        ListSelectionListener listener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = tblAccountChart.getSelectedRow();
                if (row >= 0) {
                    int code = Integer.valueOf(tblAccountChart.getValueAt(row, 0).toString());
                    String name = tblAccountChart.getValueAt(row, 1).toString();
                    frmGL.pilihAkun(code, name);
                }
                dispose();
            }
        };
        tblAccountChart.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblAccountChart.getSelectionModel().addListSelectionListener(listener);
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
        tblAccountChart = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Account Chart");

        tblAccountChart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
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
        tblAccountChart.setColumnSelectionAllowed(true);
        tblAccountChart.setShowHorizontalLines(false);
        tblAccountChart.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblAccountChart);
        tblAccountChart.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblAccountChart.getModel();
        tableModel.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAccountChart;
    // End of variables declaration//GEN-END:variables
}
