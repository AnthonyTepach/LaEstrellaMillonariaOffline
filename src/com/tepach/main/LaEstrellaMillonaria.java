package com.tepach.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tepach.bean.Boleto;
import com.tepach.bean.Usuario;
import com.tepach.service.LeerBase;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class LaEstrellaMillonaria extends JFrame {

	private JPanel contentPane;
	private JTextField jtf_user;
	private JPasswordField jpf_contrasenia;
	private JLabel jlb_error;
	private int cont = 0;
	private int cont2 = 1;
	private JPanel jpn_login;
	private JPanel jpn_Escaneo;
	private static String user;
	private static String pass;
	private JTextField jtf_folescaneo;
	private JLabel lblFolio;
	private JTextField jtf_ruta;
	private JButton btnCargarBase;
	private JLabel lblUsuario_1;
	private Boleto boleto;
	private String antes;
	private JList jlist_escaneados;
	private DefaultListModel<String> modelEscaneados;
	private DefaultListModel<String> modelRepetido;
	private JLabel lblFoliosEscaneados;
	private JList jlist_repetidos;
	DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private JLabel lblEscaneosErroneos;
	private JButton btnExportar_escaneados;
	private JButton btnExportar_falos;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaEstrellaMillonaria frame = new LaEstrellaMillonaria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LaEstrellaMillonaria() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(LaEstrellaMillonaria.class.getResource("/com/tepach/imagen/laEstrella.jpg")));

		setResizable(false);
		setTitle("La Estrella Millonaria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 401);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setForeground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		modelEscaneados = new DefaultListModel();

		modelRepetido = new DefaultListModel();
		
				jpn_Escaneo = new JPanel();
				jpn_Escaneo.setBackground(Color.WHITE);
				jpn_Escaneo.setBounds(0, 0, 531, 372);
				contentPane.add(jpn_Escaneo);
				jpn_Escaneo.setLayout(null);
				jpn_Escaneo.setVisible(false);
				
						jtf_folescaneo = new JTextField();
						jtf_folescaneo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								cont++;
								String a = jtf_folescaneo.getText();

								int posicion = getIndex(a);

								if (posicion != -1) {

									boleto.getEscaneo()
											.add(boleto.getFolio().get(posicion) + "\t" + boleto.getKit().get(posicion) + "\t"
													+ boleto.getCantidad().get(posicion) + "\t" + user + "\t"
													+ hourdateFormat.format(new Date()));
									boleto.getFolio().remove(posicion);
									boleto.getCantidad().remove(posicion);
									boleto.getKit().remove(posicion);
									cargarLimoiar();
								} else {
									String contra = "Estrella2018";
									String val;
									boleto.getRepetido().add(jtf_folescaneo.getText() + "\t" + lblUsuario_1.getText() + "\t"
											+ hourdateFormat.format(new Date()));
									cargarLimoiar2();
									do {
										val = JOptionPane.showInputDialog(contentPane,
												"El Folio: " + a + "\nYa se escaneo con anterioridad.",
												"Verifique consulte al supervisor en turno", JOptionPane.ERROR_MESSAGE);

									} while (!val.equals(contra));
								}
								if (cont == 10) {
									cont = 0;
									cont2++;
								}
								jtf_folescaneo.setText("");
								jtf_folescaneo.setFocusable(true);
							}
							
						});
						jtf_folescaneo.setEnabled(false);
						jtf_folescaneo.setHorizontalAlignment(SwingConstants.CENTER);
						jtf_folescaneo.setFont(new Font("Tahoma", Font.PLAIN, 31));
						jtf_folescaneo.setBounds(10, 111, 510, 53);
						jpn_Escaneo.add(jtf_folescaneo);
						jtf_folescaneo.setColumns(10);
						jlist_escaneados = new JList();
						jlist_escaneados.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
						jlist_escaneados.setModel(modelEscaneados);
						jlist_escaneados.setValueIsAdjusting(true);
						jlist_escaneados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						jlist_escaneados.setBounds(10, 200, 240, 149);
						jpn_Escaneo.add(jlist_escaneados);
						
								lblFolio = new JLabel("Folio a escanear\r\n");
								lblFolio.setFont(new Font("Tahoma", Font.PLAIN, 30));
								lblFolio.setBounds(10, 72, 240, 36);
								jpn_Escaneo.add(lblFolio);
								
										jtf_ruta = new JTextField();
										jtf_ruta.setEnabled(false);
										jtf_ruta.setBounds(10, 11, 389, 20);
										jpn_Escaneo.add(jtf_ruta);
										jtf_ruta.setColumns(10);
										
												btnCargarBase = new JButton("Cargar Base");
												btnCargarBase.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														try {
															abrirRuta();
														} catch (Exception e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													}
												});
												btnCargarBase.setBounds(409, 10, 111, 23);
												jpn_Escaneo.add(btnCargarBase);
												
														lblUsuario_1 = new JLabel("Usuario: ");
														lblUsuario_1.setBounds(10, 42, 510, 14);
														jpn_Escaneo.add(lblUsuario_1);
														
																lblFoliosEscaneados = new JLabel("Folios escaneados");
																lblFoliosEscaneados.setBounds(10, 185, 157, 14);
																jpn_Escaneo.add(lblFoliosEscaneados);
																jlist_repetidos = new JList();
																jlist_repetidos.setModel(modelRepetido);
																jlist_repetidos.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
																jlist_repetidos.setBounds(260, 201, 260, 148);
																jpn_Escaneo.add(jlist_repetidos);
																
																		lblEscaneosErroneos = new JLabel("Escaneos erroneos");
																		lblEscaneosErroneos.setBounds(260, 185, 157, 14);
																		jpn_Escaneo.add(lblEscaneosErroneos);
																		
																				btnExportar_escaneados = new JButton("Exportar");
																				btnExportar_escaneados.addActionListener(new ActionListener() {
																					public void actionPerformed(ActionEvent e) {
																						LeerBase leer = new LeerBase();
																						leer.ExportarEscaneados(boleto, gusrdar());
																					}
																				});
																				btnExportar_escaneados.setBounds(161, 349, 89, 23);
																				jpn_Escaneo.add(btnExportar_escaneados);
																				
																						btnExportar_falos = new JButton("Exportar");
																						btnExportar_falos.addActionListener(new ActionListener() {
																							public void actionPerformed(ActionEvent e) {
																								LeerBase leer = new LeerBase();
																								leer.ExportarError(boleto, gusrdar());
																							}
																						});
																						btnExportar_falos.setBounds(432, 349, 89, 23);
																						jpn_Escaneo.add(btnExportar_falos);
																						
																								jpn_login = new JPanel();
																								jpn_login.setBackground(SystemColor.textHighlight);
																								jpn_login.setBounds(29, 0, 169, 372);
																								contentPane.add(jpn_login);
																								jpn_login.setLayout(null);
																								
																										JLabel lblUsuario = new JLabel("Usuario");
																										lblUsuario.setForeground(SystemColor.text);
																										lblUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
																										lblUsuario.setBounds(5, 148, 86, 14);
																										jpn_login.add(lblUsuario);
																										
																												JLabel lblNewLabel = new JLabel("Contraseña");
																												lblNewLabel.setForeground(SystemColor.text);
																												lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
																												lblNewLabel.setBounds(5, 196, 86, 14);
																												jpn_login.add(lblNewLabel);
																												
																														jtf_user = new JTextField();
																														jtf_user.setForeground(SystemColor.text);
																														jtf_user.setBackground(SystemColor.textHighlight);
																														jtf_user.setFont(new Font("Arial", Font.PLAIN, 13));
																														jtf_user.setBounds(5, 163, 154, 20);
																														jpn_login.add(jtf_user);
																														jtf_user.setColumns(10);
																														
																																jpf_contrasenia = new JPasswordField();
																																jpf_contrasenia.setForeground(SystemColor.text);
																																jpf_contrasenia.setBackground(SystemColor.textHighlight);
																																jpf_contrasenia.setFont(new Font("Arial", Font.PLAIN, 13));
																																jpf_contrasenia.setBounds(5, 210, 154, 20);
																																jpn_login.add(jpf_contrasenia);
																																
																																		JButton btn_Login = new JButton("Acceder");
																																		btn_Login.setIcon(new ImageIcon(LaEstrellaMillonaria.class.getResource("/com/tepach/imagen/cloud-computing-32.png")));
																																		btn_Login.setForeground(SystemColor.textText);
																																		btn_Login.addActionListener(new ActionListener() {
																																			public void actionPerformed(ActionEvent arg0) {
																																				login();
																																			}
																																		});
																																		btn_Login.setFont(new Font("Arial", Font.PLAIN, 12));
																																		btn_Login.setBounds(47, 241, 112, 23);
																																		jpn_login.add(btn_Login);
																																		
																																				jlb_error = new JLabel("");
																																				jlb_error.setVerticalAlignment(SwingConstants.TOP);
																																				jlb_error.setFont(new Font("Arial", Font.BOLD, 10));
																																				jlb_error.setForeground(SystemColor.text);
																																				jlb_error.setHorizontalAlignment(SwingConstants.CENTER);
																																				jlb_error.setBounds(5, 275, 154, 52);
																																				jpn_login.add(jlb_error);
																																				
																																				lblNewLabel_1 = new JLabel("Iniciar sesión");
																																				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
																																				lblNewLabel_1.setForeground(SystemColor.text);
																																				lblNewLabel_1.setIcon(new ImageIcon(LaEstrellaMillonaria.class.getResource("/com/tepach/imagen/cabinet.png")));
																																				lblNewLabel_1.setBounds(5, 11, 154, 85);
																																				jpn_login.add(lblNewLabel_1);
																																				
																																				lblNewLabel_2 = new JLabel("");
																																				lblNewLabel_2.setIcon(new ImageIcon(LaEstrellaMillonaria.class.getResource("/com/tepach/imagen/users.png")));
																																				lblNewLabel_2.setBounds(321, 130, 75, 113);
																																				contentPane.add(lblNewLabel_2);
	}

	private void login() {
		Usuario us = new Usuario();
		if (validarFields()) {
			user = jtf_user.getText();
			pass = String.valueOf(jpf_contrasenia.getPassword());
			if (us.getUsuarios().containsKey(user)) {

				if (us.getUsuarios().get(user).equals(pass)) {
					cont = 0;
					System.out.println("Existe");
					jpn_login.setVisible(false);
					jpn_Escaneo.setBounds(0, 0, 531, 372);
					lblUsuario_1.setText("Usuario: " + user);
					jpn_Escaneo.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(contentPane, "La Contraseña es Incorrecta",
							"Error: problemas con la contraseña", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(contentPane, "El Usuario no Existe", "Error: problemas con el usuario",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {

			cont++;
			jlb_error.setText("Ingresa todos los datos");
			System.out.println(cont);
			if (cont == 3) {
				cont = 0;
				JOptionPane.showMessageDialog(contentPane, "Has alcansado el total de intentos", "Saliendo del sistema",
						JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}

	@SuppressWarnings("deprecation")
	private boolean validarFields() {
		return (!jtf_user.getText().isEmpty() && !jpf_contrasenia.getText().isEmpty());
	}

	private void abrirRuta() throws Exception {
		LeerBase leerBase = new LeerBase();
		FileDialog dialogoArchivo;
		dialogoArchivo = new FileDialog(this, "Selecciona la Base de datos 'LA ESTRELLA MILLONARIA'", FileDialog.LOAD);
		dialogoArchivo.setVisible(true);
		String ruta = dialogoArchivo.getDirectory() + dialogoArchivo.getFile();
		System.out.println(ruta);
		jtf_ruta.setText(ruta);
		boleto = leerBase.guardaInfoArray(ruta);
		jtf_folescaneo.enable();
		jtf_folescaneo.setFocusable(true);
		btnCargarBase.setEnabled(false);
	}

	private int getIndex(String r) {
		int index = -1;
		int bound = boleto.getFolio().size();
		for (int userInd = 0; userInd < bound; userInd++) {
			if (boleto.getFolio().get(userInd).equals(r)) {
				index = userInd;
				break;
			}
		}
		return index;
	}

	public void cargarLimoiar() {
		modelEscaneados.clear();
		for (int i = 0; i < boleto.getEscaneo().size(); i++) {
			modelEscaneados.addElement(boleto.getEscaneo().get(i));
			// System.out.println(boleto.getEscaneo().get(i));
		}
	}

	public void cargarLimoiar2() {
		modelRepetido.clear();
		for (int i = 0; i < boleto.getRepetido().size(); i++) {
			modelRepetido.addElement(boleto.getRepetido().get(i));
			// System.out.println(boleto.getRepetido().get(i));
		}
	}

	String gusrdar() {
		FileDialog dialogoArchivo;
		dialogoArchivo = new FileDialog(this, "Guardar como...", FileDialog.SAVE);
		dialogoArchivo.setVisible(true);
		String ruta = dialogoArchivo.getDirectory() + dialogoArchivo.getFile() + ".txt";
		return ruta;
	}
}
