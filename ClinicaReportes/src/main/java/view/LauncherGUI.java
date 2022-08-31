package view;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import exceptions.FileException;
import model.Constants;
import model.DoctorManager;
import model.ReportsCreator;
import model.person.Doctor;

public class LauncherGUI extends javax.swing.JFrame {
	DoctorManager doctorManager;
	ReportsCreator reportsCreator;
	Vector<Vector<Object>> doctorsData;
	Vector<Vector<Object>> modifiersData;
	DefaultTableModel doctorsTableModel;
	DefaultTableModel modifiersTableModel;
	int docsCont=0;
    /**
     * Creates new form LauncherGUI
     */
    public LauncherGUI(){
    	
    	try {
    		doctorManager = new DoctorManager();
			doctorManager.loadFromFile(Constants.DOCTORS_IN_FILE);
    		reportsCreator = new ReportsCreator(doctorManager);
			doctorsData = new Vector<Vector<Object>>();
	    	Vector<Object> header = new Vector<Object>();
	    	header.add("CMP");header.add("Nombre y Apellidos");
	    	doctorsTableModel = new DefaultTableModel(doctorsData,header);
			modifiersData = new Vector<Vector<Object>>();
	    	Vector<Object> header2 = new Vector<Object>();
	    	header2.add("Valor");header2.add("Porcentaje");
	    	modifiersTableModel = new DefaultTableModel(modifiersData,header2);
	    	
	        initComponents();
	        
	    	this.jTableDoctors.getColumn("CMP").setMinWidth(110);
	    	this.jTableDoctors.getColumn("CMP").setPreferredWidth(110);
	    	this.jTableDoctors.getColumn("CMP").setMaxWidth(110);
	    	this.updateDoctorsTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e);
			e.printStackTrace();
		}
    	
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpDocsGeneration = new javax.swing.JPanel();
        jtfDestinationFolder = new javax.swing.JTextField();
        jlDocumentsCounter = new javax.swing.JLabel();
        jbAddDocument = new javax.swing.JButton();
        jbGenerate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtModifiers = new javax.swing.JTable();
        jbSelectDestinationFolder = new javax.swing.JButton();
        jpDoctorManager = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDoctors = new javax.swing.JTable();
        jtfFullName = new javax.swing.JTextField();
        jtfCMP = new javax.swing.JTextField();
        jbAddDoctor = new javax.swing.JButton();
        jbRemoveDoctor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtfDestinationFolder.setText("carpeta destino");

        jlDocumentsCounter.setText("Documentos cargados: 0");

