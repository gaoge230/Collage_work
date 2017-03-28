package keshe1;

public class manlifa {
    int i,j,k;
    static char[] l=new char[80];
    static String xian="";//用于存放运行结果
    
    //构造函数
	public manlifa(int i,int j,int k){
		this.i=i;
		this.j=j;
		this.k=k;
	}
	
	//蛮力算法
	public static int fun(int m,int n,int i,int k,int ways) 
	{  //ways为计数，记录多少个排列
	   if(m>0){
		   l[i]='-';
		   ways=fun(m-1,n,i+1,k,ways);
		   } 
	   
	   if(n>0){
		   l[i]='o';
		   ways=fun(m,n-1,i+1,k,ways);
		   } 
	   if(m+n==0) 
	   { 
		  l[i]='\0'; 
	      ways++;
		  if(k==0){
			  
			  System.out.print(ways+"为");
			  System.out.println(l);
			  
			  //提取字符串中的编码
			  StringBuffer sb = new StringBuffer();
			  for(int d=0;l[d]!='\0';d++){
				sb.append(l[d]);
				}
			  xian=xian+Integer.toString(ways)+"为     "+sb.toString()+"\n";
			  
		   }
		  //如果找到第k个编码，就输出该编码
		  if(k==ways) {
			  
			  System.out.print("第"+ways+"密码为:");
			  
			  StringBuffer sb = new StringBuffer();
			  for(int d=0 ;l[d]!='\0' ;d++){
				sb.append(l[d]);
				}
			  
			  xian=xian+"您要求的第"+Integer.toString(ways)+"个编码"+"为: \n"+sb.toString()+"\n";
			  System.out.println(l);
			 
		}
		
	   } 
	 
	return ways;	 
	} 

	
	public void man()
	{ 
    //显示所有的编码
	 //fun(i,j,0,0,0);
    //显示第k个编码
	 fun(i,j,0,k,0); 

	}
	
	//返回一个字符串，以便显示到界面上
	public String xian(){
		System.out.println(xian);
		return xian;
	}
}
