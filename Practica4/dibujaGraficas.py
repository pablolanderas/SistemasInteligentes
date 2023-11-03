import matplotlib.pyplot as plt

DIR_ARCHIVO = "datosSalida.txt"
LINEAS_DATOS = [38,39,43,44]
NOMBRE_DATOS = ["Cruce en 2 puntos - Media", "Cruce en 2 puntos - Maximo",
                "Operacion de reemplazo generacional - Media", 
                "Operacion de reemplazo generacional - Maximo",]
MOSTRAR_GRAFICAS = True
GUARDAR_GRAFICAS = False

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
    with open(DIR_ARCHIVO) as file:
        for i, text in enumerate(file):
            if i in dats:
                data = text
                data = list(map(lambda x: float(x), filter(comprobarNumero, data.split("	"))))
    
                fig, ax = plt.subplots()
                ax.plot(range(1, len(data)+1), data)
                plt.title(NOMBRE_DATOS[dats.index(i)])
                if GUARDAR_GRAFICAS: plt.savefig(NOMBRE_DATOS[dats.index(i)] + ".jpg", bbox_inches='tight')
                if MOSTRAR_GRAFICAS: plt.show()


if __name__ == "__main__":
    main()