/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_updateversion;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas-fang
 */
public class ATMBO {

    /**
     * @param args the command line arguments
     */
    
    /*
    *注意静态数据类型只能在类中出现,但是这里是不需要静态数据类型的，因为我们使用了文本文件来储存信息？？？
    */
    private int count = 0;
    //当前账户卡号
    public String code[];                       /*????为什么数据要声明public或者private*/
    //当前账户密码
    public String password[];
    //当前账户余额
    public double money[];
    
    public String line = null;
    public int index = 0;
    
    public ATMBO(){
        try {
            //calculate the number of user
            FileReader filereader = new FileReader("file/users.txt");
            BufferedReader bufferedreader = new BufferedReader(filereader);
            while (bufferedreader.readLine()!=null) {
                count++;
            }
            bufferedreader.close();   //需要先关闭，不然会影响到后面的文件读取
            filereader.close();       //需要先关闭，不然会影响到后面的文件读取????
            code = new String[count];
            password = new String[count];
            money = new double [count];

            filereader = new FileReader("file/users.txt");       //??重新赋值
            bufferedreader = new BufferedReader(filereader);     //??重新赋值
            while ((line = bufferedreader.readLine())!=null) {
                String temp[] = line.split(" ");
                code[index] = temp[0];
                password[index] = temp[1];
                money[index] = Double.valueOf(temp[2]);
                index++;
            }
            bufferedreader.close();
            filereader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /*
    *登陆业务
    *1.用户输入的卡号和密码作为登录业务的两个参数,由前面的ATMUI类调用的时候传入,一起判断
    *2.登录业务处理后,如果登录成功,返回-1,否则返回登录错误次数(作为全局变量存储起来)
    *
    */
    public int cs = 0;
    public int doLogin(String code_input, String password_input) {
        index = java.util.Arrays.asList(code).indexOf(code_input);   //如果数组中不存在，返回index=-1,否则调整index的值，表示用户在数组中的位置
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
    
    public void doQuit(){
        try {
            FileWriter filewriter = new FileWriter("file/users.txt");
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
            for (index = 0; index < count; index++) {                   //性能上的考虑
                bufferedwriter.write(code[index] + " " + password[index] + " "+ money[index]);
                bufferedwriter.newLine();
            }
            bufferedwriter.close();
            filewriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        } 
    }

        
}
