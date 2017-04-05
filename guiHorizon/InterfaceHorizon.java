package guiHorizon;

//Copyright @ 2017 by Valdecy Pereira

//This file is part of J-Horizon.
//
//J-ELECTRE is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//J-Horizon is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with J-Horizon.  If not, see <http://www.gnu.org/licenses/>.

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class InterfaceHorizon{

	public  static double Model_Selection    = 1; 
	public  static double Pickup_Delivery    = 1;
	public  static double Backhaul           = 1;
	public  static double Route_Type         = 1;
	public  static double Fleet_Size         = 1;
	public  static double Depots             = 1;
	public  static double Time_Window        = 1;
	public  static double Map_Preferences    = 1;
	public  static double Show_Routes        = 1;
	public  static double Vehicle_Capacity   = 1;
	public  static double Routes_Animation   = 1;
	public  static double Distances          = 1;
	public  static double Speed              = 1;

	public  static int    TotalDepots        = 0;
	public  static int    TotalClients       = 0;
	public  static int    TypesVehicles      = 1;

	private static JTable table_Clients;
	private static final ButtonGroup buttonGroup_1  = new ButtonGroup();
	private static final ButtonGroup buttonGroup_2  = new ButtonGroup();
	private static final ButtonGroup buttonGroup_3  = new ButtonGroup();
	private static final ButtonGroup buttonGroup_4  = new ButtonGroup();
	private static final ButtonGroup buttonGroup_5  = new ButtonGroup();
	private static final ButtonGroup buttonGroup_6  = new ButtonGroup();
	private static final ButtonGroup buttonGroup_7  = new ButtonGroup();
	private static final ButtonGroup buttonGroup_8  = new ButtonGroup();
	private static final ButtonGroup buttonGroup_9  = new ButtonGroup();
	private static final ButtonGroup buttonGroup_10 = new ButtonGroup();
	private static final ButtonGroup buttonGroup_11 = new ButtonGroup();
	private static final ButtonGroup buttonGroup_12 = new ButtonGroup();

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows Classic".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}

		JSpinner spinner_NumberOfClients = new JSpinner();
		JLabel lblNumberOfDepots = new JLabel("Depots (DT):");
		JSpinner spinner_NumberOfDepots = new JSpinner();
		JButton btnMap = new JButton("     Map      ");
		JLabel lblVehicles = new JLabel("Vehicle Types (VT):");
		JSpinner spinner_NumberOfVehicles = new JSpinner();
		JButton btnMapRoutes = new JButton("  Map-Routes ");

		JLabel lblFleetSizeH = new JLabel("Fleet Size (Hmg or Htg)");
		JRadioButton rdbtnFinite = new JRadioButton("Finite                   ");
		JRadioButton rdbtnInfinite = new JRadioButton("Infinite");
		lblFleetSizeH.setEnabled(false);
		rdbtnFinite.setEnabled(false);
		rdbtnInfinite.setEnabled(false);
		rdbtnFinite.setSelected(true);
		JLabel lblDepot = new JLabel("Depots");
		JRadioButton rdbtnSingle = new JRadioButton("Single                   ");
		JRadioButton rdbtnMultiple = new JRadioButton("Multiple                  ");
		lblDepot.setEnabled(false);
		rdbtnSingle.setEnabled(false);
		rdbtnMultiple.setEnabled(false);
		rdbtnSingle.setSelected(true);
		JRadioButton rdbtnUnlimited = new JRadioButton("Unlimited                 ");
		JRadioButton rdbtnLine = new JRadioButton("Line Waypoints");
		JButton btnDistance = new JButton("   Dist/Time  ");
		JButton btnSolve = new JButton("     Solve    ");
		JRadioButton rdbtnHarversine = new JRadioButton("Harversine");
		JRadioButton rdbtnWithoutB = new JRadioButton("Without                ");
		JRadioButton rdbtnWithB = new JRadioButton("With");
		JLabel lblVelocitykmh = new JLabel("Velocity (km/h):");
		JSpinner spinner_Velocity = new JSpinner();
		JLabel lblShowRoutes = new JLabel("Route Animation");
		JRadioButton rdbtnStaticDisplay = new JRadioButton("Static");
		JRadioButton rdbtnAnimatedDisplay = new JRadioButton("Animated");
		lblShowRoutes.setEnabled(false);
		rdbtnStaticDisplay.setEnabled(false);
		rdbtnAnimatedDisplay.setEnabled(false);
		
		JFrame f = new JFrame("J-Horizon: A Vehicle Routing Problem Software - github.com/Valdecy");
		f.setSize(1220, 638);
		f.setVisible(true);
		f.getContentPane().setLayout(new BorderLayout(0, 0));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.setResizable(true);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		f.getContentPane().add(tabbedPane);

		JDialog dialog = new JDialog();
		JLabel label = new JLabel("        Please Wait...");
		dialog.setTitle("     Please Wait...");
		dialog.getContentPane().add(label);
		dialog.pack();
		dialog.setSize(210,65);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(false);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		class IPanel extends JPanel {
			private static final long serialVersionUID = 1L;

			private Image imageOrg = null;
			private Image image    = null;
			{
				addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(final ComponentEvent e) {
						final int w = IPanel.this.getWidth();
						final int h = IPanel.this.getHeight();
						image = w > 0 && h > 0 ? imageOrg.getScaledInstance(w, h, Image.SCALE_SMOOTH) : imageOrg;
						IPanel.this.repaint();
					}
				});
			}
			public IPanel(final Image i) {
				imageOrg = i;
				image    = i;
			}

			@Override
			public void paintComponent(final Graphics g) {
				super.paintComponent(g);
				if (image != null)
					g.drawImage(image, 0, 0, null);
			}
		}

		File sourceimage = new File ("maps/image-01.jpg");
		Image image = null;
		try {
			image = ImageIO.read(sourceimage);
		} catch (IOException e2) {

			e2.printStackTrace();
		}

		JPanel panel_Models = new IPanel(image);
		tabbedPane.addTab("   Model  ", null, panel_Models, "");
		panel_Models.setLayout(new MigLayout("", "[189px][][][]", "[15px][][]"));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Models.add(panel_1, "cell 0 0,grow");
		panel_1.setLayout(new MigLayout("", "[189px]", "[15px][23px][23px][23px][23px][23px][23px][]"));

		JLabel lblModelSelection = new JLabel("Model Selection");
		panel_1.add(lblModelSelection, "cell 0 0,alignx center,aligny top");
		lblModelSelection.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModelSelection.setToolTipText("");
		lblModelSelection.setForeground(Color.BLACK);
		lblModelSelection.setBackground(Color.WHITE);

		JRadioButton rdbtnTSP = new JRadioButton("TSP      ( Travelling Salesman Problem )");
		buttonGroup_1.add(rdbtnTSP);
		panel_1.add(rdbtnTSP, "cell 0 1,alignx left,aligny top");
		rdbtnTSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Model_Selection = 1;
				lblFleetSizeH.setEnabled(false);
				rdbtnFinite.setEnabled(false);
				rdbtnInfinite.setEnabled(false); 
				rdbtnFinite.setSelected(true);

				lblDepot.setEnabled(false);
				rdbtnSingle.setEnabled(false);
				rdbtnMultiple.setEnabled(false);
				rdbtnSingle.setSelected(true);

				Fleet_Size  = 1;
				Depots = 1;

			}
		});

		JRadioButton rdbtnMTSP = new JRadioButton("MTSP    ( Multiple TSP )");
		buttonGroup_1.add(rdbtnMTSP);
		panel_1.add(rdbtnMTSP, "cell 0 2");
		rdbtnMTSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Model_Selection = 2;
				lblFleetSizeH.setEnabled(true);
				rdbtnFinite.setEnabled(true);
				rdbtnInfinite.setEnabled(true);

				lblDepot.setEnabled(true);
				rdbtnSingle.setEnabled(true);
				rdbtnMultiple.setEnabled(true);

			}
		});

		JRadioButton rdbtnVRP = new JRadioButton("VRP      ( Vehicle Routing Problem )            ");
		buttonGroup_1.add(rdbtnVRP);
		panel_1.add(rdbtnVRP, "cell 0 3,growx,aligny top");
		rdbtnVRP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Model_Selection = 3;
				lblFleetSizeH.setEnabled(true);
				rdbtnFinite.setEnabled(true);
				rdbtnInfinite.setEnabled(true);

				lblDepot.setEnabled(true);
				rdbtnSingle.setEnabled(true);
				rdbtnMultiple.setEnabled(true);
			}
		});

		JLabel lblPickup_Deliveries = new JLabel("Pickups and Deliveries");
		lblPickup_Deliveries.setToolTipText("");
		lblPickup_Deliveries.setForeground(Color.BLACK);
		lblPickup_Deliveries.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPickup_Deliveries.setBackground(Color.WHITE);
		panel_1.add(lblPickup_Deliveries, "cell 0 4,alignx center,aligny center");


		rdbtnTSP.setSelected(true);

		JRadioButton rdbtnWithoutPD = new JRadioButton("Without                ");
		buttonGroup_2.add(rdbtnWithoutPD);
		panel_1.add(rdbtnWithoutPD, "flowx,cell 0 5");
		rdbtnWithoutPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pickup_Delivery = 1;
				rdbtnWithoutB.setSelected(true);
				Backhaul = 1;
			}
		});

		JRadioButton rdbtnWithPD = new JRadioButton("With");
		buttonGroup_2.add(rdbtnWithPD);
		panel_1.add(rdbtnWithPD, "cell 0 5");
		rdbtnWithPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pickup_Delivery = 2;
			}
		});

		rdbtnWithoutPD.setSelected(true);

		JLabel lblBackhauls = new JLabel("Backhauls");
		lblBackhauls.setToolTipText("");
		lblBackhauls.setForeground(Color.BLACK);
		lblBackhauls.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBackhauls.setBackground(Color.WHITE);
		panel_1.add(lblBackhauls, "cell 0 6,alignx center,aligny center");

//		JRadioButton rdbtnWithoutB = new JRadioButton("Without                ");
		buttonGroup_3.add(rdbtnWithoutB);
		panel_1.add(rdbtnWithoutB, "flowx,cell 0 7");
		rdbtnWithoutB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Backhaul = 1;
			}
		});

//		JRadioButton rdbtnWithB = new JRadioButton("With");
		buttonGroup_3.add(rdbtnWithB);
		panel_1.add(rdbtnWithB, "cell 0 7");
		rdbtnWithB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Backhaul = 2;
				rdbtnWithPD.setSelected(true);

				Pickup_Delivery = 2;
			}
		});

		rdbtnWithoutB.setSelected(true);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Models.add(panel_2, "cell 1 0,grow");
		panel_2.setLayout(new MigLayout("", "[]", "[15px][23px][23px][23px][23px][23px][23px][]"));

		JLabel lblRouteType = new JLabel("Route Type");
		panel_2.add(lblRouteType, "cell 0 0,alignx center,aligny center");
		lblRouteType.setToolTipText("");
		lblRouteType.setForeground(Color.BLACK);
		lblRouteType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRouteType.setBackground(Color.WHITE);

		JRadioButton rdbtnClosed = new JRadioButton("Closed                  ");
		buttonGroup_4.add(rdbtnClosed);
		panel_2.add(rdbtnClosed, "flowx,cell 0 1");
		rdbtnClosed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Route_Type = 1;
			}
		});

		JRadioButton rdbtnOpen = new JRadioButton("Open              ");
		panel_2.add(rdbtnOpen, "cell 0 1");
		buttonGroup_4.add(rdbtnOpen);
		rdbtnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Route_Type = 2;
			}
		});

		rdbtnClosed.setSelected(true);

//	    JLabel lblFleet = new JLabel("Fleet");
		panel_2.add(lblFleetSizeH, "cell 0 2,alignx center,aligny center");
		lblFleetSizeH.setToolTipText("");
		lblFleetSizeH.setForeground(Color.BLACK);
		lblFleetSizeH.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFleetSizeH.setBackground(Color.WHITE);

