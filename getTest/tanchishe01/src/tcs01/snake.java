package tcs01;

import java.util.LinkedList;

public class snake
{
        //蛇的身体
        private LinkedList<Node> body;
        //蛇的运动方向默认向左
        private Driection driection=Driection.LEFT;
        //构造方法，在创建Snake时使用
        //蛇是否活着
        private Boolean isLiving=true;
        public snake()
        {
            initsnake();
        }
        //初始化蛇身体
        private void initsnake()
        {
            //创建集合
            body=new LinkedList<>();
            //创建7个节点，添加到蛇的身体中去
            body.add(new Node(16,20));
        }
        //蛇会沿着蛇头的方向移动
        //控制蛇的移动，在蛇头位置添加一个节点，蛇尾的位置删除
        public void move()
        {
            if (isLiving)
            {
                //获取蛇头
                Node head = body.getFirst();
                switch (driection)
                {
                    case UP:
                        //在蛇头的上边添加一个节点
                        body.addFirst(new Node(head.getX(), head.getY() - 1));
                        break;
                    case DOWN:
                        body.addFirst(new Node(head.getX(), head.getY() + 1));
                        break;
                    case LEFT:
                        body.addFirst(new Node(head.getX() - 1, head.getY()));
                        break;
                    case RIGHT:
                        body.addFirst(new Node(head.getX() + 1, head.getY()));
                        break;
                }
                //删除最后的节点
                body.removeLast();
                //判断蛇是否撞墙
                head = body.getFirst();
                if(head.getX()<0||head.getY()<0||head.getX()>=40||head.getY()>=40)
                {
                    isLiving=false;
                }
                //判断是否碰到自己身体
                for (int i = 1; i < body.size(); i++)
                {
                    Node node = body.get(i);
                    if(head.getX()==node.getX()&&head.getY()==node.getY())
                       {
                           isLiving=false;
                       }
                }
            }
        }
        public LinkedList<Node>getBody()
        {
            return body;
        }
        public void setBody(LinkedList<Node>body)
        {
            this.body = body;
        }

    public Driection getDriection() {
        return driection;
    }

    public void setDriection(Driection driection) {
        this.driection = driection;
    }
    //吃食物
    public void eat(Node food)
    {
        //获取蛇头
        Node head = body.getFirst();
        switch (driection)
        {
            case UP:
                //在蛇头的上边添加一个节点
                body.addFirst(new Node(head.getX(), head.getY() - 1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }
    }
}

