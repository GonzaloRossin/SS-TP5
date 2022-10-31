from matplotlib import pyplot as plt
import numpy as np


def ej1(particles, times, frecuencias):
    qs = []
    q_errors = []
    for i in range(len(frecuencias)):
        plt.plot(times[i], particles[i], "k-o", label="ω = " + str(frecuencias[i]))
        aux = np.polyfit(times[i], particles[i], 1)
        print(aux)
        qs.append(aux[1] / aux[0])
        q_errors.append(i + 1)
    plt.xlabel("Time(s)", fontsize=16)
    plt.ylabel("Number of particles", fontsize=16)
    plt.legend()
    plt.show()
    ej1_secondplot(frecuencias, qs, q_errors)


def ej1_secondplot(frecuencias, qs, errors):
    plt.scatter(frecuencias, qs)
    plt.errorbar(frecuencias, qs, yerr=errors, fmt="o")
    plt.xlabel("Frequency", fontsize=16)
    plt.ylabel("Q", fontsize=16)
    plt.show()


def ej2(particles, times, ds):
    qs = []
    q_errors = []
    for i in range(len(ds)):
        aux = np.polyfit(times[i], particles[i], 1)
        print(aux)
        qs.append(aux[1] / aux[0])
        q_errors.append(i + 1)
    plt.scatter(ds, qs)
    plt.errorbar(ds, qs, yerr=q_errors, fmt="o")
    plt.xlabel("Caudal", fontsize=16)
    plt.ylabel("Q", fontsize=16)
    plt.show()
