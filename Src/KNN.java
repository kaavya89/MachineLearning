package src;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.*;
import java.util.Map.Entry;

public class KNN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f1 = new File(args[0]);
		File f2 = new File(args[1]);
		int linecount=0,right=0,countvalue=0,k=0,count1,count2;
		double accuracy;
		Scanner sc,sc1;
		ArrayList<String> l = new ArrayList<String>();
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
		String trainsplit[],testsplit[],line,classvalue;			
		try
		{		
			//System.setOut(new PrintStream(new File("IP1.txt")));
			sc = new Scanner(new FileReader(f1));
			sc1 = new Scanner(new FileReader(f2));
			while(sc.hasNext())
			{				
				line=sc.nextLine();
				l.add(line);
			}
			sc1 = new Scanner(new FileReader(f1));
			while(sc1.hasNext())//checking the training data for correctness
			{
				linecount++;				
				count1=count2=k=0;
				line = sc1.nextLine();
				testsplit = line.split(",");
				classvalue = line.substring(line.lastIndexOf(",")+1,line.length());
				for(int i=0;i<l.size();i++) //loop through test data lines and find the hamming code
				{	
					countvalue=0;
					trainsplit = l.get(i).split(",");
					for(int j=0;j<testsplit.length-1;j++)
					{
						if(!testsplit[j].equals(trainsplit[j]))//finding hamming code
							countvalue++;
					}
					if(countvalue!=0)
						tm.put(Integer.toString(i),countvalue);
				}
				
				TreeMap<String,Integer> tm1 = sortByValues(tm);//sorting the hamming code distance in ascending order				
				for(Entry<String, Integer> es:tm1.entrySet())
				{					
					if(k<29)//this is where the k value is changed for different runs. by default set to 1.
					{						
						String temp = l.get(Integer.valueOf(es.getKey()));
						//System.out.println(Integer.valueOf(temp.substring(temp.lastIndexOf(",")+1,temp.length())));
						l2.add(Integer.valueOf(temp.substring(temp.lastIndexOf(",")+1,temp.length())));
						k++;
					}
				}				
				count1=count2=0;
				for(int i=0;i<l2.size();i++)
				{
					//System.out.println("Coming second line");
					int temp = l2.get(i);
					if(temp==0)
						count1++;
					if(temp==1)
						count2++;
				}
				//System.out.println("classified 0 value:"+count1+" classified 1 value:"+count2);
				if(count1>count2)
				{
					//System.out.println("classified 0 value"+count1);
					if("0".equals(classvalue))
						right++;//computing rightly classified instances
				}
				else
				{
					//System.out.println("classified 1 value"+count2);
					if("1".equals(classvalue))
						right++;
				}
				tm.clear();
				l2.clear();
			}
			accuracy=(double)right/(double)linecount;
			//System.out.println("Total number of instances in test file"+linecount);
			//System.out.println("Correctly classified are "+right);			
			System.out.println(accuracy*100.00);
			sc.close();
		}catch(Exception e)
		{e.printStackTrace();}
	}
	//sorting the hashmap using values and not keys
	public static <String, Integer extends Comparable<Integer>> TreeMap<String, Integer> sortByValues(final TreeMap<String, Integer> map) {
	    Comparator<String> valueComparator =  new Comparator<String>() {
	        public int compare(String i1, String i2) {	        	
	            int compare = map.get(i1).compareTo(map.get(i2));	            
	            if (compare == 0) return 1;
	            else return compare;
	        }
	    };
	    TreeMap<String, Integer> sortedByValues = new TreeMap<String, Integer>(valueComparator);
	    sortedByValues.putAll(map);
	    return sortedByValues;
	}
	
}