//	    JRadioButton rdbtnHomogeneous = new JRadioButton("Homogeneous      ");
		buttonGroup_5.add(rdbtnFinite);
		panel_2.add(rdbtnFinite, "cell 0 3");
		rdbtnFinite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fleet_Size = 1;
			}
		});

//	    JRadioButton rdbtnHeterogeneous = new JRadioButton("Heterogeneous     ");
		buttonGroup_5.add(rdbtnInfinite);
		panel_2.add(rdbtnInfinite, "cell 0 3");
		rdbtnInfinite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fleet_Size = 2;
			}
		});

//		JLabel lblDepot = new JLabel("Depots");
		panel_2.add(lblDepot, "cell 0 4,alignx center,aligny center");
		lblDepot.setToolTipText("");
		lblDepot.setForeground(Color.BLACK);
		lblDepot.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDepot.setBackground(Color.WHITE);

//		JRadioButton rdbtnSingle = new JRadioButton("Single                   ");
		buttonGroup_6.add(rdbtnSingle);
		panel_2.add(rdbtnSingle, "cell 0 5");
		rdbtnSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Depots = 1;
			}
		});

//		JRadioButton rdbtnMultiple = new JRadioButton("Multiple                  ");
		buttonGroup_6.add(rdbtnMultiple);
		panel_2.add(rdbtnMultiple, "cell 0 5");
		rdbtnMultiple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Depots = 2;
			}
		});

		rdbtnSingle.setSelected(true);

		JLabel lblTime = new JLabel("Time Window");
		panel_2.add(lblTime, "cell 0 6,alignx center,aligny center");
		lblTime.setToolTipText("");
		lblTime.setForeground(Color.BLACK);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTime.setBackground(Color.WHITE);

		JRadioButton rdbtnWithoutTW = new JRadioButton("Without                ");
		buttonGroup_7.add(rdbtnWithoutTW);
		panel_2.add(rdbtnWithoutTW, "cell 0 7");
		rdbtnWithoutTW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Time_Window = 1;
			}
		});

		JRadioButton rdbtnWithTW = new JRadioButton("With                ");
		buttonGroup_7.add(rdbtnWithTW);
		panel_2.add(rdbtnWithTW, "cell 0 7");
		rdbtnWithTW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Time_Window = 2;
			}
		});

		rdbtnWithoutTW.setSelected(true);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Models.add(panel_3, "cell 2 0,grow");
		panel_3.setLayout(new MigLayout("", "[]", "[15px][23px][23px][23px][23px][23px][23px][]"));

		JLabel lblCapacity = new JLabel("Vehicle Capacity");
		lblCapacity.setToolTipText("");
		lblCapacity.setForeground(Color.BLACK);
		lblCapacity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCapacity.setBackground(Color.WHITE);
		panel_3.add(lblCapacity, "cell 0 0,alignx center,aligny center");

//		JRadioButton rdbtnFixedLocations = new JRadioButton("Fixed     ");
		buttonGroup_8.add(rdbtnUnlimited);
		panel_3.add(rdbtnUnlimited, "flowx,cell 0 1");
		rdbtnUnlimited.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vehicle_Capacity = 1;
			}
		});

		JRadioButton rdbtnLimited = new JRadioButton("Limited");
		buttonGroup_8.add(rdbtnLimited);
		panel_3.add(rdbtnLimited, "cell 0 1"); 
		rdbtnLimited.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vehicle_Capacity = 2;
			}
		});

		rdbtnUnlimited.setSelected(true);

		JLabel lblMapPreferences = new JLabel("Map Preferences");
		panel_3.add(lblMapPreferences, "cell 0 2,alignx center,aligny center");
		lblMapPreferences.setToolTipText("");
		lblMapPreferences.setForeground(Color.BLACK);
		lblMapPreferences.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMapPreferences.setBackground(Color.WHITE);

		JRadioButton rdbtnStreetMap = new JRadioButton("Street Map ( Latitude and Longitude )         ");
		panel_3.add(rdbtnStreetMap, "cell 0 3");
		buttonGroup_9.add(rdbtnStreetMap);
		rdbtnStreetMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Map_Preferences = 1;
				rdbtnHarversine.setEnabled(true);
				
				lblShowRoutes.setEnabled(false);
				rdbtnStaticDisplay.setEnabled(false);
				rdbtnAnimatedDisplay.setEnabled(false);
				
				rdbtnStaticDisplay.setSelected(true);
			}
		});

		JRadioButton rdbtnCarthesianPlane = new JRadioButton("Cartesian Plane ( xy Coordinates )");
		panel_3.add(rdbtnCarthesianPlane, "cell 0 4");
		buttonGroup_9.add(rdbtnCarthesianPlane);
		rdbtnCarthesianPlane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Map_Preferences = 2;
				rdbtnLine.setSelected(true);
				rdbtnHarversine.setEnabled(false);
				
				lblShowRoutes.setEnabled(true);
				rdbtnStaticDisplay.setEnabled(true);
				rdbtnAnimatedDisplay.setEnabled(true);
			}
		});

		rdbtnStreetMap.setSelected(true);

		JLabel lblRoutesDisplay = new JLabel("Show Routes");
		panel_3.add(lblRoutesDisplay, "cell 0 5,alignx center,aligny center");
		lblRoutesDisplay.setToolTipText("");
		lblRoutesDisplay.setForeground(Color.BLACK);
		lblRoutesDisplay.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRoutesDisplay.setBackground(Color.WHITE);

		JRadioButton rdbtnUrban = new JRadioButton("Urban Waypoints");
		panel_3.add(rdbtnUrban, "cell 0 6");
		buttonGroup_10.add(rdbtnUrban);
		rdbtnUrban.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Show_Routes = 1;
				rdbtnStreetMap.setSelected(true);
			}
		});

//		JRadioButton rdbtnLine = new JRadioButton("Line Waypoints");
		panel_3.add(rdbtnLine, "cell 0 7");
		buttonGroup_10.add(rdbtnLine);
		rdbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Show_Routes = 2;
			}
		});

		rdbtnUrban.setSelected(true);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Models.add(panel_4, "cell 3 0,grow");
		panel_4.setLayout(new MigLayout("", "[]", "[15px][23px][23px][23px][23px][23px][23px][]"));

//		JLabel lblShowRoutes = new JLabel("Route Animation");
		panel_4.add(lblShowRoutes, "cell 0 0,alignx center,aligny center");
		lblShowRoutes.setToolTipText("");
		lblShowRoutes.setForeground(Color.BLACK);
		lblShowRoutes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblShowRoutes.setBackground(Color.WHITE);

//		JRadioButton rdbtnStaticDisplay = new JRadioButton("Static");
		buttonGroup_11.add(rdbtnStaticDisplay);
		panel_4.add(rdbtnStaticDisplay, "cell 0 1");
		rdbtnStaticDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Routes_Animation = 1;
			}
		});

//		JRadioButton rdbtnAnimatedDisplay = new JRadioButton("Animated");
		buttonGroup_11.add(rdbtnAnimatedDisplay);
		panel_4.add(rdbtnAnimatedDisplay, "cell 0 2");
		rdbtnAnimatedDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Routes_Animation = 2;
			}
		});

		rdbtnStaticDisplay.setSelected(true);

		JLabel lblDistances = new JLabel("Distances");
		panel_4.add(lblDistances, "cell 0 3,alignx center");
		lblDistances.setToolTipText("");
		lblDistances.setForeground(Color.BLACK);
		lblDistances.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDistances.setBackground(Color.WHITE);

		JRadioButton rdbtnEuclidean = new JRadioButton("Euclidean");
		buttonGroup_12.add(rdbtnEuclidean);
		panel_4.add(rdbtnEuclidean, "cell 0 4");
		rdbtnEuclidean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Distances = 1;
			}
		});

		JRadioButton rdbtnManhattan = new JRadioButton("Manhattan");
		buttonGroup_12.add(rdbtnManhattan);
		panel_4.add(rdbtnManhattan, "cell 0 5");
		rdbtnManhattan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Distances = 2;
			}
		});

