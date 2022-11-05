from matplotlib import pyplot as plt
import numpy as np
import pandas as pd


def ej1():
    particles = []
    times = []
    frecuencias = [5.0, 10.0, 15.0, 20.0, 30.0, 50.0]

    df = pd.read_json('ParticlesvsTime.json')
    df2 = pd.read_json('ParticlesvsTime2.json')
    df3 = pd.read_json('ParticlesvsTime3.json')
    df4 = pd.read_json('ParticlesvsTime4.json')
    valueLen = len(df['5.0'])
    for frecuencia in frecuencias:
        averageList = []
        for i in range(valueLen):
            data = [df[str(frecuencia)][i], df2[str(frecuencia)][i], df3[str(frecuencia)][i],
                    df4[str(frecuencia)][i]]
            averageList.append(np.average(data))
        particles.append(averageList)

    for frecuencia in frecuencias:
        times.append(list(df['time']))
        particles.append(list(df[str(frecuencia)]))

    qs = []
    q_errors = []
    for i in range(len(frecuencias)):
        plt.plot(times[i], particles[i], "k", label="ω = " + str(frecuencias[i]))
        aux = np.polyfit(times[i], particles[i], 1)
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

def appendFrequency():
    df = pd.read_json('ParticlesvsTime.json')
    df2 = pd.read_json('ParticlesvsTime2.json')
    df3 = pd.read_json('ParticlesvsTime3.json')
    df4 = pd.read_json('ParticlesvsTime4.json')

    frequencyLists = []
    frecuencias = [5.0, 10.0, 15.0, 20.0, 30.0, 50.0]
    for frecuencia in frecuencias:
        frequencyList = []
        frequencyList.append(df[str(frecuencia)])
ej1()
