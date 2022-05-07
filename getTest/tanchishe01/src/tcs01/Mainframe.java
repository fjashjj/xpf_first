package tcs01;

import jdk.jshell.Snippet;

import javax.print.attribute.standard.DialogOwner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.Timer;

public class Mainframe  extends JFrame
{
    private snake Snake;//蛇
    private Timer timer;//在指定的时间内调用蛇
    private JPanel jPanel;//重绘棋盘
    private Node food;//食物
    public Mainframe () throws HeadlessException
    {
        //初始化窗体参数
        framenit();
        //初始化棋盘
        initGamePanel();
        //初始化蛇
        initsnake();
        //初始化定时器
        initTimer();
        //设置键盘监听，让蛇随着上下左右方向移动
        setKeyListener();
        //初始化食物
        initFood();
    }
    private void initFood()
    {
        food=new Node();
        food.random();
    }
    private void setKeyListener()
    {
        addKeyListener(new KeyAdapter() {
            //当键盘按下时会自动调用此方法
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_UP :
                        if(Snake.getDriection()!=Driection.DOWN)
                        {
                            Snake.setDriection(Driection.UP);
                        }
                        break;
                    case KeyEvent.VK_DOWN :
                        if(Snake.getDriection()!=Driection.UP)
                        {
                            Snake.setDriection(Driection.DOWN);
                        }
                         break;
                    case KeyEvent.VK_LEFT :
                        if(Snake.getDriection()!=Driection.RIGHT)
                        {
                            Snake.setDriection(Driection.LEFT);
                        }
                        break;
                    case KeyEvent.VK_RIGHT :
                        if(Snake.getDriection()!=Driection.LEFT)
                        {
                            Snake.setDriection(Driection.RIGHT);
                        }
                        break;
                }
            }
        });
    }
    //初始化定时器
    private void initTimer()
    {
        //创造定时器对象
        timer= new Timer();
        //初始化定时任务
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run()
            {
                Snake.move();
                //判断蛇头是否和食物重合
                Node head = Snake.getBody().getFirst();
                if(food==null){
                    food=new Node();
                    food.random();
                }
                if(head.getX()==food.getX()&&head.getY()==food.getY())
                {
                    Snake.eat(food);
                    food.random();
                }
                //重新绘制游戏棋盘，更新
                jPanel.repaint();
            }
        };
        //每一百豪s执行一次定时任务
        timer.scheduleAtFixedRate(timerTask,0,500);
    }
    private void initsnake()
    {
        Snake=new snake();
    }
    private void initGamePanel()
    {
        jPanel = new JPanel()
        {
            //绘制棋盘内容
            @Override
            public void paint(Graphics g)
            {
                //清空棋盘
                g.clearRect(0,0,600,600);
                //绘制40条横线
                for (int i = 0; i <= 40; i++)
                {
                    g.drawLine(0,i*15,600,i*15);
                }
                //绘制40条竖线
                for (int i = 0; i <= 40; i++)
                {
                    g.drawLine(i * 15, 0, i * 15, 600);
                }
                //绘制蛇蛇
                LinkedList<Node>body=Snake.getBody();
                for (Node node : body)
                {
                    g.fillRect(node.getX()*15,node.getY()*15,15,15);
                }
                //绘制食物
                g.fillRect(food.getX()*15,food.getY()*15,15,15);
            }
        };
        //把棋盘添加到窗体中
        add(jPanel);
    }
    //初始化窗体参数
    private void framenit()
    {
        //设置窗体大小
        setSize(610,640);
        //设置窗体的位置
        setLocation(400,400);
        //设置关闭按钮的作用（退出程序）
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体大小不可变
        setResizable(false);
    }
    public static void main(String[] args)
    {
        //创建窗体
        new Mainframe().setVisible(true);
    }
}

