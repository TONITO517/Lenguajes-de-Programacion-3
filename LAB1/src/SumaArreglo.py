def sumar_arreglo(arreglo):
    suma = 0
    for num in arreglo:
        suma += num
    return suma

n = int(input("Introduce el tamaño del arreglo: "))
arreglo = []
print("Introduce los elementos del arreglo:")
for i in range(n):
    arreglo.append(int(input()))

print("La suma es:", sumar_arreglo(arreglo))