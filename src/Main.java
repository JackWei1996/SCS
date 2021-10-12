/***************************自助校园卡系统1.0********************************/
/*********************                              *************************/
/*********************         Jack魏制作           *************************/
/*********************    2016.12..22-2016.12.23    *************************/
/****************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/*-------------------------取原卡金额界面------------------------------*/
class Money extends Frame implements ActionListener
{
	Label tag1, tag2, tag3;
	TextField t1, t2, t3;
	Button yes, no;
	String cardpass, cardbalance;

	Connection con;
	Statement sql;
	ResultSet rs;

	Money(){
		super("取原卡金额界面");
		this.setFont(new Font("黑体", Font.BOLD, 18));
		this.setBackground(Color.green);

		yes = new Button("确定");
		no = new Button("取消");

		tag1 = new Label("	  证件号:");
		tag2 = new Label("	      姓名:");
		tag3 = new Label("    卡密码:");

		t1 = new TextField(20);
		t2 = new TextField(20);
		t3 = new TextField(20);

		setLayout(new FlowLayout());
		add(tag1);
		add(t1);
		add(tag2);
		add(t2);
		add(tag3);
		add(t3);
		add(yes);
		add(no);

		t3.addActionListener(this);
		yes.addActionListener(this);
		no.addActionListener(this);

		t3.setEchoChar('*');

		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		try{
			con = DriverManager.getConnection("jdbc:odbc:Card","","");
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM Card");

			while(rs.next()){
				t1.setText(rs.getString("CardID"));
				t2.setText(rs.getString("Name"));
				cardpass = rs.getString("CardPass");
				cardbalance = rs.getString("CardBalance");
			}
			con.close();
		}
		catch(SQLException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "读取错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		setSize(400, 210);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);
		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == yes){
			if(t3.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入密码", "错误提示框",JOptionPane.ERROR_MESSAGE);
			else{
				if(cardpass.equals(t3.getText())){
					try{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					}
					catch(ClassNotFoundException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
					try{
						con = DriverManager.getConnection("jdbc:odbc:Card","","");
						sql = con.createStatement();
						rs = sql.executeQuery("SELECT * FROM Card");
						String updata1 = "UPDATE Card SET CardBalance =? WHERE CardBalance = "+"\'" + cardbalance +"\'";
						String updata2 = "UPDATE Card SET State =? WHERE State = "+"\'" + "冻结" +"\'";
						while(rs.next())
						if(rs.getString("State").equals("冻结"))
						{
							PreparedStatement ps = con.prepareStatement(updata1);
							ps.setString(1, String.valueOf(rs.getString("CardBalance")));
							ps.executeUpdate();
							ps = con.prepareStatement(updata2);
							ps.setString(1, "正常");
							ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "取回成功");
						}
						else
							JOptionPane.showMessageDialog(null, "校园卡未冻结，无法取回原卡金额！", "错误提示框",JOptionPane.ERROR_MESSAGE);
						con.close();
					}
					catch(SQLException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "密码输入错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
			}

		}
		else if(ae.getSource() == no)
			dispose();
	}
}

/*-------------------------修改密码界面------------------------------*/
class Password extends Frame implements ActionListener
{
	Label tag1, tag2, tag3, tag4,tag5;
	TextField t1, t2, t3, t4, t5;
	Button yes, no;
	String cardpass;

	Connection con;
	Statement sql;
	ResultSet rs;

	Password(){
		super("修改密码界面");
		this.setFont(new Font("黑体", Font.BOLD, 18));
		this.setBackground(Color.green);

		yes = new Button("确定");
		no = new Button("取消");

		tag1 = new Label("	  证件号:");
		tag2 = new Label("	      姓名:");
		tag3 = new Label("    旧密码:");
		tag4 = new Label("    新密码:");
		tag5 = new Label("确认密码:");

		t1 = new TextField(20);
		t2 = new TextField(20);
		t3 = new TextField(20);
		t4 = new TextField(20);
		t5 = new TextField(20);

		setLayout(new FlowLayout());
		add(tag1);
		add(t1);
		add(tag2);
		add(t2);
		add(tag3);
		add(t3);
		add(tag4);
		add(t4);
		add(tag3);
		add(t3);
		add(tag4);
		add(t4);
		add(tag5);
		add(t5);
		add(yes);
		add(no);

		t3.addActionListener(this);
		t4.addActionListener(this);
		t5.addActionListener(this);
		yes.addActionListener(this);
		no.addActionListener(this);

		t3.setEchoChar('*');
		t4.setEchoChar('*');
		t5.setEchoChar('*');

		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		try{
			con = DriverManager.getConnection("jdbc:odbc:Card","","");
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM Card");

			while(rs.next()){
				t1.setText(rs.getString("CardID"));
				t2.setText(rs.getString("Name"));
				cardpass = rs.getString("CardPass");
			}
			con.close();
		}
		catch(SQLException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		setSize(400, 270);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);

		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == yes){
			if(t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入密码", "错误提示框",JOptionPane.ERROR_MESSAGE);
			if( !t4.getText().equals(t5.getText()))
				JOptionPane.showMessageDialog(null, "新密码和确认密码不一致", "错误提示框",JOptionPane.ERROR_MESSAGE);
			else{
				if(cardpass.equals(t3.getText())){
					try{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					}
					catch(ClassNotFoundException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
					try{
						con = DriverManager.getConnection("jdbc:odbc:Card","","");
						sql = con.createStatement();
						rs = sql.executeQuery("SELECT * FROM Card");
						String updata = "UPDATE Card SET CardPass =? WHERE CardPass = "+"\'" + t3.getText()+"\'";

						PreparedStatement ps = con.prepareStatement(updata);
						ps.setString(1, String.valueOf(t4.getText()));
						ps.executeUpdate();

						JOptionPane.showMessageDialog(null, "密码修改成功");
						con.close();
					}
					catch(SQLException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "密码输入错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
			}

		}
		else if(ae.getSource() == no)
			dispose();
	}
}

/*-------------------------校园卡挂失界面------------------------------*/
class LossGUI extends Frame implements ActionListener
{
	Label tag1;
	TextField t1;
	Button yes, no;

	LossGUI(){
		super("校园卡挂失界面");
		this.setFont(new Font("黑体", Font.BOLD, 20));
		this.setBackground(Color.green);

		tag1 = new Label("证件号：");
		t1 = new TextField(20);
		yes = new Button("确定");
		no = new Button("取消");

		setLayout(new FlowLayout());
		add(tag1);
		add(t1);
		add(yes);
		add(no);
		t1.addActionListener(this);
		yes.addActionListener(this);
		no.addActionListener(this);

		setSize(300, 190);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);
		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent ae)
	{
		Connection con;
		Statement sql;
		ResultSet rs;
		if(ae.getSource() == yes){
			if(t1.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入证件号", "错误提示框",JOptionPane.ERROR_MESSAGE);
			else{
				try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				}
				catch(ClassNotFoundException e){
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
				}
				try{
					con = DriverManager.getConnection("jdbc:odbc:Card","","");
					sql = con.createStatement();
					rs = sql.executeQuery("SELECT * FROM Card");
					while(rs.next())
					if(t1.getText().equals(rs.getString("CardID")))
					{
						String updata = "UPDATE Card SET State =? WHERE State = "+"\'" + rs.getString("State")+"\'";
						PreparedStatement ps = con.prepareStatement(updata);
						ps.setString(1, String.valueOf("冻结"));
						ps.executeUpdate();

						JOptionPane.showMessageDialog(null, "此卡已被冻结");
					}
					else
						JOptionPane.showMessageDialog(null, "证件号输入错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
					con.close();
				}
				catch(SQLException e){
					System.out.println(e);
					JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(ae.getSource() == no)
			dispose();
	}
}

/*-------------------------信息查询界面------------------------------*/
class Query extends Frame implements ActionListener
{
	Label tag1, tag2, tag3, tag4,tag5, tag6, tag7, tag8,tag9;
	TextField t1, t2, t3, t4, t5, t6, t7, t8, t9;

	Connection con;
	Statement sql;
	ResultSet rs;

	Query(){
		super("信息查询界面");
		this.setFont(new Font("黑体", Font.BOLD, 18));
		this.setBackground(Color.green);

		tag1 = new Label("	             姓名:");
		tag2 = new Label("	         证件号:");
		tag3 = new Label("              班级:");
		tag4 = new Label("              性别:");
		tag5 = new Label("       账户类型:");
		tag6 = new Label("       账户状态:");
		tag7 = new Label("       账户余额:");
		tag8 = new Label("每次消费限额:");
		tag9 = new Label("每天消费限额:");

		t1 = new TextField(20);
		t2 = new TextField(20);
		t3 = new TextField(20);
		t4 = new TextField(20);
		t5 = new TextField(20);
		t6 = new TextField(20);
		t7 = new TextField(20);
		t8 = new TextField(20);
		t9 = new TextField(20);

		setLayout(new FlowLayout());
		add(tag1);
		add(t1);
		add(tag2);
		add(t2);
		add(tag3);
		add(t3);
		add(tag4);
		add(t4);
		add(tag3);
		add(t3);
		add(tag4);
		add(t4);
		add(tag5);
		add(t5);
		add(tag6);
		add(t6);
		add(tag7);
		add(t7);
		add(tag8);
		add(t8);
		add(tag9);
		add(t9);

		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		try{
			con = DriverManager.getConnection("jdbc:odbc:Card","","");
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM Card");

			while(rs.next()){
				t1.setText(rs.getString("Name"));
				t2.setText(rs.getString("CardID"));
				t3.setText(rs.getString("Class"));
				t4.setText(rs.getString("Sex"));
				t5.setText(rs.getString("Type"));
				t6.setText(rs.getString("State"));
				t7.setText(rs.getString("CardBalance"));
				t8.setText(rs.getString("NextMax"));
				t9.setText(rs.getString("DayMax"));
			}
			con.close();
		}
		catch(SQLException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		setSize(420, 380);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);

		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent ae){}
}

/*-------------------------自助服务界面------------------------------*/
class SeverGUI extends Frame implements ActionListener
{
	Button queryBtn, lossBtn, passBtn, moneyBtn;
	Image myImage;

	SeverGUI(){
		super("自助服务界面");

//初始化标题
		queryBtn = new Button("信息查询");
		lossBtn = new Button("校园卡挂失");
		passBtn = new Button("修改密码");
		moneyBtn = new Button("取原卡金额");
		myImage = new ImageIcon("back_word.jpg").getImage();
//初始化位置
		queryBtn.setBounds(100,350,128,49);	//setBounds(x,y,width,height)
		lossBtn.setBounds(350,350,128,49);
		passBtn.setBounds(100,450,128,49);
		moneyBtn.setBounds(350,450,128,49);
//初始化颜色
		queryBtn.setForeground(Color.blue);
		queryBtn.setBackground(Color.green);
		lossBtn.setForeground(Color.blue);
		lossBtn.setBackground(Color.green);
		passBtn.setForeground(Color.blue);
		passBtn.setBackground(Color.green);
		moneyBtn.setForeground(Color.blue);
		moneyBtn.setBackground(Color.green);

		setLayout(null);

		add(queryBtn);
		add(lossBtn);
		add(passBtn);
		add(moneyBtn);

		queryBtn.addActionListener(this);
		lossBtn.addActionListener(this);
		passBtn.addActionListener(this);
		moneyBtn.addActionListener(this);

		setSize(570, 620);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);
		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == queryBtn)
			new Query();
		else if(e.getSource() == lossBtn)
			new LossGUI();
		else if(e.getSource() == passBtn)
			new Password();
		else if(e.getSource() == moneyBtn)
			new Money();
	}

	public void paint(Graphics g)//显示图片
	{
		g.drawImage(myImage, 0, 0, this);
	}
}

/*-------------------------自购水费界面------------------------------*/
class Water extends Frame implements ActionListener
{
	Label tag1, tag2, tag3, tag4,tag5,tag6;
	TextField t1, t2, t3, t4, t5, t6;
	Button yes, no;
	String electricbalance;
	String cardpass;
	String cardbalance;

	Connection con;
	Statement sql;
	ResultSet rs;

	Water(){
		super("自购水费界面");
		this.setFont(new Font("黑体", Font.BOLD, 18));
		this.setBackground(Color.green);

		yes = new Button("充值");
		no = new Button("取消");

		tag1 = new Label("	  证件号:");
		tag2 = new Label("	      姓名:");
		tag3 = new Label("账户余额:");
		tag4 = new Label("水费余额:");
		tag5 = new Label("转账金额:");
		tag6 = new Label("    卡密码:");

		t1 = new TextField(20);
		t2 = new TextField(20);
		t3 = new TextField(20);
		t4 = new TextField(20);
		t5 = new TextField(20);
		t6 = new TextField(20);

		setLayout(new FlowLayout());
		add(tag1);
		add(t1);
		add(tag2);
		add(t2);
		add(tag3);
		add(t3);
		add(tag4);
		add(t4);
		add(tag3);
		add(t3);
		add(tag4);
		add(t4);
		add(tag5);
		add(t5);
		add(tag6);
		add(t6);
		add(yes);
		add(no);

		t3.addActionListener(this);		//账户余额
		t4.addActionListener(this);		//水费余额
		t5.addActionListener(this);		//转账金额
		yes.addActionListener(this);
		no.addActionListener(this);

		t6.setEchoChar('*');

		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		try{
			con = DriverManager.getConnection("jdbc:odbc:Card","","");
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM Card");

			while(rs.next()){
				t1.setText(rs.getString("CardID"));
				t2.setText(rs.getString("Name"));
				t3.setText(cardbalance = rs.getString("CardBalance"));
				t4.setText(rs.getString("WaterBalance"));
				cardpass = rs.getString("CardPass");
			}
			con.close();
		}
		catch(SQLException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		setSize(400, 300);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);
		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == yes)
		{
			if(t5.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入金额", "错误提示框",JOptionPane.ERROR_MESSAGE);
			if(t6.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入密码", "错误提示框",JOptionPane.ERROR_MESSAGE);
			if(Double.parseDouble(t5.getText()) < 0)
				JOptionPane.showMessageDialog(null, "充值金额不能小于零", "错误提示框",JOptionPane.ERROR_MESSAGE);
			else{
				if(cardpass.equals(t6.getText())){
					try{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					}
					catch(ClassNotFoundException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
					try{
						con = DriverManager.getConnection("jdbc:odbc:Card","","");
						sql = con.createStatement();
						rs = sql.executeQuery("SELECT * FROM Card");
						double w = Double.parseDouble(t4.getText()) + (Double.parseDouble(t5.getText()));
						double b = Double.parseDouble(cardbalance) - Double.parseDouble(t5.getText());
						String updata1 = "UPDATE Card SET CardBalance =? WHERE CardBalance = "+"\'" + t3.getText()+"\'";
						String updata2 = "UPDATE Card SET WaterBalance =? WHERE WaterBalance = "+"\'" + t4.getText()+"\'";

						PreparedStatement ps = con.prepareStatement(updata1);
						ps.setString(1, String.valueOf(b));
						ps.executeUpdate();
						ps = con.prepareStatement(updata2);
						ps.setString(1, String.valueOf(w));
						ps.executeUpdate();

						JOptionPane.showMessageDialog(null, "充值成功");
						con.close();
					}
					catch(SQLException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "密码输入错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ae.getSource() == no)
			dispose();
	}
}

/*-------------------------自购电费界面------------------------------*/
class Electric extends Frame implements ActionListener
{
	Label tag1, tag2, tag3, tag4,tag5, tag6, tag7, tag8,tag9, tag10;
	TextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
	Button yes, no;
	String electricbalance;
	String cardpass;
	String cardbalance;

	Connection con;
	Statement sql;
	ResultSet rs;

	Electric(){
		super("自购电费界面");
		this.setFont(new Font("黑体", Font.BOLD, 18));
		this.setBackground(Color.green);

		yes = new Button("充值");
		no = new Button("取消");

		tag1 = new Label("	  证件号:");
		tag2 = new Label("	      姓名:");
		tag3 = new Label("账户状态:");
		tag4 = new Label("   卡余额:");
		tag5 = new Label("       校区:");
		tag6 = new Label("       楼栋:");
		tag7 = new Label("       房间:");
		tag8 = new Label("剩余电量:");
		tag9 = new Label("缴费金额:");
		tag10 = new Label("    卡密码:");

		t1 = new TextField(20);
		t2 = new TextField(20);
		t3 = new TextField(20);
		t4 = new TextField(20);
		t5 = new TextField(20);
		t6 = new TextField(20);
		t7 = new TextField(20);
		t8 = new TextField(20);
		t9 = new TextField(20);
		t10 = new TextField(20);

		setLayout(new FlowLayout());
		add(tag1);
		add(t1);
		add(tag2);
		add(t2);
		add(tag3);
		add(t3);
		add(tag4);
		add(t4);
		add(tag3);
		add(t3);
		add(tag4);
		add(t4);
		add(tag5);
		add(t5);
		add(tag6);
		add(t6);
		add(tag7);
		add(t7);
		add(tag8);
		add(t8);
		add(tag9);
		add(t9);
		add(tag10);
		add(t10);
		add(yes);
		add(no);

		t3.addActionListener(this);
		t4.addActionListener(this);
		t8.addActionListener(this);
		t9.addActionListener(this);
		t10.addActionListener(this);
		yes.addActionListener(this);
		no.addActionListener(this);

		t10.setEchoChar('*');

		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		try{
			con = DriverManager.getConnection("jdbc:odbc:Card","","");
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM Card");

			while(rs.next()){
				t1.setText(rs.getString("CardID"));
				t2.setText(rs.getString("Name"));
				t3.setText(electricbalance = rs.getString("ElectricState"));
				t4.setText(cardbalance = rs.getString("CardBalance"));
				t5.setText(rs.getString("Campus"));
				t6.setText(rs.getString("Building"));
				t7.setText(rs.getString("Dorm"));
				t8.setText(rs.getString("ElectricBalance"));
				cardpass = rs.getString("CardPass");
			}
			con.close();
		}
		catch(SQLException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		setSize(400, 450);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);

		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == yes){
			if(t9.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入金额", "错误提示框",JOptionPane.ERROR_MESSAGE);
			if(t10.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入密码", "错误提示框",JOptionPane.ERROR_MESSAGE);
			if(Double.parseDouble(t9.getText()) < 0)
				JOptionPane.showMessageDialog(null, "充值金额不能小于零", "错误提示框",JOptionPane.ERROR_MESSAGE);
			else{
				if(cardpass.equals(t10.getText())){
					try{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					}
					catch(ClassNotFoundException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
					try{
						con = DriverManager.getConnection("jdbc:odbc:Card","","");
						sql = con.createStatement();
						rs = sql.executeQuery("SELECT * FROM Card");
						double e = Double.parseDouble(t8.getText()) + (Double.parseDouble(t9.getText())*0.5);
						double b = Double.parseDouble(cardbalance) - Double.parseDouble(t9.getText());
						String updata1 = "UPDATE Card SET CardBalance =? WHERE CardBalance = "+"\'" + t4.getText()+"\'";
						String updata2 = "UPDATE Card SET ElectricBalance =? WHERE ElectricBalance = "+"\'" + t8.getText()+"\'";
						String updata3 = "UPDATE Card SET ElectricState =? WHERE ElectricState = "+"\'" + t3.getText()+"\'";

						PreparedStatement ps = con.prepareStatement(updata1);
						ps.setString(1, String.valueOf(b));
						ps.executeUpdate();
						ps = con.prepareStatement(updata2);
						ps.setString(1, String.valueOf(e));
						ps.executeUpdate();
						if(e<0){
							ps = con.prepareStatement(updata3);
							ps.setString(1, String.valueOf("欠费"));
							ps.executeUpdate();
						}
						JOptionPane.showMessageDialog(null, "充值成功");
						con.close();
					}
					catch(SQLException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "密码输入错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ae.getSource() == no)
			dispose();
	}
}

/*-------------------------银校转账界面------------------------------*/
class BankTransferGUI extends Frame implements ActionListener
{
	Label tag1, tag2, tag3, tag4;
	TextField t1, t2, t3, t4;
	Button yes, no;
	String bankbalance;
	String bankpass;

	Connection con;
	Statement sql;
	ResultSet rs;

	BankTransferGUI(){
		super("银校转账界面");
		this.setFont(new Font("黑体", Font.BOLD, 18));
		this.setBackground(Color.green);

		yes = new Button("转账");
		no = new Button("取消");
		tag1 = new Label("身份证号：");
		tag2 = new Label("   卡余额：");
		tag3 = new Label("充值金额：");
		tag4 = new Label("银行密码：");

		t1 = new TextField(20);
		t2 = new TextField(20);
		t3 = new TextField(20);
		t4 = new TextField(20);

		setLayout(new FlowLayout());
		add(tag1);
		add(t1);
		add(tag2);
		add(t2);
		add(tag3);
		add(t3);
		add(tag4);
		add(t4);
		add(yes);
		add(no);

		t1.addActionListener(this);
		t2.addActionListener(this);
		t3.addActionListener(this);
		t4.addActionListener(this);
		yes.addActionListener(this);
		no.addActionListener(this);

		t4.setEchoChar('*');

		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		try{
			con = DriverManager.getConnection("jdbc:odbc:Card","","");
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM Card");

			while(rs.next()){
				t1.setText(rs.getString("IDcard"));
				t2.setText(rs.getString("CardBalance"));
				bankbalance = (rs.getString("BankBalance"));
				bankpass = (rs.getString("BankPass"));
			}
			con.close();
		}
		catch(SQLException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		setSize(400, 250);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);
		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == yes){
			if(t3.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入金额", "错误提示框",JOptionPane.ERROR_MESSAGE);
			if(t4.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入密码", "错误提示框",JOptionPane.ERROR_MESSAGE);
			if(Double.parseDouble(t3.getText()) < 0)
				JOptionPane.showMessageDialog(null, "充值金额不能小于零", "错误提示框",JOptionPane.ERROR_MESSAGE);
			else{
				if(bankpass.equals(t4.getText())){
					try{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					}
					catch(ClassNotFoundException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
					try{
						con = DriverManager.getConnection("jdbc:odbc:Card","","");
						sql = con.createStatement();
						rs = sql.executeQuery("SELECT * FROM Card");
						double mane = Double.parseDouble(t3.getText()) + Double.parseDouble(t2.getText());
						double bmane = Double.parseDouble(bankbalance) - Double.parseDouble(t3.getText());
						String updata1 = "UPDATE Card SET CardBalance =? WHERE CardBalance = "+"\'" + t2.getText()+"\'";
						String updata2 = "UPDATE Card SET BankBalance =? WHERE BankBalance = "+"\'" + bankbalance+"\'";

						PreparedStatement ps = con.prepareStatement(updata1);
						ps.setString(1, String.valueOf(mane));
						ps.executeUpdate();
						ps = con.prepareStatement(updata2);
						ps.setString(1, String.valueOf(bmane));
						ps.executeUpdate();

						JOptionPane.showMessageDialog(null, "充值成功");
						con.close();
					}
					catch(SQLException e){
						System.out.println(e);
						JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "密码输入错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
			}

		}
		else if(ae.getSource() == no)
			dispose();
	}
}

/*-------------------------查询银行余额界面------------------------------*/
class BankBalanceGUI extends Frame implements ActionListener
{
	Label tag1, tag2;
	TextField t1, t2;

	BankBalanceGUI(){
		super("查询银行余额界面");
		this.setFont(new Font("黑体", Font.BOLD, 20));
		this.setBackground(Color.green);

		tag1 = new Label("身份证号：");
		tag2 = new Label("银行余额：");
		t1 = new TextField(20);
		t2 = new TextField(20);

		setLayout(new FlowLayout());
		add(tag1);
		add(t1);
		add(tag2);
		add(t2);
		t1.addActionListener(this);
		t2.addActionListener(this);

		Connection con;
		Statement sql;
		ResultSet rs;

		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "数据库配置错误！", "错误提示框",JOptionPane.ERROR_MESSAGE);
		}
		try{
			con = DriverManager.getConnection("jdbc:odbc:Card","","");
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM Card");
			while(rs.next()){
				t1.setText(rs.getString("IDcard"));
				t2.setText(rs.getString("BankBalance"));
			}

			con.close();
		}
		catch(SQLException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "错误提示框",JOptionPane.ERROR_MESSAGE);
		}

		setSize(400,140);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);
		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e){}
}

/*-------------------------银行服务界面------------------------------*/
class BankGUI extends Frame implements ActionListener
{
	Button blance, transfer;
	Image myImage;

	BankGUI( ){
		super("银行服务");

//初始化标题
		blance = new Button("查询银行余额");
		transfer = new Button("银行转账");
		myImage = new ImageIcon("back_word.jpg").getImage();
//初始化位置
		blance.setBounds(100,350,150,60);//setBounds(x,y,width,height)
		transfer.setBounds(350,350,150,60);
//初始化颜色
		blance.setForeground(Color.blue);
		blance.setBackground(Color.green);
		transfer.setForeground(Color.blue);
		transfer.setBackground(Color.green);

		setLayout(null);
		add(blance);
		add(transfer);
		blance.addActionListener(this);
		transfer.addActionListener(this);

		setSize(570, 620);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);
		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == blance)
			new BankBalanceGUI();
		else if(e.getSource() == transfer)
			new BankTransferGUI();
	}
	public void paint(Graphics g)//显示图片
	{
		g.drawImage(myImage, 0, 0, this);
	}
}

/*-------------------------主界面图------------------------------*/
class InterfaceGUI extends Frame implements ActionListener
{
	Button bankBtn, electBtn, waterBtn, selfBtn;
	Image myImage;

	InterfaceGUI(){
		super("校园卡自助服务系统");

//初始化标题
		bankBtn = new Button("银行服务");
		electBtn = new Button("自购电费");
		waterBtn = new Button("自购水费");
		selfBtn = new Button("自助服务");
		myImage = new ImageIcon("back_word.jpg").getImage();
//初始化位置
		bankBtn.setBounds(100,350,128,49);	//setBounds(x,y,width,height)
		electBtn.setBounds(350,350,128,49);
		waterBtn.setBounds(100,450,128,49);
		selfBtn.setBounds(350,450,128,49);
//初始化颜色
		bankBtn.setForeground(Color.blue);
		bankBtn.setBackground(Color.green);
		electBtn.setForeground(Color.blue);
		electBtn.setBackground(Color.green);
		waterBtn.setForeground(Color.blue);
		waterBtn.setBackground(Color.green);
		selfBtn.setForeground(Color.blue);
		selfBtn.setBackground(Color.green);

		setLayout(null);
		add(bankBtn);
		add(electBtn);
		add(waterBtn);
		add(selfBtn);

		bankBtn.addActionListener(this);
		electBtn.addActionListener(this);
		waterBtn.addActionListener(this);
		selfBtn.addActionListener(this);

		setSize(570, 620);
		//设置窗口居中显示
		Toolkit tool;
		int w, h;
		tool = Toolkit.getDefaultToolkit();
		w = (tool.getScreenSize().width - this.getWidth()) / 2;
		h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getWidth()) / 2;
		setLocation(w, h);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == bankBtn)
			new BankGUI();
		else if(e.getSource() == electBtn)
			new Electric();
		else if(e.getSource() == waterBtn)
			new Water();
		else if(e.getSource() == selfBtn)
			new SeverGUI();
		/*-----------单击窗口右上角的“X”按钮，即关闭窗口退出------------*/
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	public void paint(Graphics g){
		g.drawImage(myImage, 0, 0, this);
	}
}

public class Main{
	public static void main(String[] args){
		new InterfaceGUI();
	}
}