        jbAddDocument.setText("Seleccionar");
        jbAddDocument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddDocumentActionPerformed(evt);
            }
        });

        jbGenerate.setText("Generar");
        jbGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGenerateActionPerformed(evt);
            }
        });

        jtModifiers.setModel(modifiersTableModel);
        jScrollPane2.setViewportView(jtModifiers);

        jbSelectDestinationFolder.setText("Seleccionar");
        jbSelectDestinationFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSelectDestinationFolderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDocsGenerationLayout = new javax.swing.GroupLayout(jpDocsGeneration);
        jpDocsGeneration.setLayout(jpDocsGenerationLayout);
        jpDocsGenerationLayout.setHorizontalGroup(
            jpDocsGenerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDocsGenerationLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jpDocsGenerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDocsGenerationLayout.createSequentialGroup()
                        .addComponent(jbGenerate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfDestinationFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jbSelectDestinationFolder))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpDocsGenerationLayout.createSequentialGroup()
                        .addComponent(jlDocumentsCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAddDocument)))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jpDocsGenerationLayout.setVerticalGroup(
            jpDocsGenerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDocsGenerationLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jpDocsGenerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDocumentsCounter)
                    .addComponent(jbAddDocument))
                .addGap(18, 18, 18)
                .addGroup(jpDocsGenerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGenerate)
                    .addComponent(jtfDestinationFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSelectDestinationFolder))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Genración de reportes", jpDocsGeneration);

        jTableDoctors.setModel(doctorsTableModel);
        jScrollPane1.setViewportView(jTableDoctors);

        jtfFullName.setText("Nombre y apellidos");

        jtfCMP.setText("CMP");

        jbAddDoctor.setText("Agregar");
        jbAddDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddDoctorActionPerformed(evt);
            }
        });

        jbRemoveDoctor.setText("Eliminar");
        jbRemoveDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoveDoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDoctorManagerLayout = new javax.swing.GroupLayout(jpDoctorManager);
        jpDoctorManager.setLayout(jpDoctorManagerLayout);
        jpDoctorManagerLayout.setHorizontalGroup(
            jpDoctorManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDoctorManagerLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jpDoctorManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfCMP, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpDoctorManagerLayout.createSequentialGroup()
                        .addComponent(jbAddDoctor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbRemoveDoctor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jpDoctorManagerLayout.setVerticalGroup(
            jpDoctorManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDoctorManagerLayout.createSequentialGroup()
                .addGroup(jpDoctorManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDoctorManagerLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDoctorManagerLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jpDoctorManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbAddDoctor)
                            .addComponent(jbRemoveDoctor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtfCMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Médicos", jpDoctorManager);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                          

    private void jbAddDoctorActionPerformed(java.awt.event.ActionEvent evt) {      
    	Doctor doctor = new Doctor(this.jtfFullName.getText(), this.jtfCMP.getText());
    	try {
			doctorManager.addDoctor(doctor);
	    	updateDoctorsTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e);
			e.printStackTrace();
		}
    }                                           

    private void jbRemoveDoctorActionPerformed(java.awt.event.ActionEvent evt){                                               
       	Doctor doctor = new Doctor(this.jtfFullName.getText(), this.jtfCMP.getText());
       	try {
			doctorManager.removeDoctor(doctor);
			updateDoctorsTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e);
			e.printStackTrace();
		}
       	
    }    
    

	private void jbAddDocumentActionPerformed(java.awt.event.ActionEvent evt) {                                              
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		chooser.setCurrentDirectory(new File("resources/exampledata/"));
		int result = chooser.showOpenDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			File[] files = chooser.getSelectedFiles();
			try {
				this.reportsCreator.addFiles(files);
				docsCont += files.length;
				this.jlDocumentsCounter.setText("Documentos cargados: "+String.valueOf(docsCont));
			} catch (FileException e) {
				JOptionPane.showMessageDialog(this, e);
				e.printStackTrace();
			}
		}
	}                                             
	
	private void jbGenerateActionPerformed(java.awt.event.ActionEvent evt) {                                           
		this.reportsCreator.generate(this.jtfDestinationFolder.getText(), this);                                        
		//this.reportsCreator.generate("resources/", this);
	}    

    private void jbSelectDestinationFolderActionPerformed(java.awt.event.ActionEvent evt) {    
    	JFileChooser chooser = new JFileChooser();
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(new File("resources/"));
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			try {
				this.jtfDestinationFolder.setText(selectedFile.getCanonicalPath());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    }     
    private void updateDoctorsTable() throws FileException {
    	doctorsData.clear();
    	for(Doctor doctor:this.doctorManager) {
    		Vector<Object> v = new Vector<Object>();
    		v.add(doctor.getCMP());
    		v.add(doctor.getFullname());
    		this.doctorsTableModel.addRow(v);
    	}
    	this.jTableDoctors.repaint();
    	doctorManager.saveToFile(Constants.DOCTORS_OUT_FILE);
    }

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
            java.util.logging.Logger.getLogger(LauncherGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LauncherGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LauncherGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LauncherGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LauncherGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableDoctors;
    private javax.swing.JButton jbAddDoctor;
    private javax.swing.JButton jbAddDocument;
    private javax.swing.JButton jbGenerate;
    private javax.swing.JButton jbRemoveDoctor;
    private javax.swing.JButton jbSelectDestinationFolder;
    private javax.swing.JLabel jlDocumentsCounter;
    private javax.swing.JPanel jpDocsGeneration;
    private javax.swing.JPanel jpDoctorManager;
    private javax.swing.JTable jtModifiers;
    private javax.swing.JTextField jtfCMP;
    private javax.swing.JTextField jtfDestinationFolder;
    private javax.swing.JTextField jtfFullName;
    // End of variables declaration                   
}

