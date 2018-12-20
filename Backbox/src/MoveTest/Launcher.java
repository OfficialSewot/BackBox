//package MoveTest;
//
//import java.awt.DisplayMode;
//import java.awt.EventQueue;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.JCheckBox;
//
//@SuppressWarnings("serial")
//public class Launcher extends JFrame {
//
//	/*
//	 * 
//	 * Screen Resolution changer is not working but it uses now the normal screen resolution. Gonna fix this later (or never)
//	 * Update -> Never fixed it
//	 */
//	
//	private static Launcher frame;
//	private JPanel contentPane;
//	@SuppressWarnings("rawtypes")
//	private JComboBox comboBox;
//	
//	
//    private GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//    private GraphicsDevice device = environment.getDefaultScreenDevice();
//    private DisplayMode[] ds = device.getDisplayModes();
//    private JLabel lblDoNotUse;
//    private JLabel lblWidth;
//    private JLabel lblHeight;
//    private JLabel lblRefreshrate;
//    private JLabel lblBitdepth;
//    private JTextField tfWidth;
//    private JTextField tfHeight;
//    private JTextField tfRefreshRate;
//    private JTextField tfBitDepth;
//    Main game = new Main();
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame = new Launcher();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	
//	@SuppressWarnings("rawtypes")
//	public Launcher() {
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 474, 345);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setSize(300, 400);
//		setContentPane(contentPane);
//		
//		lblDoNotUse = new JLabel("DO NOT USE THIS WITH THE NVIDIA DRIVER FOR LINUX");
//		lblDoNotUse.setBounds(12, 12, 404, 50);
//		contentPane.add(lblDoNotUse);
//		
//		lblWidth = new JLabel("Width");
//		lblWidth.setBounds(12, 86, 51, 50);
//		contentPane.add(lblWidth);
//		
//		lblHeight = new JLabel("Height");
//		lblHeight.setBounds(120, 86, 51, 50);
//		contentPane.add(lblHeight);
//		
//		lblRefreshrate = new JLabel("RefreshRate");
//		lblRefreshrate.setBounds(229, 86, 100, 50);
//		contentPane.add(lblRefreshrate);
//		
//		lblBitdepth = new JLabel("BitDepth");
//		lblBitdepth.setBounds(361, 86, 91, 50);
//		contentPane.add(lblBitdepth);
//		
//		tfWidth = new JTextField();
//		tfWidth.setBounds(12, 132, 75, 19);
//		contentPane.add(tfWidth);
//		tfWidth.setColumns(10);
//		
//		tfHeight = new JTextField();
//		tfHeight.setColumns(10);
//		tfHeight.setBounds(120, 132, 75, 19);
//		contentPane.add(tfHeight);
//		
//		tfRefreshRate = new JTextField();
//		tfRefreshRate.setColumns(10);
//		tfRefreshRate.setBounds(229, 132, 91, 19);
//		contentPane.add(tfRefreshRate);
//		
//		tfBitDepth = new JTextField();
//		tfBitDepth.setColumns(10);
//		tfBitDepth.setBounds(361, 132, 75, 19);
//		contentPane.add(tfBitDepth);
//		
//		final JCheckBox chckbxcustomres = new JCheckBox("Use custom resolution");
//		chckbxcustomres.setBounds(12, 159, 216, 23);
//		contentPane.add(chckbxcustomres);
//		
//		final JCheckBox chckbxFullscreen = new JCheckBox("Fullscreen");
//		chckbxFullscreen.setBounds(12, 186, 129, 23);
//		contentPane.add(chckbxFullscreen);
//		
//		JButton btnLaunch = new JButton("Launch");
//		btnLaunch.setBounds(12, 278, 446, 25);
//		btnLaunch.addActionListener(new ActionListener() {
//			
//		boolean fullscreen = false;
//		boolean customResolution = false;
//		
//			public void actionPerformed(ActionEvent arg0) 
//			{
//				final int chosenSetting = comboBox.getSelectedIndex();
//				
//				if (chckbxFullscreen.isSelected()){
//					fullscreen = true;
//				}
//				else{
//					fullscreen = false;
//				}
//				
//				if (chckbxcustomres.isSelected()) {
//					
//					customResolution = true;
//					
//					Thread t1 = new Thread(new Runnable(){
//
//						@Override
//						public void run() {
//							String tfw = tfWidth.getText();
//							String tfh = tfHeight.getText();
//							String tfbd = tfBitDepth.getText();
//							String tfrr = tfRefreshRate.getText();
//							
//							int width = Integer.parseInt(tfw);
//							int height = Integer.parseInt(tfh);
//							int bitDepth = Integer.parseInt(tfbd);
//							int refreshRate = Integer.parseInt(tfrr);
//							
//							Main.RunGame(width, height, bitDepth, refreshRate, fullscreen, customResolution);
//						}
//						
//					});
//					
//					t1.start();
//					frame.setVisible(false);
//					frame.dispose();
//					
//				}
//					
//				else{
//					
//					customResolution = false;
//					
//					if (chosenSetting != -1) {
//						Thread t1 = new Thread(new Runnable() {
//
//							@SuppressWarnings("static-access")
//							@Override
//							public void run() {
//								
//								game.RunGame(ds[chosenSetting].getWidth(),
//										ds[chosenSetting].getHeight(),
//										ds[chosenSetting].getRefreshRate(),
//										ds[chosenSetting].getBitDepth(),
//										fullscreen, customResolution);
//
//							}
//						});
//					
//
//						t1.start();
//						frame.setVisible(false);
//						frame.dispose();
//					}
//				}
//				
//				
//			}
//		});
//		contentPane.setLayout(null);
//		contentPane.add(btnLaunch);
//		
//		comboBox = new JComboBox();
//		comboBox.setBounds(12, 50, 446, 24);
//		contentPane.add(comboBox);		
//		
//		fillComboBoxWithDisplayModes();
//		
//		
//		
//
//	    
//		
//	}
//	
//	@SuppressWarnings("unchecked")
//	private void fillComboBoxWithDisplayModes()
//	{
//		
//	    for (DisplayMode displayMode : ds) 
//	    {
//			comboBox.addItem(displayMode.getWidth() + "x" + 
//							 displayMode.getHeight() + " " + 
//							 displayMode.getRefreshRate() + "Hz " + 
//							 displayMode.getBitDepth());
//		}
//	    
//	}
//}
