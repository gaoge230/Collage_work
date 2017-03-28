package keshe1;

public class dongtaiguihua {

	int m,n,k;
	int M=1000000100;//防止越界
	static int[][] bino=new int[201][201];//用于存放杨辉三角
	
	public dongtaiguihua(int m,int n,int k)
	{
		this.m=m;
		this.n=n;
		this.k=k;
	}
	
	
	
	//生成所有必要的二项式系数，即生成杨辉三角
	
	public void calBino()
	{
	   //杨辉三角，这个数等于它上一行的对应相邻两数之和
	   for(int i=0;i<=200;++i)
	  {
	    bino[i][0]=bino[i][i]=1;
	    
		for(int j=1;j<i;++j)
			{
			 bino[i][j]=Math.min(M,bino[i-1][j-1]+bino[i-1][j]);
			}
	   }
	
	}

	
	//动态规划算法
	public String kth(int nn,int mm,int skip)
	{
	   //如果nn=0，即“-”为零，输出mm个“o”
	   if(nn==0){
		   
		   String a="";
		   for(int i=1;i<=mm;i++)
			   a=a+"o";
		   return a;
		   }
	   
	   if(skip<bino[nn+mm-1][nn-1])	     
	       return "-"+kth(nn-1,mm,skip);
	   else
           return "o"+kth(nn,mm-1,skip-bino[nn+mm-1][nn-1]);

	}

	public String  main() 
	{ 
	 calBino();
     return kth(n,m,k);	 
	}
}
