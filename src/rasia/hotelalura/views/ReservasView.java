package rasia.hotelalura.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import net.bytebuddy.asm.Advice.This;
import rasia.hotelalura.dao.ReservationDAO;
import rasia.hotelalura.dao.DailyRateDAO;
import rasia.hotelalura.entity.Reservation;
import rasia.hotelalura.util.JPAUtil;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import java.text.Format;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtDataE;
	public static JDateChooser txtDataS;
	public static JComboBox<String> txtFormaPagamento;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel lblValorSimbolo;
	private JLabel labelAtras;
	
	private Reservation reservation = new Reservation();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/* *************************************** */
	public ReservasView(Reservation reservation) {
		this();
		this.reservation = reservation;
		
		txtDataE.setDate(reservation.getCheckInDate());
		txtDataS.setDate(reservation.getCheckOutDate());
		txtValor.setText(reservation.getPrice().toString());
		txtFormaPagamento.setSelectedItem(reservation.getPaymentOption());
	}
	/* *************************************** */
	


	/**
	 * Create the frame.
	 */
	public ReservasView() {
		super("Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		/* *************************************** */
		// precisava instanciar o txtValor antes da txtDataS
		// para manter a ordem deste metodo alterei para instanciar todos componentes primeiro 
		JSeparator separator_1_2 = new JSeparator();
		JSeparator separator_1_3 = new JSeparator();
		JSeparator separator_1_1 = new JSeparator();
		txtDataE = new JDateChooser();
		lblValorSimbolo = new JLabel("$");
		JLabel lblCheckIn = new JLabel("DATA DE CHECK IN");
		JLabel lblCheckOut = new JLabel("DATA DE CHECK OUT");
		txtDataS = new JDateChooser();
		txtValor = new JTextField();
		JLabel lblValor = new JLabel("VALOR DA RESERVA");
		txtFormaPagamento = new JComboBox();
		JLabel lblFormaPago = new JLabel("FORMA DE PAGAMENTO");
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		JPanel panel_1 = new JPanel();
		JLabel logo = new JLabel("");
		JLabel imagenFondo = new JLabel("");
		JPanel btnexit = new JPanel();
		labelExit = new JLabel("X");
		JPanel header = new JPanel();
		JPanel btnAtras = new JPanel();
		labelAtras = new JLabel("<");
		JSeparator separator_1 = new JSeparator();
		JPanel btnProximo = new JPanel();
		JLabel lblSeguinte = new JLabel("PRÓXIMO");
		/* *************************************** */

		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);

		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		txtDataE.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtDataE.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtDataE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtDataE.setBounds(68, 161, 289, 35);
		txtDataE.getCalendarButton().setBounds(268, 0, 21, 33);
		txtDataE.setBackground(Color.WHITE);
		txtDataE.setBorder(new LineBorder(SystemColor.window));
		txtDataE.setDateFormatString("dd/MM/yyyy");
		txtDataE.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtDataE);

		lblValorSimbolo.setVisible(false);
		lblValorSimbolo.setBounds(121, 332, 17, 25);
		lblValorSimbolo.setForeground(SystemColor.textHighlight);
		lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));

		panel.add(lblValorSimbolo);

		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 169, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 187, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		txtDataS.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtDataS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtDataS.setBounds(68, 246, 289, 35);
		txtDataS.getCalendarButton().setBounds(267, 1, 21, 31);
		txtDataS.setBackground(Color.WHITE);
		txtDataS.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtDataS.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				// Ativa o evento, após o usuário selecionar as datas, 
				// o valor da reserva deve ser calculado
				/* *************************************** */
				recalcularValorDaReserva();
				/* *************************************** */
			}
		});
		txtDataS.setDateFormatString("dd/MM/yyyy");
		txtDataS.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtDataS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtDataS);

		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(78, 328, 75, 33); // de 43 para 75 se não não exibia o valor total
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);

		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 196, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		txtFormaPagamento.setBounds(68, 417, 289, 38);
		txtFormaPagamento.setBackground(SystemColor.text);
		txtFormaPagamento.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPagamento.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPagamento.setModel(
				new DefaultComboBoxModel(new String[] { "Cartão de Crédito", "Cartão de Débito", "Dinheiro" }));
		panel.add(txtFormaPagamento);

		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 213, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		lblTitulo.setBounds(109, 60, 219, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);

		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);

		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));

		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));

		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);

		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);

		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		btnProximo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasView.txtDataE.getDate() != null && ReservasView.txtDataS.getDate() != null) {
					/* *************************************** */
					recalcularValorDaReserva();
					if(reservation.getId() == null) {
						reservation = 
								new Reservation(txtDataE.getDate(),
								txtDataS.getDate(), 
								new BigDecimal(txtValor.getText()),
								(String)txtFormaPagamento.getSelectedItem());
					} else {
						reservation.setCheckInDate(txtDataE.getDate());
						reservation.setCheckOutDate(txtDataS.getDate());
						reservation.setPrice(new BigDecimal(txtValor.getText()));
						reservation.setPaymentOption((String)txtFormaPagamento.getSelectedItem());
					}

					EntityManager em = JPAUtil.getEntityManager();
					ReservationDAO reservationDAO = new ReservationDAO(em);
					em.getTransaction().begin();
					reservationDAO.save(reservation);
					em.getTransaction().commit();
					
					/* *************************************** */
					RegistroHospede registro = new RegistroHospede(reservation); // alterado para enviar a reserva para a tela de hóspede 
					registro.setVisible(true);
					
					em.close();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Deve preencher todos os campos.");
				}
			}
		});
		btnProximo.setLayout(null);
		btnProximo.setBackground(SystemColor.textHighlight);
		btnProximo.setBounds(238, 493, 122, 35);
		panel.add(btnProximo);
		btnProximo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		lblSeguinte.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeguinte.setForeground(Color.WHITE);
		lblSeguinte.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSeguinte.setBounds(0, 0, 122, 35);
		btnProximo.add(lblSeguinte);
	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e
	// "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
	

	/* *************************************** */
	private void recalcularValorDaReserva() {
		System.out.println("***");
		int dias = calcularNumeroDeDias(txtDataE.getDate(), txtDataS.getDate());
		System.out.println("dias = " + dias);
		// dias *= 100; // 'vergonha da profissão' (Jacquin , Erick)

		EntityManager em = JPAUtil.getEntityManager();
		DailyRateDAO dailyRateDAO = new DailyRateDAO(em);
		BigDecimal dailyRate = dailyRateDAO.getDailyRate();
		System.out.println("diaria = " + dailyRate );
		dailyRate = dailyRate.multiply(new BigDecimal(dias));
		System.out.println("total = " + dailyRate);

		String resultado = dailyRate.toString();
		System.out.println(resultado);
		txtValor.setText(resultado);

	    //txtValor.revalidate();
	    //txtValor.repaint();
		System.out.println("***");
	}
	

	// GPT help, calcular a diferença entre as duas datas
	// problema de receber uma ajuda do GPT é que não tem como deixar comentado o link para a fonte 
	private int calcularNumeroDeDias(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;
		
		// Calculate the difference between the dates in milliseconds
		long differenceMillis = date2.getTime() - date1.getTime();

		// Convert the difference to days
		int differenceDays = (int) (differenceMillis / (24 * 60 * 60 * 1000));

		return ++differenceDays;
	}
	/* *************************************** */
}
