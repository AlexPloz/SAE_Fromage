import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class factureEdition {
	private static DefaultTableModel model;
	private JDialog frame;
	private JTable table;

	// vars = 0.NOM , 1.PRENOM , 2.ADR1 , 3.ADR2 , 4.CP , 5.VILLE , 6.TEL , 7.MAIL

	/**
	 * Create the application.
	 */
	public factureEdition(String[] vars, List<Article> panier, float port) {
		initialize(vars, panier, port);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] vars, List<Article> panier, float port) {
		float prixTotal=0.0F;
		for (Article articleDansPanier : panier) {
			prixTotal+=articleDansPanier.getPrixTTC()*articleDansPanier.getQuantitéEnStock();
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.FRENCH);
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("H:m:s", Locale.FRENCH);
		SimpleDateFormat simpleTimeZoneFormat = new SimpleDateFormat("zzzz", Locale.FRENCH);

		String date = simpleDateFormat.format(new Date());
		String heure = simpleTimeFormat.format(new Date());
		String timeZone = simpleTimeZoneFormat.format(new Date());

		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setModal(true);
		frame.setBounds(100, 100, 660, 663);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel fenetre = new JPanel();
		frame.getContentPane().add(fenetre);
		fenetre.setLayout(new BorderLayout(0, 0));

		JPanel panel_bas = new JPanel();
		fenetre.add(panel_bas, BorderLayout.SOUTH);
		panel_bas.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_bas.add(panel_2, BorderLayout.EAST);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.dispose();
				accueil.fermer();
				Panier.fermer();
				facture.fermer();
			}
		});
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnQuitter);
		
				JLabel TextFin = new JLabel("                     En vous remerciant, la fromagerie d'Alos.");
				panel_bas.add(TextFin, BorderLayout.CENTER);

		JPanel contenu = new JPanel();
		fenetre.add(contenu, BorderLayout.CENTER);
		contenu.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contenu.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("INFORMATIONS CLIENT :");
		panel.add(lblNewLabel_3, BorderLayout.WEST);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(5, 0, 0, 0));

		JLabel PrenomNom = new JLabel(vars[0] + " " + vars[1]);
		panel_1.add(PrenomNom);

		JLabel Address = new JLabel(vars[2] + " " + vars[3]);
		panel_1.add(Address);

		JLabel CPVille = new JLabel(vars[4] + " " + vars[5] + " CEDEX");
		panel_1.add(CPVille);

		JLabel Tel = new JLabel("N\u00B0 t\u00E9l\u00E9phone : " + vars[6]);
		panel_1.add(Tel);

		JLabel Mail = new JLabel("@mail : " + vars[7]);
		panel_1.add(Mail);
		
				JLabel lblNewLabel_5 = new JLabel("RECAPITULATIF COMMANDE :");
				panel.add(lblNewLabel_5, BorderLayout.SOUTH);

		JPanel panel_4 = new JPanel();
		contenu.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(2, 0, 0, 0));

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "Denomination", "Quantitée", "Prix"},
			},
			new String[] {
				"id", "nom", "quantit\u00E9", "prix"
			}
		));
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		for (Article articleDansPanier : panier) {
			model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] { table.getRowCount(),
					articleDansPanier.toString(),
					"quantité commandé:"+articleDansPanier.getQuantitéEnStock(),
					(articleDansPanier.getPrixTTC() * articleDansPanier.getQuantitéEnStock())+"€" });
		}
		panel_4.add(table);

		JPanel panel_6 = new JPanel();
		contenu.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
				JPanel panel_3 = new JPanel();
				panel_6.add(panel_3);
				panel_3.setLayout(new GridLayout(3, 0, 0, 0));
				
						JLabel PrixCommande = new JLabel("TOTAL TTC COMMANDE : "+prixTotal);
						panel_3.add(PrixCommande);
						
								JLabel Transport = new JLabel("TRANSPORT :"+port);
								panel_3.add(Transport);
								
										JLabel Total = new JLabel("PRIX TOTAL TTC : "+(port+prixTotal));
										panel_3.add(Total);

		JPanel panel_5 = new JPanel();
		fenetre.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new GridLayout(2, 1, 0, 20));

		JLabel TextDeb1 = new JLabel("                     Fromagerie d'Alos pour vous servir avec plaisir");
		panel_5.add(TextDeb1);

		JLabel TextDeb2 = new JLabel("Toulouse, le " + date + " à " + heure + " " + timeZone + ".");
		panel_5.add(TextDeb2);
	}

}
