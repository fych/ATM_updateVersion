/*
 * To change this license header, choose License Headers in Project Properties.  
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_updateversion;                                                     
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author lucas-fang
 */
public class ATMUI {
    /*
    *main function
    */
    public static void main(String [] args) {
        ATMUI ui  = new ATMUI();
        ui.initBO();
        ui.initFrame();
        ui.showLogin();
    }
    /*
    *初始化业务对象
    */
    //业务对象
    public ATMBO bo = null;
    private JTextField loginCodeInput;
    private double money;
    public void initBO() {
        bo = new ATMBO();
    }
    
    /*
    *初始化主窗口
    */
    //界面宽与高
    public int width = 960;
    public int height = 720;
    //界面窗口
    public JFrame jFrame = null;
    //层叠容器
    public JLayeredPane layeredPane = null;
    //背景层
    public JPanel backLayer = null;
    //前景层
    public JPanel frontLayer = null;
    //前景布局器
    public CardLayout cardLayout = null;

    public void initFrame() {
        //--初始化窗口与层叠容器--
        //创建窗口对象,窗口标题是"ATM触摸屏系统"
        jFrame = new JFrame("ATM触摸屏系统");
        //创建层叠容器对象
        layeredPane = new JLayeredPane();
        //设置层叠容器对象大小
        layeredPane.setPreferredSize(new Dimension(width,height));
		//把层叠容器对象添加到窗口中
        jFrame.add(layeredPane);
		//设置窗口对象不能放大缩小
        jFrame.setResizable(false);
		//设置窗口对象大小适应内容(层叠容器)大小
        jFrame.pack();
		//设置窗口可见(默认不可见)
        jFrame.setVisible(true);
		//窗口关闭时,程序关闭
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//--在层叠容器中增加背景层--
		//创建背景层对象
        backLayer = new JPanel();
		//设置背景层布局器FlowLayout的水平间距为0(默认为5)
        ((FlowLayout)backLayer.getLayout()).setHgap(0);
		//设置背景层布局器FlowLayout的垂直间距为0(默认为5)
        ((FlowLayout)backLayer.getLayout()).setHgap(0);
		//设置背景层大小(同一窗口)
        backLayer.setSize(width,height);
		//设置背景层位置(相对于窗口左上角)
        backLayer.setLocation(0,0);
        //创建背景层图对象
        JLabel bg = new JLabel(new ImageIcon("img/bg.jpg"));
        //在背景层添加背景图
        backLayer.add(bg);
		//将背景层添加到层叠容器的较底层
        layeredPane.add(backLayer,new Integer(0));
        
		//--在层叠容器中增加前景层--
		//创建前景层
        frontLayer = new JPanel();
		//创建CardLayout布局器对象,水平\垂直间距为0
        cardLayout = new CardLayout(0,0);
		//将前景层布局器设置为CardLayout
        frontLayer.setLayout(cardLayout);
		//将前景层的背景色设成透明(这样才能看到背景层)
        frontLayer.setOpaque(false);
		//设置前景层大小(同一窗口)
        frontLayer.setSize(width,height);
		//设置前景层位置(相对于窗口左上角)
        frontLayer.setLocation(0,0);
        //将前景层添加到层叠容器较顶层
        layeredPane.add(frontLayer,new Integer(1));
        
    }
    
    /*
    *登陆界面
    *
	*本案例中,前景界面层使用了CardLayout,是希望做到调用对应的
	*方法时,把对应的层面调到最顶层即可;另外,每个层面第一次调用
    *时,则初始化,后面的调用就可以直接把已初始化的层面调出并把一
    *些必须的组件重置即可
    */
	//登录层容器
    public JPanel loginPane = null;
	//登录卡号输入框
    public JTextField loinCodePassInput = null;
	//登录密码输入框
    public JPasswordField loginPassInput = null;
	//登录信息提示
    public JLabel loginTipsLabel = null;
    
