/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_updateversion;

/**
 *
 * @author lucas-fang
 */
public class ATMBO {

    /**
     * @param args the command line arguments
     */
    //当前账户卡号
    public static String code[] = {"001","002","003"};
    //当前账户密码
    public static String password[] = {"001","002","003"};
    //当前账户余额
    public static double money[] = {1000.00,1000.00,1000.00};
    
    public int index = -1;
    /*
    *登陆业务
    *1.用户输入的卡号和密码作为登录业务的两个参数,由前面的ATMUI类调用的时候传入,一起判断
    *2.登录业务处理后,如果登录成功,返回-1,否则返回登录错误次数(作为全局变量存储起来)
    *
	*
    */
    public int cs = 0;
    public int doLogin(String code_input, String password_input) {
        index = java.util.Arrays.asList(code).indexOf(code_input);
        //System.out.println(password_input.equals(password[index]));
        if ((index >= 0) && (password_input.equals(password[index]))) {
            return -1;
        } else {
            cs++;
            return cs;
        }
    }
    /*
    *查询业务
    */
    public double doChaxun() {
        return money[index];
    }
    /*
    *存款业务
    */
    public void doCunkuan(double amount) {
            money[index] = money[index] + amount;
    }
    
    /*
    *取款业务
    */
    public boolean doQukuan(double amount) {                                   
        if(amount > money[index]){
            return false;
        } else {
            money[index] = money[index] - amount;
            return true;
        }
    }
    
    /*
    *改密业务
    */
    public int doXiugai(String oldPass,String newPass, String confirmPass){     //默认参数为空字符串
        if (oldPass.equals(password[index])) {  //string a == string b只能用于判断string指引的是否是同一个地方
            //authentica pass
            if (newPass.equals(confirmPass)) {
                password[index] = newPass;
                return 2;
            } else {
                return 3;
            }
        }else {
            return 1;
        }
    }
    
    public int doTransfer(String transfer_card, double doublevalue, String transfer_passw) {

        int cardindex = java.util.Arrays.asList(code).indexOf(transfer_card);
        if (cardindex < 0) {
            return 1; //卡号不存在
        } else if (cardindex == index) {
            return 2;  //对方卡号与自己相同
        } else if (doublevalue > money[index]) {
            return 3; //转账金额大于余款
        } else if (!transfer_passw.equals(password[index])) {
            return 4; //密码错误
        } else {
            money[index] -= doublevalue;
            money[cardindex] += doublevalue;
            return 5;
        }
    }
}
