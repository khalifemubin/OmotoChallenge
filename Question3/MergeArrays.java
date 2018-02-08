public static void mergeArrays(int[] A, int[] B) {

	int[] arrMerged = new int[A.length + B.length];
	int i = 0, j = 0, k = 0;
	while (i < A.length && j < B.length)
	{
		if (A[i] < B[j])
		{
			arrMerged[k] = A[i];
			i++;
		}
		else
		{
			arrMerged[k] = B[j];
			j++;
		}
		k++;
	}

	while (i < A.length)
	{
		arrMerged[k] = A[i];
		i++;
		k++;
	}

	while (j < B.length)
	{
		arrMerged[k] = B[j];
		System.out.println(arrMerged[k]);
		j++;
		k++;
	}
}