//		JRadioButton rdbtnHarversine = new JRadioButton("Harversine");
		buttonGroup_12.add(rdbtnHarversine);
		panel_4.add(rdbtnHarversine, "cell 0 6");
		rdbtnHarversine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Distances = 3;
			}
		});

		JRadioButton rdbtnMyDistance = new JRadioButton("My Dist/Time Matrix                             ");
		buttonGroup_12.add(rdbtnMyDistance);
		panel_4.add(rdbtnMyDistance, "cell 0 7");
		rdbtnMyDistance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Distances = 4;
			}
		});

		rdbtnEuclidean.setSelected(true);

		JPanel panel_Input = new JPanel();
		panel_Input.setBackground(new Color(220, 220, 220));
		tabbedPane.addTab("   Input  ", null, panel_Input, "");
		panel_Input.setLayout(new MigLayout("", "[][][grow][-125.00][][][grow][][][]", "[][grow][][]"));
		tabbedPane.setEnabledAt(1, false);
		
		JScrollPane scroll_Clients = new JScrollPane();
		panel_Input.add(scroll_Clients, "cell 2 1 8 1,grow");

		class CellRenderer extends DefaultTableCellRenderer {
			public CellRenderer() {
				super();
			}
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				this.setHorizontalAlignment(CENTER);
				return super.getTableCellRendererComponent(table, value, isSelected,
						hasFocus, row, column);
			}
		}

		int p = 158;
		int q = 1;

		String[]   cols = new String[p];
		String[][] rows = new String[q][p];

		for (int i = 0; i < p; i++){
			cols[i] = "";
		}

		cols[ 0] = "Id";
		cols[ 1] = "Demand";
		cols[ 2] = "Latitude";
		cols[ 3] = "Longitude";
		cols[ 4] = "Service Time";
		cols[ 5] = "Waiting Cost";
		cols[ 6] = "TW (early)";
		cols[ 7] = "TW (late)";
		cols[ 8] = "PCK (Lat)";
		cols[ 9] = "PCK (Long)";
		cols[10] = "TW (early)";
		cols[11] = "TW (late)";
		cols[12] = "Service Time";
		cols[13] = "DLV (Lat)";
		cols[14] = "DLV (Long)";
		cols[15] = "TW (early)";
		cols[16] = "TW (late)";
		cols[17] = "Service Time";

		int m = 0;

		for (int i = 1; i < 21; i++){
			cols[i + 17 + m] = "VT_" + (i) + ": QT";
			cols[i + 18 + m] = "VT_" + (i) + ": CT";
			cols[i + 19 + m] = "VT_" + (i) + ": FC";
			cols[i + 20 + m] = "VT_" + (i) + ": VC";
			cols[i + 21 + m] = "Break (early)";
			cols[i + 22 + m] = "Break (late)";
			cols[i + 23 + m] = "Duration";

			m = m + 6;
		}

		for (int i = 0; i < q; i++){		
			for (int j = 0; j < p; j++){		
				rows[i][j] = "";
			}
		}

		DefaultTableModel model_Clients = new DefaultTableModel(rows, cols);
		table_Clients = new JTable( model_Clients ){

			public boolean isCellEditable(int row,int column){
				if(column == 0) return false;
				Object value = getValueAt(row, column);
				if("-//-".equals(value) || "Infinite".equals(value)  || "Unlimited".equals(value)  || " 1 ".equals(value) ){
					return false;
				}
				return true;
			}	

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);

				Object value = getValueAt(row, column); 

				if ("-//-".equals(value) || "Infinite".equals(value) || "Unlimited".equals(value)  || 
						" 1 ".equals(value)){

					c.setBackground(Color.LIGHT_GRAY);

				}else if ("Optional".equals(value)){

					c.setBackground(Color.ORANGE);
				}
				else if (table_Clients.isCellSelected(row, column)) {

					c.setBackground(Color.BLACK);

				}else {
					c.setBackground(Color.WHITE);
				}

				return c;
			}
		};
		scroll_Clients.setViewportView(table_Clients);
		table_Clients.setCellSelectionEnabled(true);
		table_Clients.setShowVerticalLines(true);
		table_Clients.setShowHorizontalLines(true);
		table_Clients.putClientProperty("terminateEditOnFocusLost", true);// Confirm all entered values
		table_Clients.setDefaultRenderer(Object.class, new CellRenderer());
		table_Clients.getTableHeader().setReorderingAllowed(false);
		table_Clients.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		@SuppressWarnings("unused")
		ExcelAdapter enable_Clients = new ExcelAdapter(table_Clients);

		JScrollPane scroll_Distance = new JScrollPane();

		int t = 1;
		int u = 1;

		String[]   cols_2 = new String[t];
		String[][] rows_2 = new String[t][u];

		for (int i = 0; i < t; i++){
			cols_2[i] = "";
		}

		for (int i = 0; i < u; i++){		
			for (int j = 0; j < t; j++){		
				rows_2[i][j] = "";
			}
		}

		DefaultTableModel model_Distance = new DefaultTableModel(rows_2, cols_2);
		JTable table_Distance = new JTable(model_Distance){

			public boolean isCellEditable(int row,int column){
				if(row    == 0) return false;
				if(column == 0) return false;
				return true;
			}	

		};
		scroll_Distance.setViewportView(table_Distance);
		table_Distance.setTableHeader(null);
		table_Distance.setCellSelectionEnabled(true);
		table_Distance.setShowVerticalLines(true);
		table_Distance.setShowHorizontalLines(true);
		table_Distance.putClientProperty("terminateEditOnFocusLost", true);
		table_Distance.setDefaultRenderer(Object.class, new CellRenderer());
		table_Distance.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		@SuppressWarnings("unused")
		ExcelAdapter enable_Distance = new ExcelAdapter(table_Distance);


		JScrollPane scroll_Time = new JScrollPane();
		String[]   cols_3 = new String[t];
		String[][] rows_3 = new String[t][u];

		for (int i = 0; i < t; i++){
			cols_3[i] = "";
		}

		for (int i = 0; i < u; i++){		
			for (int j = 0; j < t; j++){		
				rows_3[i][j] = "";
			}
		}

		DefaultTableModel model_Time = new DefaultTableModel(rows_3, cols_3);
		JTable table_Time = new JTable(model_Time){

			public boolean isCellEditable(int row,int column){
				if(row    == 0) return false;
				if(column == 0) return false;
				return true;
			}	

		};
		scroll_Time.setViewportView(table_Time);
		table_Time.setTableHeader(null);
		table_Time.setCellSelectionEnabled(true);
		table_Time.setShowVerticalLines(true);
		table_Time.setShowHorizontalLines(true);
		table_Time.putClientProperty("terminateEditOnFocusLost", true);
		table_Time.setDefaultRenderer(Object.class, new CellRenderer());
		table_Time.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		@SuppressWarnings("unused")
		ExcelAdapter enable_Time = new ExcelAdapter(table_Time);
		
		JButton btnBuildProblem = new JButton("                         Build Problem                        ");
		btnBuildProblem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				btnMap.setEnabled(false);
				btnMapRoutes.setEnabled(false);
				spinner_NumberOfVehicles.setValue(1);
				spinner_NumberOfDepots.setValue(1);
				spinner_NumberOfClients.setValue(1);

				for (int i = 0; i < table_Clients.getRowCount(); i++){		
					for (int j = 0; j < table_Clients.getColumnCount(); j++){
						table_Clients.getModel().setValueAt("", i, j);
					}
				}

				model_Clients.setColumnCount(table_Clients.getColumnCount());
				model_Clients.setRowCount(1);

				int p  =  table_Clients.getColumnCount();
				int q  = 1;

				for (int i = 0; i < q; i++){
					for (int j = 0; j < p; j++){
						table_Clients.getModel().setValueAt("-//-", i, j);
					}
				}

				if (Pickup_Delivery == 1){

					for(int i = 8; i < 10; i++){
						table_Clients.getColumnModel().getColumn(i).setMinWidth(15);
						table_Clients.getColumnModel().getColumn(i).setMaxWidth(15);
						table_Clients.getColumnModel().getColumn(i).setWidth(15);
					}
					for(int i = 13; i < 15; i++){
						table_Clients.getColumnModel().getColumn(i).setMinWidth(15);
						table_Clients.getColumnModel().getColumn(i).setMaxWidth(15);
						table_Clients.getColumnModel().getColumn(i).setWidth(15);
					}
				}else if (Pickup_Delivery == 2){
					for(int i = 8; i < 10; i++){
						table_Clients.getColumnModel().getColumn(i).setMinWidth(75);
						table_Clients.getColumnModel().getColumn(i).setMaxWidth(75);
						table_Clients.getColumnModel().getColumn(i).setWidth(75);
					}
					for(int i = 13; i < 15; i++){
						table_Clients.getColumnModel().getColumn(i).setMinWidth(75);
						table_Clients.getColumnModel().getColumn(i).setMaxWidth(75);
						table_Clients.getColumnModel().getColumn(i).setWidth(75);
					}

				}

				if (Model_Selection != 1){
					lblVehicles.setEnabled(true);			    
					spinner_NumberOfVehicles.setEnabled(true);	

				}else{
					lblVehicles.setEnabled(false);			    
					spinner_NumberOfVehicles.setEnabled(false);	
				}

				if (Depots == 2){
					lblNumberOfDepots.setEnabled(true);			    
					spinner_NumberOfDepots.setEnabled(true);
				}else{
					lblNumberOfDepots.setEnabled(false);			    
					spinner_NumberOfDepots.setEnabled(false);
				}

				if (Time_Window == 1){

					for(int i = 4; i < 8; i++){
						table_Clients.getColumnModel().getColumn(i).setMinWidth(15);
						table_Clients.getColumnModel().getColumn(i).setMaxWidth(15);
						table_Clients.getColumnModel().getColumn(i).setWidth(15);
					}
					for(int i = 10; i < 13; i++){
						table_Clients.getColumnModel().getColumn(i).setMinWidth(15);
						table_Clients.getColumnModel().getColumn(i).setMaxWidth(15);
						table_Clients.getColumnModel().getColumn(i).setWidth(15);
					}
					for(int i = 15; i < 18; i++){
						table_Clients.getColumnModel().getColumn(i).setMinWidth(15);
						table_Clients.getColumnModel().getColumn(i).setMaxWidth(15);
						table_Clients.getColumnModel().getColumn(i).setWidth(15);
					}

				}else if (Time_Window == 2){

					for(int i = 4; i < 8; i++){
						table_Clients.getColumnModel().getColumn(i).setMinWidth(75);
						table_Clients.getColumnModel().getColumn(i).setMaxWidth(75);
						table_Clients.getColumnModel().getColumn(i).setWidth(75);
					}
					if(Pickup_Delivery == 1){
						for(int i = 10; i < 13; i++){
							table_Clients.getColumnModel().getColumn(i).setMinWidth(15);
							table_Clients.getColumnModel().getColumn(i).setMaxWidth(15);
							table_Clients.getColumnModel().getColumn(i).setWidth(15);
							table_Clients.getModel().setValueAt("-//-", 0, i);
						}
						for(int i = 15; i < 18; i++){
							table_Clients.getColumnModel().getColumn(i).setMinWidth(15);
							table_Clients.getColumnModel().getColumn(i).setMaxWidth(15);
							table_Clients.getColumnModel().getColumn(i).setWidth(15);
							table_Clients.getModel().setValueAt("-//-", 0, i);
						}
					}
				}

				if (Map_Preferences == 2){	

					table_Clients.getColumnModel().getColumn( 2).setHeaderValue("x");
					table_Clients.getColumnModel().getColumn( 3).setHeaderValue("y");
					table_Clients.getColumnModel().getColumn( 8).setHeaderValue("PCK (x)");
					table_Clients.getColumnModel().getColumn( 9).setHeaderValue("PCK (y)");
					table_Clients.getColumnModel().getColumn(13).setHeaderValue("DLV (x)");
					table_Clients.getColumnModel().getColumn(14).setHeaderValue("DLV (y)");
				}

				for(int i = 25; i < table_Clients.getColumnCount(); i++){
					table_Clients.getColumnModel().getColumn(i).setMinWidth(0);
					table_Clients.getColumnModel().getColumn(i).setMaxWidth(0);
					table_Clients.getColumnModel().getColumn(i).setWidth(0);
				}

				if (Distances == 4 || Map_Preferences == 2){
					lblVelocitykmh.setEnabled(false);
					spinner_Velocity.setEnabled(false);
				}else{
					lblVelocitykmh.setEnabled(true);
					spinner_Velocity.setEnabled(true);
				}

				spinner_NumberOfClients.setValue(1);
				spinner_NumberOfDepots.setValue(1);
				spinner_NumberOfVehicles.setValue(1);
				spinner_Velocity.setValue(1);
				
				btnSolve.setEnabled(false);
				btnDistance.setEnabled(false);
				table_Clients.repaint();
				table_Clients.getTableHeader().repaint();
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setEnabledAt(2, false);
				tabbedPane.setEnabledAt(3, false);
				tabbedPane.setSelectedComponent(panel_Input);
			}
		});
		panel_Models.add(btnBuildProblem, "cell 3 1");
		File url_out  = new File("maps/temp_.txt");

		class JavaFxWebBrowserOut extends JFXPanel {
			private WebView webViewOut;
			private WebEngine webEngineOut;
			public JavaFxWebBrowserOut() {
				Platform.runLater(() -> {
					initialiseJavaFXScene();
				});
			}
			private void initialiseJavaFXScene() {
				webViewOut = new WebView();
				webEngineOut = webViewOut.getEngine();
				webEngineOut.load("file:///" + url_out.getAbsolutePath());
				Scene sceneOut = new Scene(webViewOut);
				setScene(sceneOut);
			}
		}

		JavaFxWebBrowserOut   panel_Out   = new JavaFxWebBrowserOut();

		JPanel panel_DistanceTime = new JPanel();
		tabbedPane.addTab("  Dist/Time  ", null, panel_DistanceTime, "");
		panel_DistanceTime.setLayout(new MigLayout("", "[][][grow]", "[][][][][grow][grow]"));

		JButton btnSaveInputs = new JButton("     Solve    ");
		btnSaveInputs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object check_obj = null;
				String check_str = null;
				double check_dbl = 0;
				double early     = 1;
				double late      = 1;
				double input     = 1;

				for (int j = 1; j < 18; j++) {
					if (input == 0){
						break;	
					}
					for (int i = 0; i < table_Clients.getRowCount(); i++) {
						
						if (j == 2 || j == 3 || j == 8 || j == 9 || j == 13 || j == 14){
							break;
						}

						check_obj = table_Clients.getValueAt(i, j);
						if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
							check_obj = "0";
						}
						check_str = (String)check_obj;
						try {
							Double.parseDouble(check_str);
						} catch (NumberFormatException e1) {
							check_str = "0";
						}
						check_dbl = Double.parseDouble(check_str);
						if (check_dbl < 0){
							tabbedPane.setSelectedComponent(panel_Input);
							JOptionPane.showMessageDialog(null, (String)table_Clients.getValueAt(i, 0) + " [" + table_Clients.getColumnName(j)+ "] must be >= 0");
							input = 0;
						}
					}	
				}
				
				for (int j = 18; j < 18 + 7*((Integer) spinner_NumberOfVehicles.getValue()); j++) {
					if (input == 0){
						break;	
					}
					for (int i = 0; i < table_Clients.getRowCount(); i++) {
						check_obj = table_Clients.getValueAt(i, j);
						if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
							check_obj = "0";
						}
						check_str = (String)check_obj;
						try {
							Double.parseDouble(check_str);
						} catch (NumberFormatException e1) {
							check_str = "0";
						}
						check_dbl = Double.parseDouble(check_str);
						if (check_dbl < 0){
							tabbedPane.setSelectedComponent(panel_Input);
							JOptionPane.showMessageDialog(null, (String)table_Clients.getValueAt(i, 0) + " [" + table_Clients.getColumnName(j)+ "] must be >= 0");
							input = 0;
						}
					}
				}
				
				for (int j = 6; j < 17; j++) {
					if (input == 0){
						break;	
					}
					for (int i = 0; i < table_Clients.getRowCount(); i++) {
						if (j == 8 || j == 9 || j == 12 || j == 13 || j == 14){
							break;
						}
						if (j == 6 || j == 10 || j == 15){
							check_obj = table_Clients.getValueAt(i, j);
							if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
								check_obj = "0";
							}
							check_str = (String)check_obj;
							try {
								Double.parseDouble(check_str);
							} catch (NumberFormatException e1) {
								check_str = "0";
							}
							early = Double.parseDouble(check_str);
							
							check_obj = table_Clients.getValueAt(i, j + 1);
							if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
								check_obj = "0";
							}
							check_str = (String)check_obj;
							try {
								Double.parseDouble(check_str);
							} catch (NumberFormatException e1) {
								check_str = "0";
							}
							late = Double.parseDouble(check_str);
						}
					if (early > late){
						tabbedPane.setSelectedComponent(panel_Input);
						JOptionPane.showMessageDialog(null, "TW (early) must be <= TW (late) at row: " + (String)table_Clients.getValueAt(i, 0));
						input = 0;
					}	
				}
			}
				
			for (int j = 22; j < 23 + 7*((Integer) spinner_NumberOfVehicles.getValue()); j += 7) {
				if (input == 0){
					break;	
				}
				for (int i = 0; i < table_Clients.getRowCount(); i++) {
					
					check_obj = table_Clients.getValueAt(i, j);
					if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
						check_obj = "0";
					}
					check_str = (String)check_obj;
					try {
						Double.parseDouble(check_str);
					} catch (NumberFormatException e1) {
						check_str = "0";
					}
					early = Double.parseDouble(check_str);
					
					check_obj = table_Clients.getValueAt(i, j + 1);
					if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
						check_obj = "0";
					}
					check_str = (String)check_obj;
					try {
						Double.parseDouble(check_str);
					} catch (NumberFormatException e1) {
						check_str = "0";
					}
					late = Double.parseDouble(check_str);
					
					if (early > late){
						tabbedPane.setSelectedComponent(panel_Input);
						JOptionPane.showMessageDialog(null, "Break (early) must be <= Break (late) at row: " + (String)table_Clients.getValueAt(i, 0));
						input = 0;
					}

				}
			}
				
				if (input == 0){
				
				}else if (input == 1){
					
					dialog.setVisible(true);

					TotalDepots  = (Integer) spinner_NumberOfDepots.getValue();
					TotalClients = (Integer) spinner_NumberOfClients.getValue();
					int p        = (Integer) spinner_NumberOfDepots.getValue();; 
					int q        = (Integer) spinner_NumberOfClients.getValue();

					Object[][] getData   = new Object[p + q][table_Clients.getColumnCount()];
					String[][] getString = new String[p + q][table_Clients.getColumnCount()];
					double[][] getValue  = new double[p + q][table_Clients.getColumnCount()];

					for (int i = 0; i < (p + q); i++) {
						for (int j = 0; j < table_Clients.getColumnCount(); j++){
							getData[i][j] = table_Clients.getValueAt(i, j);	
							if (getData[i][j] == null) {
								getData[i][j] = "0";
							} 
						}
					}

					for (int i = 0; i < (p + q); i++) {
						for (int j = 0; j < table_Clients.getColumnCount(); j++){
							getString[i][j] = (String)getData[i][j];
							getString[i][j] = getString[i][j].replace(",", ".");	
						}
					}

					for (int i = 0; i < (p + q); i++) {
						for (int j = 0; j < table_Clients.getColumnCount(); j++){
							try {
								Double.parseDouble(getString[i][j]);
							} catch (NumberFormatException e1) {
								getString[i][j] = "0";
							}
							getValue[i][j] = Double.parseDouble(getString[i][j]);		
						}
					}

					Object[][] getDataD   = new Object[table_Distance.getRowCount() - 1][table_Distance.getColumnCount() - 1];
					String[][] getStringD = new String[table_Distance.getRowCount() - 1][table_Distance.getColumnCount() - 1];
					double[][] getValueD  = new double[table_Distance.getRowCount() - 1][table_Distance.getColumnCount() - 1];

					for (int i = 0; i < table_Distance.getRowCount() - 1; i++) {
						for (int j = 0; j < table_Distance.getColumnCount() - 1; j++){
							if (i == j){
								getDataD[i][j] = "0";
							}
							getDataD[i][j] = table_Distance.getValueAt(i + 1, j + 1);

							if (getDataD[i][j] == null) {
								getDataD[i][j] = "0";
							} 
						}
					}

					for (int i = 0; i < table_Distance.getRowCount() - 1; i++) {
						for (int j = 0; j < table_Distance.getColumnCount() - 1; j++){
							getStringD[i][j] = (String)getDataD[i][j];
							getStringD[i][j] = getStringD[i][j].replace(",", ".");	
						}
					}

					for (int i = 0; i < table_Distance.getRowCount() - 1; i++) {
						for (int j = 0; j < table_Distance.getColumnCount() - 1; j++){
							try {
								Double.parseDouble(getStringD[i][j]);
							} catch (NumberFormatException e1) {
								getStringD[i][j] = "0";
							}
							getValueD[i][j] = Double.parseDouble(getStringD[i][j]);	
						}
					}

					Object[][] getDataT   = new Object[table_Time.getRowCount() - 1][table_Time.getColumnCount() - 1];
					String[][] getStringT = new String[table_Time.getRowCount() - 1][table_Time.getColumnCount() - 1];
					double[][] getValueT  = new double[table_Time.getRowCount() - 1][table_Time.getColumnCount() - 1];

					Object value1 = table_Time.getValueAt(2,1);

					if(value1 == null){	
						for (int i = 0; i < table_Distance.getRowCount() - 1; i++) {
							for (int j = 0; j < table_Distance.getColumnCount() - 1; j++){
								getValueT[i][j] = getValueD[i][j];
							}
						}
					}else{
						for (int i = 0; i < table_Time.getRowCount() - 1; i++) {
							for (int j = 0; j < table_Time.getColumnCount() - 1; j++){
								if (i == j){
									getDataT[i][j] = "0";
								}
								getDataT[i][j] = table_Time.getValueAt(i + 1, j + 1);	
								if (getDataT[i][j] == null) {
									getDataT[i][j] = "0";
								} 
							}
						}

						for (int i = 0; i < table_Time.getRowCount() - 1; i++) {
							for (int j = 0; j < table_Time.getColumnCount() - 1; j++){
								getStringT[i][j] = (String)getDataT[i][j];
								getStringT[i][j] = getStringT[i][j].replace(",", ".");	
							}
						}

						for (int i = 0; i < table_Time.getRowCount() - 1; i++) {
							for (int j = 0; j < table_Time.getColumnCount() - 1; j++){
								try {
									Double.parseDouble(getStringT[i][j]);
								} catch (NumberFormatException e1) {
									getStringT[i][j] = "0";
								}
								getValueT[i][j] = Double.parseDouble(getStringT[i][j]);		
							}
						}	
					}

					Object value2 = table_Distance.getValueAt(2,1);

					if(value2 == null){	
						for (int i = 0; i < table_Distance.getRowCount() - 1; i++) {
							for (int j = 0; j < table_Distance.getColumnCount() - 1; j++){
								getValueD[i][j] = getValueT[i][j];
							}
						}
					}

					Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, getValueD, getValueT, TotalDepots, TotalClients);

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							String out = "";
							panel_Out.webEngineOut.load(out);
							out =  "maps/output.txt";
							File url_change_out  = new File(out);
							panel_Out.webEngineOut.load("file:///" + url_change_out.getAbsolutePath());
						}
					});
					
					tabbedPane.setEnabledAt(3, true);
					tabbedPane.setSelectedIndex(3);
					dialog.setVisible(false);
					
				}
			}
		});
		btnSaveInputs.setForeground(Color.WHITE);
		btnSaveInputs.setBackground(Color.BLACK);
		panel_DistanceTime.add(btnSaveInputs, "flowx,cell 2 0");

		JLabel lblInstruction_1 = new JLabel("INSTRUCTION #1: Please insert ALL VALUES (empty cells are not allowed). ");
		lblInstruction_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_DistanceTime.add(lblInstruction_1, "cell 2 1");

		JLabel lblInstruction_2 = new JLabel("INSTRUCTION #2: Distance matrix OR time matrix can be left in blank. Preferably use BOTH matrices.");
		lblInstruction_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_DistanceTime.add(lblInstruction_2, "cell 2 2");

		JLabel lblInstruction_3 = new JLabel("INSTRUCTION #3: The velocity is not related with distance matrix. Use the time matrix to make this relation.");
		lblInstruction_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_DistanceTime.add(lblInstruction_3, "cell 2 3");
		tabbedPane.setEnabledAt(2, false);

		panel_DistanceTime.add(scroll_Distance, "cell 2 4,grow");
		panel_DistanceTime.add(scroll_Time, "cell 2 5,grow");

		JScrollPane scroll_Out = new JScrollPane(panel_Out);
		scroll_Out.setPreferredSize(new Dimension(300, 300));
		tabbedPane.addTab("  Output  ", null, scroll_Out, "");
		tabbedPane.setEnabledAt(3, false);

		JLabel lblNumberOfClients = new JLabel("Clients (CT):");
		panel_Input.add(lblNumberOfClients, "flowx,cell 2 0,alignx left,aligny center");

		Dimension dim = new Dimension(75, 18);

