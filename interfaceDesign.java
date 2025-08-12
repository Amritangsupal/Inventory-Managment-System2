package mainPackage;
import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class interfaceDesign {

	private JFrame frame;
	private JTextField txtPname;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JTextField txtProductid;
	private JTextField txtSellid;
	private JTextField txtsellquantity;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaceDesign window = new interfaceDesign();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public interfaceDesign() {
		initialize();
		buildConnention();
		LoadTable();
		
	}
	Connection con;
	PreparedStatement prestm;
	ResultSet rst;
	public void buildConnention() {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root", "12345678");
			System.out.println("Done with the stable connection");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void LoadTable() {
		try {
			prestm=con.prepareCall("select * from inventory_table");
			rst=prestm.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rst));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 498, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Inventory Management System");
		lblNewLabel_1.setFont(new Font("Sylfaen", Font.BOLD, 30));
		lblNewLabel_1.setBounds(40, 0, 425, 51);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Add Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 61, 230, 232);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 53, 100, 13);
		panel.add(lblNewLabel);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuantity.setBounds(10, 93, 100, 13);
		panel.add(lblQuantity);
		
		JLabel lblNewLabel_2_1 = new JLabel("Price Per Item");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(10, 134, 119, 13);
		panel.add(lblNewLabel_2_1);
		
		txtPname = new JTextField();
		txtPname.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtPname.setBounds(124, 52, 96, 19);
		panel.add(txtPname);
		txtPname.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtQuantity.setBounds(124, 92, 96, 19);
		panel.add(txtQuantity);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtPrice.setBounds(124, 133, 96, 19);
		panel.add(txtPrice);
		
		JButton btnadditem = new JButton("Add Item");
		btnadditem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,quantity,price;
				name=txtPname.getText();
				quantity=txtQuantity.getText();
				price=txtPrice.getText();
				try {
				
				prestm=con.prepareCall("insert into  inventory_table(ProductName,Quantity,PriceperItem)values(?,?,?)");
				
				prestm.setString(1, name);
				prestm.setString(2, quantity);
				prestm.setString(3, price);
				prestm.executeUpdate();
				JOptionPane.showMessageDialog(null,"Item Added!!");
				
				LoadTable();
				txtPname.setText("");
				txtQuantity.setText("");
				txtPrice.setText("");
				txtPname.requestFocus();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
		btnadditem.setBounds(10, 177, 85, 21);
		panel.add(btnadditem);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPname.setText("");
				txtQuantity.setText("");
				txtPrice.setText("");
				txtPname.requestFocus();
			}
			
		});
		btnClear.setBounds(130, 177, 85, 21);
		panel.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 303, 230, 86);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel txtSearchid = new JLabel("Search Item");
		txtSearchid.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtSearchid.setBounds(0, 0, 121, 20);
		txtSearchid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(txtSearchid);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Enter product Id");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_1_1.setBorder(null);
		lblNewLabel_2_1_1_1.setBounds(0, 30, 121, 20);
		panel_1.add(lblNewLabel_2_1_1_1);
		
		txtProductid = new JTextField();
		txtProductid.setColumns(10);
		txtProductid.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtProductid.setBounds(131, 30, 96, 19);
		panel_1.add(txtProductid);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchId=txtProductid.getText();
				try {
					prestm=con.prepareCall("select * from inventory_table where ID=?");
					prestm.setString(1, searchId);
					
					rst=prestm.executeQuery();
					if(rst.next()) {
						txtPname.setText(rst.getString(2));
						txtQuantity.setText(rst.getString(3));
						txtPrice.setText(rst.getString(4));
						
					}
					else {
						txtPname.setText("");
						txtQuantity.setText("");
						txtPrice.setText("");
						txtPname.requestFocus();
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});

		btnSearch.setBounds(69, 60, 85, 21);
		panel_1.add(btnSearch);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Modify Record", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 399, 230, 79);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,quantity,price,id;
				
				name=txtPname.getText();
				quantity=txtQuantity.getText();
				price=txtPrice.getText();
				id=txtProductid.getText();
			
				try {
				
				prestm=con.prepareCall("update  inventory_table set ProductName=?,Quantity=?,PriceperItem=? where ID=?");
				
				prestm.setString(1, name);
				prestm.setString(2, quantity);
				prestm.setString(3, price);
				prestm.setString(4, id);
				prestm.executeUpdate();
				JOptionPane.showMessageDialog(null,"Item Updated!!");
				
				LoadTable();
				txtPname.setText("");
				txtQuantity.setText("");
				txtPrice.setText("");
				txtPname.requestFocus();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(10, 48, 85, 21);
		panel_2.add(btnUpdate);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=txtProductid.getText();
                try {
					prestm=con.prepareCall("Delete from inventory_table  where ID=?");
					prestm.setString(1, id);
					prestm.executeUpdate();
					JOptionPane.showMessageDialog(null,"Item Deleted!!");
					LoadTable();
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					txtPname.requestFocus();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btndelete.setBounds(123, 48, 85, 21);
		panel_2.add(btndelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 74, 224, 212);
		frame.getContentPane().add(scrollPane);

		table_1 = new JTable();
		table_1.setGridColor(new Color(0, 0, 255));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table_1);

		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			}
		});
		btnExit.setBounds(409, 57, 65, 21);
		frame.getContentPane().add(btnExit);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Sell Item", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(250, 306, 224, 172);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Product id");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_1_1_1.setBorder(null);
		lblNewLabel_2_1_1_1_1.setBounds(10, 37, 90, 20);
		panel_4.add(lblNewLabel_2_1_1_1_1);
		
		txtSellid = new JTextField();
		txtSellid.setColumns(10);
		txtSellid.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtSellid.setBounds(118, 40, 96, 19);
		panel_4.add(txtSellid);
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Quantity");
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_1_1_1_1.setBorder(null);
		lblNewLabel_2_1_1_1_1_1.setBounds(10, 93, 90, 20);
		panel_4.add(lblNewLabel_2_1_1_1_1_1);
		
		txtsellquantity = new JTextField();
		txtsellquantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sellid = txtSellid.getText();
				String sellQuantity = txtsellquantity.getText();
				try {
					prestm = con.prepareCall("select * from inventory_table where ID=?");
					prestm.setString(1, sellid);
					
					rst = prestm.executeQuery();
				
					if (rst.next()) {
						int availableQty = Integer.parseInt(rst.getString(3));
						int requestedQty = Integer.parseInt(sellQuantity);
						
						if (requestedQty <= availableQty) {
							txtPname.setText(rst.getString(2));
							txtQuantity.setText(rst.getString(3));
							txtPrice.setText(rst.getString(4));
						} else {
							// Not enough stock
							txtPname.setText("");
							txtQuantity.setText("");
							txtPrice.setText("");
							txtPname.requestFocus();
						}
					} else {
						// No such item found
						txtPname.setText("");
						txtQuantity.setText("");
						txtPrice.setText("");
						txtPname.requestFocus();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		txtsellquantity.setColumns(10);
		txtsellquantity.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtsellquantity.setBounds(118, 96, 96, 19);
		panel_4.add(txtsellquantity);
		
		JButton btnSellitem = new JButton("Sell Item");
		btnSellitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
String name,quantity,price,id;
				
				name=txtPname.getText();
				quantity=txtQuantity.getText();
				price=txtPrice.getText();
				id=txtSellid.getText();
				String sellQuantity=txtsellquantity.getText();
				Integer diff=Integer.parseInt(quantity)-Integer.parseInt(sellQuantity);
			
				try {
				
				prestm=con.prepareCall("update  inventory_table set Quantity=? where ID=?");
				
				
				prestm.setString(1, diff.toString());
				
				prestm.setString(2, id);
				prestm.executeUpdate();
				JOptionPane.showMessageDialog(null,"Item Sold!!");
				
				LoadTable();
				txtPname.setText("");
				txtQuantity.setText("");
				txtPrice.setText("");
				txtPname.requestFocus();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			
			}
		});

		btnSellitem.setBounds(65, 123, 85, 21);
		panel_4.add(btnSellitem);
	}

	private void addActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		
	}
}
