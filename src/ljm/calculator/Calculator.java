package ljm.calculator;

import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame {
    private TextArea show;  //  显示

    public Calculator(String t){
        super(t);
        setLayout(null);
        setSize(430,488);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setBackground(new Color(237, 239, 238));

        setShow();
        addBut();
        setVisible(true);
    }

    private void setShow() {
        show = new TextArea("",8,52,1);
        show.setSize(415,102);
        show.setLocation(0,0);
        show.setFont(new Font("", Font.BOLD, 40));
        show.setBackground(Color.WHITE);
        show.setForeground(new Color(48, 48, 48));
        show.setEditable(false);
        show.setText(0 + "");
        this.add(show);
    }

    private double nub1 = 0;    //  数1
    private double nub2 = 0;    //  数2
    private String snub2 = "";  //  数2的字符串型
    private int f = '+';        //  运算符号
    private boolean isDoc = false;  //  是否有小数点
    private boolean isEqual = false;    //  是否点击等于
    private int nubLen = 0; //  数的长度

    private void setBut(String s, int i, int x, int y, final int c) {
        Button b = new Button(s);
        b.setLocation(x, y);
        b.setSize(i, 70);
        b.setFont(new Font("", Font.BOLD, 35));
        b.setBackground(new Color(254, 254, 254));
        b.setForeground(new Color(88, 92, 100));

        //  计算逻辑算法
        //  刘家敏 ljm@lpqo.cn
        //  2020.6.23 江西科技学院
        b.addActionListener(e -> {
            if (c < 10) {
                //	数字0-9
                if (isEqual) {
                    nub1 = 0;
                }
                if (nubLen > 20) {
                    return;
                }
                if (c == 0 && nub2 == 0 ) {
                    return;
                }
                snub2 += String.valueOf(c);
                show.setText(snub2);
                nubLen = show.getText().length();
                nub2 = Double.parseDouble(snub2);

            } else if (c == '.') {
                //	小数
                if (nubLen > 20) {
                    return;
                }
                if (!isDoc) {
                    if (nub2 != 0) {
                        snub2 += ".";
                    } else {
                        snub2 = "0.";
                    }
                    nub2 = Double.parseDouble(snub2);
                    show.setText(snub2);
                    isDoc = true;
                }

            } else if (c == 'S') {
                // 结果相反数
                if (Double.parseDouble(show.getText()) == nub1) {
                    nub1 = 0 - nub1;
                    showf(nub1 +"");
                } else {
                    nub2 = 0 -nub2;
                    showf(nub2 +"");
                }
            } else if (c == 'C') {
                // 归零
                nub1 = 0;
                nub2 = 0;
                snub2 = "";
                f = '+';
                isDoc = false;
                isEqual = false;
                nubLen = 0;
                showf("0");
            } else {
                isEqual = false;
                nubLen = 0;

                switch (f) {
                    case '/':
                        //	除法
                        nub1 = DoubleUtil.divide(nub1, nub2);
                        break;
                    case '*':
                        // 乘法
                        nub1 = DoubleUtil.mul(nub1, nub2);
                        break;
                    case '-':
                        // 减法
                        nub1 = DoubleUtil.sub(nub1, nub2);
                        break;
                    case '+':
                        //	加法
                        nub1 = DoubleUtil.add(nub1, nub2);
                        break;
                }

                showf(nub1 + "");
                if (c != '=') {
                    f = c;
                } else {
                    // 等于
                    f = '+';
                    isEqual = true;
                }

                nub2 = 0;
                snub2 = "";
                isDoc = false;
            }
        });
        this.add(b);
    }

    //	去0小数
    private void showf(String s){
        double td = Double.parseDouble(s);
        long ti = (long)td;

        if (td == ti) {
            show.setText(ti +"");
        } else {
            show.setText(td + "");
        }
    }

    private void addBut() {
        setBut("AC", 215, 0, 100, 'C');
        setBut("±", 107, 214, 100, 'S');
        setBut("÷", 108, 312, 100, '/');

        setBut("7", 107, 0, 170, 7);
        setBut("8", 108, 107, 170, 8);
        setBut("9", 107, 214, 170, 9);
        setBut("×", 108, 312, 170, '*');

        setBut("4", 107, 0, 240, 4);
        setBut("5", 108, 107, 240, 5);
        setBut("6", 107, 214, 240, 6);
        setBut("-", 108, 312, 240, '-');

        setBut("1", 107, 0, 310, 1);
        setBut("2", 108, 107, 310, 2);
        setBut("3", 107, 214, 310, 3);
        setBut("+", 108, 312, 310, '+');

        setBut("0", 107, 0, 380, 0);
        setBut(".", 108, 107, 380, '.');
        setBut("=", 215, 214, 380, '=');
    }

}