//	    JSpinner spinner_NumberOfClients = new JSpinner();
		spinner_NumberOfClients.setPreferredSize(dim);
		spinner_NumberOfClients.setMinimumSize(dim);
		spinner_NumberOfClients.setModel(new SpinnerNumberModel(1, 1, 1000000, 1)); // SpinnerNumberModel(value, min, max, step);
		panel_Input.add(spinner_NumberOfClients, "cell 2 0");

//	    JLabel lblNumberOfDepots = new JLabel("Depots:");
		panel_Input.add(lblNumberOfDepots, "cell 2 0");

//	    JSpinner spinner_NumberOfDepots = new JSpinner();
		spinner_NumberOfDepots.setPreferredSize(dim);
		spinner_NumberOfDepots.setMinimumSize(dim);
		spinner_NumberOfDepots.setModel(new SpinnerNumberModel(1, 1, 1000000, 1));
		panel_Input.add(spinner_NumberOfDepots, "cell 2 0");

		JButton btnBuildLocations = new JButton("     Build    ");
		btnBuildLocations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TypesVehicles  = (Integer) spinner_NumberOfVehicles.getValue();

				btnMap.setEnabled(true);
				btnSolve.setEnabled(true);

				if (Distances == 4){
					btnDistance.setEnabled(true);
					btnSolve.setEnabled(false);
				}else{
					btnDistance.setEnabled(false);
				}

				int p  = (Integer) spinner_NumberOfDepots.getValue();
				int q  = (Integer) spinner_NumberOfClients.getValue();
				
				model_Clients.setRowCount(p + q);
				for (int i = 0; i < table_Clients.getRowCount(); i++){
					for (int j = 0; j < table_Clients.getColumnCount(); j++){
						table_Clients.getModel().setValueAt("", i, j);
					}
				}

				for (int i = 0; i < p; i++){
					table_Clients.getModel().setValueAt("# DT-" + (i) + " #", i, 0);
					table_Clients.getModel().setValueAt("-//-", i, 1);

					for(int j = 0; j < TypesVehicles; j++){
						table_Clients.getModel().setValueAt("1", i, (18 + 7*j));	
						table_Clients.getModel().setValueAt("1", i, (19 + 7*j));	
						table_Clients.getModel().setValueAt("0", i, (20 + 7*j));	
						table_Clients.getModel().setValueAt("1", i, (21 + 7*j));
						table_Clients.getModel().setValueAt("Optional",  i, (22 + 7*j));
						table_Clients.getModel().setValueAt("Optional",  i, (23 + 7*j));
						table_Clients.getModel().setValueAt("Optional",  i, (24 + 7*j));

						if (Vehicle_Capacity == 1){
							table_Clients.getModel().setValueAt("Unlimited", i, (19 + 7*j));
						}
						if (Fleet_Size == 2){
							table_Clients.getModel().setValueAt("Infinite", i, (18 + 7*j));
						}
					}
					for(int j = 18 + 7*TypesVehicles; j < table_Clients.getColumnCount() ; j++){
						table_Clients.getModel().setValueAt("-//-", i, j);	
					}
					if (Pickup_Delivery == 1 || Pickup_Delivery == 2){
						table_Clients.getModel().setValueAt("",     i,  2);
						table_Clients.getModel().setValueAt("",     i,  3);
						table_Clients.getModel().setValueAt("-//-", i,  4);
						table_Clients.getModel().setValueAt("-//-", i,  5);
						table_Clients.getModel().setValueAt("-//-", i,  6);
						table_Clients.getModel().setValueAt("-//-", i,  7);
						table_Clients.getModel().setValueAt("-//-", i,  8);
						table_Clients.getModel().setValueAt("-//-", i,  9);
						table_Clients.getModel().setValueAt("-//-", i, 10);
						table_Clients.getModel().setValueAt("-//-", i, 11);
						table_Clients.getModel().setValueAt("-//-", i, 12);
						table_Clients.getModel().setValueAt("-//-", i, 13);
						table_Clients.getModel().setValueAt("-//-", i, 14);
						table_Clients.getModel().setValueAt("-//-", i, 15);
						table_Clients.getModel().setValueAt("-//-", i, 16);
						table_Clients.getModel().setValueAt("-//-", i, 17);
						if (Distances == 4){
							table_Clients.getModel().setValueAt("Optional", i, 2);
							table_Clients.getModel().setValueAt("Optional", i, 3);
						}
					}
					if (Time_Window == 1){
						table_Clients.getModel().setValueAt("-//-", i,  4);
						table_Clients.getModel().setValueAt("-//-", i,  5);
						table_Clients.getModel().setValueAt("-//-", i,  6);
						table_Clients.getModel().setValueAt("-//-", i,  7);
					}else if (Time_Window == 2){
						table_Clients.getModel().setValueAt("-//-", i,  4);
						table_Clients.getModel().setValueAt("-//-", i,  5);
						table_Clients.getModel().setValueAt("", i,  6);
						table_Clients.getModel().setValueAt("", i,  7);
					}
					if (Vehicle_Capacity == 1){
						table_Clients.getModel().setValueAt("Unlimited", i, 19);
					}else if(Vehicle_Capacity == 2){
						table_Clients.getModel().setValueAt("1", i, 19);
					}
					if (Fleet_Size == 1){
						table_Clients.getModel().setValueAt("1", i, 18);
					}else if(Fleet_Size == 2){
						table_Clients.getModel().setValueAt("Infinite", i, 18);
					}
					if (Model_Selection == 1 && Vehicle_Capacity == 1){
						table_Clients.getModel().setValueAt(" 1 ", i, 18);
						table_Clients.getModel().setValueAt("Unlimited", i, 19);
					}else if (Model_Selection == 1 && Vehicle_Capacity == 2){
						table_Clients.getModel().setValueAt(" 1 ", i, 18);
						table_Clients.getModel().setValueAt("1",    i, 19);
					}
				}
				for (int i = p; i < (p + q); i++){
					table_Clients.getModel().setValueAt("CT-" + (i - p + 1), i, 0);		
					if (Pickup_Delivery == 1){
						table_Clients.getModel().setValueAt("-//-", i,  8);
						table_Clients.getModel().setValueAt("-//-", i,  9);
						table_Clients.getModel().setValueAt("-//-", i, 13);
						table_Clients.getModel().setValueAt("-//-", i, 14);
						if (Distances == 4){
							table_Clients.getModel().setValueAt("Optional", i, 2);
							table_Clients.getModel().setValueAt("Optional", i, 3);
						}
					}else if (Pickup_Delivery == 2){
						table_Clients.getModel().setValueAt("1",    i,  1);
						table_Clients.getModel().setValueAt("-//-", i,  2);
						table_Clients.getModel().setValueAt("-//-", i,  3);
						table_Clients.getModel().setValueAt("-//-", i,  4);
						table_Clients.getModel().setValueAt("-//-", i,  5);
						table_Clients.getModel().setValueAt("-//-", i,  6);
						table_Clients.getModel().setValueAt("-//-", i,  7);
						table_Clients.getModel().setValueAt("",     i,  8);
						table_Clients.getModel().setValueAt("",     i,  9);
						table_Clients.getModel().setValueAt("",     i, 13);
						table_Clients.getModel().setValueAt("",     i, 14);
						if (Distances == 4){
							table_Clients.getModel().setValueAt("Optional", i,  8);
							table_Clients.getModel().setValueAt("Optional", i,  9);
							table_Clients.getModel().setValueAt("Optional", i, 13);
							table_Clients.getModel().setValueAt("Optional", i, 14);
						}
					}
					for(int j = 18; j < table_Clients.getColumnCount() ; j++){
						table_Clients.getModel().setValueAt("-//-", i, j);	
					}
					if (Vehicle_Capacity == 1 && Pickup_Delivery == 1){
						table_Clients.getModel().setValueAt("-//-", i, 1);
					}else if (Vehicle_Capacity == 2 && Pickup_Delivery == 1){
						table_Clients.getModel().setValueAt("1",    i, 1);
					}
				}	
				if (Time_Window == 1){
					for (int i = 0; i < (p + q); i++){
						table_Clients.getModel().setValueAt("-//-", i,  4);
						table_Clients.getModel().setValueAt("-//-", i,  5);
						table_Clients.getModel().setValueAt("-//-", i,  6);
						table_Clients.getModel().setValueAt("-//-", i,  7);
						table_Clients.getModel().setValueAt("-//-", i, 10);
						table_Clients.getModel().setValueAt("-//-", i, 11);
						table_Clients.getModel().setValueAt("-//-", i, 12);
						table_Clients.getModel().setValueAt("-//-", i, 15);
						table_Clients.getModel().setValueAt("-//-", i, 16);
						table_Clients.getModel().setValueAt("-//-", i, 17);
					}
				}else if (Time_Window == 2 && Pickup_Delivery == 1){
					for (int i = 0; i < p; i++){
						table_Clients.getModel().setValueAt("-//-", i, 4);
						table_Clients.getModel().setValueAt("-//-", i, 5);
						table_Clients.getModel().setValueAt("",     i, 6);
						table_Clients.getModel().setValueAt("",     i, 7);
						table_Clients.getModel().setValueAt("-//-", i, 10);
						table_Clients.getModel().setValueAt("-//-", i, 11);
						table_Clients.getModel().setValueAt("-//-", i, 12);
						table_Clients.getModel().setValueAt("-//-", i, 15);
						table_Clients.getModel().setValueAt("-//-", i, 16);
						table_Clients.getModel().setValueAt("-//-", i, 17);
					}
					for (int i = p; i < (p + q); i++){
						table_Clients.getModel().setValueAt("0",    i, 4);
						table_Clients.getModel().setValueAt("0",    i, 5);
						table_Clients.getModel().setValueAt("",     i, 6);
						table_Clients.getModel().setValueAt("",     i, 7);
						table_Clients.getModel().setValueAt("-//-", i, 10);
						table_Clients.getModel().setValueAt("-//-", i, 11);
						table_Clients.getModel().setValueAt("-//-", i, 12);
						table_Clients.getModel().setValueAt("-//-", i, 15);
						table_Clients.getModel().setValueAt("-//-", i, 16);
						table_Clients.getModel().setValueAt("-//-", i, 17);
					}
				}else if (Time_Window == 2 && Pickup_Delivery == 2){
					for (int i = 0; i < p; i++){
						table_Clients.getModel().setValueAt("-//-", i, 4);
						table_Clients.getModel().setValueAt("-//-", i, 5);
						table_Clients.getModel().setValueAt("", i, 6);
						table_Clients.getModel().setValueAt("", i, 7);
						table_Clients.getModel().setValueAt("-//-", i, 10);
						table_Clients.getModel().setValueAt("-//-", i, 11);
						table_Clients.getModel().setValueAt("-//-", i, 12);
						table_Clients.getModel().setValueAt("-//-", i, 15);
						table_Clients.getModel().setValueAt("-//-", i, 16);
						table_Clients.getModel().setValueAt("-//-", i, 17);
					}
					for (int i = p; i < (p + q); i++){
						table_Clients.getModel().setValueAt("-//-", i, 4);
						table_Clients.getModel().setValueAt("0",    i, 5);
						table_Clients.getModel().setValueAt("-//-", i, 6);
						table_Clients.getModel().setValueAt("-//-", i, 7);
						table_Clients.getModel().setValueAt("",  i, 10);
						table_Clients.getModel().setValueAt("",  i, 11);
						table_Clients.getModel().setValueAt("0", i, 12);
						table_Clients.getModel().setValueAt("",  i, 15);
						table_Clients.getModel().setValueAt("",  i, 16);
						table_Clients.getModel().setValueAt("0", i, 17);
					}
				}

				for(int i = 18; i < (25 + 7*(TypesVehicles - 1)); i++){
					table_Clients.getColumnModel().getColumn(i).setMinWidth(75);
					table_Clients.getColumnModel().getColumn(i).setMaxWidth(75);
					table_Clients.getColumnModel().getColumn(i).setWidth(75);
				}

				for(int i = (25 + 7*(TypesVehicles - 1)); i < table_Clients.getColumnCount() ; i++){
					table_Clients.getColumnModel().getColumn(i).setMinWidth(0);
					table_Clients.getColumnModel().getColumn(i).setMaxWidth(0);
					table_Clients.getColumnModel().getColumn(i).setWidth(0);
				}

				// Redundancy needed in order to update "table_Clients" with only one click.
				for(int i = 18; i < (25 + 7*(TypesVehicles - 1)); i++){
					table_Clients.getColumnModel().getColumn(i).setMinWidth(75);
					table_Clients.getColumnModel().getColumn(i).setMaxWidth(75);
					table_Clients.getColumnModel().getColumn(i).setWidth(75);
				}

				for(int i = (25 + 7*(TypesVehicles - 1)); i < table_Clients.getColumnCount() ; i++){
					table_Clients.getColumnModel().getColumn(i).setMinWidth(0);
					table_Clients.getColumnModel().getColumn(i).setMaxWidth(0);
					table_Clients.getColumnModel().getColumn(i).setWidth(0);
				}

				table_Clients.repaint();
			}
		});

