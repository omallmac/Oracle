
package crud.product;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Product extends javax.swing.JFrame {

   
    public Product() {
        initComponents();
        mostarAlumnos();
        
        tblProcuto.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent Mouse_evt){
                JTable table =(JTable) Mouse_evt.getSource();
                Point point =Mouse_evt.getPoint();
                int row = table .rowAtPoint(point);
                if(Mouse_evt.getClickCount()==1)
                {
                    txtId.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),0).toString());
                    txtNameProduct.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),1).toString());
                    txtprovedor.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),2).toString());
                    txtCategoria.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),3).toString());
                    txtCantidadUnidad.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),4).toString());
                    txtPrecioUnitario.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),5).toString());
                    txtUnidadesStock.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),6).toString());
                    txtUnidadesPedido.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),7).toString());
                    txtReordeNivel.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),8).toString());
                    txtdISCONTINUIDAD.setText(tblProcuto.getValueAt(tblProcuto.getSelectedRow(),9).toString());
                    
                
                }
            
            }  

       });
    }

    public void mostarAlumnos(){
       try{
            Conexion cn=new Conexion();
            Statement statement = cn.conectar().createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from product");
            DefaultTableModel modelo= (DefaultTableModel)tblProcuto.getModel();
            
            modelo.setColumnCount(0);
            modelo.setRowCount(0);
            
            String[] colums={"ID","Name product","supplier","ID categoria","quantityPerUnit","unitPrice","unitsInStock","unitsOnOrder","reorderLevel","discontinued"};      
            for(String column:colums ){
                modelo.addColumn(column);
            }
            while(resultSet.next()){
                String[] row={
                        String.valueOf(resultSet.getInt("productId")) ,
                        resultSet.getString("productName"),
                        String.valueOf(resultSet.getInt("supplierId")),
                        String.valueOf(resultSet.getInt("categoryId")),
                        resultSet.getString("quantityPerUnit"),
                        String.valueOf(resultSet.getDouble("unitPrice")),
                        String.valueOf(resultSet.getInt("unitsInStock")),
                        String.valueOf(resultSet.getInt("unitsOnOrder")),
                        String.valueOf(resultSet.getInt("reorderLevel")),
                        resultSet.getString("discontinued")
                };
                modelo.addRow(row); 
            }
                tblProcuto.setModel(modelo);   
        }catch(SQLException e){
            e.printStackTrace();
        }  
    }
    
    public void insertar(){
        try{
            int id=Integer.parseInt(txtId.getText());
            String nombre=txtNameProduct.getText();
            int proveedor=Integer.parseInt(txtprovedor.getText());
            int idcategoria=Integer.parseInt(txtCategoria.getText());
            String catidadunitaria=txtCantidadUnidad.getText();
            double preciounitario=Double.parseDouble(txtPrecioUnitario.getText());
            int stock=Integer.parseInt(txtUnidadesStock.getText());
            int orden=Integer.parseInt(txtUnidadesPedido.getText());
            int nivel=Integer.parseInt(txtReordeNivel.getText());
            String dis=txtdISCONTINUIDAD.getText();
            Conexion cn=new Conexion();
            PreparedStatement ps =
                    cn.conectar().prepareStatement("Insert into product value(?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setInt(3, proveedor);
            ps.setInt(4, idcategoria);
            ps.setString(5, catidadunitaria);
            ps.setDouble(6, preciounitario);
            ps.setInt(7, stock);
            ps.setInt(8, orden);
            ps.setInt(9, nivel);
            ps.setString(10, dis);
            int rows=ps.executeUpdate();
            if(rows >0){
                JOptionPane.showMessageDialog(null, "Producto Insertado");
                mostarAlumnos();
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void eliminar(){
        try{
            int id=Integer.parseInt(txtId.getText());
            Conexion  cn=new Conexion();
            PreparedStatement preparedStatement =cn.conectar().prepareStatement("Delete from product where productId=?");
            preparedStatement.setInt(1, id);         
            int rows=preparedStatement.executeUpdate();
            if(rows >0){    
                JOptionPane.showMessageDialog(null, "Producto Eliminado");
                mostarAlumnos();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
  
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void actualisar(){
        try{
            int id=Integer.parseInt(txtId.getText());
            String nombre=txtNameProduct.getText();
            int proveedor=Integer.parseInt(txtprovedor.getText());
            int idcategoria=Integer.parseInt(txtCategoria.getText());
            String catidadunitaria=txtCantidadUnidad.getText();
            double preciounitario=Double.parseDouble(txtPrecioUnitario.getText());
            int stock=Integer.parseInt(txtUnidadesStock.getText());
            int orden=Integer.parseInt(txtUnidadesPedido.getText());
            int nivel=Integer.parseInt(txtReordeNivel.getText());
            String dis=txtdISCONTINUIDAD.getText();
            
            Conexion  cn=new Conexion();
            
            PreparedStatement ps =cn.conectar().prepareStatement("Update product set productName=?,supplierId=?,categoryId=?,quantityPerUnit=?,unitPrice=?,unitsInStock=?,unitsOnOrder=?,reorderLevel=?,discontinued=? where productId=?");
           
            ps.setString(1, nombre);
            ps.setInt(2, proveedor);
            ps.setInt(3, idcategoria);
            ps.setString(4, catidadunitaria);
            ps.setDouble(5, preciounitario);
            ps.setInt(6, stock);
            ps.setInt(7, orden);
            ps.setInt(8, nivel);
            ps.setString(9, dis);
            ps.setInt(10, id);
            
            int rows=ps.executeUpdate();
            
            if(rows >0){
                JOptionPane.showMessageDialog(null, "Tabla actualizada");
                mostarAlumnos();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Actualizar");
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        
        }

    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProcuto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtprovedor = new javax.swing.JTextField();
        txtCategoria = new javax.swing.JTextField();
        txtPrecioUnitario = new javax.swing.JTextField();
        txtNameProduct = new javax.swing.JTextField();
        txtdISCONTINUIDAD = new javax.swing.JTextField();
        btnInsertar = new javax.swing.JButton();
        btnActualisar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCantidadUnidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtUnidadesStock = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtUnidadesPedido = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtReordeNivel = new javax.swing.JTextField();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblProcuto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblProcuto);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setText("CRUD Tabla \"Product\"");

        jLabel3.setText("ID:");

        jLabel4.setText("Nombre del Producto:");

        jLabel5.setText("Precio unitario:");

        jLabel6.setText("ID de categoria:");

        jLabel7.setText("ID del proveedor:");

        jLabel8.setText("Discontinuidad:");

        btnInsertar.setText("INSERTAR");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });

        btnActualisar.setText("ACTUALIZAR");
        btnActualisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualisarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel9.setText("Cantidad por Unidad:");

        txtCantidadUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadUnidadActionPerformed(evt);
            }
        });

        jLabel10.setText(" Unidades en Stock:");

        txtUnidadesStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadesStockActionPerformed(evt);
            }
        });

        jLabel11.setText("Unidades en pedido :");

        txtUnidadesPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadesPedidoActionPerformed(evt);
            }
        });

        jLabel12.setText(" Reordenar nivel :");

        txtReordeNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReordeNivelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(77, 77, 77)
                                    .addComponent(txtdISCONTINUIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(130, 130, 130)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel10))
                                            .addGap(31, 31, 31)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtprovedor, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCantidadUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtUnidadesStock, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtReordeNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(116, 116, 116)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(39, 39, 39)
                                            .addComponent(txtNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(54, 54, 54)
                                            .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(37, 37, 37)
                                            .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtUnidadesPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel12))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btnInsertar)
                        .addGap(220, 220, 220)
                        .addComponent(btnActualisar)
                        .addGap(169, 169, 169)
                        .addComponent(btnEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtprovedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCantidadUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtUnidadesStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtUnidadesPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdISCONTINUIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(txtReordeNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualisar)
                    .addComponent(btnInsertar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
       insertar();
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void txtCantidadUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadUnidadActionPerformed
        
    }//GEN-LAST:event_txtCantidadUnidadActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtUnidadesStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnidadesStockActionPerformed
        
    }//GEN-LAST:event_txtUnidadesStockActionPerformed

    private void txtUnidadesPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnidadesPedidoActionPerformed
        
    }//GEN-LAST:event_txtUnidadesPedidoActionPerformed

    private void txtReordeNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReordeNivelActionPerformed
        
    }//GEN-LAST:event_txtReordeNivelActionPerformed

    private void btnActualisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualisarActionPerformed
        actualisar();
    }//GEN-LAST:event_btnActualisarActionPerformed

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
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualisar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
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
    private javax.swing.JTable tblProcuto;
    private javax.swing.JTextField txtCantidadUnidad;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNameProduct;
    private javax.swing.JTextField txtPrecioUnitario;
    private javax.swing.JTextField txtReordeNivel;
    private javax.swing.JTextField txtUnidadesPedido;
    private javax.swing.JTextField txtUnidadesStock;
    private javax.swing.JTextField txtdISCONTINUIDAD;
    private javax.swing.JTextField txtprovedor;
    // End of variables declaration//GEN-END:variables
}
