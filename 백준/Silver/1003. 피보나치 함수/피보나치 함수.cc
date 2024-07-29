#include <stdio.h>
#include <iostream>
using namespace std;

int dt[5000][2];

int main()
{
	int n;
	int a;
	cin>>n;
	dt[0][0]=1;
	dt[0][1]=0;
	dt[1][0]=0;
	dt[1][1]=1;

	for (int i=2;i<=40;i++)
	{
		dt[i][0]=dt[i-1][0]+dt[i-2][0];
		dt[i][1]=dt[i-1][1]+dt[i-2][1];
	}
	for (int i=0;i<n;i++)
	{
		scanf("%d",&a);
		printf("%d %d\n",dt[a][0],dt[a][1]);
	}
	return 0;
}
