package computing;

import java.util.*;

public class Expression {
 
	public static void main(String[] args)
	{
		Expression2 a = new Expression2();
		Scanner in = new Scanner(System.in);
		String phrase=new String();
		while(in.hasNextLine())
		{
			String input = in.nextLine();
			if(input.charAt(0) == '!')
			{
				String op[] = input.split(" ");
				if(op[0].equals("!simplify"))
				{
					Expression2 b = new Expression2();
					//��Ϊÿ����һ�Σ�map��ϵ�������¸ı䣬���Ծͻ����ÿ��setһ��
					b.set(phrase);
					b.simplify(op);
				}
				else
				{
					Expression2 c = new Expression2();
					int len = input.length();
					String op2 = input.substring(4);
			        c.set(phrase);
					c.derivative(op2);
				}
			}
			else
			{
				if(a.set(input)){
					a.write();
					phrase=input;
				}
				else
					System.out.println("Input Error!");
			}
		}
	}
}

//git�޸�1
//git�޸�2
//git change2.4 on C4
//git change2.5 on B1
//git change2.7 on B2
remotes/Lab1/1142800130
