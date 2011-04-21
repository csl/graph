package com.graph;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class Main extends JFrame 
{

	int NodeNum = 1;
	
	private Main my = this;
	
	private GraphAlgorithm GraphaA = null;

	static int CameraTakePictureNumber = 0;
	static public String IMEI;
	
	private boolean [] syncT;
	public String root_path = "/home/shulong/camera/";
	
	//Middle
	protected JPanel jPanelMiddle = null;
	protected JPanel jPanelToolBarBar = null;
	protected JPanel jPanelToolBarBarDetail = null;

	protected JToolBar JToolBarBar = null;

	protected static JLabel jLabelCameraTotal = null;
	protected static JLabel jLabelCameraStatus = null;

	protected JButton JButtonSync = null;
	protected JButton JButtonTakePicture = null;
	protected JButton JButtonSyncData = null;	

	//Bottom
	protected JTabbedPane jTabbedPane = null;
	protected JPanel jPanelCameraTabContext = null;
	protected JPanel jPanelCameraTabContextDetail = null;
	protected JPanel jPanelInCameraTab = null;

	protected JPanel jPanelGraphicTabContext = null;
	protected JPanel jPanelGraphicTabContextDetail = null;
	protected JPanel jPanelGraphicTab = null;

	private JTextField[] JTextF; 
	private JLabel[] JSpLabel; 
	
	protected JLabel jLabelDFSDisplay = null; 
	protected JLabel jLabelBFSDisplay = null;
	
	protected JLabel jLabelReceiver = null;
	protected JLabel jLabelSubject = null;
	protected JPanel jPanel3 = null;
	protected JPanel jPanel1 = null;
	protected JScrollPane jScrollPane = null;

	protected JMenuItem jMenuItemAbout = null;

	protected static JTextField jTextFieldSender = null;
	static JTextField jTextFieldReceiveDate = null;
	static JTextField jTextFieldReceiveSubject = null;
	protected static JTextField jTextFieldReceiveSender = null;
	protected static JTextArea jTextArea1 = null;
	protected  JButton jButtonArrowLeft = null;
	protected  JButton jButtonArrowRight = null;
	protected  JLabel jLabelStatus = null;
	
	public Main(int num) 
	{
		super();
		NodeNum = num;
		try {
			GraphaA = new GraphAlgorithm(NodeNum);
			for (int i=0; i<NodeNum; i++)
			{
				GraphaA.SetNode(Integer.toString(i), i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	
	public void updateUI() {
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void initialize() 
	{
		int _programNum = NodeNum;
		
		JTextF = new JTextField[_programNum]; 
		JSpLabel = new JLabel[_programNum]; 
		
		this.setSize(508, 558);
		this.setContentPane(getJPanelMiddle());
		this.setTitle("Graph");
		this.setResizable(false);
	}

	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Setup Edge", getJPanelTabStatus());
			jTabbedPane.addTab("Graphic", getJPanelTabGraphic());
			jTabbedPane.setBounds(new Rectangle(1, 45, 501, 442));
		}
		return jTabbedPane;
	}
	
	public JPanel getJPanelTabStatus() {
		if (jPanelCameraTabContext == null) {
			jPanelCameraTabContext = new JPanel();
			jPanelCameraTabContext.setLayout(null);
			jPanelCameraTabContext.add(getJPanelTabContext(), null);
		}
		return jPanelCameraTabContext;
	}

	public JPanel getJPanelTabContext() {
		if (jPanelCameraTabContextDetail == null) {
			jPanelCameraTabContextDetail = new JPanel();
			jPanelCameraTabContextDetail.setLayout(null);
			jPanelCameraTabContextDetail.setBounds(new Rectangle(14, 2,  501, 442));
			jPanelCameraTabContextDetail.add(getJPanelInCameraTab(), null);
			//jPanelCameraTabContextDetail.add(getJPanel3(), null);
		}
		return jPanelCameraTabContextDetail;
	}
	
	private int SetJPanel(final int i, JPanel TabJPanel) 
	{
		
		JSpLabel[i] = new JLabel();
		JSpLabel[i].setText("Edge" + i);
		JSpLabel[i].setBounds(new Rectangle(0, i*40 + 20, 45, 27));
		
		JTextF[i] = new JTextField();
		JTextF[i].setBounds(new Rectangle(80, i*40 + 20, 180, 22));
		
		JTextF[i].setText("");
		
		TabJPanel.add(JSpLabel[i], null);
		TabJPanel.add(JTextF[i], null);

		return 0;
	}

	public JPanel getJPanelInCameraTab() {
		if (jPanelInCameraTab == null) {
			jPanelInCameraTab = new JPanel();
			jPanelInCameraTab.setLayout(null);
			jPanelInCameraTab.setBounds(new Rectangle(0, 0,  501, 442));
			jPanelInCameraTab.setName("jPanelInCameraTab");

			for (int i=0; i<NodeNum; i++)
			{
				SetJPanel(i, jPanelInCameraTab);
			}
		}
		return jPanelInCameraTab;
	}

	public JPanel getJPanelMiddle() {
		if (jPanelMiddle == null) {
			jLabelStatus = new JLabel();
			jLabelStatus.setBounds(new Rectangle(1, 487, 501, 16));
			jLabelStatus.setText("");
			
			jPanelMiddle = new JPanel();
			jPanelMiddle.setLayout(null);
			jPanelMiddle.add(getJPanelMidToolBar(), null);
			jPanelMiddle.add(getJTabbedPane(), null);
			jPanelMiddle.add(jLabelStatus, null);
		}
		return jPanelMiddle;
	}

	public JPanel getJPanelMidToolBar() {
		if (jPanelToolBarBar == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			gridLayout.setVgap(0);
			gridLayout.setHgap(0);
			jPanelToolBarBar = new JPanel();
			jPanelToolBarBar.setBounds(new Rectangle(0, 0, 501, 37));
			jPanelToolBarBar.setLayout(gridLayout);
			jPanelToolBarBar.add(getJToolBarBar(), null);
		}
		return jPanelToolBarBar;
	}

	public JToolBar getJToolBarBar() {
		if (JToolBarBar == null) {
			JToolBarBar = new JToolBar();
			JToolBarBar.add(getJPanelToolBarBarDetail());
		}
		return JToolBarBar;
		}

	public JPanel getJPanelToolBarBarDetail() {
		if (jPanelToolBarBarDetail == null) {
			jLabelCameraStatus = new JLabel();
			jLabelCameraStatus.setBounds(new Rectangle(350, 19, 120, 15));
			jLabelCameraTotal = new JLabel();
			jLabelCameraTotal.setBounds(new Rectangle(350, 1, 120, 15));
			jPanelToolBarBarDetail = new JPanel();
			jPanelToolBarBarDetail.setLayout(null);
			jPanelToolBarBarDetail.add(getJButtonOK(), null);
			jPanelToolBarBarDetail.add(getJButtonTakePicture(), null);
			jPanelToolBarBarDetail.add(jLabelCameraTotal, null);
			jPanelToolBarBarDetail.add(jLabelCameraStatus, null);
		}
		return jPanelToolBarBarDetail;
	}
	
	public JButton getJButtonOK() 
	{
		if (JButtonSync == null) {
			JButtonSync = new JButton();
			JButtonSync.setText("OK");
			JButtonSync.setToolTipText("CreateGraphic");
			JButtonSync.setBounds(new Rectangle(1, 0, 58, 33));
			JButtonSync.setMnemonic(java.awt.event.KeyEvent.VK_S);
			JButtonSync.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent ae) 
				{
					String edge_str;
					String Node;
					int iStartNode = 0;
					int iEndNodde = NodeNum + 1;
					
					//fetch data
					for (int i=0; i<NodeNum; i++)
					{
						edge_str = JTextF[i].getText();
						
						if (!edge_str.equals(""))
						{
							StringTokenizer Tok = new StringTokenizer(edge_str, ",");
	
							while (Tok.hasMoreElements())
							{
								int edge_node = Integer.parseInt((String) Tok.nextElement());
								GraphaA.SetEdge(i, edge_node);
							}
						}
					}
					
					String message = "";
					//get start node 
					while (iEndNodde > NodeNum-1)
					{
						Node = JOptionPane.showInputDialog(null,
								message + "請選擇欲搜尋的頂點編號?",
								  "建立Graph圖片",
								  JOptionPane.QUESTION_MESSAGE);
						iEndNodde = Integer.parseInt(Node);
						message = "找不到這個頂點, ";
					}
					
					boolean[] G = new boolean[NodeNum + 1];
					for (int i=0; i<NodeNum + 1; i++)
					{
							G[i] = false;
					}					
					
					GraphaA.DFS(iStartNode, G, iEndNodde);

					for (int i=0; i<NodeNum + 1; i++)
					{
							G[i] = false;
					}					

					GraphaA.BFS(iStartNode, G, iEndNodde);
					
					ArrayList<String> dfslist = GraphaA.getDFSArrayList();
					jLabelDFSDisplay.setText("DFS: " + dfslist);

					ArrayList<String> bfslist = GraphaA.getBFSArrayList();
					jLabelBFSDisplay.setText("BFS: " + bfslist);
					jTabbedPane.setSelectedIndex(1);
					//JOptionPane.showMessageDialog(null, "OK", "message", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return JButtonSync;
	}
	
	private JButton getJButtonTakePicture() 
	{
		if (JButtonTakePicture == null) {
			JButtonTakePicture = new JButton();
			JButtonTakePicture.setBounds(new Rectangle(70, 0, 65, 33));
			JButtonTakePicture.setMnemonic(KeyEvent.VK_R);
			JButtonTakePicture.setText("Clear");
			JButtonTakePicture.setToolTipText("Clear");
			JButtonTakePicture
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) 
						{
							jLabelDFSDisplay.setText("DFS: test");
							jLabelBFSDisplay.setText("BFS: test");
										
						}
					});
			JButtonTakePicture.setMnemonic(java.awt.event.KeyEvent.VK_R);
		}
		return JButtonTakePicture;
	}

	public JPanel getJPanelTabGraphic() {
		if (jPanelGraphicTabContext == null) {
			jPanelGraphicTabContext = new JPanel();
			jPanelGraphicTabContext.setLayout(null);
			jPanelGraphicTabContext.add(getJPanelGraphicTabContext(), null);
		}
		return jPanelGraphicTabContext;
	}
	
	public JPanel getJPanelGraphicTabContext() {
		if (jPanelGraphicTabContextDetail == null) {
			jPanelGraphicTabContextDetail = new JPanel();
			jPanelGraphicTabContextDetail.setLayout(null);
			jPanelGraphicTabContextDetail.setBounds(new Rectangle(14, 2,  501, 442));
			jPanelGraphicTabContextDetail.add(getJPanelGraphicTab(), null);
			//jPanelCameraTabContextDetail.add(getJPanel3(), null);
		}
		return jPanelGraphicTabContextDetail;
	}
	
	private int SetDisplayJPanel(JPanel TabJPanel) 
	{
		
		jLabelDFSDisplay = new JLabel();
		jLabelDFSDisplay.setBounds(new Rectangle(0, 20, 180, 27));
		
		jLabelBFSDisplay = new JLabel();
		jLabelBFSDisplay.setBounds(new Rectangle(0, 60, 180, 27));
		
		TabJPanel.add(jLabelDFSDisplay, null);
		TabJPanel.add(jLabelBFSDisplay, null);
		
		return 0;
	}

	public JPanel getJPanelGraphicTab() {
		if (jPanelGraphicTab == null) {
			jPanelGraphicTab = new JPanel();
			jPanelGraphicTab.setLayout(null);
			jPanelGraphicTab.setBounds(new Rectangle(0, 0,  501, 442));
			jPanelGraphicTab.setName("jPanelGraphicTab");
			
			SetDisplayJPanel(jPanelGraphicTab);

		}
		return jPanelGraphicTab;
	}

	public static void main(String[] args) 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       
		String nodes = JOptionPane.showInputDialog(null,
				  "請選擇欲搜尋的頂點編號",
				  "建立Graph",
				  JOptionPane.QUESTION_MESSAGE);
      
		Main app = new Main(Integer.parseInt(nodes));
		app.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});
		
		Dimension frameSize = app.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		app.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		app.setVisible(true);
	}
}