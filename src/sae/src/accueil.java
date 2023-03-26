package sae.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class accueil {
	private static List<Article> panier;
	private static JFrame frmFromagerie;
	private static JLabel lblPanier;
	private static JList jlisteFromage;
	private static Articles listeFromagesInitialisation;
	private Articles listeFromageVache;
	private Articles listeFromageChevre;
	private Articles listeFromageBrebis;
	public static final DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accueil window = new accueil();
					window.frmFromagerie.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public accueil() {
		initialize();
		panier = new LinkedList<>();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.initialisationListeFromage();

		frmFromagerie = new JFrame();
		frmFromagerie.setTitle("Fromagerie");
		frmFromagerie.setBounds(100, 100, 596, 538);
		frmFromagerie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFromagerie.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frmFromagerie.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		frmFromagerie.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_2, BorderLayout.EAST);

		lblPanier = new JLabel("0.0€");
		lblPanier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Panier fenetrePanier = new Panier(panier);
			}

		});
		lblPanier.setIcon(new ImageIcon(accueil.class.getResource("/Images/panier.png")));
		panel_2.add(lblPanier);

		JLabel lblNewLabel_1 = new JLabel("Touts les Fromages");
		lblNewLabel_1.setIcon(new ImageIcon(accueil.class.getResource("/Images/Fromage.png")));
		panel_1.add(lblNewLabel_1, BorderLayout.CENTER);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setHgap(30);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_3, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		frmFromagerie.getContentPane().add(scrollPane, BorderLayout.CENTER);
		List<String> designation = new LinkedList<String>();
		for (Fromage f : listeFromagesInitialisation.getListeFromage()) {
			designation.add(f.getDésignation());
		}
		designation.sort(null);
		jlisteFromage = new JList(designation.toArray());
		jlisteFromage.setBorder(null);
		jlisteFromage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				for (Fromage fromton : listeFromagesInitialisation.getListeFromage()) {
					if (fromton.getDésignation() == jlisteFromage.getSelectedValue()) {
						new InfoFromage(fromton, frmFromagerie);
					}
				}
			}
		});
		scrollPane.setViewportView(jlisteFromage);

		JButton btnVache = new JButton("Vache");
		btnVache.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				List<String> designation = new LinkedList<String>();
				for (Fromage f : listeFromageVache.getListeFromage()) {
					designation.add(f.getDésignation());
				}
				designation.sort(null);
				jlisteFromage = new JList(designation.toArray());
				scrollPane.setViewportView(jlisteFromage);
				jlisteFromage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						for (Fromage i : listeFromageVache.getListeFromage()) {
							if (i.getDésignation() == jlisteFromage.getSelectedValue()) {
								new InfoFromage(i, frmFromagerie);
							}
						}
					}
				});
			}

		});
		btnVache.setIcon(new ImageIcon(accueil.class.getResource("/Images/vache.png")));
		panel.add(btnVache);

		JButton btnChevre = new JButton("Chevre");
		btnChevre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				List<String> designation = new LinkedList<String>();
				for (Fromage f : listeFromageChevre.getListeFromage()) {
					designation.add(f.getDésignation());
				}
				designation.sort(null);
				jlisteFromage = new JList(designation.toArray());
				scrollPane.setViewportView(jlisteFromage);
				jlisteFromage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						for (Fromage i : listeFromageChevre.getListeFromage()) {
							if (i.getDésignation() == jlisteFromage.getSelectedValue()) {
								new InfoFromage(i, frmFromagerie);
							}
						}
					}
				});
			}
		});

		btnChevre.setIcon(new ImageIcon(accueil.class.getResource("/Images/chevre.png")));
		panel.add(btnChevre);

		JButton btnBrebis = new JButton("Brebis");
		btnBrebis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				List<String> designation = new LinkedList<String>();
				for (Fromage f : listeFromageBrebis.getListeFromage()) {
					designation.add(f.getDésignation());
				}
				designation.sort(null);
				jlisteFromage = new JList(designation.toArray());
				scrollPane.setViewportView(jlisteFromage);
				jlisteFromage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						for (Fromage i : listeFromageBrebis.getListeFromage()) {
							if (i.getDésignation() == jlisteFromage.getSelectedValue()) {
								new InfoFromage(i, frmFromagerie);
							}
						}
					}
				});
			}
		});
		btnBrebis.setIcon(new ImageIcon(accueil.class.getResource("/Images/brebis.png")));
		panel.add(btnBrebis);

		JButton btnTous = new JButton("Tous");
		btnTous.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				List<String> designation = new LinkedList<String>();
				for (Fromage f : listeFromagesInitialisation.getListeFromage()) {
					designation.add(f.getDésignation());
				}
				designation.sort(null);
				jlisteFromage = new JList(designation.toArray());
				scrollPane.setViewportView(jlisteFromage);
				jlisteFromage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						for (Fromage i : listeFromagesInitialisation.getListeFromage()) {
							if (i.getDésignation() == jlisteFromage.getSelectedValue()) {
								new InfoFromage(i, frmFromagerie);
							}
						}
					}
				});
			}
		});
		panel.add(btnTous);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		frmFromagerie.getContentPane().add(panel_4, BorderLayout.WEST);

	}

	public static void ajoutDansPanier(Article nouvelleArticle, int qte) throws IllegalArgumentException {
		if (qte == 0) {
			InfoFromage.fermer();
			throw new IllegalArgumentException("La quantité de formage est de 0");
		} else {
			boolean existe = false;
			int indice = 0;
			for (Article articleDansPanier : panier) {

				if (articleDansPanier.getFromage().equals(nouvelleArticle.getFromage())
						&& articleDansPanier.getPrixTTC() == nouvelleArticle.getPrixTTC()) {
					panier.get(indice).setQuantitéEnStock(panier.get(indice).getQuantitéEnStock() + qte);
					existe = true;
				}
				indice++;
			}
			if (!existe) {
				Article ajout = new Article(nouvelleArticle.getFromage(), nouvelleArticle.getClé(),
						nouvelleArticle.getPrixTTC());
				ajout.setQuantitéEnStock(qte);
				panier.add(ajout);
			}
			float prixTotal = 0.0F;
			for (Article articleDansPanier : panier) {
				prixTotal += articleDansPanier.getPrixTTC() * articleDansPanier.getQuantitéEnStock();
			}
			lblPanier.setText(df.format(prixTotal)  + "€");

		}
	}

	public static void viderPanier() {
		for (Article articleDansPanier : panier) {
			for (Fromage FromageDansReserve : listeFromagesInitialisation.getListeFromage()) {
				if (articleDansPanier.getFromage().getDésignation() == FromageDansReserve.getDésignation()) {
					for (Article articleDansReserve : FromageDansReserve.getArticles()) {
						if (articleDansPanier.getPrixTTC() == articleDansReserve.getPrixTTC()) {
							articleDansReserve.setQuantitéEnStock(
									articleDansReserve.getQuantitéEnStock() + articleDansPanier.getQuantitéEnStock());
						}
					}
				}
			}
		}
		panier = new ArrayList<>();
		lblPanier.setText("0.0€");

	}

	public void initialisationListeFromage() {

		// creation de tous les fromage
		listeFromagesInitialisation = new Articles();
		listeFromagesInitialisation.addFromages(GenerationFromages.getTousFromages());
		listeFromagesInitialisation.regénérationDuStock();

		// creation variable poiur la liste de fromage de vache
		List<Fromage> listeFromageVacheTemp = new ArrayList<>();
		listeFromageVache = new Articles();
		for (Fromage fromton : listeFromagesInitialisation.getListeFromage()) {
			if (fromton.getTypeFromage() == TypeLait.VACHE)
				listeFromageVacheTemp.add(fromton);
			;
		}
		listeFromageVache.addFromages(listeFromageVacheTemp);

		// creation variable poiur la liste de fromage de chevre
		List<Fromage> listeFromageChevreTemp = new ArrayList<>();
		listeFromageChevre = new Articles();
		for (Fromage fromton : listeFromagesInitialisation.getListeFromage()) {
			if (fromton.getTypeFromage() == TypeLait.CHEVRE)
				listeFromageChevreTemp.add(fromton);
		}
		listeFromageChevre.addFromages(listeFromageChevreTemp);

		// brebis
		List<Fromage> listeFromageBrebisTemp = new ArrayList<>();
		listeFromageBrebis = new Articles();
		for (Fromage fromton : listeFromagesInitialisation.getListeFromage()) {
			if (fromton.getTypeFromage() == TypeLait.BREBIS)
				listeFromageBrebisTemp.add(fromton);
		}
		listeFromageBrebis.addFromages(listeFromageBrebisTemp);

	}

	public static void actualisationListeFromage(Article articleAActualisaer, int qte) {
		for (Fromage fromton : listeFromagesInitialisation.getListeFromage()) {
			if (fromton.getDésignation() == articleAActualisaer.getFromage().getDésignation()) {
				for (Article article : fromton.getArticles()) {
					if (article.getPrixTTC() == articleAActualisaer.getPrixTTC()) {
						article.setQuantitéEnStock(qte);
					}
				}
			}
		}
	}

	public static void fermer() {
		frmFromagerie.dispose();
	}
}
