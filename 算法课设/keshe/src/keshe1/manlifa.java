package keshe1;

public class manlifa {
    int i,j,k;
    static char[] l=new char[80];
    static String xian="";//���ڴ�����н��
    
    //���캯��
	public manlifa(int i,int j,int k){
		this.i=i;
		this.j=j;
		this.k=k;
	}
	
	//�����㷨
	public static int fun(int m,int n,int i,int k,int ways) 
	{  //waysΪ��������¼���ٸ�����
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
			  
			  System.out.print(ways+"Ϊ");
			  System.out.println(l);
			  
			  //��ȡ�ַ����еı���
			  StringBuffer sb = new StringBuffer();
			  for(int d=0;l[d]!='\0';d++){
				sb.append(l[d]);
				}
			  xian=xian+Integer.toString(ways)+"Ϊ     "+sb.toString()+"\n";
			  
		   }
		  //����ҵ���k�����룬������ñ���
		  if(k==ways) {
			  
			  System.out.print("��"+ways+"����Ϊ:");
			  
			  StringBuffer sb = new StringBuffer();
			  for(int d=0 ;l[d]!='\0' ;d++){
				sb.append(l[d]);
				}
			  
			  xian=xian+"��Ҫ��ĵ�"+Integer.toString(ways)+"������"+"Ϊ: \n"+sb.toString()+"\n";
			  System.out.println(l);
			 
		}
		
	   } 
	 
	return ways;	 
	} 

	
	public void man()
	{ 
    //��ʾ���еı���
	 //fun(i,j,0,0,0);
    //��ʾ��k������
	 fun(i,j,0,k,0); 

	}
	
	//����һ���ַ������Ա���ʾ��������
	public String xian(){
		System.out.println(xian);
		return xian;
	}
}
