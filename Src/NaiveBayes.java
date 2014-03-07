package src;

import java.io.*;
import java.util.*;


public class NaiveBayes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f1 = new File(args[0]);
		File f2 = new File(args[1]);
		String line="",classvalue="";		
		double priorpos,priorneg,likehood,likehood2,accuracy;
		int linecount=0,right=0;
		Scanner sc;		
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		HashMap<String,Integer> hm1 = new HashMap<String,Integer>();			
		String splitted[],split[],split1[];			
		try
		{		
			System.setOut(new PrintStream(new File("IP.txt")));
			sc = new Scanner(new FileReader(f1));		
			while(sc.hasNext())//Reading files from training data and computing probabilities
			{
				linecount++;
				line = sc.nextLine();
				splitted = line.split(",");				
				classvalue = line.substring(line.lastIndexOf(","),line.length());
				if(hm.containsKey(classvalue)) // Contains unique values of classlabels
					hm.put(classvalue, hm.get(classvalue).intValue()+1);
				else						
					hm.put(classvalue,1);				
				for(int i=0;i<splitted.length-1;i++)
				{
					if(hm1.containsKey(splitted[i]+","+Integer.toString(i)+classvalue))//contains individual attributes along with their corresponding classlabel count
						hm1.put(splitted[i]+","+Integer.toString(i)+classvalue, hm1.get(splitted[i]+","+Integer.toString(i)+classvalue).intValue()+1);
					else					
						hm1.put(splitted[i]+","+Integer.toString(i)+classvalue,1);					
				}				
			}
			
			priorpos=hm.get(",0").floatValue()/linecount;//prior probability of classlabels
			priorneg=hm.get(",1").floatValue()/linecount;
			System.out.println("Right values is "+priorpos);
			System.out.println("Left values is "+priorneg);
			System.out.println("Linecount values is "+linecount);
			sc = new Scanner(new FileReader(f2));
			linecount=0;
			while(sc.hasNext())//loop for test data file.
			{
				likehood=likehood2=1;
				linecount++;
				line = sc.nextLine();
				split = line.split(",");
				classvalue = line.substring(line.lastIndexOf(","),line.length()); // classlabel of test data to check for accuracy
				for(int i=0;i<split.length-1;i++) // Compute likelihood for individual values of attributes in test data. 
				{
					if(hm1.containsKey(split[i]+","+Integer.toString(i)+",0"))
					{						
						likehood *= hm1.get(split[i]+","+Integer.toString(i)+",0").floatValue()/hm.get(",0").floatValue();						
					}
					if(hm1.containsKey(split[i]+","+Integer.toString(i)+",1"))
					{						
						likehood2 *= hm1.get(split[i]+","+Integer.toString(i)+",1").floatValue()/hm.get(",1").floatValue();				
					}
				}	
				likehood = likehood*priorpos; // Multiply with prior probability
				likehood2 = likehood2*priorneg; // Multiply with prior probability
				if(likehood > likehood2)
				{
					if(classvalue.equals(",0"))
						right++;//Computes Accuracy for correctly classified values
					System.out.println(line+"--->Predicted is 0 likelihood-->"+likehood);
				}
				else
				{
					if(classvalue.equals(",1"))
						right++;
					System.out.println(line+"--->Predicted is 1 likelihood-->"+likehood2);				
				}				
			}
			accuracy=right/(double)linecount;
			System.out.println("Total number of instances in test file"+linecount);
			System.out.println("Correctly classified are "+right);			
			System.out.println("Accuracy classified are "+accuracy*100.00+"%");
			sc = new Scanner(new FileReader(f1));
			linecount=right=0;
			hm1.clear();
			
			while(sc.hasNext())//loop for test data file.
			{
				likehood=likehood2=1;
				linecount++;
				line = sc.nextLine();
				split1 = line.split(",");
				classvalue = line.substring(line.lastIndexOf(","),line.length()); // classlabel of test data to check for accuracy
				for(int i=0;i<split1.length-1;i++) // Compute likelihood for individual values of attributes in test data. 
				{
					if(hm1.containsKey(split1[i]+","+Integer.toString(i)+",0"))
					{						
						likehood *= hm1.get(split1[i]+","+Integer.toString(i)+",0").doubleValue()/hm.get(",0").doubleValue();						
					}
					if(hm1.containsKey(split1[i]+","+Integer.toString(i)+",1"))
					{						
						likehood2 *= hm1.get(split1[i]+","+Integer.toString(i)+",1").doubleValue()/hm.get(",1").doubleValue();				
					}
				}	
				likehood = likehood*priorpos; // Multiply with prior probability
				likehood2 = likehood2*priorneg; // Multiply with prior probability
				if(likehood > likehood2)
				{
					if(classvalue.equals(",0"))
						right++;//Computes Accuracy for correctly classified values
					System.out.println(line+"--->Predicted is 0 likelihood-->"+likehood);
				}
				else
				{
					if(classvalue.equals(",1"))
						right++;
					System.out.println(line+"--->Predicted is 1 likelihood-->"+likehood2);				
				}				
			}
			accuracy=right/(double)linecount;
			System.out.println("Total number of instances in train file"+linecount);
			System.out.println("Correctly classified are "+right);			
			System.out.println("Accuracy classified are "+accuracy*100.00+"%");			
			sc.close();
		}catch(Exception e)
		{e.printStackTrace();}
	}
}