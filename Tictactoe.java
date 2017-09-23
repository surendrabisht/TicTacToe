import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Tictactoe extends Frame implements ActionListener
{
 Button b[]=new Button[9];
 String p1,p2,p1n,p2n;
 Start parent;
 int click;
 int turn;
Tictactoe(String p1,String p2,Start ob)
{
	p1n = JOptionPane.showInputDialog(this,"Player X : ");
	p2n = JOptionPane.showInputDialog(this,"Player 0 : ");
	if(p1n==null)
	p1n="Player X";
	if(p2n==null)
	p2n="Player 0";
	JOptionPane.showMessageDialog(this,p1n+" Plays first");
	click=0;
    parent=ob;
	this.p1=p1;
	this.p2=p2;

  setLayout(new GridLayout(3,3));
  setSize(300,300);
  setLocation(Get.size(getSize()));                                                             // fixing application at centre
  setResizable(false);
  WindowAdapter w = new WindowAdapter()
  		{
  			public void windowClosing(WindowEvent we)
  			{

				dispose();
  				parent.dispose();
  			}
  		};
 addWindowListener(w);


  for(int i=0;i<9;i++)                                                    // adding buttons to frame & registering to actionEvent
   {
	   b[i]=new Button("");
 	  add(b[i]);
 	  b[i].addActionListener(this);
  }

turn=0;
setTitle(p1n+" 's turn .");
setVisible(true);
}


public void actionPerformed(ActionEvent ae)
{
	for(int i=0;i<9;i++)
	{
     if(ae.getSource()==b[i] && b[i].getLabel()=="")
       {
                    if(turn==0)
                      {
                           b[i].setLabel(p1);
                             turn=1;
                           setTitle(p2n+" 's turn .");
                           click++;
                       }
                   else
                   {
		                  b[i].setLabel(p2);
		                      turn=0;
		                 setTitle(p1n+" 's turn .");
		                  click++;
	               }
	      break;
       }
   }

    check();
}



void check()
{
for(int i=0;i<3;i++)
if(b[i].getLabel() == b[i+3].getLabel() && b[i+3].getLabel() == b[i+6].getLabel()  &&  b[i].getLabel()!="")
win(b[i].getLabel());

for(int i=0;i<=6;i=i+3)
if(b[i].getLabel() == b[i+1].getLabel() && b[i+1].getLabel() == b[i+2].getLabel()  &&  b[i].getLabel()!="")
win(b[i].getLabel());


if(b[0].getLabel() == b[4].getLabel() && b[4].getLabel() == b[8].getLabel()  &&  b[0].getLabel()!="")
win(b[0].getLabel());


if(b[2].getLabel() == b[4].getLabel() && b[4].getLabel() == b[6].getLabel()  &&  b[2].getLabel()!="")
win(b[2].getLabel());

if(click==9)
win("Match draw..");
}

void win(String str)
{
	if(str==p1)
	str=p1n+"  wins...!!   ";
	else if(str==p2)
	str=p2n+"  wins...!!   ";
	else
	str=str+" U both played well ";

    click=0;
	setTitle(" end of game");
	JOptionPane.showMessageDialog(this,str);
	dispose();
	parent.setVisible(true);

}


public static void main(String args[])
{

	new Start();

}
}



class Start extends Frame implements ActionListener
{

String a,b;                                          //  signs of players
Button b1,b2;
JLabel pic;
Start()
{
   setLayout(new FlowLayout());
   setBackground(new Color(50,50,0));
   setSize(500,500);
   setLocation(Get.size(getSize()));                                                              // fixing application at centre
   setResizable(false);

b1=new Button("New game");
b2 =new Button("quit !");
b1.addActionListener(this);
b2.addActionListener(this);
add(b1);
add(b2);

pic=new JLabel(new ImageIcon("abc.jpg"));
add(pic);

 WindowAdapter w = new WindowAdapter()
  		{
  			public void windowClosing(WindowEvent we)
  			{
  				dispose();
  			}
  		};
addWindowListener(w);

	a="X";
	b="O";
setVisible(true);
}

public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b1)
   {
	setVisible(false);
	new Tictactoe(a,b,this);
       }
else
   {
	dispose();
      }
 }

}


class Get
{
	public static Point size(Dimension d2)
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d1 = tk.getScreenSize();
        Point p=new Point((d1.width-d2.width)/2,(d1.height-d2.height)/2);
        return p;
		}
}