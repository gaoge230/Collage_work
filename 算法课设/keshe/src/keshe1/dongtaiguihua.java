package keshe1;

public class dongtaiguihua {

	int m,n,k;
	int M=1000000100;//��ֹԽ��
	static int[][] bino=new int[201][201];//���ڴ���������
	
	public dongtaiguihua(int m,int n,int k)
	{
		this.m=m;
		this.n=n;
		this.k=k;
	}
	
	
	
	//�������б�Ҫ�Ķ���ʽϵ�����������������
	
	public void calBino()
	{
	   //������ǣ��������������һ�еĶ�Ӧ��������֮��
	   for(int i=0;i<=200;++i)
	  {
	    bino[i][0]=bino[i][i]=1;
	    
		for(int j=1;j<i;++j)
			{
			 bino[i][j]=Math.min(M,bino[i-1][j-1]+bino[i-1][j]);
			}
	   }
	
	}

	
	//��̬�滮�㷨
	public String kth(int nn,int mm,int skip)
	{
	   //���nn=0������-��Ϊ�㣬���mm����o��
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