//	    JLabel lblVehicles = new JLabel("Vehicle Types:");
		lblVehicles.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_Input.add(lblVehicles, "cell 2 0,alignx left,aligny center");

//	    JSpinner spinner_Vehicles = new JSpinner();
		spinner_NumberOfVehicles.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_NumberOfVehicles.setPreferredSize(dim);
		spinner_NumberOfVehicles.setMinimumSize(dim);
		spinner_NumberOfVehicles.setModel(new SpinnerNumberModel(1, 1, 20, 1)); // SpinnerNumberModel(value, min, max, step);
		panel_Input.add(spinner_NumberOfVehicles, "cell 2 0");

//		JLabel lblVelocitykmh = new JLabel("Velocity (km/h):");
		panel_Input.add(lblVelocitykmh, "cell 2 0");

//		JSpinner spinnerVelocity = new JSpinner();
		spinner_Velocity.setPreferredSize(dim);
		spinner_Velocity.setMinimumSize(dim);
		spinner_Velocity.setModel(new SpinnerNumberModel(1, 1, 1000000, 1));
		spinner_Velocity.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Speed = (Integer) spinner_Velocity.getValue();
			}
		});
		panel_Input.add(spinner_Velocity, "cell 2 0");

		panel_Input.add(btnBuildLocations, "cell 2 0");

