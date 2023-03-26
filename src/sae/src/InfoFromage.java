package sae.src;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

public class InfoFromage {

	private static JDialog frmFromagerieDescription;

	/**
	 * Create the application.
	 */
	public InfoFromage(Fromage arg, JFrame parent) {
		initialize(arg, parent);
		frmFromagerieDescription.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Fromage Fromton, JFrame parent) {
		frmFromagerieDescription = new JDialog(parent, true);
		frmFromagerieDescription.setModal(true);
		frmFromagerieDescription.setType(Type.POPUP);
		frmFromagerieDescription.setBounds(100, 100, 566, 375);
		frmFromagerieDescription.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


		JPanel panel = new JPanel();
		frmFromagerieDescription.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);

		JSpinner spinner = new JSpinner();
		Article articleTemp = (Article) Fromton.getArticles().get(0);
		spinner.setModel(new SpinnerNumberModel(0, 0, articleTemp.getQuantitéEnStock(), 1));

		JComboBox<Article> comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			int qte = 0;

			public void actionPerformed(ActionEvent e) {
				Article articleSelectionne = (Article) comboBox.getSelectedItem();
				qte = articleSelectionne.getQuantitéEnStock();
				spinner.setModel(new SpinnerNumberModel(1, 1, qte, 1));
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		comboBox.setModel(new DefaultComboBoxModel(Fromton.getArticles().toArray()));

		panel_1.add(comboBox);
		panel_1.add(spinner);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAjoutPanier = new JButton("Ajouter au panier");
		btnAjoutPanier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) throws IllegalArgumentException {
				try {
					Article articleSelectionne = (Article) comboBox.getSelectedItem();
					accueil.ajoutDansPanier(articleSelectionne,(int)spinner.getValue());
					accueil.actualisationListeFromage(articleSelectionne,articleSelectionne.getQuantitéEnStock()-(int)spinner.getValue());
					frmFromagerieDescription.dispose();
				} catch (Exception e2) {
					System.out.println(e2);
				}
				
			}
		});
		btnAjoutPanier.setIcon(new ImageIcon("C:\\Users\\alexa\\eclipse-workspace\\SAEFacture\\src\\Images\\plus.png"));
		panel_2.add(btnAjoutPanier);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frmFromagerieDescription.dispose();
			}
		});
		btnAnnuler.setIcon(new ImageIcon(InfoFromage.class.getResource("/Images/annumer.png")));
		panel_2.add(btnAnnuler);

		JScrollBar scrollBar = new JScrollBar();
		frmFromagerieDescription.getContentPane().add(scrollBar, BorderLayout.EAST);

		JTextPane txtpnBoubuole = new JTextPane();
		txtpnBoubuole.setText(Fromton.getDescription());
		frmFromagerieDescription.getContentPane().add(txtpnBoubuole, BorderLayout.CENTER);
		frmFromagerieDescription.setTitle("Fromagerie - Description fromage");
	}
	public static void fermer() {
		frmFromagerieDescription.dispose();
	}
}
