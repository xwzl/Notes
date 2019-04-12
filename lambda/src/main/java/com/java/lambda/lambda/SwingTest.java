package com.java.lambda.lambda;

import javax.swing.*;

/**
 * @ClassName SwingTest
 * @Author XuWeiZhi
 * @Description
 * @Date 2018-11-18 23:07 星期日 Java8
 * @VERSION 1.0.0
 **/
public class SwingTest {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("My JFrame");
        JButton jButton = new JButton("My JButton");
        jButton.addActionListener(e -> {
            System.out.println("xxxx");
        });

        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
