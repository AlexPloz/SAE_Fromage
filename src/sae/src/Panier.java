package sae.src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Panier {

	private static JDialog frmFromageriePanier;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static DefaultTableModel model;
	private static JRadioButton rdbtnChronopost;
	private static JRadioButton rdbtnColissimoDomicile;
	private static float prixPort=0.0F;

	/**
	 * Create the application.
	 */
	public Panier(List<Article> panier) {
		initialize(panier);
		frmFromageriePanier.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<Article> panier) {
		frmFromageriePanier = new JDialog();
		frmFromageriePanier.setModalityType(ModalityType.APPLICATION_MODAL);
		frmFromageriePanier.setModal(true);
		frmFromageriePanier.setTitle("Fromagerie - Panier");
		frmFromageriePanier.setType(Type.POPUP);
		frmFromageriePanier.setBounds(100, 100, 617, 547);
		frmFromageriePanier.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmFromageriePanier.getContentPane().setLayout(new BorderLayout(50, 20));

		JPanel panel = new JPanel();
		frmFromageriePanier.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

		JButton btnValider = new JButton("Valider le panier");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new facture(panier,prixPort);
			}
		});
		btnValider.setIcon(new ImageIcon(Panier.class.getResource("/Images/valider.png")));
		panel.add(btnValider);

		JButton btnContinuer = new JButton("Continuer les achats");
		btnContinuer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frmFromageriePanier.dispose();
			}
		});
		btnContinuer.setIcon(new ImageIcon(Panier.class.getResource("/Images/continuer.png")));
		panel.add(btnContinuer);
		
		JButton btnAnnuler = new JButton("Vider le panier");
		btnAnnuler.setIcon(new ImageIcon(Panier.class.getResource("/Images/annumer.png")));
		panel.add(btnAnnuler);

		JPanel panel_1 = new JPanel();
		frmFromageriePanier.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(3, 1, 0, 0));

		JRadioButton rdbtnColissimoRelais = new JRadioButton("Colissimo en point Relais 4.9\u20AC");
		rdbtnColissimoRelais.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				model.removeRow(model.getRowCount() - 2);
				model.removeRow(model.getRowCount() - 1);
				prixPort = 0;
				prixPort = 4.9F;
				float prixTotal = 0.0F;
				for (Article articleDansPanier : panier) {
					prixTotal += articleDansPanier.getPrixTTC() * articleDansPanier.getQuantitéEnStock();
				}
				model.addRow(new Object[] { null, null, "FRAIS DE PORT",  prixPort + "€" });
				model.addRow(new Object[] { null, null, "TOTAL TTC", prixTotal + prixPort + "€" });
			}
		});
		buttonGroup.add(rdbtnColissimoRelais);
		panel_3.add(rdbtnColissimoRelais);

		rdbtnColissimoDomicile = new JRadioButton("Colissimo \u00E0 domicile 4.9\u20AC");
		rdbtnColissimoDomicile.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				model.removeRow(model.getRowCount() - 2);
				model.removeRow(model.getRowCount() - 1);
				prixPort = 0;
				prixPort = 4.9F;
				float prixTotal = 0.0F;
				for (Article articleDansPanier : panier) {
					prixTotal += articleDansPanier.getPrixTTC() * articleDansPanier.getQuantitéEnStock();
				}
				model.addRow(new Object[] { null, null, "FRAIS DE PORT", prixPort + "€" });
				model.addRow(new Object[] { null, null, "TOTAL TTC", prixTotal + prixPort + "€" });

			}
		});
		buttonGroup.add(rdbtnColissimoDomicile);
		panel_3.add(rdbtnColissimoDomicile);

		rdbtnChronopost = new JRadioButton("Chronopost 9.9\u20AC");
		rdbtnChronopost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeRow(model.getRowCount() - 2);
				model.removeRow(model.getRowCount() - 1);
				prixPort = 0;
				prixPort = 9.9F;
				float prixTotal = 0.0F;
				for (Article articleDansPanier : panier) {
					prixTotal += articleDansPanier.getPrixTTC() * articleDansPanier.getQuantitéEnStock();
				}
				model.addRow(new Object[] { null, null, "FRAIS DE PORT", prixPort + "€" });
				model.addRow(new Object[] { null, null, "TOTAL TTC", prixTotal + prixPort + "€" });
			
			}
		});
		rdbtnChronopost.setSelected(true);
		buttonGroup.add(rdbtnChronopost);
		panel_3.add(rdbtnChronopost);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblProduit = new JLabel("Produit");
		lblProduit.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduit.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(lblProduit);

		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(lblPrix);

		JLabel lblQuantite = new JLabel("Quantit\u00E9");
		lblQuantite.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantite.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(lblQuantite);

		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(lblTotal);

		table = new JTable();
		table.setEnabled(false);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Produit", "Prix", "Quantite", "TOTAL" }));
		for (Article articleDansPanier : panier) {
			model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] { articleDansPanier.getFromage().getDésignation() + articleDansPanier.getClé(),
					accueil.df.format(articleDansPanier.getPrixTTC()), articleDansPanier.getQuantitéEnStock(),
					accueil.df.format(articleDansPanier.getPrixTTC() * articleDansPanier.getQuantitéEnStock()) });
		}
		model = (DefaultTableModel) table.getModel();
		float prixTotal = 0.0F;
		for (Article articleDansPanier : panier) {
			prixTotal += articleDansPanier.getPrixTTC() * articleDansPanier.getQuantitéEnStock();
		}
		prixPort = 0;
		if (rdbtnChronopost.isEnabled()) {
			prixPort = 9.9F;
		} else if (rdbtnColissimoDomicile.isEnabled()) {
			prixPort = 4.9F;
		} else if (rdbtnColissimoRelais.isEnabled()) {
			prixPort = 4.9F;
		}
		model.addRow(new Object[] { null, null, "SOUS TOTAL TTC", prixTotal + "€" });
		model.addRow(new Object[] { null, null, "FRAIS DE PORT", 9.9F + "€" });
		model.addRow(new Object[] { null, null, "TOTAL TTC", prixTotal + prixPort + "€" });
		panel_2.add(table);

		JLabel lblVotrePanier = new JLabel("Votre panier");
		lblVotrePanier.setHorizontalAlignment(SwingConstants.LEFT);
		lblVotrePanier.setIcon(new ImageIcon(Panier.class.getResource("/Images/panier2.png")));
		lblVotrePanier.setFont(new Font("Stencil", Font.PLAIN, 19));
		frmFromageriePanier.getContentPane().add(lblVotrePanier, BorderLayout.NORTH);
		
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				accueil.viderPanier();
				frmFromageriePanier.dispose();
			}
		});
		
	}
	public static void fermer() {
		frmFromageriePanier.dispose();
	}
}
