#include <stdio.h>
#include <stdbool.h>
void wait_time(int bt[],int n,int quantam,int wt[]){
int rem_bt[n],i,t,c=0;
bool completed;
for(i=0;i<n;i++){
rem_bt[i]=bt[i];
}
t=0;
while(1){
completed = true;
for(i=0;i<n;i++){
if (rem_bt[i] != 0){
completed =false;
if(rem_bt[i]>quantam){
t=t + quantam;
rem_bt[i] = rem_bt[i]-quantam;
}
else{
t = t + rem_bt[i];
wt[i] = t - bt[i];
rem_bt[i] = 0;
}//else
}//!=0
}//for loop
if (completed == true)
break;
}//while
}//function
float turn_around_time(int bt[],int wt[],int n,int tat[]){
int i;
float avg_tat=0;
for(i=0;i<n;i++){
tat[i]=0;
tat[i]= bt[i] + wt[i];
avg_tat += tat[i];
}
avg_tat=avg_tat/n;
return avg_tat;
}
float average_wait_time(int wt[],int n){
int i;
float avg_wt=0;
for(i=0;i<n;i++){
avg_wt +=wt[i];
}
avg_wt = avg_wt/n;
return avg_wt;
}
void main()
{
int n ,i,bt[10], wt[10]={0,0,0,0,0,0,0,0,0,0},quantam, tat[10];
float avg_wt,avg_tat;
printf("Enter number of processes: ");
scanf("%d",&n);
for (i=0;i<n;i++){
printf("Burst time for process %d: ",i+1);
scanf("%d",&bt[i]);
}
printf("Enter quantam: ");
scanf("%d",&quantam);
wait_time(bt,n,quantam,wt);
avg_tat=turn_around_time(bt,wt, n,tat);
avg_wt =average_wait_time(wt, n);
printf("PNO\tBT\tWT\tTAT\n");
for(i=0;i<n;i++){
printf("%d\t%d\t%d\t%d\n",i+1,bt[i],wt[i],tat[i]);
}
printf("Average wait time %f:\n",avg_wt);
printf("Average turn around time %f:",avg_tat);
}
