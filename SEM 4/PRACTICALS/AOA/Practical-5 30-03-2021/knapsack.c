#include<stdio.h>
#include<time.h>
#include<conio.h>
void knapsack(float capacity, int n, float weight[], float profit[])
{
	float x[20], totalprofit,y;
	int i,j;
	y=capacity;
	totalprofit=0;
	for(i=0;i < n;i++)
		x[i]=0.0;
	for(i=0;i < n;i++)
	{
		if(weight[i] > y)
			break;
		else
		{
			x[i]=1.0;
			totalprofit=totalprofit+profit[i];
			y=y-weight[i];
		}
	}
	if(i < n)	
		x[i]=y/weight[i];
	totalprofit=totalprofit+(x[i]*profit[i]);
	printf("The selected elements are:-\n ");
	for(i=0;i < n;i++)
		if(x[i]==1.0)
			printf("\nProfit is %f with weight %f ", profit[i], weight[i]);
		else if(x[i] > 0.0)
			printf("\n%f part of Profit %f with weight %f", x[i], profit[i], weight[i]);
	printf("\nTotal profit for %d objects with capacity %f = %f\n\n", n, capacity,totalprofit);
}			
void main()
{
	float weight[20],profit[20],ratio[20], t1,t2,t3;
	int n;
	time_t start,stop;
	float capacity;
	int i,j;
	printf("Enter number of objects:  ");
	scanf("%d", &n);
	printf("\nEnter the capacity of knapsack: ");
	scanf("%f", &capacity);
	for(i=0;i < n;i++)
	{
		printf("\nEnter %d(th)  profit: ", (i+1));
		scanf("%f", &profit[i]);
		printf("Enter %d(th)  weight: ", (i+1));
		scanf("%f", &weight[i]);
		ratio[i]=profit[i]/weight[i];
	}
	start=time(NULL);
	for(i=0;i < n;i++)
		for(j=0;j < n;j++)
		{
			if(ratio[i] > ratio[j])
			{
				t1=ratio[i];
				ratio[i]=ratio[j];
				ratio[j]=t1;
				t2=weight[i];
				weight[i]=weight[j];
				weight[j]=t2;
				t3=profit[i];
				profit[i]=profit[j];
				profit[j]=t3;
			}
		}
	knapsack(capacity,n,weight,profit);
	stop=time(NULL);
	printf("\nKnapsack = %f\n", difftime(stop,start));
	getch();
}