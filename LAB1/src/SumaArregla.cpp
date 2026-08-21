#include <iostream>
using namespace std;
int sumarArreglo(int arreglo[], int tamano) {
    int suma = 0;
    for (int i = 0; i < tamano; i++) {
        suma += arreglo[i];
    }
    return suma;
}
int main() {
    int n;
    cout << "Introduce el tamaño del arreglo: ";
    cin >> n;
    int arreglo[n];
    cout << "Introduce los elementos del arreglo:" << endl;
    for (int i = 0; i < n; i++) {
        cin >> arreglo[i];
    }
    cout << "La suma es: " << sumarArreglo(arreglo, n) << endl;
    return 0;
}
