/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemapersonalmunicipal.view;
import com.mycompany.sistemapersonalmunicipal.dao.CargoDAO;
import com.mycompany.sistemapersonalmunicipal.dao.AreaDAO;
import com.mycompany.sistemapersonalmunicipal.dao.TrabajadorDAO;
import com.mycompany.sistemapersonalmunicipal.model.Cargo;
import com.mycompany.sistemapersonalmunicipal.model.ObreroMunicipal;
import com.mycompany.sistemapersonalmunicipal.model.EmpleadoMunicipal;
import com.mycompany.sistemapersonalmunicipal.model.Trabajador;
import com.mycompany.sistemapersonalmunicipal.model.Area;
import javax.swing.JOptionPane;

/**
 *
 * @author marantonio55
 */
public class FrmRegistrarTrabajador extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmRegistrarTrabajador.class.getName());

    /**
     * Creates new form FrmRegistrarTrabajador
     */
    public FrmRegistrarTrabajador() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        actualizarCamposPago();
        cargarAreasYCargos();
        generarCodigoTrabajador();
    }
    private void limpiarCampos() {

    txtNumeroDocumento.setText("");
    txtNombres.setText("");
    txtApellidoPaterno.setText("");
    txtApellidoMaterno.setText("");
    txtCelular.setText("");
    txtCorreo.setText("");
    txtCodigoTrabajador.setText("");
    txtFechaIngreso.setText("");

    txtSueldoMensual.setText("");
    txtBonificacion.setText("");
    txtJornalDiario.setText("");
    txtDiasTrabajados.setText("");

    cboTipoTrabajador.setSelectedIndex(0);
    cboTipoDocumento.setSelectedIndex(0);
    cboRegimenLaboral.setSelectedIndex(0);
    }
        private void actualizarCamposPago() {

        String tipo = cboTipoTrabajador.getSelectedItem().toString();

        boolean esEmpleado =
                tipo.equals("Empleado Municipal");

        // CAMPOS DE EMPLEADO
        txtSueldoMensual.setEnabled(esEmpleado);
        txtBonificacion.setEnabled(esEmpleado);

        jLabel15.setEnabled(esEmpleado); // Sueldo mensual
        jLabel17.setEnabled(esEmpleado); // Bonificación

        // CAMPOS DE OBRERO
        txtJornalDiario.setEnabled(!esEmpleado);
        txtDiasTrabajados.setEnabled(!esEmpleado);

        jLabel16.setEnabled(!esEmpleado); // Jornal diario
        jLabel18.setEnabled(!esEmpleado); // Días trabajados

        // LIMPIAR CAMPOS QUE NO CORRESPONDEN
        if (esEmpleado) {

            txtJornalDiario.setText("");
            txtDiasTrabajados.setText("");

        } else {

            txtSueldoMensual.setText("");
            txtBonificacion.setText("");
        }
    }
        private void cargarAreasYCargos() {

        cboArea.removeAllItems();
        cboCargo.removeAllItems();

        AreaDAO areaDAO = new AreaDAO();

        java.util.List<Object[]> areas =
                areaDAO.listarTodas();

        for (Object[] area : areas) {

            cboArea.addItem(
                    area[1] + " - " + area[2]
            );
        }


        CargoDAO cargoDAO = new CargoDAO();

        java.util.List<Object[]> cargos =
                cargoDAO.listarTodos();

        for (Object[] cargo : cargos) {

            cboCargo.addItem(
                    cargo[1] + " - " + cargo[2]
            );
        }
        cargarCargosSegunTipo();
    }
            private void cargarCargosSegunTipo() {

        cboCargo.removeAllItems();

        String tipoTrabajador =
                cboTipoTrabajador.getSelectedItem().toString();

        CargoDAO cargoDAO = new CargoDAO();

        java.util.List<Object[]> cargos =
                cargoDAO.listarTodos();

        for (Object[] cargo : cargos) {

            String codigo = cargo[1].toString();
            String nombre = cargo[2].toString();

            if (tipoTrabajador.equals("Empleado Municipal")) {

                if (!nombre.equalsIgnoreCase("Obrero municipal")) {

                    cboCargo.addItem(
                            codigo + " - " + nombre
                    );
                }

            } else {

                if (nombre.equalsIgnoreCase("Obrero municipal")) {

                    cboCargo.addItem(
                            codigo + " - " + nombre
                    );
                }
            }
        }
    }
        private void generarCodigoTrabajador() {

      TrabajadorDAO trabajadorDAO =
              new TrabajadorDAO();

      String tipoTrabajador =
              cboTipoTrabajador
                      .getSelectedItem()
                      .toString();

      String codigo =
              trabajadorDAO.generarSiguienteCodigo(
                      tipoTrabajador
              );

      txtCodigoTrabajador.setText(codigo);
    }      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cboTipoDocumento = new javax.swing.JComboBox<>();
        txtNumeroDocumento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtApellidoMaterno = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        cboTipoTrabajador = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCodigoTrabajador = new javax.swing.JTextField();
        txtFechaIngreso = new javax.swing.JTextField();
        cboRegimenLaboral = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cboArea = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        cboCargo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSueldoMensual = new javax.swing.JTextField();
        txtJornalDiario = new javax.swing.JTextField();
        txtDiasTrabajados = new javax.swing.JTextField();
        txtBonificacion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS PERSONALES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CE" }));

        txtNumeroDocumento.addActionListener(this::txtNumeroDocumentoActionPerformed);

        jLabel4.setText("Nombres:");

        jLabel5.setText("Ap. paterno:");

        jLabel6.setText("Celular: ");

        jLabel7.setText("Tipo documento:");

        jLabel8.setText("Número:");

        txtNombres.addActionListener(this::txtNombresActionPerformed);

        txtApellidoPaterno.addActionListener(this::txtApellidoPaternoActionPerformed);

        jLabel9.setText("Correo:");

        txtApellidoMaterno.addActionListener(this::txtApellidoMaternoActionPerformed);

        txtCelular.addActionListener(this::txtCelularActionPerformed);

        jLabel10.setText("Ap. materno:");

        txtCorreo.addActionListener(this::txtCorreoActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellidoPaterno)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtCorreo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cboTipoTrabajador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado Municipal", "Obrero Municipal" }));
        cboTipoTrabajador.addActionListener(this::cboTipoTrabajadorActionPerformed);

        jLabel3.setText("REGISTRO DE TRABAJADOR MUNICIPAL   ");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS LABORALES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel11.setText("Código trabajador:");

        jLabel12.setText("Fecha ingreso:");

        jLabel13.setText("Régimen laboral:");

        txtCodigoTrabajador.setEditable(false);
        txtCodigoTrabajador.addActionListener(this::txtCodigoTrabajadorActionPerformed);

        txtFechaIngreso.addActionListener(this::txtFechaIngresoActionPerformed);

        cboRegimenLaboral.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CAS", "276", "728" }));

        jLabel19.setText("Area:");

        cboArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CAS", "276", "728" }));

        jLabel20.setText("Cargo:");

        cboCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CAS", "276", "728" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel19))
                    .addComponent(jLabel20))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cboRegimenLaboral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigoTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addComponent(txtFechaIngreso)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCodigoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(cboRegimenLaboral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel14.setText("Tipo trabajador:");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DE PAGO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel15.setText("Sueldo mensual:");

        jLabel16.setText("Jornal diario: ");

        jLabel17.setText("Bonificacion:");

        jLabel18.setText("Días trabajados:");

        txtSueldoMensual.addActionListener(this::txtSueldoMensualActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtJornalDiario, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(txtSueldoMensual))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBonificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiasTrabajados, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(txtSueldoMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBonificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(txtDiasTrabajados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJornalDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel14)
                        .addGap(32, 32, 32)
                        .addComponent(cboTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(btnGuardar)
                        .addGap(59, 59, 59)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap(147, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(167, 167, 167))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cboTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCerrar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {

        String tipoTrabajador = cboTipoTrabajador.getSelectedItem().toString();
                // ================= VALIDACIONES EN ORDEN =================

        // 1. NÚMERO DE DOCUMENTO

        if (txtNumeroDocumento.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el número de documento."
            );

            txtNumeroDocumento.requestFocus();

            return;
        }


        // VALIDAR DNI

        if (cboTipoDocumento.getSelectedItem().toString().equals("DNI")
                && !txtNumeroDocumento.getText().trim().matches("\\d{8}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "El DNI debe contener exactamente 8 dígitos numéricos."
            );

            txtNumeroDocumento.requestFocus();

            return;
        }


        // 2. NOMBRES

        if (txtNombres.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar los nombres."
            );

            txtNombres.requestFocus();

            return;
        }


        // 3. APELLIDO PATERNO

        if (txtApellidoPaterno.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el apellido paterno."
            );

            txtApellidoPaterno.requestFocus();

            return;
        }


        // 4. APELLIDO MATERNO

        if (txtApellidoMaterno.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el apellido materno."
            );

            txtApellidoMaterno.requestFocus();

            return;
        }


        // 5. CELULAR

        if (txtCelular.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el número de celular."
            );

            txtCelular.requestFocus();

            return;
        }


        // VALIDAR QUE EL CELULAR TENGA 9 NÚMEROS

        if (!txtCelular.getText().trim().matches("\\d{9}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "El celular debe contener exactamente 9 dígitos numéricos."
            );

            txtCelular.requestFocus();

            return;
        }


        // 6. CORREO

        if (txtCorreo.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el correo electrónico."
            );

            txtCorreo.requestFocus();

            return;
        }


        // VALIDAR FORMATO DEL CORREO

        if (!txtCorreo.getText().trim().matches(
                "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$"
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un correo electrónico válido."
            );

            txtCorreo.requestFocus();

            return;
        }


        // 7. CÓDIGO DEL TRABAJADOR

        if (txtCodigoTrabajador.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el código del trabajador."
            );

            txtCodigoTrabajador.requestFocus();

            return;
        }


        // 8. FECHA DE INGRESO

        if (txtFechaIngreso.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar la fecha de ingreso."
            );

            txtFechaIngreso.requestFocus();

            return;
        }
        Trabajador trabajador;

        if (tipoTrabajador.equals("Empleado Municipal")) {
            
            if (txtSueldoMensual.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar el sueldo mensual.");
                return;
            }

            if (txtBonificacion.getText().trim().isEmpty()) {

                javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar la bonificación.");
                 return;
            }

            EmpleadoMunicipal empleado = new EmpleadoMunicipal();

            empleado.setSueldoMensual(Double.parseDouble(txtSueldoMensual.getText()));

            empleado.setBonificacion(Double.parseDouble(txtBonificacion.getText()));

            trabajador = empleado;

        }else {
            if (txtJornalDiario.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar el jornal diario.");
                return;
            }

            if (txtDiasTrabajados.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar los días trabajados.");
                 return;
                }
            
            ObreroMunicipal obrero = new ObreroMunicipal();

            obrero.setJornalDiario(Double.parseDouble(txtJornalDiario.getText()));

            obrero.setDiasTrabajados(Integer.parseInt(txtDiasTrabajados.getText()));

            trabajador = obrero;

        }

        trabajador.setTipoDocumento(cboTipoDocumento.getSelectedItem().toString());

        trabajador.setNumeroDocumento(txtNumeroDocumento.getText());

        trabajador.setNombres(txtNombres.getText());

        trabajador.setApellidoPaterno(txtApellidoPaterno.getText());

        trabajador.setApellidoMaterno(txtApellidoMaterno.getText());

        trabajador.setCelular(txtCelular.getText());

        trabajador.setCorreo(txtCorreo.getText());

        trabajador.setCodigoTrabajador(txtCodigoTrabajador.getText());

        trabajador.setFechaIngreso(txtFechaIngreso.getText());

        trabajador.setRegimenLaboral(cboRegimenLaboral.getSelectedItem().toString());

        String seleccionArea =
                cboArea.getSelectedItem().toString();

        String seleccionCargo =
                cboCargo.getSelectedItem().toString();


        // Separar código y nombre del área
        String[] partesArea =
                seleccionArea.split(" - ", 2);

        String codigoArea =
                partesArea[0];

        String nombreArea =
                partesArea[1];


        // Separar código y nombre del cargo
        String[] partesCargo =
                seleccionCargo.split(" - ", 2);

        String codigoCargo =
                partesCargo[0];

        String nombreCargo =
                partesCargo[1];


        Area area =
                new Area(
                        codigoArea,
                        nombreArea
                );

        Cargo cargo =
                new Cargo(
                        codigoCargo,
                        nombreCargo
                );

        //Contrato contrato = new Contrato("CT001", trabajador.getFechaIngreso(), "2026-12-31");

        trabajador.setArea(area);

        trabajador.setCargo(cargo);

        //trabajador.setContrato(contrato);

        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

        boolean registradoBD = trabajadorDAO.insertar(trabajador);

        if (registradoBD) {

            JOptionPane.showMessageDialog(
                    this,
                    "Trabajador registrado correctamente en la base de datos."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el trabajador.\n"
                    + "Verifique que el documento y el código no estén duplicados.",
                    "Error de registro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        limpiarCampos();
        } catch (NumberFormatException e) {

        javax.swing.JOptionPane.showMessageDialog(this, "Error: los campos de sueldo, bonificación, jornal o días trabajados deben contener valores numéricos válidos.");

        } catch (IllegalArgumentException e) {

        javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());

        } catch (Exception e) {

        javax.swing.JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());

        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPaternoActionPerformed

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed

    private void txtApellidoMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoMaternoActionPerformed

    private void txtCodigoTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoTrabajadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoTrabajadorActionPerformed

    private void txtFechaIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaIngresoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaIngresoActionPerformed

    private void txtSueldoMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSueldoMensualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSueldoMensualActionPerformed

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void txtNumeroDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroDocumentoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
         dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cboTipoTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoTrabajadorActionPerformed
        // TODO add your handling code here:
        actualizarCamposPago();
        cargarCargosSegunTipo();
        generarCodigoTrabajador();
    }//GEN-LAST:event_cboTipoTrabajadorActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmRegistrarTrabajador().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cboArea;
    private javax.swing.JComboBox<String> cboCargo;
    private javax.swing.JComboBox<String> cboRegimenLaboral;
    private javax.swing.JComboBox<String> cboTipoDocumento;
    private javax.swing.JComboBox<String> cboTipoTrabajador;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtBonificacion;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCodigoTrabajador;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDiasTrabajados;
    private javax.swing.JTextField txtFechaIngreso;
    private javax.swing.JTextField txtJornalDiario;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumeroDocumento;
    private javax.swing.JTextField txtSueldoMensual;
    // End of variables declaration//GEN-END:variables
}