//	    JButton btnMap = new JButton("     Map      ");
		btnMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TotalDepots  = (Integer) spinner_NumberOfDepots.getValue();
				int p  = (Integer) spinner_NumberOfDepots.getValue();
				int q  = (Integer) spinner_NumberOfClients.getValue();

				Object[][] getData = new Object[p + q][15];
				for (int i = 0; i < (p + q); i++) {
					for (int j = 0; j < 15; j++){
						getData[i][j] = table_Clients.getValueAt(i, j);	
						if (getData[i][j].equals(null) || getData[i][j].equals("-//-")|| getData[i][j].equals("Optional")) {
							getData[i][j] = "0";
						} 
					}
				}
				String[][] getString = new String[p + q][15];
				for (int i = 0; i < (p + q); i++) {
					for (int j = 0; j < 15; j++){
						getString[i][j] = (String)getData[i][j];
						getString[i][j] = getString[i][j].replace(",", ".");	
						if (getString[i][j].isEmpty()){
							getString[i][j] = "0";
						}
					}
				}

				try {
					jsHorizon.writeFile_VRP(getString);
				} catch (IOException e) {
					e.printStackTrace();
				}	
				
				try {
					jsHorizon.writeFile_VRP_depots(getString);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					jsHorizon.writeFile_VRP_pck(getString);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					jsHorizon.writeFile_VRP_dlv(getString);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				String map = "";
				if(Map_Preferences == 1 && Pickup_Delivery == 1){
					map  = "maps/Grph-LtLng-MK-01.html";
				}else if (Map_Preferences == 1 && Pickup_Delivery == 2){
					map  = "maps/Grph-LtLng-MK-03.html";
				} else if (Map_Preferences == 2 && Pickup_Delivery == 1){
					map  = "maps/Grph-XY-MK-02.html";
				} else if (Map_Preferences == 2 && Pickup_Delivery == 2){
					map  = "maps/Grph-XY-MK-03.html";
				}

				File url_change_map  = new File(map);
				String exe = "maps/K-Meleon";
				File exe_path  = new File(exe);
				ProcessBuilder process = new ProcessBuilder(exe_path.getAbsolutePath() + "\\k-meleon.exe", "file:///" + url_change_map.getAbsolutePath());
				process.directory(new File(exe_path.getAbsolutePath()));
				try {
					process.start();
				} catch (IOException e) {
				}
			}
		});

		panel_Input.add(btnMap, "cell 2 0");
		
//		JButton btnDistance = new JButton("   Distance   ");
		btnDistance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setSelectedIndex(2);

				TotalDepots  = (Integer) spinner_NumberOfDepots.getValue();
				TotalClients = (Integer) spinner_NumberOfClients.getValue();

				int k = TotalDepots;

				if (Pickup_Delivery == 1){
					model_Distance.setColumnCount(table_Clients.getRowCount() + 1);
					model_Distance.setRowCount(table_Clients.getRowCount() + 1);

					model_Time.setColumnCount(table_Clients.getRowCount() + 1);
					model_Time.setRowCount(table_Clients.getRowCount() + 1);

					table_Distance.getModel().setValueAt("Distance", 0, 0);
					table_Time.getModel().setValueAt("Time", 0, 0);

					for (int i = 0; i < table_Clients.getRowCount(); i++){
						table_Distance.getModel().setValueAt((String)table_Clients.getValueAt(i, 0), i + 1, 0);
						table_Distance.getModel().setValueAt((String)table_Clients.getValueAt(i, 0), 0, i + 1);
						table_Time.getModel().setValueAt((String)table_Clients.getValueAt(i, 0), i + 1, 0);
						table_Time.getModel().setValueAt((String)table_Clients.getValueAt(i, 0), 0, i + 1);
					}
				}else if (Pickup_Delivery == 2){

					model_Distance.setColumnCount(TotalDepots + 2*TotalClients + 1);
					model_Distance.setRowCount(TotalDepots + 2*TotalClients + 1);

					model_Time.setColumnCount(TotalDepots + 2*TotalClients + 1);
					model_Time.setRowCount(TotalDepots + 2*TotalClients + 1);

					table_Distance.getModel().setValueAt("Distance", 0, 0);
					table_Time.getModel().setValueAt("Time(Optional)", 0, 0);


					for (int i = 0; i < table_Clients.getRowCount(); i++){
						if(i < TotalDepots){
							table_Distance.getModel().setValueAt((String)table_Clients.getValueAt(i, 0), i + 1, 0);
							table_Distance.getModel().setValueAt((String)table_Clients.getValueAt(i, 0), 0, i + 1);
							table_Time.getModel().setValueAt((String)table_Clients.getValueAt(i, 0), i + 1, 0);
							table_Time.getModel().setValueAt((String)table_Clients.getValueAt(i, 0), 0, i + 1);
						}else{

							table_Distance.getModel().setValueAt((String)table_Clients.getValueAt(i, 0) + "_pck", k + 1, 0);
							table_Distance.getModel().setValueAt((String)table_Clients.getValueAt(i, 0) + "_pck", 0, k + 1);
							table_Time.getModel().setValueAt((String)table_Clients.getValueAt(i, 0) + "_pck", k + 1, 0);
							table_Time.getModel().setValueAt((String)table_Clients.getValueAt(i, 0) + "_pck", 0, k + 1);

							table_Distance.getModel().setValueAt((String)table_Clients.getValueAt(i, 0) + "_dlv", k + 2, 0);
							table_Distance.getModel().setValueAt((String)table_Clients.getValueAt(i, 0) + "_dlv", 0, k + 2);
							table_Time.getModel().setValueAt((String)table_Clients.getValueAt(i, 0) + "_dvl", k + 2, 0);
							table_Time.getModel().setValueAt((String)table_Clients.getValueAt(i, 0) + "_dlv", 0, k + 2);
							k = k + 2;
						}

					}
				}

			}
		});

		panel_Input.add(btnDistance, "cell 2 0");

		panel_Input.revalidate();

