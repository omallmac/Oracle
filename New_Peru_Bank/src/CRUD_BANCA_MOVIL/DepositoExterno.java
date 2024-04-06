/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CRUD_BANCA_MOVIL;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
public class DepositoExterno extends javax.swing.JFrame {

    public String dato;
    public DepositoExterno() {
        initComponents();
    }
    
    public  void setDato(String dato){
        this.dato=dato;
          
    }
    public void Transferir(){
        try{
            String ncuenta=txtCuenta.getText();
            String ntarjeta=txtTarjeta.getText();
            double monto=Double.parseDouble(txtMonto.getText());
            Conexion cn=new Conexion();
            Connection con=cn.conectar();
            String sql="select * from Tarjeta where N_Tarjeta=? and N_Cuenta=?";
            PreparedStatement pst = con.prepareStatement(sql);   
            pst.setString(1,ntarjeta );
            pst.setString(2,ncuenta);
            ResultSet rsExistencia =pst.executeQuery();
            if(!rsExistencia.next()){
                JOptionPane.showMessageDialog(null, "Error: La tarjeta o la cuenta no existe.");
                return;
            }
            sql="select saldo from Tarjeta where N_Tarjeta=?";
            pst = con.prepareStatement(sql);   
            pst.setString(1,dato);
            ResultSet rsp=pst.executeQuery();  
            double saldo = 0;
            if(rsp.next()){
                saldo = rsp.getDouble("saldo");
            }
            if(monto> saldo){
                JOptionPane.showMessageDialog(null, "Error: No tienes suficiente saldo para realizar la transferencia.");
                return;
            }
            
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
            
            if (respuesta == JOptionPane.YES_OPTION) {
            con.setAutoCommit(false); 
            String sqlActualizarOrigen = "UPDATE Tarjeta SET saldo=saldo-? WHERE N_Tarjeta=?";
            PreparedStatement pstActualizarOrigen = con.prepareStatement(sqlActualizarOrigen);
            pstActualizarOrigen.setDouble(1, monto);
            pstActualizarOrigen.setString(2, dato);
            pstActualizarOrigen.executeUpdate();
            
            
            String sqlActualizarDestino = "UPDATE Tarjeta SET saldo=saldo+? WHERE N_Tarjeta=?";
            PreparedStatement pstActualizarDestino = con.prepareStatement(sqlActualizarDestino);
            pstActualizarDestino.setDouble(1, monto);
            pstActualizarDestino.setString(2, ntarjeta);
            pstActualizarDestino.executeUpdate();

            con.commit(); 
            con.setAutoCommit(true);
             JOptionPane.showMessageDialog(null, "Transferencia realizada con éxito.");}
            else { 
                return;
            } 
        
           
            

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JTextField();
        txtCuenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        btnTransferir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("TRANSFERENCIA BANCARIO");

        jLabel2.setText("N_Tarjeta:");

        jLabel3.setText("N_Cuenta:");

        jLabel4.setText("Monto:");

        btnTransferir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTransferir.setText("TRANSFERIR");
        btnTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(46, 46, 46)
                            .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(43, 43, 43))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(59, 59, 59)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCuenta)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addComponent(jLabel1))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(btnTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirActionPerformed
        Transferir();
    }//GEN-LAST:event_btnTransferirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       App app = new App(); 
        app.setDato(dato);
        app.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DepositoExterno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepositoExterno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepositoExterno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepositoExterno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DepositoExterno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTransferir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCuenta;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtTarjeta;
    // End of variables declaration//GEN-END:variables
}
