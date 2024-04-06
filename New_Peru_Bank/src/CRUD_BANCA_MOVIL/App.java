
package CRUD_BANCA_MOVIL;


import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Pc-01
 */
public class App extends javax.swing.JFrame {
    private String dato;

    public App() {
        initComponents();
    
    }
    
    public  void setDato(String dato){
        this.dato=dato;
        lblCodigo.setText("  "+dato);
        
    }
    
    

    public void Consultar( ){
        try{
            Conexion cn = new Conexion();
            Connection con = cn.conectar();
           
            String sql = "select saldo from Tarjeta where N_Tarjeta=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,dato);
            ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            double saldo = rs.getDouble("saldo");
            JOptionPane.showMessageDialog(null, "Su saldo actual es de: " + saldo+"$");
        } 
        rs.close();
        pst.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }}
    public void Depositar(){
        String input = JOptionPane.showInputDialog(null, " Ingrese Monto a Depositar:", "DEPOSITO", JOptionPane.QUESTION_MESSAGE);
        double depostio=Double.parseDouble(input);
        try{
            Conexion cn=new Conexion();
            Connection con=cn.conectar();
            String sql="update Tarjeta set Saldo=Saldo+? where N_Tarjeta=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setDouble(1, depostio);
            pst.setString(2,dato);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deposito de "+depostio+"$ realizado");
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
        btnConsultar = new javax.swing.JButton();
        btnRetiro = new javax.swing.JButton();
        btnDepositoExterno = new javax.swing.JButton();
        btnDeposito = new javax.swing.JButton();
        btnTransacciones = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnConsultar.setText("CONSULTAR SALDO");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnRetiro.setText("RETIRAR MONTO");
        btnRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetiroActionPerformed(evt);
            }
        });

        btnDepositoExterno.setText("DEPOSITO EXTERNO");
        btnDepositoExterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositoExternoActionPerformed(evt);
            }
        });

        btnDeposito.setText("DEPOSITAR DINERO");
        btnDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositoActionPerformed(evt);
            }
        });

        btnTransacciones.setText("TRANSACCIONES");

        jLabel2.setText("TARJETA INICIADA");

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDepositoExterno, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDeposito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDepositoExterno, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(btnTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDepositoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositoActionPerformed
            Depositar();
    }//GEN-LAST:event_btnDepositoActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
            Consultar();
       
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnRetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiroActionPerformed
            // Crea una nueva instancia de Retiro pasando la instancia actual de App
    Retiro ret = new Retiro(); 
    ret.setDato(dato);
    ret.setVisible(true);
    
    dispose(); // Cierra la ventana actual de App
    }//GEN-LAST:event_btnRetiroActionPerformed

    private void btnDepositoExternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositoExternoActionPerformed
        DepositoExterno cn=new DepositoExterno();
        cn.setDato(dato);
        cn.setVisible(true);
    
        dispose();
        
        
        
        
    }//GEN-LAST:event_btnDepositoExternoActionPerformed

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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnDeposito;
    private javax.swing.JButton btnDepositoExterno;
    private javax.swing.JButton btnRetiro;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblCodigo;
    // End of variables declaration//GEN-END:variables
}