//		JButton btnSolve = new JButton("     Solve    ");
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object check_obj = null;
				String check_str = null;
				double check_dbl = 0;
				double early     = 1;
				double late      = 1;
				double input     = 1;

				for (int j = 1; j < 18; j++) {
					if (input == 0){
						break;	
					}
					for (int i = 0; i < table_Clients.getRowCount(); i++) {
						
						if (j == 2 || j == 3 || j == 8 || j == 9 || j == 13 || j == 14){
							break;
						}

						check_obj = table_Clients.getValueAt(i, j);
						if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
							check_obj = "0";
						}
						check_str = (String)check_obj;
						try {
							Double.parseDouble(check_str);
						} catch (NumberFormatException e1) {
							check_str = "0";
						}
						check_dbl = Double.parseDouble(check_str);
						if (check_dbl < 0){
							JOptionPane.showMessageDialog(null, (String)table_Clients.getValueAt(i, 0) + " [" + table_Clients.getColumnName(j)+ "] must be >= 0");
							input = 0;
						}
					}	
				}
				
				for (int j = 18; j < 18 + 7*((Integer) spinner_NumberOfVehicles.getValue()); j++) {
					if (input == 0){
						break;	
					}
					for (int i = 0; i < table_Clients.getRowCount(); i++) {
						check_obj = table_Clients.getValueAt(i, j);
						if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
							check_obj = "0";
						}
						check_str = (String)check_obj;
						try {
							Double.parseDouble(check_str);
						} catch (NumberFormatException e1) {
							check_str = "0";
						}
						check_dbl = Double.parseDouble(check_str);
						if (check_dbl < 0){
							JOptionPane.showMessageDialog(null, (String)table_Clients.getValueAt(i, 0) + " [" + table_Clients.getColumnName(j)+ "] must be >= 0");
							input = 0;
						}
					}
				}
				
				for (int j = 6; j < 17; j++) {
					if (input == 0){
						break;	
					}
					for (int i = 0; i < table_Clients.getRowCount(); i++) {
						if (j == 8 || j == 9 || j == 12 || j == 13 || j == 14){
							break;
						}
						if (j == 6 || j == 10 || j == 15){
							check_obj = table_Clients.getValueAt(i, j);
							if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
								check_obj = "0";
							}
							check_str = (String)check_obj;
							try {
								Double.parseDouble(check_str);
							} catch (NumberFormatException e1) {
								check_str = "0";
							}
							early = Double.parseDouble(check_str);
							
							check_obj = table_Clients.getValueAt(i, j + 1);
							if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
								check_obj = "0";
							}
							check_str = (String)check_obj;
							try {
								Double.parseDouble(check_str);
							} catch (NumberFormatException e1) {
								check_str = "0";
							}
							late = Double.parseDouble(check_str);
						}
					if (early > late){
						JOptionPane.showMessageDialog(null, "TW (early) must be <= TW (late) at row: " + (String)table_Clients.getValueAt(i, 0));
						input = 0;
					}	
				}
			}
				
			for (int j = 22; j < 23 + 7*((Integer) spinner_NumberOfVehicles.getValue()); j += 7) {
				if (input == 0){
					break;	
				}
				for (int i = 0; i < table_Clients.getRowCount(); i++) {
					
					check_obj = table_Clients.getValueAt(i, j);
					if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
						check_obj = "0";
					}
					check_str = (String)check_obj;
					try {
						Double.parseDouble(check_str);
					} catch (NumberFormatException e1) {
						check_str = "0";
					}
					early = Double.parseDouble(check_str);
					
					check_obj = table_Clients.getValueAt(i, j + 1);
					if (check_obj.equals(null) || check_obj.equals("-//-")|| check_obj.equals("Optional")) {
						check_obj = "0";
					}
					check_str = (String)check_obj;
					try {
						Double.parseDouble(check_str);
					} catch (NumberFormatException e1) {
						check_str = "0";
					}
					late = Double.parseDouble(check_str);
					
					if (early > late){
						JOptionPane.showMessageDialog(null, "Break (early) must be <= Break (late) at row: " + (String)table_Clients.getValueAt(i, 0));
						input = 0;
					}

				}
			}
				
				if (input == 0){
				
				}else if (input == 1){
					
					dialog.setVisible(true);
					btnMapRoutes.setEnabled(true);

					TotalDepots  = (Integer) spinner_NumberOfDepots.getValue();
					TotalClients = (Integer) spinner_NumberOfClients.getValue();
					int p  = (Integer) spinner_NumberOfDepots.getValue();
					int q  = (Integer) spinner_NumberOfClients.getValue();

					Object[][] getData_Points = new Object[p + q][15];
					for (int i = 0; i < (p + q); i++) {
						for (int j = 0; j < 15; j++){
							getData_Points[i][j] = table_Clients.getValueAt(i, j);	
							if (getData_Points[i][j].equals(null) || getData_Points[i][j].equals("-//-")|| getData_Points[i][j].equals("Optional")) {
								getData_Points[i][j] = "0";
							} 
						}
					}
					String[][] getString_Points = new String[p + q][15];
					for (int i = 0; i < (p + q); i++) {
						for (int j = 0; j < 15; j++){
							getString_Points[i][j] = (String)getData_Points[i][j];
							getString_Points[i][j] = getString_Points[i][j].replace(",", ".");
							if (getString_Points[i][j].isEmpty()){
								getString_Points[i][j] = "0";
							}
						}
					}

					try {
						jsHorizon.writeFile_VRP(getString_Points);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					try {
						jsHorizon.writeFile_VRP_depots(getString_Points);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					try {
						jsHorizon.writeFile_VRP_pck(getString_Points);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					try {
						jsHorizon.writeFile_VRP_dlv(getString_Points);
					} catch (IOException e) {
						e.printStackTrace();
					}

					Object[][] getData = new Object[p + q][table_Clients.getColumnCount()];
					for (int i = 0; i < (p + q); i++) {
						for (int j = 0; j < table_Clients.getColumnCount(); j++){
							getData[i][j] = table_Clients.getValueAt(i, j);	
							if (getData[i][j] == null) {
								getData[i][j] = "0";
							} 
						}
					}
					String[][] getString = new String[p + q][table_Clients.getColumnCount()];
					for (int i = 0; i < (p + q); i++) {
						for (int j = 0; j < table_Clients.getColumnCount(); j++){
							getString[i][j] = (String)getData[i][j];
							getString[i][j] = getString[i][j].replace(",", ".");	
						}
					}
					double[][] getValue = new double[p + q][table_Clients.getColumnCount()];
					for (int i = 0; i < (p + q); i++) {
						for (int j = 0; j < table_Clients.getColumnCount(); j++){
							try {
								Double.parseDouble(getString[i][j]);
							} catch (NumberFormatException e1) {
								getString[i][j] = "0";
							}
							getValue[i][j] = Double.parseDouble(getString[i][j]);		
						}
					}

					double latitude     =    0;
					double longitude    =    0;
					double x            =    0;
					double y            =    0;

					if (Pickup_Delivery == 1){

						if (Map_Preferences == 1 && Distances == 1){

							double[][] euclidean = new double[p + q][p + q];
							double[][] euclidean_time = new double[p + q][p + q];

							for (int i = 0; i < getValue.length; i++){

								latitude  = getValue[i][2];
								longitude = getValue[i][3];

								x = Util_Mercator.mercX(longitude);
								y = Util_Mercator.mercY(latitude);

								getValue[i][2] = x/1000;
								getValue[i][3] = y/1000;
							}

							for (int i = 0; i < (p + q); i++){
								for (int j = 0; j < (p + q); j++){

									euclidean[i][j] = Util_Euclidean.euclidean_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									euclidean_time[i][j] = ((euclidean[i][j])/Speed)*60;

									euclidean[j][i] = Util_Euclidean.euclidean_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									euclidean_time[j][i] = ((euclidean[j][i])/Speed)*60;
								}

							}
							Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, euclidean, euclidean_time, p, q);
						}

						if (Map_Preferences == 1 && Distances == 2){

							double[][] manhattan = new double[p + q][p + q];
							double[][] manhattan_time = new double[p + q][p + q];

							for (int i = 0; i < (p + q); i++){

								latitude  = getValue[i][2];
								longitude = getValue[i][3];

								x = Util_Mercator.mercX(longitude);
								y = Util_Mercator.mercY(latitude);

								getValue[i][2] = x/1000;
								getValue[i][3] = y/1000;
							}

							for (int i = 0; i < (p + q); i++){
								for (int j = 0; j < (p + q); j++){

									manhattan[i][j] = Util_Manhattan.manhattan_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									manhattan_time[i][j] = ((manhattan[i][j])/Speed)*60;

									manhattan[j][i] = Util_Manhattan.manhattan_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									manhattan_time[j][i] = ((manhattan[j][i])/Speed)*60;
								}
							}

							Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, manhattan, manhattan_time, p, q);
						}

						if (Map_Preferences == 1 && Distances == 3){

							double[][] harversine = new double[p + q][p + q];
							double[][] harversine_time = new double[p + q][p + q];

							for (int i = 0; i < (p + q); i++){
								for (int j = 0; j < (p + q); j++){

									harversine[i][j] = Util_Harversine.harversine_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									harversine_time[i][j] = ((harversine[i][j])/Speed)*60;

									harversine[j][i] = Util_Harversine.harversine_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									harversine_time[j][i] = ((harversine[j][i])/Speed)*60;
								}
							}

							Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, harversine, harversine_time, p, q);
						}

						if (Map_Preferences == 2 && Distances == 1){

							double[][] euclidean2 = new double[p + q][p + q];
							double[][] euclidean2_time = new double[p + q][p + q];

							for (int i = 0; i < (p + q); i++){
								for (int j = 0; j < (p + q); j++){

									euclidean2[i][j] = Util_Euclidean.euclidean_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									euclidean2_time[i][j] = ((euclidean2[i][j])/Speed);

									euclidean2[j][i] = Util_Euclidean.euclidean_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									euclidean2_time[j][i] = ((euclidean2[j][i])/Speed);
								}
							}
                            
							Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, euclidean2, euclidean2_time, p, q);
						}

						if (Map_Preferences == 2 && Distances == 2){

							double[][] manhattan2 = new double[p + q][p + q];
							double[][] manhattan2_time = new double[p + q][p + q];

							for (int i = 0; i < (p + q); i++){
								for (int j = 0; j < (p + q); j++){
									
									manhattan2[i][j] = Util_Manhattan.manhattan_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									manhattan2_time[i][j] = ((manhattan2[i][j])/Speed);

									manhattan2[j][i] = Util_Manhattan.manhattan_distance(getValue[i][2], getValue[i][3], getValue[j][2], getValue[j][3]);
									manhattan2_time[j][i] = ((manhattan2[j][i])/Speed);
								}
							}
							Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, manhattan2, manhattan2_time, p, q);
						}

					}else if (Pickup_Delivery == 2){

						if (Map_Preferences == 1 && Distances == 1){
							 double[][] vector    = new double[p + 2*q][2];
							 double[][] euclidean = new double[p + 2*q][p + 2*q];
							 double[][] euclidean_time = new double[p + 2*q][p + 2*q];
							 
							 for (int i = 0; i < getValue.length; i++){
								 if(i < TotalDepots){
										latitude  = getValue[i][2];
										longitude = getValue[i][3];

										x = Util_Mercator.mercX(longitude);
										y = Util_Mercator.mercY(latitude);

										getValue[i][2] = x/1000;
										getValue[i][3] = y/1000;
								 }else{
										latitude  = getValue[i][8];
										longitude = getValue[i][9];

										x = Util_Mercator.mercX(longitude);
										y = Util_Mercator.mercY(latitude);

										getValue[i][8] = x/1000;
										getValue[i][9] = y/1000;
										
										latitude  = getValue[i][13];
										longitude = getValue[i][14];

										x = Util_Mercator.mercX(longitude);
										y = Util_Mercator.mercY(latitude);

										getValue[i][13] = x/1000;
										getValue[i][14] = y/1000;
								 }
							 }
							 
							 int k = p;
							 for (int i = 0; i < (p + q); i++){
								 if(i < p){
									 vector[i][0] = getValue[i][2];
									 vector[i][1] = getValue[i][3];
								 }else{
									 vector[k][0] = getValue[i][8];
									 vector[k][1] = getValue[i][9];
									 
									 vector[k + 1][0] = getValue[i][13];
									 vector[k + 1][1] = getValue[i][14];
									 k = k + 2;
								 }	 
							 }
							 
							 for (int i = 0; i < vector.length; i++){
								 for (int j = 0; j < vector.length; j++){
									 
									   euclidean[i][j] = Util_Euclidean
									   .euclidean_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									   euclidean_time[i][j] = ((euclidean[i][j])/Speed)*60;
									   
									   euclidean[j][i] = Util_Euclidean
									   .euclidean_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									   euclidean_time[j][i] = ((euclidean[j][i])/Speed)*60;
		
								 }
							 }
							 

							 Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, euclidean, euclidean_time, p, q);
						}

						if (Map_Preferences == 1 && Distances == 2){
							 double[][] vector    = new double[p + 2*q][2];
							 double[][] manhattan = new double[p + 2*q][p + 2*q];
							 double[][] manhattan_time = new double[p + 2*q][p + 2*q];
							 						 
							 for (int i = 0; i < getValue.length; i++){
								 if(i < TotalDepots){
										latitude  = getValue[i][2];
										longitude = getValue[i][3];

										x = Util_Mercator.mercX(longitude);
										y = Util_Mercator.mercY(latitude);

										getValue[i][2] = x/1000;
										getValue[i][3] = y/1000;
								 }else{
										latitude  = getValue[i][8];
										longitude = getValue[i][9];

										x = Util_Mercator.mercX(longitude);
										y = Util_Mercator.mercY(latitude);

										getValue[i][8] = x/1000;
										getValue[i][9] = y/1000;
										
										latitude  = getValue[i][13];
										longitude = getValue[i][14];

										x = Util_Mercator.mercX(longitude);
										y = Util_Mercator.mercY(latitude);

										getValue[i][13] = x/1000;
										getValue[i][14] = y/1000;
								 }
							 }
							 
							 int k = p;
							 for (int i = 0; i < (p + q); i++){
								 if(i < p){
									 vector[i][0] = getValue[i][2];
									 vector[i][1] = getValue[i][3];
								 }else{
									 vector[k][0] = getValue[i][8];
									 vector[k][1] = getValue[i][9];
									 
									 vector[k + 1][0] = getValue[i][13];
									 vector[k + 1][1] = getValue[i][14];
									 k = k + 2;
								 }	 
							 }
							 
							 for (int i = 0; i < vector.length; i++){
								 for (int j = 0; j < vector.length; j++){
									 
									 manhattan[i][j] = Util_Manhattan
									 .manhattan_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									 manhattan_time[i][j] = ((manhattan[i][j])/Speed)*60;
									   
									 manhattan[j][i] = Util_Manhattan
									 .manhattan_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									 manhattan_time[j][i] = ((manhattan[j][i])/Speed)*60;
		
								 }
							 }
							
							 Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, manhattan, manhattan_time, p, q);
						}

						if (Map_Preferences == 1 && Distances == 3){
							 double[][] vector    = new double[p + 2*q][2];
							 double[][] harversine = new double[p + 2*q][p + 2*q];
							 double[][] harversine_time = new double[p + 2*q][p + 2*q];
							
							 for (int i = 0; i < getValue.length; i++){
								 if(i < TotalDepots){
										latitude  = getValue[i][2];
										longitude = getValue[i][3];

										x = Util_Mercator.mercX(longitude);
										y = Util_Mercator.mercY(latitude);

										getValue[i][2] = x/1000;
										getValue[i][3] = y/1000;
								 }else{
										latitude  = getValue[i][8];
										longitude = getValue[i][9];

										x = Util_Mercator.mercX(longitude);
										y = Util_Mercator.mercY(latitude);

										getValue[i][8] = x/1000;
										getValue[i][9] = y/1000;
										
										latitude  = getValue[i][13];
										longitude = getValue[i][14];

										x = Util_Mercator.mercX(longitude);
										y = Util_Mercator.mercY(latitude);

										getValue[i][13] = x/1000;
										getValue[i][14] = y/1000;
								 }
							 }
							 
							 int k = p;
							 for (int i = 0; i < (p + q); i++){
								 if(i < p){
									 vector[i][0] = getValue[i][2];
									 vector[i][1] = getValue[i][3];
								 }else{
									 vector[k][0] = getValue[i][8];
									 vector[k][1] = getValue[i][9];
									 
									 vector[k + 1][0] = getValue[i][13];
									 vector[k + 1][1] = getValue[i][14];
									 k = k + 2;
								 }	 
							 }
							 
							 for (int i = 0; i < vector.length; i++){
								 for (int j = 0; j < vector.length; j++){
									 
									 harversine[i][j] = Util_Harversine
									 .harversine_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									 harversine_time[i][j] = ((harversine[i][j])/Speed)*60;
									   
									 harversine[j][i] = Util_Harversine
									 .harversine_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									 harversine_time[j][i] = ((harversine[j][i])/Speed)*60;
		
								 }
							 }
							
							 Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, harversine, harversine_time, p, q);
						}

						if (Map_Preferences == 2 && Distances == 1){
							 double[][] vector    = new double[p + 2*q][2];
							 double[][] euclidean2 = new double[p + 2*q][p + 2*q];
							 double[][] euclidean2_time = new double[p + 2*q][p + 2*q];
							
							 int k = p;
							 for (int i = 0; i < (p + q); i++){
								 if(i < p){
									 vector[i][0] = getValue[i][2];
									 vector[i][1] = getValue[i][3];
								 }else{
									 vector[k][0] = getValue[i][8];
									 vector[k][1] = getValue[i][9];
									 
									 vector[k + 1][0] = getValue[i][13];
									 vector[k + 1][1] = getValue[i][14];
									 k = k + 2;
								 }	 
							 }
							 
							 for (int i = 0; i < vector.length; i++){
								 for (int j = 0; j < vector.length; j++){
									 
									   euclidean2[i][j] = Util_Euclidean
									   .euclidean_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									   euclidean2_time[i][j] = ((euclidean2[i][j])/Speed);
									   
									   euclidean2[j][i] = Util_Euclidean
									   .euclidean_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									   euclidean2_time[j][i] = ((euclidean2[j][i])/Speed);
		
								 }
							 }
							
							 Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, euclidean2, euclidean2_time, p, q);
						}

						if (Map_Preferences == 2 && Distances == 2){
							 double[][] vector    = new double[p + 2*q][2];
							 double[][] manhattan2 = new double[p + 2*q][p + 2*q];
							 double[][] manhattan2_time = new double[p + 2*q][p + 2*q];
							
							 int k = p;
							 for (int i = 0; i < (p + q); i++){
								 if(i < p){
									 vector[i][0] = getValue[i][2];
									 vector[i][1] = getValue[i][3];
								 }else{
									 vector[k][0] = getValue[i][8];
									 vector[k][1] = getValue[i][9];
									 
									 vector[k + 1][0] = getValue[i][13];
									 vector[k + 1][1] = getValue[i][14];
									 k = k + 2;
								 }	 
							 }
							 
							 for (int i = 0; i < vector.length; i++){
								 for (int j = 0; j < vector.length; j++){
									 
									 manhattan2[i][j] = Util_Manhattan
									 .manhattan_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									 manhattan2_time[i][j] = ((manhattan2[i][j])/Speed);
									   
									 manhattan2[j][i] = Util_Manhattan
									 .manhattan_distance(vector[i][0], vector[i][1], vector[j][0], vector[j][1]);
									 manhattan2_time[j][i] = ((manhattan2[j][i])/Speed);
		
								 }
							 }
							
							 Model_Builder_and_Solver_01.TSP_CostMatrix(getValue, manhattan2, manhattan2_time, p, q);
						}
					}

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							String out = "";
							panel_Out.webEngineOut.load(out);
							out =  "maps/output.txt";
							File url_change_out  = new File(out);
							panel_Out.webEngineOut.load("file:///" + url_change_out.getAbsolutePath());
						}
					});
					
					tabbedPane.setEnabledAt(3, true);
					tabbedPane.setSelectedComponent(scroll_Out);
					dialog.setVisible(false);
				}

			}
		});
		btnSolve.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_Input.add(btnSolve, "cell 2 0");


