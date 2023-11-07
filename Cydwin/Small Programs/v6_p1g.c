main(){
	int i, z, y, x, s, n; 
    scanf("%d", &n);
	printf("n=%d\n", n);

	for (i=1;i<=n;i++){
		x=i*2;
		y=i+(i+1);
		z=x*y;
		s=s+z;
	}

printf("%d\n", s);
    
}

/*1*2 * 1+(1+1) = 6
2*2 * 2+(2+1) = 20
3*2 * 3+(3+1) = 42*/
