from matplotlib import pyplot as plt
import numpy as np
import pandas as pd


def ej1():
    particles = []
    times = []
    frecuencias = ['5.0', '10.0', '15.0', '20.0', '30.0', '50.0']


    valueLen = len(df['5.0'])
    for frecuencia in frecuencias:
        averageList = []
        for i in range(valueLen):
            data = [df[frecuencia][i], df2[frecuencia][i], df3[frecuencia][i],
                    df4[frecuencia][i]]
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
        qs.append(aux[1] / aux[0])
        q_errors.append(i + 1)
    plt.scatter(ds, qs)
    plt.errorbar(ds, qs, yerr=q_errors, fmt="o")
    plt.xlabel("Caudal", fontsize=16)
    plt.ylabel("Q", fontsize=16)
    plt.show()

def calculate_errors():
    frecuencias = ['5.0', '10.0', '15.0', '20.0', '30.0', '50.0']
    df1 = pd.read_json('ParticlesvsTime.json')
    df2 = pd.read_json('ParticlesvsTime2.json')
    df3 = pd.read_json('ParticlesvsTime3.json')
    errors = {}
    qaverage = {}
    timeList = list(df1['time'])
    for frecuencia in frecuencias:
        list1 = list(df1[frecuencia])
        list2 = list(df2[frecuencia])
        list3 = list(df3[frecuencia])
        aux1 = np.polyfit(timeList, list1, 1)
        aux2 = np.polyfit(timeList, list2, 1)
        aux3 = np.polyfit(timeList, list3, 1)
        q1 = aux1[1] / aux1[0]
        q2 = aux2[1] / aux2[0]
        q3 = aux3[1] / aux3[0]
        qs = np.array([q1,q2,q3])
        std = np.std(qs)
        errors[frecuencia] = std
        qaverage[frecuencia] = np.average(qs)
    
    return qaverage, errors

ej1()