//	    JButton btnMapRoutes = new JButton("  Map-Routes ");
		btnMapRoutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BufferedReader sequence = null;
				try {
					sequence = new BufferedReader(new FileReader("maps\\Route-00.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String string_seq_00;

				List<String> list_00 = new ArrayList<String>();
				try {
					while((string_seq_00 = sequence.readLine()) != null){
						list_00.add(string_seq_00);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				sequence = null;
				try {
					sequence = new BufferedReader(new FileReader("maps\\Route-01.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String string_seq_01;

				List<String> list_01 = new ArrayList<String>();
				try {
					while((string_seq_01 = sequence.readLine()) != null){
						list_01.add(string_seq_01);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				sequence = null;
				try {
					sequence = new BufferedReader(new FileReader("maps\\Route-02.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String string_seq_02;

				List<String> list_02 = new ArrayList<String>();
				try {
					while((string_seq_02 = sequence.readLine()) != null){
						list_02.add(string_seq_02);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				sequence = null;
				try {
					sequence = new BufferedReader(new FileReader("maps\\Route-03.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String string_seq_03;

				List<String> list_03 = new ArrayList<String>();
				try {
					while((string_seq_03 = sequence.readLine()) != null){
						list_03.add(string_seq_03);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				String[] stringArray_00 = list_00.toArray(new String[0]);
				String[] stringArray_01 = list_01.toArray(new String[0]);
				String[] stringArray_02 = list_02.toArray(new String[0]);
				String[] stringArray_03 = list_03.toArray(new String[0]);
				
				Integer [] seqRoute = new Integer[stringArray_00.length];

				for (int i = 0; i < stringArray_00.length; i++){
					String index = String.valueOf(stringArray_02[i].charAt(3));
					if (stringArray_00[i].equals("-")){
						if (String.valueOf(stringArray_02[i].charAt(4)).equals("_")){
							index = String.valueOf(stringArray_02[i].charAt(3));
						}else if (String.valueOf(stringArray_02[i].charAt(5)).equals("_")){
							index =         String.valueOf(stringArray_02[i].charAt(3));
							index = index + String.valueOf(stringArray_02[i].charAt(4));
						}else if (String.valueOf(stringArray_02[i].charAt(6)).equals("_")){
							index =         String.valueOf(stringArray_02[i].charAt(3));
							index = index + String.valueOf(stringArray_02[i].charAt(4));
							index = index + String.valueOf(stringArray_02[i].charAt(5));
						}else if (String.valueOf(stringArray_02[i].charAt(7)).equals("_")){
							index =         String.valueOf(stringArray_02[i].charAt(3));
							index = index + String.valueOf(stringArray_02[i].charAt(4));
							index = index + String.valueOf(stringArray_02[i].charAt(5));
							index = index + String.valueOf(stringArray_02[i].charAt(6));
						}else if (String.valueOf(stringArray_02[i].charAt(8)).equals("_")){
							index =         String.valueOf(stringArray_02[i].charAt(3));
							index = index + String.valueOf(stringArray_02[i].charAt(4));
							index = index + String.valueOf(stringArray_02[i].charAt(5));
							index = index + String.valueOf(stringArray_02[i].charAt(6));
							index = index + String.valueOf(stringArray_02[i].charAt(7));
						}
						seqRoute[i] = Integer.valueOf(index) + 1;
						
					}else if(stringArray_00[i].indexOf("brk") >= 0 && i - 1 >= 0){
							seqRoute[i] = seqRoute[i - 1];

					}else if (stringArray_00[i].indexOf("brk") >= 0 && i - 1 < 0){
						seqRoute[i] = 1;
					}
					else{
						stringArray_00[i] = stringArray_00[i].replace("dlv_", "");
						stringArray_00[i] = stringArray_00[i].replace("pck_", "");
						seqRoute[i] = Integer.valueOf(stringArray_00[i]) + TotalDepots;
					}
				}

				String[][] getRoute = new String[seqRoute.length][7];

				for (int i = 0; i < seqRoute.length; i++) {
					for (int j = 0; j < 4; j++) {
						getRoute[i][j] = (String) table_Clients.getValueAt((seqRoute[i] - 1), j);
						if(Pickup_Delivery == 2 && (stringArray_03[i].equals("pickupShipment") || stringArray_03[i].equals("pickup")) && (j == 2 || j == 3)){
							getRoute[i][2] = (String) table_Clients.getValueAt((seqRoute[i] - 1), 8);
							getRoute[i][3] = (String) table_Clients.getValueAt((seqRoute[i] - 1), 9);
						}else if(Pickup_Delivery == 2 && (stringArray_03[i].equals("deliverShipment") || stringArray_03[i].equals("delivery")) && (j == 2 || j == 3)){
							getRoute[i][2] = (String) table_Clients.getValueAt((seqRoute[i] - 1), 13);
							getRoute[i][3] = (String) table_Clients.getValueAt((seqRoute[i] - 1), 14);
						}
					}
				}
				for (int i = 0; i < seqRoute.length; i++) {
						if( stringArray_03[i].equals("break")){
							getRoute[i][0] = "";
					}
				}
				for (int i = 0; i < getRoute.length; i++) {
					getRoute[i][4] = stringArray_01[i];
					getRoute[i][5] = stringArray_02[i];
					getRoute[i][6] = stringArray_03[i];
				}
				
					try {
						jsHorizon.writeFileRoute_VRP(getRoute);
						jsHorizon.writeFileRoute_VRP_Complete(getRoute);
					} catch (IOException e) {
						e.printStackTrace();
					}	
				
				String route = "";
				if       (Map_Preferences == 1 && Route_Type == 1 && Show_Routes == 1 && Pickup_Delivery == 1){
					route  = "maps/Grph-LtLng-RT-01.html";
				}else if (Map_Preferences == 1 && Route_Type == 2 && Show_Routes == 1 && Pickup_Delivery == 1){
					route  = "maps/Grph-LtLng-RT-01-Open.html";	
				}else if (Map_Preferences == 1 && Route_Type == 1 && Show_Routes == 2 && Pickup_Delivery == 1){
					route  = "maps/Grph-LtLng-RT-02.html";		
				}else if (Map_Preferences == 1 && Route_Type == 2 && Show_Routes == 2 && Pickup_Delivery == 1){
					route  = "maps/Grph-LtLng-RT-02-Open.html";		
				}else if (Map_Preferences == 1 && Route_Type == 1 && Show_Routes == 1 && Pickup_Delivery == 2){
					route  = "maps/Grph-LtLng-RT-03.html";
				}else if (Map_Preferences == 1 && Route_Type == 2 && Show_Routes == 1 && Pickup_Delivery == 2){
					route  = "maps/Grph-LtLng-RT-03-Open.html";	
				}else if (Map_Preferences == 1 && Route_Type == 1 && Show_Routes == 2 && Pickup_Delivery == 2){
					route  = "maps/Grph-LtLng-RT-04.html";		
				}else if (Map_Preferences == 1 && Route_Type == 2 && Show_Routes == 2 && Pickup_Delivery == 2){
					route  = "maps/Grph-LtLng-RT-04-Open.html";		
				}else if (Map_Preferences == 2 && Route_Type == 1 && Pickup_Delivery == 1 && Routes_Animation == 1){
					route  = "maps/Grph-XY-RT-02.html";
				}else if (Map_Preferences == 2 && Route_Type == 2 && Pickup_Delivery == 1 && Routes_Animation == 1){
					route  = "maps/Grph-XY-RT-02-Open.html";
				}else if (Map_Preferences == 2 && Route_Type == 1 && Pickup_Delivery == 2 && Routes_Animation == 1){
					route  = "maps/Grph-XY-RT-03.html";
				}else if (Map_Preferences == 2 && Route_Type == 2 && Pickup_Delivery == 2 && Routes_Animation == 1){
					route  = "maps/Grph-XY-RT-03-Open.html";
				}else if (Map_Preferences == 2 && Route_Type == 1 && Pickup_Delivery == 1 && Routes_Animation == 2){
					route  = "maps/Grph-XY-RT-02-Animation.html";
				}else if (Map_Preferences == 2 && Route_Type == 2 && Pickup_Delivery == 1 && Routes_Animation == 2){
					route  = "maps/Grph-XY-RT-02-Open-Animation.html";
				}else if (Map_Preferences == 2 && Route_Type == 1 && Pickup_Delivery == 2 && Routes_Animation == 2){
					route  = "maps/Grph-XY-RT-03-Animation.html";
				}else if (Map_Preferences == 2 && Route_Type == 2 && Pickup_Delivery == 2 && Routes_Animation == 2){
					route  = "maps/Grph-XY-RT-03-Open-Animation.html";
				}
				File url_change_route  = new File(route);
				String exe = "maps/K-Meleon";
				File exe_path  = new File(exe);
				ProcessBuilder process = new ProcessBuilder(exe_path.getAbsolutePath() + "\\k-meleon.exe", "file:///" + url_change_route.getAbsolutePath());
				process.directory(new File(exe_path.getAbsolutePath()));
				try {
					process.start();
				} catch (IOException e) {
				}
			}
		});

		panel_Input.add(btnMapRoutes, "cell 2 0");

	}
}
