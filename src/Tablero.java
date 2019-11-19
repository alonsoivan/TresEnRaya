
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Tablero extends JFrame implements ActionListener,MouseListener{

	private int j1 = 0, j2 = 0;
	private JLabel texto = new JLabel(j1+" - "+j2);
	private int cont = 0;
	private ArrayList<JButton> pulsaciones= new ArrayList<JButton>();
	
	JLabel nombreJugador1 = new JLabel(" Jugador 1: ");
	JLabel nombreJugador2 = new JLabel(" Jugador 2: ",JLabel.RIGHT);
	JTextField pedirJ1 = new JTextField("Pepe");
	JTextField pedirJ2 = new JTextField("Juan");
	
	JButton aceptar = new JButton("Aceptar");
	private JButton[] btTablero = new JButton[9];
	
	public Tablero(String jugador1, String jugador2) {
		super("Tres en raya");
		setLayout(new BorderLayout());
		setSize(620,700);
		
		crearCabecera(jugador1,jugador2);
		crearCentro();
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public Tablero() {
		super("Tres en raya");
		setLayout(new GridLayout(5,1));
		setSize(300,300);
		
		this.add(nombreJugador1);
		nombreJugador1.setFont(new Font("Monospaced", Font.BOLD, 35));
		this.add(pedirJ1);
		pedirJ1.setFont(new Font("Monospaced", Font.ITALIC, 35));
		pedirJ1.setHorizontalAlignment(JTextField.CENTER);
		this.add(nombreJugador2);
		nombreJugador2.setFont(new Font("Monospaced", Font.BOLD, 35));
		nombreJugador2.setHorizontalAlignment(JLabel.LEFT);
		this.add(pedirJ2);
		pedirJ2.setFont(new Font("Monospaced", Font.ITALIC, 35));
		pedirJ2.setHorizontalAlignment(JTextField.CENTER);
		this.add(aceptar);
		aceptar.setFont(new Font("Monospaced", Font.BOLD, 35));
		
		aceptar.addActionListener(this);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void crearCabecera(String nombreJ1, String nombreJ2) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		nombreJugador1.setText(" "+nombreJ1);
		nombreJugador2.setText(nombreJ2+" ");
		
		texto.setFont(new Font("Monospaced", Font.BOLD, 45));
		nombreJugador1.setFont(new Font("Monospaced", Font.BOLD, 35));
		nombreJugador2.setFont(new Font("Monospaced", Font.BOLD, 35));
		texto.setForeground(Color.white);
		nombreJugador1.setForeground(Color.white);
		nombreJugador2.setForeground(Color.white);
		
		JPanel pp = new JPanel(new FlowLayout());
		pp.add(texto);
		
		nombreJugador1.setPreferredSize(new Dimension(220,40));
		nombreJugador2.setPreferredSize(new Dimension(220,40));
		
		p.add(nombreJugador1, BorderLayout.WEST);
		p.add(pp,BorderLayout.CENTER);
		p.add(nombreJugador2, BorderLayout.EAST);
		
		p.setBackground(Color.DARK_GRAY);
		pp.setBackground(Color.DARK_GRAY);
		pp.setForeground(Color.white);
		
		this.add(p, BorderLayout.NORTH);
	}
	
	public void crearCentro() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,3));
		
		for (int i=0; i<btTablero.length;i++) {
			btTablero[i] = new JButton("-");
			btTablero[i].setFont(new Font("Monospaced", Font.BOLD, 200));
			p.add(btTablero[i]);
			btTablero[i].setFocusable(false);
			btTablero[i].setBackground(Color.LIGHT_GRAY);
			btTablero[i].setForeground(Color.white);
			btTablero[i].addActionListener(this);
			btTablero[i].addMouseListener(this);
			btTablero[i].setBorderPainted(false);
		}
		
		this.add(p, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == aceptar){
			new Tablero(pedirJ1.getText(),pedirJ2.getText());
			this.dispose();
		}else{
			if(!pulsaciones.contains(e.getSource())){
				
				pulsaciones.add((JButton) e.getSource());
				JButton bt = (JButton) e.getSource();
				if (cont++%2 == 0)
					bt.setText("X");
				else
					bt.setText("O");
				
				comprobarTablero(3);
				comprobarTablero(1);
				comprobarTablero(5);
				comprobarTablero(6);
				
				if(cont == 9) 
					reset(0);		
			}
		}
	}
	
	public void comprobarTablero(int comprobador) {
		boolean ganador = false;
		int a = 0, b = 0, c = 0 , d = 0;
		switch(comprobador) {
			case 3:
				a = 0; b = 1; c = 2; d = 6;
				break;
			case 1: 
				a = 0; b = 3; c = 6; d = 2;
				break;
			case 5:
				a = 0; b = 4; c = 8; d = 0;
				break;
			case 6:
				a = 2; b = 4; c = 6; d = 0;
				break;
		}
		
		int i = 0;
		while(!ganador && i<=d) {
			if (btTablero[a+i].getText() == btTablero[b+i].getText() && btTablero[b+i].getText() == btTablero[c+i].getText()) {
				
				if (btTablero[b+i].getText()!="-") {
					
					btTablero[a+i].setBackground(Color.red);
					btTablero[b+i].setBackground(Color.red);
					btTablero[c+i].setBackground(Color.red);
					
					if (cont%2 == 0) {
						j2++;
						reset(nombreJugador2.getText());
					}else {
						j1++;
						reset(nombreJugador1.getText());
					}
					ganador = true;	
				}
			}
			i = i + comprobador;
		}
	}
	
	public void reset(int x) {
		
		if( x == 0)
			JOptionPane.showMessageDialog(this, "EMPATE");
		
		for (int i = 0; i<btTablero.length;i++) {
			btTablero[i].setText("-");
			btTablero[i].setBackground(Color.LIGHT_GRAY);
		}

		pulsaciones.clear();
		cont = 0;
		
	}
	
	public void reset(String ganador) {	
	
		texto.setText(j1+" - "+j2);	
		JOptionPane.showMessageDialog(this, ganador+" GANÓ ESTA RONDA");
		
		reset(1);	
	}
	
	public static void main(String[] args) {
		new Tablero();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		JButton bt = (JButton) arg0.getSource();
		bt.setBackground(Color.DARK_GRAY);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		JButton bt = (JButton) arg0.getSource();
		bt.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		JButton bt = (JButton) arg0.getSource();
		bt.setBackground(Color.DARK_GRAY);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
