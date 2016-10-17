package computing;

import java.util.HashMap;
import java.util.Map;

public class Expression2
{
	public static String input = new String();
	public static Map<String,Integer> var[] = new Map[1000];
	public static float[] coe = new float[1000];
	public static int num;
	
	public static int judge(String term)
	{
		int now = 0;
		for(int i=0;i<term.length();i++)
		{
			if((now == 0 || now == 1) && (Character.isDigit(term.charAt(i))||term.charAt(i)=='.'))
			{
			   now = 1;	
			}
			else if((now == 0 || now == 2) && (Character.isLowerCase(term.charAt(i))||Character.isUpperCase(term.charAt(i))))
			{
				now = 2;
			}
			else 
				return 0;
		}
		return now;
	}
	
	//write����
	public static void write()
	{
		coe[num] = 0;
		int write_flag;
		boolean flag = false;
		for(int i=0;i<num;i++)
		{
			write_flag=0;
			if(flag && var[i].size()!=0 && coe[i] != 0)
				System.out.print("+");
			if(coe[i] == 0)
				continue;
			if(var[i].size() != 0)
			{
				if((coe[i]>0)&&(coe[i]!=1))
					System.out.print((float)(Math.round(coe[i]*10)/10.0));
				flag = true;
			}
			else
				coe[num] += coe[i];
			
			//Entry�����ش�ӳ���а�����ӳ���ϵ��Set��ͼ
			for(Map.Entry<String, Integer> m:var[i].entrySet())
			{
				if(write_flag==0 && coe[i]==1)
					System.out.print(m.getKey());
				else
					System.out.print("*"+m.getKey());
				if(m.getValue()!=1)
					System.out.print("^"+m.getValue());
				write_flag++;
			}
			//��Ϊvar�п��ܴ��ڶ��key����x,y,
		}
		if(coe[num] != 0)
		{
			if(flag)
				System.out.print("+");
		    System.out.print(coe[num]);
		}	
		System.out.println();
		
	}
	
	public static void simplify(String[] op)
	{
		for(int i=1;i<op.length;i++)
		{
			String tmp[] = op[i].split("=");
			//System.out.println(tmp[0]+" "+tmp[1]);
			//�����ж������Сд��ĸ���ұ�������
			if(judge(tmp[0]) == 2 && judge(tmp[1]) == 1)
			{
				boolean flag = false;
				for(int j=0;j<num;j++)
				{
					if(var[j].containsKey(tmp[0]))
					{
						flag = true;
						for(int k=1;k<=var[j].get(tmp[0]);k++)
							coe[j] *= Float.valueOf(tmp[1]);
						var[j].remove(tmp[0]);
						//�Ƴ�ӳ���ϵ��
					}
				}
				if(!flag)
				{
					System.out.println("input error(variable not exist)");
					return;
				}
			}
			else
			{
				System.out.println("input error(invalid input)");
				return;
			}
		}
		write();
	}
	
//write ��������Ϊsimplify��ÿ����ֵ�ͽ���ϵ����ˣ��������õĻ��϶���ң�����ÿ����һ�ξ�setһ�Σ�
	public static void derivative(String dx)
	{
		boolean flag = false;
		for(int i=0;i<num;i++)
		{
			if(var[i].containsKey(dx))
			{
				flag = true;
				int m = var[i].get(dx);
				coe[i] *= m;
				if(m-1!=0)
					var[i].put(dx, m-1);
				else
					var[i].remove(dx);
			}
			else
				coe[i] = 0;
		}
		if(!flag)
			System.out.println("Error,no variable");
		else
			write();
	}
	
	//set�����������ʽ�����š�+�������Ӧ�Ĳ��֣�Ȼ����coe[i]�洢����Map�洢������������
	public static boolean set(String st)
	{
		input  = st;
		String poly[] = st.split("\\+");
		num = poly.length;
		for(int i=0;i<num;i++)
		{
			var[i] = new HashMap<String,Integer>();
			//var1[i] = new HashMap<String,Integer>();
			//����һ��map
			coe[i] = 1;
			String term[] = poly[i].split("\\*");
			
			for(int j=0;j<term.length;j++)
			{
				int sign = judge(term[j]);
				if(sign == 1)
				{
					//System.out.println(term[j]);
					//coe[i] *= Integer.parseInt(term[j]);
					coe[i] *= Float.valueOf(term[j]);
				}
				//coe[i]��Ӧ��ص�ÿ���׶������֣��������Ӧ����var[i];
 				else if(sign == 2)
				{
					if(var[i].containsKey(term[j]))
					{
						var[i].put(term[j],var[i].get(term[j])+1);
						//var1[i].put(term[j], var1[i].get(term[j]+1));
					}
					else
					{
						var[i].put(term[j], 1);
						//var1[i].put(term[j], 1);
					}
				}
 				else
 					return false;
			}
			
		}
		return true;
	}
}

//git�޸�3
//git�޸�4
//git change2.4 on C4
//git change2.5 on B1
//git change2.7 on B2
remotes/Lab1/1142800130