    public void showLogin() {
        if(loginPane == null) {
			//--登录层容器未初始化时--
			//创建登录层容器对象
            loginPane = new JPanel();
			//登录层背景色设置为透明
            loginPane.setOpaque(false);
			
			//--往登录层容器中添加组件--

			//创建一个垂直Box容器
            Box loginBox = Box.createVerticalBox();

			//在垂直Box中添加180高度的距离
            loginBox.add(Box.createVerticalStrut(180));
            
			//创建一个欢迎信息容器
            JPanel welcome_panel = new JPanel();
			//将欢迎信息容器的背景色设置为透明
            welcome_panel.setOpaque(false);
			//创建欢迎信息
            JLabel welcome_label = new JLabel("海阁银行");
			//设置信息字体
            welcome_label.setForeground(Color.WHITE);
            welcome_label.setFont(new Font("微软雅黑",Font.PLAIN,30));

			//把欢迎信息添加到欢迎信息容器中
            welcome_panel.add(welcome_label);
			//把欢迎信息容器添加到垂直Box容器中
            loginBox.add(welcome_panel);
			
			//在垂直Box中添加30高度的距离
            loginBox.add(Box.createVerticalStrut(30));
            
    		//创建一个账号输入容器
            JPanel code_panel = new JPanel();
			//把卡号输入容器的背景色设置成透明的
            code_panel.setOpaque(false);
			//创建提示输入卡号信息"请输入卡号"
            JLabel code_label = new JLabel("请输入卡号");
			//设置信息字体
            code_label.setForeground(Color.WHITE);
            code_label.setFont(new Font("微软雅黑",Font.PLAIN,25));
			//将提示卡号输入信息添加到卡号输入容器里面中
            code_panel.add(code_label);
			//创建卡号输入框
            loginCodeInput = new JTextField(10);
			//设置卡号输入框字体
            loginCodeInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
			//将卡号输入框添加到卡号输入容器里面
            code_panel.add(loginCodeInput);
			//将卡号输入容器添加到垂直Box容器中
            loginBox.add(code_panel);

			//在垂直容器中添加20高度的距离
            loginBox.add(Box.createVerticalStrut(20));
            
            //类似于卡号输入块，创建密码输入容器
            JPanel pass_panel = new JPanel();
            pass_panel.setOpaque(false);
            JLabel pass_label = new JLabel("请输入密码");
            pass_label.setForeground(Color.WHITE);
            pass_label.setFont(new Font("微软雅黑",Font.PLAIN,25));
            pass_panel.add(pass_label);
            loginPassInput = new JPasswordField(10);
            loginPassInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
            pass_panel.add(loginPassInput);
            loginBox.add(pass_panel);

            loginBox.add(Box.createVerticalStrut(30));
            
            //创建按钮容器
            JPanel btn_panel = new JPanel();
			//把按钮容器的背景色设置为透明
            btn_panel.setOpaque(false);
			//创建登录按钮并设置字体
            JButton login_btn = new JButton("登陆");
            login_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
			//将登录按钮添加到按钮容器中
            btn_panel.add(login_btn);
			//创建重置按钮并设置字体
            JButton reset_btn = new JButton("重置");
            reset_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
			//将重置按钮添加到按钮容器中
            btn_panel.add(reset_btn);
			//将按钮容器添加到垂直Box容器中
            loginBox.add(btn_panel);

			//在垂直Box中添加10高度的距离
            loginBox.add(Box.createVerticalStrut(10));
            
			//创建登录信息提示容器
            JPanel tips_panel = new JPanel();
            tips_panel.setOpaque(false);
            loginTipsLabel = new JLabel("");
            loginTipsLabel.setForeground(new Color(238,32,32));
            loginTipsLabel.setFont(new Font("微软雅黑",Font.PLAIN,25));
            tips_panel.add(loginTipsLabel);
            loginBox.add(tips_panel);

            //把垂直Box容器添加到登录层容器中
            loginPane.add(loginBox);

			//将登录层容器添加到前景层容器中
            frontLayer.add("loginPane",loginPane);
			//通过布局器,使登录层在前景层中置于顶层显示
            cardLayout.show(frontLayer,"loginPane");
			//刷新前景层使其可视化
            frontLayer.validate();
			//使卡号输入框获取焦点(方便直接输入)
            loginCodeInput.requestFocus();
            
			//--监听重置按钮事件--
            reset_btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    loginCodeInput.setText("");
                    loginPassInput.setText("");
                    loginTipsLabel.setText("");
                    loginCodeInput.requestFocus();
                }
            });
            
            //--监听登录按钮事件--
            login_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    String code_str = loginCodeInput.getText();
                    String pass_str = new String(loginPassInput.getPassword());
                    if ("".equals(code_str)) {
                        loginTipsLabel.setText("卡号不能为空");
                        loginCodeInput.requestFocus();
                    } else if ("".equals(pass_str)) {
                        loginTipsLabel.setText("密码不能为空");
                        loginPassInput.requestFocus();
                    } else {
                        int login_rtn = bo.doLogin(code_str, pass_str);
                        if (login_rtn ==-1) {
                            showMenu();
                        } else if(login_rtn == 3) {
                            showTunka();
                        } else {
                            loginCodeInput.setText("");
                            loginPassInput.setText("");
                            loginTipsLabel.setText("卡号或密码错误，请重新输入，你还有"+(3-login_rtn)+"次机会");
                            loginCodeInput.requestFocus();
                        }
                    }
                }
            });
            
            
        } else {
            cardLayout.show(frontLayer,"loginPane");
            loginCodeInput.setText("");
            loginPassInput.setText("");
            loginTipsLabel.setText("");
            loginCodeInput.requestFocus();
        }
    }
    /*
    *吞卡提示界面
    */
    public JPanel tunkaPane = null;
    public void showTunka() {
        if(tunkaPane == null) {
            tunkaPane = new JPanel();
            tunkaPane.setOpaque(false);
            Box tunkaBox = Box.createVerticalBox();
            tunkaBox.add(Box.createVerticalStrut(180));
            
            JPanel tunka_panel = new JPanel();
            tunka_panel.setOpaque(false);
            JLabel tunka_label = new JLabel("你已经超过三次输入密码，系统吞卡");
            tunka_label.setForeground(Color.WHITE);
            tunka_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            tunka_panel.add(tunka_label);
            tunkaBox.add(tunka_panel);
            
            tunkaBox.add(Box.createVerticalStrut(30));
            
            JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
            JButton tunka_btn = new JButton("确定");
            tunka_btn.setFont(new Font("微软雅黑",Font.PLAIN,30));
            btn_panel.add(tunka_btn);
            tunkaBox.add(btn_panel);
            
            tunkaPane.add(tunkaBox);
            
            frontLayer.add("tunkapane",tunkaPane);
            cardLayout.show(frontLayer, "tunkapane");
            frontLayer.validate();
            tunka_btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    quit();
                }
            });
        } else {
            cardLayout.show(frontLayer, "tunkaPane");
        }
    }
    /*
    *主菜单界面
    */
    public JPanel menuPane = null;
    public void showMenu() {
        if (menuPane == null) {
            menuPane = new JPanel();
            menuPane.setOpaque(false);
            menuPane.setLayout(new BorderLayout());

            Box tipsBox = Box.createVerticalBox();
            menuPane.add(tipsBox,BorderLayout.NORTH);
            
            tipsBox.add(Box.createVerticalStrut(150));
            JLabel tips_label = new JLabel("请选择你需要的服务");
            tips_label.setForeground(Color.WHITE);
            tips_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            tips_label.setAlignmentX(Component.CENTER_ALIGNMENT);
            tipsBox.add(tips_label);
            
            //左栏按钮块，位于BorderLayout西区
            Box menuLeft = Box.createVerticalBox();
            menuPane.add(menuLeft,BorderLayout.WEST);
            
            menuLeft.add(Box.createVerticalStrut(50));
            JButton chaxun_btn = new JButton("查询余额");
            chaxun_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
            menuLeft.add(chaxun_btn);
            
            menuLeft.add(Box.createVerticalStrut(100));
            JButton cunkuan_btn = new JButton("存款");
            cunkuan_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
            menuLeft.add(cunkuan_btn);
            
            menuLeft.add(Box.createVerticalStrut(100));
            JButton qukuan_btn = new JButton("取款");
            qukuan_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
            menuLeft.add(qukuan_btn);
            
            //右栏按钮块，位于BorderLayout东区
            Box menuRight = Box.createVerticalBox();
            menuPane.add(menuRight,BorderLayout.EAST);
            
            menuRight.add(Box.createVerticalStrut(50));
            JButton transfer_btn = new JButton("转账");
            transfer_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
            transfer_btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
            menuRight.add(transfer_btn);
            
            menuRight.add(Box.createVerticalStrut(100));
            JButton xiugai_btn = new JButton("修改密码");
            xiugai_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
            xiugai_btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
            menuRight.add(xiugai_btn);
            
            menuRight.add(Box.createVerticalStrut(100));
            JButton quit_btn = new JButton("退卡");
            quit_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
            quit_btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
            menuRight.add(quit_btn);
            //显示主菜单界面
            frontLayer.add("menuPane",menuPane);
            cardLayout.show(frontLayer,"menuPane");
            frontLayer.validate();
            
            //监听给按钮事件
            chaxun_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showChaxun();
                }
            });
            cunkuan_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showCunkuan();
                }
            });
            qukuan_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showQukuan();
                }
            });
            transfer_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showTransfer();
                }
            });
            xiugai_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showXiugai();
                }
            });
            quit_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    quit();
                }
            });                                                          
        } else {
            cardLayout.show(frontLayer,"menuPane");
        }
    }
    /*
    *查询界面
    */
    public JPanel chaxunPane = null;
    public JLabel chaxun_label = null;
    public void showChaxun() {
        if (chaxunPane == null){
            chaxunPane = new JPanel();
            chaxunPane.setOpaque(false);
            
            Box chaxunBox = Box.createVerticalBox();
            chaxunBox.add(Box.createVerticalStrut(180));
            JPanel chaxun_panel = new JPanel();
            chaxun_panel.setOpaque(false);
            money = bo.doChaxun();  
            chaxun_label = new JLabel("您的余额是" + String.valueOf(money));
            chaxun_label.setForeground(Color.WHITE);
            chaxun_label.setFont(new Font("微软雅黑",Font.PLAIN,25));
            
            chaxun_panel.add(chaxun_label);
            chaxunBox.add(chaxun_panel);
            chaxunBox.add(Box.createVerticalStrut(30));
            
            JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
            JButton return_btn = new JButton("返回");
            return_btn.setFont(new Font("微软雅黑",Font.PLAIN,30));
            btn_panel.add(return_btn);
            chaxunBox.add(btn_panel);

            chaxunPane.add(chaxunBox);
            
            //显示余额界面
            frontLayer.add("chaxunPane",chaxunPane);
            cardLayout.show(frontLayer, "chaxunPane");
            frontLayer.validate();
            
            return_btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showMenu();
                }
            });
            
        } else {
            cardLayout.show(frontLayer,"chaxunPane");
            money = bo.doChaxun();  
            chaxun_label.setText("您的余额是" + money);
        }
    }
    /*
    *存款界面
    */
    public JPanel cunkuanPane = null;
    public JTextField cunkuan_input = null;
    public JLabel cunkuan_label = null;
    public JLabel cunkuan_tips = null;
    public void showCunkuan() {
        if (cunkuanPane == null) {
  	    cunkuanPane = new JPanel();
            cunkuanPane.setOpaque(false);

            Box cunkuanBox = Box.createVerticalBox();

            cunkuanBox.add(Box.createVerticalStrut(180));

            JPanel cunkuan_pane = new JPanel();
            cunkuan_pane.setOpaque(false);
            cunkuan_label = new JLabel("请输入存款金额: ");
	    cunkuan_label.setForeground(Color.WHITE);
            cunkuan_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            
            cunkuan_pane.add(cunkuan_label);

            cunkuan_input = new JTextField(10); 
            cunkuan_input.setFont(new Font("微软雅黑",Font.PLAIN,25));
            cunkuan_pane.add(cunkuan_input);
            
	    cunkuanBox.add(cunkuan_pane);            

	    cunkuanBox.add(Box.createVerticalStrut(30));            

	    JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
	    JButton btn_confirm = new JButton("确定");
            btn_confirm.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_confirm);
	    JButton btn_return = new JButton("返回");
            btn_return.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_return);
            cunkuanBox.add(btn_panel);

            cunkuanBox.add(Box.createVerticalStrut(25));

            JPanel cunkuanTips = new JPanel();
	    cunkuanTips.setOpaque(false);
            cunkuan_tips = new JLabel("");
            cunkuan_tips.setForeground(new Color(238,32,32));
   	    cunkuan_tips.setFont(new Font("微软雅黑",Font.PLAIN,30));
            cunkuanTips.add(cunkuan_tips);
            cunkuanBox.add(cunkuanTips);         
 
            cunkuanPane.add(cunkuanBox);

            frontLayer.add("cunkuanPane",cunkuanPane);
            cardLayout.show(frontLayer,"cunkuanPane");
            frontLayer.validate();
            cunkuan_input.requestFocus();
            
            btn_return.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showMenu();
                }
            });
            btn_confirm.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                double doublevalue;
                try{
                    doublevalue = Double.valueOf(cunkuan_input.getText());
                } catch(NumberFormatException e) {
                    //e.printStackTrace();
                    doublevalue = 0;
                }
                if (doublevalue % 100 != 0 || doublevalue == 0) {
                    //System.out.println("charge failed! The charge amount must be hundreds");
                    cunkuan_tips.setText("请输入整百金额");
                } else {
                        bo.doCunkuan(doublevalue);
                        showCunkuanSuccess();
                    }
                }
            });
	} else {
            cardLayout.show(frontLayer,"cunkuanPane");
            cunkuan_input.setText("");
            cunkuan_tips.setText("");
            cunkuan_input.requestFocus();
	} 

    }
    /*
    *存款成功提示界面
    */
    public JPanel cunkuanSuccessPane = null;
    public void showCunkuanSuccess() {
        if (cunkuanSuccessPane == null) {
  	    cunkuanSuccessPane = new JPanel();
            cunkuanSuccessPane.setOpaque(false);

            Box cunkuanSuccessBox = Box.createVerticalBox();

            cunkuanSuccessBox.add(Box.createVerticalStrut(180));

            JPanel cunkuanSuccess_pane = new JPanel();
            cunkuanSuccess_pane.setOpaque(false);
            JLabel cunkuanSuccess_label = new JLabel("存款成功！");
	    cunkuanSuccess_label.setForeground(Color.WHITE);
            cunkuanSuccess_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            
            cunkuanSuccess_pane.add(cunkuanSuccess_label);
            
	    cunkuanSuccessBox.add(cunkuanSuccess_pane);            

	    cunkuanSuccessBox.add(Box.createVerticalStrut(30));            

	    JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
            JButton btn_return = new JButton("返回");
            btn_return.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_return);
            cunkuanSuccessBox.add(btn_panel);
 
            cunkuanSuccessPane.add(cunkuanSuccessBox);

            frontLayer.add("cunkuanSuccessPane",cunkuanSuccessPane);
            cardLayout.show(frontLayer,"cunkuanSuccessPane");
            frontLayer.validate();
            
            btn_return.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showMenu();
                }
            });
	} else {
            cardLayout.show(frontLayer,"cunkuanSuccessPane");
	} 
    }
    /*
    *取款界面
    */
    public JPanel qukuanPane = null;
    public JLabel qukuan_label = null;
    public JTextField qukuan_input = null;
    public JLabel qukuan_tips = null;
    public void showQukuan() {
        if (qukuanPane == null) {
            qukuanPane = new JPanel();
            qukuanPane.setOpaque(false);

            Box qukuanBox = Box.createVerticalBox();

            qukuanBox.add(Box.createVerticalStrut(180));

            JPanel qukuan_pane = new JPanel();
            qukuan_pane.setOpaque(false);
            qukuan_label = new JLabel("请输入取款金额: ");
            qukuan_label.setForeground(Color.WHITE);
            qukuan_label.setFont(new Font("微软雅黑",Font.PLAIN,30));

            qukuan_pane.add(qukuan_label);

            qukuan_input = new JTextField(10); 
            qukuan_input.setFont(new Font("微软雅黑",Font.PLAIN,25));
            qukuan_pane.add(qukuan_input);

            qukuanBox.add(qukuan_pane);            

            qukuanBox.add(Box.createVerticalStrut(30));            

            JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
            JButton btn_confirm = new JButton("确定");
            btn_confirm.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_confirm);
            JButton btn_return = new JButton("返回");
            btn_return.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_return);
            qukuanBox.add(btn_panel);

            qukuanBox.add(Box.createVerticalStrut(25));

            JPanel qukuanTips = new JPanel();
            qukuanTips.setOpaque(false);
            qukuan_tips = new JLabel("");
            qukuan_tips.setForeground(new Color(238,32,32));
            qukuan_tips.setFont(new Font("微软雅黑",Font.PLAIN,30));
            qukuanTips.add(qukuan_tips);
            qukuanBox.add(qukuanTips);         

            qukuanPane.add(qukuanBox);

            frontLayer.add("qukuanPane",qukuanPane);
            cardLayout.show(frontLayer,"qukuanPane");
            frontLayer.validate();
            qukuan_input.requestFocus();

            btn_return.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showMenu();
                }
            });
            btn_confirm.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    double doublevalue;
                    try{
                        doublevalue = Double.valueOf(qukuan_input.getText());
                    } catch(NumberFormatException e) {
                        //e.printStackTrace();
                        doublevalue = 0;
                    }
                    if (doublevalue == 0 ||doublevalue % 100 != 0) {
                        qukuan_tips.setText("请输入整百金额");
                    } else {
                        boolean isOk = bo.doQukuan(doublevalue);
                        if (isOk) {
                            showQukuanSuccess();
                        } else {
                            qukuan_tips.setText("您的余额不足");
                        }
                    }
                }
            });
        } else {
            cardLayout.show(frontLayer,"qukuanPane");
            qukuan_input.setText("");
            qukuan_tips.setText("");
            qukuan_input.requestFocus();
        } 
    }
    /*
    *取款成功提示界面
    */
    JPanel qukuanSuccessPane = null;
    public void showQukuanSuccess() {
        if (qukuanSuccessPane == null) {
  	    qukuanSuccessPane = new JPanel();
            qukuanSuccessPane.setOpaque(false);

            Box qukuanSuccessBox = Box.createVerticalBox();

            qukuanSuccessBox.add(Box.createVerticalStrut(180));

            JPanel qukuanSuccess_pane = new JPanel();
            qukuanSuccess_pane.setOpaque(false);
            JLabel qukuanSuccess_label = new JLabel("取款成功！");
	    qukuanSuccess_label.setForeground(Color.WHITE);
            qukuanSuccess_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            
            qukuanSuccess_pane.add(qukuanSuccess_label);
            
	    qukuanSuccessBox.add(qukuanSuccess_pane);            

	    qukuanSuccessBox.add(Box.createVerticalStrut(30));            

	    JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
            JButton btn_return = new JButton("返回");
            btn_return.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_return);
            qukuanSuccessBox.add(btn_panel);
 
            qukuanSuccessPane.add(qukuanSuccessBox);

            frontLayer.add("qukuanSuccessPane",qukuanSuccessPane);
            cardLayout.show(frontLayer,"qukuanSuccessPane");
            frontLayer.validate();
            
            btn_return.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showMenu();
                }
            });
	} else {
            cardLayout.show(frontLayer,"qukuanSuccessPane");
	} 
    }
    
    /*
    *转账界面
    */
    public JPanel transferPane = null;
    public JTextField transfer_to_card_input = null;
    public JTextField transfer_amount_input = null;
    public JPasswordField password_input = null;
    public JLabel transfer_to_card_label = null;
    public JLabel transfer_amount_label = null;
    public JLabel password_label = null;
    public JLabel transfer_tips = null;
    
    public void showTransfer() {
        if (transferPane == null) {
  	    transferPane = new JPanel();
            transferPane.setOpaque(false);

            Box transfer_Box = Box.createVerticalBox();
            transfer_Box.add(Box.createVerticalStrut(180));
            JPanel transfer_to_card_pane = new JPanel();
            transfer_to_card_pane.setOpaque(false);
            transfer_to_card_label = new JLabel("请输入对方卡号: ");
	    transfer_to_card_label.setForeground(Color.WHITE);
            transfer_to_card_label.setFont(new Font("微软雅黑",Font.PLAIN,30));          
            transfer_to_card_pane.add(transfer_to_card_label);
            transfer_to_card_input = new JTextField(10); 
            transfer_to_card_input.setFont(new Font("微软雅黑",Font.PLAIN,25));
            transfer_to_card_pane.add(transfer_to_card_input);
	    transfer_Box.add(transfer_to_card_pane);
            
	    transfer_Box.add(Box.createVerticalStrut(30));      
            JPanel transfer_amount_pane = new JPanel();
            transfer_amount_pane.setOpaque(false);
            transfer_amount_label = new JLabel("请输入转账金额: ");
	    transfer_amount_label.setForeground(Color.WHITE);
            transfer_amount_label.setFont(new Font("微软雅黑",Font.PLAIN,30));          
            transfer_amount_pane.add(transfer_amount_label);
            transfer_amount_input = new JTextField(10); 
            transfer_amount_input.setFont(new Font("微软雅黑",Font.PLAIN,25));
            transfer_amount_pane.add(transfer_amount_input);
	    transfer_Box.add(transfer_amount_pane);
            
            transfer_Box.add(Box.createVerticalStrut(30));
            JPanel password_pane = new JPanel();
            password_pane.setOpaque(false);
            password_label = new JLabel("请输入你的密码: ");
	    password_label.setForeground(Color.WHITE);
            password_label.setFont(new Font("微软雅黑",Font.PLAIN,30));          
            password_pane.add(password_label);
            password_input = new JPasswordField(10); 
            password_input.setFont(new Font("微软雅黑",Font.PLAIN,25));
            password_pane.add(password_input);
	    transfer_Box.add(password_pane);
            
            transfer_Box.add(Box.createVerticalStrut(30));
	    JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
	    JButton btn_confirm = new JButton("确定");
            btn_confirm.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_confirm);
	    JButton btn_return = new JButton("返回");
            btn_return.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_return);
            transfer_Box.add(btn_panel);
            transfer_Box.add(Box.createVerticalStrut(25));

            JPanel transferTips = new JPanel();
	    transferTips.setOpaque(false);
            transfer_tips = new JLabel("");
            transfer_tips.setForeground(new Color(238,32,32));
   	    transfer_tips.setFont(new Font("微软雅黑",Font.PLAIN,30));
            transferTips.add(transfer_tips);
            transfer_Box.add(transferTips);         
 
            transferPane.add(transfer_Box);

            frontLayer.add("transferPane",transferPane);
            cardLayout.show(frontLayer,"transferPane");
            frontLayer.validate();
            transfer_to_card_input.requestFocus();
            
            btn_return.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showMenu();
                }
            });
            btn_confirm.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    double doublevalue;
                    try{
                        doublevalue = Double.valueOf(transfer_amount_input.getText());
                    } catch(NumberFormatException e) {
                        //e.printStackTrace();
                        doublevalue = 0;
                    }
                    String passw = new String(password_input.getPassword());    //注意getPassword()得到的不是String
                    if (transfer_to_card_input.getText().equals("")) {
                        transfer_tips.setText("请输入对方卡号");
                        transfer_to_card_input.requestFocus();
                    } else if (doublevalue < 1) {
                        transfer_amount_input.setText("");
                        transfer_tips.setText("转账金额不得小于一块钱");
                        transfer_amount_input.requestFocus();   
                    } else if(passw.equals("")){
                        transfer_tips.setText("请确认密码");
                        password_input.requestFocus(); 
                        System.out.println(1);
                    } else {
                        int sign = bo.doTransfer(transfer_to_card_input.getText(),doublevalue,passw);
                        switch (sign) {
                            //对方卡号不存在
                            case 1:
                                transfer_to_card_input.setText("");
                                transfer_tips.setText("对方卡号不存在");
                                transfer_to_card_input.requestFocus();
                                break;
                           //对方卡号与自己相同
                            case 2:
                                transfer_to_card_input.setText("");
                                transfer_tips.setText("对方卡号不能与自己相同");
                                transfer_to_card_input.requestFocus();
                                break;
                            //转账金额大于余款  
                            case 3:
                                transfer_amount_input.setText("");
                                transfer_tips.setText("您的余额不足，请重新输入金额");
                                transfer_amount_input.requestFocus();     
                                break;
                            //密码错误
                            case 4:
                                password_input.setText("");
                                transfer_tips.setText("密码错误");
                                password_input.requestFocus();     
                                break;
                            //转账成功
                            case 5:
                                showTransferSuccess();
                                break;
                        }
                    }

                }
            });
	} else {
            cardLayout.show(frontLayer,"transferPane");
            transfer_to_card_input.setText("");
            transfer_amount_input.setText("");
            password_input.setText("");
            transfer_tips.setText("");
            transfer_to_card_input.requestFocus();
	} 

    }
    /*
    *转账成功提示界面
    */
    public JPanel TransferSuccessPane = null;
    public void showTransferSuccess() {
        if (TransferSuccessPane == null) {
  	    TransferSuccessPane = new JPanel();
            TransferSuccessPane.setOpaque(false);

            Box TransferSuccessBox = Box.createVerticalBox();

            TransferSuccessBox.add(Box.createVerticalStrut(180));

            JPanel TransferSuccess_pane = new JPanel();
            TransferSuccess_pane.setOpaque(false);
            JLabel TransferSuccess_label = new JLabel("转账成功！");
	    TransferSuccess_label.setForeground(Color.WHITE);
            TransferSuccess_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            
            TransferSuccess_pane.add(TransferSuccess_label);
            
	    TransferSuccessBox.add(TransferSuccess_pane);            

	    TransferSuccessBox.add(Box.createVerticalStrut(30));            

	    JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
            JButton btn_return = new JButton("返回");
            btn_return.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_return);
            TransferSuccessBox.add(btn_panel);
 
            TransferSuccessPane.add(TransferSuccessBox);

            frontLayer.add("TransferSuccessPane",TransferSuccessPane);
            cardLayout.show(frontLayer,"TransferSuccessPane");
            frontLayer.validate();
            
            btn_return.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showMenu();
                }
            });
	} else {
            cardLayout.show(frontLayer,"TransferSuccessPane");
	} 
    }
    
    
    /*
    *密码修改界面
    */
    public JPanel xiugaiPane = null;
    //密码
    public JPasswordField old_loginPassInput = null;
    public JPasswordField new_loginPassInput = null;
    public JPasswordField new_confirm_loginPassInput = null;
    //信息提示
    public JLabel xiugaiTipsLabel = null;
    public void showXiugai() {
        if (xiugaiPane == null) {
  	    xiugaiPane = new JPanel();
            xiugaiPane.setOpaque(false);

            Box xiugaiBox = Box.createVerticalBox();

            xiugaiBox.add(Box.createVerticalStrut(180));

            JPanel oldPass_pane = new JPanel();
            JPanel newPass_pane = new JPanel();
            JPanel confirmPass_pane = new JPanel();
            
            oldPass_pane.setOpaque(false);
            JLabel oldPass_label = new JLabel("请输入旧密码: ");
	    oldPass_label.setForeground(Color.WHITE);
            oldPass_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            oldPass_pane.add(oldPass_label);
            old_loginPassInput = new JPasswordField(10); 
            old_loginPassInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
            oldPass_pane.add(old_loginPassInput);
            
	    xiugaiBox.add(oldPass_pane);      
            
	    xiugaiBox.add(Box.createVerticalStrut(30));
            
            newPass_pane.setOpaque(false);
            JLabel newPass_label = new JLabel("请输入新密码: ");
	    newPass_label.setForeground(Color.WHITE);
            newPass_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            newPass_pane.add(newPass_label);
            new_loginPassInput = new JPasswordField(10); 
            new_loginPassInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
            newPass_pane.add(new_loginPassInput);
            
	    xiugaiBox.add(newPass_pane);   
            
	    xiugaiBox.add(Box.createVerticalStrut(30));
            
            confirmPass_pane.setOpaque(false);
            JLabel confirmPass_label = new JLabel("请确认新密码: ");
	    confirmPass_label.setForeground(Color.WHITE);
            confirmPass_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            confirmPass_pane.add(confirmPass_label);
            new_confirm_loginPassInput = new JPasswordField(10); 
            new_confirm_loginPassInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
            confirmPass_pane.add(new_confirm_loginPassInput);
            
	    xiugaiBox.add(confirmPass_pane);
            
	    xiugaiBox.add(Box.createVerticalStrut(30));
            
	    JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
	    JButton btn_confirm = new JButton("确定");
            btn_confirm.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_confirm);
	    JButton btn_return = new JButton("返回");
            btn_return.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_return);
            
            xiugaiBox.add(btn_panel);

            xiugaiBox.add(Box.createVerticalStrut(25));

            JPanel xiugaiTips = new JPanel();
	    xiugaiTips.setOpaque(false);
            xiugaiTipsLabel = new JLabel("");
            xiugaiTipsLabel.setForeground(new Color(238,32,32));
   	    xiugaiTipsLabel.setFont(new Font("微软雅黑",Font.PLAIN,30));
            xiugaiTips.add(xiugaiTipsLabel);
            
            xiugaiBox.add(xiugaiTips);         
 
            xiugaiPane.add(xiugaiBox);

            frontLayer.add("xiugaiPane",xiugaiPane);
            cardLayout.show(frontLayer,"xiugaiPane");
            frontLayer.validate();
            old_loginPassInput.requestFocus();
            
            btn_return.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showMenu();
                }
            });
            btn_confirm.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    String oldpass = new String(old_loginPassInput.getPassword()); //如果没有输入，此处其实得到的是空字符串
                    String newpass = new String(new_loginPassInput.getPassword());
                    String new_confirm_pass = new String(new_confirm_loginPassInput.getPassword());                    
                    if (oldpass.equals("")) {
                        xiugaiTipsLabel.setText("请输入原来密码");
                        old_loginPassInput.requestFocus();
                    } else if (newpass.equals("")) {
                        xiugaiTipsLabel.setText("请输入新密码");
                        new_loginPassInput.requestFocus();
                    } else if (new_confirm_pass.equals("")) {
                        xiugaiTipsLabel.setText("请确认新密码");
                        new_confirm_loginPassInput.requestFocus();
                    } else {
                        int case_value = bo.doXiugai(oldpass,newpass,new_confirm_pass);
                        switch (case_value) {
                            case 1:
                                old_loginPassInput.setText("");
                                new_loginPassInput.setText("");
                                new_confirm_loginPassInput.setText("");
                                xiugaiTipsLabel.setText("原密码错误");
                                old_loginPassInput.requestFocus();
                                break;
                            case 2:
                                showXiugaiSuccess();
                                break;
                            case 3:
                                //old_loginPassInput.setText("");
                                new_loginPassInput.setText("");
                                new_confirm_loginPassInput.setText("");
                                xiugaiTipsLabel.setText("新密码输入不一致");
                                new_loginPassInput.requestFocus();
                                break;
                        }
                    }
                }
            });
	} else {
            cardLayout.show(frontLayer,"xiugaiPane");
            old_loginPassInput.setText("");
            new_loginPassInput.setText("");
            new_confirm_loginPassInput.setText("");
            xiugaiTipsLabel.setText("");
            old_loginPassInput.requestFocus();
	} 
    }
    /*
    *密码修改成功提示界面
    */
    public JPanel xiugaiSuccessPane = null;
    public void showXiugaiSuccess() {
        if (xiugaiSuccessPane == null) {
  	    xiugaiSuccessPane = new JPanel();
            xiugaiSuccessPane.setOpaque(false);

            Box xiugaiSuccessBox = Box.createVerticalBox();

            xiugaiSuccessBox.add(Box.createVerticalStrut(180));

            JPanel xiugaiSuccess_pane = new JPanel();
            xiugaiSuccess_pane.setOpaque(false);
            JLabel xiugaiSuccess_label = new JLabel("修改密码成功！");
	    xiugaiSuccess_label.setForeground(Color.WHITE);
            xiugaiSuccess_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
            
            xiugaiSuccess_pane.add(xiugaiSuccess_label);
            
	    xiugaiSuccessBox.add(xiugaiSuccess_pane);            

	    xiugaiSuccessBox.add(Box.createVerticalStrut(30));            

	    JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(false);
            JButton btn_return = new JButton("返回");
            btn_return.setFont(new Font("微软雅黑",Font.PLAIN,15));
            btn_panel.add(btn_return);
            xiugaiSuccessBox.add(btn_panel);
 
            xiugaiSuccessPane.add(xiugaiSuccessBox);

            frontLayer.add("xiugaiSuccessPane",xiugaiSuccessPane);
            cardLayout.show(frontLayer,"xiugaiSuccessPane");
            frontLayer.validate();
            
            btn_return.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    showMenu();
                }
            });
	} else {
            cardLayout.show(frontLayer,"xiugaiSuccessPane");
	} 
    }
    /*
    *退卡
    */
    public void quit() {
        bo.doWriteBack();
        //重新初始化业务对象
        initBO();          //我认为这一行应该注释掉，从多用户的角度去思考就不应注释
        //重新显示登陆界面
        showLogin();
    }
}
