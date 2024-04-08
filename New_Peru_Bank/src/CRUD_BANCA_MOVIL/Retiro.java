
package CRUD_BANCA_MOVIL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Retiro extends javax.swing.JFrame {
    public  String dato;
    private App appInstance;
    
    public Retiro() {
        initComponents();
        
    }
   public  void setDato(String dato){
        this.dato=dato;
          
    }
    
    public void Retiros(int ret){
        try{
            Conexion cn = new Conexion();
            Connection con = cn.conectar();
            String sql = "select Saldo from Tarjeta where N_Tarjeta=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dato);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            double saldo = rs.getDouble("saldo"); 
            if(saldo==0){JOptionPane.showMessageDialog(null, "Usted no cuenta con saldo disponible");   }
            else if(ret<=saldo){
               int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
               if (respuesta == JOptionPane.YES_OPTION) {
                    sql = "update Tarjeta set saldo = saldo - ? where N_Tarjeta=?";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, ret); 
                    pst.setString(2, dato);
                    pst.executeUpdate(); 
                    JOptionPane.showMessageDialog(null, "Retiro ds"+ret+"$ realizado");  
                    sql = "INSERT INTO Transacciones (N_Cuenta, N_Tarjeta, Monto, tipo_transaccion, tarjeta_destino)\n" +
                            "VALUES (?, ?, ?,'Retiro',?);";
                    String n_cuenta=NCuenta();
                    pst = con.prepareStatement(sql);
                    pst.setString(1,n_cuenta ); 
                    pst.setString(2, dato);
                    pst.setDouble(3, ret);
                    pst.setString(4, dato);
                    pst.executeUpdate();
            } else {  }   
            }else{JOptionPane.showMessageDialog(null, "El monto a retirar exede asu saldo", "Error", JOptionPane.ERROR_MESSAGE); } 
            }else{
                 JOptionPane.showMessageDialog(null, "Sin saldo encontrado"); }
            rs.close();
            pst.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
    }}

    public String NCuenta() {
    String n_cuenta = null;
    try {
        Conexion cn = new Conexion();
        Connection con = cn.conectar();
        String sql = "select N_Cuenta from Tarjeta where N_Tarjeta=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, dato);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            n_cuenta = rs.getString("N_Cuenta");
        } 
        rs.close();
        pst.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return n_cuenta;
}
    public void Digitar(){
        String input = JOptionPane.showInputDialog(null, " Ingrese Monto a retirar:", "Ingreso de Número", JOptionPane.QUESTION_MESSAGE);
        double retiro=Double.parseDouble(input);
        try{
            Conexion cn = new Conexion();
            Connection con = cn.conectar();
            String sql = "select Saldo from Tarjeta where N_Tarjeta=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dato);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            double saldo = rs.getDouble("saldo"); 
            if(saldo==0){JOptionPane.showMessageDialog(null, "Usted no cuenta con saldo disponible");   }
            else if(retiro<=saldo){
               int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
               if (respuesta == JOptionPane.YES_OPTION) {
                    sql = "update Tarjeta set saldo = saldo - ? where N_Tarjeta=?";
                    pst = con.prepareStatement(sql);
                    pst.setDouble(1, retiro);
                    pst.setString(2, dato);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Retiro ds"+retiro+"$ realizado");
                    sql = "INSERT INTO Transacciones (N_Cuenta, N_Tarjeta, Monto, tipo_transaccion, tarjeta_destino)\n" +
                            "VALUES (?, ?, ?,'Retiro',?);";
                    String n_cuenta=NCuenta();
                    pst = con.prepareStatement(sql);
                    pst.setString(1,n_cuenta ); 
                    pst.setString(2, dato);
                    pst.setDouble(3, retiro);
                    pst.setString(4, dato);
                    pst.executeUpdate();
            } else {  }   
            }else{JOptionPane.showMessageDialog(null, "El monto a retirar exede asu saldo", "Error", JOptionPane.ERROR_MESSAGE); } 
            }else{
                 JOptionPane.showMessageDialog(null, "Sin saldo encontrado");  
            }
            rs.close();
            pst.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
    }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCien = new javax.swing.JButton();
        btnTres = new javax.swing.JButton();
        btnCinco = new javax.swing.JButton();
        btnMil = new javax.swing.JButton();
        btnDos = new javax.swing.JButton();
        btnDigitar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("RETIROS");

        btnCien.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnCien.setText("100$");
        btnCien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCienActionPerformed(evt);
            }
        });

        btnTres.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnTres.setText("300$");
        btnTres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTresActionPerformed(evt);
            }
        });

        btnCinco.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnCinco.setText("500$");
        btnCinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCincoActionPerformed(evt);
            }
        });

        btnMil.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnMil.setText("1000$");
        btnMil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMilActionPerformed(evt);
            }
        });

        btnDos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnDos.setText("200$");
        btnDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDosActionPerformed(evt);
            }
        });

        btnDigitar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnDigitar.setText("Digitar Retiro");
        btnDigitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDigitarActionPerformed(evt);
            }
        });

        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCien, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCinco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDigitar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCinco, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCien, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMil, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTres, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDigitar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCienActionPerformed
        int retiro=100;
        Retiros(retiro);
    }//GEN-LAST:event_btnCienActionPerformed

    private void btnDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDosActionPerformed
        int retiro=200;
        Retiros(retiro);
    }//GEN-LAST:event_btnDosActionPerformed

    private void btnTresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTresActionPerformed
        int retiro=300;
        Retiros(retiro);
    }//GEN-LAST:event_btnTresActionPerformed

    private void btnCincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCincoActionPerformed
        int retiro=500;
        Retiros(retiro);
    }//GEN-LAST:event_btnCincoActionPerformed

    private void btnMilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMilActionPerformed
        int retiro=1000;
        Retiros(retiro);
    }//GEN-LAST:event_btnMilActionPerformed

    private void btnDigitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDigitarActionPerformed
       Digitar();
       
    }//GEN-LAST:event_btnDigitarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        App app = new App(); 
        
        
        app.setDato(dato);
        app.setVisible(true);
        dispose();
            
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(Retiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Retiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Retiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Retiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Retiro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCien;
    private javax.swing.JButton btnCinco;
    private javax.swing.JButton btnDigitar;
    private javax.swing.JButton btnDos;
    private javax.swing.JButton btnMil;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnTres;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
