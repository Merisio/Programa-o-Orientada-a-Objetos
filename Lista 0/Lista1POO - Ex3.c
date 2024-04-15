#include <stdio.h>
#include <stdlib.h>

void fazMat(int **mat, int n, int tam){
    int i, j;
    
    for (i = 0; i < tam; i++){
        for (j = 0; j < tam; j++){
            if (i == j)
                mat[i][j] = 0;
            else if (i < j)
                mat[i][j] = n;
            else
                mat[i][j] = -n;
        }
    }

    for (i = 0; i < tam; i++){
        printf("\n");
        for (j = 0; j < tam; j++)
            printf("%d ", mat[i][j]);
    }

}

int main(){
    int **mat;
    int n, tam;

    printf ("digite o tamanho da matriz quadrada: ");
    scanf ("%d", &tam);

    mat = (int**) malloc(sizeof(int*)*tam);

    for(int i = 0; i < 5; i++)
        mat[i] = (int*) malloc(sizeof(int)*tam);

    printf ("digite um numero inteiro: ");
    scanf ("%d", &n);

    fazMat(mat, n, tam);

    return 0;
}