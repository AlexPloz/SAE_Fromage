package sae.src;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;

public class facture {

	private static JDialog frmFromagerieFacture;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldAddress1;
	private JTextField textFieldAddress2;
	private JTextField textFieldCP;
	private JTextField textFieldVille;
	private JTextField textFieldTelephone;
	private JTextField textFieldMail;
	private JButton btnOK;


	/**
	 * Create the application.
	 */
	public facture(List<Article> panier,float port) {
		initialize(panier,port);
		frmFromagerieFacture.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<Article> panier,float port) {
		frmFromagerieFacture = new JDialog();
		frmFromagerieFacture.setModalityType(ModalityType.APPLICATION_MODAL);
		frmFromagerieFacture.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmFromagerieFacture.setModal(true);
		frmFromagerieFacture.setTitle("Fromagerie - Renseignement");
		frmFromagerieFacture.setType(Type.POPUP);
		frmFromagerieFacture.setBounds(100, 100, 519, 383);
		frmFromagerieFacture.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmFromagerieFacture.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelButton = new JPanel();
		frmFromagerieFacture.getContentPane().add(panelButton, BorderLayout.SOUTH);
		panelButton.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panelButton.add(panel_1, BorderLayout.EAST);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frmFromagerieFacture.dispose();
			}
		});
		btnAnnuler.setIcon(new ImageIcon(facture.class.getResource("/Images/annumer.png")));
		panel_1.add(btnAnnuler);

		JPanel contenu = new JPanel();
		frmFromagerieFacture.getContentPane().add(contenu, BorderLayout.CENTER);
		contenu.setLayout(new GridLayout(8, 2, 0, 0));

		JLabel lblNom = new JLabel("  Nom");
		contenu.add(lblNom);

		textFieldNom = new JTextField();
		contenu.add(textFieldNom);
		textFieldNom.setColumns(10);

		JLabel lblPrenom = new JLabel("  Prenom");
		contenu.add(lblPrenom);

		textFieldPrenom = new JTextField();
		contenu.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		JLabel lblAdresse1 = new JLabel("  Adresse 1");
		contenu.add(lblAdresse1);

		textFieldAddress1 = new JTextField();
		contenu.add(textFieldAddress1);
		textFieldAddress1.setColumns(10);

		JLabel lblAdresse2 = new JLabel("  Adresse 2");
		contenu.add(lblAdresse2);

		textFieldAddress2 = new JTextField();
		contenu.add(textFieldAddress2);
		textFieldAddress2.setColumns(10);

		JLabel lblCodePostal = new JLabel("  Code Postal");
		contenu.add(lblCodePostal);

		textFieldCP = new JTextField();
		contenu.add(textFieldCP);
		textFieldCP.setColumns(10);

		JLabel lblVille = new JLabel("  Ville");
		contenu.add(lblVille);

		textFieldVille = new JTextField();
		contenu.add(textFieldVille);
		textFieldVille.setColumns(10);

		JLabel lblTéléphone = new JLabel("  T\u00E9l\u00E9phone");
		contenu.add(lblTéléphone);

		textFieldTelephone = new JTextField();
		contenu.add(textFieldTelephone);
		textFieldTelephone.setColumns(10);

		JLabel lbMail = new JLabel("  Mail");
		contenu.add(lbMail);

		textFieldMail = new JTextField();
		contenu.add(textFieldMail);
		textFieldMail.setColumns(10);

		btnOK = new JButton("OK");
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String[] vars = new String[] { textFieldNom.getText(), textFieldPrenom.getText(),
						textFieldAddress1.getText(), textFieldAddress2.getText(), textFieldCP.getText(),
						textFieldVille.getText(), textFieldTelephone.getText(), textFieldMail.getText() };
				new factureEdition(vars,panier,port);
			}
		});
		btnOK.setSelectedIcon(new ImageIcon(facture.class.getResource("/Images/valider.png")));
		btnOK.setIcon(new ImageIcon(facture.class.getResource("/Images/valider.png")));
		panel_1.add(btnOK);

	}

	public Icon getBtnOKIcon() {
		return btnOK.getIcon();
	}

	public void setBtnOKIcon(Icon icon) {
		btnOK.setIcon(icon);
	}
	
	public static void fermer() {
		frmFromagerieFacture.dispose();
	}
}
