import matplotlib.pyplot as plt

LINEAS_DATOS = [3,4,8,9,13,14,18,19,23,24,28,29,33,34]
NOMBRE_DATOS = ["Datos nomrales - Media", "Datos normales - Maximo",
                "Iteracciones bajas (200) - Media", "Iteracciones bajas (200) - Maximo",
                "Iteraciones altas (5000): - Media", "Iteraciones altas (5000): - Maximo",
                "Cruce alto (siempre cruza) - Media", "Cruce alto (siempre cruza) - Maximo",
                "Cruce bajo (20%) - Media", "Cruce bajo (20%) - Maximo",
                "Mutacion baja (10%) - Media", "Mutacion baja (10%) - Maximo",
                "Mutacion alta (80%) - Media", "Mutacion alta (80%) - Maximo"]
MOSTRAR_GRAFICAS = False
GUARDAR_GRAFICAS = True

def comprobarNumero(num):
    try:
        float(num)
        return True
    except:
        return False

def main():

    if len(LINEAS_DATOS) != len(NOMBRE_DATOS):
        print("[ERROR]: No has puesto el mismo numero de lineas de datos que de nombres para los datos")
        return 0
    dats = list(map(lambda x:x-1, LINEAS_DATOS))
    with open("datosSalida.txt") as file:
        for i, text in enumerate(file):
            if i in dats:
                data = text
                data = list(map(lambda x: float(x), filter(comprobarNumero, data.split("	"))))
    
                fig, ax = plt.subplots()
                ax.plot(range(1, len(data)+1), data)
                plt.title(NOMBRE_DATOS[dats.index(i)])
                plt.savefig(NOMBRE_DATOS[dats.index(i)] + ".jpg", bbox_inches='tight')
                plt.show()


if __name__ == "__main__":
    main()