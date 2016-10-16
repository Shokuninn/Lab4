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
	
	//write方法
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
			
			//Entry：返回此映射中包含的映射关系的Set视图
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
			//因为var中可能存在多个key，如x,y,
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
			//用来判断左边是小写字母，右边是数字
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
						//移除映射关系。
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
	
//write 方法：因为simplify中每次算值就将关系解除了，所以再用的话肯定会挂，所以每调用一次就set一次；
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
	
	//set方法，将表达式按符号“+”拆成相应的部分，然后用coe[i]存储数，Map存储变量和幂数；
	public static boolean set(String st)
	{
		input  = st;
		String poly[] = st.split("\\+");
		num = poly.length;
		for(int i=0;i<num;i++)
		{
			var[i] = new HashMap<String,Integer>();
			//var1[i] = new HashMap<String,Integer>();
			//声明一个map
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
				//coe[i]对应相关的每个阶段中数字，与它相对应的是var[i];
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